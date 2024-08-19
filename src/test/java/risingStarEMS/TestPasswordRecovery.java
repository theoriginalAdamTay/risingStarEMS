package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TestPasswordRecovery {
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private EmailService emailService;
  
  @InjectMocks
  private UserService userService;
  
  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }
  
  @Test
  void TestSuccessfulPasswordRecovery() {
    User user = new User("jimothy_dough", "password");
    when(userRepository.findByUsername("jimothy_dough")).thenReturn(Optional.of(user));
    String result = userService.forgotPassword("jimothy_dough");
    verify(emailService, times(1)).sendPasswordResetEmail(user);
    assertEquals("Password reset link sent", result);
  }
  
  @Test
  void TestNonExistentUserPasswordRecovery() {
    when(userRepository.findByUsername("jimian_dougheth")).thenReturn(Optional.empty());
    String result = userService.forgotPassword("jimian_dougheth");
    verify(emailService, never()).sendPasswordResetEmail(any(User.class));
    assertEquals("User not found", result);
  }
}
