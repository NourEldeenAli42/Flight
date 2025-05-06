package guis;

import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class GetAvailableFlights extends Form {
    public GetAvailableFlights() {
        super("Get Available Flights");
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(1200, 680));
        setResizable(true);
        addGuiComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void addGuiComponents() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        // North panel for the title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(CommonConstants.SECONDARY_COLOR);
        
        JLabel GAFLapel = new JLabel("Get Available Flights");
        GAFLapel.setForeground(CommonConstants.TEXT_COLOR);
        GAFLapel.setFont(new Font("Dialog", Font.BOLD, 30));
        titlePanel.add(GAFLapel);
        add(titlePanel, BorderLayout.NORTH);

        // Center panel for the table
        JTable flights = new JTable();
        flights.setForeground(CommonConstants.TEXT_COLOR);
        flights.setBackground(CommonConstants.PRIMARY_COLOR);
        flights.setFont(new Font("Dialog", Font.PLAIN, 18));
        flights.setGridColor(CommonConstants.TEXT_COLOR);
        flights.setSelectionBackground(CommonConstants.TEXT_COLOR);
        flights.setSelectionForeground(CommonConstants.PRIMARY_COLOR);
        
        // Style the table header
        JTableHeader header = flights.getTableHeader();
        header.setBackground(CommonConstants.PRIMARY_COLOR);
        header.setForeground(CommonConstants.TEXT_COLOR);
        header.setFont(new Font("Dialog", Font.BOLD, 18));
        
        // Add table to a scroll pane with themed colors
        JScrollPane scrollPane = new JScrollPane(flights);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        scrollPane.getViewport().setBackground(CommonConstants.PRIMARY_COLOR);
        scrollPane.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        
        // Style the scroll bars
        scrollPane.getVerticalScrollBar().setBackground(CommonConstants.PRIMARY_COLOR);
        scrollPane.getHorizontalScrollBar().setBackground(CommonConstants.PRIMARY_COLOR);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = CommonConstants.TEXT_COLOR;
                this.trackColor = CommonConstants.PRIMARY_COLOR;
            }
        });
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = CommonConstants.TEXT_COLOR;
                this.trackColor = CommonConstants.PRIMARY_COLOR;
            }
        });
        
        add(scrollPane, BorderLayout.CENTER);

        // South panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(CommonConstants.SECONDARY_COLOR);

        JButton getAvailableFlightsButton = new JButton("Get Available Flights");
        getAvailableFlightsButton.setPreferredSize(new Dimension(350, 70));
        getAvailableFlightsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        getAvailableFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        getAvailableFlightsButton.setFont(new Font("Dialog", Font.BOLD, 20));
        getAvailableFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flights.setModel(myDB.getAvailableFlight());
            }
        });
        buttonPanel.add(getAvailableFlightsButton);

        JButton BackButton = new JButton("Back");
        BackButton.setPreferredSize(new Dimension(250, 60));
        BackButton.setForeground(CommonConstants.PRIMARY_COLOR);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setFont(new Font("Dialog", Font.BOLD, 20));
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                navigateBack();
            }
        });
        buttonPanel.add(BackButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}