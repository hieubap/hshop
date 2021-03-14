package spring.backend.hshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.backend.library.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends BaseDTO {
  private Long userId;
  private Long foodId;
  private Long storeId;
  private Long billId;
}
