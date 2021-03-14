package spring.backend.hshop.configuration.dto;

import spring.backend.hshop.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {
  private String username;
  private String password;

  private String name;
  private String phone;
  private String address;
  private String email;
  private RoleType role;
}