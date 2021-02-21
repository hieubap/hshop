package com.hshop.dto;

import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO {
  private Long id;

  private String title;
  private String name;
  private Short star;
  private Long numberComment;
  private Long numberSell;
  private Long number;
  private Long oldPrice;
  private Long newPrice;
  private String type;
  private String color;
  private String tradeMark;
  private String manufactureCountry;
  private String volume;
  private String mass;
  private String detail;
  private String img;
  private String distributor;
  private Integer per;

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}
