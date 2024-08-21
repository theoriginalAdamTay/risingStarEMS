package risingStarEMS;

import java.util.List;

public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }
  
  public Employee addEmployee(String name, String position, String department) {
    Employee employee = new Employee();
    employee.setName(name);
    employee.setPosition(position);
    employee.setDepartment(department);
    return employeeRepository.save(employee);
  }
  
  public Employee getEmployee(String id) {
    return employeeRepository.findById(id).orElse(null);
  }
  
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
  
  public boolean updateEmployee(String id, String name, String position, String department) {
    Employee existingEmployee = employeeRepository.findById(id).orElse(null);
    if (existingEmployee != null) {
      existingEmployee.setName(name);
      existingEmployee.setPosition(position);
      existingEmployee.setDepartment(department);
      return employeeRepository.update(existingEmployee);
    }
    return false;
  }
  
  public boolean deleteEmployee(String id) {
    return employeeRepository.deleteById(id);
  }
}
