import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;

public class Reservation {
    private int reservationID;
    private String checkInDate;
    private String checkOutDate;
    private Guest customer;
    private Room board;
    private int numPeople;
    private int nights; // Add nights field to store the number of nights stayed



    // Constructors
    public Reservation() {
    }

    public Reservation(String checkInDate, String checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getter and Setter methods
    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public Room getBoard() {
        return board;
    }

    public void setBoard(Room board) {
        this.board = board;
    }

    public Guest getCustomer() {
        return customer;
    }

    public void setCustomer(Guest customer) {
        this.customer = customer;
    }

    // Other methods
    public void printReservation() {
        // Implement this method to print the reservation details
    }

    public void displayReservation() {
        // Implement this method to display the reservation details
    }

    public static long calculateNightsStayed(LocalDate checkIn, LocalDate checkOut) {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    // Fix method signature, add 'void' return type
    public void setNightsStayed(int nights) {
        this.nights = nights;
    }

    public int getNightsStayed() {
        return nights;
    }

    public int checkIn(String guest, Scanner scanner, Room roomType) {
        while (getNightsStayed() < 1) {
            System.out.print("Enter check-in date (yyyy-MM-dd): ");
            String checkInStr = scanner.nextLine();
            LocalDate checkInDate = LocalDate.parse(checkInStr);
    
            System.out.print("Enter check-out date (yyyy-MM-dd): ");
            String checkOutStr = scanner.nextLine();
            LocalDate checkOutDate = LocalDate.parse(checkOutStr);
    
            long nightsStayed = calculateNightsStayed(checkInDate, checkOutDate);
            setNightsStayed((int) nightsStayed);
    
            if (getNightsStayed() < 1) {
                System.out.print("Invalid: Must stay at least one night\n");
            } else {
                System.out.print("How many guests are staying " + guest + ": ");
                int numPeople = scanner.nextInt();
                Room room = new Room();
                scanner.nextLine(); // consume the newline character
                Random rand = new Random();
                int roomNumber = rand.nextInt(1000) + 1;
                String bedsize = "";
                int roomPrice = 0; // Initialize room price
    
                while (roomPrice == 0) { // option for room price must be chosen
                    switch (numPeople) {
                        case 1:
                        case 2:
                            System.out.print("What type of beds would you like:\nTwo Twins or One King: ");
                            bedsize = scanner.nextLine();
                            if (bedsize.equalsIgnoreCase("Two Twins") || bedsize.equalsIgnoreCase("One King")) {
                                roomPrice = 110; // Set the room price based on the choice
                                room.setBedSize(bedsize);
                                room.setMaxGuests(2);
                            } else {
                                System.out.print("Invalid input, please enter Two Twins or One King: ");
                            }
                            break;
                        case 3:
                        case 4:
                            System.out.print("What type of beds would you like:\nFour Twins or Two Kings: ");
                            bedsize = scanner.nextLine();
                            if (bedsize.equalsIgnoreCase("Four Twins") || bedsize.equalsIgnoreCase("Two Kings")) {
                                roomPrice = 120; // Set the room price based on the choice
                                room.setBedSize(bedsize);
                                room.setMaxGuests(4);
                            } else {
                                System.out.print("Invalid input, please enter Four Twins or Two Kings: ");
                            }
                            break;
                        default:
                            System.out.print("Invalid Number of guests, please choose 1 through 4: ");
                            numPeople = scanner.nextInt();
                            scanner.nextLine(); // consume the newline character
                            break;
                    }
                }
    
                System.out.println("Your room is set, and your room number is " + roomNumber + "\nRoom Price: $" + roomPrice + "\nYour room has " + bedsize + " as requested");
    
                return roomPrice; // Return the room price
            }
        } return 0; // Return the room price// Provide a default return value to handle all code paths
    }

        public void checkOut(Guest guest) {
        // Implement this method to handle the check-out process for the specified guest
    }
}
