package com.hshop.service;

import com.hshop.configuration.userdetail.UserDetailService;
import com.hshop.dao.model.BillEntity;
import com.hshop.dao.model.BillProductEntity;
import com.hshop.dao.model.ProductEntity;
import com.hshop.dao.model.UserEntity;
import com.hshop.dao.repository.BillProductRepository;
import com.hshop.dao.repository.BillRepository;
import com.hshop.dao.repository.ProductRepository;
import com.hshop.dao.repository.UserRepository;
import com.hshop.dto.BillDTO;
import com.hshop.dto.BillDTO.Bill_Product;
import com.hshop.dto.UserDTO;
import com.hshop.enums.BillStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.library.common.exception.BaseException;
import spring.library.common.exception.DataException;
import spring.library.common.service.AbstractBaseService;

@Service
public class BillServiceImpl
    extends AbstractBaseService<BillEntity,BillDTO,BillRepository> implements BillService{
  @Autowired
  private BillRepository billRepository;

  @Autowired
  private BillProductRepository billProductRepository;

  @Autowired
  private UserDetailService userDetailService;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductServiceImpl productService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserServiceImpl userService;

  @Override
  protected BillRepository getRepository() {
    return billRepository;
  }

  @Override
  public Page<BillDTO> search(BillDTO dto,Pageable page) {
    UserEntity entity = userDetailService.getUsernameFromRequest();
    Page<BillEntity> listEntity = billRepository.search(dto, page, entity.getId());

    return listEntity.map(this::mapToDTO);
  }

  @Override
  protected void specificMapToDTO(BillEntity entity, BillDTO dto) {
    super.specificMapToDTO(entity, dto);
    UserDTO userDTO = userService.mapToDTO(entity.getBuyer());
    dto.setUser(userDTO);
    List<Bill_Product> list = new ArrayList<>();
    long sum = 0;
    for (BillProductEntity billProduct : entity.getListFoods()){
      Bill_Product bp = new Bill_Product();
      bp.setFood(productService.mapToDTO(billProduct.getFood()));
      bp.setNumber(billProduct.getNumber());
      bp.setPrice(billProduct.getFood().getNewPrice());

      list.add(bp);
      sum += billProduct.getFood().getNewPrice()*billProduct.getNumber();
    }
    dto.setFoods(list);
    dto.setTotal(sum);

  }

  @Override
  protected void specificMapToEntity(BillDTO dto, BillEntity entity) {
    super.specificMapToEntity(dto, entity);
    if (dto.getBuyerId() == null || !userRepository.existsById(dto.getBuyerId())){
      throw new DataException.NullOrEmpty("buyer_id");
    }
    if (dto.getListFoodsOrder() == null){
      throw new DataException.NullOrEmpty("listFoodsOrder");
    }
    for (Map.Entry<Long,Integer> id : dto.getListFoodsOrder().entrySet()){
      if (!productRepository.existsById(id.getKey())){
        throw new DataException.NullOrEmpty("id food");
      }
    }
    UserEntity buyer = userRepository.findById(dto.getBuyerId()).get();
    entity.setBuyer(buyer);
    List<BillProductEntity> billFoodEntities = new ArrayList<>();
    for (Map.Entry<Long,Integer> billFood : dto.getListFoodsOrder().entrySet()){
      BillProductEntity billProduct = new BillProductEntity();
      ProductEntity productEntity = productRepository.findById(billFood.getKey()).get();

      billProduct.setFood(productRepository.findById(billFood.getKey()).get());
      billProduct.setBill(entity);
      billProduct.setNumber(billFood.getValue());
      billProduct.setStore(productEntity.getStore());
      billProduct.setSeller(productEntity.getSeller());
      billFoodEntities.add(billProduct);
    }
    entity.setListFoods(billFoodEntities);
  }

  //  @Override
//  public ResponseDTO<?> create(OrderDTO dto) throws Exception {
//    if (dto.getListFoodsOrder() == null){
//      throw new BaseException(400,"listFoodsOrder is null",null);
//    }
//    if (dto.getUserId() == null){
//      throw new BaseException(400,"userId is null",null);
//    }
//    for (Map.Entry<Long,Integer> id : dto.getListFoodsOrder().entrySet()){
//      if (!productRepository.existsById(id.getKey())){
//        throw new BaseException(400,"id food is not exist",id);
//      }
//    }
//
//    BillEntity billEntity = new BillEntity();
//    UserEntity buyer = userRepository.findById(dto.getUserId()).get();
//
//    billEntity.setBuyer(buyer);
//    billEntity.setStatus(BillStatus.WAIT_STORE_CONFIRM);
//
//    billRepository.save(billEntity);
//
//    List<BillProductEntity> billFoodEntities = new ArrayList<>();
//    for (Map.Entry<Long,Integer> billFood : dto.getListFoodsOrder().entrySet()){
//      BillProductEntity billFoodEntity = new BillProductEntity();
//      ProductEntity productEntity = productRepository.findById(billFood.getKey()).get();
//
//      billFoodEntity.setFood(productRepository.findById(billFood.getKey()).get());
//      billFoodEntity.setBill(billEntity);
//      billFoodEntity.setNumber(billFood.getValue());
//      billFoodEntity.setStore(productEntity.getStore());
//      billFoodEntity.setSeller(productEntity.getSeller());
//
//      billFoodRepository.save(billFoodEntity);
//      billFoodEntities.add(billFoodEntity);
//    }
//    billEntity.setListFoods(billFoodEntities);
//
//    return new ResponseDTO<>(200,"successful",mapToDTO(billEntity));
//  }

//  @Override
//  public ResponseDTO<?> delete(Long id) throws Exception {
//    if (!billRepository.existsById(id)) {
//      throw new BaseException(400,"id bill is not exist",id);
//    }
//    billRepository.deleteById(id);
//    return new ResponseDTO<>(200,"successful",null);
//  }

  @Override
  public BillDTO storeConfirm(Long id){
    BillEntity billEntity = billRepository.findById(id).get();

    if (billEntity.getStatus() == BillStatus.CANCEL){
      throw new BaseException(400,"bill just have cancel",mapToDTO(billEntity));
    }
    if (billEntity.getStatus() == BillStatus.DELIVERED){
      throw new BaseException(400,"bill just have delivered",mapToDTO(billEntity));
    }
    if (billEntity.getStatus() == BillStatus.STORE_CONFIRM){
      throw new BaseException(400,"bill just have confirm",mapToDTO(billEntity));
    }

    billEntity.setStatus(BillStatus.STORE_CONFIRM);

    billRepository.save(billEntity);

    return mapToDTO(billEntity);
  }

  @Override
  public BillDTO cancel(Long id) {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.CANCEL);

    billRepository.save(billEntity);

    return mapToDTO(billEntity);
  }

  @Override
  public BillDTO delivered(Long id) {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.DELIVERED);

    billRepository.save(billEntity);

    return mapToDTO(billEntity);
  }

  @Override
  public Page<Map<Long,Long>> dashboard(LocalDate start, LocalDate end,
      Pageable pageable) {
    UserEntity userEntity = userDetailService.getUsernameFromRequest();
    Page<Map<Long,Long>> data = billRepository.dashboard(start, end,userEntity.getId(),pageable);

    return data;
  }
}
