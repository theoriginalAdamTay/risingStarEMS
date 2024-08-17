package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TestUserRegistration {
  @Mock
  private UserRepository userRepository;
  
  @InjectMocks
  private UserService userService;
  
  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }
  
  @Test
  void TestRegistration() {
    User user = new User("jimothy_dough", "password");
    when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
    String result = userService.register(user);
    assertEquals("Registration successful", result);
    verify(userRepository).save(user);
  }
  
  @Test
  void TestExistingUsernameRegistration() {
    User user = new User("jimian_dougheth", "password");
    when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
    String result = userService.register(user);
    assertEquals("Username already exists", result);
    verify(userRepository, never()).save(user);
  }
}
