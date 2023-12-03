import java.text.ParseException;
import java.util.*;
import java.lang.Math;
import java.lang.reflect.Member;
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
        
        try {
            initialMenu();
         } catch (ParseException ex) {
                ex.printStackTrace();
            }
        
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
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    
        frame = new JFrame("Hotel Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setVisible(true);
    
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        JButton reservationsButton = new JButton("Reservations");
        JButton roomsButton = new JButton("Rooms");
        JButton guestsButton = new JButton("Guests");
        JButton checkInButton = new JButton("Check In");
        JButton checkOutButton = new JButton("Check Out");
        JButton employeesButton = new JButton("Employees");
        JButton invoicesButton = new JButton("Invoices");
        JButton reportsButton = new JButton("Reports");
        JButton exitButton = new JButton("Exit");
    
        // Add vertical glue before the buttons
        panel.add(Box.createVerticalGlue());
    
        // Add buttons to the panel
        for (JButton button : Arrays.asList(
                reservationsButton, roomsButton, guestsButton, checkInButton,
                checkOutButton, employeesButton, invoicesButton, reportsButton, exitButton)) {
            button.setBorder(new EmptyBorder(10, 100, 10, 800));
            button.setPreferredSize(new Dimension(2150, 1550));
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            panel.add(button);
        }
    
        // Add vertical glue after the buttons
        panel.add(Box.createVerticalGlue());
    
        // Add the panel to the frame
        frame.add(panel);
        frame.revalidate(); // Update the layout
        frame.repaint();
    
        // Add action listeners for each button
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
    
        employeesButton.addActionListener(e -> option6());
    
        invoicesButton.addActionListener(e -> option7());
    
        reportsButton.addActionListener(e -> option8());
    
        exitButton.addActionListener(e -> System.exit(0));
    }
    public void option1() throws ParseException {
        // Remove existing buttons
        panel.removeAll();
    
        // Create an array of button labels(more efficent this way)
        String[] buttonLabels = {"Add Reservation", "Search Reservation", "Show All Reservations","Main Menu","Exit"};
    
        // Create an array to store the buttons
        JButton[] buttons = new JButton[buttonLabels.length];
    
        // Set preferred size for each button
        Dimension buttonSize = new Dimension(1000, 333);//size for new buttons
    
        // Create buttons in a loop for the same size
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
            buttons[i].setPreferredSize(new Dimension(2150, 1550));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
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
    
        buttons[3].addActionListener(e -> {
            try {
                frame.dispose();
                initialMenu();
            } catch (ParseException p) {
                p.printStackTrace();
                // Handle the ParseException for initialMenu
            }
        });
    
        buttons[4].addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
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
            buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
            buttons[i].setPreferredSize(new Dimension(2150, 1550));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
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
                
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                option2_3();
            } catch (ParseException ex) {
                ex.printStackTrace();

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
                frame.dispose();
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
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
    
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
            buttons[i].setPreferredSize(new Dimension(2150, 1550));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
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
            }
        });
    
        buttons[2].addActionListener(e -> {
            try {
                option3_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[3].addActionListener(e -> {
            try {
                option3_4();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[4].addActionListener(e -> {
            try {
                option3_5();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[5].addActionListener(e -> {
            try {
                option3_6();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[6].addActionListener(e -> {
            try {
                frame.dispose();
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
        frame.dispose();

        JFrame newFrame = new JFrame("Check in");
        JPanel newpanel = new JPanel(null);  // Use absolute positioning
        newFrame.setSize(1500, 300);

        JTextField lastNameField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton okayButton = new JButton("OK");
        JButton okayButton2 = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JTextArea availableTextArea = new JTextArea();
        JTextArea notAvailableTextArea = new JTextArea();

        JLabel lastNameLabel = new JLabel("Enter the Guest last name: ");
        lastNameLabel.setBounds(10, 10, 150, 25);
        newpanel.add(lastNameLabel);

        lastNameField.setBounds(170, 10, 200, 25);
        newpanel.add(lastNameField);

        JLabel firstNameLabel = new JLabel("Enter the Guest first name: ");
        firstNameLabel.setBounds(10, 40, 150, 25);
        newpanel.add(firstNameLabel);

        firstNameField.setBounds(170, 40, 200, 25);
        newpanel.add(firstNameField);

        submitButton.setBounds(10, 80, 100, 25);
        newpanel.add(submitButton);

        cancelButton.setBounds(120, 80, 100, 25);
        newpanel.add(cancelButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
        
                newpanel.removeAll();
        
                JTextArea resultTextArea = new JTextArea();
                resultTextArea.setBounds(10, 110, 300, 100);
                newpanel.add(resultTextArea);
        
                boolean reservationFound = false;
        
                resultTextArea.setBounds(10, 110, 400, 100); 
                newpanel.add(resultTextArea);

                for (Reservation reservation : reservations) {
                    if (Objects.equals(lastName, reservation.getCustomer().getLastName()) &&
                            Objects.equals(firstName, reservation.getCustomer().getFirstName())) {
                        reservationFound = true;

                        reservation.setCheckInDate(new Date());
                        reservation.getBoard().setRoomStatus("Reserved");
                        resultTextArea.setText("Thank you " + firstName + " your room is now reserved.");
                        okayButton2.setBounds(10, 220, 100, 25);
                        newpanel.add(okayButton2);
                        break;
                    }
                }
        
                if (!reservationFound) {
                    resultTextArea.setText("Sorry " + firstName + " no reservation found.");
                okayButton.setBounds(10, 220, 100, 25);
                newpanel.add(okayButton);
                }
                okayButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                        try {
                            option4();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                okayButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                        try {
                            initialMenu();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        
                newpanel.revalidate();
                newpanel.repaint();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose();
                try {
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        newFrame.add(newpanel);
        newFrame.setPreferredSize(new Dimension(550, 350));
        newFrame.pack();
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
    }
    public void option5() throws ParseException {
        frame.dispose();

        JFrame newFrame = new JFrame("Check in");
        JPanel newpanel = new JPanel(null);  // Use absolute positioning
        newFrame.setSize(1500, 300);
        JTextField roomnum = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton okayButton = new JButton("OK");
        JButton okayButton2 = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JTextArea availableTextArea = new JTextArea();
        JTextArea notAvailableTextArea = new JTextArea();

        JLabel roomNumLabel = new JLabel("Enter the Room Number: ");
        roomNumLabel.setBounds(10, 10, 150, 25);
        newpanel.add(roomNumLabel);

        roomnum.setBounds(170, 10, 200, 25);
        newpanel.add(roomnum);

        submitButton.setBounds(10, 80, 100, 25);
        newpanel.add(submitButton);

        cancelButton.setBounds(120, 80, 100, 25);
        newpanel.add(cancelButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numText = roomnum.getText();
                int num = Integer.parseInt(numText);
        
                newpanel.removeAll();
        
                JTextArea resultTextArea = new JTextArea();
                resultTextArea.setBounds(10, 110, 300, 100);
                newpanel.add(resultTextArea);
        
                boolean reservationFound = false;
        
                resultTextArea.setBounds(10, 110, 400, 100);  
                newpanel.add(resultTextArea);

                for (Reservation reservation : reservations) {
                    if (Objects.equals(num, reservation.getBoard().getRoomNumber())) {
                        reservation.setCheckOutDate(new Date());
                        reservation.getBoard().setRoomStatus("Available");
                        Invoice newInvoice = new Invoice(reservation.getCustomer(), reservation.getBoard().getRoomPrice(), reservation.getNights());
                        invoices.add(newInvoice);
                        reservationFound = true;
                        resultTextArea.setText("Thank you for Staying with us, goodbye.");
                        okayButton2.setBounds(10, 220, 100, 25);
                        newpanel.add(okayButton2);
                        break;
                    }
                }
        
                if (!reservationFound) {
                    resultTextArea.setText("Sorry room number is not found or was not reserved");
                okayButton.setBounds(10, 220, 100, 25);
                newpanel.add(okayButton);
                }
                okayButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                        try {
                            option5();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                okayButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                        try {
                            initialMenu();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        
                newpanel.revalidate();
                newpanel.repaint();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose();
                try {
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        newFrame.add(newpanel);
        newFrame.setPreferredSize(new Dimension(550, 350));
        newFrame.pack();
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
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
                frame.dispose();
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
        JFrame newframe = new JFrame("Reservation Adder");
        JPanel newpanel = new JPanel(new GridLayout(0, 2, 10, 10));
        newframe.setSize(500, 300);

        String[] questions = {"Enter the Guest last name:", "Enter the Guest first name:", "Enter the number of people:", "Enter the Guest Phone Number:"};
        JTextField[] answerFields = new JTextField[questions.length];
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        String[] newquestions = {"Enter the Guest Age:","Enter the number of nights to stay (1-21):"};
        JTextField[] newanswerFields = new JTextField[newquestions.length];
        JButton submitButton2 = new JButton("Submit");


        for (int i = 0; i < questions.length; i++) {
            JLabel label = new JLabel(questions[i]);
            newpanel.add(label);

            answerFields[i] = new JTextField();
            newpanel.add(answerFields[i]);
            answerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }
        newpanel.add(submitButton);
        newpanel.add(cancelButton);
       
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newpanel.removeAll();

                for (int i = 0; i < newquestions.length; i++) {
                JLabel newlabel = new JLabel(newquestions[i]);
                newpanel.add(newlabel);

                newanswerFields[i] = new JTextField();
                newpanel.add(newanswerFields[i]);
                newanswerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
            }
                newpanel.add(submitButton2);
            

                submitButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                String lastName = answerFields[0].getText();
                String firstName = answerFields[1].getText();
                int numPeople = Integer.parseInt(answerFields[2].getText());
                String phoneNum = answerFields[3].getText();
                int age = Integer.parseInt(newanswerFields[0].getText());
                int nights = Integer.parseInt(newanswerFields[1].getText());
                
                Guest names = new Guest();
                names.setFirstName(firstName);
                names.setLastName(lastName);
    
                Reservation newReservation = new Reservation();
                newReservation.setCheckInDate();
                newReservation.setNights(nights);
                newReservation.calculateCheckOutDate();
    
                // Use a flag to track whether a matching guest is found
                boolean guestFound = false;
    
                for (Guest guest : guests) {
                    if (Objects.equals(lastName, guest.getLastName()) && Objects.equals(firstName, guest.getFirstName())) {
                        frame.dispose();
                        newReservation.setCustomer(guest);
                        guestFound = true;
                        try {
                            initialMenu();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                }
                if (!guestFound) {
                    frame.dispose();
                    Guest newGuest = new Guest(firstName, lastName, age);
                    newGuest.setPhoneNumber(phoneNum);
                    newReservation.setCustomer(newGuest);
                    guests.add(newGuest);
    
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
            }
        });
        newpanel.revalidate();
        newpanel.repaint();
    }
});

        cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                newframe.dispose();
                                try {
                                    initialMenu();
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
        newframe.add(newpanel);
        newframe.setPreferredSize(new Dimension(1000, 650));
        newframe.pack();
        newframe.setLocationRelativeTo(null);
        newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newframe.setVisible(true);
    }
    
    public void option1_2() throws ParseException {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        if (frame != null) {
            frame.dispose();
        }
    
        JFrame newFrame = new JFrame("");
        JPanel newpanel = new JPanel(null);
        newFrame.setSize(500, 300);
    
        JTextField lastNameField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
    
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
    
        lastNameField.setBounds(10, 10, 150, 25);
        submitButton.setBounds(10, 40, 80, 25);
        scrollPane.setBounds(10, 70, 480, 150); 
    
        newpanel.add(new JLabel("Enter the Guest last name:"));
        newpanel.getComponent(0).setBounds(10, 10, 200, 25);
        newpanel.add(lastNameField);
        newpanel.getComponent(1).setBounds(10, 40, 150, 25);
        newpanel.add(submitButton);
        newpanel.getComponent(2).setBounds(170, 40, 80, 25);
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newpanel.removeAll();
                JButton Okaybutton = new JButton("Ok");
                submitButton.setBounds(10, 40, 80, 25);
    
                String lastName = lastNameField.getText();
                if (containsName(guests, lastName)) {
                    StringBuilder combinedReservations = new StringBuilder();
                    for (Reservation reservation : reservations) {
                        if (Objects.equals(lastName, reservation.getCustomer().getLastName())) {
                            combinedReservations.append(reservation.printReservation());
                            combinedReservations.append("\n");
                        }
                    }
                    outputTextArea.setText(combinedReservations.toString());
                } else {
                    outputTextArea.setText("There is no Guest with this last name.\n");
                }
    
                newpanel.add(scrollPane);
                scrollPane.setBounds(10, 70, 480, 150); // Adjust the size if needed
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
                newpanel.add(Okaybutton);
                newpanel.getComponent(1).setBounds(170, 40, 80, 25);
                Okaybutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                        try {
                            initialMenu();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
    
                newpanel.revalidate();
                newpanel.repaint();
            }
    
            boolean containsName(java.util.List<Guest> list, String name) {
                return list.stream().anyMatch(p -> p.getLastName().equals(name));
            }
        });
    
        newFrame.add(newpanel);
        newFrame.setPreferredSize(new Dimension(550, 350));
        newFrame.pack();
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
    }
    

        public void option1_3() throws ParseException {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            if (frame != null) {
                frame.dispose();
            }
        
            JTextArea outputTextArea = new JTextArea();
            JFrame newFrame = new JFrame("");
            JPanel newpanel = new JPanel(new BorderLayout());
            JButton okayButton = new JButton("OK");
            newFrame.setSize(500, 300);
        
            StringBuilder combinedReservations = new StringBuilder();
            for (Reservation reservation : reservations) {
                combinedReservations.append(reservation.printReservation());
                combinedReservations.append("\n");
            }
        
            outputTextArea.setText("\t\tGuest\t\tCheckIn Date\t\tCheckOut Date\t\tNights\t\t\tRoom\t\t\tPeople\n\n" + combinedReservations.toString());
            outputTextArea.setEditable(false);
        
            newpanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);//scroll wheel to navigate
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(okayButton);
            newpanel.add(buttonPanel, BorderLayout.EAST);
        
            okayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    newFrame.dispose();
                    try {
                        initialMenu();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            newFrame.add(newpanel);
            newFrame.setPreferredSize(new Dimension(1500, 1300));
            newFrame.pack();
            newFrame.setLocationRelativeTo(null);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setVisible(true);
        }

        public void option2_1() throws ParseException {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            if (frame != null) {
                frame.dispose();
            }
            // Check if there are available rooms
            boolean hasAvailableRooms = false;
            for (Room room : rooms) {
                if (Objects.equals(room.getRoomStatus(), "Available")) {
                    hasAvailableRooms = true;
                    break;
                }
            }

            if (hasAvailableRooms) {
                JTextArea outputTextArea = new JTextArea();
                JFrame newFrame = new JFrame("");
                JPanel newpanel = new JPanel(new BorderLayout());
                JButton okayButton = new JButton("OK");
                newFrame.setSize(500, 300);
        
                StringBuilder combinedRooms = new StringBuilder();
                
                for (Room room : rooms) {
                    if (Objects.equals(room.getRoomStatus(), "Available")) {
                        combinedRooms.append(room.printRoom());
                        combinedRooms.append("\n");
                    }
                }
                outputTextArea.setText("\tRoom \t\t\t Bed \t\t\t Maximum Guest\t\t\t Price \n\n"+ combinedRooms.toString());
                outputTextArea.setEditable(false);
        
                newpanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(okayButton);
                newpanel.add(buttonPanel, BorderLayout.EAST);
        
                okayButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                        try {
                            initialMenu();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                newFrame.add(newpanel);
                newFrame.setPreferredSize(new Dimension(1500, 1300));
                newFrame.pack();
                newFrame.setLocationRelativeTo(null);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
            } 
        }
        public void option2_2() throws ParseException {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            if (frame != null) {
                frame.dispose();
            }
        
            JFrame resultFrame = new JFrame("Room Information");
            JPanel resultPanel = new JPanel(new FlowLayout());
            resultFrame.setSize(500, 300);
        
            JTextField roomNumField = new JTextField(10);
            JButton submitButton = new JButton("Submit");
            JTextArea outputTextArea = new JTextArea();
            outputTextArea.setEditable(false);
            JButton okayButton = new JButton("Ok");
            JButton cancelButton = new JButton("Cancel");
        
            resultPanel.add(new JLabel("Enter the Room number: "));
            resultPanel.add(roomNumField);
            resultPanel.add(submitButton);
        
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int num = Integer.parseInt(roomNumField.getText()); // convert to int
                        boolean roomFound = false;

                        resultPanel.removeAll();
                        resultPanel.add(outputTextArea);
                        resultPanel.add(okayButton);
        
                        for (Room room : rooms) {
                            if (room.getRoomNumber() == num) {
                                 resultPanel.remove(cancelButton);
                                String outcome = "Room " + room.getRoomNumber() + " is " + room.getRoomStatus();
                                outputTextArea.setText(outcome);
                                roomFound = true;
                                okayButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        resultFrame.dispose();
                                        try {
                                            initialMenu();
                                        } catch (ParseException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                });
                                break;
                            }
                        }
        
                        if (!roomFound) {
                            outputTextArea.setText("Room " + num + " not found");
                            resultPanel.add(cancelButton);
                            okayButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    resultFrame.dispose();
                                    try {
                                        option2_2();
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                        }

                        cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                resultFrame.dispose();
                                try {
                                    initialMenu();
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        resultFrame.revalidate();
                        resultFrame.repaint();
                    } catch (NumberFormatException ex) {
                        outputTextArea.setText("Invalid room number format");
                    }
                }
            });
            resultFrame.add(resultPanel);
            resultFrame.setPreferredSize(new Dimension(500, 300));
            resultFrame.pack();
            resultFrame.setLocationRelativeTo(null);
            resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            resultFrame.setVisible(true);
        }
           
        public void option2_3() throws ParseException {
            JFrame resultFrame = new JFrame("Room Number");
            JPanel resultPanel = new JPanel(new FlowLayout());
            resultFrame.setSize(500, 300);
        
            JTextField roomNumField = new JTextField(10);
            JButton submitButton = new JButton("Submit");
            JButton cancelButton = new JButton("Cancel");
            JTextArea outputTextArea = new JTextArea();
            resultPanel.add(new JLabel("Enter the Room number: "));
            resultPanel.add(roomNumField);
            resultPanel.add(submitButton);
            resultPanel.add(cancelButton);
        
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int num;
                    try {
                        num = Integer.parseInt(roomNumField.getText()); // convert to int
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        return;
                    }
        
                    boolean roomFound = false;
        
                    for (Room room : rooms) {
                        if (Objects.equals(room.getRoomNumber(), num)) {
                            roomFound = true;
        
                            resultFrame.dispose();
                            panel.removeAll();
        
                            String[] buttonLabels = {"Edit Bed Size", "Edit Bed Number", "Edit Room Price", "Change Room Status", "Main Menu", "Exit"};
                            JButton[] buttons = new JButton[buttonLabels.length];
        
                            // Create buttons in a loop
                            for (int i = 0; i < buttonLabels.length; i++) {
                                    buttons[i] = new JButton(buttonLabels[i]);
                                    buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
                                    buttons[i].setPreferredSize(new Dimension(2150, 1550));
                                    buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
                                    buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
                                    panel.add(buttons[i]);
                                // Add action listeners to the buttons
                                switch (i) {
                                    case 0:
                                        buttons[i].addActionListener(e1 -> room.setBedSize());
                                        break;
                                    case 1:
                                        buttons[i].addActionListener(e1 -> room.setBedNumber());
                                        break;
                                    case 2:
                                        buttons[i].addActionListener(e1 -> room.changeRoomPrice());
                                        break;
                                    case 3:
                                        buttons[i].addActionListener(e1 -> {
                                            if (Objects.equals(room.getRoomStatus(), "Available")) {
                                                room.setRoomStatus("Reserved");
                                            }
                                        });
                                        break;
                                    case 4:
                                        buttons[i].addActionListener(e1 -> {
                                            try {
                                                resultFrame.dispose();
                                                frame.dispose();
                                                initialMenu();
                                            } catch (ParseException p) {
                                                p.printStackTrace();
                                            }
                                        });
                                        break;
                                    case 5:
                                        buttons[i].addActionListener(e1 -> {
                                            resultFrame.dispose();
                                            System.exit(0);
                                        });
                                        break;
                                }
        
                                // Add buttons to the panel
                                panel.add(buttons[i]);
                            }
        
                            // Revalidate and repaint the panel
                            panel.revalidate();
                            panel.repaint();
                            return;
                        }else
                    
                    resultPanel.removeAll();
                    outputTextArea.setText("Room " + num + " not found");
                    JButton okayButton = new JButton("Okay");
                    resultPanel.add(outputTextArea);
                    resultPanel.add(okayButton);
                    okayButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            resultFrame.dispose();
                            frame.dispose();
                            try {
                                option2_3();
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                
                    // Revalidate and repaint the panel
                    resultPanel.revalidate();
                    resultPanel.repaint();
                    panel.revalidate();
                    panel.repaint();
                }
                }
            });
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resultFrame.dispose();
                    frame.dispose();
                    try {
                        initialMenu();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        
            resultFrame.add(resultPanel);
            resultFrame.setPreferredSize(new Dimension(500, 300));
            resultFrame.pack();
            resultFrame.setLocationRelativeTo(null);
            resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            resultFrame.setVisible(true);
        }
        
        public void option2_4() throws ParseException {
            panel.removeAll();
        
            JButton submitButton = new JButton("Okay");
        
            StringBuilder combinedRooms = new StringBuilder();
        
            for (Room room : rooms) {
                combinedRooms.append(room.printRoom());
            }
        
            String finalResult = combinedRooms.toString();
            JLabel label = new JLabel("<html>" + "All Rooms" + finalResult.replaceAll("\n", "<br>") + "</html>");


        
            // Setting the font size and style
            Font labelFont = new Font("Arial", Font.PLAIN, 13); // Adjust the font size as needed
            label.setFont(labelFont);
            // Get the text area from the JScrollPane's viewport
            JTextArea textArea = new JTextArea(finalResult);
            textArea.setMargin(new Insets(20, 20, 20, 20));  // Set line and paragraph margins to zero

            // Customize text area properties if needed
            textArea.setFont(new Font("Arial", Font.PLAIN, 13)); // Adjust the font size as needed

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            Dimension textAreaSize = new Dimension(0, 1000); // Adjust width and height as needed
            scrollPane.setPreferredSize(textAreaSize);

            // Use BorderLayout for the main panel
            panel.setLayout(new BorderLayout());

            // Add the scroll pane to the center
            panel.add(scrollPane);

            // Add the submit button to the east (right) side
            panel.add(submitButton, BorderLayout.EAST);

            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        frame.dispose();
                        initialMenu();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        
            panel.revalidate();
            panel.repaint();
        }
        
    public void option3_1() throws ParseException {
        Guest newGuest = new Guest();
        guests.add(newGuest);
        initialMenu();
    }
    public void option3_2() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Guest last name: ");
        String lastName = in.nextLine();
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
    
        JTextArea title = new JTextArea("\tFirstName\t\tLast Name\tRoom Number\tCheck-in Date\t\tCheck-out Date");
        title.setEditable(false);
    
        // Setting the front size and style
        title.setFont(new Font("Arial", Font.PLAIN, 19));
    
        panel.add(title);
    
        JTextArea textArea = new JTextArea(finalResult);
        textArea.setEditable(false);
    
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
        // Setting the front size and style
        textArea.setFont(new Font("Arial", Font.PLAIN, 19));
    
        panel.add(scrollPane);
        panel.add(submitButton);
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
    
        panel.revalidate();
        panel.repaint();
    }
    
    public void option3_4() throws ParseException {
        JFrame newframe = new JFrame("Guest Confirming");
        JPanel newpanel = new JPanel(new GridLayout(0, 2, 10, 10));
        newframe.setSize(500, 300);

        String[] Labels = {"Enter the Guest last name: ", "Enter the Guest first name: "};
        JLabel[] questions = new JLabel[Labels.length];
        JTextField[] answerFields = new JTextField[questions.length];
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton tryagainButton = new JButton("Try again");
        String[] buttonLabels = {"First Name", "Last Name", "Age", "Phone number", "Main Menu", "Exit"};
        JButton[] buttons = new JButton[buttonLabels.length]; 
        JTextArea notfound = new JTextArea();


        for (int i = 0; i < questions.length; i++) {
            JLabel label = new JLabel(Labels[i]);
            newpanel.add(label);

            answerFields[i] = new JTextField();
            newpanel.add(answerFields[i]);
            answerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
            tryagainButton.setFont(new Font("Arial", Font.PLAIN, 20));
        }
        newpanel.add(submitButton);
        newpanel.add(cancelButton);
       
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                

        String lastName = answerFields[0].getText();
        String firstName = answerFields[1].getText();
        for (Guest guest : guests) {
            if (Objects.equals(lastName, guest.getLastName()) && Objects.equals(firstName, guest.getFirstName())) {
                
                newframe.dispose();
                panel.removeAll();
               

                 for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
            buttons[i].setPreferredSize(new Dimension(2150, 1550));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
            panel.add(buttons[i]);
        }
    
        buttons[0].addActionListener(o -> {
            try {
                option1_1();
            } catch (ParseException ex) {
                ex.printStackTrace();
                
            }
        });
    
        buttons[1].addActionListener(o -> {
            try {
                option1_2();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[2].addActionListener(o -> {
            try {
                option1_3();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
    
        buttons[3].addActionListener(o -> {
            try {
                newframe.dispose();
                frame.dispose();
                initialMenu();
            } catch (ParseException p) {
                p.printStackTrace();
            }
        });
    
        buttons[4].addActionListener(o -> {
            frame.dispose();
            newframe.dispose();
        });

            }

        
        else {
            newpanel.removeAll();
            notfound.setText("The Guest " + firstName + " " + lastName + " is not found");
            newpanel.add(notfound);
            cancelButton.setPreferredSize(new Dimension(5, 5));
            tryagainButton.setPreferredSize(new Dimension(5, 5));
            newpanel.add(cancelButton);
            newpanel.add(tryagainButton);
            newframe.revalidate();
            newframe.repaint();
        }   }
        frame.revalidate();
        frame.repaint();
            }});
            cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                newframe.dispose();
                                frame.dispose();
                                try {
                                    initialMenu();
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
        tryagainButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                newframe.dispose();
                                try {
                                    option3_4();
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
        newframe.add(newpanel);
        newframe.setPreferredSize(new Dimension(650, 350));
        newframe.pack();
        newframe.setLocationRelativeTo(null);
        newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newframe.setVisible(true);
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
        JFrame newFrame = new JFrame("Employee adder");
        JPanel mainPanel = new JPanel(new BorderLayout());
        newFrame.setSize(750, 450);
    
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
        JTextField lastNameField = new JTextField(10);
        JTextField firstNameField = new JTextField(10);
        JTextField ageField = new JTextField(10);
        JTextField hourlyWageField = new JTextField(10);
        JTextField hoursWorkedField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton okayButton = new JButton("Ok");
    
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
    
        inputPanel.add(new JLabel("Enter the Employee first Name: "));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Enter the Employee last name: "));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Enter the Employee age: "));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Enter the Employee hourly wage: "));
        inputPanel.add(hourlyWageField);
        inputPanel.add(new JLabel("Enter the Employee hours worked: "));
        inputPanel.add(hoursWorkedField);
    
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
    
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Use scrollPane directly
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Employee newEmployees = new Employee();
            inputPanel.removeAll();
            buttonPanel.removeAll();
            String employeeLastName = lastNameField.getText();
            String employeeFirstName = firstNameField.getText();
            int employeeAge = Integer.parseInt(ageField.getText());
            float hourlyWage = Float.parseFloat(hourlyWageField.getText());
            float hourlyWorked = Float.parseFloat(hoursWorkedField.getText());

            outputTextArea.setText("Employee " + employeeFirstName + " " + employeeLastName + " has been added.");
            buttonPanel.add(Box.createVerticalStrut(10)); // Add some vertical space
            buttonPanel.add(okayButton);
            newEmployees.setEmployeeFirstName(employeeFirstName);
            newEmployees.setEmployeeLastName(employeeLastName);
            newEmployees.setAge(employeeAge);
            newEmployees.setHourlyWage(hourlyWage);
            newEmployees.setHoursWorked(hourlyWorked);
            employees.add(newEmployees);
            okayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        newFrame.dispose();
                        frame.dispose();
                        initialMenu();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            buttonPanel.revalidate();
            buttonPanel.repaint();
            inputPanel.revalidate();
            inputPanel.repaint();
        }
    });

    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                newFrame.dispose();
                frame.dispose();
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    });

    newFrame.add(mainPanel);
    newFrame.setPreferredSize(new Dimension(750, 450));
    newFrame.pack();
    newFrame.setLocationRelativeTo(null);
    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newFrame.setVisible(true);
}

public void option6_2() throws ParseException {
    frame.dispose();
    JFrame newFrame = new JFrame("Employee info");
    JPanel mainPanel = new JPanel(new BorderLayout());
    newFrame.setSize(750, 450);

    JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5)); // Rows, Columns, Horizontal Gap, Vertical Gap
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JTextField lastNameField = new JTextField(10);
    JTextField firstNameField = new JTextField(10);
    JButton submitButton = new JButton("Submit");
    JButton cancelButton = new JButton("Cancel");
    JButton okayButton = new JButton("Ok");
    JButton tryagainButton = new JButton("Try Again");

    JTextArea outputTextArea = new JTextArea();
    outputTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(outputTextArea);

    inputPanel.add(new JLabel("Enter the Employee first Name: "));
    inputPanel.add(firstNameField);
    inputPanel.add(new JLabel("Enter the Employee last name: "));
    inputPanel.add(lastNameField);

    buttonPanel.add(submitButton);
    buttonPanel.add(cancelButton);

    mainPanel.add(inputPanel, BorderLayout.NORTH);
    mainPanel.add(scrollPane, BorderLayout.CENTER); // Use scrollPane directly
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Employee newEmployees = new Employee();
            String employeeLastName = lastNameField.getText();
            String employeeFirstName = firstNameField.getText();

            boolean found = false;

            for (Employee employee : employees) {
                if (Objects.equals(employeeLastName, employee.getEmployeeLastName())
                        && Objects.equals(employeeFirstName, employee.getEmployeeFirstName())) {
                    outputTextArea.setText("\t\tName\t\tAge\t\tHourly Wage\t\tHours Worked\t\tTotal Pay\n\n");
                    outputTextArea.append(employee.printEmployeeInfo());
                    buttonPanel.removeAll();
                    buttonPanel.add(Box.createVerticalStrut(10));
                    buttonPanel.add(okayButton);
                    found = true;
                    break;
                }
            }

            if (!found) {
                outputTextArea.setText("Sorry, no employee found");
                buttonPanel.removeAll();
                buttonPanel.add(Box.createVerticalStrut(10));
                buttonPanel.add(tryagainButton);
                buttonPanel.add(cancelButton);
            }

            okayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        newFrame.dispose();
                        frame.dispose();
                        initialMenu();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            tryagainButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        newFrame.dispose();
                        frame.dispose();
                        option6_2();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        newFrame.dispose();
                        frame.dispose();
                        initialMenu();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            buttonPanel.revalidate();
            buttonPanel.repaint();
        }
    });

    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                newFrame.dispose();
                frame.dispose();
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    });

    newFrame.add(mainPanel);
    newFrame.setPreferredSize(new Dimension(750, 450));
    newFrame.pack();
    newFrame.setLocationRelativeTo(null);
    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newFrame.setVisible(true);
}

public void option6_3() throws ParseException {
    panel.removeAll();

    JTextArea title = new JTextArea("\tFirstName\t\tAge\tHourly Wage\t\tHours Worked\tTotal Pay");
    title.setEditable(false);

    // Setting the front size and style
    title.setFont(new Font("Arial", Font.PLAIN, 19));

    panel.add(title);

    JButton submitButton = new JButton("Okay");

    StringBuilder combinedEmployees = new StringBuilder();

    JLabel label = new JLabel();
    panel.add(label);
    for (Employee employee : employees) {
        combinedEmployees.append(employee.printEmployeeInfo());
    }

    // Convert StringBuilder to a String and print it(very similar to show all guest method)
    String finalResult = combinedEmployees.toString();

    JTextArea textArea = new JTextArea(finalResult);
    textArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // Setting the front size and style
    textArea.setFont(new Font("Arial", Font.PLAIN, 19));

    
    panel.add(scrollPane); 
    panel.add(submitButton);

    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                frame.dispose();
                initialMenu();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    });

    panel.revalidate();
    panel.repaint();
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
                      //  employee.setEmployeeFirstName();
                        break;
                    case 2:
                     //   employee.setEmployeeLastName();
                        break;
                    case 3:
                     //   employee.setAge();
                        break;
                    case 4:
                       // employee.setHourlyWage();
                        break;
                    case 5:
                       // employee.setHoursWorked();
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
        frame.dispose();
        JFrame newFrame = new JFrame("Search Invoice");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        newFrame.setSize(750, 450);
    
        JTextField lastNameField = new JTextField(10);
        JTextField firstNameField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton okayButton = new JButton("Ok");
        JButton tryagainButton = new JButton("Try Again");
    
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
    
        inputPanel.add(new JLabel("Enter the Guest first Name: "));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Enter the Guest last name: "));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(submitButton);
        inputPanel.add(cancelButton);
    
        mainPanel.add(inputPanel, BorderLayout.NORTH);
    
        JPanel titlePanel = new JPanel(); // New JPanel for the title
        JTextArea title = new JTextArea("\tFirst Name\tLast Name\tNights Stayed\tDiscount\tTotal \n\n");
        title.setEditable(false);
        titlePanel.add(title);
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
    
                boolean exists = false;
    
                for (Invoice invoice : invoices) {
                    if (Objects.equals(lastName, invoice.getCostumer().getLastName())
                            && Objects.equals(firstName, invoice.getCostumer().getFirstName())) {
                        titlePanel.add(title);
                        outputTextArea.append(invoice.printInvoice());
                        exists = true;
                        buttonPanel.add(okayButton);
                    }
                }
    
                if (!exists) {
                    outputTextArea.setText("Invoice is not found for " + firstName + " " + lastName);
                    buttonPanel.add(tryagainButton);
                    buttonPanel.add(cancelButton);
                }
    
                ActionListener buttonListener = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            newFrame.dispose();
                            option7_1();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                };
    
                okayButton.addActionListener(buttonListener);
                tryagainButton.addActionListener(buttonListener);
    
                mainPanel.add(titlePanel, BorderLayout.NORTH);
                mainPanel.add(scrollPane, BorderLayout.CENTER);
                mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    
                mainPanel.validate();
                mainPanel.repaint();
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    newFrame.dispose();
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
    
        newFrame.add(mainPanel);
        newFrame.setPreferredSize(new Dimension(750, 450));
        newFrame.pack();
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
    }    

    public void option7_2() throws ParseException {
        panel.removeAll();
    
        JButton okayButton = new JButton("Okay");
        JTextArea allInvoices = new JTextArea();
    
        StringBuilder combinedInvoices = new StringBuilder();
    
        for (Invoice invoice : invoices) {
            combinedInvoices.append(invoice.printInvoice());
        }
    
        // Convert StringBuilder to a String and print it (just like options before it)
        String finalResult = combinedInvoices.toString();
    
        JTextArea title = new JTextArea("\t\tFirst Name\t\tLast Name\t\tNights Stayed\t\tDiscount\t\tTotal \n\n");
        title.setEditable(false);
    
        JTextArea textArea = new JTextArea(finalResult);
        textArea.setEditable(false);
        // Setting the font size and style
        textArea.setFont(new Font("Arial", Font.PLAIN, 19));
    
        panel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
    
        panel.add(title);
        panel.add(textArea);
        panel.add(okayButton); 
    
        okayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    initialMenu();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
    
        panel.revalidate();
        panel.repaint();
    }
    
}