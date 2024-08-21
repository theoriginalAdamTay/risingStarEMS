package risingStarEMS;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
  Employee save(Employee employee);
  Optional<Employee> findById(String id);
  List<Employee> findAll();
  void deleteById(String id);
  void update(Employee employee);
}
