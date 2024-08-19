package risingStarEMS;

import java.util.Optional;

public class UserService {
  private final UserRepository userRepository;
  private final EmailService emailService;
  private final PasswordEncoder passwordEncoder;
  
  public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.emailService = emailService;
    this.passwordEncoder = passwordEncoder;
  }
  
  public String register(User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      return "Username already exists";
    }
    userRepository.save(user);
    return "Registration successful";
  }
  
  public String login(String username, String password) {
    if (userRepository.authenticate(username, password)) {
      return "Logged in successfully";
    }
    return "Invalid credentials";
  }
  
  public String logout(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
      return "Logged out successfully";
    }
    return "User not found";
  }
  
  public String forgotPassword(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      emailService.sendPasswordResetEmail(user);
      return "Password reset link sent";
    } else {
      return "User not found";
    }
  }
  
  public String changePassword(String username, String currentPassword, String newPassword) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
        return "Current password is incorrect";
      }
      if (passwordEncoder.matches(newPassword, user.getPassword())) {
        return "New password cannot be the same as current password";
      }
      if (!isValidPassword(newPassword)) {
        return "New password does not meet required criteria";
      }
      user.setPassword(passwordEncoder.encode(newPassword));
      userRepository.save(user);
      emailService.sendPasswordChangedEmail(user);
      return "Changed password successfully";
    } else {
      return "User not found";
    }
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
}
