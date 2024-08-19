package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEmployeeRepository {
  @Mock
  private EmployeeRepository employeeRepository;
  
  @InjectMocks
  private EmployeeService employeeService;
  
  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

}
