package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import myJDBC.myDB;

public class ModifyingBooking extends Form {
    public ModifyingBooking() {
        super("Modify Booking");
        setSize (520,750);
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel MBLabel = new JLabel("Modify Booking");
        MBLabel.setForeground(CommonConstants.TEXT_COLOR);
        MBLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        MBLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MBLabel.setBounds(130,30,240,50);
        add(MBLabel);


        //TO DO : REMOVE CLIENT USERNAME AND PASSWORD FROM THE GUI
        JLabel clientUsernameLabel = new JLabel("Client username : ");
        clientUsernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        clientUsernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        clientUsernameLabel.setBounds(30,-90,400,400);
        add(clientUsernameLabel);

        JTextField clientUsernameTextField = new JTextField();
        clientUsernameTextField.setBounds(43,130,420,30);
        clientUsernameTextField.setForeground(CommonConstants.TEXT_COLOR);
        clientUsernameTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        clientUsernameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        clientUsernameTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(clientUsernameTextField);

        JLabel ClientPasswordLapel = new JLabel("Client password : ");
        ClientPasswordLapel.setForeground(CommonConstants.TEXT_COLOR);
        ClientPasswordLapel.setFont(new Font("Dialog", Font.PLAIN, 18));
        ClientPasswordLapel.setBounds(30,0,400,400);
        add(ClientPasswordLapel);

        JPasswordField clientPasswordTextField = new JPasswordField();
        clientPasswordTextField.setBounds(43,220,420,30);
        clientPasswordTextField.setForeground(CommonConstants.TEXT_COLOR);
        clientPasswordTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        clientPasswordTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        clientPasswordTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(clientPasswordTextField);

        JLabel flightNumberLabel = new JLabel("Flight Number : ");
        flightNumberLabel.setForeground(CommonConstants.TEXT_COLOR);
        flightNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightNumberLabel.setBounds(30,90,400,400);
        add(flightNumberLabel);

        JTextField flightNumberTextField = new JTextField();
        flightNumberTextField.setBounds(43,310,420,30);
        flightNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
        flightNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        flightNumberTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        flightNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(flightNumberTextField);
        
        JLabel ANFLabel = new JLabel("Assign New Flight : ");
        ANFLabel.setForeground(CommonConstants.TEXT_COLOR);
        ANFLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        ANFLabel.setBounds(30,180,400,400);
        add(ANFLabel);

        JTextField ANFTextField = new JTextField();
        ANFTextField.setBounds(43,410,420,30);
        ANFTextField.setForeground(CommonConstants.TEXT_COLOR);
        ANFTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        ANFTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        ANFTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(ANFTextField);


        JLabel addPaymentAmount = new JLabel("Add Payment Amount : ");
        addPaymentAmount.setForeground(CommonConstants.TEXT_COLOR);
        addPaymentAmount.setFont(new Font("Dialog", Font.PLAIN, 18));
        addPaymentAmount.setBounds(30,280,400,400);
        add(addPaymentAmount);

        JTextField addPaymentAmountTextField = new JTextField();
        addPaymentAmountTextField.setBounds(43,500,420,30);
        addPaymentAmountTextField.setForeground(CommonConstants.TEXT_COLOR);
        addPaymentAmountTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        addPaymentAmountTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        addPaymentAmountTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(addPaymentAmountTextField);
        addPaymentAmountTextField.setText ( String.valueOf(0) );

        JRadioButton classAButton = new JRadioButton("Class A");
        classAButton.setBounds(60, 560, 100, 30);
        classAButton.setForeground(CommonConstants.TEXT_COLOR);
        classAButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classAButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classAButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(classAButton);

        JRadioButton classBButton = new JRadioButton("Class B");
        classBButton.setBounds(200, 560, 100, 30);
        classBButton.setForeground(CommonConstants.TEXT_COLOR);
        classBButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classBButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classBButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        classBButton.setSelected(true);
        add(classBButton);

        JRadioButton classCButton = new JRadioButton("Class C");
        classCButton.setBounds(340, 560, 100, 30);
        classCButton.setForeground(CommonConstants.TEXT_COLOR);
        classCButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classCButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classCButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(classCButton);

        ButtonGroup classButtonGroup = new ButtonGroup();
        classButtonGroup.add(classAButton);
        classButtonGroup.add(classBButton);
        classButtonGroup.add(classCButton);


        JButton modifyBooking = new JButton("Modify Booking");
        modifyBooking.setBounds(250, 620, 200, 60);
        modifyBooking.setForeground(CommonConstants.SECONDARY_COLOR);
        modifyBooking.setBackground(CommonConstants.TEXT_COLOR);
        modifyBooking.setFont(new Font("Dialog", Font.BOLD, 25));
        modifyBooking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modifyBooking.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(clientPasswordTextField.getPassword());
                int flightNumber = Integer.parseInt(flightNumberTextField.getText());
                int paymentAmount = Integer.parseInt(addPaymentAmountTextField.getText());
                int newflightnumber = Integer.parseInt(ANFTextField.getText());
                if(myDB.modifyBooking (clientUsernameTextField.getText (),password,paymentAmount,flightNumber,newflightnumber)){
                    JOptionPane.showMessageDialog(null, "Booking modified successfully");
                    dispose();
                    new ClientGUI().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Booking modification failed");
                }
                clientUsernameTextField.setText("");
                clientPasswordTextField.setText("");
                flightNumberTextField.setText("");
            }
        });
        add(modifyBooking);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(43, 620, 200, 60);
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

    

