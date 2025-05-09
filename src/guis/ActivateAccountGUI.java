package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import myJDBC.myDB;



public class ActivateAccountGUI extends Form {
    int userType=3;
    public ActivateAccountGUI() {
        super("Activate Account");
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);



        JLabel activateAccountLapel = new JLabel("Activate Account GUI");
        activateAccountLapel.setFont(new Font ("Dialog" , Font.BOLD, 30));
        activateAccountLapel.setBounds(0, 25, 520, 100);
        activateAccountLapel.setForeground(CommonConstants.TEXT_COLOR);
        activateAccountLapel.setHorizontalAlignment(SwingConstants.CENTER);
        add(activateAccountLapel);

        JLabel usernameLabel = new JLabel("Username : ");
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        usernameLabel.setBounds(40, 200, 200, 50);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(usernameLabel);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        usernameTextField.setBounds(180, 200, 300, 50);
        usernameTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        usernameTextField.setForeground(CommonConstants.TEXT_COLOR);
        usernameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        passwordLabel.setBounds(40, 300, 200, 50);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(passwordLabel);

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordTextField.setBounds(180, 300, 300, 50);
        passwordTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        passwordTextField.setForeground(CommonConstants.TEXT_COLOR);
        usernameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        add(passwordTextField);

        JRadioButton adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        adminRadioButton.setBounds(40, 400, 100, 50);
        adminRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        adminRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        adminRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adminRadioButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 1;
            }
        });
        add(adminRadioButton);

        JRadioButton agentRadioButton = new JRadioButton("Agent");
        agentRadioButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        agentRadioButton.setBounds(200, 400, 100, 50);
        agentRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        agentRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        agentRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        agentRadioButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 2;
            }
        });
        add(agentRadioButton);

        JRadioButton customerRadioButton = new JRadioButton("Customer");
        customerRadioButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        customerRadioButton.setBounds(350, 400, 150, 50);
        customerRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        customerRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        customerRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        customerRadioButton.setSelected(true);
        customerRadioButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 3;
            }
        });
        add(customerRadioButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(adminRadioButton);
        buttonGroup.add(agentRadioButton);
        buttonGroup.add(customerRadioButton);



        JButton activateAccountButton = new JButton("Activate Account");
        activateAccountButton.setFont (new Font ("Dialog" , Font.BOLD , 25));
        activateAccountButton.setBounds (60, 500, 400, 50);
        activateAccountButton.setForeground(CommonConstants.SECONDARY_COLOR);
        activateAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        activateAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        activateAccountButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameTextField.getText ().equals (myDB.getUsername (CommonConstants.CURRENT_USER_ID))){
                    JOptionPane.showMessageDialog (ActivateAccountGUI.this, "You cannot activate your own account");
                    return;
                }
                String password = new String (passwordTextField.getPassword ());
                     if(myDB.changeUserType (usernameTextField.getText (),password,userType)){
                         JOptionPane.showMessageDialog (ActivateAccountGUI.this, "Account Activated");
                         ActivateAccountGUI.this.dispose ();
                         new AdminGUI ().setVisible (true);
                     }
            }
        });
        add(activateAccountButton);
        
        JButton BackButton = new JButton("Back");
        BackButton.setBounds(60,570,400,50);
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
