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

  private String img;
  private String name;
  private Long oldPrice;
  private Long curPrice;
  private Long sold;
  private String brand;
  private String origin;
  private String react;
  private Long per;
  private String label;
  private String link;
  private String liked;
  private Long rev;
  private Long oldPriceInfo;
  private Long curPriceInfo;
  private Long available;
  private String owner;
  private Long revTotal;
  private Long resRate;

  private Long proQnt;
  private String resTime;
  private String follower;
  private String material;

  @Column(name = "address")
  private String from;

  private Long depot;

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
