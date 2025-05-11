package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FlightProgram extends Form{
    public FlightProgram() {
        super("Flight Program");
        addGuiComponents();
    }

    public void addGuiComponents(){

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel titleLabel = new JLabel("Flight Program");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        titleLabel.setBounds(0, 25, 520, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(titleLabel);

        JLabel flightTypeLabel = new JLabel("Flight Type : ");
        flightTypeLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        flightTypeLabel.setBounds(50, 200, 200, 30);
        flightTypeLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(flightTypeLabel);

        JTextField flightTypeField = new JTextField();
        flightTypeField.setFont(new Font("Dialog", Font.PLAIN, 20));
        flightTypeField.setBounds(200, 205, 200, 30);
        flightTypeField.setBackground(CommonConstants.PRIMARY_COLOR);
        flightTypeField.setForeground(CommonConstants.TEXT_COLOR);
        flightTypeField.setEnabled(false);
        flightTypeField.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        add(flightTypeField);

        JLabel residentLabel = new JLabel("Resident : ");
        residentLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        residentLabel.setBounds(50, 300, 200, 30);
        residentLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(residentLabel);

        JTextField residentField = new JTextField();
        residentField.setFont(new Font("Dialog", Font.PLAIN, 20));
        residentField.setBounds(200, 305, 200, 30);
        residentField.setBackground(CommonConstants.PRIMARY_COLOR);
        residentField.setForeground(CommonConstants.TEXT_COLOR);
        residentField.setEnabled(false);
        residentField.setBorder(BorderFactory.createLineBorder(CommonConstants.TEXT_COLOR));
        add(residentField);

        JLabel selsectedMealsLabel = new JLabel("Meals : ");
        selsectedMealsLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        selsectedMealsLabel.setBounds(50, 400, 200, 30);
        selsectedMealsLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(selsectedMealsLabel);

        JCheckBox breakfastButton = new JCheckBox("Breakfast");
        breakfastButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        breakfastButton.setBounds(200, 400, 150, 30);
        breakfastButton.setBackground(CommonConstants.SECONDARY_COLOR);
        breakfastButton.setForeground(CommonConstants.TEXT_COLOR);
        breakfastButton.setEnabled(false);
        breakfastButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(breakfastButton);

        JCheckBox dinnerButton = new JCheckBox("Dinner");
        dinnerButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        dinnerButton.setBounds(200, 445, 150, 30);
        dinnerButton.setBackground(CommonConstants.SECONDARY_COLOR);
        dinnerButton.setForeground(CommonConstants.TEXT_COLOR);
        dinnerButton.setEnabled(false);
        dinnerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(dinnerButton);
        
        ButtonGroup mealGroup = new ButtonGroup();
        mealGroup.add(breakfastButton);
        mealGroup.add(dinnerButton);

        JButton BackButton = new JButton("Back");
        BackButton.setFont(new Font("Dialog", Font.BOLD, 25));
        BackButton.setBounds(80, 550, 350, 50);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setForeground(CommonConstants.SECONDARY_COLOR);
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                navigateBack();
            }
        });
        add(BackButton);
    }
    
}
