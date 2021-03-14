package spring.backend.hshop.dao.repository;


import spring.backend.hshop.dao.model.BillEntity;
import spring.backend.hshop.dto.BillDTO;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import spring.backend.library.dao.repository.BaseRepository;

public interface BillRepository extends BaseRepository<BillEntity,BillDTO,Long> {
  @Query(value = "select * from ( select "
      + "       year(created_at) as year, "
      + "       month(created_at) as month, "
      + "       day(created_at) as day, "
      + "       count(day(created_at)) as soluong "
      + " from bill "
      + " where deleted = 0"
      + " and (buyer_id = :#{#userId} or :#{#userId} is null) "
      + " and (date(created_at) <= :#{#end} or :#{#end} is null) "
      + " and (date(created_at) >= :#{#start} or :#{#start} is null)"
      + " group by year(created_at),month(created_at), day(created_at) ) as tb ",
      countQuery = "select count(*) from ( select "
          + "       year(created_at) as year, "
          + "       month(created_at) as month, "
          + "       day(created_at) as day, "
          + "       count(day(created_at)) as soluong "
          + " from bill "
          + " where deleted = 0"
          + " and (buyer_id = :#{#userId} or :#{#userId} is null) "
          + " and (date(created_at) <= :#{#end} or :#{#end} is null) "
          + " and (date(created_at) >= :#{#start} or :#{#start} is null)"
          + " group by year(created_at),month(created_at), day(created_at) ) as tb "
      ,nativeQuery = true)
  Page<Map<Long,Long>> dashboard(LocalDate start,LocalDate end,Long userId,Pageable pageable);

  @Override
  @Query(value = "select e from BillEntity e"
      + " left join e.buyer buyer"
      + " where e.deleted = 0 "
      + " and (buyer.id = :#{#dto.buyerId} or :#{#dto.buyerId} is null )"
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.storeId = :#{#dto.storeId} or :#{#dto.storeId} is null) "
      + " and (e.status = :#{#dto.status} or :#{#dto.status} is null)"

      , countQuery = "select count(e) from BillEntity e"
      + " left join e.buyer buyer"
      + " where e.deleted = 0 "
      + " and (buyer.id = :#{#dto.buyerId} or :#{#dto.buyerId} is null )"
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.storeId = :#{#dto.storeId} or :#{#dto.storeId} is null) "
      + " and (e.status = :#{#dto.status} or :#{#dto.status} is null)")
  Page<BillEntity> search(BillDTO dto, Pageable page);

  @Query("select e from BillEntity e"
      + " where e.deleted = 0 "
      + "and (e.storeId = :#{#storeId} or :#{#storeId} is null )"
      + "and e.status = 1 ")
  Page<BillEntity> getOrder(Long storeId, Pageable pageable);

  @Query("select e from BillEntity e"
      + " where e.deleted = 0 "
      + "and (e.storeId = :#{#storeId} or :#{#storeId} is null )"
      + "and e.status <> 1 ")
  Page<BillEntity> getBill(Long storeId, Pageable pageable);

  @Override
  @Transactional
  @Modifying
  @Query(value = "update BillEntity e set e.deleted = 1 "
      + " where e.id = ?#{#id} ")
  void deleteById(Long id);

  @Query("select count(e) from BillEntity e"
      + " where e.deleted = 0 "
      + "and day(e.createdAt) = day(:#{#date})")
  Integer numberBillOnDay(LocalDate date);
}
