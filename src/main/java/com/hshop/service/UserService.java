package com.hshop.service;


import com.hshop.dto.ResponseDTO;
import com.hshop.dto.UserDTO;
import com.hshop.exception.BaseException;

public interface UserService {
  public ResponseDTO<?> detail() throws BaseException;
  public ResponseDTO<?> search(UserDTO userDTO);
  public ResponseDTO<?> update(Long id,UserDTO userDTO) throws Exception;
  public ResponseDTO<?> delete(Long id) throws Exception;
}
