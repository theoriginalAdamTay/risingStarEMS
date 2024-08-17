package risingStarEMS;

public class SimpleEmailService implements EmailService {
  @Override
  public void sendPasswordResetEmail(User user) {
    String message = String.format("Password reset link sent to user %s. Reset link: https://example.com/reset?user=%s", user.getUsername(), user.getUsername());
    System.out.println(message);
  }
  
  @Override
  public void sendPasswordChangedEmail(User user) {
    String message = String.format("Dear %s, your password has been successfully changed. If you did not make this change, please contact support immediately.", user.getUsername());
    System.out.println(message);
  }
}
