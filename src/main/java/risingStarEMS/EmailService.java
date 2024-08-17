package risingStarEMS;

public interface EmailService {
  void sendPasswordResetEmail(User user);
  void sendPasswordChangedEmail(User user);
}
