package com.hshop.service;


import com.hshop.configuration.userdetail.UserDetailService;
import com.hshop.dao.model.UserEntity;
import com.hshop.dao.repository.UserRepository;
import com.hshop.dto.UserDTO;
import com.hshop.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.library.common.exception.BaseException;
import spring.library.common.service.AbstractBaseService;

@Service
public class UserServiceImpl extends
    AbstractBaseService<UserEntity,UserDTO,UserRepository> implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDetailService userDetailService;

  @Override
  protected UserRepository getRepository() {
    return userRepository;
  }

  @Override
  protected void beforeSave(UserEntity entity, UserDTO dto) {
    super.beforeSave(entity, dto);
    if (dto.getUsername() == null || dto.getPassword() == null)
      throw new BaseException(400,"username and password is null",null);

    if(userRepository.existsByUsername(dto.getUsername())){
      throw new BaseException(400,"username is exist",null);
    }
    if (dto.getRole()!= null && dto.getRole().equals(RoleType.ADMIN)){
      throw new BaseException(400,"role cant admin",null);
    }
    else if (dto.getRole() != null)
      entity.setRole(dto.getRole());
    else
      entity.setRole(RoleType.USER);
  }

  //  @Override
//  public ResponseDTO<?> search(UserDTO userDTO) {
//    List<UserEntity> listEntities = userRepository.search(userDTO);
//    List<UserDTO> listDto = new ArrayList<>();
//
//    for (UserEntity entity : listEntities){
//      UserDTO dto = new UserDTO();
//      dto.setId(entity.getId());
//      dto.setName(entity.getName());
//      dto.setPhone(entity.getPhone());
//      dto.setEmail(entity.getEmail());
//      dto.setAddress(entity.getAddress());
//
//      listDto.add(dto);
//    }
//
//    return new ResponseDTO<>(listDto);
//  }
//
//  @Override
//  public ResponseDTO<?> detail() throws BaseException {
//    return new ResponseDTO<>(200,"detail ok",userDetailService.detail());
//  }
//
//  @Override
//  public ResponseDTO<?> update(Long id, UserDTO userDTO) throws Exception {
//    if (!userRepository.existsById(id)) {
//      throw new BaseException(400,"id is not exist. check your id",id);
//    }
//
//    UserEntity entity = userRepository.findById(id).get();
//    entity.setName(userDTO.getName());
//    entity.setPhone(userDTO.getPhone());
//    entity.setAddress(userDTO.getAddress());
//    entity.setEmail(userDTO.getEmail());
//    userRepository.save(entity);
//
//    return new ResponseDTO<>(entity);
//  }
//
//  @Override
//  public ResponseDTO<?> delete(Long id) throws Exception {
//    if (!userRepository.existsById(id)) {
//      throw new BaseException(400,"id is not exist. check your id",id);
//    }
//
//    userRepository.deleteById(id);
//    return new ResponseDTO<>(200,"delete ok",null);
//  }
//
//  public static UserDTO convertUserEntityToDTO(UserEntity entity){
//    UserDTO dto = new UserDTO();
//    dto.setId(entity.getId());
//    dto.setName(entity.getName());
//    dto.setPhone(entity.getPhone());
//    dto.setEmail(entity.getEmail());
//    dto.setAddress(entity.getAddress());
//
//    return dto;
//  }
}
