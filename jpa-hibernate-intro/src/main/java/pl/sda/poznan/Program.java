package pl.sda.poznan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.sda.poznan.model.Category;
import pl.sda.poznan.model.Product;

public class Program {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
    EntityManager entityManager = factory.createEntityManager();

    // todo 1 - kategoria
    Category category = new Category();
    category.setName("Laptops");

    //todo 2 - 3 produkty
    Product sony = new Product();
    sony.setName("SONY VAIO");

    Product msi = new Product();
    msi.setName("MSI");

    Product dell = new Product();
    dell.setName("dell");

    //todo 3- ustawienie kategorii w produktach
    sony.setCategory(category);
    msi.setCategory(category);
    dell.setCategory(category);

    entityManager.getTransaction().begin();
    entityManager.persist(category);
    entityManager.persist(msi);
    entityManager.persist(sony);
    entityManager.persist(dell);

    entityManager.getTransaction().commit();
  }

}
