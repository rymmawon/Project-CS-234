import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.List;
import java.util.Scanner;

public class Room {

    private int roomNumber;
    private String bedSize;
    private int bedNumber;
    private int maxGuests;
    private double roomPrice;
    private String roomStatus;

    JFrame frame = new JFrame("Room");
    JPanel panel = new JPanel(new FlowLayout());
    JTextArea outputTextArea = new JTextArea();

    JTextField roomNumField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton okayButton = new JButton("Okay");
        JButton tryAgainButton = new JButton("Try Again");

        

    public Room() {
        roomNumber = 0;
        bedSize = "";
        maxGuests = 0;
        roomPrice = 0;
        roomStatus = "";

        frame.setSize(500, 300);

        
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
        panel.removeAll();
        JLabel bedLabel = new JLabel("Enter the bed size Queen or King: ");
        JTextField bedSizTextField = new JTextField(10);

        panel.add(bedLabel);
        panel.add(bedSizTextField);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newbedSize = bedSizTextField.getText();
                if (!Objects.equals(bedSize, "Queen") && !Objects.equals(bedSize, "King")) {
                    outputTextArea.setText("Wrong Bed type. Try again.");
                    panel.add(outputTextArea);
                    panel.add(tryAgainButton);
                } else {
                    setBedSize(newbedSize);
                    outputTextArea.setText("Okay " + newbedSize + " is now set.");
                    panel.add(outputTextArea);
                    panel.add(okayButton);
                }

                panel.revalidate();
                panel.repaint();
            }
        });

        okayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        tryAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                setBedSize();
            }
        });
        frame.add(panel);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
    panel.removeAll();
    JLabel bedLabel =new JLabel("Enter the bed size: ");
    JLabel maxGuestLabel =new JLabel("Enter the maximum guests:");
    JLabel roomPriceLabel =new JLabel("Enter the price per night: ");
    JTextField bedSizTextField = new JTextField();
    JTextField maxGuesTextField = new JTextField();
    JTextField roomPriceTextField = new JTextField();

    panel.add(bedLabel);
    panel.add(bedSizTextField);
    panel.add(maxGuestLabel);
    panel.add(maxGuesTextField);
    panel.add(roomPriceLabel);
    panel.add(roomPriceTextField);

    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String bedSize =bedSizTextField.getText();
            String maxGuest = maxGuesTextField.getText();
            int roomPrice = Integer.parseInt(roomPriceTextField.getText());

        }});

    }

    public String printRoom() {
        StringBuilder formattedString = new StringBuilder("|" + "\t");
        formattedString.append(String.format("%-20s", "Room Number: " + roomNumber));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", "Bed Size: " + bedSize));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", "Bed Number: " + bedNumber));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", "Max Guests: " + maxGuests));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", "Room Price: $" + roomPrice));
        formattedString.append("|\n");
        return formattedString.toString();
    }

    public void changeRoomPrice() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the price for the room in $: ");
        roomPrice = in.nextDouble();
    }
    
}