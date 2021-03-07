package com.hshop.controller;

import com.hshop.dto.ResponseDTO;
import com.hshop.dto.StoreDTO;
import com.hshop.service.StoreService;
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
import spring.library.common.controller.BaseController;

@RestController
@RequestMapping(value = "store")
public class StoreController extends BaseController<StoreDTO,StoreService> {

  @Autowired
  public StoreService service;

  @Override
  public StoreService getService() {
    return service;
  }

//  @GetMapping
//  public ResponseDTO<?> search(
//      @RequestParam(defaultValue = "1") Integer page,
//      @RequestParam(defaultValue = "10") Integer size,
//      StoreDTO data
//  ) throws Exception {
//    return service.search(data, page, size);
//  }
//
//
//  @PostMapping
//  public ResponseDTO<?> create(@RequestBody StoreDTO storeDTO) throws Exception {
//    return service.create(storeDTO);
//  }
//
//  @PutMapping("/{id}")
//  public ResponseDTO<?> update(@RequestBody StoreDTO storeDTO, @PathVariable Long id)
//      throws Exception {
//    return service.update(id, storeDTO);
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseDTO<?> delete(@PathVariable Long id) throws Exception {
//    return service.delete(id);
//  }
}
