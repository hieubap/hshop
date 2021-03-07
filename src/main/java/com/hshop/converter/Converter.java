//package com.hshop.converter;
//
//import com.hshop.dao.model.StoreEntity;
//import com.hshop.dao.model.TypeProductEntity;
//import com.hshop.dao.model.UserEntity;
//import com.hshop.dto.StoreDTO;
//import com.hshop.dto.TypeProductDTO;
//import com.hshop.dto.UserDTO;
//
//public class Converter {
//  public static TypeProductDTO convertTypeProductToDTO(TypeProductEntity entity){
//    TypeProductDTO dto = new TypeProductDTO();
//    dto.setId(entity.getId());
//    dto.setName(entity.getName());
//    dto.setCreatedDate(entity.getCreatedDate());
//    dto.setUpdatedDate(entity.getUpdatedDate());
//
//    return dto;
//  }
//
//  public static TypeProductEntity convertTypeProductToEntity(TypeProductDTO dto){
//    TypeProductEntity entity = new TypeProductEntity();
//    entity.setId(dto.getId());
//    entity.setName(dto.getName());
//    entity.setCreatedDate(dto.getCreatedDate());
//    entity.setUpdatedDate(dto.getUpdatedDate());
//
//    return entity;
//  }
//
//  public static StoreDTO convertStoreToDTO(StoreEntity entity){
//    StoreDTO dto = new StoreDTO();
//    dto.setId(entity.getId());
//    dto.setName(entity.getName());
//    dto.setAddress(entity.getAddress());
//    dto.setEmail(entity.getEmail());
//    dto.setPhone(entity.getPhone());
//    dto.setTimeStart(entity.getTimeStart());
//    dto.setTimeEnd(entity.getTimeEnd());
//    dto.setOwnerId(entity.getOwnerId());
//    if (entity.getOwner()!=null) {
//      dto.setOwner(convertUserToDTO(entity.getOwner()));
//    }
//    return dto;
//  }
//  public static StoreEntity convertStoreToEntity(StoreDTO dto){
//    StoreEntity entity = new StoreEntity();
//    entity.setName(dto.getName());
//    entity.setAddress(dto.getAddress());
//    entity.setEmail(dto.getEmail());
//    entity.setPhone(dto.getPhone());
//    entity.setTimeStart(dto.getTimeStart());
//    entity.setTimeEnd(dto.getTimeEnd());
//    entity.setOwnerId(dto.getOwnerId());
//
//    return entity;
//  }
//  public static UserDTO convertUserToDTO (UserEntity entity){
//    UserDTO dto = new UserDTO();
//    dto.setId(entity.getId());
//    dto.setName(entity.getName());
//    dto.setPhone(entity.getPhone());
//    dto.setEmail(entity.getEmail());
//    dto.setAddress(entity.getAddress());
//    dto.setRole(entity.getRole());
//
//    return dto;
//  }
//
//}
