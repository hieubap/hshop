package com.hshop.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.library.common.dao.model.BaseEntity;
import spring.library.common.service.BaseService;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bill_product")
public class OrderEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "bill_id")
  private Long billId;

  @OneToOne
  @JoinColumn(name = "product_id",insertable = false,updatable = false)
  private ProductEntity food;

  @ManyToOne
  @JoinColumn(name = "bill_id",insertable = false,updatable = false)
  private BillEntity bill;

  private Integer number;

  private Short deleted = 0;
}
