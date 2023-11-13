import java.text.ParseException;
import java.util.*;
import java.lang.Math;
import java.text.SimpleDateFormat;

public class Menu {
    private int option;
    private ArrayList<Reservation> reservations;
    private ArrayList<Guest> guests;
    private ArrayList<Member> members;
    private ArrayList<Room> rooms;
    private ArrayList<Invoice> invoices;
    private ArrayList<Employee> employees;

    public Menu() {
        reservations = new ArrayList<>();
        guests = new ArrayList<>();
        members = new ArrayList<>();
        rooms = new ArrayList<>();
        employees = new ArrayList<>();
        invoices = new ArrayList<>();
        generateGuests();
        generateRooms();
        generateMembers();
        generateReservations();
        generateInvoices();
        generateEmployees();
    }

    public void generateRooms() {
        String[] bedSize = {"Queen", "King"};
        for(int i = 101; i < 151; i++) {
            Room newRoom = new Room(i, bedSize[generateRandomNumber(1, 2) - 1], generateRandomNumber(1,2));
            rooms.add(newRoom);
        }
        for(int i = 201; i < 251; i++) {
            Room newRoom = new Room(i, bedSize[generateRandomNumber(1, 2) - 1], generateRandomNumber(1,2));
            rooms.add(newRoom);
        }
        for(int i = 301; i < 351; i++) {
            Room newRoom = new Room(i, bedSize[generateRandomNumber(1, 2) -  1], generateRandomNumber(1,2));
            rooms.add(newRoom);
        }
    }
    public String generateFirstName() {
        String[] firstNames = {"Olivia", "Noah", "Emma", "Liam", "Amelia", "Oliver", "Sophia", "Elijah",
                "Charlotte", "Mateo", "Ava", "Lucas", "Isabella", "Levi", "Mia", "Leo", "Luna", "Ezra",
                "Evelyn", "Luca", "Gianna",	"Asher", "Lily", "James", "Aria", "Ethan", "Aurora", "Sebastian",
                "Ellie", "Henry", "Harper",	"Muhammad", "Mila", "Hudson", "Sofia", "Maverick", "Camila", "Benjamin",
                "Layla", "Theo", "Nova", "Kai", "Eliana", "Jackson", "Ella", "Michael", "Violet", "Daniel", "Hazel", "Aiden"};

        return firstNames[generateRandomNumber(1,50) - 1];

    }
    public String generateLastName() {
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
                "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzales", "Wilson", "Anderson", "Thomas", "Taylor",
                "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez",
                "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen",
                "Hill", "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell",
                "Carter", "Roberts"};
        return lastNames[generateRandomNumber(1,50) - 1];
    }

    public void generateGuests() {
        for(int i = 0; i < 16; i++)  {
            Guest newGuest = new Guest(generateFirstName(), generateLastName(), generateRandomNumber(18, 100));
            guests.add(newGuest);
        }
    }
   public void generateMembers() {
       String[] tiers = {"Basic", "Standard", "Premium"};
        for(int i = 0; i < 16; i++)  {
            Member newMembers = new Member(generateFirstName(), generateLastName(), generateRandomNumber(18, 100));
            members.add(newMembers);
            newMembers.setMemberTier(tiers[generateRandomNumber(1,3) - 1]);
        }
    }

    public void generateReservations() {
        for (Guest guest : guests) {
            Reservation newReservation = new Reservation();
            newReservation.setCustomer(guest);
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            cal.setTime(today);
            cal.add(Calendar.DATE, -generateRandomNumber(1, 14));
            newReservation.setCheckInDate(cal.getTime());
            newReservation.setNights(generateRandomNumber(1, 14));
            newReservation.setNumPeople(generateRandomNumber(1, 4));
            for (Room room : rooms) {
                if (room.getMaxGuests() >= newReservation.getNumPeople() && Objects.equals(room.getRoomStatus(), "Available")) {
                    newReservation.setBoard(room);
                    room.setRoomStatus("Reserved");
                    break;
                }
            }
            reservations.add(newReservation);
        }
        for (Member member : members) {
            Reservation newReservation = new Reservation();
            newReservation.setCustomer(member);
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            cal.setTime(today);
            cal.add(Calendar.DATE, -generateRandomNumber(1, 14));
            newReservation.setCheckInDate(cal.getTime());
            newReservation.setNights(generateRandomNumber(1, 14));
            newReservation.setNumPeople(generateRandomNumber(1, 4));
            for (Room room : rooms) {
                if (room.getMaxGuests() >= newReservation.getNumPeople() && Objects.equals(room.getRoomStatus(), "Available")) {
                    newReservation.setBoard(room);
                    room.setRoomStatus("Reserved");
                    break;
                }
            }
            reservations.add(newReservation);
        }
    }
    public void generateInvoices() {
        Double [] prices = {50.0, 65.0, 80.0, 95.0};
        for(int i = 0; i < 5; i++) {
            Guest newGuest = new Guest(generateFirstName(), generateLastName(), generateRandomNumber(18, 100));
            guests.add(newGuest);
            Invoice newInvoice = new Invoice(newGuest, prices[generateRandomNumber(1, 4) - 1], generateRandomNumber(1, 21));
            invoices.add(newInvoice);
        }
    }
    public void generateEmployees() {
        for(int i = 0; i < 31; i++) {
            Employee newEmployee = new Employee(generateFirstName(), generateLastName(), generateRandomNumber(18, 100));
            employees.add(newEmployee);
        }

    }
    public int generateRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min + 1)) + min);
    }

    public void initialMenu() throws ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Reservations");
        System.out.println("2. Rooms");
        System.out.println("3. Guests");
        System.out.println("4. Check In");
        System.out.println("5. Check Out");
        System.out.println("6. Employees");
        System.out.println("7. Invoices");
        System.out.println("8. Reports");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        int option = in.nextInt();
        System.out.println("You selected " + option);

        switch (option) {
            case 1:
                option1();
                break;
            case 2:
                option2();
                break;
            case 3:
                option3();
                break;
            case 4:
                option4();
                break;
            case 5:
                option5();
                break;
            case 6:
                option6();
                break;
            case 7:
                option7();
                break;
            case 8:
                option8();
                break;
            case 0:
                System.out.println("Bye");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("~".repeat(18));
        initialMenu();
    }

    public void option1() throws ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Add Reservation");
        System.out.println("2. Search Reservation");
        System.out.println("3. Show All Reservations");
        System.out.println("4. Edit Reservation");
        System.out.println("5. Cancel Reservation");
        System.out.println("9. Main Menu");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        // Get the user input for the option
        int option = in.nextInt();
        System.out.println("You selected " + option);

        // Call the respective option
        switch (option) {
            case 1:
                option1_1();
                break;
            case 2:
                option1_2();
                break;
            case 3:
                option1_3();
                break;
            case 4:
                option1_4();
                break;
            case 5:
                option1_5();
                break;
            case 9:
                initialMenu();
                break;
            case 0:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("~".repeat(18));
        if (option == 0) {
            System.exit(0);
        }
        initialMenu();

    }

    public void option2() throws ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Check Available Rooms");
        System.out.println("2. Check Room Status");
        System.out.println("3. Modify Room");
        System.out.println("4. Show All Rooms");
        System.out.println("9. Main Menu");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        // Get the user input for the option
        int option = in.nextInt();
        System.out.println("You selected " + option);

        // Call the respective option
        switch (option) {
            case 1:
                option2_1();
                break;
            case 2:
                option2_2();
                break;
            case 3:
                option2_3();
                break;
            case 4:
                option2_4();
                break;
            case 9:
                initialMenu();
                break;
            case 0:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("~".repeat(18));
        if (option == 0) {
            System.exit(0);
        }
        initialMenu();
    }

    public void option3() throws ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Add Guest");
        System.out.println("2. Search Guest");
        System.out.println("3. Show All Guests");
        System.out.println("4. Edit Guest Info");
        System.out.println("5. Delete Guest");
        System.out.println("6. Get Membership");
        System.out.println("9. Main Menu");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        // Get the user input for the option
        int option = in.nextInt();
        System.out.println("You selected " + option);

        // Call the respective option
        switch (option) {
            case 1:
                option3_1();
                break;
            case 2:
                option3_2();
                break;
            case 3:
                option3_3();
                break;
            case 4:
                option3_4();
                break;
            case 5:
                option3_5();
                break;
            case 6:
                option3_6();
                break;
            case 9:
                initialMenu();
                break;
            case 0:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("~".repeat(18));
        if (option == 0) {
            System.exit(0);
        }

        initialMenu();
    }
    public void option4() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Guest first name: ");
        String firstName = in.nextLine();
        for (Reservation reservation : reservations) {
            if (Objects.equals(lastName, reservation.getCustomer().getLastName()) && Objects.equals(firstName, reservation.getCustomer().getFirstName())) {
                reservation.setCheckInDate(new Date());
                reservation.getBoard().setRoomStatus("Reserved");
            }
            break;
        }
        initialMenu();
    }

    public void option5() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Room number: ");
        int num = in.nextInt();
        for (Reservation reservation : reservations) {
            if (Objects.equals(num, reservation.getBoard().getRoomNumber())) {
                reservation.setCheckOutDate(new Date());
                reservation.getBoard().setRoomStatus("Available");
                Invoice newInvoice = new Invoice(reservation.getCustomer(), reservation.getBoard().getRoomPrice(), reservation.getNights());
                invoices.add(newInvoice);
            }
            break;
        }
        initialMenu();
    }

    public void option6() throws ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Add Employee");
        System.out.println("2. Print Employee Info");
        System.out.println("3. Show All Employees");
        System.out.println("4. Edit Employee Info");
        System.out.println("5. Delete Employee");
        System.out.println("9. Main Menu");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        // Get the user input for the option
        int option = in.nextInt();
        System.out.println("You selected " + option);

        // Call the respective option
        switch (option) {
            case 1:
                option6_1();
                break;
            case 2:
                option6_2();
                break;
            case 3:
                option6_3();
                break;
            case 4:
                option6_4();
                break;
            case 5:
                option6_5();
                break;
            case 9:
                initialMenu();
                break;
            case 0:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("~".repeat(18));
        if (option == 0) {
            System.exit(0);
        }
        initialMenu();
    }

    public void option7() throws ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Search Invoice");
        System.out.println("2. Show All Invoices");
        System.out.println("9. Main Menu");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        // Get the user input for the option
        int option = in.nextInt();
        System.out.println("You selected " + option);

        // Call the respective option
        switch (option) {
            case 1:
                option7_1();
                break;
            case 2:
                option7_2();
                break;
            case 9:
                initialMenu();
                break;
            case 0:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("~".repeat(18));
        if (option == 0) {
            System.exit(0);
        }
        initialMenu();
    }

    public void option8() throws ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Print Guest Report");
        System.out.println("2. Print Employee Report");
        System.out.println("3. Print Total Revenue");
        System.out.println("9. Main Menu");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        // Get the user input for the option
        int option = in.nextInt();
        System.out.println("You selected " + option);

        // Call the respective option
        switch (option) {
            case 1:
                option3_3();;
                break;
            case 2:
                option6_3();
                break;
            case 3:
                option7_2();
                break;
            case 9:
                initialMenu();
                break;
            case 0:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("~".repeat(18));
        if (option == 0) {
            System.exit(0);
        }
        initialMenu();
    }

    public void option1_1() throws ParseException{
        Scanner in = new Scanner(System.in);
        Reservation newReservation = new Reservation();
        newReservation.setCheckInDate();
        newReservation.setNights();
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Guest first name: ");
        String firstName = in.nextLine();
        for(int i = 0; i < guests.size(); i++) {
            if(Objects.equals(lastName, guests.get(i).getLastName()) && Objects.equals(firstName, guests.get(i).getFirstName())) {
                newReservation.setCustomer(guests.get(i));
                break;
            } else {
                Guest newGuest = new Guest();
                newReservation.setCustomer(newGuest);
                newGuest.setLastName(lastName);
                newGuest.setFirstName(firstName);
                newGuest.setAge();
                newGuest.setPhoneNumber();
                guests.add(newGuest);
                break;
            }
        }
        newReservation.setNumPeople();
        for (Room room : rooms) {
            if (newReservation.getNumPeople() >= room.getMaxGuests() && Objects.equals(room.getRoomStatus(), "Available")) {
                newReservation.setBoard(room);
                room.setRoomStatus("Reserved");
                break;
            }
        }
        reservations.add(newReservation);
        initialMenu();
    }

    public void option1_2() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        if (containsName(guests, lastName)) {
            System.out.print("\t\tGuest\t\tCheckIn Date\t\tCheckOut Date\t\t\tNights\t\tRoom\t\t\tPeople\n\n");
            for(int i = 0; i < reservations.size(); i++) {
                if(Objects.equals(lastName, reservations.get(i).getCustomer().getLastName())) {
                    reservations.get(i).printReservation();
                }

            }
        } else{
            System.out.println("There is no Guest with this last name.");
        }
        initialMenu();
    }
    boolean containsName(List<Guest> list,String name) {
        return list.stream().anyMatch(p -> p.getLastName().equals(name));
    }

    public void option1_3() throws ParseException {
        System.out.print("\t\tGuest\t\tCheckIn Date\t\tCheckOut Date\t\tNights\t\t\tRoom\t\t\tPeople\n\n");
        for (Reservation reservation : reservations) {
            reservation.printReservation();
        }
        initialMenu();
    }
     public void option1_4() throws ParseException {
         Scanner in = new Scanner(System.in);
         System.out.print("Enter the Guest last name: ");
         String lastName = in.nextLine();
         System.out.print("Enter the Guest first name: ");
         String firstName = in.nextLine();
         for (Reservation reservation : reservations) {
             if (Objects.equals(lastName, reservation.getCustomer().getLastName()) && Objects.equals(firstName, reservation.getCustomer().getFirstName())) {
                 System.out.println("What would you like to edit? ");
                 System.out.println("1. Check In Date");
                 System.out.println("2. Check Out Date");
                 System.out.println("3. Number of People");
                 System.out.println("3. Nights Stayed");
                 System.out.println("9. Main Menu");
                 System.out.println("0. Exit");
                 System.out.print("Enter your choice: ");
                 int option = in.nextInt();
                 switch (option) {
                     case 1:
                         reservation.setCheckInDate();
                         break;
                     case 2:
                         reservation.setCheckOutDate();
                         break;
                     case 3:
                         reservation.setNumPeople();
                         break;
                     case 4:
                         reservation.setNights();
                         break;
                     case 9:
                         initialMenu();
                         break;
                     case 0:
                         System.out.println("Bye");
                         break;
                     default:
                         System.out.println("Invalid option");
                         break;
                 }
             }else {
                 System.out.println("Reservation is not found.");
             }
         }
         initialMenu();
     }
    public void option1_5() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Guest first name: ");
        String firstName = in.nextLine();
        for(int i = 0; i < reservations.size(); i++) {
            if(Objects.equals(lastName, reservations.get(i).getCustomer().getLastName()) && Objects.equals(firstName, reservations.get(i).getCustomer().getFirstName())) {
                reservations.remove(i);
                break;
            }
        }
        initialMenu();
    }

    public void option2_1() throws ParseException {
        System.out.print("\tRoom \t\t\t Bed \t\t\t Maximum Guest\t\t\t Price \n\n");
        for (Room room : rooms) {
            if (Objects.equals(room.getRoomStatus(), "Available")) {
                room.printRoom();
            }
        }
        initialMenu();
    }
    public void option2_2() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Room number: ");
        int num = in.nextInt();
        for (Room room : rooms) {
            if (Objects.equals(room.getRoomNumber(), num)) {
                System.out.println("Room " + room.getRoomNumber() + " is " + room.getRoomStatus());
            }
        }
        initialMenu();
    }
    public void option2_3() throws ParseException {
        boolean exists = false;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Room number: ");
        int num = in.nextInt();
        for (Room room : rooms) {
            if (Objects.equals(room.getRoomNumber(), num)) {
                exists = true;
                System.out.println("What would you like to edit? ");
                System.out.println("1. Bed Size");
                System.out.println("2. Bed Number");
                System.out.println("3. Room Price");
                System.out.println("4. Room Status");
                System.out.println("9. Main Menu");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int option= in.nextInt();
                switch (option) {
                    case 1:
                        room.setBedSize();
                        break;
                    case 2:
                        room.setBedNumber();
                        break;
                    case 3:
                        room.changeRoomPrice();
                        break;
                    case 4:
                        if(Objects.equals(room.getRoomStatus(), "Available")) {
                            room.setRoomStatus("Reserved");
                        }
                        break;
                    case 9:
                        initialMenu();
                        break;
                    case 0:
                        System.out.println("Bye");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }

        }
        if(!exists) {
            System.out.println("The Room is not found");
        }
        initialMenu();
    }

    public void option2_4() throws ParseException {
        System.out.print("\t\t Room \t\t Bed Size \t\tBed Number \t\tMaximum Guest \t\tPrice ****\n\n");
        for (Room room : rooms) {
            room.printRoom();
        }
        initialMenu();
    }

    public void option3_1() throws ParseException {
        Guest newGuest = new Guest();
        newGuest.setFirstName();
        newGuest.setLastName();
        newGuest.setAge();
        newGuest.setPhoneNumber();
        guests.add(newGuest);
        initialMenu();
    }
    public void option3_2() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        if (containsName(guests, lastName)) {
            System.out.print("\t\tFirst Name \t\tLast Name \t\tAge\t\tPhone Number \n\n");
            for (Guest guest : guests) {
                if (Objects.equals(lastName, guest.getLastName())) {
                    guest.printGuest();
                }
            }
        } else {
            System.out.print("Guest with this last name does not exist.");
        }
        initialMenu();
    }
    public void option3_3() throws ParseException {
        System.out.print("\t\tFirst Name\t\tLast Name\t\tAge\t\tPhone Number\n\n");
        for (Guest guest : guests) {
            guest.printGuest();
        }
        initialMenu();
    }

    public void option3_4() throws ParseException {
        boolean exists = false;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Guest first name: ");
        String firstName = in.nextLine();
        for (Guest guest : guests) {
            if (Objects.equals(lastName, guest.getLastName()) && Objects.equals(firstName, guest.getFirstName())) {
                exists = true;
                System.out.println("What would you like to edit? ");
                System.out.println("1. First Name");
                System.out.println("2. Last Name");
                System.out.println("3. Age");
                System.out.println("4. Phone number");
                System.out.println("9. Main Menu");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                option = in.nextInt();
                switch (option) {
                    case 1:
                        guest.setFirstName();
                        break;
                    case 2:
                        guest.setFirstName();
                        break;
                    case 3:
                        guest.setAge();
                        break;
                    case 4:
                        guest.setPhoneNumber();
                        break;
                    case 9:
                        initialMenu();
                        break;
                    case 0:
                        System.out.println("Bye");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;

                }
            }

        }
        if(!exists) {
            System.out.println("The Guest is not found");
        }
        initialMenu();
    }

    public void option3_5() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Guest first name: ");
        String firstName = in.nextLine();
        for(int i = 0; i < guests.size(); i++) {
            if(Objects.equals(lastName, guests.get(i).getLastName()) && Objects.equals(firstName, guests.get(i).getFirstName())) {
                guests.remove(i);
            }
        }
        initialMenu();
    }
    public void option3_6() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Guest first name: ");
        String firstName = in.nextLine();
        for (Guest guest : guests) {
            if (Objects.equals(lastName, guest.getLastName()) && Objects.equals(firstName, guest.getFirstName())) {
                Member newMember = new Member(guest.getFirstName(), guest.getLastName(), guest.getAge());
                members.add(newMember);
            }
        }
        initialMenu();
    }

    public void option6_1() throws ParseException {
        Employee newEmployees = new Employee();
        newEmployees.createEmployee();
        initialMenu();
    }
    public void option6_2() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Employee first name: ");
        String firstName = in.nextLine();
        for (Employee employee : employees) {
            System.out.print("\t\tName\t\tAge\t\tHourly Wage\t\tHours Worked\t\tTotal Pay\n\n");
            if (Objects.equals(lastName, employee.getEmployeeLastName()) && Objects.equals(firstName, employee.getEmployeeFirstName())) {
                employee.printEmployeeInfo();
            }
        }
        initialMenu();
    }
    public void option6_3() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("\t\tFirstName\t\tAge\t\tHourly Wage\t\tHours Worked\t\tTotal Pay\n\n");
        for (Employee employee : employees) {
            employee.printEmployeeInfo();

        }
        initialMenu();
    }
    public  void  option6_4() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Employee first name: ");
        String firstName = in.nextLine();
        System.out.println("Enter the Employee new information: ");
        for (Employee employee : employees) {
            if (Objects.equals(lastName, employee.getEmployeeLastName()) && Objects.equals(firstName, employee.getEmployeeFirstName())) {
                System.out.println("What would you like to edit? ");
                System.out.println("1. First Name");
                System.out.println("2. Last Name");
                System.out.println("3. Age");
                System.out.println("4. Hourly Wage");
                System.out.println("5. Hours Worked");
                System.out.println("9. Main Menu");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                option = in.nextInt();
                switch (option) {
                    case 1:
                        employee.setEmployeeFirstName();
                        break;
                    case 2:
                        employee.setEmployeeLastName();
                        break;
                    case 3:
                        employee.setAge();
                        break;
                    case 4:
                        employee.setHourlyWage();
                        break;
                    case 5:
                        employee.setHoursWorked();
                        break;
                    case 9:
                        initialMenu();
                        break;
                    case 0:
                        System.out.println("Bye");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;

                }
            }
        }
        initialMenu();
    }
    public void option6_5() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Employee last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Employee first name: ");
        String firstName = in.nextLine();
        System.out.println("Enter the Employee new information: ");
        for(int i = 0; i < employees.size(); i++) {
            if(Objects.equals(lastName, employees.get(i).getEmployeeLastName()) && Objects.equals(firstName, employees.get(i).getEmployeeFirstName())) {
                employees.remove(i);
            }
        }
        initialMenu();
    }

    public void option7_1() throws ParseException {
        boolean exists = false;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter the Guest first name: ");
        String firstName = in.nextLine();
        for (Invoice invoice : invoices) {
            if (Objects.equals(lastName, invoice.getCostumer().getLastName()) && Objects.equals(lastName, invoice.getCostumer().getFirstName())) {
                System.out.print("\t\tFirstName\t\tLast Name\t\tNights Stayed\t\tDiscount\t\tTotal \n\n");
                invoice.printInvoice();
                exists = true;
            }
        }
        if(!exists) {
            System.out.println("Invoice is not found");
        }
        initialMenu();
    }
    public void option7_2() throws ParseException {
        System.out.print("\t\tFirst Name\t\tLast Name\\tNights Stayed\t\tDiscount\t\tTotal \n\n");
        for (Invoice invoice : invoices) {
                invoice.printInvoice();
        }
        initialMenu();
    }
}