package com.hshop.service;


import com.hshop.dto.FoodDTO;
import org.springframework.http.ResponseEntity;

public interface ProductService {
  ResponseEntity<?> search(FoodDTO dto);
  ResponseEntity<?> create(FoodDTO dto);
  ResponseEntity<?> update(Long id,FoodDTO dto) throws Exception;
  ResponseEntity<?> delete(Long id) throws Exception;
}
