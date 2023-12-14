/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelPackage;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author 15747
 */
public class Reservation {
    public static ArrayList<Reservation> reservations;
    private int reservNumber;
    private Date checkInDate;
    private Date checkOutDate;
    private Guest customer;
    private Room board;
    private int numPeople;
    private int nights;
    
    public Reservation() {
        if(reservations == null) {
            reservations = new ArrayList<>();
            generateReservations();
        }
    }
    public Reservation(Guest customer, Date checkInDate, int numPeople, int nights) {
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.numPeople = numPeople;
        this.nights = nights;
        calculateCheckOutDate();
        
    }
    
    public static void generateReservations() {
        for (int i = 0; i < Guest.guests.size(); i++) {
            Reservation newReservation = new Reservation();
            newReservation.setCustomer(Guest.guests.get(i));
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            cal.setTime(today);
            cal.add(Calendar.DATE, generateRandomNumber(1, 14));
            newReservation.setCheckInDate(cal.getTime());
            newReservation.setNights(generateRandomNumber(1, 14));
            newReservation.setNumPeople(generateRandomNumber(1, 4));
            for (int j = 0; j < Room.rooms.size(); j++) {
                if (Room.rooms.get(j).getMaxGuests() >= newReservation.getNumPeople() && Objects.equals(Room.rooms.get(j).getRoomStatus(), "Available")) {
                    newReservation.setBoard(Room.rooms.get(j));
                    Room.rooms.get(j).setRoomStatus("Reserved");
                    break;
                }
            }
            reservations.add(newReservation);
            newReservation.setReservNumber(i + 1);
            newReservation.calculateCheckOutDate();
        }
    }
     public void calculateCheckOutDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        cal.add(Calendar.DATE, nights);
        checkOutDate = cal.getTime();
    }
    public int getReservNumber() {
        return reservNumber;
    }

    public void setReservNumber(int reservNumber) {
        this.reservNumber = reservNumber;
    }
    
    public Date getCheckInDate() {
        return checkInDate;
    }
    public String getStrCheckInDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        return formatter.format(checkInDate);
    }
    
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public String getStrCheckOutDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        return formatter.format(checkOutDate);
    }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Guest getCustomer() {
        return customer;
    }

    public void setCustomer(Guest customer) {
        this.customer = customer;
    }

    public Room getBoard() {
        return board;
    }

    public void setBoard(Room board) {
        this.board = board;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public static int generateRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min + 1)) + min);
    }
}
