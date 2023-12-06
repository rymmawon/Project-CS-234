import java.text.ParseException;
import java.util.*;
import java.lang.Math;
import java.text.SimpleDateFormat;

// for a more customizeable layout
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
            Member newMember = new Member(generateFirstName(), generateLastName(), generateRandomNumber(18, 100));
            members.add(newMember);
            newMember.setMemberTier(tiers[generateRandomNumber(1,3) - 1]);
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
        }
    
        frame = new JFrame("Hotel Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setBackground(Color.BLUE); 
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
        JPanel newpanel = new JPanel(new GridLayout(0, 2, 10, 10)); // GridLayout
        newFrame.setSize(1500, 300);
    
        JTextField lastNameField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton okayButton = new JButton("OK");
        JButton okayButton2 = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JTextArea availableTextArea = new JTextArea();
        JTextArea notAvailableTextArea = new JTextArea();
    
        Font labelFont = new Font("Arial", Font.PLAIN, 15);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);
    
        JLabel lastNameLabel = new JLabel("Enter the Guest last name:");
        lastNameLabel.setFont(labelFont);
        availableTextArea.setFont(textFieldFont);
        notAvailableTextArea.setFont(textFieldFont);
        newpanel.add(lastNameLabel);
        notAvailableTextArea.setEditable(false);
        availableTextArea.setEditable(false);

        lastNameField.setFont(textFieldFont);
        newpanel.add(lastNameField);
    
        JLabel firstNameLabel = new JLabel("Enter the Guest first name:");
        firstNameLabel.setFont(labelFont);
        newpanel.add(firstNameLabel);
    
        firstNameField.setFont(textFieldFont);
        newpanel.add(firstNameField);
    
        // Font and size for buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 15);
    
        submitButton.setFont(buttonFont);
        newpanel.add(submitButton);
    
        cancelButton.setFont(buttonFont);
        newpanel.add(cancelButton);

        okayButton.setFont(buttonFont);
        okayButton2.setFont(buttonFont);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
        
                newpanel.removeAll();
                
        
                JTextArea resultTextArea = new JTextArea();
                resultTextArea.setBounds(10, 110, 400, 100);
                resultTextArea.setFont(textFieldFont);
                resultTextArea.setEditable(false);
                newpanel.add(resultTextArea);
                newpanel.setLayout(new BoxLayout(newpanel, BoxLayout.Y_AXIS));
        
                boolean reservationFound = false;

                for (Reservation reservation : reservations) {
                    if (Objects.equals(lastName, reservation.getCustomer().getLastName()) &&
                            Objects.equals(firstName, reservation.getCustomer().getFirstName())) {
                        reservationFound = true;

                        reservation.setCheckInDate(new Date());
                        reservation.getBoard().setRoomStatus("Reserved");
                        resultTextArea.setText("Thank you " + firstName + " your room is now reserved.");
                        okayButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        newpanel.add(okayButton2, BorderLayout.SOUTH);
                        break;
                    }
                }
        
                if (!reservationFound) {
                resultTextArea.setText("Sorry " + firstName + " no reservation found.");
                okayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                newpanel.add(okayButton, BorderLayout.SOUTH);
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

        JFrame newFrame = new JFrame("Check out");
        JPanel newpanel = new JPanel(null);
        newFrame.setSize(1500, 300);
        JTextField roomnum = new JTextField(20);
        JTextArea availableTextArea = new JTextArea();
        JTextArea notAvailableTextArea = new JTextArea();

        String[] buttonLabels = {"Submit", "OK", "Ok","Cancel"};
        JButton[] buttons = new JButton[buttonLabels.length];
    
        // Create buttons in a loop for the same size
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }

        JLabel roomNumLabel = new JLabel("<html>Enter the Room Number:</html>");
        roomNumLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        roomNumLabel.setBounds(10, 10, 150, 50);
        newpanel.add(roomNumLabel);
        
        roomnum.setFont(new Font("Arial", Font.PLAIN, 20));

        roomnum.setBounds(170, 10, 200, 25);
        newpanel.add(roomnum);

        buttons[0].setBounds(10, 80, 100, 25);
        newpanel.add(buttons[0]);

        buttons[3].setBounds(120, 80, 100, 25);
        newpanel.add(buttons[3]);

        buttons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numText = roomnum.getText();
                int num = Integer.parseInt(numText);
        
                newpanel.removeAll();
        
                JTextArea resultTextArea = new JTextArea();
        
                boolean reservationFound = false;
        
                resultTextArea.setBounds(10, 110, 600, 100); 
                resultTextArea.setFont(new Font("Arial", Font.PLAIN, 20)); 
                resultTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
                newpanel.add(resultTextArea);

                for (Reservation reservation : reservations) {
                    if (Objects.equals(num, reservation.getBoard().getRoomNumber())) {
                        reservation.setCheckOutDate(new Date());
                        reservation.getBoard().setRoomStatus("Available");
                        Invoice newInvoice = new Invoice(reservation.getCustomer(), reservation.getBoard().getRoomPrice(), reservation.getNights());
                        invoices.add(newInvoice);
                        reservationFound = true;
                        resultTextArea.setText("Thank you for Staying with us, goodbye.");
                        buttons[2].setBounds(10, 220, 100, 25);
                        newpanel.add(buttons[2]);
                        break;
                    }
                }
        
                if (!reservationFound) {
                    resultTextArea.setText("Sorry room number is not found or was not reserved");
                buttons[1].setBounds(10, 220, 100, 25);
                newpanel.add(buttons[1]);
                }
                buttons[1].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame.dispose();
                        try {
                            option5();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                buttons[2].addActionListener(new ActionListener() {
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
        
        buttons[3].addActionListener(new ActionListener() {
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
            buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
            buttons[i].setPreferredSize(new Dimension(2150, 1550));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
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
        frame.dispose();
        JFrame newframe = new JFrame("Guest Search");
        JPanel newpanel = new JPanel(new GridLayout(0, 2, 10, 10));
        newframe.setSize(500, 300);

        String[] Labels = {"Enter the Guest last name: ", "Enter the Guest first name: "};
        JLabel[] questions = new JLabel[Labels.length];
        JTextField[] answerFields = new JTextField[questions.length];
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton okaybutton = new JButton("OK");
        JButton tryagainButton = new JButton("Try again");
        JTextArea textArea = new JTextArea();


        for (int i = 0; i < questions.length; i++) {
            JLabel label = new JLabel(Labels[i]);
            newpanel.add(label);

            answerFields[i] = new JTextField();
            newpanel.add(answerFields[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            answerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
            okaybutton.setFont(new Font("Arial", Font.PLAIN, 20));
            tryagainButton.setFont(new Font("Arial", Font.PLAIN, 20));
            textArea.setFont(new Font("Arial", Font.PLAIN, 20));
        }
        newpanel.add(submitButton);
        newpanel.add(cancelButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newpanel.removeAll();
                newpanel.setLayout(new BoxLayout(newpanel, BoxLayout.Y_AXIS));
        String lastName = answerFields[0].getText();
        String firstName = answerFields[1].getText();
        JTextArea title = new JTextArea("\tFirstName\t\tLast Name\t\t\tAge");
        boolean found = false;
            for (Guest guest : guests) {
                    if (Objects.equals(lastName, guest.getLastName()) && Objects.equals(firstName, guest.getFirstName())) {
                    newframe.setSize(900, 300);
                    found= true;
                    
                    title.setEditable(false);
                
                    // Setting the front size and style
                    title.setFont(new Font("Arial", Font.PLAIN, 20));
                
                    newpanel.add(title);
                    textArea.setText("\t" + firstName + "\t\t" + lastName + "\t\t" + guest.getAge());
                    newpanel.add(textArea);
                    okaybutton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    newpanel.add(okaybutton, BorderLayout.SOUTH);
                    }}
                    if(!found){
                    textArea.setText("NO guest found for " + firstName + " " + lastName);
                    newpanel.add(textArea);
                    tryagainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    newpanel.add(tryagainButton, BorderLayout.SOUTH);
                }
                
                newframe.revalidate();
                newframe.repaint();
            }
        });   
                    okaybutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        newframe.dispose();
                        frame.dispose();
                       option3_2();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            tryagainButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        newframe.dispose();
                        frame.dispose();
                        option3_2();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
                        cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        newframe.dispose();
                        frame.dispose();
                        initialMenu();
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
        JFrame firstnameframe = new JFrame("Guest Confirming");
        JFrame newframe = new JFrame("Guest Confirming");
        JPanel newpanel = new JPanel(new GridLayout(0, 2, 10, 10));
        newframe.setSize(500, 300);

        String[] Labels = {"Enter the Guest last name: ", "Enter the Guest first name: "};
        JLabel[] questions = new JLabel[Labels.length];
        JTextField[] answerFields = new JTextField[questions.length];
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton okaybutton = new JButton("ok");
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
            okaybutton.setFont(new Font("Arial", Font.PLAIN, 20));
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
                

        JPanel fpanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel fquestion = new JLabel("Enter the Guest new first name: ");
        JLabel lquestion = new JLabel("Enter the Guest new last name: ");
        JLabel aquestion = new JLabel("Enter the Guest new Age: ");
        JLabel pquestion = new JLabel("Enter the Guest new Phone Number: ");
        JTextField firstNameField = new JTextField();
        JButton firstNameButton = new JButton("Submit");
               

                 for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
            buttons[i].setPreferredSize(new Dimension(2150, 1550));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
            panel.add(buttons[i]);
        }
    
        buttons[0].addActionListener(o -> {

        fpanel.add(fquestion);
        fpanel.add(firstNameField);
        fpanel.add(firstNameButton);
        fpanel.add(cancelButton);

       firstNameButton.setFont(new Font("Arial", Font.PLAIN, 20));
       firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
       cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
    
        firstNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fpanel.removeAll();
            
                
        String firstName = firstNameField.getText();
        guest.setFirstName(firstName);
        notfound.setText("First Name has been changed to " + firstName);
        fpanel.add(notfound);
        fpanel.add(okaybutton);
        firstnameframe.revalidate();
        firstnameframe.repaint();
        }});
        firstnameframe.add(fpanel);
        firstnameframe.setPreferredSize(new Dimension(650, 350));
        firstnameframe.pack();
        firstnameframe.setLocationRelativeTo(null);
        firstnameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstnameframe.setVisible(true);
        
        });
        buttons[1].addActionListener(o -> {
        fpanel.add(lquestion);
        fpanel.add(firstNameField);
        fpanel.add(firstNameButton);
        fpanel.add(cancelButton);

       firstNameButton.setFont(new Font("Arial", Font.PLAIN, 20));
       firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
       cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
    
        firstNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fpanel.removeAll();
            
                
        String firstName = firstNameField.getText();
        guest.setLastName(firstName);
        notfound.setText("Last Name has been changed to " + firstName);
        fpanel.add(notfound);
        fpanel.add(okaybutton);
        firstnameframe.revalidate();
        firstnameframe.repaint();
            }});
        firstnameframe.add(fpanel);
        firstnameframe.setPreferredSize(new Dimension(650, 350));
        firstnameframe.pack();
        firstnameframe.setLocationRelativeTo(null);
        firstnameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstnameframe.setVisible(true);
        });
    
        buttons[2].addActionListener(o -> {
        fpanel.add(aquestion);
        fpanel.add(firstNameField);
        fpanel.add(firstNameButton);
        fpanel.add(cancelButton);

       firstNameButton.setFont(new Font("Arial", Font.PLAIN, 20));
       firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
       cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
    
        firstNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fpanel.removeAll();
            
                
        int firstName = Integer.parseInt(firstNameField.getText());
        guest.setAge(firstName);
        notfound.setText("Age has been Updated to " + firstName);
        fpanel.add(notfound);
        fpanel.add(okaybutton);
        firstnameframe.revalidate();
        firstnameframe.repaint();
            }});
        firstnameframe.add(fpanel);
        firstnameframe.setPreferredSize(new Dimension(650, 350));
        firstnameframe.pack();
        firstnameframe.setLocationRelativeTo(null);
        firstnameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstnameframe.setVisible(true);
        });
     buttons[3].addActionListener(o -> {
        fpanel.add(pquestion);
        fpanel.add(firstNameField);
        fpanel.add(firstNameButton);
        fpanel.add(cancelButton);

       firstNameButton.setFont(new Font("Arial", Font.PLAIN, 20));
       firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
       cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
    
        firstNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fpanel.removeAll();
            
                
         String firstName = firstNameField.getText();
        guest.setPhoneNumber(firstName);
        notfound.setText("Phone Number has been Updated to " + firstName);
        fpanel.add(notfound);
        fpanel.add(okaybutton);
        firstnameframe.revalidate();
        firstnameframe.repaint();
            }});
        firstnameframe.add(fpanel);
        firstnameframe.setPreferredSize(new Dimension(650, 350));
        firstnameframe.pack();
        firstnameframe.setLocationRelativeTo(null);
        firstnameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstnameframe.setVisible(true);
        });
        buttons[4].addActionListener(o -> {
            try {
                newframe.dispose();
                initialMenu();
            } catch (ParseException p) {
                p.printStackTrace();
            }
        });
    
        buttons[5].addActionListener(o -> {
            frame.dispose();
            newframe.dispose();
        });
        frame.revalidate();
        frame.repaint();
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

            }});
            okaybutton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                firstnameframe.dispose();
                                newframe.dispose();
                         } });
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
               // Member newMember = new Member(guest.getFirstName(), guest.getLastName(), guest.getAge());
                //members.add(newMember);
            }
        }
        initialMenu();
    }

    public void option6_1() throws ParseException {
        JFrame newFrame = new JFrame("Employee adder");
        JPanel mainPanel = new JPanel(new BorderLayout());
        newFrame.setSize(750, 450);
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setPreferredSize(new Dimension(550, 425));
        JTextField lastNameField = new JTextField(10);
        JTextField firstNameField = new JTextField(10);
        JTextField ageField = new JTextField(10);
        JTextField hourlyWageField = new JTextField(10);
        JTextField hoursWorkedField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton okayButton = new JButton("Ok");
        String[] questions = {"Enter the Employee first Name: ", "Enter the Employee last name: ", "Enter the Employee age: ", "Enter the Employee hourly wage: ","Enter the Employee hours worked: "};
        JTextField[] answerFields = new JTextField[questions.length];
    
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

                for (int i = 0; i < questions.length; i++) {
            JLabel label = new JLabel(questions[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            inputPanel.add(label);

            answerFields[i] = new JTextField();
            inputPanel.add(answerFields[i]);
            answerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
            okayButton.setFont(new Font("Arial", Font.PLAIN, 20));
            outputTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        }
    
        inputPanel.add(submitButton);
        inputPanel.add(cancelButton);
    
        mainPanel.add(inputPanel, BorderLayout.CENTER);
    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Employee newEmployees = new Employee();
            inputPanel.removeAll();
            buttonPanel.removeAll();
            String employeeLastName = answerFields[1].getText();
            String employeeFirstName = answerFields[0].getText();
            int employeeAge = Integer.parseInt(answerFields[2].getText());
            float hourlyWage = Float.parseFloat(answerFields[3].getText());
            float hourlyWorked = Float.parseFloat(answerFields[4].getText());

            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            outputTextArea.setText("Employee " + employeeFirstName + " " + employeeLastName + " has been added.");
            
            mainPanel.add(outputTextArea);
            mainPanel.add(okayButton);
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
    newFrame.setSize(270, 170);

    JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton submitButton = new JButton("Submit");
    JButton cancelButton = new JButton("Cancel");
    JButton okayButton = new JButton("Ok");
    JButton tryagainButton = new JButton("Try Again");

    String[] questions = {"Enter the Employee first Name: ", "Enter the Employee last name: "};
        JTextField[] answerFields = new JTextField[questions.length];
    
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
    


                for (int i = 0; i < questions.length; i++) {
            JLabel label = new JLabel(questions[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 15));
            inputPanel.add(label);

            answerFields[i] = new JTextField();
            inputPanel.add(answerFields[i]);
            answerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
            okayButton.setFont(new Font("Arial", Font.PLAIN, 20));
            tryagainButton.setFont(new Font("Arial", Font.PLAIN, 20));
            outputTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        }
        inputPanel.add(submitButton);
        inputPanel.add(cancelButton);

    mainPanel.add(inputPanel, BorderLayout.CENTER);

    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mainPanel.removeAll();
            inputPanel.removeAll();
            Employee newEmployees = new Employee();
            String employeeLastName = answerFields[1].getText();
            String employeeFirstName = answerFields[0].getText();

            boolean found = false;


            for (Employee employee : employees) {
                if (Objects.equals(employeeLastName, employee.getEmployeeLastName())
                        && Objects.equals(employeeFirstName, employee.getEmployeeFirstName())) {
                    newFrame.setSize(1350, 450);
                    outputTextArea.setText("\t\tName\t\tAge\t\tHourly Wage\t\tHours Worked\t\tTotal Pay\n\n");
                    outputTextArea.append(employee.printEmployeeInfo());
                    buttonPanel.removeAll();
                    buttonPanel.add(Box.createVerticalStrut(10));
                    mainPanel.add(outputTextArea);
                    buttonPanel.add(okayButton);
                    mainPanel.add(buttonPanel, BorderLayout.SOUTH); 
                    found = true;
                    break;
                }
            }

            if (!found) {
                inputPanel.setLayout(new GridLayout(0, 1, 15, 15));
                outputTextArea.setText("Sorry, no employee found for " + employeeFirstName);
                inputPanel.add(outputTextArea);
                buttonPanel.removeAll();
                buttonPanel.add(Box.createVerticalStrut(10));
                buttonPanel.add(tryagainButton);
                buttonPanel.add(cancelButton);
                mainPanel.add(inputPanel);
                mainPanel.add(buttonPanel, BorderLayout.SOUTH);  
            }

            okayButton.addActionListener(new ActionListener() {
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
            mainPanel.repaint();
            mainPanel.revalidate();
            inputPanel.repaint();
            inputPanel.revalidate();
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
    newFrame.setPreferredSize(new Dimension(550, 250));
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
    title.setFont(new Font("Arial", Font.PLAIN, 20));

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

public void option6_4() throws ParseException {
    JFrame Employeeframe = new JFrame("Employee Edititor");
    JFrame newframe = new JFrame("Employee Confirming");
    JPanel fpanel = new JPanel(new GridLayout(0, 2, 10, 10));
    JPanel newpanel = new JPanel(new GridLayout(0, 2, 10, 10));
    newframe.setSize(500, 300);

    String[] Labels = {"Enter the Employee last name: ", "Enter the Employee first name: "};
    JLabel[] questions = new JLabel[Labels.length];
    JTextField[] answerFields = new JTextField[questions.length];
    
    JButton submitButton = new JButton("Submit");
    JButton cancelButton = new JButton("Cancel");
    JButton okaybutton = new JButton("OK");
    JButton tryAgainbutton = new JButton("Try Again");
    String[] buttonLabels = {"First Name", "Last Name", "Age", "Hourly Wage", "Hours Worked", "Main Menu", "Exit"};
    JButton[] buttons = new JButton[buttonLabels.length];
    JTextArea notfound = new JTextArea();

    for (int i = 0; i < questions.length; i++) {
        JLabel label = new JLabel(Labels[i]);
        newpanel.add(label);

        answerFields[i] = new JTextField();
        newpanel.add(answerFields[i]);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        answerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        okaybutton.setFont(new Font("Arial", Font.PLAIN, 20));
        tryAgainbutton.setFont(new Font("Arial", Font.PLAIN, 20));
        notfound.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    newpanel.add(submitButton);
    newpanel.add(cancelButton);

    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String lastName = answerFields[0].getText();
            String firstName = answerFields[1].getText();
            for (Employee employee : employees) {
                if (Objects.equals(lastName, employee.getEmployeeLastName()) && Objects.equals(firstName, employee.getEmployeeFirstName())) {
                    newframe.dispose();
                    panel.removeAll();
                    
                    
                    String[] newLabels = {
                        "Enter the Employee new first name: ",
                        "Enter the Employee new last name: ",
                        "Enter the Employee new Age: ",
                        "Enter the Employee new Hourly Wage: ",
                        "Enter the Employee new Hours Worked: "
                };
        
                JLabel[] newQuestions = new JLabel[newLabels.length]; 
                JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));// new panel for a better layout
                JTextArea result_messasge = new JTextArea();
        
                // for loop to set the font for the questions
                for (int i = 0; i < newLabels.length; i++) {
                    JLabel questionJLabel = new JLabel(newLabels[i]); // Corrected array name
                    questionJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    result_messasge.setFont(new Font("Arial", Font.PLAIN, 20));
                    // Assign the JLabel to the array
                    newQuestions[i] = questionJLabel;
                }
                JTextField[] newanswerFields = new JTextField[3];
                for (int i = 0; i < newanswerFields.length; i++) {
                    newanswerFields[i] = new JTextField();
                    newanswerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
                }
                String[] submitButtonl = {"Submit","Submit","Submit","Submit","Submit"};// all look the same with different putposes
                JButton[] submitButtons = new JButton[submitButtonl.length];
                 for (int i = 0; i < submitButtonl.length; i++) {
                        submitButtons[i] = new JButton(submitButtonl[i]);
                        submitButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the buttons horizontally
                        submitButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
                        submitButtons[i].setPreferredSize(new Dimension(150, 40));
                    }
                    for (int i = 0; i < buttonLabels.length; i++) {
                        buttons[i] = new JButton(buttonLabels[i]);
                        buttons[i].setBorder(new EmptyBorder(10, 100, 10, 800));
                        buttons[i].setPreferredSize(new Dimension(2150, 1550));
                        buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
                        buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
                        panel.add(buttons[i]);
                    }
                        submitButton.setPreferredSize(new Dimension(150, 40));
                        cancelButton.setPreferredSize(new Dimension(150, 40));
                        submitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
                        cancelButton.setAlignmentY(Component.CENTER_ALIGNMENT);


                    buttons[0].addActionListener(o -> {
                        buttonpanel.removeAll();
                        buttonpanel.add(submitButtons[0]);
                        buttonpanel.add(cancelButton);
                        fpanel.removeAll();
                        fpanel.setLayout(new GridLayout(0, 1, 10, 10));// change it back after an item is changed
                        fpanel.add(newQuestions[0]);
                        fpanel.add(answerFields[1]); 
                        fpanel.add(buttonpanel);
                        submitButtons[0].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                fpanel.removeAll();
                                buttonpanel.removeAll();
                                fpanel.setLayout(new GridLayout(0, 1, 10, 10));
                                String newFirstName = answerFields[1].getText();
                                employee.setEmployeeFirstName(newFirstName);
                                result_messasge.setText("First Name has been changed");
                                fpanel.add(result_messasge);
                                okaybutton.setPreferredSize(new Dimension(80, 40));
                                buttonpanel.add(okaybutton);
                                fpanel.add(buttonpanel);
                                buttonpanel.revalidate();
                                buttonpanel.repaint();                                
                                fpanel.revalidate();
                                fpanel.repaint();

                            }
                        });                              
                                fpanel.revalidate();
                                fpanel.repaint();
                                Employeeframe.add(fpanel);
                                Employeeframe.setPreferredSize(new Dimension(650, 350));
                                Employeeframe.pack();
                                Employeeframe.setLocationRelativeTo(null);
                                Employeeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                Employeeframe.setVisible(true);
                    });

                    buttons[1].addActionListener(o -> {
                        buttonpanel.removeAll();
                        buttonpanel.add(submitButtons[1]);
                        buttonpanel.add(cancelButton);
                        fpanel.removeAll();
                        fpanel.setLayout(new GridLayout(0, 1, 10, 10));// change it back after an item is changed
                        fpanel.add(newQuestions[1]);
                        fpanel.add(answerFields[0]);
                        fpanel.add(buttonpanel);

                        submitButtons[1].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                fpanel.removeAll();
                                buttonpanel.removeAll();
                                fpanel.setLayout(new GridLayout(0, 1, 10, 10));
                                String newlastName = answerFields[0].getText();
                                employee.setEmployeeLastName(newlastName);
                                result_messasge.setText("Last Name has been changed");
                                fpanel.add(result_messasge);
                                okaybutton.setPreferredSize(new Dimension(80, 40));
                                buttonpanel.add(okaybutton);
                                fpanel.add(buttonpanel);
                                buttonpanel.revalidate();
                                buttonpanel.repaint();
                                fpanel.revalidate();
                                fpanel.repaint();
                                }
                        });
                                buttonpanel.revalidate();
                                buttonpanel.repaint();
                                fpanel.revalidate();
                                fpanel.repaint();
                                Employeeframe.add(fpanel);
                                Employeeframe.setPreferredSize(new Dimension(650, 350));
                                Employeeframe.pack();
                                Employeeframe.setLocationRelativeTo(null);
                                Employeeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                Employeeframe.setVisible(true);
                    });

                    buttons[2].addActionListener(o -> {
                        buttonpanel.removeAll();
                        buttonpanel.add(submitButtons[2]);
                        buttonpanel.add(cancelButton);
                        fpanel.removeAll();
                        fpanel.setLayout(new GridLayout(0, 1, 10, 10));// change it back after an item is changed
                        fpanel.add(newQuestions[2]);
                        fpanel.add(newanswerFields[0]);
                        fpanel.add(buttonpanel);

                        submitButtons[2].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                fpanel.removeAll();
                                buttonpanel.removeAll();
                                fpanel.setLayout(new GridLayout(0, 1, 10, 10));
                                int newAge = Integer.parseInt(newanswerFields[0].getText());
                                employee.setAge(newAge);
                                result_messasge.setText("Age has been Updated");
                                fpanel.add(result_messasge);
                                okaybutton.setPreferredSize(new Dimension(80, 40));
                                buttonpanel.add(okaybutton);
                                fpanel.add(buttonpanel);
                                buttonpanel.revalidate();
                                buttonpanel.repaint();
                                fpanel.revalidate();
                                fpanel.repaint();
                                }
                        });                   
                                buttonpanel.revalidate();
                                buttonpanel.repaint();   
                                fpanel.revalidate();
                                fpanel.repaint();
                                Employeeframe.add(fpanel);
                                Employeeframe.setPreferredSize(new Dimension(650, 350));
                                Employeeframe.pack();
                                Employeeframe.setLocationRelativeTo(null);
                                Employeeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                Employeeframe.setVisible(true);
                    });

                     buttons[3].addActionListener(o -> {
                        buttonpanel.removeAll();
                        buttonpanel.add(submitButtons[3]);
                        buttonpanel.add(cancelButton);
                        fpanel.removeAll();
                        fpanel.setLayout(new GridLayout(0, 1, 10, 10));// change it back after an item is changed
                        fpanel.add(newQuestions[3]);
                        fpanel.add(newanswerFields[1]);
                        fpanel.add(buttonpanel);

                        submitButtons[3].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                fpanel.removeAll();
                                buttonpanel.removeAll();
                                fpanel.setLayout(new GridLayout(0, 1, 10, 10));
                                float newwage = Float.parseFloat(newanswerFields[1].getText());
                                employee.setHourlyWage(newwage);
                                result_messasge.setText("Hourly Wage has been Updated");
                                fpanel.add(result_messasge);
                                okaybutton.setPreferredSize(new Dimension(80, 40));
                                buttonpanel.add(okaybutton);
                                fpanel.add(buttonpanel);
                                buttonpanel.revalidate();
                                buttonpanel.repaint();
                                fpanel.revalidate();
                                fpanel.repaint();
                            }
                        });
                                buttonpanel.revalidate();
                                buttonpanel.repaint();
                                fpanel.revalidate();
                                fpanel.repaint();
                                Employeeframe.add(fpanel);
                                Employeeframe.setPreferredSize(new Dimension(650, 350));
                                Employeeframe.pack();
                                Employeeframe.setLocationRelativeTo(null);
                                Employeeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                Employeeframe.setVisible(true);
                    });

                    buttons[4].addActionListener(o -> {
                        buttonpanel.removeAll();
                        buttonpanel.add(submitButtons[4]);
                        buttonpanel.add(cancelButton);
                        fpanel.removeAll();
                        fpanel.setLayout(new GridLayout(0, 1, 10, 10));// change it back after an item is changed
                        fpanel.add(newQuestions[3]);
                        fpanel.add(newanswerFields[2]);
                        fpanel.add(buttonpanel);

                        submitButtons[4].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                fpanel.removeAll();
                                buttonpanel.removeAll();
                                fpanel.setLayout(new GridLayout(0, 1, 10, 10));
                                float newHoursWorked = Float.parseFloat(newanswerFields[2].getText());
                                employee.setHoursWorked(newHoursWorked);
                                result_messasge.setText("Hours Worked has been Updated");
                                fpanel.add(result_messasge);
                                okaybutton.setPreferredSize(new Dimension(80, 40));
                                buttonpanel.add(okaybutton);
                                fpanel.add(buttonpanel);
                                fpanel.revalidate();
                                fpanel.repaint();
                                buttonpanel.revalidate();
                                buttonpanel.repaint();
                            }
                        });
                                buttonpanel.revalidate();
                                buttonpanel.repaint();
                                fpanel.revalidate();
                                fpanel.repaint();
                                Employeeframe.add(fpanel);
                                Employeeframe.setPreferredSize(new Dimension(650, 350));
                                Employeeframe.pack();
                                Employeeframe.setLocationRelativeTo(null);
                                Employeeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                Employeeframe.setVisible(true);
                    });

                    buttons[5].addActionListener(o -> {
                        try {
                            newframe.dispose();
                            initialMenu();
                        } catch (ParseException p) {
                            p.printStackTrace();
                        }
                    });

                    buttons[6].addActionListener(o -> {
                        frame.dispose();
                        newframe.dispose();
                        System.exit(0);
                    });

                    frame.revalidate();
                    frame.repaint();
                } else {
                    newpanel.removeAll();
                    newpanel.setSize(500, 300);
                    newpanel.setLayout(new BoxLayout(newpanel, BoxLayout.Y_AXIS));
                    notfound.setText("The Employee " + firstName + " " + lastName + " is not found");
                    newpanel.add(notfound);
                    tryAgainbutton.setAlignmentX(Component.CENTER_ALIGNMENT);//center the buttons after the textArea for a better look
                    cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    newpanel.add(tryAgainbutton);              
                    newpanel.add(cancelButton);
                    newframe.revalidate();
                    newframe.repaint();
                }
            }
        }
    });

    okaybutton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            newframe.dispose();
            Employeeframe.dispose();
        }
    });
    tryAgainbutton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            newframe.dispose();
            Employeeframe.dispose();
                try {
                    option6_4();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }            
        }
    });
    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            newframe.dispose();
            Employeeframe.dispose();
        }
    });

    newframe.add(newpanel);
    newframe.setPreferredSize(new Dimension(650, 350));
    newframe.pack();
    newframe.setLocationRelativeTo(null);
    newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newframe.setVisible(true);
}

    public void option6_5() throws ParseException {
    JFrame newframe = new JFrame("Deleting Employee");
    JPanel fpanel = new JPanel(new GridLayout(0, 2, 10, 10));
    JPanel newpanel = new JPanel(new GridLayout(0, 2, 10, 10));
    JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));// new panel for a better layout
    newframe.setSize(500, 300);

    String[] Labels = {"Enter the Employee last name: ", "Enter the Employee first name: "};
    JLabel[] questions = new JLabel[Labels.length];
    JTextField[] answerFields = new JTextField[questions.length];
    
    JButton submitButton = new JButton("Submit");
    JButton cancelButton = new JButton("Cancel");
    JButton okaybutton = new JButton("OK");
    JButton tryAgainbutton = new JButton("Try Again");
    JTextArea notfound = new JTextArea();

    for (int i = 0; i < questions.length; i++) {
        JLabel label = new JLabel(Labels[i]);
        newpanel.add(label);
        answerFields[i] = new JTextField();
        answerFields[i].setFont(new Font("Arial", Font.PLAIN, 20));
        newpanel.add(answerFields[i]);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        okaybutton.setFont(new Font("Arial", Font.PLAIN, 20));
        tryAgainbutton.setFont(new Font("Arial", Font.PLAIN, 20));
        notfound.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    buttonpanel.add(submitButton);
    buttonpanel.add(cancelButton);
    newpanel.add(buttonpanel);

    submitButton.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            String lastName = answerFields[0].getText();
            String firstName = answerFields[1].getText();
            newpanel.removeAll();
            boolean found = false;
        for(int i = 0; i < employees.size(); i++) {
            newpanel.setLayout(new BoxLayout(newpanel, BoxLayout.Y_AXIS));
            if(Objects.equals(lastName, employees.get(i).getEmployeeLastName()) && Objects.equals(firstName, employees.get(i).getEmployeeFirstName())) {
            employees.remove(i);
            buttonpanel.removeAll();
            found = true;
            notfound.setText("Employee has been deleted ");
            newpanel.add(notfound);
            buttonpanel.add(okaybutton);
            newpanel.add(buttonpanel);
            }if(!found){ 
            notfound.setText("Employee not found ");
            buttonpanel.removeAll();
            newpanel.add(notfound);
            buttonpanel.add(tryAgainbutton);
            buttonpanel.add(cancelButton);
            newpanel.add(buttonpanel);
             } 
            buttonpanel.revalidate();
            buttonpanel.repaint();
            newpanel.revalidate();
            newpanel.repaint(); 
     } }});
     okaybutton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            newframe.dispose();
                try {
                    option6_5();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }            
        }
    });
    tryAgainbutton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            newframe.dispose();
                try {
                    option6_5();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }            
        }
    });
    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            newframe.dispose();
        }
    });
    newframe.add(newpanel);
    newframe.setPreferredSize(new Dimension(650, 350));
    newframe.pack();
    newframe.setLocationRelativeTo(null);
    newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newframe.setVisible(true);
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