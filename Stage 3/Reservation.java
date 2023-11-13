import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class Reservation {
    private Date checkInDate;
    private Date checkOutDate;
    private Guest customer;
    private Room board;
    private int numPeople;
    private int nights; // Add nights field to store the number of nights stayed


    // Constructors
    public Reservation() {
        checkInDate = new Date();
        checkOutDate = new Date();
        customer = new Guest();
        board = new Room();
        numPeople = 0;
        nights = 0;
    }

    public Reservation(Date checkInDate, int nights, Guest customer, Room board) {
        this.checkInDate = checkInDate;
        this.nights = nights;
        this.customer = customer;
        this.board = board;
        calculateCheckOutDate();
    }



    // Getter and Setter methods
    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate)  {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {

        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {

        this.checkOutDate = checkOutDate;
        calculateNights();
    }
    public void setCheckOutDate() throws ParseException {
        Scanner in = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        System.out.print("Enter check-out Date in MM-dd-yyyy: ");
        String checkOut = in.nextLine();
        checkInDate = formatter.parse(checkOut);
        while (checkOutDate == null || (checkInDate.before(new Date()) ||!(checkOutDate.after(checkInDate)))) {
            if(checkInDate.before(new Date())) {
                System.out.println("Check out Date cannot be in the past. Try again.");
            } else if (!(checkOutDate.after(checkInDate))) {
                System.out.println("Check out Date cannot be before the check in Date. Try again.");
            } else {
                System.out.println("Error!. Try again.");
            }
            System.out.println("Enter check-out Date in MM-dd-yyyy: ");
            System.out.print("Enter check-out Date in MM-dd-yyyy: ");
            checkOut = in.nextLine();
            checkInDate = formatter.parse(checkOut);

        }
        calculateNights();
    }

    public int getNumPeople() {

        return numPeople;
    }

    public void setNumPeople(int numPeople) {

        this.numPeople = numPeople;
    }

    public void setNumPeople() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of people staying (1-4): ");
        numPeople = in.nextInt();
        while (numPeople > 4 || numPeople < 1) {
            System.out.print("Error! Try again ");
            System.out.print("Enter the number of people staying (1-4): ");
            numPeople = in.nextInt();
        }
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

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
        calculateCheckOutDate();
    }
    public void setNights() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of nights to stay (1-21): ");
        nights = in.nextInt();
        while (nights < 1 || nights > 21) {
            System.out.println("Invalid number. Try again.");
            System.out.print("Enter the number of nights to stay (1-21): ");
        }
        calculateCheckOutDate();
    }

    public void calculateNights() {
        nights = (int) (checkOutDate.getTime() - checkInDate.getTime());
    }

    public void calculateCheckOutDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        cal.add(Calendar.DATE, nights);
        checkOutDate = cal.getTime();
    }
    public void setCheckInDate() throws ParseException {
        Scanner in = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        System.out.print("Enter check-in Date in MM-dd-yyyy: ");
        String checkIn = in.nextLine();
        checkInDate = formatter.parse(checkIn);
        while (checkInDate == null || (checkInDate.after(checkOutDate))) {
            if(checkInDate.after(checkOutDate)) {
                System.out.println("Check in Date cannot be after Check out day. Try again.");
            }
            System.out.print("Enter check-in Date in MM-dd-yyyy: ");
            checkIn = in.nextLine();
            checkInDate = formatter.parse(checkIn);
        }
    }

    public void printReservation() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        System.out.print("|" + "\t");
        System.out.printf("%-20s", customer.getFirstName() + " " + customer.getLastName());
        System.out.print("|" + "\t");
        System.out.printf("%-20s", checkInDate);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", checkOutDate);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", nights);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", board.getRoomNumber());
        System.out.print("|" + "\t");
        System.out.printf("%-20s", numPeople);
        System.out.print("|\n");
    }
}
