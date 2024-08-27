package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TestEmployeeRepository {
  @Mock
  private EmployeeRepository employeeRepository;
  
  @InjectMocks
  private EmployeeService employeeService;
  
  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }
  
  @Test
  void SaveEmployeeSuccessfully() {
    Employee employee = new Employee(null, null, null, null);
    employee.setName("Jimothy Dough");
    employee.setPosition("HR Manager");
    employee.setDepartment("Human Resources");
    when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> {
      Employee emp = invocation.getArgument(0);
      emp.setId("1");
      return emp;
    });
    Employee savedEmployee = employeeRepository.save(employee);
    assertNotNull(savedEmployee.getId());
    assertEquals("Jimothy Dough", savedEmployee.getName());
    assertEquals("HR Manager", savedEmployee.getPosition());
    assertEquals("Human Resources", savedEmployee.getDepartment());
  }
  
  @Test
  void FindEmployeeByIDSuccessfully() {
    Employee employee = new Employee(null, null, null, null);
    employee.setId("1");
    employee.setName("Jimothy Dough");
    employee.setPosition("HR Manager");
    employee.setDepartment("Human Resources");
    when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));
    Optional<Employee> foundEmployee = employeeRepository.findById("1");
    assertTrue(foundEmployee.isPresent());
    assertEquals(employee.getId(), foundEmployee.get().getId());
    assertEquals("Jimothy Dough", foundEmployee.get().getName());
  }
  
  @Test
  void FindNonExistentEmployeeByID() {
    when(employeeRepository.findById("123")).thenReturn(Optional.empty());
    Optional<Employee> foundEmployee = employeeRepository.findById("123");
    assertFalse(foundEmployee.isPresent());
  }
  
  @Test
  void FindAllSuccessfully() {
    Employee employee1 = new Employee(null, null, null, null);
    employee1.setId("1");
    employee1.setName("Jimothy Dough");
    employee1.setPosition("HR Manager");
    employee1.setDepartment("Human Resources");
    Employee employee2 = new Employee(null, null, null, null);
    employee2.setId("2");
    employee2.setName("Jimian Dougheth");
    employee2.setPosition("Payroll Accountant");
    employee2.setDepartment("Payroll");
    when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));
    List<Employee> employees = employeeRepository.findAll();
    assertEquals(2, employees.size());
    assertTrue(employees.contains(employee1));
    assertTrue(employees.contains(employee2));
  }
}
