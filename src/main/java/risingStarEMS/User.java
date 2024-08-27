package risingStarEMS;

public class User {
  private String id; // User ID as string
  private String username; // Username of registered user
  private String password; // Password of registered user
  
  public User(String id, String username, String password) { // Constructor for the User object
    this.id = id;
    this.username = username;
    this.password = password;
  }
  
  public void setId(String id) { // Setter for user ID
    this.id = id;
  }
  
  public void setUsername(String username) { // Setter for username
    this.username = username;
  }
  
  public void setPassword(String password) { // Setter for user password
    this.password = password;
  }
  
  public String getId() { // Getter for user ID
    return id;
  }
  
  public String getUsername() { // Getter for username
    return username;
  }
  
  public String getPassword() { // Getter for user password
    return password;
  }
}
