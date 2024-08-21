package risingStarEMS;

public interface EmailService {
  void sendPasswordResetEmail(String recipient, String newPassword);
  void sendPasswordChangedEmail(User user);
}
