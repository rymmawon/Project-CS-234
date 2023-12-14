/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelPackage;
import java.util.*;

/**
 *
 * @author 15747
 */
public class Employee {
    public static ArrayList<Employee> employees;
    private int employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private int employeeAge;
    private double hourlyWage;
    private double hoursWorked;
    private double totalPay;
    
    public Employee(int employeeId, String employeeFirstName, String employeeLastName, int employeeAge) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAge = employeeAge;
        hourlyWage = 15.0;
        hoursWorked = 40;
        calculateTotalPay();
    }
    
    public Employee() {
        if(employees == null) {
            employees = new ArrayList<>();
            generateEmployees();
        }
    }
    public void generateEmployees() {
        for(int i = 0; i < 31; i++) {
            Employee newEmployee = new Employee(i + 1, generateFirstName(), generateLastName(), generateRandomNumber(18, 100));
            employees.add(newEmployee);
        }

    }
    public String generateFirstName() {
        String[] firstNames = {"Olivia", "Noah", "Emma", "Liam", "Amelia", "Oliver", "Sophia", "Elijah",
                "Charlotte", "Mateo", "Ava", "Lucas", "Isabella", "Levi", "Mia", "Leo", "Luna", "Ezra",
                "Evelyn", "Luca", "Gianna",	"Asher", "Lily", "James", "Aria", "Ethan", "Aurora", "Sebastian",
                "Ellie", "Henry", "Harper",	"Muhammad", "Mila", "Hudson", "Sofia", "Maverick", "Camila", "Benjamin",
                "Layla", "Theo", "Nova", "Kai", "Eliana", "Jackson", "Ella", "Michael", "Violet", "Daniel", "Hazel", "Aiden"};

        return firstNames[generateRandomNumber(1,50) - 1];

    }
    public String generateLastName() {
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
                "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzales", "Wilson", "Anderson", "Thomas", "Taylor",
                "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez",
                "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen",
                "Hill", "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell",
                "Carter", "Roberts"};
        return lastNames[generateRandomNumber(1,50) - 1];
    }
    
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
        this.calculateTotalPay(); 
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
        calculateTotalPay(); 
    }

    public double getTotalPay() {
        return totalPay;
    }
    private void calculateTotalPay() {
        totalPay = hourlyWage * hoursWorked;
    }
    public int generateRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min + 1)) + min);
    }
  
}
