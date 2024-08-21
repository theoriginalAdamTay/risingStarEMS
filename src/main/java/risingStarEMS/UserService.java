package risingStarEMS;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
  private final UserRepository userRepository;
  private final EmailService emailService;
  private final PasswordEncoder passwordEncoder;
  
  public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.emailService = emailService;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }
  
  public String register(String username, String rawPassword) {
    if (userRepository.findByUsername(username).isPresent()) {
      return "Username already taken, choose another one";
    }
    String encodedPassword = passwordEncoder.encode(rawPassword);
    User user = new User(generateUserId(), username, encodedPassword);
    userRepository.save(user);
    return "New user registered successfully";
  }
  
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
  
  public String logout(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
      return "Logged out successfully";
    }
    return "User not found";
  }
  
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
