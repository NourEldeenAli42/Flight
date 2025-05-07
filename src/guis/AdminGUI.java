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
        tabbedPane.setBounds (0, 0, 520, 680);
        tabbedPane.setBackground(CommonConstants.SECONDARY_COLOR);
        tabbedPane.setForeground(CommonConstants.TEXT_COLOR);
        add (tabbedPane);

        JPanel adminPanel = new JPanel ();
        adminPanel.setBackground (CommonConstants.SECONDARY_COLOR);
        tabbedPane.addTab ("Admin", adminPanel);
        JPanel agentPanel = new JPanel ();
        agentPanel.setBackground (CommonConstants.SECONDARY_COLOR);
        tabbedPane.addTab ("Agent", agentPanel);
        JPanel clientPanel = new JPanel ();
        clientPanel.setBackground (CommonConstants.SECONDARY_COLOR);
        tabbedPane.addTab ("Client", clientPanel);



        adminPanel.setLayout (null);
        agentPanel.setLayout (null);
        clientPanel.setLayout (null);


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


        //TODO: make new GUI for editing existing account MAHDI
        JButton EditExistingAccountButton = new JButton("Edit Existing Account");
        EditExistingAccountButton.setFont(new Font("Dialog", Font.BOLD, 18));
        EditExistingAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EditExistingAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        EditExistingAccountButton.setForeground(CommonConstants.PRIMARY_COLOR);
        EditExistingAccountButton.setBounds(43, 400, 420, 60);
        EditExistingAccountButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ();
                new EditExistingAccount ().setVisible (true);
            }
        });
        adminPanel.add(EditExistingAccountButton);

        JLabel agentLabel = new JLabel("Agent GUI");
        agentLabel.setForeground(CommonConstants.TEXT_COLOR);
        agentLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        agentLabel.setBounds(0, 25, 520, 100);
        agentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        agentPanel.add(agentLabel);

        JButton createNewFlightButton = new JButton("Create New Flight");
        createNewFlightButton.setFont(new Font("Dialog", Font.BOLD, 18));
        createNewFlightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createNewFlightButton.setBackground(CommonConstants.TEXT_COLOR);
        createNewFlightButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createNewFlightButton.setBounds(43,200,420,60);
        agentPanel.add(createNewFlightButton);
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
        agentPanel.add(modifyExistingFlightButton);
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
        agentPanel.add(generateReportOfFlightsButton);
        generateReportOfFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new GenerateReportGUI().setVisible(true);
            }
        });

        JButton ModifyClientBookingButton = new JButton("Modify Client Booking");
        ModifyClientBookingButton.setFont(new Font("Dialog", Font.BOLD, 18));
        ModifyClientBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ModifyClientBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        ModifyClientBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        ModifyClientBookingButton.setBounds(43,500,420,60);
        agentPanel.add(ModifyClientBookingButton);
        ModifyClientBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new ModifyingBooking().setVisible(true);
            }
        });

        
        JLabel clientLabel = new JLabel("Client GUI");
        clientLabel.setForeground(CommonConstants.TEXT_COLOR);
        clientLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        clientLabel.setBounds(0, 25, 520, 100);
        clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientPanel.add(clientLabel);

        JButton createBookingButton = new JButton("Create Booking");
        createBookingButton.setFont(new Font("Dialog", Font.BOLD, 18));
        createBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        createBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createBookingButton.setBounds(43,200,420,60);
        clientPanel.add(createBookingButton);
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
        clientPanel.add(modifyBookingButton);
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
        clientPanel.add(getAvailableFlightsButton);
        getAvailableFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new GetAvailableFlights().setVisible(true);
            }
        });

        JButton addPaymentMethodButton = new JButton("Add Payment Method");
        addPaymentMethodButton.setFont(new Font("Dialog", Font.BOLD, 18));
        addPaymentMethodButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addPaymentMethodButton.setBackground(CommonConstants.TEXT_COLOR);
        addPaymentMethodButton.setForeground(CommonConstants.PRIMARY_COLOR);
        addPaymentMethodButton.setBounds(43,500,420,60);
        clientPanel.add(addPaymentMethodButton);
        addPaymentMethodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new AddPaymentGUI().setVisible(true);
            }
        });


        JRadioButton adminProfile = new JRadioButton ();
        adminProfile.setBounds (10, 10, 60, 50);
        adminProfile.setIcon (new ImageIcon (CommonConstants.USER_ICON_PATH));
        adminProfile.setBackground (CommonConstants.SECONDARY_COLOR);
        adminProfile.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        adminProfile.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ();
                new ProfileGUI ().setVisible (true);
            }
        });
        adminPanel.add (adminProfile);
        

    }
}
