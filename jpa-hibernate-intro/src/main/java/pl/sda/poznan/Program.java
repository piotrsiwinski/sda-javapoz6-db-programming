package pl.sda.poznan;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import pl.sda.poznan.model.Category;
import pl.sda.poznan.model.Product;

public class Program {


  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
    EntityManager entityManager = factory.createEntityManager();
    seedData(entityManager);
    // get all products

    Query query = entityManager.createQuery("from Product");
    List resultList = query.getResultList();

    // Find by id
    Query getByIdQuery = entityManager.createQuery("select p from Product p where p.id = :productId");
    getByIdQuery.setParameter("productId", 2L);
    Product singleResult = (Product) getByIdQuery.getSingleResult();


  }

  private static void seedData(EntityManager entityManager) {
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
