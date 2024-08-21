package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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

class TestPasswordRecovery {
  private UserService userService;
  private UserRepository userRepository;
  private EmailService emailService;
  private PasswordEncoder passwordEncoder;
  
  @BeforeEach
  public void setup() {
    userRepository = mock(UserRepository.class);
    emailService = mock(EmailService.class);
    passwordEncoder = new BCryptPasswordEncoder();
    userService = new UserService(userRepository, emailService, passwordEncoder);
  }
  
  @Test
  void TestSuccessfulPasswordRecovery() {
    String username = "jimothy_dough";
    String newPassword = "password2!";
    User user = new User(null, username, passwordEncoder.encode("password1!"));
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
    doNothing().when(emailService).sendPasswordResetEmail(eq(username), anyString());
    String result = userService.forgotPassword(username);
    assertEquals("Password reset successfully, check your email for the new password", result);
    verify(emailService).sendPasswordResetEmail(eq(username), anyString());
    assertTrue(passwordEncoder.matches(newPassword, user.getPassword()));
  }
  
  @Test
  void TestNonExistentUserPasswordRecovery() {
    String username = "jimian_dougheth";
    when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
    String result = userService.forgotPassword(username);
    assertEquals("User not found", result);
    verify(emailService, never()).sendPasswordResetEmail(anyString(), anyString());
  }
}
