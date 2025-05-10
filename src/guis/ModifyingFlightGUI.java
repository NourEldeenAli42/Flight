package guis;


import com.toedter.calendar.JDateChooser;
import components.CommonConstants;
import myJDBC.myDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ModifyingFlightGUI extends Form {
    
    public ModifyingFlightGUI() {
        super("Modify Flight");
        addGuiComponents();
    }

    public void addGuiComponents() {
        
    getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel CNFLapel = new JLabel("Modify Flight");
        CNFLapel.setForeground(CommonConstants.TEXT_COLOR);
        CNFLapel.setFont(new Font("Dialog", Font.BOLD, 25));
        CNFLapel.setHorizontalAlignment(SwingConstants.CENTER);
        CNFLapel.setBounds(130,30,240,50);
        add(CNFLapel);

        JLabel flightNumberLabel = new JLabel("Flight Number \"to be modified\":");
        flightNumberLabel.setForeground(CommonConstants.TEXT_COLOR);
        flightNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightNumberLabel.setBounds(30, -65, 400, 400);
        add(flightNumberLabel);



        JLabel flightOrigin = new JLabel("Flight Origin:");
        flightOrigin.setForeground(CommonConstants.TEXT_COLOR);
        flightOrigin.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightOrigin.setBounds(30, -60+40, 400, 400);
        add(flightOrigin);

        JTextField flightOriginTextField = new JTextField();
        flightOriginTextField.setBounds(140, 125+40, 200, 30);
        flightOriginTextField.setForeground(CommonConstants.TEXT_COLOR);
        flightOriginTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        flightOriginTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightOriginTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        flightOriginTextField.addKeyListener (new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = flightOriginTextField.getText();

                if (text.length() >= 20) {
                    e.consume();
                    return;
                }
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ') {
                    e.consume();
                }
            }
        });
        add (flightOriginTextField);

        JLabel flightDestination = new JLabel("Flight Destination:");
        flightDestination.setForeground(CommonConstants.TEXT_COLOR);
        flightDestination.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightDestination.setBounds(30, -10+40, 400, 400);
        add(flightDestination);

        JTextField flightDestinationTextField = new JTextField();
        flightDestinationTextField.setBounds(180, 175+40, 200, 30);
        flightDestinationTextField.setForeground(CommonConstants.TEXT_COLOR);
        flightDestinationTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        flightDestinationTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightDestinationTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        flightDestinationTextField.addKeyListener (new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = flightOriginTextField.getText();

                if (text.length() >= 20) {
                    e.consume();
                    return;
                }
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ') {
                    e.consume();
                }
            }
        });
        add (flightDestinationTextField);

        JLabel flightDate = new JLabel("Flight Date:");
        flightDate.setForeground(CommonConstants.TEXT_COLOR);
        flightDate.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightDate.setBounds(30, 50+40, 400, 400);
        add(flightDate);


        JDateChooser flightDateChooser = new JDateChooser();
        flightDateChooser.setBounds(130, 235+40, 200, 30); // Using the same position as your text field
        flightDateChooser.setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getCalendarButton().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getDateEditor().getUiComponent().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getCalendarButton().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getDateEditor().getUiComponent().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getJCalendar().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getJCalendar().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.getJCalendar().getDayChooser().getDayPanel().setBackground(CommonConstants.PRIMARY_COLOR);
        flightDateChooser.getJCalendar().getDayChooser().getDayPanel().setForeground(CommonConstants.TEXT_COLOR);
        flightDateChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightDateChooser.setDateFormatString("yyyy-MM-dd");
        add(flightDateChooser);

        JLabel setPrice = new JLabel("Set Price:");
        setPrice.setForeground(CommonConstants.TEXT_COLOR);
        setPrice.setFont(new Font("Dialog", Font.PLAIN, 18));
        setPrice.setBounds(30, 105+40, 400, 400);
        add(setPrice);

        JTextField setPriceTextField = new JTextField();
        JLabel aClassSeats = new JLabel("A Class Seats:");
        aClassSeats.setForeground(CommonConstants.TEXT_COLOR);
        aClassSeats.setFont(new Font("Dialog", Font.PLAIN, 18));
        aClassSeats.setBounds(30, 160+40, 400, 400);
        add(aClassSeats);

        JTextField aClassSeatsTextField = new JTextField();
        JTextField aClassPriceTextField = new JTextField();
        aClassPriceTextField.setEditable(false);

        setPriceTextField.setBounds(120, 290+40, 200, 30);
        setPriceTextField.setForeground(CommonConstants.TEXT_COLOR);
        setPriceTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        setPriceTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        setPriceTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setPriceTextField.addKeyListener (new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = setPriceTextField.getText();

                if (text.length() >= 5) {
                    e.consume();
                    return;
                }
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        add (setPriceTextField);


        aClassSeatsTextField.setBounds(158, 345+40, 100, 30);
        aClassSeatsTextField.setForeground(CommonConstants.TEXT_COLOR);
        aClassSeatsTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        aClassSeatsTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        aClassSeatsTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        aClassSeatsTextField.addKeyListener (new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = aClassSeatsTextField.getText();

                if (text.length() >= 3) {
                    e.consume();
                    return;
                }
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        add (aClassSeatsTextField);


        aClassPriceTextField.setBounds(270, 345+40, 100, 30);
        aClassPriceTextField.setForeground(CommonConstants.TEXT_COLOR);
        aClassPriceTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        aClassPriceTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        aClassPriceTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        aClassPriceTextField.setEditable(false);
        add (aClassPriceTextField);


        JLabel bClassSeats = new JLabel("B Class Seats:");
        bClassSeats.setForeground(CommonConstants.TEXT_COLOR);
        bClassSeats.setFont(new Font("Dialog", Font.PLAIN, 18));
        bClassSeats.setBounds(30, 200+40, 400, 400);
        add(bClassSeats);

        JTextField bClassSeatsTextField = new JTextField();
        JTextField bClassPriceTextField = new JTextField();
        bClassPriceTextField.setEditable(false);
        bClassSeatsTextField.setBounds(158, 385+40, 100, 30);
        bClassSeatsTextField.setForeground(CommonConstants.TEXT_COLOR);
        bClassSeatsTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        bClassSeatsTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        bClassSeatsTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        bClassSeatsTextField.addKeyListener (new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = bClassSeatsTextField.getText();

                if (text.length() >= 3) {
                    e.consume();
                    return;
                }
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        add (bClassSeatsTextField);


        bClassPriceTextField.setBounds(270, 385+40, 100, 30);
        bClassPriceTextField.setForeground(CommonConstants.TEXT_COLOR);
        bClassPriceTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        bClassPriceTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        bClassPriceTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        bClassPriceTextField.setEditable(false);
        add (bClassPriceTextField);


        JLabel cClassSeats = new JLabel("C Class Seats:");
        cClassSeats.setForeground(CommonConstants.TEXT_COLOR);
        cClassSeats.setFont(new Font("Dialog", Font.PLAIN, 18));
        cClassSeats.setBounds(30, 240+40, 400, 400);
        add(cClassSeats);

        JTextField cClassSeatsTextField = new JTextField();
        JTextField cClassPriceTextField = new JTextField();
        cClassPriceTextField.setEditable(false);
        cClassSeatsTextField.setBounds(158, 425+40, 100, 30);
        cClassSeatsTextField.setForeground(CommonConstants.TEXT_COLOR);
        cClassSeatsTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        cClassSeatsTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        cClassSeatsTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        cClassSeatsTextField.addKeyListener (new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = cClassSeatsTextField.getText();

                if (text.length() >= 3) {
                    e.consume();
                    return;
                }
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        add (cClassSeatsTextField);


        cClassPriceTextField.setBounds(270, 425+40, 100, 30);
        cClassPriceTextField.setForeground(CommonConstants.TEXT_COLOR);
        cClassPriceTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        cClassPriceTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        cClassPriceTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        cClassPriceTextField.setEditable(false);
        add (cClassPriceTextField);


        setPriceTextField.getDocument().addDocumentListener(new DocumentListener () {
            public void changedUpdate(DocumentEvent e) {
                updatePrice();
            }
            public void removeUpdate(DocumentEvent e) {
                updatePrice();
            }
            public void insertUpdate(DocumentEvent e) {
                updatePrice();
            }

            private void updatePrice() {
                try {
                    String input = setPriceTextField.getText();
                    if (!input.isEmpty()) {
                        double basePrice = Double.parseDouble(input);
                        double aClassPrice = basePrice * 1.5;
                        aClassPriceTextField.setText(aClassPrice+"$");
                        bClassPriceTextField.setText(basePrice+"$");
                        cClassPriceTextField.setText (basePrice*0.5+"$");
                    } else {
                        aClassPriceTextField.setText("");
                        bClassPriceTextField.setText ("");
                        cClassPriceTextField.setText ("");
                    }
                } catch (NumberFormatException ex) {
                    aClassPriceTextField.setText("Invalid input");
                    bClassPriceTextField.setText ("Invalid input");
                    cClassPriceTextField.setText ("Invalid input");
                }
            }
        });

        JTextField flightNumberTextField = new JTextField();
        flightNumberTextField.setBounds(285, 120, 100, 30);
        flightNumberTextField.setForeground(CommonConstants.TEXT_COLOR);
        flightNumberTextField.setBackground(CommonConstants.PRIMARY_COLOR);
        flightNumberTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
        flightNumberTextField.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        flightNumberTextField.addKeyListener (new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = flightNumberTextField.getText();

                if (text.length() >= 5) {
                    e.consume();
                    return;
                }
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
                if (e.getKeyChar () == KeyEvent.VK_ENTER) {
                   if (myDB.checkFlight (Integer.parseInt (flightNumberTextField.getText ()))){
                          JOptionPane.showMessageDialog (null,"Flight not found!","Error",JOptionPane.ERROR_MESSAGE);
                   } else {
                       int flightID = Integer.parseInt (flightNumberTextField.getText ());
                    flightOriginTextField.setText (myDB.getFlightOrigin (flightID));
                    flightDestinationTextField.setText (myDB.getFlightDestination (flightID));
                    flightDateChooser.setDate (myDB.getFlightDate (flightID));
                    int flightPrice = myDB.getFlightPrice (flightID);
                    setPriceTextField.setText (String.valueOf (flightPrice));
                    aClassSeatsTextField.setText (String.valueOf (myDB.getFlightASeats (flightID)));
                    bClassSeatsTextField.setText (String.valueOf (myDB.getFlightBSeats (flightID)));
                    cClassSeatsTextField.setText (String.valueOf (myDB.getFlightCSeats (flightID)));
                    }
                }
            }
        });
        add (flightNumberTextField);

        JButton modifyFlight = new JButton("Modify Flight");
        modifyFlight.setBounds(103, 490+40, 300, 40);
        modifyFlight.setBackground(CommonConstants.TEXT_COLOR);
        modifyFlight.setForeground(CommonConstants.SECONDARY_COLOR);
        modifyFlight.setFont(new Font("Dialog", Font.BOLD, 20));
        modifyFlight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modifyFlight.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                myDB.modifyFlight (Integer.parseInt (flightNumberTextField.getText ()),flightOriginTextField.getText (),flightDestinationTextField.getText (),
                        flightDateChooser.getDate (),Integer.parseInt (setPriceTextField.getText ()),
                        Integer.parseInt (aClassSeatsTextField.getText ()),Integer.parseInt (bClassSeatsTextField.getText ())
                        ,Integer.parseInt (cClassSeatsTextField.getText ()));
                navigateBack ();
            }
        });
        add (modifyFlight);



        JLabel backButton = new JLabel("Back");
        backButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(CommonConstants.TEXT_COLOR);
        backButton.setBounds(420, 20, 100, 30);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateBack();

            }
        });
        
        add(backButton);
        

    }
    
}
