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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "evaluate")
public class EvaluateEntity {
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

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  private Short deleted = 0;

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
