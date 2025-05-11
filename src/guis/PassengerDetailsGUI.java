package guis;

import components.CommonConstants;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import myJDBC.myDB;

public class PassengerDetailsGUI extends Form {

    private static int flightID;

    public PassengerDetailsGUI(int flightID) {
        super("Passenger Details");
        PassengerDetailsGUI.flightID = flightID;
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
        add (passportNumTextField);


        JLabel dateOfBirthLabel = new JLabel ("Date of Birth");
        dateOfBirthLabel.setBounds (30, 210, 400, 400);
        dateOfBirthLabel.setForeground (CommonConstants.TEXT_COLOR);
        dateOfBirthLabel.setFont (new Font ("Dialog", Font.BOLD, 23));
        add (dateOfBirthLabel);

        JDateChooser dateOfBirthChooser = new JDateChooser();
        dateOfBirthChooser.setBounds(43, 435, 420, 50);
        dateOfBirthChooser.setBackground(CommonConstants.PRIMARY_COLOR);
        dateOfBirthChooser.getCalendarButton().setBackground(CommonConstants.PRIMARY_COLOR);
        dateOfBirthChooser.getDateEditor().getUiComponent().setBackground(CommonConstants.PRIMARY_COLOR);
        dateOfBirthChooser.setForeground(CommonConstants.TEXT_COLOR);
        dateOfBirthChooser.getCalendarButton().setForeground(CommonConstants.TEXT_COLOR);
        dateOfBirthChooser.getDateEditor().getUiComponent().setForeground(CommonConstants.TEXT_COLOR);
        dateOfBirthChooser.getJCalendar().setBackground(CommonConstants.PRIMARY_COLOR);
        dateOfBirthChooser.getJCalendar().setForeground(CommonConstants.TEXT_COLOR);
        dateOfBirthChooser.getJCalendar().getDayChooser().getDayPanel().setBackground(CommonConstants.PRIMARY_COLOR);
        dateOfBirthChooser.getJCalendar().getDayChooser().getDayPanel().setForeground(CommonConstants.TEXT_COLOR);
        dateOfBirthChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
        dateOfBirthChooser.setDateFormatString("yyyy-MM-dd");
        add(dateOfBirthChooser);

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
        confirmButton.setBounds(200, 500, 500, 60); 
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passportNum = passportNumTextField.getText();
                String dateOfBirth = ((JTextField) dateOfBirthChooser.getDateEditor().getUiComponent()).getText();

                if (passportNum.isEmpty() || dateOfBirth.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int passportNumber = Integer.parseInt (passportNumTextField.getText ());
                    // Handle the confirmation logic here
                    JOptionPane.showMessageDialog(null, "Passenger details confirmed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    myDB.setPassengerData (passportNumber,dateOfBirthChooser.getDate ());
                }

                dispose();
                new AddPaymentGUI(flightID).setVisible(true);

            }
        });
        add(confirmButton);


    }
    
}
