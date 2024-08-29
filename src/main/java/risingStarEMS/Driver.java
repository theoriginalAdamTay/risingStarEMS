package risingStarEMS;

import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/** A class from which all functions are run including the main method.
 * 
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public class Driver {
  /**
   * The program's main method.
   * @param args The supplied command line arguments as an array of type String.
   */
  public static void main(String[] args) {
    UserRepository userRepository = new InMemoryUserRepository();
    EmailService emailService = new SimpleEmailService();
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserService userService = new UserService(userRepository, emailService, passwordEncoder);
    runApplication(userService);
  }
  
  /**
   * A method used as part of the main method to facilitate running of the program.
   * @param userService The program's user service which provides an in-memory user repository, simple email service and password encoder.
   */
  private static void runApplication(UserService userService) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("\nWelcome to Rising Star Employee Management System");
      System.out.println("1. Register a new employee");
      System.out.println("2. Login as an existing user");
      System.out.println("3. Change a password");
      System.out.println("4. Logout an existing user");
      System.out.println("5. Reset a forgotten password");
      System.out.println("6. Exit the program");
      System.out.println("Please select an option: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          registerEmployee(scanner, userService);
          break;
        case 2:
          loginUser(scanner, userService);
          break;
        case 3:
          changePassword(scanner, userService);
          break;
        case 4:
          logoutUser(scanner, userService);
          break;
        case 5:
          forgotPassword(scanner, userService);
          break;
        case 6:
          if (confirmBeforeQuit(scanner)) { 
            System.out.println("Exiting Rising Star EMS. Goodbye!");
            scanner.close();
            return;
          } else {
            System.out.println("Continuing the application...");
          }
          break;
        default:
          System.out.println("Invalid input. Please try again");
      }
    }
  }
  
  /**
   * A function which prompts the user to confirm before quitting the program.
   * @param scanner An instance of a Scanner object allowing for user input.
   * @return True if the user input is "yes" or "y", otherwise false.
   */
  private static boolean confirmBeforeQuit(Scanner scanner) { // Function for prompting confirm before quit
    System.out.print("Are you sure you want to quit? (yes/no): ");
    String input = scanner.nextLine().trim().toLowerCase();
    return "yes".equals(input) || "y".equals(input); // Return true if input is "yes" or "y"
  }
  
  /**
   * A function for registering a new user.
   * @param scanner An instance of a Scanner object allowing for user input.
   * @param userService An instance of a UserService object facilitating user registration functionality.
   */
  private static void registerEmployee(Scanner scanner, UserService userService) {
    System.out.print("Enter desired username for new employee: ");
    String username = scanner.nextLine();
    System.out.print("Enter desired password for new employee: ");
    String password = scanner.nextLine();
    String result = userService.register(username, password);
    System.out.println(result);
  }
  
  /**
   * A function for logging in as an existing user.
   * @param scanner An instance of a Scanner object allowing for user input.
   * @param userService An instance of a UserService object facilitating user login functionality.
   */
  private static void loginUser(Scanner scanner, UserService userService) {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    String result = userService.login(username, password);
    System.out.println(result);
  }
  
  /**
   * A function for changing a user's password.
   * @param scanner An instance of a Scanner object allowing for user input.
   * @param userService An instance of a UserService object facilitating password change functionality.
   */
  private static void changePassword(Scanner scanner, UserService userService) {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter current password: ");
    String currentPassword = scanner.nextLine();
    System.out.print("Enter new password: ");
    String newPassword = scanner.nextLine();
    String result = userService.changePassword(username, currentPassword, newPassword);
    System.out.println(result);
  }
  
  /**
   * A function for logging out a user.
   * @param scanner An instance of a Scanner object allowing for user input.
   * @param userService An instance of a UserService object facilitating user logout functionality.
   */
  private static void logoutUser(Scanner scanner, UserService userService) {
    System.out.print("Enter username of user to log out: ");
    String username = scanner.nextLine();
    String result = userService.logout(username);
    System.out.println(result);
  }
  
  /**
   * A function for resetting a forgotten password.
   * @param scanner An instance of a Scanner object allowing for user input.
   * @param userService An instance of a UserService object facilitating password reset functionality. 
   */
  private static void forgotPassword(Scanner scanner, UserService userService) {
    System.out.print("Enter username of account with forgotten password: ");
    String username = scanner.nextLine();
    String result = userService.forgotPassword(username);
    System.out.println(result);
  }
}
