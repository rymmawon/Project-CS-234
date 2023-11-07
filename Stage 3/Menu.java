import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Menu {
    private List<Guest> guests = new ArrayList<>();
    private Room roomType = new Room();
    private List<Employee> employees = new ArrayList<>(); // Add employees list
    private List<String> checkedInGuests = new ArrayList<>();
    private String guestName;
    private List<String> guestID = new ArrayList<>();
    private String strID;
    
    public void menu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.print("Welcome to our hotel\n");
            System.out.print("Menu\n1. Employee Check-in\n2. Guest Check-in\n3. Check-out\n4. Exit\n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                System.out.println("Employee Check-in selected.\nPlease enter your first name: ");
                    String employeeFirstName = scanner.nextLine();
                    System.out.print("Please enter your last name: ");
                    String employeeLastName = scanner.nextLine();
                    System.out.print("What is your age, " + employeeFirstName + ": ");
                    int employeeAge = scanner.nextInt();
                    Random rand = new Random();
                    int employeeID = rand.nextInt(999) + 100; // setting a random employee ID with at least 3 digits
                    if (employeeAge >= 17) { // must be at least 17 years to work
                        System.out.println("How many hours do you expect to work this week? ");
                        
                        float hoursWorked = scanner.nextInt();
                        Employee employee1 = new Employee(employeeID, employeeFirstName, employeeLastName, employeeAge);
                        employee1.addHoursWorked(hoursWorked);
                        String F = employee1.getEmployeeFirstName();
                        String l = employee1.getEmployeeLastName();
                        int Age = employee1.getAge();
                        float wage = employee1.getHourlyWage();
                        float hours = employee1.getHoursWorked();
                        float pay = hours*wage;

                        

                        System.out.println("Age " + Age +" Is aceptable\nEmployee " + F + " " + l + " is Checked-in, and your ID is: " + employeeID);
                        System.out.println("\nOkay " + F + "\nExpect pay this week of: $" + pay + " (before tax)");
                     } break;
                case 2:
                boolean found = true;
                    System.out.println("Guest Check-in selected.\nWelcome, What is your name? ");
                    guestName = scanner.nextLine();
                    if (checkedInGuests.contains(guestName)) {
                        System.out.println(guestName + " Already checked in.");//message for alreday checking
                    } else {
                    System.out.println(guestName + " do you have a memebership ID? ");  
                    String idcheck = scanner.nextLine();  
                    if(idcheck.equalsIgnoreCase("yes")){
                    System.out.println("What is your 4 digit ID: ");
                    String id = scanner.nextLine();
                    if(id.equalsIgnoreCase(strID)){
                    System.out.println("ID found, welcome back " + guestName);
                        guests.add(new Guest(guestName, strID));//ID added to the parameters
                        
                        Reservation hotelReservation = new Reservation();
                        Reservation hotel1 = new Reservation();
                        int roomPrice = hotel1.checkIn(guestName, scanner, roomType);
                        int nights = hotel1.getNightsStayed();
                        roomType.addAmenities(roomPrice, guests,nights);
                        checkedInGuests.add(guestName); // Add the guest to the list of checked-in guests
                    
                    }else{
                     found = false;
                    }}if(idcheck.equalsIgnoreCase("no")|| !found){
                    System.out.println("Sorry no ID found\nWould like a membership?\nThe additinal cost will be a one time purchase of $100 ");
                    String IDcheck = scanner.nextLine();
                        if(IDcheck.equalsIgnoreCase("yes")){
                        System.out.println("Please choose a 4 digit membership Id: ");
                    strID = scanner.nextLine();
                     guests.add(new Guest(guestName, strID));
                    
                        Reservation hotelReservation = new Reservation();
                        Reservation hotel1 = new Reservation();
                        int roomPrice = hotel1.checkIn(guestName, scanner, roomType);
                        int newroomPrice = roomPrice + 100;//adding $100 for the first time membership
                        int nights = hotel1.getNightsStayed();
                        roomType.addAmenities(newroomPrice, guests,nights);
                        checkedInGuests.add(guestName); // Add the guest to the list of checked-in guests
                        guestID.add(strID);// Add the new ID to the list
                        }
                        else if(IDcheck.equalsIgnoreCase("no")) {
                            guests.add(new Guest(guestName, null));
                        
                        Reservation hotelReservation = new Reservation();
                        Reservation hotel1 = new Reservation();
                        int roomPrice = hotel1.checkIn(guestName, scanner, roomType);
                        int nights = hotel1.getNightsStayed();
                        roomType.addAmenities(roomPrice, guests,nights);
                        checkedInGuests.add(guestName); // Add the guest to the list of checked-in guests

                      //  }else if(guestName.equalsIgnoreCase("Chris")) { //for now let's say "Chris" is a member
                        //    guests.add(new Guest(guestName, "284"));
                            //System.out.println("Welcome back " + guestName + ", Thanks for coming back to the hotel");
                        } else {
                        System.out.println("please choose yes or no ");
                        }
                    }
                    
                }
                    break;
                case 3:
                    System.out.println("Check-out selected.\n Who are we checking out today:");
                    String guestToCheckOut = scanner.nextLine();
                    if (checkedInGuests.contains(guestToCheckOut)) {
                        System.out.println("Checking out " + guestToCheckOut);
                        checkedInGuests.remove(guestToCheckOut); // Remove the guest from the checked-in list
                    } else {
                        System.out.println("Error: " + guestToCheckOut + " is not checked in.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    exit = true; // Set the exit flag to true to exit the loop
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }

    }
}
        