package com.hshop.controller;

import com.hshop.dto.ProductDTO;
import com.hshop.exception.BaseException;
import com.hshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

  @Autowired
  private ProductService foodService;

  @GetMapping(value = "/search")
  public ResponseEntity<?> search(ProductDTO foodDTO) {
    return foodService.search(foodDTO);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody ProductDTO foodDTO) throws BaseException {
    return foodService.create(foodDTO);
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody ProductDTO dto,@Param(value = "id") Long id)
      throws Exception {
    return foodService.update(id, dto);
  }

  @DeleteMapping(value = "/delete")
  public ResponseEntity<?> delete(@Param(value = "id") Long id) throws Exception {
    return foodService.delete(id);
  }
}
