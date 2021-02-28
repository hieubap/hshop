package com.hshop.service;

import com.hshop.dto.ResponseDTO;
import com.hshop.dto.TypeProductDTO;

public interface TypeProductService {

  ResponseDTO<?> create(TypeProductDTO object) throws Exception;

  ResponseDTO<?> update(Long id, TypeProductDTO object) throws Exception;

  ResponseDTO<?> delete(Long id) throws Exception;

  ResponseDTO<?> search(TypeProductDTO dto, Integer page, Integer size)
      throws Exception;
}