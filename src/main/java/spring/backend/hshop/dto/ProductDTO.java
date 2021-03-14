package spring.backend.hshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring.backend.library.dto.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO extends BaseDTO {
  private Long id;

  private String title;
  private String name;
  private Short star;
  private Long numberComment;
  private Long numberSell;
  private Long number;
  private Long oldPrice;
  private Long newPrice;
  private String typeName;
  private Short type;
  private String color;
  private String tradeMark;
  private String manufactureCountry;
  private String volume;
  private String mass;
  private String detail;
  private String img;
  private String distributor;
  private Integer per;
  private Long storeId;
}
