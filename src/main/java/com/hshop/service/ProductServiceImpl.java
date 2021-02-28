package com.hshop.service;


import com.hshop.converter.Converter;
import com.hshop.dao.model.ProductEntity;
import com.hshop.dao.repository.ProductRepository;
import com.hshop.dto.ProductDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.exception.BaseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public ResponseDTO<?> search(ProductDTO foodDTO, Integer page, Integer size) {
    List<ProductDTO> listDto = new ArrayList<>();
    
    Sort sort = Sort.by(Sort.Direction.ASC,"id"); 
    Page<ProductEntity> list = productRepository.search(foodDTO,
        PageRequest.of(page-1,size,sort));

    for (ProductEntity entity : list){
      listDto.add(Converter.convertProductToDTO(entity));
    }

    return new ResponseDTO<>(200,"search ok",new ResponseDTO<>(listDto),listDto.size(),
        list.toList().size());
  }

  @Override
  public ResponseDTO<?> create(ProductDTO dto) throws BaseException {
    if (dto.getName() == null || dto.getName().isEmpty()){
      throw new BaseException(400,"name is null or empty. enter name",null);
    }
    if (dto.getNewPrice() == null || dto.getNewPrice()<0){
      throw new BaseException(400,
          "newPrice is null or empty or not exactly. enter newPrice",null);
    }
    if (dto.getNewPrice()>999999999){
      throw new BaseException(400,
          "newPrice cant over 999.999.999",null);
    }
    if (dto.getOldPrice() != null && dto.getOldPrice()>999999999){
      throw new BaseException(400,
          "oldPrice cant over 999.999.999",null);
    }
    if (dto.getImg() == null || dto.getImg().isEmpty()){
      throw new BaseException(400,
          "img is null or empty or not exactly. enter img",null);
    }

    ProductEntity entity = Converter.convertProductToEntity(dto);

    productRepository.save(entity);

    return new ResponseDTO<>(200,"create ok",entity);
  }

  @Override
  public ResponseDTO<?> update(Long id, ProductDTO dto) throws Exception {
    if (id == null){
      throw new BaseException(400,"id is null. enter id",dto);
    }
    if (!productRepository.existsById(id)){
      throw new BaseException(400,"id is not exist. check your id",dto);
    }
    ProductEntity entity = Converter.convertProductToEntity(dto);
    entity.setId(id);

    productRepository.save(entity);

    return new ResponseDTO<>(200,"update ok",entity);
  }

  @Override
  public ResponseDTO<?> delete(Long id) throws Exception {
    if (!productRepository.existsById(id)){
      throw new BaseException(400,"id is not exist. check your id",id);
    }
    productRepository.deleteById(id);
    return new ResponseDTO<>(200,"delete ok",null);
  }
}
