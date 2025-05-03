package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CreatingNewBooking extends Form {
    public CreatingNewBooking() {
        super("Create New Booking");
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel CNBLapel = new JLabel("Create New Booking");
        CNBLapel.setForeground(CommonConstants.TEXT_COLOR);
        CNBLapel.setFont(new Font("Dialog", Font.BOLD, 25));
        CNBLapel.setHorizontalAlignment(SwingConstants.CENTER);
        CNBLapel.setBounds(130,30,240,50);
        add(CNBLapel);

        JLabel ClientUsernameLapel = new JLabel("Client username : ");
        ClientUsernameLapel.setForeground(CommonConstants.TEXT_COLOR);
        ClientUsernameLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        ClientUsernameLapel.setBounds(30,-90,400,400);
        add(ClientUsernameLapel);

        JTextField ClientUsernameTextField = new JTextField();
        ClientUsernameTextField.setBounds(43,130,420,30);
        ClientUsernameTextField.setForeground(CommonConstants.TEXT_COLOR);
        ClientUsernameTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ClientUsernameTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(ClientUsernameTextField);

        JLabel ClientPasswordLapel = new JLabel("Client password : ");
        ClientPasswordLapel.setForeground(CommonConstants.TEXT_COLOR);
        ClientPasswordLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        ClientPasswordLapel.setBounds(30,0,400,400);
        add(ClientPasswordLapel);

        JPasswordField ClientPasswordTextField = new JPasswordField();
        ClientPasswordTextField.setBounds(43,220,420,30);
        ClientPasswordTextField.setForeground(CommonConstants.TEXT_COLOR);
        ClientPasswordTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ClientPasswordTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(ClientPasswordTextField);

        JLabel FlightNumberLapel = new JLabel("Flight Number : ");
        FlightNumberLapel.setForeground(CommonConstants.TEXT_COLOR);
        FlightNumberLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        FlightNumberLapel.setBounds(30,100,400,400);
        add(FlightNumberLapel);

        JTextField FlightNumberTextField = new JTextField();
        FlightNumberTextField.setBounds(43,330,420,30);
        FlightNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
        FlightNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        FlightNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(FlightNumberTextField);
        
        JLabel TotalPaidLapel = new JLabel("Total Paid : ");
        TotalPaidLapel.setForeground(CommonConstants.TEXT_COLOR);
        TotalPaidLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        TotalPaidLapel.setBounds(30,200,400,400);
        add(TotalPaidLapel);

        JTextField TotalPaidTextField = new JTextField();
        TotalPaidTextField.setBounds(43,430,420,30);
        TotalPaidTextField.setForeground(CommonConstants.TEXT_COLOR);
        TotalPaidTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        TotalPaidTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(TotalPaidTextField);


        JButton createBookingButton = new JButton("Create Booking");
        createBookingButton.setBounds(250, 500, 200, 60);
        createBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        createBookingButton.setFont(new Font("Dialog", Font.BOLD, 18));
        createBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(createBookingButton);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(43, 500, 200, 60);
        BackButton.setForeground(CommonConstants.PRIMARY_COLOR);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setFont(new Font("Dialog", Font.BOLD, 18));
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new AgentGUI().setVisible(true);
            }
        });
        add(BackButton);
    }
    
}
