package guis;

import components.CommonConstants;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import myJDBC.myDB;

public class PassengerDetailsGUI extends Form {

    private static int flightID;

    public PassengerDetailsGUI(int flightID) {
        super("Passenger Details");
        PassengerDetailsGUI.flightID = flightID;
        addGuiComponents();
    }
    public PassengerDetailsGUI() {
        super("Passenger Details");
        addGuiComponents();
    }

    public void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel passengerDetailsLabel = new JLabel("Passenger Details");
        passengerDetailsLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        passengerDetailsLabel.setForeground(CommonConstants.TEXT_COLOR);
        passengerDetailsLabel.setBounds(0, 25, 520, 100); 
        passengerDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(passengerDetailsLabel);

        JLabel passportNumGUI = new JLabel ("Passport Number");
        passportNumGUI.setBounds (30, 0, 400, 400);
        passportNumGUI.setForeground (CommonConstants.TEXT_COLOR);
        passportNumGUI.setFont (new Font ("Dialog", Font.BOLD, 23));
        add (passportNumGUI);

        JTextField passportNumTextField = new JTextField ();
        passportNumTextField.setBounds (43, 230, 420, 50);
        passportNumTextField.setForeground (CommonConstants.TEXT_COLOR);
        passportNumTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        passportNumTextField.setFont (new Font ("Dialog", Font.PLAIN, 24));
        passportNumTextField.setCursor (Cursor.getPredefinedCursor (Cursor.TEXT_CURSOR));
        passportNumTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (passportNumTextField.getText().length() >= 10) {
                    e.consume();
                }
                if (e.getKeyChar () < '0' || e.getKeyChar () > '9') {
                    e.consume ();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add (passportNumTextField);


        JLabel dateOfBirthLabel = new JLabel ("Date of Birth");
        dateOfBirthLabel.setBounds (30, 160, 400, 400);
        dateOfBirthLabel.setForeground (CommonConstants.TEXT_COLOR);
        dateOfBirthLabel.setFont (new Font ("Dialog", Font.BOLD, 23));
        add (dateOfBirthLabel);

        JDateChooser flightDateChooser = new JDateChooser();
        flightDateChooser.getDateEditor().getUiComponent().setEnabled(false);
        flightDateChooser.setBounds(43, 385, 420, 50); 
        flightDateChooser.setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getCalendarButton().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getDateEditor().getUiComponent().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getCalendarButton().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getDateEditor().getUiComponent().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getJCalendar().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getJCalendar().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getJCalendar().getDayChooser().getDayPanel().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getJCalendar().getDayChooser().getDayPanel().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightDateChooser.setDateFormatString("yyyy-MM-dd");
        add(flightDateChooser);

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

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Dialog", Font.BOLD, 25));
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(CommonConstants.TEXT_COLOR);
        confirmButton.setForeground(CommonConstants.SECONDARY_COLOR);
        confirmButton.setBounds(75, 500, 350, 60); 
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int passportNum = Integer.parseInt (passportNumTextField.getText());
                Date dateOfBirth =  flightDateChooser.getDate ();

                if (passportNumTextField.getText ().isEmpty() || ((JTextField) flightDateChooser.getDateEditor ().getUiComponent ()).getText ().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Handle the confirmation logic here
                    myDB.setPassengerData (passportNum,dateOfBirth);
                    dispose();
                    AddPaymentGUI temp = new AddPaymentGUI();
                    temp.setFlightid (flightID);
                    temp.refresh ();
                    temp.setVisible (true);
                }


            }
        });
        add(confirmButton);


    }
    
}
