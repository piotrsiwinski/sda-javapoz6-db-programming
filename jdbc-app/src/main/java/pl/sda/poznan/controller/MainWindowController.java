package pl.sda.poznan.controller;

import java.util.Date;
import javafx.event.ActionEvent;
import pl.sda.poznan.dao.BaseEmployeeDao;
import pl.sda.poznan.dao.EmployeeDao;
import pl.sda.poznan.model.Employee;

public class MainWindowController {

  private EmployeeDao employeeDao = new BaseEmployeeDao();

  public void readEmployee(ActionEvent actionEvent) {
    Employee e = employeeDao.getById(1);
    System.out.println("Button clicked");
  }

  public void insertEmployee(ActionEvent actionEvent) {
    Employee employee = new Employee();
    employee.setName("Piotr");
    employee.setSurname("Nowak");
    employee.setHireDate(new Date());
    employee.setSalary(2400d);
    employeeDao.save(employee);
  }
}
