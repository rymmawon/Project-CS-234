import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public int getAge() {
        return employeeAge;
    }

    public void setHourlyWage(float hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public float getHourlyWage() {
        return hourlyWage;
    }

    // Method to add hours worked
    public void setHoursWorked(float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public float getHoursWorked() {
        return hoursWorked;
    }

    public String printEmployeeInfo() {
        StringBuilder info = new StringBuilder("|" + "\t");
    
        info.append("\n|" + "\t");
        info.append(String.format("%-20s", employeeFirstName + " " + employeeLastName));
        info.append("|" + "\t");
        info.append(String.format("%-20s", employeeAge));
        info.append("|" + "\t");
        info.append(String.format("%-20s", hourlyWage));
        info.append("|" + "\t");
        info.append(String.format("%-20s", hoursWorked));
        info.append("|" + "\t");
        info.append(String.format("%-20s", totalPay));
        info.append("|\n");
    
        return info.toString();
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