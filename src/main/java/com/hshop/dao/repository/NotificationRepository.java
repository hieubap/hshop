package com.hshop.dao.repository;

import com.hshop.dao.model.NotificationEntity;
import com.hshop.dao.model.TypeProductEntity;
import com.hshop.dto.NotificationDTO;
import com.hshop.dto.TypeProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import spring.library.common.dao.repository.BaseRepository;

public interface NotificationRepository extends BaseRepository<NotificationEntity,NotificationDTO,Long> {
  @Query(value = "select e from NotificationEntity e"
      + " where e.deleted = 0 "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.content like :#{#dto.content} or :#{#dto.content} is null)"
      + " and (e.isRead = :#{#dto.isRead} or :#{#dto.isRead} is null)"
      + " and (e.ownerId = :#{#dto.ownerId} or :#{#dto.ownerId} is null)"
      , countQuery = "select count(e) from NotificationEntity e"
      + " where e.deleted = 0 "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.content like :#{#dto.content} or :#{#dto.content} is null)"
      + " and (e.isRead = :#{#dto.isRead} or :#{#dto.isRead} is null)"
      + " and (e.ownerId = :#{#dto.ownerId} or :#{#dto.ownerId} is null)")
  Page<NotificationEntity> search(NotificationDTO dto, Pageable page);

  @Override
  @Transactional
  @Modifying
  @Query(value = "update NotificationEntity e set e.deleted = 1 "
      + " where e.id = ?#{#id} ")
  void deleteById(Long id);

  @Query("select count(e) from NotificationEntity e"
      + " where e.deleted = 0 and e.isRead = 1")
  Integer countRead();
}
