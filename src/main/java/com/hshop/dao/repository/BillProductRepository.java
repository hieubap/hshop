package com.hshop.dao.repository;

import com.hshop.dao.model.BillProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillProductRepository extends JpaRepository<BillProductEntity,Long> {
}
