package spring.backend.hshop.dto;

import spring.backend.hshop.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.backend.library.dto.BaseDTO;

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
