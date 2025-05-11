package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import myJDBC.myDB;


public class TicketGUI extends Form {

    private static int flightID;
    private static int userID;
    public TicketGUI(int flightID,int userID) {
        super("Ticket");
        TicketGUI.flightID = flightID;
        TicketGUI.userID = userID;
        setSize(700, 400);
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        String[] flightDetails = myDB.getFlightInfo (flightID);     //{from,to,date,A,B,Cseats,A,B,Creserved,airline}
        int ticketType = myDB.getTicketType (userID,flightID);
        if (flightDetails == null) {
            JOptionPane.showMessageDialog(this, "Flight not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JLabel ticketLabel = new JLabel("Ticket");
        ticketLabel.setForeground(CommonConstants.TEXT_COLOR);
        ticketLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        ticketLabel.setBounds(70, 0, 520, 100);
        ticketLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(ticketLabel);

        JLabel originLabel = new JLabel("Origin : ");
        originLabel.setForeground(CommonConstants.TEXT_COLOR);
        originLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        originLabel.setBounds(70, 100, 200, 30);
        add(originLabel);

        JTextField originTextField = new JTextField();
        originTextField.setBounds(150, 100, 150, 30);
        originTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        originTextField.setForeground(CommonConstants.TEXT_COLOR);
        originTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        originTextField.setEditable(false); // Make it non-editable
        originTextField.setText(flightDetails[0]); // Set the text to the origin
        add(originTextField);

        JLabel destinationLabel = new JLabel("Destination : ");
        destinationLabel.setForeground(CommonConstants.TEXT_COLOR);
        destinationLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        destinationLabel.setBounds(320, 100, 200, 30);
        add(destinationLabel);

        JTextField destinationTextField = new JTextField();
        destinationTextField.setBounds(450, 100, 150, 30);
        destinationTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        destinationTextField.setForeground(CommonConstants.TEXT_COLOR);
        destinationTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        destinationTextField.setEditable(false); // Make it non-editable
        destinationTextField.setText(flightDetails[1]); // Set the text to the destination
        add(destinationTextField);

        JLabel dateLabel = new JLabel("Take-off date : ");
        dateLabel.setForeground(CommonConstants.TEXT_COLOR);
        dateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        dateLabel.setBounds(70, 150, 200, 15);
        add(dateLabel);

        JTextField dateTextField = new JTextField();
        dateTextField.setBounds(220, 150, 150, 30);
        dateTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        dateTextField.setForeground(CommonConstants.TEXT_COLOR);
        dateTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        dateTextField.setEditable(false); // Make it non-editable
        dateTextField.setText(flightDetails[2]); // Set the text to the date
        add(dateTextField);

        JLabel airLineLabel = new JLabel("Airline : ");
        airLineLabel.setForeground(CommonConstants.TEXT_COLOR);
        airLineLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        airLineLabel.setBounds(400, 150, 200, 30);
        add(airLineLabel);

        JTextField airLineTextField = new JTextField();
        airLineTextField.setBounds(480, 150, 120, 30);
        airLineTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        airLineTextField.setForeground(CommonConstants.TEXT_COLOR);
        airLineTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        airLineTextField.setEditable(false); // Make it non-editable
        airLineTextField.setText(flightDetails[6]); // Set the text to the airline
        add(airLineTextField);

        JLabel classLabel = new JLabel("Class : ");
        classLabel.setForeground(CommonConstants.TEXT_COLOR);
        classLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        classLabel.setBounds(70, 200, 200, 30);
        add(classLabel);

        JRadioButton classARadioButton = new JRadioButton("Class A");
        classARadioButton.setForeground(CommonConstants.TEXT_COLOR);
        classARadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classARadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        classARadioButton.setBounds(200, 200, 100, 30);
        classARadioButton.setEnabled(false); // Disable the radio button
        add(classARadioButton);

        JRadioButton classBRadioButton = new JRadioButton("Class B");
        classBRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        classBRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classBRadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        classBRadioButton.setBounds(350, 200, 100, 30);
        classBRadioButton.setEnabled(false); // Disable the radio button
        add(classBRadioButton);

        JRadioButton classCRadioButton = new JRadioButton("Class C");
        classCRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        classCRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classCRadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        classCRadioButton.setBounds(500, 200, 100, 30);
        classCRadioButton.setEnabled(false); // Disable the radio button
        add(classCRadioButton);

        ButtonGroup classButtonGroup = new ButtonGroup();
        classButtonGroup.add(classARadioButton);
        classButtonGroup.add(classBRadioButton);
        classButtonGroup.add(classCRadioButton);

        switch (ticketType) {
            case 1:
                classARadioButton.setSelected(true);
                classARadioButton.setEnabled (true);
                break;
            case 2:
                classBRadioButton.setSelected(true);
                classBRadioButton.setEnabled (true);
                break;
            case 3:
                classCRadioButton.setSelected(true);
                classCRadioButton.setEnabled (true);
                break;
            default:
                break;
        }

        JLabel ticketPriceLabel = new JLabel("Price : ");
        ticketPriceLabel.setForeground(CommonConstants.TEXT_COLOR);
        ticketPriceLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        ticketPriceLabel.setBounds(70, 250, 200, 30);
        add(ticketPriceLabel);

        JTextField ticketPriceTextField = new JTextField();
        ticketPriceTextField.setBounds(150, 250, 150, 30);
        ticketPriceTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ticketPriceTextField.setForeground(CommonConstants.TEXT_COLOR);
        ticketPriceTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        ticketPriceTextField.setEditable(false); // Make it non-editable
        ticketPriceTextField.setText(String.valueOf (myDB.getTicketPrice (userID,flightID))); // Set the text to the price
        add(ticketPriceTextField);

        JLabel ticketNumberLabel = new JLabel("Ticket Number : ");
        ticketNumberLabel.setForeground(CommonConstants.TEXT_COLOR);
        ticketNumberLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        ticketNumberLabel.setBounds(340, 250, 200, 30);
        add(ticketNumberLabel);

        JTextField ticketNumberTextField = new JTextField();
        ticketNumberTextField.setBounds(500, 250, 100, 30);
        ticketNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ticketNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
        ticketNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        ticketNumberTextField.setEditable(false); // Make it non-editable
        switch (ticketType) {
            case 1:
                {
                    String number = String.valueOf (Integer.parseInt (flightDetails[3])+1);
                    ticketNumberTextField.setText(number);
                    break;
                }
            case 2:
                {
                    String number = String.valueOf (Integer.parseInt (flightDetails[4])+1);
                    ticketNumberTextField.setText(number);
                    break;
                }
            case 3:
                {
                    String number = String.valueOf (Integer.parseInt (flightDetails[5])+1);
                    ticketNumberTextField.setText(number);
                    break;
                }
            default:
                break;
        }

        add(ticketNumberTextField);

        JLabel flightProgramLabel = new JLabel("Click to see flight program");
        flightProgramLabel.setForeground(CommonConstants.TEXT_COLOR);
        flightProgramLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        flightProgramLabel.setBounds(130, 300, 400, 30);
        flightProgramLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightProgramLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        flightProgramLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new DesignFlightProgram().setVisible(true);
            }
        });
        add(flightProgramLabel);

        JLabel backButton = new JLabel("Back");
        backButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(CommonConstants.TEXT_COLOR);
        backButton.setBounds(20, 20, 100, 30);
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                navigateBack();
            }
        });
        add(backButton);


        JButton saveButton = new JButton("Save Ticket");
        saveButton.setBounds(480, 20, 200, 50);
        saveButton.setForeground(CommonConstants.SECONDARY_COLOR);
        saveButton.setBackground(CommonConstants.TEXT_COLOR);
        saveButton.setFont(new Font("Dialog", Font.BOLD, 25));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the bounds of the TicketGUI
                    Rectangle screenRect = TicketGUI.this.getBounds();
                    screenRect.setLocation(TicketGUI.this.getLocationOnScreen());

                    // Capture the screen area of the TicketGUI
                    BufferedImage capture = new Robot().createScreenCapture(screenRect);

                    // Save the captured image to a file
                    File outputFile = new File("ticket.png");
                    ImageIO.write(capture, "png", outputFile);

                    JOptionPane.showMessageDialog(TicketGUI.this, "Ticket saved as ticket.png");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TicketGUI.this, "Failed to save the ticket.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(saveButton);
    }

}