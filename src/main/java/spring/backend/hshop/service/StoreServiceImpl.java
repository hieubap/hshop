package spring.backend.hshop.service;

import com.google.common.base.Strings;
import spring.backend.hshop.dao.model.StoreEntity;
import spring.backend.hshop.dao.repository.StoreRepository;
import spring.backend.hshop.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.backend.library.exception.BaseException;
import spring.backend.library.service.AbstractBaseService;

@Service
public class StoreServiceImpl extends
    AbstractBaseService<StoreEntity, StoreDTO,StoreRepository> implements StoreService {
  @Autowired
  private StoreRepository storeRepository;
  @Autowired
  private UserServiceImpl userService;

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

  @Override
  protected void specificMapToDTO(StoreEntity entity, StoreDTO dto) {
    super.specificMapToDTO(entity, dto);
    dto.setOwner(userService.mapToDTO(entity.getOwner()));
  }
}