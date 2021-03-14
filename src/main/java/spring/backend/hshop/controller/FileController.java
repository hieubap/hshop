package spring.backend.hshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spring.backend.library.controller.BaseResponseController;
import spring.backend.library.dto.ResponseEntity;
import spring.backend.library.storage.StorageService;

@RestController
@RequestMapping("/file")
public class FileController extends BaseResponseController {
  @Autowired
  private StorageService storageService;

  @PostMapping
  public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile){
    return response(storageService.upload(multipartFile));
  }

}
