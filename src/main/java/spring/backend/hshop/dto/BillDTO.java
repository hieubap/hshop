package spring.backend.hshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.backend.library.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO extends BaseDTO {
  private Long id;

  private List<Bill_Product> foods;

  @JsonInclude(value = Include.NON_NULL)
  private Long buyerId;

  private UserDTO buyer;

  private Long total;

  private Short status;

  private String statusName;

  private LocalDateTime date;

  private Long storeId;

  @JsonInclude(value = Include.NON_NULL)
  private Map<Long,Integer> listFoodsOrder;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Bill_Product {
    public ProductDTO food;
    public Integer number;
    public Long price;
  }
}
