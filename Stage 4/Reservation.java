import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class Reservation {
    private Date checkInDate;
    private Date checkOutDate;
    private Guest customer;
    private Room board;
    private int numPeople;
    private int nights;
    private JFrame frame;
    
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
        calculateNights();
    }

    public void setCheckOutDate() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Enter Check-out Date");
        dialog.setLayout(new GridLayout(3, 2));

        JTextField checkOutField = new JTextField(20);
        dialog.add(new JLabel("Enter check-out Date in MM-dd-yyyy:"));
        dialog.add(checkOutField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkOut = checkOutField.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                try {
                    checkOutDate = formatter.parse(checkOut);
                    dialog.dispose();
                    calculateNights();
                } catch (ParseException parseException) {
                    JOptionPane.showMessageDialog(dialog, "Invalid date format. Please enter in MM-dd-yyyy.");
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOutDate = null;
                dialog.dispose();
            }
        });

        dialog.add(okButton);
        dialog.add(cancelButton);

        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
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
    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
        calculateCheckOutDate();
    }

    public void setNights() {
        frame.removeAll();

        JTextField nightsField = new JTextField(2);
        frame.add(new JLabel("Enter the number of nights to stay (1-21):"));
        frame.add(nightsField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nights = Integer.parseInt(nightsField.getText());
                    frame.dispose();
                    calculateCheckOutDate();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nights = 0;
                frame.dispose();
            }
        });

        frame.add(okButton);
        frame.add(cancelButton);

        frame.revalidate();
        frame.repaint();
    }

    public void calculateNights() {
        nights = (int) ((checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public void calculateCheckOutDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        cal.add(Calendar.DATE, nights);
        checkOutDate = cal.getTime();
    }

    public void setCheckInDate() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Enter Check-in Date");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
            JTextField checkInField = new JTextField(20);
    
            panel.add(new JLabel("Enter check-in Date in MM-dd-yyyy:"));
            panel.add(checkInField);
    
            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Cancel");
    
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
    
            okButton.addActionListener(e -> {
                String checkIn = checkInField.getText();
    
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                try {
                    checkInDate = formatter.parse(checkIn);
                    frame.dispose();
    
                    JFrame newFrame = new JFrame("");
                    JPanel newpanel = new JPanel();
                    newpanel.setLayout(new BoxLayout(newpanel, BoxLayout.Y_AXIS));
    
                    JButton okayButton = new JButton("OK");

                   okayButton.setBounds(10, 40, 80, 25);
    
                    newpanel.add(new JLabel("Reservation has been added."));
                    newpanel.getComponent(0).setBounds(10, 10, 650, 25); 
                    newpanel.add(okayButton);
    
                    okayButton.addActionListener(event -> {// clsoe the tab only when the ok button is pressed
                        newFrame.dispose();
                    });
    
                    newFrame.add(newpanel);
                    newFrame.setSize(500, 150);
                    newFrame.setLocationRelativeTo(null);
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.setVisible(true);
    
                } catch (ParseException parseException) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please enter in MM-dd-yyyy.");
                }
            });
    
            cancelButton.addActionListener(e -> {
                checkInDate = null;
                frame.dispose();
            });
    
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
    
            panel.add(buttonPanel);
    
            frame.add(panel);
    
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    

    public String printReservation() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        StringBuilder formattedString = new StringBuilder("|" + "\t");
        
        formattedString.append(String.format("%-20s", customer.getFirstName() + " " + customer.getLastName()));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", formatter.format(checkInDate)));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", formatter.format(checkOutDate)));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", nights));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", board.getRoomNumber()));
        formattedString.append("|" + "\t");
        formattedString.append(String.format("%-20s", numPeople));
        formattedString.append("|\n");
    
        return formattedString.toString();
    }
}
