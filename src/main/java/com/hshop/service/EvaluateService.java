package com.hshop.service;

import com.hshop.dto.EvaluateDTO;
import com.hshop.dto.ResponseDTO;

public interface EvaluateService {

  ResponseDTO<?> create(EvaluateDTO object) throws Exception;

  ResponseDTO<?> update(Long id, EvaluateDTO object) throws Exception;

  ResponseDTO<?> delete(Long id) throws Exception;

  ResponseDTO<?> search(EvaluateDTO dto, Integer page, Integer size)
      throws Exception;
}