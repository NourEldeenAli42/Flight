package guis;

import java.awt.*;
import javax.swing.*;

public class AdminGUI extends Form {
    public AdminGUI() {
        super("Admin");
        this.setSize (1000,800);

        JTabbedPane tabbedPane = new JTabbedPane ();
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
        adminPanel.add (passwordTextField);

        
    }
}
