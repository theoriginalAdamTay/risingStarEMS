package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEmployeeManagement {
  private EmployeeManager employeeManager;
  
  @BeforeEach
  public void setup() {
    employeeManager = new EmployeeManager();
  }
  
  @Test
  public void testEmployeeAddition() {
    Employee emp = new Employee("1", "Jimothy Dough");
    employeeManager.addEmployee(emp);
    Employee retrievedEmployee = employeeManager.getEmployee("1");
    assertNotNull(retrievedEmployee);
    assertEquals("Jimothy Dough", retrievedEmployee.getName());
  }
  
  @Test
  public void testEmployeeRetrieval() {
    Employee emp = new Employee("2", "Jimian Dougheth");
    employeeManager.addEmployee(emp);
    Employee retrievedEmployee = employeeManager.getEmployee("2");
    assertNotNull(retrievedEmployee);
    assertEquals("Jimian Dougheth", retrievedEmployee.getName());
  }
  
  @Test
  public void testEmployeeDeletion() {
    Employee emp = new Employee("3", "Jiminy Doughethness");
    employeeManager.addEmployee(emp);
    employeeManager.removeEmployee("3");
    Employee retrievedEmployee = employeeManager.getEmployee("3");
    assertNull(retrievedEmployee);
  }
  
  @Test
  public void testNonExistentEmployeeRetrieval() {
    Employee retrievedEmployee = employeeManager.getEmployee("123");
    assertNull(retrievedEmployee);
  }
}
