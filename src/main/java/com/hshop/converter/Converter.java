package com.hshop.converter;
import java.time.LocalDateTime;

import com.hshop.dao.model.NotificationEntity;
import com.hshop.dao.model.ProductEntity;
import com.hshop.dao.model.StoreEntity;
import com.hshop.dao.model.TypeProductEntity;
import com.hshop.dao.model.UserEntity;
import com.hshop.dto.NotificationDTO;
import com.hshop.dto.ProductDTO;
import com.hshop.dto.StoreDTO;
import com.hshop.dto.TypeProductDTO;
import com.hshop.dto.UserDTO;

public class Converter {
  public static ProductDTO convertProductToDTO(ProductEntity entity){
    ProductDTO dto = new ProductDTO();
    dto.setId(entity.getId());
    dto.setTitle(entity.getTitle());
    dto.setName(entity.getName());
    dto.setStar(entity.getStar());
    dto.setNumberComment(entity.getNumberComment());
    dto.setNumberSell(entity.getNumberSell());
    dto.setNumber(entity.getNumber());
    dto.setOldPrice(entity.getOldPrice());
    dto.setNewPrice(entity.getNewPrice());
    dto.setType(entity.getType());
    dto.setColor(entity.getColor());
    dto.setTradeMark(entity.getTradeMark());
    dto.setManufactureCountry(entity.getManufactureCountry());
    dto.setVolume(entity.getVolume());
    dto.setMass(entity.getMass());
    dto.setDetail(entity.getDetail());
    dto.setImg(entity.getImg());
    dto.setDistributor(entity.getDistributor());
    dto.setCreatedDate(entity.getCreatedDate());
    dto.setUpdatedDate(entity.getUpdatedDate());
    if (entity.getOldPrice() != null) {
      dto.setPer(100 - (int) ((double)entity.getNewPrice()*100 / entity.getOldPrice()));
    }

    return dto;
  }

  public static ProductEntity convertProductToEntity(ProductDTO d){
    ProductEntity e = new ProductEntity();
    e.setId(d.getId());
    e.setTitle(d.getTitle());
    e.setName(d.getName());
    e.setStar(d.getStar());
    e.setNumberComment(d.getNumberComment());
    e.setNumberSell(d.getNumberSell());
    e.setNumber(d.getNumber());
    e.setOldPrice(d.getOldPrice());
    e.setNewPrice(d.getNewPrice());
    e.setType(d.getType());
    e.setColor(d.getColor());
    e.setTradeMark(d.getTradeMark());
    e.setManufactureCountry(d.getManufactureCountry());
    e.setVolume(d.getVolume());
    e.setMass(d.getMass());
    e.setDetail(d.getDetail());
    e.setImg(d.getImg());
    e.setDistributor(d.getDistributor());
    e.setCreatedDate(d.getCreatedDate());
    e.setUpdatedDate(d.getUpdatedDate());

    return e;
  }

  public static TypeProductDTO convertTypeProductToDTO(TypeProductEntity entity){
    TypeProductDTO dto = new TypeProductDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setCreatedDate(entity.getCreatedDate());
    dto.setUpdatedDate(entity.getUpdatedDate());

    return dto;
  }

  public static TypeProductEntity convertTypeProductToEntity(TypeProductDTO dto){
    TypeProductEntity entity = new TypeProductEntity();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setCreatedDate(dto.getCreatedDate());
    entity.setUpdatedDate(dto.getUpdatedDate());

    return entity;
  }

  public static StoreDTO convertStoreToDTO(StoreEntity entity){
    StoreDTO dto = new StoreDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setAddress(entity.getAddress());
    dto.setEmail(entity.getEmail());
    dto.setPhone(entity.getPhone());
    dto.setTimeStart(entity.getTimeStart());
    dto.setTimeEnd(entity.getTimeEnd());
    dto.setOwnerId(entity.getOwnerId());
    if (entity.getOwner()!=null) {
      dto.setOwner(convertUserToDTO(entity.getOwner()));
    }
    return dto;
  }
  public static StoreEntity convertStoreToEntity(StoreDTO dto){
    StoreEntity entity = new StoreEntity();
    entity.setName(dto.getName());
    entity.setAddress(dto.getAddress());
    entity.setEmail(dto.getEmail());
    entity.setPhone(dto.getPhone());
    entity.setTimeStart(dto.getTimeStart());
    entity.setTimeEnd(dto.getTimeEnd());
    entity.setOwnerId(dto.getOwnerId());

    return entity;
  }
  public static UserDTO convertUserToDTO (UserEntity entity){
    UserDTO dto = new UserDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPhone(entity.getPhone());
    dto.setEmail(entity.getEmail());
    dto.setAddress(entity.getAddress());
    dto.setRole(entity.getRole());

    return dto;
  }

}
