package risingStarEMS;

import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Driver {
  public static void Main(String[] args) { // Main method
    UserRepository userRepository = new InMemoryUserRepository(); // Create new in-memory user repository
    EmailService emailService = new SimpleEmailService(); // Create new simple email service for testing password reset/change
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Create new password encoder using Spring Security
    // Create new user service using in-memory user repository, simple email service and password encoder
    UserService userService = new UserService(userRepository, emailService, passwordEncoder);
    runApplication(userService); // Method used to run application functionality
  }
  
  private static void runApplication(UserService userService) {
    Scanner scanner = new Scanner(System.in); // Create new Scanner object allowing for user input
    while(true) { // Always execute until program function chosen or program is quit
      System.out.println("\nWelcome to Rising Star Employee Management System");
      System.out.println("1. Register a new employee");
      System.out.println("2. Login as an existing user");
      System.out.println("3. Change a password");
      System.out.println("4. Logout an existing user");
      System.out.println("5. Reset a forgotten password");
      System.out.println("6. Exit the program");
      System.out.println("Please select an option: ");
      int choice = scanner.nextInt(); // User inputs choice as integer which is stored as variable choice
      scanner.nextLine();
      switch(choice) { // Switch case statement which selects function based on value of choice variable
        case 1:
          registerEmployee(scanner, userService); // Execute function for new user registration
          break;
        case 2:
          loginUser(scanner, userService); // Execute function for user login
          break;
        case 3:
          changePassword(scanner, userService); // Execute function for changing a user's password
          break;
        case 4:
          logoutUser(scanner, userService); // Execute function for user logout
          break;
        case 5:
          forgotPassword(scanner, userService); // Execute function for resetting a forgotten password
          break;
        case 6:
          if (confirmBeforeQuit(scanner)) { // Check if user has confirmed quitting program
            System.out.println("Exiting Rising Star EMS. Goodbye!");
            scanner.close(); // Close Scanner object as no longer required
            return;
          } else {
            System.out.println("Continuing the application...");
          }
          break;
        default: // Default user input
          System.out.println("Invalid input. Please try again");
      }
    }
  }
  
  private static boolean confirmBeforeQuit(Scanner scanner) { // Function for prompting confirmation before quitting program
    System.out.print("Are you sure you want to quit? (yes/no): ");
    String input = scanner.nextLine().trim().toLowerCase();
    return "yes".equals(input) || "y".equals(input); // Return true if input is "yes" or "y", otherwise false
  }
  
  private static void registerEmployee(Scanner scanner, UserService userService) { // Function for new user registration
    System.out.print("Enter desired username for new employee: ");
    String username = scanner.nextLine();
    System.out.print("Enter desired password for new employee: ");
    String password = scanner.nextLine();
    String result = userService.register(username, password);
    System.out.println(result);
  }
  
  private static void loginUser(Scanner scanner, UserService userService) { // Function for logging in as an existing user
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    String result = userService.login(username, password);
    System.out.println(result);
  }
  
  private static void changePassword(Scanner scanner, UserService userService) { // Function for changing a user's password
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter current password: ");
    String currentPassword = scanner.nextLine();
    System.out.print("Enter new password: ");
    String newPassword = scanner.nextLine();
    String result = userService.changePassword(username, currentPassword, newPassword);
    System.out.println(result);
  }
  
  private static void logoutUser(Scanner scanner, UserService userService) { // Function for user logout
    System.out.print("Enter username of user to log out: ");
    String username = scanner.nextLine();
    String result = userService.logout(username);
    System.out.println(result);
  }
  
  private static void forgotPassword(Scanner scanner, UserService userService) { // Function for resetting a forgotten password
    System.out.print("Enter username of account with forgotten password: ");
    String username = scanner.nextLine();
    String result = userService.forgotPassword(username);
    System.out.println(result);
  }
}
