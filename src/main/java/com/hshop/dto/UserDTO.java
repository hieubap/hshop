package com.hshop.dto;

import com.hshop.enums.RoleType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
  private Long id;

  private String name;
  private String phone;
  private String email;
  private String address;
  private RoleType role;

  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;
}
