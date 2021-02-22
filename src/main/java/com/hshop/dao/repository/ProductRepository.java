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
          + " and ( f.id = :#{#dto.id} or :#{#dto.id} is null ) "
          + " and ( LOWER(f.title) like concat('%',LOWER(:#{#dto.title}),'%') or LOWER(:#{#dto.title}) is null ) "
          + " and ( LOWER(f.name) like concat('%',LOWER(:#{#dto.name}),'%') or LOWER(:#{#dto.name}) is null ) "
          + " and ( f.star = :#{#dto.star} or :#{#dto.star} is null ) "
          + " and ( f.numberComment = :#{#dto.numberComment} or :#{#dto.numberComment} is null ) "
          + " and ( f.numberSell = :#{#dto.numberSell} or :#{#dto.numberSell} is null ) "
          + " and ( f.number = :#{#dto.number} or :#{#dto.number} is null ) "
          + " and ( f.oldPrice = :#{#dto.oldPrice} or :#{#dto.oldPrice} is null ) "
          + " and ( f.newPrice = :#{#dto.newPrice} or :#{#dto.newPrice} is null ) "
          + " and ( LOWER(f.type) like concat('%',LOWER(:#{#dto.type}),'%') or LOWER(:#{#dto.type}) is null ) "
          + " and ( LOWER(f.color) like concat('%',LOWER(:#{#dto.color}),'%') or LOWER(:#{#dto.color}) is null ) "
          + " and ( LOWER(f.tradeMark) like concat('%',LOWER(:#{#dto.tradeMark}),'%') or LOWER(:#{#dto.tradeMark}) is null ) "
          + " and ( f.manufactureCountry = :#{#dto.manufactureCountry} or :#{#dto.manufactureCountry} is null ) "
      , nativeQuery = true
  )
  List<ProductEntity> search(ProductDTO dto);
}
