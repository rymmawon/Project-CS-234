import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {

    private int roomNumber;
    private String roomType;
    private String bedSize; 
    private int maxGuests;
    private int roomPrice;
    private String roomStatus;
    private String[] amenities;

    public Room() {
    }

    // Parameterized constructor
    public Room(int roomNumber, String roomType, String bedSize, int maxGuests) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bedSize = bedSize;
        this.maxGuests = maxGuests;
        this.roomPrice = roomPrice; // Initialize roomPrice
        this.roomStatus = "Available"; // Initialize roomStatus to "Available"
        this.amenities = new String[0]; // Initialize amenities as an empty array
    }
    

    // Setter for roomType
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Getter for roomType
    public String getRoomType(int roomNumber) {
        return this.roomType;
    }

    // Setter for bedSize
    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    // Getter for bedSize
    public String getBedSize(int roomNumber) {
        return this.bedSize;
    }

    // Setter for maxGuests
    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    // Getter for maxGuests
    public int getMaxGuests() {
        return this.maxGuests;
    }

    // Setter for roomPrice
    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    // Getter for roomPrice
    public int getRoomPrice() {
        return roomPrice;
    }
    // Setter for roomStatus
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    // Getter for roomStatus
    public String getRoomStatus() {
        return this.roomStatus;
    }
    // Method to add amenities
    public void addAmenities(int roomPrice,List<Guest> guests,int nights) {
        Services hotelServices = new Services(guests);
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.print("Would you like a service?\n");
            String choice = scanner.nextLine();
            Services gettotal = new Services(guests);
    
            if (choice.equalsIgnoreCase("Yes")) {
                hotelServices.displayAvailableServices();
                System.out.print("Enter the service you want: ");
                String userChoice = scanner.nextLine();
        
                if (userChoice.equalsIgnoreCase("Pool") || userChoice.equalsIgnoreCase("Gym")
                        || userChoice.equalsIgnoreCase("Free Wifi") || userChoice.equalsIgnoreCase("Free Parking")) {
                    System.out.println("\nYou chose " + userChoice + ", a default free service");
        
                    for (Guest guest : guests) {
                        gettotal.addAllFees(0.0f, guest, roomPrice, scanner, nights);
                    }
                    break;
                } else if (userChoice.equalsIgnoreCase("Room Service")) {
                    System.out.println("You chose a paid service");
                    gettotal.checkMembership(roomPrice, scanner,nights);
                    break;
                }
            } else if (choice.equalsIgnoreCase("No")) {
                System.out.println("Are you sure?");
                String fschoice = scanner.nextLine();
    
                if (fschoice.equalsIgnoreCase("Yes")) {
                    for (Guest guest : guests) {
                        gettotal.addAllFees(0.0f, guest, roomPrice, scanner,nights);
                    }
                    break; // Exit the loop
                }
            } else {
                System.out.print("No service selected.\n");
                for (Guest guest : guests) {
                    gettotal.addAllFees(0.0f, guest, roomPrice, scanner,nights);
                }
                break; // Exit the loop
            }
        }
    }
        
}
