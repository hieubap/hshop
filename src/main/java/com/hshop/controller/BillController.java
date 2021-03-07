package com.hshop.controller;

import com.hshop.dto.BillDTO;
import com.hshop.service.BillService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.library.common.controller.BaseController;
import spring.library.common.dto.ResponseEntity;

@CrossOrigin
@RestController
@RequestMapping(value = "/bill")
public class BillController extends BaseController<BillDTO,BillService> {

  @Autowired
  private BillService billService;

  @Override
  public BillService getService() {
    return billService;
  }

//  @GetMapping
//  public ResponseDTO<?> search(BillDTO billDTO,
//      @RequestParam(defaultValue = "1") Integer page,
//      @RequestParam(defaultValue = "10") Integer size){
//    return billService.search(billDTO,page,size);
//  }

  @GetMapping(value = "/dashboard")
  public ResponseEntity<?> dashboard(
      @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
      @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to,
      @PageableDefault(size = 200, sort = "year", direction = Sort.Direction.DESC) Pageable pageable){
    return response(billService.dashboard(from, to,pageable));
  }

//  @PostMapping
//  public ResponseDTO<?> order(@RequestBody OrderDTO orderDTO) throws Exception {
//    return billService.create(orderDTO);
//  }

  @PutMapping(value = "/store/confirm/{id}")
  public ResponseEntity<?> storeConfirm(@PathVariable(value = "id") Long id){
    return response(billService.storeConfirm(id));
  }

  @PutMapping(value = "/cancel/{id}")
  public ResponseEntity<?> cancelBill(@PathVariable(value = "id") Long id) {
    return response(billService.cancel(id));
  }

  @PutMapping(value = "/delivered/{id}")
  public ResponseEntity<?> deliveredBill(@PathVariable(value = "id") Long id){
    return response(billService.delivered(id));
  }

//  @DeleteMapping("/{id}")
//  public ResponseDTO<?> deleteBill(@PathVariable(value = "id") Long id) throws Exception {
//    return billService.delete(id);
//  }
}
