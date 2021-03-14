package spring.backend.hshop.controller;

import spring.backend.hshop.configuration.dto.RegisterDto;
import spring.backend.hshop.configuration.userdetail.UserDetailService;
import spring.backend.hshop.dto.ResponseDTO;
import spring.backend.hshop.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
  @Autowired
  private UserDetailService userDetailService;

  @RequestMapping(value = "/register",method = RequestMethod.POST)
  public ResponseDTO<?> register(@RequestBody RegisterDto dto)
      throws BaseException {
    return userDetailService.register(dto);
  }
}
