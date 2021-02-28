package com.hshop.dao.model;

import com.sun.javafx.beans.IDProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "type_product")
public class TypeProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  @Column(name = "deleted",nullable = false)
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
