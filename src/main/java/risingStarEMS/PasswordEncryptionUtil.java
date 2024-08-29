package risingStarEMS;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A class for a password encryption utility which makes use of the Spring Security password encoder.
 * @author Adam Tay
 * @version 1.0 unstable
 */
public class PasswordEncryptionUtil {
  /**
   * An instance of a Spring Security password encoder object.
   */
  private final PasswordEncoder passwordEncoder;
  
  /**
   * A constructor for the password encryption utility instantiating a new BCryptPasswordEncoder object.
   */
  public PasswordEncryptionUtil() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }
  
  /**
   * A function for encoding a raw password.
   * @param rawPassword A raw, unencoded password to encode.
   * @return The encoded version of the raw password.
   */
  public String encodePassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }
  
  /**
   * A function for verifying an encoded password.
   * @param rawPassword A raw, unencoded password to verify.
   * @param encodedPassword An encoded password to verify the raw password against.
   * @return True if the raw and encoded passwords match, otherwise false.
   */
  public boolean verifyPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
