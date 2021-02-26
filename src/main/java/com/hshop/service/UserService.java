package com.hshop.service;


import com.hshop.dto.LoginDTO;
import com.hshop.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
  public ResponseEntity<?> login(LoginDTO loginDTO) throws Exception;
  public ResponseEntity<?> register(UserDTO userDTO);

  public ResponseEntity<?> detail(String userDTO);
  public ResponseEntity<?> search(UserDTO userDTO);
  public ResponseEntity<?> update(Long id,UserDTO userDTO) throws Exception;
  public ResponseEntity<?> delete(Long id) throws Exception;
}
