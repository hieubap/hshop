package com.hshop.service;

import com.google.common.base.Strings;
import com.hshop.converter.Converter;
import com.hshop.dao.model.TypeProductEntity;
import com.hshop.dao.repository.TypeProductRepository;
import com.hshop.dto.ResponseDTO;
import com.hshop.dto.TypeProductDTO;
import com.hshop.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TypeProductServiceImpl implements TypeProductService {
  @Autowired
  private TypeProductRepository repository;

  @Override
  public ResponseDTO<?> create(TypeProductDTO object) throws BaseException {
    if (Strings.isNullOrEmpty(object.getName())){
      throw new BaseException(400,"name is empty or null",null);
    }
    TypeProductEntity entity = new TypeProductEntity();
    entity.setName(object.getName());

    repository.save(entity);
    object.setId(entity.getId());
    object.setCreatedDate(entity.getCreatedDate());
    object.setUpdatedDate(entity.getUpdatedDate());

    return new ResponseDTO<>(200,"create successful",object);
  }

  @Override
  public ResponseDTO<?> update(Long id, TypeProductDTO object) throws BaseException {
    if (!repository.existsById(id)){
      throw new BaseException(400,"not exist id",null);
    }
    TypeProductEntity entity = repository.findById(id).get();
    entity.setName(object.getName());

    repository.save(entity);

    return new ResponseDTO<>(200,"update successful" ,
        Converter.convertTypeProductToDTO(entity));
  }

  @Override
  public ResponseDTO<?> delete(Long id) throws BaseException {
    if (!repository.existsById(id)){
      throw new BaseException(400,"not exist id",null);
    }
    repository.deleteById(id);

    return new ResponseDTO<>(200,"delete successful",null);
  }

  @Override
  public ResponseDTO<?> search(TypeProductDTO dto, Integer page, Integer size) {
    Sort sort = Sort.by(Sort.Direction.ASC,"id");
    Page<TypeProductEntity> list = repository.search(dto, PageRequest.of(page-1,size,sort));

    return new ResponseDTO<>(200, "find successful", list.toList(), list.toList().size(),
        (int) list.getTotalElements());
  }
}