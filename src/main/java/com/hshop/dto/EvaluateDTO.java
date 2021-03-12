package com.hshop.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.library.common.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class EvaluateDTO extends BaseDTO {
  private Long id;

  private Long userId;
  private Object user;

  private Long productId;
  private Object product;

  private String content;
  private Short star;
}
