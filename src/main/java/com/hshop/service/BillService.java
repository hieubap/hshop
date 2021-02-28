package com.hshop.service;

import com.hshop.dto.BillDTO;
import com.hshop.dto.OrderDTO;
import com.hshop.dto.ResponseDTO;
import java.time.LocalDate;


public interface BillService {
  public ResponseDTO<?> search(BillDTO dto,Integer page,Integer size);
  public ResponseDTO<?> create(OrderDTO dto) throws Exception;
  public ResponseDTO<?> update(Long id,OrderDTO dto) throws Exception;
  public ResponseDTO<?> delete(Long id) throws Exception;

  public ResponseDTO<?> storeConfirm(Long id) throws Exception;
  public ResponseDTO<?> cancel(Long id) throws Exception;
  public ResponseDTO<?> delivered(Long id) throws Exception;
  public ResponseDTO<?> dashboard(LocalDate start,LocalDate end);
}
