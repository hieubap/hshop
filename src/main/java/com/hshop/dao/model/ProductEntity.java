package com.hshop.dao.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  @PrePersist
  void prePersist() {
    createdDate = LocalDateTime.now();
    updatedDate = createdDate;
  }

  @PreUpdate
  void preUpdate() {
    updatedDate = LocalDateTime.now();
  }
}
