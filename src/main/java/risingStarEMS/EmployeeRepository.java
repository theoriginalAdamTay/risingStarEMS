package risingStarEMS;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
  Employee save(Employee employee);
  Optional<Employee> findById(String id);
  List<Employee> findAll();
  boolean deleteById(String id);
  boolean update(Employee employee);
}
