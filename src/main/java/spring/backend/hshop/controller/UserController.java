package spring.backend.hshop.controller;

import spring.backend.hshop.dto.UserDTO;
import spring.backend.hshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.backend.library.controller.BaseController;

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