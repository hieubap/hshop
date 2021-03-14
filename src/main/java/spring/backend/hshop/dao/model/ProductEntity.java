package spring.backend.hshop.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Entity
@Where(clause = "deleted=0")
@Table(name = "product")
public class ProductEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String name;
  private Short star;
  private Long numberComment;
  private Long numberSell;
  private Long number;
  private Long oldPrice;
  private Long newPrice;
  private Short type;
  private String color;
  private String tradeMark;
  private String manufactureCountry;
  private String volume;
  private String mass;
  private String detail;
  private String img;
  private String distributor;

  @Column(name = "store_id")
  private Long storeId;

  @OneToOne
  @JoinColumn(name = "seller_id")
  private UserEntity seller;

  @OneToOne
  @JoinColumn(name = "store_id",updatable = false,insertable = false)
  private StoreEntity store;
}
