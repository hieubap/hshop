package com.hshop.dao.model;

import com.hshop.enums.BillStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Where(clause = "deleted = 0")
@Entity
@Table(name = "bill")
public class BillEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BillStatus status;

  @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  private List<BillProductEntity> listFoods;

  @ManyToOne
  @JoinColumn(name = "buyer_id")
  private UserEntity buyer;

  private LocalDateTime deliveredDate;

}
