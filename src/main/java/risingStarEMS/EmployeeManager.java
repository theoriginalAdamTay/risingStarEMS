package risingStarEMS;

import java.util.HashMap;
import java.util.Map;
/**A class providing functionality for employee management through addition, retrieval and deletion of employees.
 * 
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public class EmployeeManager {
  /**
   * A hash map which maps Employee objects to string ID values.
   */
  private Map<String, Employee> employees = new HashMap<>();
  
  /**
   * Adds an employee to the management system.
   * @param employee An employee object associated with an ID to which it will be mapped.
   */
  public void addEmployee(Employee employee) {
    employees.put(employee.getId(), employee);
  }
  
  /**
   * Retrieves an employee from the management system.
   * @param id The ID of the employee to retrieve.
   * @return The associated employee with the given ID.
   */
  public Employee getEmployee(String id) {
    return employees.get(id);
  }
  
  /**
   * Deletes an employee from the management system.
   * @param id The ID of the employee to delete.
   */
  public void removeEmployee(String id) {
    employees.remove(id);
  }
}
