package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DesignFlightProgram extends Form {

    private static int flightNumber = 0;

    public DesignFlightProgram(int flightNumber) {
        super("Design Flight Program");
        this.flightNumber = flightNumber;
        addGuiComponents();
    }

    private void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel designFlightLabel = new JLabel("Design Flight Program");
        designFlightLabel.setForeground(CommonConstants.TEXT_COLOR);
        designFlightLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        designFlightLabel.setBounds(0, 25, 520, 100);
        designFlightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(designFlightLabel);

        JComboBox<String> type = new JComboBox<>();
        type.addItem ( "Select Type");
        type.setBackground ( CommonConstants.PRIMARY_COLOR );
        type.setForeground ( CommonConstants.TEXT_COLOR );
        type.setFont ( new Font ( "Dialog", Font.PLAIN, 20) );
        type.setBounds(50, 150, 400, 30);
        type.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        type.setSelectedIndex (0);
        add (type);

        JLabel residentLabel = new JLabel("Resident : ");
        residentLabel.setForeground(CommonConstants.TEXT_COLOR);
        residentLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        residentLabel.setBounds(50, 250, 150, 30);  
        add(residentLabel);


        JTextField residentTextField = new JTextField();
        residentTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        residentTextField.setForeground(CommonConstants.TEXT_COLOR);
        residentTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        residentTextField.setBounds(200, 250, 250, 40);
        residentTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        residentTextField.setCaretColor(CommonConstants.TEXT_COLOR);
        residentTextField.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        add(residentTextField);

        JLabel mealsLabel = new JLabel("Meals : ");
        mealsLabel.setForeground(CommonConstants.TEXT_COLOR);
        mealsLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        mealsLabel.setBounds(50, 350, 150, 30);
        add(mealsLabel);


        JCheckBox breakfast = new JCheckBox("Breakfast");
        breakfast.setBackground(CommonConstants.SECONDARY_COLOR);
        breakfast.setForeground(CommonConstants.TEXT_COLOR);
        breakfast.setFont(new Font("Dialog", Font.PLAIN, 20));
        breakfast.setBounds(200, 350, 150, 30);
        breakfast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        breakfast.setSelected(false);
        add(breakfast);

        JCheckBox Dinner = new JCheckBox("Dinner");
        Dinner.setBackground(CommonConstants.SECONDARY_COLOR); 
        Dinner.setForeground(CommonConstants.TEXT_COLOR);
        Dinner.setFont(new Font("Dialog", Font.PLAIN, 20));
        Dinner.setBounds(200, 400, 150, 30);
        Dinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Dinner.setSelected(false);
        add(Dinner);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(CommonConstants.TEXT_COLOR);
        confirmButton.setForeground(CommonConstants.SECONDARY_COLOR);
        confirmButton.setFont(new Font("Dialog", Font.BOLD, 25));
        confirmButton.setBounds(55, 500, 400, 50);
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String typeSelected = (String) type.getSelectedItem();
                String resident = residentTextField.getText();
                boolean breakfastSelected = breakfast.isSelected();
                boolean dinnerSelected = Dinner.isSelected();

                if (typeSelected.equals("Select Type")) {
                    JOptionPane.showMessageDialog(DesignFlightProgram.this, "Please select a type.");
                    return;
                }

                if (resident.isEmpty()) {
                    JOptionPane.showMessageDialog(DesignFlightProgram.this, "Please enter a resident.");
                    return;
                }

                JOptionPane.showMessageDialog(DesignFlightProgram.this, "Flight Program designed successfully!");
                new AddPaymentGUI(flightNumber).setVisible(true);
                }
            });
        add(confirmButton);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(55, 570, 400, 50);
        BackButton.setForeground(CommonConstants.SECONDARY_COLOR);
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
