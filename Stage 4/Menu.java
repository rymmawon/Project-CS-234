import java.text.ParseException;
import java.util.*;
import java.lang.Math;
import java.text.SimpleDateFormat;
import javax.swing.*; // For GUI components
import java.awt.*; // For layout managers and basic GUI functionalities
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Menu extends JFrame {
    private int option;
    private ArrayList<Reservation> reservations;
    private ArrayList<Guest> guests;
    private ArrayList<Member> members;
    private ArrayList<Room> rooms;
    private ArrayList<Invoice> invoices;
    private ArrayList<Employee> employees;
    private JTextArea textArea;
    private JPanel panel;
    private JFrame frame;

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
        
        frame = new JFrame("Hotel Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create buttons for each menu option
        JButton reservationsButton = new JButton("Reservations");
        JButton roomsButton = new JButton("Rooms");
        JButton guestsButton = new JButton("Guests");
        JButton checkInButton = new JButton("Check In");
        JButton checkOutButton = new JButton("Check Out");
        JButton employeesButton = new JButton("Employees");
        JButton invoicesButton = new JButton("Invoices");
        JButton reportsButton = new JButton("Reports");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the panel
        for (JButton button : Arrays.asList(//loop to size all buttons the same size mor efficentlly
                reservationsButton, roomsButton, guestsButton, checkInButton,
                checkOutButton, employeesButton, invoicesButton, reportsButton, exitButton)) {
            button.setBorder(new EmptyBorder(10, 10, 10, 20));
            button.setPreferredSize(new Dimension(750,250));
            panel.add(button);
        }

        // Add the panel to the frame
        frame.add(panel);
        frame.revalidate(); // Update the layout
        frame.repaint(); 

        
        reservationsButton.addActionListener(e -> {
            try {
                option1();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        roomsButton.addActionListener(e -> option2());

        guestsButton.addActionListener(e -> {
            try {
                option3();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        checkInButton.addActionListener(e -> {
            try {
                option4();
            } catch (ParseException p) {
                p.printStackTrace();
            }
        });

        checkOutButton.addActionListener(e -> {
            try {
                option5();
            } catch (ParseException p) {
                p.printStackTrace();
            }
        });

        employeesButton.addActionListener(e -> {
                option6();
           
        });

        invoicesButton.addActionListener(e -> {
                option7();
          
        });

        reportsButton.addActionListener(e -> {
                option8();
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

    public void option1() throws ParseException {
        // Remove existing buttons
        panel.removeAll();
    
        // Create new buttons for the current context
        JButton mainMenuButton = new JButton("Main Menu");
        JButton exitButton = new JButton("Exit");
    
        // Create an array of button labels(more efficent this way)
        String[] buttonLabels = {"Add Reservation", "Search Reservation", "Show All Reservations"};
    
        // Create an array to store the buttons
        JButton[] buttons = new JButton[buttonLabels.length];
    
        // Set preferred size for each button
        Dimension buttonSize = new Dimension(1000, 333);//size for new buttons
    
        // Create buttons in a loop for the same size
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setPreferredSize(buttonSize);
            panel.add(buttons[i]);
        }
    
        // Add action listeners for each button
        buttons[0].addActionListener(e -> {
            try {
                option1_1();
            } catch (ParseException ex) {
                ex.printStackTrace();
                
            }
        });
    
        buttons[1].addActionListener(e -> {
            try {
                option1_2();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option1_2
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                option1_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option1_3
            }
        });
    
        mainMenuButton.setPreferredSize(buttonSize);
        mainMenuButton.addActionListener(e -> {
            try {
                initialMenu();
            } catch (ParseException p) {
                p.printStackTrace();
                // Handle the ParseException for initialMenu
            }
        });
    
        exitButton.setPreferredSize(buttonSize);
        exitButton.addActionListener(e -> {
            System.out.println("Bye");
            System.exit(0);
        });
    
        // Add new buttons to the panel
        panel.add(mainMenuButton);
        panel.add(exitButton);
    
        // Update the layout and repaint the UI
        frame.revalidate();
        frame.repaint();
    }      
    
    public void option2() {
        panel.removeAll(); // Clear the existing panel

        String[] buttonLabels = {"Check Available Rooms","Check Room Status","Modify Room","Show All Rooms","Main Menu","Exit"};

        JButton[] buttons = new JButton[buttonLabels.length];

        Dimension buttonSize = new Dimension(1000, 333);//size for new buttons
    
        // Create buttons in a loop for the same size
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setPreferredSize(buttonSize);
            panel.add(buttons[i]);
        }
    
        frame.revalidate(); // Update the layout
        frame.repaint();    // Repaint the UI

        buttons[0].addActionListener(e -> {
            try {
                option2_1();
            } catch (ParseException ex) {
                ex.printStackTrace();
                
            }
        });
    
        buttons[1].addActionListener(e -> {
            try {
                option2_2();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option1_2
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                option2_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option1_3
            }
        });

        buttons[3].addActionListener(e -> {
            try {
                option2_4();
            } catch (ParseException ex) {
                ex.printStackTrace();
                
            }
        });
    
        buttons[4].addActionListener(e -> {
            try {
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option1_2
            }
        });
    
        buttons[5].addActionListener(e -> {
            System.exit(0);
        });
    }    

    public void option3() throws ParseException {
        panel.removeAll();
    
        //An array of button labels
        String[] buttonLabels = {"Add Guest", "Search Guest", "Show All Guests", "Edit Guest Info", "Delete Guest", "Get Membership", "Main Menu", "Exit"};
    
        // An array to store the buttons
        JButton[] buttons = new JButton[buttonLabels.length];
    
        // Size for each button
        Dimension buttonSize = new Dimension(750, 333); // size for new buttons
    
        // Create buttons in a loop
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setPreferredSize(buttonSize);
            panel.add(buttons[i]);
        }
    
        // Add action listeners for each button
        buttons[0].addActionListener(e -> {
            try {
                option3_1();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[1].addActionListener(e -> {
            try {
                option3_2();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option3_2
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                option3_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option3_3
            }
        });
    
        buttons[3].addActionListener(e -> {
            try {
                option3_4();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option3_4
            }
        });
    
        buttons[4].addActionListener(e -> {
            try {
                option3_5();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option3_5
            }
        });
    
        buttons[5].addActionListener(e -> {
            try {
                option3_6();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option3_6
            }
        });
    
        buttons[6].addActionListener(e -> {
            try {
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[7].addActionListener(e -> {
            System.out.println("Bye");
            System.exit(0);
        });
    
        // Update the layout and repaint the UI
        frame.revalidate();
        frame.repaint();
    }
    
       
    public void option4() throws ParseException {
        panel.removeAll();

        JTextField lastNameField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        
        panel.add(new JLabel("Enter the Guest last name: "));
        panel.add(lastNameField);
        panel.add(new JLabel("Enter the Guest first name: "));
        panel.add(firstNameField);
        panel.add(submitButton);
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //establish textboxes for there questions 
                String lastName = lastNameField.getText();//receive strings from the user
                String firstName = firstNameField.getText();
        
                for (Reservation reservation : reservations) {
                    if (Objects.equals(lastName, reservation.getCustomer().getLastName()) && Objects.equals(firstName, reservation.getCustomer().getFirstName())) {
                        reservation.setCheckInDate(new Date());
                        reservation.getBoard().setRoomStatus("Reserved");
                        break; 
                    }
                }
        try {
            initialMenu();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
                }
            });
            panel.revalidate();
            panel.repaint();
    }

    public void option5() throws ParseException {
        panel.removeAll();

        JTextField roomnum = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        
        panel.add(new JLabel("Enter the Room Number: "));
        panel.add(roomnum);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numText = roomnum.getText();
                //establish textboxes for ther questions 
                int num = Integer.parseInt(numText);
        
                for (Reservation reservation : reservations) {
                    if (Objects.equals(num, reservation.getBoard().getRoomNumber())) {
                        reservation.setCheckOutDate(new Date());
                        reservation.getBoard().setRoomStatus("Available");
                        Invoice newInvoice = new Invoice(reservation.getCustomer(), reservation.getBoard().getRoomPrice(), reservation.getNights());
                        invoices.add(newInvoice);
                    }
                }
        try {
            initialMenu();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
                }
            });
            panel.revalidate();
            panel.repaint();
    }  

    public void option6() {
        panel.removeAll();
    
        String[] buttonLabels = {"Add Employee", "Print Employee Info", "Show All Employees", "Edit Employee Info", "Delete Employee", "Main Menu", "Exit"};
        JButton[] buttons = new JButton[buttonLabels.length];
    
        // Create buttons in a loop
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setPreferredSize(new Dimension(750, 250));
            panel.add(buttons[i]);
        }
    
        // Add action listeners for each button
        buttons[0].addActionListener(e -> {
            try {
                option6_1();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option6_1
            }
        });
    
        buttons[1].addActionListener(e -> {
            try {
                option6_2();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option6_2
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                option6_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option6_3
            }
        });
    
        buttons[3].addActionListener(e -> {
            try {
                option6_4();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option6_4
            }
        });
    
        buttons[4].addActionListener(e -> {
            try {
                option6_5();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option6_5
            }
        });
    
        buttons[5].addActionListener(e -> {
            try {
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for initialMenu
            }
        });
    
        buttons[6].addActionListener(e -> {
            System.out.println("Bye");
            System.exit(0);
        });
    
        // Update the layout and repaint the UI
        frame.revalidate();
        frame.repaint();
    }    

    public void option7() {
        panel.removeAll();
    
        String[] buttonLabels = {"Search Invoice", "Show All Invoices", "Main Menu", "Exit"};
        JButton[] buttons = new JButton[buttonLabels.length];
    
        // Create buttons in a loop since all buttons will be the same size 
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setPreferredSize(new Dimension(750, 250));
            panel.add(buttons[i]);
        }
    
        // Add action listeners for each button
        buttons[0].addActionListener(e -> {
            try {
                option7_1();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[1].addActionListener(e -> {
            try {
                option7_2();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[3].addActionListener(e -> {
            System.out.println("Bye");
            System.exit(0);
        });
    
        frame.revalidate();
        frame.repaint();
    }
    

    public void option8() {
        panel.removeAll();
    
        String[] buttonLabels = {"Print Guest Report", "Print Employee Report", "Print Total Revenue", "Main Menu", "Exit"};
        JButton[] buttons = new JButton[buttonLabels.length];
    
        // Create buttons in a loop
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setPreferredSize(new Dimension(750, 250));
            panel.add(buttons[i]);
        }
    
        // Add action listeners for each button
        buttons[0].addActionListener(e -> {
            try {
                option3_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option3_3
            }
        });
    
        buttons[1].addActionListener(e -> {
            try {
                option6_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option6_3
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                option7_2();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for option7_2
            }
        });
    
        buttons[3].addActionListener(e -> {
            try {
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
                // Handle the ParseException for initialMenu
            }
        });
    
        buttons[4].addActionListener(e -> {
            System.out.println("Bye");
            System.exit(0);
        });
    
        // Update the layout and repaint the UI
        frame.revalidate();
        frame.repaint();
    }
    

    public void option1_1() throws ParseException {
        panel.removeAll();
    
        JTextField lastNameField = new JTextField(10);
        JTextField firstNameField = new JTextField(10);
        JTextField numPeopleField = new JTextField(3);  // Only allowed to enter one digit
        JTextField phoneNumber = new JTextField(15);
        JTextField Age = new JTextField(3);
        JTextField nightsField = new JTextField(2);
        JButton submitButton = new JButton("Submit");

        // Set preferred size for text fields
        Dimension textFieldSize = new Dimension(150, 25);
        lastNameField.setPreferredSize(textFieldSize);
        firstNameField.setPreferredSize(textFieldSize);
        numPeopleField.setPreferredSize(textFieldSize);
        phoneNumber.setPreferredSize(textFieldSize);
        Age.setPreferredSize(textFieldSize);

        // Use GridLayout for a more organized layout
        panel.setLayout(new GridLayout(0, 2, 20, 20));
        // Add labels and text fields to the input panel
        panel.add(new JLabel("Enter the Guest last name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Enter the Guest first name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Enter the number of people:"));
        panel.add(numPeopleField);
        panel.add(new JLabel("Enter the Guest Phone Number:"));
        panel.add(phoneNumber);
        panel.add(submitButton);

       
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();

                JTextField ageField = new JTextField(3);
                JTextField nightsField = new JTextField(2);
                JButton submitButton2 = new JButton("Submit");

                // Add labels and text fields to the input panel
                panel.add(new JLabel("Enter the Guest Age:"));
                panel.add(Age);
                panel.add(new JLabel("Enter the number of nights to stay (1-21):"));//maximum nights are 21 for this hotel
                panel.add(nightsField);;
                panel.add(submitButton2);
            

                submitButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                int numPeople = Integer.parseInt(numPeopleField.getText());
                String phoneNum = phoneNumber.getText();
                int age = Integer.parseInt(Age.getText());
                int nights = Integer.parseInt(nightsField.getText());
                    
    
                Reservation newReservation = new Reservation();
                newReservation.setCheckInDate();
                newReservation.setNights(nights);
                newReservation.calculateCheckOutDate();
    
                // Use a flag to track whether a matching guest is found
                boolean guestFound = false;
    
                for (Guest guest : guests) {
                    if (Objects.equals(lastName, guest.getLastName()) && Objects.equals(firstName, guest.getFirstName())) {
                        newReservation.setCustomer(guest);
                        guestFound = true;
                        break;
                    }
                }
                if (!guestFound) {
                    Guest newGuest = new Guest(firstName, lastName, age);
                    newGuest.setPhoneNumber(phoneNum);
                    newReservation.setCustomer(newGuest);
                    guests.add(newGuest);
                
                
    
               // newReservation.setNumPeople();
    
                for (Room room : rooms) {
                    if (newReservation.getNumPeople() >= room.getMaxGuests() && Objects.equals(room.getRoomStatus(), "Available")) {
                        newReservation.setBoard(room);
                        room.setRoomStatus("Reserved");
                        break;
                    }
                }
    
                reservations.add(newReservation);
                try {
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
    
                try {
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.revalidate();
        panel.repaint();
    }
});
    
        // Update the layout and repaint the UI
        panel.revalidate();
        panel.repaint();
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
    boolean containsName(java.util.List<Guest> list, String name) {
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
                        // reservation.setNumPeople();
                         break;
                     case 4:
                       //  reservation.setNights();
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
        //
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
        panel.removeAll();
    
        JButton submitButton = new JButton("Okay");
    
        StringBuilder combinedGuests = new StringBuilder();
    
        for (Guest guest : guests) {
            combinedGuests.append(guest.printGuest());
        }
    
        // Convert StringBuilder to a String and print it
        String finalResult = combinedGuests.toString();
       

        JLabel label = new JLabel("<html>" + "All Guest" + finalResult.replaceAll("\n", "<br>") + "</html>");

         //Setting the front size
         label.setFont(new Font("Arial", Font.PLAIN, 19));
        panel.add(label);
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    // Handle the ParseException for initialMenu
                }
            }
        });
    
        panel.add(submitButton);
    
        panel.revalidate();
        panel.repaint();
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
                //

                
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