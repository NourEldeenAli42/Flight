package guis;
//TODO : improve Profile data collection

import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProfileGUI extends Form {
    
    public ProfileGUI(){
        super("Profile GUI");
        addGuiComponents();
    }

    public void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);


        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setForeground(CommonConstants.TEXT_COLOR);
        profileLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileLabel.setBounds(130, 30, 240, 50);
        add(profileLabel);

        JLabel usernameLabel = new JLabel("Username : ");
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        usernameLabel.setBounds(30, -70, 150, 400);
        add(usernameLabel);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(170, 110, 300, 50);
        usernameTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        usernameTextField.setForeground(CommonConstants.TEXT_COLOR);
        usernameTextField.setEnabled(false);
        usernameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        usernameTextField.setText(myDB.getUsername ( CommonConstants.CURRENT_USER_ID));
        usernameTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        passwordLabel.setBounds(30, 30, 150, 400);
        add(passwordLabel);

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(170, 210, 200, 50);
        passwordTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        passwordTextField.setForeground(CommonConstants.TEXT_COLOR);
        passwordTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        passwordTextField.setText(myDB.getPassword ( CommonConstants.CURRENT_USER_ID));
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(passwordTextField);

        JCheckBox showPasswordCheckBox = new JCheckBox("Show");
        showPasswordCheckBox.setBounds(400, 220, 110, 30);
        showPasswordCheckBox.setBackground(CommonConstants.SECONDARY_COLOR);
        showPasswordCheckBox.setForeground(CommonConstants.TEXT_COLOR);
        showPasswordCheckBox.setFont(new Font("Dialog", Font.PLAIN, 15));
        showPasswordCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        showPasswordCheckBox.addActionListener(_ -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordTextField.setEchoChar((char) 0);
            } else {
                passwordTextField.setEchoChar('*');
            }
        });
        add(showPasswordCheckBox);

        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setForeground(CommonConstants.TEXT_COLOR);
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        nameLabel.setBounds(30, 130, 150, 400);
        add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(170, 310, 300, 50);
        nameTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        nameTextField.setForeground(CommonConstants.TEXT_COLOR);
        nameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        nameTextField.setText(myDB.getName ( CommonConstants.CURRENT_USER_ID));
        nameTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(nameTextField);

        JLabel emailLabel = new JLabel("Email : ");
        emailLabel.setForeground(CommonConstants.TEXT_COLOR);   
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        emailLabel.setBounds(30, 230, 150, 400);
        add(emailLabel);

        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(170, 410, 300, 50);
        emailTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        emailTextField.setForeground(CommonConstants.TEXT_COLOR);
        emailTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        emailTextField.setText(myDB.getEmail ( CommonConstants.CURRENT_USER_ID));
        emailTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(emailTextField);



        JRadioButton adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setBounds(80, 490, 100, 30);
        adminRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        adminRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        adminRadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        adminRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(adminRadioButton);

        JRadioButton agentRadioButton = new JRadioButton("Agent");
        agentRadioButton.setBounds(220, 490, 100, 30);
        agentRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        agentRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        agentRadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        agentRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(agentRadioButton);

        JRadioButton clientRadioButton = new JRadioButton("Client");
        clientRadioButton.setBounds(360, 490, 100, 30);
        clientRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        clientRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        clientRadioButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        clientRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(clientRadioButton);

        adminRadioButton.setEnabled(false);
        agentRadioButton.setEnabled(false);
        clientRadioButton.setEnabled(false);

        ButtonGroup userTypeGroup = new ButtonGroup();
        userTypeGroup.add(adminRadioButton);
        userTypeGroup.add(agentRadioButton);
        userTypeGroup.add(clientRadioButton);
        userTypeGroup.setSelected(adminRadioButton.getModel(), true);

        int userType = myDB.getUserType ( CommonConstants.CURRENT_USER_ID);
        switch (userType) {
            case 1:
                adminRadioButton.setSelected(true);
                break;
            case 2:
                agentRadioButton.setSelected(true);
                break;
            case 3:
                clientRadioButton.setSelected(true);
                break;
            case 0:
                adminRadioButton.setEnabled(false);
                agentRadioButton.setEnabled(false);
                clientRadioButton.setEnabled(false);
                break;
        }
        JButton saveButton = new JButton("Save Changes");
        saveButton.setBounds(270, 560, 200, 60);
        saveButton.setForeground(CommonConstants.PRIMARY_COLOR);
        saveButton.setBackground(CommonConstants.TEXT_COLOR);
        saveButton.setFont(new Font("Dialog", Font.BOLD, 18));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String name = nameTextField.getText();
                String email = emailTextField.getText();
                String password = new String(passwordTextField.getPassword());
                myDB.updateUser ( CommonConstants.CURRENT_USER_ID, name, email, password);
                JOptionPane.showMessageDialog(null, "Profile updated successfully");
            }
        });
        add(saveButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(30, 560, 200, 60);
        logoutButton.setForeground(CommonConstants.PRIMARY_COLOR);
        logoutButton.setBackground(CommonConstants.TEXT_COLOR);
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 18));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CommonConstants.CURRENT_USER_ID=-1;
                dispose();
                new LoginFormGUI ().setVisible (true);
            }
        });
        add(logoutButton);

        JLabel backButton = new JLabel("Back");
        backButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(CommonConstants.TEXT_COLOR);
        backButton.setBounds(20, 20, 100, 30);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateBack();

            }
        });
        
        add(backButton);
    }

}
