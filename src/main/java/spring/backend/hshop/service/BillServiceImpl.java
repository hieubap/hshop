package spring.backend.hshop.service;

import spring.backend.hshop.configuration.userdetail.UserDetailService;
import spring.backend.hshop.dao.model.BillEntity;
import spring.backend.hshop.dao.model.OrderEntity;
import spring.backend.hshop.dao.model.UserEntity;
import spring.backend.hshop.dao.repository.BillRepository;
import spring.backend.hshop.dao.repository.OrderRepository;
import spring.backend.hshop.dao.repository.ProductRepository;
import spring.backend.hshop.dao.repository.StoreRepository;
import spring.backend.hshop.dao.repository.UserRepository;
import spring.backend.hshop.dto.BillDTO;
import spring.backend.hshop.dto.BillDTO.Bill_Product;
import spring.backend.hshop.dto.UserDTO;
import spring.backend.hshop.enums.BillStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.backend.library.exception.BaseException;
import spring.backend.library.exception.DataException;
import spring.backend.library.service.AbstractBaseService;

@Service
public class BillServiceImpl
    extends AbstractBaseService<BillEntity,BillDTO,BillRepository> implements BillService{
  @Autowired
  private BillRepository billRepository;

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

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private StoreRepository storeRepository;

  @Override
  protected BillRepository getRepository() {
    return billRepository;
  }

  @Override
  protected void specificMapToDTO(BillEntity entity, BillDTO dto) {
    super.specificMapToDTO(entity, dto);
    UserDTO userDTO = userService.mapToDTO(entity.getBuyer());
    dto.setBuyer(userDTO);
    List<Bill_Product> list = new ArrayList<>();
    long sum = 0;
    for (OrderEntity billProduct : entity.getListFoods()){
      Bill_Product bp = new Bill_Product();
      bp.setFood(productService.mapToDTO(billProduct.getFood()));
      bp.setNumber(billProduct.getNumber());
      bp.setPrice(billProduct.getFood().getNewPrice());

      list.add(bp);
      sum += billProduct.getFood().getNewPrice()*billProduct.getNumber();
    }
    dto.setStatusName(BillStatus.getString(entity.getStatus()));
    dto.setFoods(list);
    dto.setTotal(sum);
  }

  @Override
  protected void specificMapToEntity(BillDTO dto, BillEntity entity) {
    super.specificMapToEntity(dto, entity);
    if (dto.getBuyerId() == null || !userRepository.existsById(dto.getBuyerId())){
      throw new DataException.NullOrEmpty("buyerId");
    }
    if (dto.getStoreId() == null || !storeRepository.existsById(dto.getStoreId())){
      throw new DataException.NullOrEmpty("storeId");
    }
    if (dto.getListFoodsOrder() == null){
      throw new DataException.NullOrEmpty("listFoodsOrder");
    }
    UserEntity buyer = userRepository.findById(dto.getBuyerId()).get();
    entity.setBuyer(buyer);
    entity.setStatus(BillStatus.WAIT_STORE_CONFIRM.getValue());
  }

  @Override
  protected void beforeSave(BillEntity entity, BillDTO dto) {
    super.beforeSave(entity, dto);
    for (Map.Entry<Long,Integer> order : dto.getListFoodsOrder().entrySet()){
      if (!productRepository.existsById(order.getKey())){
        throw new DataException.NullOrEmpty("productId");
      }
      if (!productRepository.findById(order.getKey()).get().getStore().getId()
      .equals(dto.getStoreId())){
        throw new BaseException("id " + order.getKey() + " is not on store "
            + productRepository.findById(order.getKey()).get().getStore().getName());
      }
    }
  }

  @Override
  protected void afterSave(BillEntity entity, BillDTO dto) {
    super.afterSave(entity, dto);
    List<OrderEntity> orderEntities = new ArrayList<>();

    for (Map.Entry<Long,Integer> order : dto.getListFoodsOrder().entrySet()){
      if (!productRepository.existsById(order.getKey())){
        throw new DataException.NullOrEmpty("productId");
      }

      OrderEntity billProduct = new OrderEntity();

      billProduct.setProductId(order.getKey());
      billProduct.setFood(productRepository.findById(order.getKey()).get());
      billProduct.setBillId(entity.getId());
      billProduct.setNumber(order.getValue());
      orderEntities.add(billProduct);
    }
    orderRepository.saveAll(orderEntities);
    entity.setListFoods(orderEntities);
  }

  @Override
  public BillDTO storeConfirm(Long id){
    BillEntity billEntity = billRepository.findById(id).get();

    if (billEntity.getStatus().equals(BillStatus.CANCEL.getValue())){
      throw new BaseException(400,"bill just have cancel",mapToDTO(billEntity));
    }
    if (billEntity.getStatus().equals(BillStatus.DELIVERED.getValue())){
      throw new BaseException(400,"bill just have delivered",mapToDTO(billEntity));
    }
    if (billEntity.getStatus().equals(BillStatus.STORE_CONFIRM.getValue())){
      throw new BaseException(400,"bill just have confirm",mapToDTO(billEntity));
    }

    billEntity.setStatus(BillStatus.STORE_CONFIRM.getValue());

    billRepository.save(billEntity);

    return mapToDTO(billEntity);
  }

  @Override
  public BillDTO cancel(Long id) {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.CANCEL.getValue());

    billRepository.save(billEntity);

    return mapToDTO(billEntity);
  }

  @Override
  public BillDTO delivered(Long id) {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.DELIVERED.getValue());

    billRepository.save(billEntity);

    return mapToDTO(billEntity);
  }

  @Override
  public Page<Map<Long,Long>> chart(LocalDate start, LocalDate end,
      Long userId, Pageable pageable) {
    Page<Map<Long,Long>> data = billRepository.dashboard(start, end,userId,pageable);

    return data;
  }

  @Override
  public Page<BillDTO> getOrder(Long storeId, Pageable pageable) {
    return billRepository.getOrder(storeId,pageable).map(this::mapToDTO);
  }

  @Override
  public Page<BillDTO> getBill(Long storeId, Pageable pageable) {
    return billRepository.getBill(storeId,pageable).map(this::mapToDTO);
  }
}
