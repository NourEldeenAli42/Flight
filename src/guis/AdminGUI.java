package guis;

import javax.swing.*;
import java.awt.*;

public class AdminGUI extends Form {
    public AdminGUI() {
        super("Admin");
        this.setSize (1000,800);
        JLabel titleLabel = new JLabel ("Welcome to Admin Panel");
//        titleLabel.setFont (new Font ("Dialog", Font.BOLD, 30));
//        titleLabel.setHorizontalAlignment (SwingConstants.CENTER);
//        add (titleLabel);
//        JRadioButton clientRadioButton = new JRadioButton ("Client");
//        clientRadioButton.setBounds (0, 1, 40, 100);
//        clientRadioButton.setFont (new Font ("Dialog", Font.BOLD, 20));
//        JRadioButton adminRadioButton = new JRadioButton ("Admin");
//        adminRadioButton.setBounds (0, 150, 40, 100);
//        adminRadioButton.setFont (new Font ("Dialog", Font.BOLD, 20));
//        ButtonGroup buttonGroup = new ButtonGroup ();
//        buttonGroup.add (clientRadioButton);
//        buttonGroup.add (adminRadioButton);
//        add (clientRadioButton);
//        add (adminRadioButton);

        JTabbedPane tabbedPane = new JTabbedPane ();
        tabbedPane.setBounds (0, 0, 1000, 800);
        add (tabbedPane);

        JPanel clientPanel = new JPanel ();
        tabbedPane.addTab ("Client", clientPanel);
        JPanel adminPanel = new JPanel ();
        tabbedPane.addTab ("Admin", adminPanel);

        clientPanel.setLayout (null);
        adminPanel.setLayout (null);

        JLabel activateAccountLabel = new JLabel ("Activate Account");
        activateAccountLabel.setFont (new Font ("Dialog", Font.BOLD, 24));
        activateAccountLabel.setBounds (400, -20, 200, 100);
        clientPanel.add (activateAccountLabel);

        JLabel username = new JLabel ("Username");
        username.setFont (new Font ("Dialog", Font.BOLD, 20));
        username.setBounds (0, 30, 100, 30);
        clientPanel.add (username);

        JFormattedTextField usernameTextField = new JFormattedTextField ();
        usernameTextField.setBounds (100, 30, 200, 100);
        clientPanel.add (usernameTextField);

        JLabel password = new JLabel ("Password");
        password.setFont (new Font ("Dialog", Font.BOLD, 20));
        password.setBounds (0, 60, 100, 100);
        clientPanel.add (password);

        JPasswordField passwordTextField = new JPasswordField ();
        usernameTextField.setBounds (100, 60, 200, 100);
        clientPanel.add (usernameTextField);
    }

}
