package com.hshop.dto;

import com.hshop.enums.RoleType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.library.common.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {
  private Long id;

  private String name;
  private String phone;
  private String email;
  private String address;
  private RoleType role;

  private String username;
  private String password;
}
