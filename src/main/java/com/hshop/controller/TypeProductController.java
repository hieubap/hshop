package com.hshop.controller;

import com.hshop.dto.ProductDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.dto.TypeProductDTO;
import com.hshop.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "typeProduct")
public class TypeProductController {

  @Autowired
  public TypeProductService service;

  @GetMapping
  public ResponseDTO<?> search(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      TypeProductDTO data
  ) throws Exception {
    return service.search(data, page, size);
  }


  @PostMapping
  public ResponseDTO<?> create(@RequestBody TypeProductDTO object) throws Exception {
    return service.create(object);
  }

  @PutMapping("/{id}")
  public ResponseDTO<?> update(@RequestBody TypeProductDTO object, @PathVariable Long id)
      throws Exception {
    return service.update(id, object);
  }

  @DeleteMapping("/{id}")
  public ResponseDTO<?> delete(@PathVariable Long id) throws Exception {
    return service.delete(id);
  }
}