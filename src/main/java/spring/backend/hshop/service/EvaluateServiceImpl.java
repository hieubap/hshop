package spring.backend.hshop.service;

import com.google.common.base.Strings;
import spring.backend.hshop.dao.model.EvaluateEntity;
import spring.backend.hshop.dao.repository.EvaluateRepository;
import spring.backend.hshop.dao.repository.ProductRepository;
import spring.backend.hshop.dao.repository.UserRepository;
import spring.backend.hshop.dto.EvaluateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.backend.library.exception.BaseException;
import spring.backend.library.service.AbstractBaseService;

@Service
public class EvaluateServiceImpl extends
    AbstractBaseService<EvaluateEntity, EvaluateDTO,EvaluateRepository> implements EvaluateService {
  @Autowired
  private EvaluateRepository evaluateRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  protected EvaluateRepository getRepository() {
    return evaluateRepository;
  }

  @Override
  protected void beforeSave(EvaluateEntity entity, EvaluateDTO dto) {
    super.beforeSave(entity, dto);
    if (Strings.isNullOrEmpty(dto.getContent())|| dto.getProductId() == null
        || dto.getUserId() == null || dto.getStar() == null){
      throw new BaseException(400,"content or star or productId or userId is null or empty",dto);
    }
    if (!productRepository.existsById(dto.getProductId()) || !userRepository.existsById(dto.getUserId())){
      throw new BaseException(400,"productId or userId is not exist",dto);
    }
  }
  //  @Override
//  public ResponseDTO<?> create(EvaluateDTO dto) throws BaseException {
//
//    EvaluateEntity entity = new EvaluateEntity();
//    entity.setContent(dto.getContent());
//    entity.setStar(dto.getStar());
//    entity.setUserId(dto.getUserId());
//    entity.setProductId(dto.getProductId());
//
//    evaluateRepository.save(entity);
//    dto.setId(entity.getId());
//    dto.setCreatedDate(entity.getCreatedDate());
//    dto.setUpdatedDate(entity.getUpdatedDate());
//
//    return new ResponseDTO<>(200,"create ok" , dto);
//  }

//  @Override
//  public ResponseDTO<?> update(Long id, EvaluateDTO dto) throws BaseException {
//    if (!evaluateRepository.existsById(id)){
//      throw new BaseException(400,"id is not exist",dto);
//    }
//    if (!productRepository.existsById(dto.getProductId()) || !userRepository.existsById(dto.getUserId())){
//      throw new BaseException(400,"productId or userId is not exist",dto);
//    }
//    EvaluateEntity entity = evaluateRepository.findById(id).get();
//    entity.setContent(dto.getContent());
//    entity.setStar(dto.getStar());
//    entity.setUserId(dto.getUserId());
//    entity.setProductId(dto.getProductId());
//
//    evaluateRepository.save(entity);
//
//    return new ResponseDTO<>(200,"update ok" , dto);
//  }

//  @Override
//  public ResponseDTO<?> delete(Long id) throws BaseException {
//    if (!evaluateRepository.existsById(id)){
//      throw new BaseException(400,"id is not exist",null);
//    }
//    evaluateRepository.deleteById(id);
//
//    return new ResponseDTO<>(200,"delete ok" , null);
//  }
//
//  @Override
//  public ResponseDTO<?> search(EvaluateDTO dto, Integer page, Integer size) {
//    Sort sort = Sort.by(Sort.Direction.ASC,"id");
//    Page<EvaluateEntity> list = evaluateRepository.search(dto, PageRequest
//        .of(page-1,size,sort));
//
//    return new ResponseDTO<>(200, "find successful", list.toList(), list.toList().size(),
//        (int) list.getTotalElements());
//  }
}