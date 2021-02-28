package com.hshop.service;

import com.google.common.base.Strings;
import com.hshop.dao.model.EvaluateEntity;
import com.hshop.dao.model.NotificationEntity;
import com.hshop.dao.repository.EvaluateRepository;
import com.hshop.dao.repository.ProductRepository;
import com.hshop.dao.repository.UserRepository;
import com.hshop.dto.EvaluateDTO;
import com.hshop.dto.ProductDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EvaluateServiceImpl implements EvaluateService {
  @Autowired
  private EvaluateRepository evaluateRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public ResponseDTO<?> create(EvaluateDTO dto) throws BaseException {
    if (Strings.isNullOrEmpty(dto.getContent())|| dto.getProductId() == null
    || dto.getUserId() == null || dto.getStar() == null){
      throw new BaseException(400,"content or star or productId or userId is null or empty",dto);
    }
    if (!productRepository.existsById(dto.getProductId()) || !userRepository.existsById(dto.getUserId())){
      throw new BaseException(400,"productId or userId is not exist",dto);
    }
    EvaluateEntity entity = new EvaluateEntity();
    entity.setContent(dto.getContent());
    entity.setStar(dto.getStar());
    entity.setUserId(dto.getUserId());
    entity.setProductId(dto.getProductId());

    evaluateRepository.save(entity);
    dto.setId(entity.getId());
    dto.setCreatedDate(entity.getCreatedDate());
    dto.setUpdatedDate(entity.getUpdatedDate());

    return new ResponseDTO<>(200,"create ok" , dto);
  }

  @Override
  public ResponseDTO<?> update(Long id, EvaluateDTO dto) throws BaseException {
    if (!evaluateRepository.existsById(id)){
      throw new BaseException(400,"id is not exist",dto);
    }
    if (!productRepository.existsById(dto.getProductId()) || !userRepository.existsById(dto.getUserId())){
      throw new BaseException(400,"productId or userId is not exist",dto);
    }
    EvaluateEntity entity = evaluateRepository.findById(id).get();
    entity.setContent(dto.getContent());
    entity.setStar(dto.getStar());
    entity.setUserId(dto.getUserId());
    entity.setProductId(dto.getProductId());

    evaluateRepository.save(entity);

    return new ResponseDTO<>(200,"update ok" , dto);
  }

  @Override
  public ResponseDTO<?> delete(Long id) throws BaseException {
    if (!evaluateRepository.existsById(id)){
      throw new BaseException(400,"id is not exist",null);
    }
    evaluateRepository.deleteById(id);

    return new ResponseDTO<>(200,"delete ok" , null);
  }

  @Override
  public ResponseDTO<?> search(EvaluateDTO dto, Integer page, Integer size) {
    Sort sort = Sort.by(Sort.Direction.ASC,"id");
    Page<EvaluateEntity> list = evaluateRepository.search(dto, PageRequest
        .of(page-1,size,sort));

    return new ResponseDTO<>(200, "find successful", list.toList(), list.toList().size(),
        (int) list.getTotalElements());
  }
}