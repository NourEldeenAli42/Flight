package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class TicketGUI extends Form {

    public TicketGUI() {
        super("Ticket");
        setSize(700, 400);
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel ticketLabel = new JLabel("Ticket");
        ticketLabel.setForeground(CommonConstants.TEXT_COLOR);
        ticketLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        ticketLabel.setBounds(70, 0, 520, 100);
        ticketLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(ticketLabel);

        JLabel originLabel = new JLabel("Origin : ");
        originLabel.setForeground(CommonConstants.TEXT_COLOR);
        originLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        originLabel.setBounds(70, 100, 200, 30);
        add(originLabel);

        JTextField originTextField = new JTextField();
        originTextField.setBounds(150, 100, 150, 30);
        originTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        originTextField.setForeground(CommonConstants.TEXT_COLOR);
        originTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        originTextField.setEditable(false); // Make it non-editable
        add(originTextField);

        JLabel destinationLabel = new JLabel("Destination : ");
        destinationLabel.setForeground(CommonConstants.TEXT_COLOR);
        destinationLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        destinationLabel.setBounds(320, 100, 200, 30);
        add(destinationLabel);

        JTextField destinationTextField = new JTextField();
        destinationTextField.setBounds(450, 100, 150, 30);
        destinationTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        destinationTextField.setForeground(CommonConstants.TEXT_COLOR);
        destinationTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        destinationTextField.setEditable(false); // Make it non-editable
        add(destinationTextField);

        JLabel dateLabel = new JLabel("Take-off date : ");
        dateLabel.setForeground(CommonConstants.TEXT_COLOR);
        dateLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        dateLabel.setBounds(70, 150, 200, 30);
        add(dateLabel);

        JTextField dateTextField = new JTextField();
        dateTextField.setBounds(220, 150, 150, 30);
        dateTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        dateTextField.setForeground(CommonConstants.TEXT_COLOR);
        dateTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        dateTextField.setEditable(false); // Make it non-editable
        add(dateTextField);

        JLabel airLineLabel = new JLabel("Airline : ");
        airLineLabel.setForeground(CommonConstants.TEXT_COLOR);
        airLineLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        airLineLabel.setBounds(400, 150, 200, 30);
        add(airLineLabel);

        JTextField airLineTextField = new JTextField();
        airLineTextField.setBounds(480, 150, 120, 30);
        airLineTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        airLineTextField.setForeground(CommonConstants.TEXT_COLOR);
        airLineTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        airLineTextField.setEditable(false); // Make it non-editable
        add(airLineTextField);

        JLabel classLabel = new JLabel("Class : ");
        classLabel.setForeground(CommonConstants.TEXT_COLOR);
        classLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        classLabel.setBounds(70, 200, 200, 30);
        add(classLabel);

        JRadioButton classARadioButton = new JRadioButton("Class A");
        classARadioButton.setForeground(CommonConstants.TEXT_COLOR);
        classARadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classARadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        classARadioButton.setBounds(200, 200, 100, 30);
        classARadioButton.setEnabled(false); // Disable the radio button
        add(classARadioButton);

        JRadioButton classBRadioButton = new JRadioButton("Class B");
        classBRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        classBRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classBRadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        classBRadioButton.setBounds(350, 200, 100, 30);
        classBRadioButton.setEnabled(false); // Disable the radio button
        add(classBRadioButton);

        JRadioButton classCRadioButton = new JRadioButton("Class C");
        classCRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        classCRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classCRadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        classCRadioButton.setBounds(500, 200, 100, 30);
        classCRadioButton.setEnabled(false); // Disable the radio button
        add(classCRadioButton);

        ButtonGroup classButtonGroup = new ButtonGroup();
        classButtonGroup.add(classARadioButton);
        classButtonGroup.add(classBRadioButton);
        classButtonGroup.add(classCRadioButton);

        JLabel ticketPriceLabel = new JLabel("Price : ");
        ticketPriceLabel.setForeground(CommonConstants.TEXT_COLOR);
        ticketPriceLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        ticketPriceLabel.setBounds(70, 250, 200, 30);
        add(ticketPriceLabel);

        JTextField ticketPriceTextField = new JTextField();
        ticketPriceTextField.setBounds(150, 250, 150, 30);
        ticketPriceTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ticketPriceTextField.setForeground(CommonConstants.TEXT_COLOR);
        ticketPriceTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        ticketPriceTextField.setEditable(false); // Make it non-editable
        add(ticketPriceTextField);

        JLabel ticketNumberLabel = new JLabel("Ticket Number : ");
        ticketNumberLabel.setForeground(CommonConstants.TEXT_COLOR);
        ticketNumberLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        ticketNumberLabel.setBounds(340, 250, 200, 30);
        add(ticketNumberLabel);

        JTextField ticketNumberTextField = new JTextField();
        ticketNumberTextField.setBounds(500, 250, 100, 30);
        ticketNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ticketNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
        ticketNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        ticketNumberTextField.setEditable(false); // Make it non-editable
        add(ticketNumberTextField);

        JLabel flightProgramLabel = new JLabel("Click to see flight program");
        flightProgramLabel.setForeground(CommonConstants.TEXT_COLOR);
        flightProgramLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        flightProgramLabel.setBounds(130, 300, 400, 30);
        flightProgramLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightProgramLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        flightProgramLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new DesignFlightProgram().setVisible(true);
            }
        });
        add(flightProgramLabel);

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