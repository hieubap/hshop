package com.hshop.controller;

import com.hshop.configuration.dto.RegisterDto;
import com.hshop.configuration.dto.UsernameAndPasswordDto;
import com.hshop.configuration.userdetail.UserDetailService;
import com.hshop.dto.ResponseDTO;
import com.hshop.dto.UserDTO;
import com.hshop.exception.BaseException;
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
