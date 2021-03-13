package com.hshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spring.library.common.controller.BaseResponseController;
import spring.library.common.dto.ResponseEntity;
import spring.library.common.storage.StorageService;

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
