package com.hshop.service;

import com.google.common.base.Strings;
import com.hshop.dao.model.NotificationEntity;
import com.hshop.dao.model.TypeProductEntity;
import com.hshop.dao.repository.NotificationRepository;
import com.hshop.dto.NotificationDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
  @Autowired
  private NotificationRepository notificationRepository;

  @Override
  public ResponseDTO<?> create(NotificationDTO dto) throws BaseException {
    if (Strings.isNullOrEmpty(dto.getContent()) &&
    dto.getOwnerId() == null){
      throw new BaseException(400,"content or ownerId is null or empty",null);
    }
    NotificationEntity entity = new NotificationEntity();
    entity.setContent(dto.getContent());
    entity.setUserId(dto.getOwnerId());

    notificationRepository.save(entity);

    dto.setId(entity.getId());
    dto.setIsRead(entity.getIsRead());
    dto.setCreatedDate(entity.getCreatedDate());

    return new ResponseDTO<>(200,"create ok" , dto);
  }

  @Override
  public ResponseDTO<?> update(Long id, NotificationDTO dto) throws BaseException {
    if (!notificationRepository.existsById(id)){
      throw new BaseException(400,"id is not exist",null);
    }
    if (Strings.isNullOrEmpty(dto.getContent()) &&
        dto.getOwnerId() == null){
      throw new BaseException(400,"content or ownerId is null or empty",null);
    }
    NotificationEntity entity = notificationRepository.findById(id).get();
    entity.setContent(dto.getContent());
    entity.setIsRead((short) 0);
    entity.setUserId(dto.getOwnerId());

    notificationRepository.save(entity);

    dto.setIsRead((short)0);
    return new ResponseDTO<>(200,"update ok" , dto);
  }

  @Override
  public ResponseDTO<?> delete(Long id) throws BaseException {
    if (!notificationRepository.existsById(id)){
      throw new BaseException(400,"id is not exist",null);
    }

    notificationRepository.deleteById(id);

    return new ResponseDTO<>(200,"delete ok" , null);
  }

  @Override
  public ResponseDTO<?> search(NotificationDTO dto, Integer page, Integer size) {
    Sort sort = Sort.by(Sort.Direction.ASC,"id");
    Page<NotificationEntity> list = notificationRepository.search(dto, PageRequest.of(page-1,size,sort));

    return new ResponseDTO<>(200, "find successful", list.toList(), list.toList().size(),
        (int) list.getTotalElements());
  }
}