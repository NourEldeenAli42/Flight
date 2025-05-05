package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminGUI extends Form {
    public AdminGUI() {
        super("Admin");
        addGuiComponents();
    }

    private void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JTabbedPane tabbedPane = new JTabbedPane ();
        tabbedPane.setBounds (0, 0, 1000, 800);
        add (tabbedPane);

        JPanel adminPanel = new JPanel ();
        tabbedPane.addTab ("Admin", adminPanel);
        JPanel agentPanel = new JPanel ();
        tabbedPane.addTab ("Agent", agentPanel);
        JPanel CustomerPanel = new JPanel ();
        tabbedPane.addTab ("Agent", CustomerPanel);

        adminPanel.setLayout (null);
        agentPanel.setLayout (null);
        CustomerPanel.setLayout (null);

        adminPanel.setBackground(CommonConstants.SECONDARY_COLOR);
        agentPanel.setBackground(CommonConstants.SECONDARY_COLOR);
        CustomerPanel.setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel adminLabel = new JLabel("Admin GUI");
        adminLabel.setForeground(CommonConstants.TEXT_COLOR);
        adminLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        adminLabel.setBounds(0, 25, 520, 100);
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminPanel.add(adminLabel);

        JButton ActivateAccountButton = new JButton("Activate Account");
        ActivateAccountButton.setFont(new Font("Dialog", Font.BOLD, 18));
        ActivateAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ActivateAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        ActivateAccountButton.setForeground(CommonConstants.PRIMARY_COLOR);
        ActivateAccountButton.setBounds(43, 200, 420, 60);
        ActivateAccountButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ();
                new ActivateAccountGUI ().setVisible (true);
            }
        });
        adminPanel.add(ActivateAccountButton);
       
        JButton CreateNewAccountButton = new JButton("Create New Account"); 
        CreateNewAccountButton.setFont(new Font("Dialog", Font.BOLD, 18));
        CreateNewAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CreateNewAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        CreateNewAccountButton.setForeground(CommonConstants.PRIMARY_COLOR);
        CreateNewAccountButton.setBounds(43, 300, 420, 60);
        CreateNewAccountButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ();
                new RegisterFormGUI ().setVisible (true);
            }
        });
        adminPanel.add(CreateNewAccountButton);

        JButton EditExistingAccountButton = new JButton("Edit Existing Account");
        EditExistingAccountButton.setFont(new Font("Dialog", Font.BOLD, 18));
        EditExistingAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EditExistingAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        EditExistingAccountButton.setForeground(CommonConstants.PRIMARY_COLOR);
        EditExistingAccountButton.setBounds(43, 400, 420, 60);
        adminPanel.add(EditExistingAccountButton);



        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel agentLabel = new JLabel("Agent GUI");
        agentLabel.setForeground(CommonConstants.TEXT_COLOR);
        agentLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        agentLabel.setBounds(0, 25, 520, 100);
        agentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        agentPanel.add (agentLabel);

        JButton createBookingButton = new JButton("Create Booking");
        createBookingButton.setFont(new Font("Dialog", Font.BOLD, 18));
        createBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        createBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createBookingButton.setBounds(43,200,420,60);
        agentPanel.add(createBookingButton);
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
        agentPanel.add(modifyBookingButton);
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
        agentPanel.add(GetAvailableFlightsButton);
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
        agentPanel.add(GenerateReportButton);
        GenerateReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new GenerateReport().setVisible(true);
            }
        });

    }
}
