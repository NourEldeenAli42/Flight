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


        JButton getAvailableFlightsButton = new JButton("Get Available Flights");
        getAvailableFlightsButton.setFont(new Font("Dialog", Font.BOLD, 18));
        getAvailableFlightsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getAvailableFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        getAvailableFlightsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        getAvailableFlightsButton.setBounds(43,400,420,60);
        add(getAvailableFlightsButton);
        getAvailableFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new GetAvailableFlights().setVisible(true);
            }
        });

        JButton show_cancelFlight = new JButton("Show | Cancel Flight");
        show_cancelFlight.setFont(new Font("Dialog", Font.BOLD, 18));
        show_cancelFlight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        show_cancelFlight.setBackground(CommonConstants.TEXT_COLOR);
        show_cancelFlight.setForeground(CommonConstants.PRIMARY_COLOR);
        show_cancelFlight.setBounds(43,500,420,60);
        show_cancelFlight.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ();
                new CancelFlightGUI ().setVisible (true);
            }
        });
        add(show_cancelFlight);
    }
}
