package com.hshop.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class TypeProductDTO {

  private Long id;
  private String name;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

}
