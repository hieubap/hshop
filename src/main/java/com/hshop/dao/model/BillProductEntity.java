package com.hshop.dao.model;

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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bill_product")
public class BillProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "food_id")
  private ProductEntity food;

  @OneToOne
  @JoinColumn(name = "store_id")
  private StoreEntity store;

  @OneToOne
  @JoinColumn(name = "buyer_id")
  private UserEntity seller;

  @ManyToOne
  @JoinColumn(name = "bill_id")
  private BillEntity bill;

  private Integer number;

  private Short deleted = 0;
}
