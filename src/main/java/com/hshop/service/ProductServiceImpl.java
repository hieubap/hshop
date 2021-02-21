package com.hshop.service;


import com.hshop.dao.model.ProductEntity;
import com.hshop.dao.repository.ProductRepository;
import com.hshop.dto.FoodDTO;
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
  public ResponseEntity<?> search(FoodDTO foodDTO) {
//    List<FoodDTO> listDto = new ArrayList<>();
    List<ProductEntity> list = foodRepository.search(foodDTO);

//    for (ProductEntity entity : list){
//      FoodDTO dto = new FoodDTO();
//      dto.setId(entity.getId());
//      dto.setName(entity.getName());
//      dto.setPrice(entity.getCurPrice());
//      listDto.add(dto);
//    }

    return new ResponseEntity<>(new ResponseDTO<>(list), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> create(FoodDTO dto) {
    ProductEntity entity = new ProductEntity();
    entity.setName(dto.getName());
    entity.setCurPrice(dto.getPrice());

    foodRepository.save(entity);

    return new ResponseEntity<>(new ResponseDTO<>(entity),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> update(Long id, FoodDTO dto) throws Exception {
    if (!foodRepository.existsById(id)){
      throw new BaseException(400,"id is not exist. check your id",dto);
    }
    ProductEntity entity = foodRepository.findById(id).get();
    entity.setName(dto.getName());
    entity.setCurPrice(dto.getPrice());
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
