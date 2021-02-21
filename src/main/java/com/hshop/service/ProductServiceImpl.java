package com.hshop.service;
import java.time.LocalDateTime;


import com.hshop.dao.model.ProductEntity;
import com.hshop.dao.repository.ProductRepository;
import com.hshop.dto.ProductDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.exception.BaseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository foodRepository;

  @Override
  public ResponseEntity<?> search(ProductDTO foodDTO) {
    List<ProductDTO> listDto = new ArrayList<>();
    List<ProductEntity> list = foodRepository.search(foodDTO);

    for (ProductEntity entity : list){
      listDto.add(ConvertService.convertProductEntityToDTO(entity));
    }

    return new ResponseEntity<>(new ResponseDTO<>(listDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> create(ProductDTO dto) throws BaseException {
    if (dto.getName() == null || dto.getName().isEmpty()){
      throw new BaseException(400,"name is null or empty. enter name",null);
    }
    if (dto.getNewPrice() == null || dto.getNewPrice()<0){
      throw new BaseException(400,
          "newPrice is null or empty or not exactly. enter newPrice",null);
    }
    if (dto.getImg() == null || dto.getImg().isEmpty()){
      throw new BaseException(400,
          "img is null or empty or not exactly. enter img",null);
    }

    ProductEntity entity = ConvertService.convertProductDTOToEntity(dto);

    foodRepository.save(entity);

    return new ResponseEntity<>(new ResponseDTO<>(entity),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> update(Long id, ProductDTO dto) throws Exception {
    if (id == null){
      throw new BaseException(400,"id is null. enter id",dto);
    }
    if (!foodRepository.existsById(id)){
      throw new BaseException(400,"id is not exist. check your id",dto);
    }
    ProductEntity entity = ConvertService.convertProductDTOToEntity(dto);
    entity.setId(id);

    foodRepository.save(entity);

    return new ResponseEntity<>(new ResponseDTO<>(entity),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> delete(Long id) throws Exception {
    if (!foodRepository.existsById(id)){
      throw new BaseException(400,"id is not exist. check your id",id);
    }
    foodRepository.deleteById(id);
    ResponseDTO<?> responseDTO = new ResponseDTO(200,"delete id '" + id +"' successful",null);

    return new ResponseEntity<>(responseDTO,HttpStatus.OK);
  }
}
