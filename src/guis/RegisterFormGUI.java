package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import myJDBC.myDB;

@SuppressWarnings("ALL")
public class RegisterFormGUI extends Form {
    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
    }
    private void addGuiComponents(){
        JLabel registerLabel = new JLabel ("New Registration");
        registerLabel.setFont (new Font("Dialog",Font.BOLD,24));
         registerLabel.setHorizontalAlignment (SwingConstants.CENTER);
         registerLabel.setForeground (CommonConstants.TEXT_COLOR);
         registerLabel.setBounds (130,30,240,50);
         add (registerLabel);
         this.getContentPane ().setBackground (CommonConstants.SECONDARY_COLOR);

        JLabel usernameLabel = new JLabel ("Username");

        //configure component properties
        usernameLabel.setBounds (30,-90,400,400);
        usernameLabel.setForeground (CommonConstants.TEXT_COLOR);
        usernameLabel.setFont (new Font("Dialog",Font.PLAIN,18));
        add (usernameLabel);

        // create a username text field
        JFormattedTextField usernameTextField = new JFormattedTextField ();
        usernameTextField.setBounds (43,130,420,30);
        usernameTextField.setForeground (CommonConstants.TEXT_COLOR);
        usernameTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        usernameTextField.setFont (new Font("Dialog",Font.PLAIN,24));
        usernameTextField.setCursor (Cursor.getPredefinedCursor (Cursor.TEXT_CURSOR));
        add(usernameTextField);

        JLabel passwordLabel = new JLabel ("Password");

        //configure component properties
        passwordLabel.setBounds (30,0,400,400);
        passwordLabel.setForeground (CommonConstants.TEXT_COLOR);
        passwordLabel.setFont (new Font("Dialog",Font.PLAIN,18));
        add (passwordLabel);

        // create a username text field
        JPasswordField passwordTextField = new JPasswordField ();
        passwordTextField.setBounds (43,220,420,30);
        passwordTextField.setForeground (CommonConstants.TEXT_COLOR);
        passwordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        passwordTextField.setFont (new Font("Dialog",Font.PLAIN,24));
        passwordTextField.setCursor (Cursor.getPredefinedCursor (Cursor.TEXT_CURSOR));
        passwordTextField.setEchoChar ('*');
        add(passwordTextField);

        JLabel rePasswordLabel = new JLabel ("Re-enter Password");

        //configure component properties
        rePasswordLabel.setBounds (30,90,400,400);
        rePasswordLabel.setForeground (CommonConstants.TEXT_COLOR);
        rePasswordLabel.setFont (new Font("Dialog",Font.PLAIN,18));
        add (rePasswordLabel);

        // create a username text field
        JPasswordField rePasswordTextField = new JPasswordField ();
        rePasswordTextField.setBounds (43,310,420,30);
        rePasswordTextField.setForeground (CommonConstants.TEXT_COLOR);
        rePasswordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        rePasswordTextField.setFont (new Font("Dialog",Font.PLAIN,24));
        rePasswordTextField.setCursor (Cursor.getPredefinedCursor (Cursor.TEXT_CURSOR));
        rePasswordTextField.setEchoChar ('*');
        add(rePasswordTextField);

        JLabel nameLabel = new JLabel ("Name");

        //configure component properties
        nameLabel.setBounds (30,180,400,400);
        nameLabel.setForeground (CommonConstants.TEXT_COLOR);
        nameLabel.setFont (new Font("Dialog",Font.PLAIN,18));
        add (nameLabel);

        // create a username text field
        JFormattedTextField nameTextField = new JFormattedTextField ();
        nameTextField.setBounds (43,400,420,30);
        nameTextField.setForeground (CommonConstants.TEXT_COLOR);
        nameTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        nameTextField.setFont (new Font("Dialog",Font.PLAIN,24));
        add(nameTextField);

        JLabel emailLabel = new JLabel ("E-Mail");

        //configure component properties
        emailLabel.setBounds (30,270,400,400);
        emailLabel.setForeground (CommonConstants.TEXT_COLOR);
        emailLabel.setFont (new Font("Dialog",Font.PLAIN,18));
        add (emailLabel);

        // create a username text field
        JFormattedTextField emailTextField = new JFormattedTextField ();
        emailTextField.setBounds (43,490,420,30);
        emailTextField.setForeground (CommonConstants.TEXT_COLOR);
        emailTextField.setBackground (CommonConstants.PRIMARY_COLOR);
        emailTextField.setFont (new Font("Dialog",Font.PLAIN,24));
        add(emailTextField);

        JButton registerButton = new JButton ("Register!");
        registerButton.setFont (new Font("Dialog",Font.BOLD,30));
        registerButton.setBounds (210,550,200,50);
        registerButton.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        registerButton.setForeground (CommonConstants.SECONDARY_COLOR);
        registerButton.setBackground (CommonConstants.TEXT_COLOR);
        registerButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePassword (passwordTextField, rePasswordTextField)){
                registerUser (usernameTextField.getText (), passwordTextField.getText(), rePasswordTextField.getText (),
                        nameTextField.getText (), emailTextField.getText ());
                }
            }
        });
        add(registerButton);

        JButton backButton = new JButton ("Back");
        backButton.setFont (new Font("Dialog",Font.BOLD,30));
        backButton.setBounds (80,550,120,50);
        backButton.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
        backButton.setForeground (CommonConstants.SECONDARY_COLOR);
        backButton.setBackground (CommonConstants.TEXT_COLOR);
        backButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateBack ();
            }
        });
        add(backButton);
    }

    private boolean registerUser(String username, String password,String rePassword, String name, String email){
        if (!validateInput (username, password, rePassword)) {
            JOptionPane.showMessageDialog (null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if(myDB.registerUser (username, password, name, email)){
                JOptionPane.showMessageDialog (RegisterFormGUI.this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                navigateBack ();
                return true;

            }else{
                JOptionPane.showMessageDialog (RegisterFormGUI.this, "Registration Failed\nUsername Taken", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
    protected boolean validateInput(String username, String password, String rePassword){
        if (username.isEmpty () || password.isEmpty () || rePassword.isEmpty ()) return false;
        if (!password.equals (rePassword)) return false;
        if (username.length () < 5 || username.length () > 15) return false;
        return true;
    }

    private boolean validatePassword(JPasswordField passwordTextField, JPasswordField rePasswordTextField){
        if (!passwordTextField.getText ().equals (rePasswordTextField.getText ())) {
            rePasswordTextField.setForeground (CommonConstants.ERROR_COLOR);
            JOptionPane.showMessageDialog (null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            rePasswordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
            rePasswordTextField.setForeground (CommonConstants.TEXT_COLOR);
        }
        if (passwordTextField.getText ().length () < 5 || passwordTextField.getText ().length () > 15) {
            passwordTextField.setForeground (CommonConstants.ERROR_COLOR);
            JOptionPane.showMessageDialog (null, "Password must be between 5 and 15 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            passwordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
            passwordTextField.setForeground (CommonConstants.TEXT_COLOR);
        }
        if (passwordTextField.getText ().isEmpty ()) {
            passwordTextField.setForeground (CommonConstants.ERROR_COLOR);
            JOptionPane.showMessageDialog (null, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            passwordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
            passwordTextField.setForeground (CommonConstants.TEXT_COLOR);
        }
        if (passwordTextField.getText ().contains (" ")) {
            passwordTextField.setForeground (CommonConstants.ERROR_COLOR);
            JOptionPane.showMessageDialog (null, "Password cannot contain spaces", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            passwordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
            passwordTextField.setForeground (CommonConstants.TEXT_COLOR);
        }
        if (!passwordTextField.getText ().contains ("*") && !passwordTextField.getText ().contains ("#")
        && !passwordTextField.getText ().contains ("_")&&!passwordTextField.getText ().contains ("/")&&
                !passwordTextField.getText ().contains ("%")&&!passwordTextField.getText ().contains ("&")&&
                !passwordTextField.getText ().contains ("@")&&!passwordTextField.getText ().contains ("!")) {
            passwordTextField.setForeground (CommonConstants.ERROR_COLOR);
            JOptionPane.showMessageDialog (null, "Password must contain at least one special character", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            passwordTextField.setBackground (CommonConstants.PRIMARY_COLOR);
            passwordTextField.setForeground (CommonConstants.TEXT_COLOR);
        }
        return true;
    }
}
