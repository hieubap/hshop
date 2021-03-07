package com.hshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.library.common.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends BaseDTO {
  private Long userId;
  private Long foodId;
  private Long storeId;
  private Long billId;
}
