package spring.backend.hshop.controller;

import spring.backend.hshop.dto.ProductDTO;
import spring.backend.hshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.backend.library.controller.BaseController;

@CrossOrigin
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
