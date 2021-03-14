package spring.backend.hshop.controller;

import spring.backend.hshop.dto.NotificationDTO;
import spring.backend.hshop.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.backend.library.controller.BaseController;
import spring.backend.library.dto.ResponseEntity;

@CrossOrigin
@RestController
@RequestMapping(value = "notification")
public class NotificationController extends BaseController<NotificationDTO,NotificationService> {

  @Autowired
  public NotificationService service;

  @Override
  public NotificationService getService() {
    return service;
  }


//    @GetMapping
//    public ResponseDTO<?> search(
//        @RequestParam(defaultValue = "1") Integer page,
//        @RequestParam(defaultValue = "10") Integer size,
//        NotificationDTO data
//    ) throws Exception {
//      return service.search(data, page, size);
//    }
//
//
    @GetMapping("/read/count")
    public ResponseEntity<?> countRead() {
      return response(service.countRead());
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<?> read(@PathVariable Long id){
      return response(service.read(id));
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseDTO<?> delete(@PathVariable Long id) throws Exception {
//      return service.delete(id);
//    }
  }