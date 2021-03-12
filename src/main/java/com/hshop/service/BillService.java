package com.hshop.service;

import com.hshop.dto.BillDTO;
import com.hshop.dto.OrderDTO;
import com.hshop.dto.ResponseDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.library.common.service.BaseService;


public interface BillService extends BaseService<BillDTO> {
//  public ResponseDTO<?> search(BillDTO dto,Integer page,Integer size);
//  public ResponseDTO<?> create(OrderDTO dto) throws Exception;
//  public ResponseDTO<?> update(Long id,OrderDTO dto) throws Exception;
//  public ResponseDTO<?> delete(Long id) throws Exception;

  BillDTO storeConfirm(Long id);
  BillDTO cancel(Long id);
  BillDTO delivered(Long id);
  Page<Map<Long,Long>> chart(LocalDate start,LocalDate end, Pageable pageable);
  Page<BillDTO> getOrder(Long storeId,Pageable pageable);
}
