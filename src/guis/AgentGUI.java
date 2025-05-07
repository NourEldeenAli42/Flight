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

    public void addGuiComponents() {
        
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel agentLabel = new JLabel("Agent GUI");
        agentLabel.setForeground(CommonConstants.TEXT_COLOR);
        agentLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        agentLabel.setBounds(0, 25, 520, 100);
        agentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(agentLabel);

        JButton createNewFlightButton = new JButton("Create New Flight");
        createNewFlightButton.setFont(new Font("Dialog", Font.BOLD, 18));
        createNewFlightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createNewFlightButton.setBackground(CommonConstants.TEXT_COLOR);
        createNewFlightButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createNewFlightButton.setBounds(43,200,420,60);
        add(createNewFlightButton);
        createNewFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new CreateNewFlight().setVisible(true);
            }
        });

        JButton modifyExistingFlightButton = new JButton("Modify Existing Flight");
        modifyExistingFlightButton.setFont(new Font("Dialog", Font.BOLD, 18));
        modifyExistingFlightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modifyExistingFlightButton.setBackground(CommonConstants.TEXT_COLOR);
        modifyExistingFlightButton.setForeground(CommonConstants.PRIMARY_COLOR);
        modifyExistingFlightButton.setBounds(43,300,420,60);
        add(modifyExistingFlightButton);
        modifyExistingFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new ModifyingFlightGUI().setVisible(true);
            }
        });


        JButton generateReportOfFlightsButton = new JButton("Generate Report Of Flights");
        generateReportOfFlightsButton.setFont(new Font("Dialog", Font.BOLD, 18));
        generateReportOfFlightsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generateReportOfFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        generateReportOfFlightsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        generateReportOfFlightsButton.setBounds(43,400,420,60);
        add(generateReportOfFlightsButton);
        generateReportOfFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new GenerateReportGUI().setVisible(true);
            }
        });

        JButton GenerateReportButton = new JButton("Modify Client Booking");
        GenerateReportButton.setFont(new Font("Dialog", Font.BOLD, 18));
        GenerateReportButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GenerateReportButton.setBackground(CommonConstants.TEXT_COLOR);
        GenerateReportButton.setForeground(CommonConstants.PRIMARY_COLOR);
        GenerateReportButton.setBounds(43,500,420,60);
        add(GenerateReportButton);
        GenerateReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new ModifyingBooking().setVisible(true);
            }
        });

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

        JRadioButton agentProfile = new JRadioButton ();
        agentProfile.setBounds (10, 10, 60, 50);
        agentProfile.setIcon (new ImageIcon (CommonConstants.USER_ICON_PATH));
        agentProfile.setBackground (CommonConstants.SECONDARY_COLOR);
        agentProfile.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        agentProfile.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ();
                new ProfileGUI ().setVisible (true);
            }
        });
        add (agentProfile);
    
    }
}
