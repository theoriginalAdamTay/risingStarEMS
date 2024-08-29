package risingStarEMS;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/** A class used for implementation of the user service providing functionality as part of the login system.
 * 
 * @author Adam Tay
 * @version 1.0 unstable
 */
public class UserService {
  /**
   * An instance of the UserRepository interface allowing for implementation of login-related methods.
   */
  private final UserRepository userRepository;
  /**
   * An instance of the EmailService interface allowing for email sending implementation for password change/reset.
   */
  private final EmailService emailService;
  /**
   * An instance of a Spring Security password encoder object allowing for encryption of user passwords.
   */
  private final PasswordEncoder passwordEncoder;
  
  /**
   * The constructor for the user service class.
   * @param userRepository An instance of the UserRepository interface.
   * @param emailService An instance of the EmailService interface.
   * @param passwordEncoder An instance of a Spring Security password encoder object.
   */
  public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.emailService = emailService;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }
  
  /**
   * A function for carrying out registration of a new user.
   * @param username The desired username for the new user.
   * @param rawPassword The desired password in raw, unencoded format.
   * @return String notifying the user of successful registration or prompting choice of another username if the chosen one is already taken.
   */
  public String register(String username, String rawPassword) {
    if (userRepository.findByUsername(username).isPresent()) {
      return "Username already taken, choose another one";
    }
    String encodedPassword = passwordEncoder.encode(rawPassword);
    User user = new User(generateUserId(), username, encodedPassword);
    userRepository.save(user);
    return "New user registered successfully";
  }
  
  /**
   * A function allowing an existing user to log into the system.
   * @param username The username of the user logging into the system.
   * @param rawPassword The user's password in raw, unencoded format.
   * @return String notifying the user of successful login, incorrect credentials or a non-existent account.
   */
  public String login(String username, String rawPassword) {
    Optional<User> userOpt = userRepository.findByUsername(username);
    if (userOpt.isPresent()) {
      User user = userOpt.get();
      if (passwordEncoder.matches(rawPassword, user.getPassword())) {
        return "Logged in successfully";
      } else {
        return "Incorrect password";
      }
    } else {
      return "User not found";
    }
  }
  
  /**
   * A function allowing a logged in user to log out of the system.
   * @param username The username of the logged in user.
   * @return String notifying the user of successful logout or a non-existent account.
   */
  public String logout(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
      return "Logged out successfully";
    }
    return "User not found";
  }
  
  /**
   * A function allowing a user to reset a forgotten password.
   * @param username The username of the user with the forgotten password.
   * @return String notifying the user of a successful password reset or a non-existent account.
   */
  public String forgotPassword(String username) {
    Optional<User> userOpt = userRepository.findByUsername(username);
    if (userOpt.isEmpty()) {
      return "User not found";
    }
    User user = userOpt.get();
    String newPassword = generateRandomPassword();
    String encodedPassword = passwordEncoder.encode(newPassword);
    user.setPassword(encodedPassword);
    userRepository.save(user);
    emailService.sendPasswordResetEmail(username, newPassword);
    return "Password reset successfully, check your email for the new password";
  }
  
  /**
   * A function allowing a user to change their password.
   * @param username The username of the user changing their password.
   * @param currentPassword The user's current password.
   * @param newPassword The desired new password for the user.
   * @return String notifying the user of a successful password change, a password not meeting the criteria or a non-existent user.
   */
  public String changePassword(String username, String currentPassword, String newPassword) {
    Optional<User> userOpt = userRepository.findByUsername(username);
    if (userOpt.isEmpty()) {
      return "User not found";
    }
    User user = userOpt.get();
    if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
      return "Current password is incorrect";
    }
    if (passwordEncoder.matches(newPassword, user.getPassword())) {
      return "New password cannot be the same as current password";
    }
    if (!isValidPassword(newPassword)) {
      return "New password must be at least 8 characters long and contain at least one special character";
    }
    String encodedNewPassword = passwordEncoder.encode(newPassword);
    user.setPassword(encodedNewPassword);
    userRepository.save(user);
    return "Password changed successfully";
  }
  
  /**
   * A function checking the validity of a password set during the user registration process.
   * @param password The password set by the user during the registration process.
   * @return True if the password given has at least eight characters and contains a special character, otherwise false.
   */
  private boolean isValidPassword(String password) {
    if (password.length() < 8) {
      return false;
    }
    String specialCharacters = "!@#$%^&*()_+[]{}|;:',.<>?/";
    boolean containsSpecialCharacter = false;
    for (char c : password.toCharArray()) {
      if(specialCharacters.indexOf(c) >= 0) {
        containsSpecialCharacter = true;
        break;
      }
    }
    return containsSpecialCharacter;
  }
  
  private String generateUserId() {
    return String.valueOf(System.currentTimeMillis());
  }
  
  private String generateRandomPassword() {
    return "password2!";
  }
}
