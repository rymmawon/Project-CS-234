/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelPackage;
import static HotelPackage.Room.generateRooms;
import static HotelPackage.Room.rooms;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.util.*;
import java.util.Vector;

/**
 *
 * @author 15747
 */
public class Guest {
    public static ArrayList<Guest> guests;
    private int guestNum;
    private String firstName;
    private String lastName;
    private int age;
    
    public Guest() {
        if(guests == null) {
            guests = new ArrayList<>();
            generateGuests();
        }
    }
    public Guest(int guestNum, String firstName, String lastName, int age) {
        this.guestNum = guestNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    public static void generateGuests() {
        for(int i = 0; i < 16; i++)  {
            Guest newGuest = new Guest(i + 1, generateFirstName(), generateLastName(), generateRandomNumber(18, 100));
            guests.add(newGuest);
        }
    }
    public static String generateFirstName() {
        String[] firstNames = {"Olivia", "Noah", "Emma", "Liam", "Amelia", "Oliver", "Sophia", "Elijah",
                "Charlotte", "Mateo", "Ava", "Lucas", "Isabella", "Levi", "Mia", "Leo", "Luna", "Ezra",
                "Evelyn", "Luca", "Gianna",	"Asher", "Lily", "James", "Aria", "Ethan", "Aurora", "Sebastian",
                "Ellie", "Henry", "Harper",	"Muhammad", "Mila", "Hudson", "Sofia", "Maverick", "Camila", "Benjamin",
                "Layla", "Theo", "Nova", "Kai", "Eliana", "Jackson", "Ella", "Michael", "Violet", "Daniel", "Hazel", "Aiden"};

        return firstNames[generateRandomNumber(1,50) - 1];

    }
    public static String generateLastName() {
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
                "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzales", "Wilson", "Anderson", "Thomas", "Taylor",
                "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez",
                "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen",
                "Hill", "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell",
                "Carter", "Roberts"};
        return lastNames[generateRandomNumber(1,50) - 1];
    }
    
    public static int generateRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min + 1)) + min);
    }
    
    public int getGuestNum() {
        return guestNum;
    }

    public void setGuestNum(int guestNum) {
        this.guestNum = guestNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
