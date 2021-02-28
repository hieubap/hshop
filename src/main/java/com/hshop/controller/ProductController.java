package com.hshop.controller;

import com.hshop.dto.ProductDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.exception.BaseException;
import com.hshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

  @Autowired
  private ProductService foodService;

  @GetMapping
  public ResponseDTO<?> search(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size, ProductDTO foodDTO) {
    return foodService.search(foodDTO,page,size);
  }

  @PostMapping
  public ResponseDTO<?> create(@RequestBody ProductDTO foodDTO) throws BaseException {
    return foodService.create(foodDTO);
  }

  @PutMapping("/{id}")
  public ResponseDTO<?> update(@RequestBody ProductDTO dto,@PathVariable(value = "id") Long id)
      throws Exception {
    return foodService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public ResponseDTO<?> delete(@PathVariable(value = "id") Long id) throws Exception {
    return foodService.delete(id);
  }
}
