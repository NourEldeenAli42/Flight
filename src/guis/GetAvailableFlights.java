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
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel GAFLapel = new JLabel("Get Available Flights");
        GAFLapel.setForeground(CommonConstants.TEXT_COLOR);
        GAFLapel.setFont(new Font("Dialog", Font.BOLD, 24));
        GAFLapel.setHorizontalAlignment(SwingConstants.CENTER);
        GAFLapel.setBounds(130,30,240,50);
        add(GAFLapel);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(160,550,200,50);
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


        JTable flights = new JTable();
        flights.setBounds(43,130,420,300);
        flights.setForeground(CommonConstants.TEXT_COLOR);
        flights.setBackground(CommonConstants.PRIMARY_COLOR);
        flights.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(flights);

        JButton getAvailableFlightsButton = new JButton("Get Available Flights");
        getAvailableFlightsButton.setBounds(110, 480, 300, 60);
        getAvailableFlightsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        getAvailableFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        getAvailableFlightsButton.setFont(new Font("Dialog", Font.BOLD, 18));
        getAvailableFlightsButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                flights.setModel (myDB.getAvailableFlight ());
            }
        });
        add(getAvailableFlightsButton);

    }

}
