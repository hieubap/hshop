package com.hshop.dao.repository;


import com.hshop.dao.model.ProductEntity;
import com.hshop.dto.ProductDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import spring.library.common.dao.repository.BaseRepository;

public interface ProductRepository extends BaseRepository<ProductEntity,ProductDTO,Long> {
  @Query(
      value = "select f.* from product f "
          + " where true "
          + " and f.deleted = 0 "
          + " and ( f.id = :#{#dto.id} or :#{#dto.id} is null ) "
          + " and ( LOWER(f.title) like concat('%',LOWER(:#{#dto.title}),'%') or LOWER(:#{#dto.title}) is null ) "
          + " and ( LOWER(f.name) like concat('%',LOWER(:#{#dto.name}),'%') or LOWER(:#{#dto.name}) is null ) "
          + " and ( f.star = :#{#dto.star} or :#{#dto.star} is null ) "
          + " and ( f.number_comment = :#{#dto.numberComment} or :#{#dto.numberComment} is null ) "
          + " and ( f.number_sell = :#{#dto.numberSell} or :#{#dto.numberSell} is null ) "
          + " and ( f.number = :#{#dto.number} or :#{#dto.number} is null ) "
          + " and ( f.old_price = :#{#dto.oldPrice} or :#{#dto.oldPrice} is null ) "
          + " and ( f.new_price = :#{#dto.newPrice} or :#{#dto.newPrice} is null ) "
          + " and ( LOWER(f.type) like concat('%',LOWER(:#{#dto.type}),'%') or LOWER(:#{#dto.type}) is null ) "
          + " and ( LOWER(f.color) like concat('%',LOWER(:#{#dto.color}),'%') or LOWER(:#{#dto.color}) is null ) "
          + " and ( LOWER(f.trade_mark) like concat('%',LOWER(:#{#dto.tradeMark}),'%') or LOWER(:#{#dto.tradeMark}) is null ) "
          + " and ( f.manufacture_country = :#{#dto.manufactureCountry} or :#{#dto.manufactureCountry} is null ) "
      ,countQuery = "select count(*) from product f "
      + " where true "
      + " and f.deleted = 0 "
      + " and ( f.id = :#{#dto.id} or :#{#dto.id} is null ) "
      + " and ( LOWER(f.title) like concat('%',LOWER(:#{#dto.title}),'%') or LOWER(:#{#dto.title}) is null ) "
      + " and ( LOWER(f.name) like concat('%',LOWER(:#{#dto.name}),'%') or LOWER(:#{#dto.name}) is null ) "
      + " and ( f.star = :#{#dto.star} or :#{#dto.star} is null ) "
      + " and ( f.number_comment = :#{#dto.numberComment} or :#{#dto.numberComment} is null ) "
      + " and ( f.number_sell = :#{#dto.numberSell} or :#{#dto.numberSell} is null ) "
      + " and ( f.number = :#{#dto.number} or :#{#dto.number} is null ) "
      + " and ( f.old_price = :#{#dto.oldPrice} or :#{#dto.oldPrice} is null ) "
      + " and ( f.new_price = :#{#dto.newPrice} or :#{#dto.newPrice} is null ) "
      + " and ( LOWER(f.type) like concat('%',LOWER(:#{#dto.type}),'%') or LOWER(:#{#dto.type}) is null ) "
      + " and ( LOWER(f.color) like concat('%',LOWER(:#{#dto.color}),'%') or LOWER(:#{#dto.color}) is null ) "
      + " and ( LOWER(f.trade_mark) like concat('%',LOWER(:#{#dto.tradeMark}),'%') or LOWER(:#{#dto.tradeMark}) is null ) "
      + " and ( f.manufacture_country = :#{#dto.manufactureCountry} or :#{#dto.manufactureCountry} is null ) "
      , nativeQuery = true
  )
  Page<ProductEntity> search(ProductDTO dto, Pageable pageable);

}
