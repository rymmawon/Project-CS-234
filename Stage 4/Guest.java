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
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
   //

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
//
    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }
 //
    public int getAge() {
        return age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
//

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String printGuest() {
        return "|" + "\t" +
                String.format("%-20s", firstName) +
                "|" + "\t" +
                String.format("%-20s", lastName) +
                "|" + "\t" +
                String.format("%-20s", age) +
                "|" + "\n";
    }
}    