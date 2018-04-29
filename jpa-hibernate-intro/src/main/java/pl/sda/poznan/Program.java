package pl.sda.poznan;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import pl.sda.poznan.model.Category;
import pl.sda.poznan.model.Product;
import pl.sda.poznan.model.User;

public class Program {


  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
    EntityManager entityManager = factory.createEntityManager();
    seedData(entityManager);

    User user = new User();
    user.setEmail("pdsadasp");

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();
    Set<ConstraintViolation<User>> violations = validator.validate(user);


    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();
  }

  private static void seedData(EntityManager entityManager) {
    // todo 1 - kategoria
    Category category = new Category();
    category.setName("Laptops");

    //todo 2 - 3 produkty
    Product sony = new Product();
    sony.setName("SONY VAIO");
    sony.setPrice(3500D);

    Product msi = new Product();
    msi.setName("MSI");
    msi.setPrice(3800D);

    Product dell = new Product();
    dell.setName("dell");
    dell.setPrice(4200D);

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
