package spring.backend.hshop.controller;

import spring.backend.hshop.dto.TypeProductDTO;
import spring.backend.hshop.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.backend.library.controller.BaseController;

@CrossOrigin
@RestController
@RequestMapping(value = "typeProduct")
public class TypeProductController extends BaseController<TypeProductDTO,TypeProductService> {

  @Autowired
  public TypeProductService service;

  @Override
  public TypeProductService getService() {
    return service;
  }
}