package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AgentGUI extends Form {

    public AgentGUI() {

        super("Agent");
        addGuiComponents();

    }

    private void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel agentLabel = new JLabel("Agent GUI");
        agentLabel.setForeground(CommonConstants.TEXT_COLOR);
        agentLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        agentLabel.setBounds(0, 25, 520, 100);
        agentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(agentLabel);

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

        JButton GenerateReportButton = new JButton("Generate Report");
        GenerateReportButton.setFont(new Font("Dialog", Font.BOLD, 18));
        GenerateReportButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GenerateReportButton.setBackground(CommonConstants.TEXT_COLOR);
        GenerateReportButton.setForeground(CommonConstants.PRIMARY_COLOR);
        GenerateReportButton.setBounds(43,500,420,60);
        add(GenerateReportButton);
        GenerateReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new GenerateReport().setVisible(true);
            }
        });
    
    }
}
