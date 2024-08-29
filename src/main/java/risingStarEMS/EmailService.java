package risingStarEMS;

/**
 * An interface providing functionality for sending emails notifying a user of changing/resetting a password.
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public interface EmailService {
  /**
   * A function for sending a password reset notification email.
   * @param recipient The recipient of the email being sent.
   * @param newPassword The new password set for the user resetting their password.
   */
  void sendPasswordResetEmail(String recipient, String newPassword);
  /**
   * A function for sending an email notifying a user of a password change.
   * @param user The user whose password is being changed.
   */
  void sendPasswordChangedEmail(User user);
}
