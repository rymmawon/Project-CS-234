import java.text.SimpleDateFormat;
import java.util.*;

class Room {

    private int roomNumber;
    private String bedSize;
    private int bedNumber;
    private int maxGuests;
    private double roomPrice;
    private String roomStatus;

    public Room() {
        roomNumber = 0;
        bedSize = "";
        maxGuests = 0;
        roomPrice = 0;
        roomStatus = "";
    }

    // Parameterized constructor
    public Room(int roomNumber, String bedSize, int bedNumber) {
        this.roomNumber = roomNumber;
        this.bedSize = bedSize;
        this.bedNumber = bedNumber;
        setMaxGuests();
        setRoomPrice(); // Initialize roomPrice
        this.roomStatus = "Available"; // Initialize roomStatus to "Available"
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }

    // Setter for bedSize
    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    public void setBedSize() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the bed size Queen or King: ");
        bedSize = in.nextLine();
        while(!Objects.equals(bedSize, "Queen") && !Objects.equals(bedSize, "King")) {
            System.out.println("Wrong Bed type. Try again. ");
            System.out.print("Enter the bed size Queen or King: ");
            bedSize = in.nextLine();
        }
    }
    // Getter for bedSize
    public String getBedSize(int roomNumber) {
        return this.bedSize;
    }

    public void setBedNumber() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the bed number (1 or 2): ");
        bedNumber = in.nextInt();
        while (bedNumber != 1 && bedNumber != 2) {
            System.out.println("Error! try again");
            System.out.print("Enter the bed number (1 or 2): ");
            bedNumber = in.nextInt();
        }
    }

    // Setter for maxGuests
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

    // Getter for maxGuests
    public int getMaxGuests() {
        return this.maxGuests;
    }

    // Setter for roomPrice
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

    // Getter for roomPrice
    public double getRoomPrice() {
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

    public void modifyRoom() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the bed size: ");
        bedSize = in.nextLine();
        System.out.print("Enter the maximum guests:");
        maxGuests = in.nextInt();
        System.out.print("Enter the price per night ");
        roomPrice = in.nextDouble();
    }

    public void printRoom() {
        System.out.print("|" + "\t");
        System.out.printf("%-20s",roomNumber);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", bedSize);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", bedNumber);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", maxGuests);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", roomPrice);
        System.out.print("|\n");
    }


    public void changeRoomPrice() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the price for the room in $: ");
        roomPrice = in.nextDouble();
    }
}
