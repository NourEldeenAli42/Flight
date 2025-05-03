package guis;

import components.CommonConstants;
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
        BackButton.setBounds(80,550,350,50);
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
