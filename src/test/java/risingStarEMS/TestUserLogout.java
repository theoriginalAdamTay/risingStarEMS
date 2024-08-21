package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestUserLogout {
  private UserService userService;
  private UserRepository userRepository;
  
  @BeforeEach
  void setup() {
    userRepository = mock(UserRepository.class);
    userService = new UserService(userRepository, null, null);
  }
  
  @Test
  void TestLogout() {
    String username = "jimothy_dough";
    User user = new User(null, username, "encodedPassword");
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
    String result = userService.logout(username);
    assertEquals("Logged out successfully", result);
  }
  
  @Test
  void TestNonExistentUserLogout() {
    String username = "jimian_dougheth";
    when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
    String result = userService.logout(username);
    assertEquals("User not found", result);
  }
}
