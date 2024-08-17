package risingStarEMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    MockitoAnnotations.openMocks(this);
  }
  
  @Test
  void TestSuccessfulPasswordChange() {
    User user = new User("jimothy_dough", "encodedOldPassword");
    when(userRepository.findByUsername("jimothy_dough")).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("currentPassword", "encodedOldPassword")).thenReturn(true);
    when(passwordEncoder.encode("newPassword$")).thenReturn("encodedNewPassword");
    String result = userService.changePassword("jimothy_dough", "currentPassword", "newPassword$");
    assertEquals("Changed password successfully", result);
    verify(userRepository, times(1)).save(user);
    verify(emailService, times(1)).sendPasswordChangedEmail(user);
  }
}
