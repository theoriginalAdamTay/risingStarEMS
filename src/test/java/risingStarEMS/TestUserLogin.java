package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class TestUserLogin {
  private UserService userService;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  
  @BeforeEach
  void setup() {
    userRepository = mock(UserRepository.class);
    passwordEncoder = new BCryptPasswordEncoder();
    userService = new UserService(userRepository, null, passwordEncoder);
  }
  
  @Test
  void TestLogin() {
    String username = "jimothy_dough";
    String password = "password";
    String encodedPassword = passwordEncoder.encode(password);
    User user = new User(null, username, encodedPassword);
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
    String result = userService.login(username, password);
    assertEquals("Logged in successfully", result);
  }
  
  @Test
  void TestInvalidCredentialsLogin() {
    String username = "jimian_dougheth";
    String correctPassword = "password";
    String incorrectPassword = "password1";
    String encodedPassword = passwordEncoder.encode(correctPassword);
    User user = new User(null, username, encodedPassword);
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
    String result = userService.login(username, incorrectPassword);
    assertEquals("Incorrect password", result);
  }
}
