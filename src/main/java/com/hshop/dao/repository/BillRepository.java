package com.hshop.dao.repository;


import com.hshop.dao.model.BillEntity;
import com.hshop.dto.BillDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BillRepository extends JpaRepository<BillEntity,Long> {
  @Query(value = "select"
      + "       year(b.created_date) as year, "
      + "       month(b.created_date) as month, "
      + "       day(b.created_date) as day, "
      + "       count(day(b.created_date)) as soluong "
      + " from bill b "
      + " where b.deleted = 0"
      + " and (b.user_id = :#{#userId} ) "
      + " and (date(b.created_date) <= :#{#end} ) "
      + " and (date(b.created_date) >= :#{#start} )"
      + " group by year(b.created_date),month(b.created_date), day(b.created_date)", nativeQuery = true)
  public List<Map<Long,Long>> dashboard(LocalDate start,LocalDate end,Long userId);

  @Query(value = "select e from BillEntity e"
      + " where e.deleted = 0 "
      + " and (e.buyer.id = :#{#userId}) "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.status = :#{#dto.status} or :#{#dto.status} is null)"

      , countQuery = "select count(e) from BillEntity e"
      + " where e.deleted = 0 "
      + " and (e.buyer.id = :#{#userId}) "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.status = :#{#dto.status} or :#{#dto.status} is null)")
  Page<BillEntity> search(BillDTO dto, Pageable page,Long userId);

  @Override
  @Transactional
  @Modifying
  @Query(value = "update BillEntity e set e.deleted = 1 "
      + " where e.id = ?#{#id} ")
  void deleteById(Long id);
}
