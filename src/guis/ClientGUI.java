package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClientGUI extends Form {

    public ClientGUI() {

        super("Client");
        addGuiComponents();

    }

    private void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel clientLabel = new JLabel("Client GUI");
        clientLabel.setForeground(CommonConstants.TEXT_COLOR);
        clientLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        clientLabel.setBounds(0, 25, 520, 100);
        clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(clientLabel);

        JButton createBookingButton = new JButton("Create Booking");
        createBookingButton.setFont(new Font("Dialog", Font.BOLD, 18));
        createBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        createBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createBookingButton.setBounds(43,200,420,60);
        add(createBookingButton);
        createBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new CreatingNewBooking().setVisible(true);
            }
        });

        JButton modifyBookingButton = new JButton("Modify Booking");
        modifyBookingButton.setFont(new Font("Dialog", Font.BOLD, 18));
        modifyBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modifyBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        modifyBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        modifyBookingButton.setBounds(43,300,420,60);
        add(modifyBookingButton);
        modifyBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new ModifyingBooking().setVisible(true);
            }
        });


        JButton GetAvailableFlightsButton = new JButton("Get Available Flights");
        GetAvailableFlightsButton.setFont(new Font("Dialog", Font.BOLD, 18));
        GetAvailableFlightsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GetAvailableFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        GetAvailableFlightsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        GetAvailableFlightsButton.setBounds(43,400,420,60);
        add(GetAvailableFlightsButton);
        GetAvailableFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new GetAvailableFlights().setVisible(true);
            }
        });

        JButton AddPaymentMethodButton = new JButton("Add Payment Method");
        AddPaymentMethodButton.setFont(new Font("Dialog", Font.BOLD, 18));
        AddPaymentMethodButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        AddPaymentMethodButton.setBackground(CommonConstants.TEXT_COLOR);
        AddPaymentMethodButton.setForeground(CommonConstants.PRIMARY_COLOR);
        AddPaymentMethodButton.setBounds(43,500,420,60);
        add(AddPaymentMethodButton);
        

        JLabel backButton = new JLabel("Back");
        backButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(CommonConstants.TEXT_COLOR);
        backButton.setBounds(420, 20, 100, 30);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateBack();

            }
        });
        
        add(backButton);

        JRadioButton clientProfile = new JRadioButton ();
        clientProfile.setBounds (10, 10, 60, 50);
        clientProfile.setIcon (new ImageIcon (CommonConstants.USER_ICON_PATH));
        clientProfile.setBackground (CommonConstants.SECONDARY_COLOR);
        clientProfile.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        clientProfile.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ();
                new ProfileGUI ().setVisible (true);
            }
        });
        add (clientProfile);
    }
}
