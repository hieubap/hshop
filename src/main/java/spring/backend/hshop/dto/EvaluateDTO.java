package spring.backend.hshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.backend.library.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class EvaluateDTO extends BaseDTO {
  private Long id;

  private Long userId;
  private Object user;

  private Long productId;
  private Object product;

  private String content;
  private Short star;
}
