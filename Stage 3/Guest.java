import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Guest {
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;

    public Guest() {
        firstName = "";
        lastName = "";
        age = 0;
        phoneNumber = "000-000-0000";
    }

    public Guest(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        phoneNumber = "000-000-0000";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setFirstName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest first Name: ");
        firstName = in.nextLine();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setLastName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        lastName = in.nextLine();
    }
    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setAge() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest age: ");
        age = in.nextInt();
    }
    public int getAge() {
        return age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPhoneNumber() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest phoneNumber: ");
        phoneNumber = in.nextLine();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void printGuest() {
        System.out.print("|" + "\t");
        System.out.printf("%-20s",firstName);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", lastName);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", age);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", phoneNumber);
        System.out.print("|\n");
    }

}