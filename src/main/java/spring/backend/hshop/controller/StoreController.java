package spring.backend.hshop.controller;

import spring.backend.hshop.dto.StoreDTO;
import spring.backend.hshop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.backend.library.controller.BaseController;

@CrossOrigin
@RestController
@RequestMapping(value = "store")
public class StoreController extends BaseController<StoreDTO,StoreService> {

  @Autowired
  public StoreService service;

  @Override
  public StoreService getService() {
    return service;
  }

//  @GetMapping
//  public ResponseDTO<?> search(
//      @RequestParam(defaultValue = "1") Integer page,
//      @RequestParam(defaultValue = "10") Integer size,
//      StoreDTO data
//  ) throws Exception {
//    return service.search(data, page, size);
//  }
//
//
//  @PostMapping
//  public ResponseDTO<?> create(@RequestBody StoreDTO storeDTO) throws Exception {
//    return service.create(storeDTO);
//  }
//
//  @PutMapping("/{id}")
//  public ResponseDTO<?> update(@RequestBody StoreDTO storeDTO, @PathVariable Long id)
//      throws Exception {
//    return service.update(id, storeDTO);
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseDTO<?> delete(@PathVariable Long id) throws Exception {
//    return service.delete(id);
//  }
}
