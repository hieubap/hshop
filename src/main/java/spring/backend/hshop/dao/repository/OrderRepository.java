package spring.backend.hshop.dao.repository;

import spring.backend.hshop.dao.model.OrderEntity;
import spring.backend.hshop.dto.OrderDTO;
import spring.backend.library.dao.repository.BaseRepository;

public interface OrderRepository extends BaseRepository<OrderEntity, OrderDTO,Long> {
}
