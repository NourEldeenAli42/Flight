package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import myJDBC.myDB;

public class CreatingNewBooking extends Form {
    public CreatingNewBooking() {
        super("Create New Booking");
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel CNBLapel = new JLabel("Create New Booking");
        CNBLapel.setForeground(CommonConstants.TEXT_COLOR);
        CNBLapel.setFont(new Font("Dialog", Font.BOLD, 25));
        CNBLapel.setHorizontalAlignment(SwingConstants.CENTER);
        CNBLapel.setBounds(130,30,240,50);
        add(CNBLapel);


        //TO DO : REMOVE CLIENT USERNAME AND PASSWORD FROM THE GUI
        JLabel ClientUsernameLapel = new JLabel("Client username : ");
        ClientUsernameLapel.setForeground(CommonConstants.TEXT_COLOR);
        ClientUsernameLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        ClientUsernameLapel.setBounds(30,-100,400,400);
        add(ClientUsernameLapel);

        JTextField ClientUsernameTextField = new JTextField();
        ClientUsernameTextField.setBounds(43,115,420,30);
        ClientUsernameTextField.setForeground(CommonConstants.TEXT_COLOR);
        ClientUsernameTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ClientUsernameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        ClientUsernameTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(ClientUsernameTextField);

        JLabel ClientPasswordLapel = new JLabel("Client password : ");
        ClientPasswordLapel.setForeground(CommonConstants.TEXT_COLOR);
        ClientPasswordLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        ClientPasswordLapel.setBounds(30,-10,400,400);
        add(ClientPasswordLapel);

        JPasswordField ClientPasswordTextField = new JPasswordField();
        ClientPasswordTextField.setBounds(43,210,420,30);
        ClientPasswordTextField.setForeground(CommonConstants.TEXT_COLOR);
        ClientPasswordTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ClientPasswordTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        ClientPasswordTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(ClientPasswordTextField);

        JLabel FlightNumberLapel = new JLabel("Flight Number : ");
        FlightNumberLapel.setForeground(CommonConstants.TEXT_COLOR);
        FlightNumberLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        FlightNumberLapel.setBounds(30,85,400,400);
        add(FlightNumberLapel);

        JTextField FlightNumberTextField = new JTextField();
        FlightNumberTextField.setBounds(43,315,420,30);
        FlightNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
        FlightNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        FlightNumberTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        FlightNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(FlightNumberTextField);
        
        JLabel TotalPaidLapel = new JLabel("Total Paid : ");
        TotalPaidLapel.setForeground(CommonConstants.TEXT_COLOR);
        TotalPaidLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        TotalPaidLapel.setBounds(30,185,400,400);
        add(TotalPaidLapel);

        JTextField TotalPaidTextField = new JTextField();
        TotalPaidTextField.setBounds(43,415,420,30);
        TotalPaidTextField.setForeground(CommonConstants.TEXT_COLOR);
        TotalPaidTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        TotalPaidTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        TotalPaidTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(TotalPaidTextField);

        JRadioButton classAButton = new JRadioButton("Class A");
        classAButton.setBounds(60, 480, 100, 30);
        classAButton.setForeground(CommonConstants.TEXT_COLOR);
        classAButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classAButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classAButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(classAButton);

        JRadioButton classBButton = new JRadioButton("Class B");
        classBButton.setBounds(200, 480, 100, 30);
        classBButton.setForeground(CommonConstants.TEXT_COLOR);
        classBButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classBButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classBButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        classBButton.setSelected(true);
        add(classBButton);

        JRadioButton classCButton = new JRadioButton("Class C");
        classCButton.setBounds(340, 480, 100, 30);
        classCButton.setForeground(CommonConstants.TEXT_COLOR);
        classCButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classCButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classCButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(classCButton);

        ButtonGroup classButtonGroup = new ButtonGroup();
        classButtonGroup.add(classAButton);
        classButtonGroup.add(classBButton);
        classButtonGroup.add(classCButton);

        JButton createBookingButton = new JButton("Create Booking");
        createBookingButton.setBounds(250, 550, 200, 60);
        createBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        createBookingButton.setFont(new Font("Dialog", Font.BOLD, 22));
        createBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createBookingButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(ClientPasswordTextField.getPassword());
                if(myDB.createBooking(ClientUsernameTextField.getText(), password, FlightNumberTextField.getText(),
                        Integer.parseInt(TotalPaidTextField.getText()))){
                    JOptionPane.showMessageDialog(null, "Booking created successfully");
                    dispose();
                    new ClientGUI().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Booking creation failed");
                }
                ClientUsernameTextField.setText("");
                ClientPasswordTextField.setText("");
                FlightNumberTextField.setText("");
                TotalPaidTextField.setText("");
            }
        });
        add(createBookingButton);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(43, 550, 200, 60);
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
