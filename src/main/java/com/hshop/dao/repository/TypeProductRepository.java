package com.hshop.dao.repository;

import com.hshop.dao.model.TypeProductEntity;
import com.hshop.dto.TypeProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import spring.library.common.dao.repository.BaseRepository;

public interface TypeProductRepository extends BaseRepository<TypeProductEntity,TypeProductDTO,Long> {
    @Query(value = "select e from TypeProductEntity e"
        + " where e.deleted = 0 "
        + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
        + " and (e.name like :#{#dto.name} or :#{#dto.name} is null)"
        , countQuery = "select count(e) from TypeProductEntity e"
        + " where e.deleted = 0 "
        + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
        + " and (e.name like :#{#dto.name} or :#{#dto.name} is null)")
    Page<TypeProductEntity> search(TypeProductDTO dto, Pageable page);

    @Override
    @Transactional
    @Modifying
    @Query(value = "update TypeProductEntity e set e.deleted = 1 "
        + " where e.id = ?#{#id} ")
    void deleteById(Long id);

}
