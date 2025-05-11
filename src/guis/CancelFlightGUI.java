package guis;

import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CancelFlightGUI extends Form {


    public CancelFlightGUI() {
        super("Cancel Flight");
        setSize(900, 700);
        addGuiComponents();
    }

    public void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel showFlightsLabel = new JLabel("Show Flights");
        showFlightsLabel.setFont(new Font("Arial", Font.BOLD, 40));
        showFlightsLabel.setForeground(CommonConstants.TEXT_COLOR);
        showFlightsLabel.setBounds(0, 15, 900, 100);
        showFlightsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(showFlightsLabel);

        
        JTable flightsTable = new JTable();
        flightsTable.setForeground(CommonConstants.TEXT_COLOR);
        flightsTable.setBackground(CommonConstants.PRIMARY_COLOR);
        flightsTable.setFont(new Font("Arial", Font.PLAIN, 15));
        flightsTable.setRowHeight(30);
        flightsTable.setSelectionBackground(CommonConstants.TEXT_COLOR);
        flightsTable.setSelectionForeground(CommonConstants.PRIMARY_COLOR);
        flightsTable.setGridColor(CommonConstants.TEXT_COLOR);
        flightsTable.setShowGrid(true);
        flightsTable.setShowVerticalLines(true);
        flightsTable.setShowHorizontalLines(true);
        flightsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        flightsTable.setFillsViewportHeight(true);
        flightsTable.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        flightsTable.setDefaultEditor(Object.class, null); 
        JScrollPane scrollPane = new JScrollPane(flightsTable);
        scrollPane.setBounds(50, 130, 800, 400);
        scrollPane.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        add(scrollPane);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Flight Number", "Origin", "Destination","Take-Off Date", "Class" });

        ResultSet rs = myDB.showBookings ();
        if (rs == null) {
        JOptionPane.showMessageDialog(this, "No flights available", "Error", JOptionPane.ERROR_MESSAGE);}
        else {
            try {
                while (rs.next()) {
                    String flightNumber = rs.getString("flightid");
                    String origin = rs.getString("from");
                    String destination = rs.getString("to");
                    String takeOffDate = rs.getString("takeoffdate");
                    int flightClass = myDB.getTicketType (CommonConstants.CURRENT_USER_ID, Integer.parseInt (flightNumber));
                    String classType = "N/A";
                    switch (flightClass) {
                        case 1 -> classType = "Class A";
                        case 2 -> classType = "Class B";
                        case 3 -> classType = "Class C";
                    }
                    model.addRow(new Object[]{flightNumber, origin, destination, takeOffDate, classType});
                }
            } catch (Exception e) {
                e.printStackTrace();
        }


        JButton showFlightsButton = new JButton("Show Flights");
        showFlightsButton.setBounds(100, 550, 300, 50);
        showFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        showFlightsButton.setForeground(CommonConstants.SECONDARY_COLOR);
        showFlightsButton.setFont(new Font("Arial", Font.BOLD, 20));
        showFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightsTable.setModel(model);
            }
        });
        
        final Object[] flight = {null};
        flightsTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = flightsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int columnIndex = 0;
                    flight[0] = flightsTable.getValueAt(selectedRow, columnIndex);
                }
            }
        });


        add(showFlightsButton);

        JButton cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.setBounds(500, 550, 300, 50);
        cancelFlightButton.setBackground(CommonConstants.TEXT_COLOR);
        cancelFlightButton.setForeground(CommonConstants.SECONDARY_COLOR);
        cancelFlightButton.setFont(new Font("Arial", Font.BOLD, 20));
        cancelFlightButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flightID = Integer.parseInt(flight[0].toString());
                myDB.cancelBooking (flightID);
            }
        });
        add(cancelFlightButton);


        JLabel backButton = new JLabel("Back");
        backButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(CommonConstants.TEXT_COLOR);
        backButton.setBounds(30, 20, 100, 30);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateBack();

            }
        });

        add(backButton);

        }
    }
}
