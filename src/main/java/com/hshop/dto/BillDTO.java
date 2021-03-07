package com.hshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.library.common.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO extends BaseDTO {
  private Long id;

  private List<Bill_Product> foods;
  private Long buyerId;
  private UserDTO user;

  private Long total;
  private String status;

  private LocalDateTime date;

  @JsonInclude(value = Include.NON_NULL)
  private Long userId;

  @JsonInclude(value = Include.NON_NULL)
  private Map<Long,Integer> listFoodsOrder;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Bill_Product {
    public ProductDTO food;
    public Integer number;
    public Long price;
  }
}
