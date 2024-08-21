package risingStarEMS;

import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Driver {
  public static void Main(String[] args) {
    UserRepository userRepository = new InMemoryUserRepository();
    EmailService emailService = new SimpleEmailService();
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserService userService = new UserService(userRepository, emailService, passwordEncoder);
    runApplication(userService);
  }
  
  private static void runApplication(UserService userService) {
    Scanner scanner = new Scanner(System.in);
    while(true) {
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
      switch(choice) {
        case 1:
          registerUser(scanner, userService);
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
  
  private static boolean confirmBeforeQuit(Scanner scanner) {
    System.out.print("Are you sure you want to quit? (yes/no): ");
    String input = scanner.nextLine().trim().toLowerCase();
    return "yes".equals(input) || "y".equals(input);
  }
  
  private static void registerEmployee(Scanner scanner, UserService userService) {
    System.out.print("Enter desired username for new employee: ");
    String username = scanner.nextLine();
    System.out.print("Enter desired password for new employee: ");
    String password = scanner.nextLine();
    String result = userService.registerUser(username, password);
    System.out.println(result);
  }
  
  private static void loginUser(Scanner scanner, UserService userService) {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    String result = userService.login(username, password);
    System.out.println(result);
  }
  
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
  
  private static void logoutUser(Scanner scanner, UserService userService) {
    System.out.print("Enter username of user to log out: ");
    String username = scanner.nextLine();
    String result = userService.logout(username);
    System.out.println(result);
  }
  
  private static void forgotPassword(Scanner scanner, UserService userService) {
    System.out.print("Enter username of account with forgotten password: ");
    String username = scanner.nextLine();
    String result = userService.forgotPassword(username);
    System.out.println(result);
  }
}
