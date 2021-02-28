package com.hshop.dao.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "notification")
public class NotificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  /** 1: chưa đọc
   *  2: đã đọc
   */
  private Short isRead = 1;

  @Column(name = "user_id")
  private Long userId;

  @ManyToOne
  @JoinColumn(name = "user_id",updatable = false,insertable = false)
  private UserEntity user;

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
