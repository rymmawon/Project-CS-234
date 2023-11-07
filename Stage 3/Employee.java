public class Employee {
    // Attributes
    private int employeeID;
    private String employeeFirstName;
    private String employeeLastName;
    private int employeeAge;
    private float hourlyWage;
    private float hoursWorked;
    private float totalPay;

    // Constructor
    public Employee(int employeeID, String employeeFirstName, String employeeLastName, int employeeAge) {
        this.employeeID = employeeID;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAge = employeeAge;
        this.hourlyWage = 18.0f; //Starting pay for a thisd hotel
        this.hoursWorked = 8.0f;//Average work load
        this.totalPay = 0.0f;
    }

    // Setter and Getter methods for employeeFirstName
    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    // Setter and Getter methods for employeeLastName
    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    // Setter and Getter methods for employeeAge
    public void setAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public int getAge() {
        return employeeAge;
    }

    // Setter and Getter methods for hourlyWage
    public void setHourlyWage(float hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public float getHourlyWage() {
        return hourlyWage;
    }

    // Method to add hours worked
    public void addHoursWorked(float hoursWorked) {
        this.hoursWorked += hoursWorked;
    }

    public float getHoursWorked() {
        return hoursWorked;
    }

    // Method to calculate and return total pay
    public float getTotalPay() {
        totalPay = hourlyWage * hoursWorked;
        return totalPay;
    }

    // Method to print the total pay
    public void printTotalPay() {
        System.out.println("Total Pay for " + employeeFirstName + " " + employeeLastName + " is: $" + getTotalPay());
    }
}
