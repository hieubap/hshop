package com.hshop.service;


import com.hshop.configuration.userdetail.UserDetailService;
import com.hshop.dao.model.UserEntity;
import com.hshop.dao.repository.UserRepository;
import com.hshop.dto.ResponseDTO;
import com.hshop.dto.UserDTO;
import com.hshop.exception.BaseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserDetailService userDetailService;

  @Override
  public ResponseDTO<?> search(UserDTO userDTO) {
    List<UserEntity> listEntities = userRepository.search(userDTO);
    List<UserDTO> listDto = new ArrayList<>();

    for (UserEntity entity : listEntities){
      UserDTO dto = new UserDTO();
      dto.setId(entity.getId());
      dto.setName(entity.getName());
      dto.setPhone(entity.getPhone());
      dto.setEmail(entity.getEmail());
      dto.setAddress(entity.getAddress());

      listDto.add(dto);
    }

    return new ResponseDTO<>(listDto);
  }

  @Override
  public ResponseDTO<?> detail() throws BaseException {
    return new ResponseDTO<>(200,"detail ok",userDetailService.detail());
  }

  @Override
  public ResponseDTO<?> update(Long id, UserDTO userDTO) throws Exception {
    if (!userRepository.existsById(id)) {
      throw new BaseException(400,"id is not exist. check your id",id);
    }

    UserEntity entity = userRepository.findById(id).get();
    entity.setName(userDTO.getName());
    entity.setPhone(userDTO.getPhone());
    entity.setAddress(userDTO.getAddress());
    entity.setEmail(userDTO.getEmail());
    userRepository.save(entity);

    return new ResponseDTO<>(entity);
  }

  @Override
  public ResponseDTO<?> delete(Long id) throws Exception {
    if (!userRepository.existsById(id)) {
      throw new BaseException(400,"id is not exist. check your id",id);
    }

    userRepository.deleteById(id);
    return new ResponseDTO<>(200,"delete ok",null);
  }

  public static UserDTO convertUserEntityToDTO(UserEntity entity){
    UserDTO dto = new UserDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPhone(entity.getPhone());
    dto.setEmail(entity.getEmail());
    dto.setAddress(entity.getAddress());

    return dto;
  }
}
