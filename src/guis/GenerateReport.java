package guis;

import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GenerateReport extends Form {
    public GenerateReport() {
        super("Generate Report");
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel GRLapel = new JLabel("Generate Report");
        GRLapel.setForeground(CommonConstants.TEXT_COLOR);
        GRLapel.setFont(new Font("Dialog", Font.BOLD, 24));
        GRLapel.setHorizontalAlignment(SwingConstants.CENTER);
        GRLapel.setBounds(130,30,240,50);
        add(GRLapel);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(80,550,350,50);
        BackButton.setForeground(CommonConstants.PRIMARY_COLOR);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setFont(new Font("Dialog", Font.BOLD, 18));
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                navigateBack();
            }
        });
        add(BackButton);
    }
    
}
