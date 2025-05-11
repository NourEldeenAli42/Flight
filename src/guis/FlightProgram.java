package guis;

import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FlightProgram extends Form{
    public static int flightNumber;
    public FlightProgram() {
        super("Flight Program");
    }

    public void setFlightid(int flightNumber){
        FlightProgram.flightNumber = flightNumber;

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
        flightTypeField.setText (myDB.getFlightType (CommonConstants.CURRENT_USER_ID, flightNumber));
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
        residentField.setText (myDB.getFlightResident (CommonConstants.CURRENT_USER_ID, flightNumber));
        add(residentField);

        JLabel selsectedMealsLabel = new JLabel("Meals : ");
        selsectedMealsLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        selsectedMealsLabel.setBounds(50, 400, 200, 30);
        selsectedMealsLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(selsectedMealsLabel);

        JTextField mealsTextField = new JTextField ();
        mealsTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        mealsTextField.setBounds(200, 400, 150, 30);
        mealsTextField.setBackground(CommonConstants.SECONDARY_COLOR);
        mealsTextField.setForeground(CommonConstants.TEXT_COLOR);
        mealsTextField.setEnabled(false);
        mealsTextField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mealsTextField.setText (myDB.getNoMeals (CommonConstants.CURRENT_USER_ID, flightNumber)+"");
        add(mealsTextField);


        JButton BackButton = new JButton("Back");
        BackButton.setFont(new Font("Dialog", Font.BOLD, 25));
        BackButton.setBounds(80, 550, 350, 50);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setForeground(CommonConstants.SECONDARY_COLOR);
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose ();
                new CreatingNewBooking ().setVisible (true);
            }
        });
        add(BackButton);
    }

    public void refresh(){
        getContentPane().removeAll();
        addGuiComponents();
        revalidate();
        repaint();
    }
    
}
