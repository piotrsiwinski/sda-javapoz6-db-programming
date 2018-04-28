package pl.sda.poznan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

  public static void playground() throws SQLException {
    Connection connection = DriverManager
        .getConnection("jdbc:mysql://localhost:3306/javapoz6_sda_jdbc", "root", "password");

    Statement statement = connection.createStatement();
    statement.executeQuery(CREATE_USERS_QUERY);

    connection.createStatement().executeQuery(
        "INSERT INTO users(name, surname, email) VALUES ('piotr', 'kowalski', 'kowalski@wp.pl')");
    connection.createStatement().executeQuery(
        "INSERT INTO users(name, surname, email) VALUES ('piotr', 'nowak', 'nowak@wp.pl')");
    connection.createStatement().executeQuery(
        "INSERT INTO users(name, surname, email) VALUES ('piotr', 'nowacki', 'nowacki@wp.pl')");

    // Pobieranie danych z bazy
    ResultSet resultSet = connection.createStatement()
        .executeQuery("SELECT id, name, surname, email FROM users");

    List<User> users = new ArrayList<User>();
    while (resultSet.next()) {
      User user = new User();
      user.setId(resultSet.getInt(1));
      user.setName(resultSet.getString(2));
      user.setSurname(resultSet.getString("surname"));
      user.setEmail(resultSet.getString("email"));
      users.add(user);
    }

    users.forEach(System.out::println);

    // ZADANIE - wyswietlic liczbe wszystkich uzytskownikow w bazie

    ResultSet rs = connection.createStatement()
        .executeQuery("SELECT COUNT(ID) FROM users");

    rs.next();
    int count = rs.getInt(1);

    System.out.println("Mamy " + count + " pracownikow ");

    // UPDATE - zaktuazlizowac uzytkownikowni o podanym id jego adres email
    connection
        .createStatement()
        .executeUpdate("UPDATE USERS SET EMAIL='update@wp.pl' WHERE ID = 1");

    // DELETE -- usunac uzytkownika kowalski
    connection.createStatement()
        .executeUpdate("DELETE FROM users where id = 2");
  }

  private static final String CREATE_USERS_QUERY = "CREATE TABLE IF NOT EXISTS USERS(id int primary key AUTO_INCREMENT, name varchar(255), surname varchar(255), email varchar(255))";

  public static void sqlInjection(Connection connection, String name) throws SQLException {
    String query = String.format("INSERT INTO users (surname) values( '%s')", name);
    connection
        .createStatement()
        .execute(query);
  }



  public static void main(String[] args) throws SQLException {
    Connection connection = DriverManager
        .getConnection("jdbc:mysql://localhost:3306/javapoz6_sda_jdbc", "root", "password");

    Scanner scanner = new Scanner(System.in);
    System.out.println("Podaj swoje imie");
    String name = scanner.nextLine();
    System.out.println("Podaj swoje nazwisko");
    String surname = scanner.nextLine();

    PreparedStatement preparedStatement = connection
        .prepareStatement("INSERT INTO users (name, surname) VALUES (?, ?)");

    preparedStatement.setString(1, name);
    preparedStatement.setString(2, surname);
    preparedStatement.execute();

  }
}
