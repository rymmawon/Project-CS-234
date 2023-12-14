/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelPackage;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author 15747
 */
public class Room {
    public static ArrayList<Room> rooms;
    private int roomNumber;
    private String bedSize;
    private int bedNumber;
    private int maxGuests;
    private double roomPrice;
    private String roomStatus;
    
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getBedSize() {
        return bedSize;
    }
    public int getBedNumber() {
        return bedNumber;
    }
    public int getMaxGuests() {
        return maxGuests;
    }
    public double getRoomPrice() {
        return roomPrice;
    }
    public String getRoomStatus() {
        return roomStatus;
    }
    
    public Room() {
        if(rooms == null) {
            rooms = new ArrayList<>();
            generateRooms();
        }
    }
    public Room(int roomNumber, String bedSize, int bedNumber) {
        this.roomNumber = roomNumber;
        this.bedSize = bedSize;
        this.bedNumber = bedNumber;
        setMaxGuests();
        setRoomPrice();
        this.roomStatus = "Available";
    }
    public static void generateRooms() {
        String[] bedSize = {"Queen", "King"};
        for(int i = 101; i < 125; i++) {
            Room newRoom = new Room(i, bedSize[generateRandomNumber(1, 2) - 1], generateRandomNumber(1,2));
            rooms.add(newRoom);
        }
        for(int i = 201; i < 225; i++) {
            Room newRoom = new Room(i, bedSize[generateRandomNumber(1, 2) - 1], generateRandomNumber(1,2));
            rooms.add(newRoom);
        }
        for(int i = 301; i < 325; i++) {
            Room newRoom = new Room(i, bedSize[generateRandomNumber(1, 2) -  1], generateRandomNumber(1,2));
            rooms.add(newRoom);
        }
    }
    public void setRoomPrice() {
        if(Objects.equals(bedSize, "Queen") && bedNumber == 1) {
            roomPrice = 50.00;
        }else if(Objects.equals(bedSize, "Queen") && bedNumber == 2) {
            roomPrice = 65.00;
        } else if (Objects.equals(bedSize, "King") && bedNumber == 1) {
            roomPrice = 80.00;
        } else if (Objects.equals(bedSize, "King") && bedNumber == 2) {
            roomPrice = 95.00;
        } else {
            roomPrice = 0;
        }
    }
   
    public void setMaxGuests() {
        if(Objects.equals(bedSize, "Queen") && bedNumber == 1) {
            maxGuests = 2;
        }else if(Objects.equals(bedSize, "King") && bedNumber == 1) {
            maxGuests = 3;
        } else if (bedNumber == 2) {
            maxGuests = 4;
        } else {
            maxGuests = 0;
        }
    }
    
    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
        setRoomPrice();
    }
    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
        setRoomPrice();
    }
    public void setRoomStatus(String roomStatus){
        this.roomStatus = roomStatus;
    }
    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }
    
    public static int generateRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min + 1)) + min);
    }
}
