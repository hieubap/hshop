package com.hshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.library.common.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO extends BaseDTO {
  private Long id;
  private String content;
  private Short isRead;
  private Long ownerId;
}
