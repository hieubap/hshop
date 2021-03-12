package com.hshop.controller;

import com.hshop.dto.EvaluateDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.service.EvaluateService;
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
@RequestMapping(value = "/evaluate")
public class EvaluateController extends BaseController<EvaluateDTO,EvaluateService> {
  @Autowired
  public EvaluateService service;

  @Override
  public EvaluateService getService() {
    return service;
  }

//  @GetMapping
//  public ResponseDTO<?> search(
//      @RequestParam(defaultValue = "1") Integer page,
//      @RequestParam(defaultValue = "10") Integer size,
//      EvaluateDTO data
//  ) throws Exception {
//    return service.search(data, page, size);
//  }
//
//
//  @PostMapping
//  public ResponseDTO<?> create(@RequestBody EvaluateDTO evaluateDTO) throws Exception {
//      return service.create(evaluateDTO);
//  }
//
//  @PutMapping("/{id}")
//  public ResponseDTO<?> update(@RequestBody EvaluateDTO evaluateDTO, @PathVariable Long id)
//      throws Exception {
//      return service.update(id, evaluateDTO);
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseDTO<?> delete(@PathVariable Long id) throws Exception {
//    return service.delete(id);
//  }
}