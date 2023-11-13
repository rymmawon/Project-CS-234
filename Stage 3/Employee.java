import java.util.Scanner;

public class Employee {
    // Attributes
    private String employeeFirstName;
    private String employeeLastName;
    private int employeeAge;
    private float hourlyWage;
    private float hoursWorked;
    private float totalPay;

    // Constructors
    public Employee() {
        employeeFirstName = "";
        employeeLastName = "";
        employeeAge = 0;
        hourlyWage = 18.0f; //Starting pay for this hotel
        hoursWorked = 8.0f;//Average work load
        totalPay = getTotalPay();
    }
    public Employee(String employeeFirstName, String employeeLastName, int employeeAge) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAge = employeeAge;
        this.hourlyWage = 18.0f; //Starting pay for this hotel
        this.hoursWorked = 8.0f;//Average work load
        totalPay = getTotalPay();
    }

    // Setter and Getter methods for employeeFirstName
    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    // Setter and Getter methods for employeeLastName
    public void setEmployeeLastName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee last name: ");
        employeeLastName = in.nextLine();
    }
    public void setEmployeeFirstName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee first Name: ");
        employeeFirstName = in.nextLine();
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    // Setter and Getter methods for employeeAge
    public void setAge() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee age: ");
        employeeAge = in.nextInt();
    }

    public int getAge() {
        return employeeAge;
    }

    // Setter and Getter methods for hourlyWage
    public void setHourlyWage() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee hourly wage: ");
        hourlyWage = in.nextFloat();
    }

    public float getHourlyWage() {
        return hourlyWage;
    }

    // Method to add hours worked
    public void setHoursWorked() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee hours worked: ");
        hourlyWage = in.nextFloat();
    }

    public float getHoursWorked() {
        return hoursWorked;
    }

    public void createEmployee() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee first Name: ");
        employeeFirstName = in.nextLine();
        System.out.print("Enter the Employee last name: ");
        employeeLastName = in.nextLine();
        System.out.print("Enter the Employee age: ");
        employeeAge = in.nextInt();
        System.out.print("Enter the Employee hourly wage: ");
        hourlyWage = in.nextFloat();
        System.out.print("Enter the Employee hours worked: ");
        hourlyWage = in.nextFloat();
    }

    public void printEmployeeInfo() {
        System.out.print("|" + "\t");
        System.out.printf("%-20s", employeeFirstName + employeeLastName);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", employeeAge);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", hourlyWage);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", hoursWorked);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", totalPay);
        System.out.print("|\n");

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
