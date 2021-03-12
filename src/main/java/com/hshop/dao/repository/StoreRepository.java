package com.hshop.dao.repository;

import com.hshop.dao.model.StoreEntity;
import com.hshop.dto.StoreDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import spring.library.common.dao.repository.BaseRepository;

public interface StoreRepository extends BaseRepository<StoreEntity,StoreDTO,Long> {
  @Query(value = "select e from StoreEntity e"
      + " where e.deleted = 0 "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.name like :#{#dto.name} or :#{#dto.name} is null)"
      + " and (e.address like :#{#dto.address} or :#{#dto.address} is null)"
      + " and (e.phone like :#{#dto.phone} or :#{#dto.phone} is null)"
      + " and (e.email like :#{#dto.email} or :#{#dto.email} is null)"
      + " and (e.timeStart = :#{#dto.timeStart} or :#{#dto.timeStart} is null)"
      + " and (e.timeEnd = :#{#dto.timeEnd} or :#{#dto.timeEnd} is null)"
      , countQuery = "select count(e) from StoreEntity e"
      + " where e.deleted = 0 "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.name like :#{#dto.name} or :#{#dto.name} is null)"
      + " and (e.address like :#{#dto.address} or :#{#dto.address} is null)"
      + " and (e.phone like :#{#dto.phone} or :#{#dto.phone} is null)"
      + " and (e.email like :#{#dto.email} or :#{#dto.email} is null)"
      + " and (e.timeStart = :#{#dto.timeStart} or :#{#dto.timeStart} is null)"
      + " and (e.timeEnd = :#{#dto.timeEnd} or :#{#dto.timeEnd} is null)")
  Page<StoreEntity> search(StoreDTO dto, Pageable page);

  @Override
  @Transactional
  @Modifying
  @Query("update StoreEntity e set e.deleted = 1 "
      + " where (e.id = ?#{#id}) ")
  void deleteById(Long id);
}
