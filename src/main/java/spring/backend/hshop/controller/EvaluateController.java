package spring.backend.hshop.controller;

import spring.backend.hshop.dto.EvaluateDTO;
import spring.backend.hshop.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.backend.library.controller.BaseController;

@CrossOrigin
@RestController
@RequestMapping(value = "/evaluate")
public class EvaluateController extends BaseController<EvaluateDTO,EvaluateService> {
  @Autowired
  public EvaluateService service;

  @Override
  public EvaluateService getService() {
    return service;
  }

//  @GetMapping
//  public ResponseDTO<?> search(
//      @RequestParam(defaultValue = "1") Integer page,
//      @RequestParam(defaultValue = "10") Integer size,
//      EvaluateDTO data
//  ) throws Exception {
//    return service.search(data, page, size);
//  }
//
//
//  @PostMapping
//  public ResponseDTO<?> create(@RequestBody EvaluateDTO evaluateDTO) throws Exception {
//      return service.create(evaluateDTO);
//  }
//
//  @PutMapping("/{id}")
//  public ResponseDTO<?> update(@RequestBody EvaluateDTO evaluateDTO, @PathVariable Long id)
//      throws Exception {
//      return service.update(id, evaluateDTO);
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseDTO<?> delete(@PathVariable Long id) throws Exception {
//    return service.delete(id);
//  }
}