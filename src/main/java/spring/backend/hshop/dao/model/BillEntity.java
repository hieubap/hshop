package spring.backend.hshop.dao.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import spring.backend.library.dao.model.BaseEntity;

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

  private Short status;
  private String address;
  private String phone;
  private String nameBuyer;
  private String note;

  @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  private List<OrderEntity> listFoods;

  @ManyToOne
  @JoinColumn(name = "buyer_id")
  private UserEntity buyer;

  @OneToOne
  @JoinColumn(name = "store_id",insertable = false,updatable = false)
  private StoreEntity store;

  @Column(name = "store_id")
  private Long storeId;

  private LocalDateTime deliveredDate;

}
