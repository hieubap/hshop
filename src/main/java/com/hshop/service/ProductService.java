package com.hshop.service;


import com.hshop.dto.ProductDTO;
import com.hshop.exception.BaseException;
import org.springframework.http.ResponseEntity;

public interface ProductService {
  ResponseEntity<?> search(ProductDTO dto);
  ResponseEntity<?> create(ProductDTO dto) throws BaseException;
  ResponseEntity<?> update(Long id, ProductDTO dto) throws Exception;
  ResponseEntity<?> delete(Long id) throws Exception;

}
