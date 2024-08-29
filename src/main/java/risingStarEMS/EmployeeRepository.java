package risingStarEMS;

import java.util.List;
import java.util.Optional;

/**
 * An interface providing functionality associated with the employee repository.
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public interface EmployeeRepository {
  /**
   * A function for saving an employee to the repository.
   * @param employee An instance of an Employee object to save to the repository.
   * @return The saved employee in the repository.
   */
  Employee save(Employee employee);
  /**
   * A function for retrieving an employee from the repository, given their ID.
   * @param id The ID of the employee to retrieve from the repository.
   * @return The employee associated with the given ID.
   */
  Optional<Employee> findById(String id);
  /**
   * A function for retrieving all employees saved in the repository.
   * @return A list of all employees saved in the repository.
   */
  List<Employee> findAll();
  /**
   * A function for deleting an employee from the repository.
   * @param id The ID of the employee to delete from the repository.
   */
  void deleteById(String id);
  /**
   * A function for updating an employee's details.
   * @param employee The employee whose details are to be updated.
   */
  void update(Employee employee);
}
