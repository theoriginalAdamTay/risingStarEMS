package risingStarEMS;
/** A class used for creation of User objects which are used elsewhere in the program.
 *
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public class User {
  /**
   * Corresponds to the user's ID.
   */
  private String id;
  /**
   * Corresponds to a registered user's username.
   */
  private String username;
  /**
   * Corresponds to a registered user's password.
   */
  private String password;
  
  /**
   * The constructor for instances of the User object.
   * @param id The user's ID as a string.
   * @param username The username for a registered user.
   * @param password The password for a registered user.
   */
  public User(String id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }
  
  /**
   * Sets a user's ID.
   * @param id The ID to be given to the user.
   */
  public void setId(String id) {
    this.id = id;
  }
  
  /**
   * Sets the username for a registered user.
   * @param username The username of the registered user.
   */
  public void setUsername(String username) {
    this.username = username;
  }
  
  /**
   * Sets the password for a registered user.
   * @param password The registered user's password.
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getId() {
    return id;
  }
  
  public String getUsername() {
    return username;
  }
  
  public String getPassword() {
    return password;
  }
}
