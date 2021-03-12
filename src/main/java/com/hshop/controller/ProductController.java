package com.hshop.controller;

import com.hshop.dto.ProductDTO;
import com.hshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.library.common.controller.BaseController;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseController<ProductDTO,ProductService> {
  @Autowired
  private ProductService foodService;

  @Override
  public ProductService getService() {
    return foodService;
  }
}
