package risingStarEMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryEmployeeRepository implements EmployeeRepository {
  private final Map<String, Employee> employeeMap = new HashMap<>();
  
  @Override
  public Employee save(Employee employee) {
    employeeMap.put(employee.getId(), employee);
    return employee;
  }
  
  @Override
  public Optional<Employee> findById(String id) {
    return Optional.ofNullable(employeeMap.get(id));
  }

  @Override
  public List<Employee> findAll() {
    return new ArrayList<>(employeeMap.values());
  }

  @Override
  public void deleteById(String id) {
    employeeMap.remove(id);
  }

  @Override
  public void update(Employee employee) {
    employeeMap.put(employee.getId(), employee);
  }
}
