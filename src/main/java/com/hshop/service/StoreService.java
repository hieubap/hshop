package com.hshop.service;

import com.hshop.dto.ResponseDTO;
import com.hshop.dto.StoreDTO;

public interface StoreService {

  ResponseDTO<?> create(StoreDTO StoreDTO) throws Exception;

  ResponseDTO<?> update(Long id, StoreDTO StoreDTO) throws Exception;

  ResponseDTO<?> delete(Long id) throws Exception;

  ResponseDTO<?> search(StoreDTO dto, Integer page, Integer size)
      throws Exception;
}