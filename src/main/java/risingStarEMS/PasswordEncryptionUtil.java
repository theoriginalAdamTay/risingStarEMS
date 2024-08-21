package risingStarEMS;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncryptionUtil {
  private final PasswordEncoder passwordEncoder;
  
  public PasswordEncryptionUtil() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }
  
  public String encodePassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }
  
  public boolean verifyPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
