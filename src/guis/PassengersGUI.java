package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PassengersGUI extends Form {

    public PassengersGUI() {
        super("Passengers");
        setSize(900, 700);
        addGuiComponents();
    }

    private void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel titleLabel = new JLabel("Passengers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(CommonConstants.TEXT_COLOR);
        titleLabel.setBounds(0, 15, 900, 100); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JTable passengersTable = new JTable();
        passengersTable.setForeground(CommonConstants.TEXT_COLOR);
        passengersTable.setBackground(CommonConstants.PRIMARY_COLOR);
        passengersTable.setFont(new Font("Arial", Font.PLAIN, 15));
        passengersTable.setRowHeight(30);
        passengersTable.setSelectionBackground(CommonConstants.TEXT_COLOR);
        passengersTable.setSelectionForeground(CommonConstants.PRIMARY_COLOR);
        passengersTable.setGridColor(CommonConstants.TEXT_COLOR);
        passengersTable.setShowGrid(true);
        passengersTable.setShowVerticalLines(true);
        passengersTable.setShowHorizontalLines(true);
        passengersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        passengersTable.setFillsViewportHeight(true);
        passengersTable.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        passengersTable.setDefaultEditor(Object.class, null); 
        JScrollPane scrollPane = new JScrollPane(passengersTable);
        scrollPane.setBounds(50, 130, 800, 400);
        scrollPane.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        add(scrollPane);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Passenger ID", "Name", "Date of Birth","Hotel Name", "Seat Number" , "Class" , "Meals" , "Type"});
        


        JButton showPassengersButton = new JButton("Show Passengers");
        showPassengersButton.setFont(new Font("Dialog", Font.BOLD, 25));
        showPassengersButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        showPassengersButton.setBackground(CommonConstants.TEXT_COLOR);
        showPassengersButton.setForeground(CommonConstants.PRIMARY_COLOR);
        showPassengersButton.setBounds(200, 550, 500, 60); // Centered and widened
        showPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passengersTable.setModel(model);
            }
        });
        add(showPassengersButton);

        JLabel backButton = new JLabel("Back");
        backButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(CommonConstants.TEXT_COLOR);
        backButton.setBounds(20, 20, 100, 30);
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                navigateBack();
            }
        });
        add(backButton);
    
    }
}
