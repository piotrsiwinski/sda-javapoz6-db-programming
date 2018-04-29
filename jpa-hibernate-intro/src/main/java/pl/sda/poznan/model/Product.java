package pl.sda.poznan.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Double price;

  @ManyToOne
  @PrimaryKeyJoinColumn(name = "category_id") //todo
  private Category category;

  // todo
  @ManyToMany
  @JoinTable(
      name = "product_order",
      joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}
  )
  private Set<ClientOrder> clientOrders = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
