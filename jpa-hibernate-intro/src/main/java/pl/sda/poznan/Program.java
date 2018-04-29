package pl.sda.poznan;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
    EntityManager entityManager = factory.createEntityManager();


    Message hello = new Message();
    hello.setMessage("Hello hibernate");
    hello.setDate(new Date());

    Message anotherMessage = new Message();
    anotherMessage.setMessage("just another message");

    entityManager.getTransaction().begin();
    entityManager.persist(hello);
    entityManager.persist(anotherMessage);
    entityManager.getTransaction().commit();
  }

}
