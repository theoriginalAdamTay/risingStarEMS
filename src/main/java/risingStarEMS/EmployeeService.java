package risingStarEMS;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }
  
  public Employee addEmployee(String id, String name, String position, String department) {
    Employee employee = new Employee(id, name, position, department);
    return employeeRepository.save(employee);
  }
  
  public Optional<Employee> findEmployeeById(String id) {
    return employeeRepository.findById(id);
  }
  
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
  
  public void removeEmployee(String id) {
    employeeRepository.deleteById(id);
  }
  
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
