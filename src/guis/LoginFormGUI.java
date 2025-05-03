package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import myJDBC.myDB;

public class LoginFormGUI extends Form{

    public LoginFormGUI() {
        super("Login");
        addGuiComponents();
    }

    private void addGuiComponents() {
        // create a login label
        JLabel loginLabel = new JLabel ("Login");

        //configure the component's x,y position and width/height values relative to the GUI
        loginLabel.setBounds (0, 25, 520, 100);

        // change the font color
        loginLabel.setForeground (CommonConstants.TEXT_COLOR);

        // change the font size
        loginLabel.setFont (new Font ("Dialog", Font.BOLD, 40));

        // center text
        loginLabel.setHorizontalAlignment (SwingConstants.CENTER);

        // add component to GUI
        add (loginLabel);

        // create username label
        JLabel usernameLabel = new JLabel ("Username");

        //configure component properties
        usernameLabel.setBounds (30, 0, 400, 400);
        usernameLabel.setForeground (CommonConstants.TEXT_COLOR);
        usernameLabel.setFont (new Font ("Dialog", Font.PLAIN, 18));
        add (usernameLabel);

        // create a username text field
        JTextField usernameTextField = new JTextField ();
        usernameTextField.setBounds (43, 230, 420, 50);
        usernameTextField.setForeground (CommonConstants.TEXT_COLOR);
        usernameTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        usernameTextField.setFont (new Font ("Dialog", Font.PLAIN, 24));
        add (usernameTextField);


        // create username label
        JLabel passwordLabel = new JLabel ("Password");

        //configure component properties
        passwordLabel.setBounds (30, 110, 400, 400);
        passwordLabel.setForeground (CommonConstants.TEXT_COLOR);
        passwordLabel.setFont (new Font ("Dialog", Font.PLAIN, 18));
        add (passwordLabel);

        // create a username text field
        JPasswordField passwordTextField = new JPasswordField ();
        passwordTextField.setBounds (43, 330, 420, 50);
        passwordTextField.setForeground (CommonConstants.TEXT_COLOR);
        passwordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        passwordTextField.setFont (new Font ("Dialog", Font.PLAIN, 24));
        passwordTextField.setCursor (Cursor.getPredefinedCursor (Cursor.TEXT_CURSOR));
        add (passwordTextField);

        getContentPane ().setBackground (CommonConstants.SECONDARY_COLOR);


        //create a login button
        JButton loginButton = new JButton ("Login");
        loginButton.setFont (new Font ("Dialog", Font.PLAIN, 18));

        // change cursor to hand while hovering
        loginButton.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        loginButton.setBackground (CommonConstants.TEXT_COLOR);
        loginButton.setBounds (130, 430, 240, 50);
        loginButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (login (usernameTextField.getText (), passwordTextField.getText ())) {
                    JOptionPane.showMessageDialog (LoginFormGUI.this, "login Successful");
                } else {
                    JOptionPane.showMessageDialog (LoginFormGUI.this, "login Failed");
                }

            }
        });
        add (loginButton);

        //create a register label
        JLabel registerLabel = new JLabel ("New User? Register Here");
        registerLabel.setHorizontalAlignment (SwingConstants.CENTER);
        registerLabel.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        registerLabel.setForeground (CommonConstants.TEXT_COLOR);
        registerLabel.setBounds (130, 500, 240, 50);

        registerLabel.addMouseListener (new MouseAdapter () {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked (e);
                LoginFormGUI.this.dispose ();
                new RegisterFormGUI ().setVisible (true);
            }
        });
        
        add(registerLabel);
    }
    
    public boolean login(String username, String password){
        return myDB.loginUser (username,password);
    }
}
