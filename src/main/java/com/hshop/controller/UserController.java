package com.hshop.controller;

import com.hshop.dto.ResponseDTO;
import com.hshop.dto.UserDTO;
import com.hshop.exception.BaseException;
import com.hshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.library.common.controller.BaseController;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserDTO,UserService> {
  @Autowired
  public UserService userService;

  @Override
  public UserService getService() {
    return userService;
  }

//  @GetMapping(value = "/search")
//  public ResponseDTO<?> search(UserDTO userDTO){
//    return userService.search(userDTO);
//  }
//
//  @GetMapping
//  public ResponseDTO<?> detail() throws BaseException {
//    return userService.detail();
//  }
//
//  @PutMapping
//  public ResponseDTO<?> update(@Param(value = "id") Long id,UserDTO userDTO) throws Exception {
//    return userService.update(id,userDTO);
//  }
//
//  @DeleteMapping
//  public ResponseDTO<?> delete(@Param(value = "id") Long id) throws Exception{
//    return userService.delete(id);
//  }
}