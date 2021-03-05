package com.hshop.service;


import com.hshop.dao.model.ProductEntity;
import com.hshop.dao.repository.ProductRepository;
import com.hshop.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.library.common.exception.DataException;
import spring.library.common.service.AbstractBaseService;

@Service
public class ProductServiceImpl extends AbstractBaseService<ProductEntity,ProductDTO,ProductRepository>
    implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  protected ProductRepository getRepository() {
    return productRepository;
  }

  @Override
  public Page<ProductDTO> search(ProductDTO foodDTO, Pageable page) {
    Page<ProductEntity> list = productRepository.search(foodDTO,page);
    return list.map(this::mapToDTO);
  }

  @Override
  protected void beforeSave(ProductEntity entity, ProductDTO dto) {
    super.beforeSave(entity, dto);
    if (dto.getName() == null || dto.getName().isEmpty()){
      throw new DataException.NullOrEmpty("name");
    }
    if (dto.getNewPrice() == null || dto.getNewPrice()<0){
      throw new DataException.NullOrEmpty("newPrice");
    }
    if (dto.getNewPrice()>999999999){
      throw new DataException.NotExistData("newPrice cant over 999.999.999");
    }
    if (dto.getOldPrice() != null && dto.getOldPrice()>999999999){
      throw new DataException.NotExistData("oldPrice cant over 999.999.999");
    }
    if (dto.getImg() == null || dto.getImg().isEmpty()){
      throw new DataException.NullOrEmpty("img");
    }
  }

  @Override
  protected void afterSave(ProductEntity entity, ProductDTO dto) {
    super.afterSave(entity, dto);
    dto.setPer(100 - (int) ((double)entity.getNewPrice()*100 / entity.getOldPrice()));
  }

  //  @Override
//  public ResponseDTO<?> update(Long id, ProductDTO dto) throws Exception {
//    if (id == null){
//      throw new BaseException(400,"id is null. enter id",dto);
//    }
//    if (!productRepository.existsById(id)){
//      throw new BaseException(400,"id is not exist. check your id",dto);
//    }
//    ProductEntity entity = Converter.convertProductToEntity(dto);
//    entity.setId(id);
//
//    productRepository.save(entity);
//
//    return new ResponseDTO<>(200,"update ok",entity);
//  }
}
