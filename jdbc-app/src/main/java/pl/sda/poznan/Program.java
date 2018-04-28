package pl.sda.poznan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.sda.poznan.util.DbUtils;

public class Program {

  public static void main(String[] args) throws SQLException {
    ResultSet rs = DbUtils.getConnection().createStatement()
        .executeQuery("SELECT COUNT(ID) FROM users");

    rs.next();
    int count = rs.getInt(1);
    System.out.println(count);
  }
}
