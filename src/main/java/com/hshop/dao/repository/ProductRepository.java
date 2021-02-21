package com.hshop.dao.repository;


import com.hshop.dao.model.ProductEntity;
import com.hshop.dto.ProductDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
  @Query(
      value = "select f.* from product f "
          + " where true "
          + " and ( LOWER(f.name) like concat('%',LOWER(:#{#dto.name}),'%') or LOWER(:#{#dto.name}) is null ) ",
      nativeQuery = true
  )
  List<ProductEntity> search(ProductDTO dto);
}
