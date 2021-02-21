package com.hshop.service;

import com.hshop.dao.model.ProductEntity;
import com.hshop.dto.ProductDTO;

public class ConvertService {
  public static ProductDTO convertProductEntityToDTO(ProductEntity entity){
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

  public static ProductEntity convertProductDTOToEntity(ProductDTO d){
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

}
