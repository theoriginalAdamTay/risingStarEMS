package risingStarEMS;
/** A class used for creation of Employee objects which are used elsewhere in the program.
 * 
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public class Employee {
  /**
   * Corresponds to the employee's ID.
   */
  private String id;
  /**
   * Corresponds to the employee's full name.
   */
  private String name;
  /**
   * Corresponds to the employee's position.
   */
  private String position;
  /**
   * Corresponds to the department which the employee is part of.
   */
  private String department;
  
  /**
   * The constructor for instances of the Employee object.
   * 
   * @param id The employee's ID as a string.
   * @param name The name of the employee.
   * @param position The employee's position.
   * @param department The employee's department.
   */
  public Employee(String id, String name, String position, String department) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.department = department;
  }
  
  /**
   * Sets the employee's ID.
   * @param id The ID to be given to the employee.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Sets the employee's name.
   * @param name The employee's name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the employee's position.
   * @param position The employee's position.
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * Sets the employee's department.
   * @param department The department to which the employee is being assigned.
   */
  public void setDepartment(String department) {
    this.department = department;
  }

  /**
   * Gets the employee's ID.
   * @return The employee's ID as a string.
   */
  public String getId() {
    return id;
  }
/**
 * Gets the employee's name.
 * @return The employee's full name as a string.
 */
  public String getName() {
    return name;
  }
/**
 * Gets the employee's position.
 * @return The employee's position as a string.
 */
  public String getPosition() {
    return position;
  }
/**
 * Gets the employee's department.
 * @return The employee's department as a string.
 */
  public String getDepartment() {
    return department;
  }
  
  @Override
  public String toString() {
    return "Employee {" + "id =" + id + ", name ='" + name + '\'' + ", position ='" + position + '\'' + ", department ='" + department + '\'' + '}';
  }
}
