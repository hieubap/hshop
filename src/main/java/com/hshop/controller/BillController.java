package com.hshop.controller;

import com.hshop.dto.BillDTO;
import com.hshop.dto.OrderDTO;
import com.hshop.dto.ResponseDTO;
import com.hshop.service.BillService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/bill")
public class BillController {

  @Autowired
  private BillService billService;

  @GetMapping
  public ResponseDTO<?> search(BillDTO billDTO,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size){
    return billService.search(billDTO,page,size);
  }

  @GetMapping(value = "/dashboard")
  public ResponseDTO<?> dashboard(
      @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
      @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to){
    return billService.dashboard(from, to);
  }

  @PostMapping
  public ResponseDTO<?> order(@RequestBody OrderDTO orderDTO) throws Exception {
    return billService.create(orderDTO);
  }

  @PutMapping(value = "/store/confirm/{id}")
  public ResponseDTO<?> storeConfirm(@PathVariable(value = "id") Long id) throws Exception {
    return billService.storeConfirm(id);
  }

  @PutMapping(value = "/cancel/{id}")
  public ResponseDTO<?> cancelBill(@PathVariable(value = "id") Long id) throws Exception {
    return billService.cancel(id);
  }

  @PutMapping(value = "/delivered/{id}")
  public ResponseDTO<?> deliveredBill(@PathVariable(value = "id") Long id) throws Exception {
    return billService.delivered(id);
  }

  @DeleteMapping("/{id}")
  public ResponseDTO<?> deleteBill(@PathVariable(value = "id") Long id) throws Exception {
    return billService.delete(id);
  }
}
