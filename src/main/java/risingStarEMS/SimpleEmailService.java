package risingStarEMS;

public class SimpleEmailService implements EmailService {
  @Override
  public void sendPasswordResetEmail(String recipient, String newPassword) {
    String subject = "Password reset";
    String body = String.format("Your new password is: " + newPassword);
    System.out.println("Sending email to " + recipient);
    System.out.println("Subject: " + subject);
    System.out.println("Body: " + body);
  }
  
  @Override
  public void sendPasswordChangedEmail(User user) {
    String message = String.format("Dear %s, your password has been successfully changed. If you did not make this change, please contact support immediately.", user.getUsername());
    System.out.println(message);
  }
}
