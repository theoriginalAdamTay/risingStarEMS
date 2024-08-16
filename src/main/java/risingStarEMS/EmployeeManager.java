package risingStarEMS;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {
  private Map<String, Employee> employees = new HashMap<>();
  
  public void addEmployee(Employee employee) {
    employees.put(employee.getId(), employee);
  }
  
  public Employee getEmployee(String id) {
    return employees.get(id);
  }
  
  public void removeEmployee(String id) {
    employees.remove(id);
  }
}
