package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TestUserLogin {
  @Mock
  private UserRepository userRepository;
  
  @InjectMocks
  private UserService userService;
  
  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void TestLogin() {
    String username = "jimothy_dough";
    String password = "password";
    when(userRepository.authenticate(username, password)).thenReturn(true);
    String result = userService.login(username, password);
    assertEquals("Logged in successfully", result);
  }
  
  @Test
  void TestInvalidCredentialsLogin() {
    String username = "jimian_dougheth";
    String password = "password";
    when(userRepository.authenticate(username, password)).thenReturn(false);
    String result = userService.login(username, password);
    assertEquals("Invalid credentials", result);
  }
}
