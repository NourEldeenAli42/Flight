package guis;


import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class GenerateReportGUI extends Form {

    public GenerateReportGUI() {
        super("Generate Report Of Flights");
        setSize(900, 700);
        addGuiComponents();
    }

    public void addGuiComponents(){

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel GenerateReportOfFlightsLabel = new JLabel("Generate Report Of Flights");
        GenerateReportOfFlightsLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        GenerateReportOfFlightsLabel.setForeground(CommonConstants.TEXT_COLOR);
        GenerateReportOfFlightsLabel.setBounds(0, 25, 900, 100); // Adjusted width
        GenerateReportOfFlightsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(GenerateReportOfFlightsLabel);


        JTable report = new JTable();
        report.setForeground(CommonConstants.TEXT_COLOR);
        report.setBackground(CommonConstants.PRIMARY_COLOR);
        report.setFont(new Font("Dialog", Font.PLAIN, 15));
        report.setRowHeight(30);
        report.setSelectionBackground(CommonConstants.TEXT_COLOR);
        report.setSelectionForeground(CommonConstants.PRIMARY_COLOR);
        report.setGridColor(CommonConstants.TEXT_COLOR);
        report.setShowGrid(true);
        report.setShowVerticalLines(true);
        report.setShowHorizontalLines(true);
        report.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        report.setFillsViewportHeight(true);
        report.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        report.setDefaultEditor(Object.class, null); // Disable editing of table cells


        JScrollPane scrollPane = new JScrollPane(report);
        scrollPane.setBounds(50, 130, 800, 300); // Increased width
        scrollPane.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        add(scrollPane);

        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = myDB.getFlightsReport();
        model.setColumnIdentifiers(new Object[]{"Flight ID", "Origin","Destination", "Available A Seats", "Available B Seats", "Available C Seats", "Total Earnings"});
        if (rs != null) {
            try {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("FlightID"), 
                        rs.getString("from"), 
                        rs.getString("to"),
                        Integer.parseInt(rs.getString("Aseats")) - Integer.parseInt(rs.getString("Areserved")),
                        Integer.parseInt(rs.getString("Bseats")) - Integer.parseInt(rs.getString("Breserved")),
                        Integer.parseInt(rs.getString("Cseats")) - Integer.parseInt(rs.getString("Creserved")),
                        rs.getString("totalgained")
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No flights found", "Error", JOptionPane.ERROR_MESSAGE);
        }


        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setFont(new Font("Dialog", Font.BOLD, 25));
        generateReportButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generateReportButton.setBackground(CommonConstants.TEXT_COLOR);
        generateReportButton.setForeground(CommonConstants.PRIMARY_COLOR);
        generateReportButton.setBounds(200, 500, 500, 60); // Centered and widened
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setModel(model);
            }
        });
        add(generateReportButton);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(200, 570, 500, 60); // Centered and widened
        BackButton.setForeground(CommonConstants.PRIMARY_COLOR);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setFont(new Font("Dialog", Font.BOLD, 25));
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                navigateBack();
            }
        });
        add(BackButton);
    }
}