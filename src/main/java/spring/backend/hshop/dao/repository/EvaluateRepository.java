package spring.backend.hshop.dao.repository;


import spring.backend.hshop.dao.model.EvaluateEntity;
import spring.backend.hshop.dto.EvaluateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import spring.backend.library.dao.repository.BaseRepository;

public interface EvaluateRepository extends BaseRepository<EvaluateEntity, EvaluateDTO,Long> {
  @Query(value = "select e from EvaluateEntity e"
      + " where e.deleted = 0 "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.content like :#{#dto.content} or :#{#dto.content} is null)"
      + " and (e.star = :#{#dto.star} or :#{#dto.star} is null)"
      + " and (e.userId = :#{#dto.userId} or :#{#dto.userId} is null)"
      + " and (e.productId = :#{#dto.productId} or :#{#dto.productId} is null)"

      , countQuery = "select count(e) from EvaluateEntity e"
      + " where e.deleted = 0 "
      + " and (e.id = :#{#dto.id} or :#{#dto.id} is null) "
      + " and (e.content like :#{#dto.content} or :#{#dto.content} is null)"
      + " and (e.star = :#{#dto.star} or :#{#dto.star} is null)"
      + " and (e.userId = :#{#dto.userId} or :#{#dto.userId} is null)"
      + " and (e.productId = :#{#dto.productId} or :#{#dto.productId} is null)")
  Page<EvaluateEntity> search(EvaluateDTO dto, Pageable page);

  @Override
  @Transactional
  @Modifying
  @Query(value = "update EvaluateEntity e set e.deleted = 1 "
      + " where e.id = ?#{#id} ")
  void deleteById(Long id);
}
