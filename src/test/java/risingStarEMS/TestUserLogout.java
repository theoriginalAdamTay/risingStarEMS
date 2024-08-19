package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TestUserLogout {
  @Mock
  private UserRepository userRepository;
  
  @InjectMocks
  private UserService userService;
  
  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }
  
  @Test
  void TestLogout() {
    User user = new User("jimothy_dough", "password");
    when(userRepository.findByUsername("jimothy_dough")).thenReturn(Optional.of(user));
    String result = userService.logout("jimothy_dough");
    assertEquals("Logged out successfully", result);
  }
  
  @Test
  void TestNonExistentUserLogout() {
    when(userRepository.findByUsername("jimian_dougheth")).thenReturn(Optional.empty());
    String result = userService.logout("jimian_dougheth");
    assertEquals("User not found", result);
  }
}
