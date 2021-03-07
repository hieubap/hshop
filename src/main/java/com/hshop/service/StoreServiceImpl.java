package com.hshop.service;

import com.google.common.base.Strings;
import com.hshop.dao.model.StoreEntity;
import com.hshop.dao.repository.StoreRepository;
import com.hshop.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.library.common.exception.BaseException;
import spring.library.common.service.AbstractBaseService;

@Service
public class StoreServiceImpl extends
    AbstractBaseService<StoreEntity,StoreDTO,StoreRepository> implements StoreService {
  @Autowired
  private StoreRepository storeRepository;

  @Override
  protected StoreRepository getRepository() {
    return storeRepository;
  }

  @Override
  protected void beforeSave(StoreEntity entity, StoreDTO dto) {
    super.beforeSave(entity, dto);
    if (Strings.isNullOrEmpty(dto.getName())
        || Strings.isNullOrEmpty(dto.getAddress())
        || Strings.isNullOrEmpty(dto.getEmail())
        || Strings.isNullOrEmpty(dto.getPhone())
        || dto.getTimeStart() == null
        || dto.getTimeEnd() == null
        || dto.getOwnerId() == null) {
      throw new BaseException(400,
          "name or address or email or phone or timeStart or timeEnd or ownerId is null or empty",
          null);
    }
  }

  //  @Override
//  public ResponseDTO<?> create(StoreDTO dto) throws Exception {
//    if (Strings.isNullOrEmpty(dto.getName())
//        || Strings.isNullOrEmpty(dto.getAddress())
//        || Strings.isNullOrEmpty(dto.getEmail())
//        || Strings.isNullOrEmpty(dto.getPhone())
//        || dto.getTimeStart() == null
//        || dto.getTimeEnd() == null
//        || dto.getOwnerId() == null) {
//      throw new BaseException(400,
//          "name or address or email or phone or timeStart or timeEnd or ownerId is null or empty",
//          null);
//    }
//    StoreEntity entity = new StoreEntity();
//    entity.setName(dto.getName());
//    entity.setAddress(dto.getAddress());
//    entity.setEmail(dto.getEmail());
//    entity.setPhone(dto.getPhone());
//    entity.setTimeStart(dto.getTimeStart());
//    entity.setTimeEnd(dto.getTimeEnd());
//    entity.setOwnerId(dto.getOwnerId());
//
//    storeRepository.save(entity);
//
//    return new ResponseDTO<>(200,"create successful", Converter.convertStoreToDTO(entity));
//  }

//  @Override
//  public ResponseDTO<?> update(Long id, StoreDTO storeDTO) throws Exception {
//    if (!storeRepository.existsById(id)){
//      throw new BaseException(400,"id not exist",null);
//    }
//    StoreEntity entity = Converter.convertStoreToEntity(storeDTO);
//    entity.setId(id);
//    storeRepository.save(entity);
//
//    return new ResponseDTO<>(200,"update successful",Converter.convertStoreToDTO(entity));
//  }
//
//  @Override
//  public ResponseDTO<?> delete(Long id) throws BaseException {
//    if (!storeRepository.existsById(id)){
//      throw new BaseException(400,"id not exist",null);
//    }
//
//    storeRepository.deleteById(id);
//
//    return new ResponseDTO<>(200,"delete successful",null);
//  }
//
//  @Override
//  public ResponseDTO<?> search(StoreDTO dto, Integer page, Integer size) {
//    Sort sort = Sort.by(Sort.Direction.ASC,"id");
//    Page<StoreEntity> list = storeRepository.search(dto, PageRequest.of(page-1,size,sort));
//    List<StoreDTO> listDTO = new ArrayList<>();
//    for (StoreEntity entity : list){
//      listDTO.add(Converter.convertStoreToDTO(entity));
//    }
//
//    return new ResponseDTO<>(200, "find successful", listDTO, list.toList().size(),
//        (int) list.getTotalElements());
//  }
}