package com.hshop.service;

import com.hshop.dto.OrderDTO;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;


public interface BillService {
  public ResponseEntity<?> search();
  public ResponseEntity<?> create(OrderDTO dto) throws Exception;
  public ResponseEntity<?> update(Long id,OrderDTO dto) throws Exception;
  public ResponseEntity<?> delete(Long id) throws Exception;

  public ResponseEntity<?> storeConfirm(Long id) throws Exception;
  public ResponseEntity<?> cancel(Long id) throws Exception;
  public ResponseEntity<?> delivered(Long id) throws Exception;
  public ResponseEntity<?> dashboard(LocalDate start,LocalDate end);
}
