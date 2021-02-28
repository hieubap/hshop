package com.hshop.service;

import com.hshop.dto.NotificationDTO;
import com.hshop.dto.ResponseDTO;

public interface NotificationService {

  ResponseDTO<?> create(NotificationDTO object) throws Exception;

  ResponseDTO<?> update(Long id, NotificationDTO object) throws Exception;

  ResponseDTO<?> delete(Long id) throws Exception;

  ResponseDTO<?> search(NotificationDTO dto, Integer page, Integer size)
      throws Exception;
}