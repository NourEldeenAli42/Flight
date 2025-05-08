package guis;

import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class AddPaymentGUI extends Form {
    private static int flightid;
    private static boolean isCash;
    public AddPaymentGUI(int flightid) {
        super("Add Payment");
        AddPaymentGUI.flightid =flightid;
        addGuiComponents();
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

        JTextField requiered = new JTextField ();
        requiered.setBounds (30, 350, 400, 50);
        requiered.setBackground (CommonConstants.SECONDARY_COLOR);
        requiered.setForeground (CommonConstants.TEXT_COLOR);
        requiered.setFont (new Font ("Dialog", Font.PLAIN, 18));
        requiered.setEditable (false);
        requiered.setText (String.valueOf (myDB.getTicketPrice (CommonConstants.CURRENT_USER_ID,AddPaymentGUI.flightid)));
        add (requiered);




        JLabel cashLabel = new JLabel("Cash Amount : ");
        cashLabel.setForeground(CommonConstants.TEXT_COLOR);
        cashLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        cashLabel.setBounds(30, 250, 250, 50);

        JTextField cashTextField = new JTextField();
        cashTextField.setBounds(230, 250, 250, 50);
        cashTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        cashTextField.setForeground(CommonConstants.TEXT_COLOR);
        cashTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        cashTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        cashTextField.addKeyListener (new KeyListener () {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar () < '0' || e.getKeyChar () > '9') {
                    e.consume ();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JLabel cardNumberLabel = new JLabel("Card Number : ");
        cardNumberLabel.setForeground(CommonConstants.TEXT_COLOR);
        cardNumberLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        cardNumberLabel.setBounds(30, 250, 250, 50);


        JTextField cardNumberTextField = new JTextField();
        cardNumberTextField.setBounds(230, 250, 250, 50);
        cardNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        cardNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
        cardNumberTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        cardNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 18));


        JLabel amountLabel = new JLabel("Amount : ");
        amountLabel.setForeground(CommonConstants.TEXT_COLOR);
        amountLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        amountLabel.setBounds(30, 350, 250, 50);


        JTextField amountTextField = new JTextField();
        amountTextField.setBounds(230, 350, 250, 50);
        amountTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        amountTextField.setForeground(CommonConstants.TEXT_COLOR);
        amountTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        amountTextField.setFont(new Font("Dialog", Font.PLAIN, 18));

        JButton payButton = new JButton("Pay");
        payButton.setBounds(80,470,350,50);
        payButton.setForeground(CommonConstants.SECONDARY_COLOR);
        payButton.setBackground(CommonConstants.TEXT_COLOR);
        payButton.setFont(new Font("Dialog", Font.BOLD, 25));
        payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (isCash) {
                    String cashAmount = cashTextField.getText();
                    if (cashAmount.isEmpty()) {
                        JOptionPane.showMessageDialog(AddPaymentGUI.this, "Please enter a cash amount.");
                        return;
                    }
                    if (Integer.parseInt (cashAmount) > myDB.getTicketPrice (CommonConstants.CURRENT_USER_ID,AddPaymentGUI.flightid)) {
                        JOptionPane.showMessageDialog(AddPaymentGUI.this, "Cash amount cannot be greater than ticket price.");
                        return;
                    }
                    myDB.addPayment(CommonConstants.CURRENT_USER_ID, flightid, Integer.parseInt (cashAmount), "Cash");
                } else {
                    String cardNumber = cardNumberTextField.getText();
                    String amount = amountTextField.getText();
                    if (cardNumber.isEmpty() || amount.isEmpty()) {
                        JOptionPane.showMessageDialog(AddPaymentGUI.this, "Please enter card number and amount.");
                        return;
                    }
                    //TODO make addPayment for credit card
                    myDB.addPayment(CommonConstants.CURRENT_USER_ID, flightid, Integer.parseInt (amount), "Credit Card");
                }
                navigateBack();
            }
        });
        add(payButton);

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


        JRadioButton cashRadioButton = new JRadioButton("Cash");
        cashRadioButton.setBounds(250, 150, 200, 50);
        cashRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        cashRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        cashRadioButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        cashRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cashRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getContentPane ().remove(cardNumberLabel);
                getContentPane ().remove(amountLabel);
                getContentPane ().remove(amountTextField);
                getContentPane ().remove(cardNumberTextField);
                add (cashLabel);
                add (cashTextField);
                revalidate();
                repaint();
                isCash = true;
            }
        });
        add(cashRadioButton);


        JRadioButton creditCardRadioButton = new JRadioButton("Credit Card");
        creditCardRadioButton.setBounds(30, 150, 200, 50);
        creditCardRadioButton.setBackground(CommonConstants.SECONDARY_COLOR);
        creditCardRadioButton.setForeground(CommonConstants.TEXT_COLOR);
        creditCardRadioButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        creditCardRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        creditCardRadioButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane ().remove(cashLabel);
                getContentPane ().remove(cashTextField);
                add (cardNumberLabel);
                add(cardNumberTextField);
                add (amountLabel);
                add(amountTextField);
                revalidate();
                repaint();
                isCash = false;
            }
        });
        add(creditCardRadioButton);

        ButtonGroup paymentMethodGroup = new ButtonGroup();
        paymentMethodGroup.add(creditCardRadioButton);
        paymentMethodGroup.add(cashRadioButton);

    }

}
