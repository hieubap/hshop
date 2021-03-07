package com.hshop.dao.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import spring.library.common.dao.model.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "evaluate")
@Where(clause = "deleted = 0")
public class EvaluateEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;
  private Short star;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "product_id")
  private Long productId;


  @ManyToOne
  @JoinColumn(name = "user_id",updatable = false,insertable = false)
  private UserEntity user;

  @OneToOne
  @JoinColumn(name = "product_id",updatable = false,insertable = false)
  private ProductEntity product;
}
