package guis;

import components.CommonConstants;
import java.awt.*;
import javax.swing.*;

public class CreateNewFlight extends Form {
    public CreateNewFlight() {
        super("Create New Flight");
        addGuiComponents();
    }

    private void addGuiComponents() {

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel CNFLapel = new JLabel("Create New Flight");
        CNFLapel.setForeground(CommonConstants.TEXT_COLOR);
        CNFLapel.setFont(new Font("Dialog", Font.BOLD, 25));
        CNFLapel.setHorizontalAlignment(SwingConstants.CENTER);
        CNFLapel.setBounds(130,30,240,50);
        add(CNFLapel);

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
