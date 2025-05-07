package guis;

import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddPaymentGUI extends Form {
    private static int flightid;
    public AddPaymentGUI(int flightid) {
        super("Add Payment");
        addGuiComponents();
        AddPaymentGUI.flightid =flightid;
    }

    public static int getFlightid() {
        return flightid;
    }

    public static void setFlightid(int flightid) {
        AddPaymentGUI.flightid = flightid;
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel APLapel = new JLabel("Add Payment");
        APLapel.setForeground(CommonConstants.TEXT_COLOR);
        APLapel.setFont(new Font("Dialog", Font.BOLD, 25));
        APLapel.setHorizontalAlignment(SwingConstants.CENTER);
        APLapel.setBounds(130,30,240,50);
        add(APLapel);

        JLabel paymentMethodLabel = new JLabel("Choose Payment Method :- ");
        paymentMethodLabel.setForeground(CommonConstants.TEXT_COLOR);
        paymentMethodLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        paymentMethodLabel.setBounds(30, 100, 350, 50);
        add(paymentMethodLabel);

        JRadioButton creditCardRadioButton = new JRadioButton("Credit Card");
        creditCardRadioButton.setBounds(30, 150, 200, 50);
        creditCardRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        creditCardRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        creditCardRadioButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        creditCardRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        creditCardRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (creditCardRadioButton.isSelected()) {
                    
                    getContentPane().removeAll();
                    addGuiComponents(); 
                    creditCardRadioButton.setSelected(true); 

                    JLabel cardNumberLabel = new JLabel("Card Number : ");
                    cardNumberLabel.setForeground(CommonConstants.TEXT_COLOR);
                    cardNumberLabel.setFont(new Font("Dialog", Font.BOLD, 25));
                    cardNumberLabel.setBounds(30, 250, 250, 50);
                    add(cardNumberLabel);

                    JTextField cardNumberTextField = new JTextField();
                    cardNumberTextField.setBounds(230, 250, 250, 50);
                    cardNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
                    cardNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
                    cardNumberTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    cardNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
                    add(cardNumberTextField);

                    JLabel amountLabel = new JLabel("Amount : ");
                    amountLabel.setForeground(CommonConstants.TEXT_COLOR);
                    amountLabel.setFont(new Font("Dialog", Font.BOLD, 25));
                    amountLabel.setBounds(30, 350, 250, 50);
                    add(amountLabel);

                    JTextField amountTextField = new JTextField();
                    amountTextField.setBounds(230, 350, 250, 50);
                    amountTextField.setBackground(CommonConstants.PRIMARY_COLOR);
                    amountTextField.setForeground(CommonConstants.TEXT_COLOR);
                    amountTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    amountTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
                    add(amountTextField);

                    revalidate();
                    repaint();

                    JButton payButton = new JButton("Pay");
                    payButton.setBounds(80,470,350,50);
                    payButton.setForeground(CommonConstants.SECONDARY_COLOR);
                    payButton.setBackground(CommonConstants.TEXT_COLOR);
                    payButton.setFont(new Font("Dialog", Font.BOLD, 25));
                    payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    payButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {

                            JOptionPane.showMessageDialog(null, "Booking Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            navigateBack();
                        }
                    });
                    add(payButton);
                }
            }
        });
        add(creditCardRadioButton);

        JRadioButton cashRadioButton = new JRadioButton("Cash");
        cashRadioButton.setBounds(250, 150, 200, 50);
        cashRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        cashRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        cashRadioButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        cashRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cashRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (cashRadioButton.isSelected()) {
                    
                    getContentPane().removeAll();
                    addGuiComponents(); 
                    cashRadioButton.setSelected(true); 

                    JLabel cashLabel = new JLabel("Cash Amount : ");
                    cashLabel.setForeground(CommonConstants.TEXT_COLOR);
                    cashLabel.setFont(new Font("Dialog", Font.BOLD, 25));
                    cashLabel.setBounds(30, 250, 250, 50);
                    add(cashLabel);

                    JTextField cashTextField = new JTextField();
                    cashTextField.setBounds(230, 250, 250, 50);
                    cashTextField.setBackground(CommonConstants.PRIMARY_COLOR);
                    cashTextField.setForeground(CommonConstants.TEXT_COLOR);
                    cashTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    cashTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
                    add(cashTextField);

                    JButton payButton = new JButton("Pay");
                    payButton.setBounds(80,470,350,50);
                    payButton.setForeground(CommonConstants.SECONDARY_COLOR);
                    payButton.setBackground(CommonConstants.TEXT_COLOR);
                    payButton.setFont(new Font("Dialog", Font.BOLD, 25));
                    payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    payButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            myDB.addPayment (CommonConstants.CURRENT_USER_ID,flightid,Integer.parseInt(cashTextField.getText()),"Cash");
                        }
                    });
                    add(payButton);

                    
                    revalidate();
                    repaint();
                }
            }
        });
        add(cashRadioButton);

        ButtonGroup paymentMethodGroup = new ButtonGroup();
        paymentMethodGroup.add(creditCardRadioButton);
        paymentMethodGroup.add(cashRadioButton);




        JButton BackButton = new JButton("Back");
        BackButton.setBounds(80,550,350,50);
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
