package com.hshop.service;


import com.hshop.dto.ProductDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.exception.BaseException;

public interface ProductService {
  ResponseDTO<?> search(ProductDTO dto,Integer page,Integer size);
  ResponseDTO<?> create(ProductDTO dto) throws BaseException;
  ResponseDTO<?> update(Long id, ProductDTO dto) throws Exception;
  ResponseDTO<?> delete(Long id) throws Exception;

}
