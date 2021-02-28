package com.hshop.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EvaluateDTO {
  private Long id;

  private Long userId;
  private UserDTO user;

  private Long productId;
  private ProductDTO product;

  private String content;
  private Short star;

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}
