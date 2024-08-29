package risingStarEMS;

import java.util.List;
import java.util.Optional;

/** A class used for implementation of the employee service providing employee management functionality.
 * 
 * @author Adam Tay
 * @version 1.0 unstable
 */
public class EmployeeService {
  /**
   * An instance of the EmployeeRepository interface allowing for implementation of employee management-related methods.
   */
  private final EmployeeRepository employeeRepository;
  
  /**
   * The constructor for the EmployeeService class.
   * @param employeeRepository An instance of the EmployeeRepository interface.
   */
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }
  
  /**
   * A function for adding a new employee to the system.
   * @param id The ID to be associated with the employee.
   * @param name The employee's name.
   * @param position The employee's position.
   * @param department The employee's department.
   * @return The employee with the given details saved to the repository.
   */
  public Employee addEmployee(String id, String name, String position, String department) {
    Employee employee = new Employee(id, name, position, department);
    return employeeRepository.save(employee);
  }
  
  /**
   * A function for finding an employee given their ID.
   * @param id The ID of the employee to be found.
   * @return The details of the employee associated with the given ID.
   */
  public Optional<Employee> findEmployeeById(String id) {
    return employeeRepository.findById(id);
  }
  
  /**
   * A function for retrieving all employees in the system.
   * @return The details of all employees saved to the repository.
   */
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
  
  /**
   * A function for deleting an employee from the system.
   * @param id The ID of the employee being deleted from the system.
   */
  public void removeEmployee(String id) {
    employeeRepository.deleteById(id);
  }
  
  /**
   * A function for updating the details of an existing employee.
   * @param id The ID of the employee whose details are being updated.
   * @param name The new name of the employee to be stored in the repository.
   * @param position The new position of the employee to be stored in the repository.
   * @param department The new department of the employee to be stored in the repository.
   */
  public void updateEmployee(String id, String name, String position, String department) {
    Optional<Employee> employeeOpt = employeeRepository.findById(id);
    if (employeeOpt.isPresent()) {
      Employee employee = employeeOpt.get();
      employee.setName(name);
      employee.setPosition(position);
      employee.setDepartment(department);
      employeeRepository.update(employee);
    } else {
      System.out.println("Employee not found");
    }
  }
}
