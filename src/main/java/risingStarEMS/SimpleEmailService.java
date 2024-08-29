package risingStarEMS;

/**
 * An implementation of a simple email service allowing for sending emails notifying users of passwords being changed or reset.
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public class SimpleEmailService implements EmailService {
  @Override
  public void sendPasswordResetEmail(String recipient, String newPassword) {
    String subject = "Password reset"; // Subject of password reset email
    String body = String.format("Your new password is: " + newPassword); // Body of email taking newPassword argument
    System.out.println("Sending email to " + recipient); // Takes recipient argument and notifies user of email being sent
    System.out.println("Subject: " + subject); // Output subject of email
    System.out.println("Body: " + body); // Output email body
  }
  
  @Override
  public void sendPasswordChangedEmail(User user) {
    // Message notifying user of successful password change
    String message = String.format("Dear %s, your password has been successfully changed. If you did not make this change, please contact support immediately.", user.getUsername());
    System.out.println(message); // Output successful password change message
  }
}
