package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class TestUserRegistration {
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
  void TestRegistration() {
    String username = "jimothy_dough";
    String password = "password";
    when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
    doNothing().when(userRepository).save(any(User.class));
    String result = userService.register(username, password);
    assertEquals("New user registered successfully", result);
    verify(userRepository).save(argThat(user -> user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword())));
  }
  
  @Test
  void TestExistingUsernameRegistration() {
    String username = "jimian_dougheth";
    String password = "password";
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User(null, username, "encodedPassword")));
    String result = userService.register(username, password);
    assertEquals("Username already taken, choose another one", result);
    verify(userRepository, never()).save(any(User.class));
  }
}
