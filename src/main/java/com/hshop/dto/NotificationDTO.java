package com.hshop.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO {
  private Long id;
  private String content;
  private Short isRead;
  private Long ownerId;

  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDateTime createdDate;
}
