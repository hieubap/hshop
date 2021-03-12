package com.hshop.dao.repository;

import com.hshop.dao.model.OrderEntity;
import com.hshop.dao.model.StoreEntity;
import com.hshop.dao.model.UserEntity;
import com.hshop.dto.OrderDTO;
import org.apache.catalina.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.library.common.dao.repository.BaseRepository;

public interface OrderRepository extends BaseRepository<OrderEntity, OrderDTO,Long> {
}
