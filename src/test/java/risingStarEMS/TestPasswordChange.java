package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class TestPasswordChange {
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private EmailService emailService;
  
  @Mock
  private PasswordEncoder passwordEncoder;
  
  @InjectMocks
  private UserService userService;
  
  @BeforeEach
  public void setup() {
    userRepository = mock(UserRepository.class);
    passwordEncoder = new BCryptPasswordEncoder();
    userService = new UserService(userRepository, null, passwordEncoder);
  }
  
  @Test
  void TestSuccessfulPasswordChange() {
    String username = "jimothy_dough";
    String currentPassword = "password1!";
    String newPassword = "password2!";
    User user = new User(null, username, passwordEncoder.encode(currentPassword));
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
    doNothing().when(userRepository).save(any(User.class));
    String result = userService.changePassword(username, currentPassword, newPassword);
    assertEquals("Password changed successfully", result);
    assertTrue(passwordEncoder.matches(newPassword, user.getPassword()));
  }
}
