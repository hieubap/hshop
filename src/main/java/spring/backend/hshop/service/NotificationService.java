package spring.backend.hshop.service;

import spring.backend.hshop.dto.NotificationDTO;
import spring.backend.library.service.BaseService;

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