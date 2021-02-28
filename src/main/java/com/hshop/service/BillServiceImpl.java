package com.hshop.service;

import com.hshop.configuration.userdetail.UserDetailService;
import com.hshop.dao.model.BillEntity;
import com.hshop.dao.model.BillProductEntity;
import com.hshop.dao.model.ProductEntity;
import com.hshop.dao.model.UserEntity;
import com.hshop.dao.repository.BillProductRepository;
import com.hshop.dao.repository.BillRepository;
import com.hshop.dao.repository.ProductRepository;
import com.hshop.dao.repository.StoreRepository;
import com.hshop.dao.repository.UserRepository;
import com.hshop.dto.BillDTO;
import com.hshop.dto.BillDTO.Bill_Food;
import com.hshop.dto.OrderDTO;
import com.hshop.dto.ProductDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.enums.BillStatus;
import com.hshop.exception.BaseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService{
  @Autowired
  private BillRepository billRepository;

  @Autowired
  private BillProductRepository billFoodRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private StoreRepository storeRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserDetailService userDetailService;

  @Override
  public ResponseDTO<?> search(BillDTO dto,Integer page,Integer size) {
    List<BillDTO> list = new ArrayList<>();
    UserEntity entity = userDetailService.getUsernameFromRequest();

    Sort sort = Sort.by(Sort.Direction.ASC,"id");
    Page<BillEntity> listEntity = billRepository.search(dto, PageRequest
        .of(page-1,size,sort),entity.getId());

    for (BillEntity bill : listEntity){
      list.add(convertBillEntityToDTO(bill));
    }

    return new ResponseDTO<>(200, "find successful", list, list.size(),
        (int) listEntity.toList().size());
  }

  @Override
  public ResponseDTO<?> create(OrderDTO dto) throws Exception {
    if (dto.getListFoodsOrder() == null){
      throw new BaseException(400,"listFoodsOrder is null",null);
    }
    if (dto.getUserId() == null){
      throw new BaseException(400,"userId is null",null);
    }
    for (Map.Entry<Long,Integer> id : dto.getListFoodsOrder().entrySet()){
      if (!productRepository.existsById(id.getKey())){
        throw new BaseException(400,"id food is not exist",id);
      }
    }

    BillEntity billEntity = new BillEntity();
    UserEntity buyer = userRepository.findById(dto.getUserId()).get();

    billEntity.setBuyer(buyer);
    billEntity.setStatus(BillStatus.WAIT_STORE_CONFIRM);

    billRepository.save(billEntity);

    List<BillProductEntity> billFoodEntities = new ArrayList<>();
    for (Map.Entry<Long,Integer> billFood : dto.getListFoodsOrder().entrySet()){
      BillProductEntity billFoodEntity = new BillProductEntity();
      ProductEntity productEntity = productRepository.findById(billFood.getKey()).get();

      billFoodEntity.setFood(productRepository.findById(billFood.getKey()).get());
      billFoodEntity.setBill(billEntity);
      billFoodEntity.setNumber(billFood.getValue());
      billFoodEntity.setStore(productEntity.getStore());
      billFoodEntity.setSeller(productEntity.getSeller());

      billFoodRepository.save(billFoodEntity);
      billFoodEntities.add(billFoodEntity);
    }
    billEntity.setListFoods(billFoodEntities);

    return new ResponseDTO<>(200,"successful",convertBillEntityToDTO(billEntity));
  }

  @Override
  public ResponseDTO<?> update(Long id, OrderDTO dto) throws Exception {
    return null;
  }

  @Override
  public ResponseDTO<?> delete(Long id) throws Exception {
    if (!billRepository.existsById(id)) {
      throw new BaseException(400,"id bill is not exist",id);
    }
    billRepository.deleteById(id);
    return new ResponseDTO<>(200,"successful",null);
  }

  @Override
  public ResponseDTO<?> storeConfirm(Long id) throws Exception {
    BillEntity billEntity = billRepository.findById(id).get();

    if (billEntity.getStatus() == BillStatus.CANCEL){
      throw new BaseException(400,"bill just have cancel",convertBillEntityToDTO(billEntity));
    }
    if (billEntity.getStatus() == BillStatus.DELIVERED){
      throw new BaseException(400,"bill just have delivered",convertBillEntityToDTO(billEntity));
    }
    if (billEntity.getStatus() == BillStatus.STORE_CONFIRM){
      throw new BaseException(400,"bill just have confirm",convertBillEntityToDTO(billEntity));
    }

    billEntity.setStatus(BillStatus.STORE_CONFIRM);

    billRepository.save(billEntity);

    return new ResponseDTO<>(200,"successful",convertBillEntityToDTO(billEntity));
  }

  @Override
  public ResponseDTO<?> cancel(Long id) throws Exception {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.CANCEL);

    billRepository.save(billEntity);

    return new ResponseDTO<>(200,"successful",convertBillEntityToDTO(billEntity));
  }

  @Override
  public ResponseDTO<?> delivered(Long id) throws Exception {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.DELIVERED);

    billRepository.save(billEntity);

    return new ResponseDTO<>(200,"successful",convertBillEntityToDTO(billEntity));
  }

  @Override
  public ResponseDTO<?> dashboard(LocalDate start, LocalDate end) {
    UserEntity userEntity = userDetailService.getUsernameFromRequest();
    List<Map<Long,Long>> data = billRepository.dashboard(start, end,userEntity.getId());

    return new ResponseDTO<>(200,"successful",data);
  }

  public static BillDTO convertBillEntityToDTO(BillEntity entity){
    BillDTO dto = new BillDTO();
    dto.setId(entity.getId());
    long sum = 0;

    List<Bill_Food> listBillFood = new ArrayList<>();
    for (BillProductEntity billFoodEntity : entity.getListFoods()){
      Bill_Food billFood = convertBillFoodEntityToDTO(billFoodEntity);

      sum += billFood.getPrice();

      listBillFood.add(billFood);
    }

    dto.setDate(entity.getCreatedDate());
    dto.setFoods(listBillFood);
    dto.setStatus(entity.getStatus().getString());
    dto.setUser(UserServiceImpl.convertUserEntityToDTO(entity.getBuyer()));
    dto.setTotal(sum);

    return dto;
  }
  public static Bill_Food convertBillFoodEntityToDTO(BillProductEntity entity){
    Bill_Food billFood = new Bill_Food();

    ProductDTO foodDTO = new ProductDTO();
    foodDTO.setId(entity.getFood().getId());
    foodDTO.setName(entity.getFood().getName());
    foodDTO.setNewPrice(entity.getFood().getNewPrice());
    billFood.setFood(foodDTO);

    billFood.setNumber(entity.getNumber());
    billFood.setPrice(entity.getNumber()*entity.getFood().getNewPrice());

    return billFood;
  }
}
