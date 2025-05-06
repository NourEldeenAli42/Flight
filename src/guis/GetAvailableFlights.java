package guis;

import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GetAvailableFlights extends Form {
    public GetAvailableFlights() {
        super("Get Available Flights");
        setSize(1200, 680);
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel GAFLapel = new JLabel("Get Available Flights");
        GAFLapel.setForeground(CommonConstants.TEXT_COLOR);
        GAFLapel.setFont(new Font("Dialog", Font.BOLD, 30));
        GAFLapel.setHorizontalAlignment(SwingConstants.CENTER);
        GAFLapel.setBounds(400, 30, 400, 60); // Adjusted bounds
        add(GAFLapel);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(475, 600, 250, 60); // Adjusted bounds
        BackButton.setForeground(CommonConstants.PRIMARY_COLOR);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setFont(new Font("Dialog", Font.BOLD, 20));
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                navigateBack();
            }
        });
        add(BackButton);

        JTable flights = new JTable();
        flights.setBounds(50, 150, 1100, 400); // Increased width to fit columns
        flights.setForeground(CommonConstants.TEXT_COLOR);
        flights.setBackground(CommonConstants.PRIMARY_COLOR);
        flights.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(flights);

        JButton getAvailableFlightsButton = new JButton("Get Available Flights");
        getAvailableFlightsButton.setBounds(425, 520, 350, 70); // Adjusted bounds
        getAvailableFlightsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        getAvailableFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        getAvailableFlightsButton.setFont(new Font("Dialog", Font.BOLD, 20));
        getAvailableFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flights.setModel(myDB.getAvailableFlight());
            }
        });
        add(getAvailableFlightsButton);
    }
}
