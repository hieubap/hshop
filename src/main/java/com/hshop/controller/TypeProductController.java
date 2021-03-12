package com.hshop.controller;

import com.hshop.dto.TypeProductDTO;
import com.hshop.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.library.common.controller.BaseController;

@RestController
@RequestMapping(value = "typeProduct")
public class TypeProductController extends BaseController<TypeProductDTO,TypeProductService> {

  @Autowired
  public TypeProductService service;

  @Override
  public TypeProductService getService() {
    return service;
  }

//  @GetMapping
//  public ResponseDTO<?> search(
//      @RequestParam(defaultValue = "1") Integer page,
//      @RequestParam(defaultValue = "10") Integer size,
//      TypeProductDTO data
//  ) throws Exception {
//    return service.search(data, page, size);
//  }
//
//
//  @PostMapping
//  public ResponseDTO<?> create(@RequestBody TypeProductDTO object) throws Exception {
//    return service.create(object);
//  }
//
//  @PutMapping("/{id}")
//  public ResponseDTO<?> update(@RequestBody TypeProductDTO object, @PathVariable Long id)
//      throws Exception {
//    return service.update(id, object);
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseDTO<?> delete(@PathVariable Long id) throws Exception {
//    return service.delete(id);
//  }
}