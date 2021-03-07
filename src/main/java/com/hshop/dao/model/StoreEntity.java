package com.hshop.dao.model;

import com.google.errorprone.annotations.FormatString;
import java.sql.Time;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.checkerframework.checker.formatter.qual.Format;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import spring.library.common.dao.model.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "store")
@Where(clause = "deleted = 0")
public class StoreEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String address;
  private String email;
  private String phone;

  @DateTimeFormat(pattern="HH:mm:ss")
  private Time timeStart;

  @DateTimeFormat(pattern="HH:mm:ss")
  private Time timeEnd;

  @Column(name = "owner_id")
  private Long ownerId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id",updatable = false,insertable = false)
  private UserEntity owner;
}
