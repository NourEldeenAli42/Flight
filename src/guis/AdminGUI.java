package guis;

import components.CommonConstants;
import java.awt.*;
import javax.swing.*;

public class AdminGUI extends Form {
    public AdminGUI() {
        super("Admin");
        addGuiComponents();
    }

    private void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JTabbedPane tabbedPane = new JTabbedPane ();
        tabbedPane.setBounds (0, 0, 1000, 800);
        add (tabbedPane);

        JPanel adminPanel = new JPanel ();
        tabbedPane.addTab ("Admin", adminPanel);
        JPanel agentPanel = new JPanel ();
        tabbedPane.addTab ("Agent", agentPanel);
        JPanel CustomerPanel = new JPanel ();
        tabbedPane.addTab ("Agent", CustomerPanel);

        adminPanel.setLayout (null);
        agentPanel.setLayout (null);
        CustomerPanel.setLayout (null);

        adminPanel.setBackground(CommonConstants.SECONDARY_COLOR);
        agentPanel.setBackground(CommonConstants.SECONDARY_COLOR);
        CustomerPanel.setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel adminLabel = new JLabel("Admin GUI");
        adminLabel.setForeground(CommonConstants.TEXT_COLOR);
        adminLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        adminLabel.setBounds(0, 25, 520, 100);
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminPanel.add(adminLabel);

        JButton ActivateAccountButton = new JButton("Activate Account");
        ActivateAccountButton.setFont(new Font("Dialog", Font.BOLD, 18));
        ActivateAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ActivateAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        ActivateAccountButton.setForeground(CommonConstants.PRIMARY_COLOR);
        ActivateAccountButton.setBounds(43, 200, 420, 60);
        adminPanel.add(ActivateAccountButton);
       
        JButton CreateNewAccountButton = new JButton("Create New Account"); 
        CreateNewAccountButton.setFont(new Font("Dialog", Font.BOLD, 18));
        CreateNewAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CreateNewAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        CreateNewAccountButton.setForeground(CommonConstants.PRIMARY_COLOR);
        CreateNewAccountButton.setBounds(43, 300, 420, 60);
        adminPanel.add(CreateNewAccountButton);

        JButton EditExistingAccountButton = new JButton("Edit Existing Account");
        EditExistingAccountButton.setFont(new Font("Dialog", Font.BOLD, 18));
        EditExistingAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EditExistingAccountButton.setBackground(CommonConstants.TEXT_COLOR);
        EditExistingAccountButton.setForeground(CommonConstants.PRIMARY_COLOR);
        EditExistingAccountButton.setBounds(43, 400, 420, 60);
        adminPanel.add(EditExistingAccountButton);
    }

   /* JTabbedPane tabbedPane = new JTabbedPane ();
        tabbedPane.setBounds (0, 0, 1000, 800);
        add (tabbedPane);

        JPanel adminPanel = new JPanel ();
        tabbedPane.addTab ("Admin", adminPanel);
        JPanel agentPanel = new JPanel ();
        tabbedPane.addTab ("Agent", agentPanel);

        adminPanel.setLayout (null);
        agentPanel.setLayout (null);

        JLabel activateAccountLabel = new JLabel ("Activate Account");
        activateAccountLabel.setFont (new Font ("Dialog", Font.BOLD, 24));
        activateAccountLabel.setBounds (60, -20, 200, 100);
        adminPanel.add (activateAccountLabel);

        JLabel username = new JLabel ("Username");
        username.setFont (new Font ("Dialog", Font.BOLD, 20));
        username.setBounds (5, 60, 100, 30);
        adminPanel.add (username);

        JTextField usernameTextField = new JTextField ();
        usernameTextField.setBounds (110, 65, 200, 30);
        adminPanel.add (usernameTextField);

        JLabel password = new JLabel ("Password");
        password.setFont (new Font ("Dialog", Font.BOLD, 20));
        password.setBounds (5, 75, 100, 100);
        adminPanel.add (password);

        JPasswordField passwordTextField = new JPasswordField ();
        passwordTextField.setBounds (110, 115, 200, 30);
        adminPanel.add (passwordTextField);*/

}
