package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ModifyingBooking extends Form {
    public ModifyingBooking() {
        super("Modify Booking");
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel MBLapel = new JLabel("Modify Booking");
        MBLapel.setForeground(CommonConstants.TEXT_COLOR);
        MBLapel.setFont(new Font("Dialog", Font.BOLD, 25));
        MBLapel.setHorizontalAlignment(SwingConstants.CENTER);
        MBLapel.setBounds(130,30,240,50);
        add(MBLapel);

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
        
        JLabel ANFLapel = new JLabel("Assign New Flight : ");
        ANFLapel.setForeground(CommonConstants.TEXT_COLOR);
        ANFLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        ANFLapel.setBounds(30,200,400,400);
        add(ANFLapel);

        JTextField ANFTextField = new JTextField();
        ANFTextField.setBounds(43,430,420,30);
        ANFTextField.setForeground(CommonConstants.TEXT_COLOR);
        ANFTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ANFTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(ANFTextField);


        JButton CheckUserNameButton = new JButton("Check User Name");
        CheckUserNameButton.setBounds(250, 500, 200, 60);
        CheckUserNameButton.setForeground(CommonConstants.PRIMARY_COLOR);
        CheckUserNameButton.setBackground(CommonConstants.TEXT_COLOR);
        CheckUserNameButton.setFont(new Font("Dialog", Font.BOLD, 18));
        CheckUserNameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(CheckUserNameButton);

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

    

