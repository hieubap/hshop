package com.hshop.dto;

import java.sql.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreDTO {
  private Long id;
  private String name;
  private String address;
  private String email;
  private String phone;
  private Time timeStart;
  private Time timeEnd;
  private Long ownerId;
  private UserDTO owner;

}
