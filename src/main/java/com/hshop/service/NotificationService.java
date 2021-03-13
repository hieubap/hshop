package com.hshop.service;

import com.hshop.dto.NotificationDTO;
import spring.library.common.service.BaseService;

public interface NotificationService extends BaseService<NotificationDTO> {

//  ResponseDTO<?> create(NotificationDTO object) throws Exception;
//
  NotificationDTO read(Long id);

  Integer countRead();
//
//  ResponseDTO<?> delete(Long id) throws Exception;
//
//  ResponseDTO<?> search(NotificationDTO dto, Integer page, Integer size)
//      throws Exception;
}