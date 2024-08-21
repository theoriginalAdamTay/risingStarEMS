package risingStarEMS;

public class Employee {
  private String id;
  private String name;
  private String position;
  private String department;
  
  public Employee() {
  }

  public Employee(String id, String name, String position, String department) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.department = department;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPosition() {
    return position;
  }

  public String getDepartment() {
    return department;
  }

  @Override
  public String toString() {
    return "Employee {" + "id =" + id + ", name ='" + name + '\'' + ", position ='" + position + '\'' + ", department ='" + department + '\'' + '}';
  }
}
