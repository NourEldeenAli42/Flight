package guis;


import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GenerateReportGUI extends Form {

    public GenerateReportGUI() {
        super("Generate Report Of Flights");
        addGuiComponents();
    }

    public void addGuiComponents(){

        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel GenerateReportOfFlightsLabel = new JLabel("Generate Report Of Flights");
        GenerateReportOfFlightsLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        GenerateReportOfFlightsLabel.setForeground(CommonConstants.TEXT_COLOR);
        GenerateReportOfFlightsLabel.setBounds(0, 25, 520, 100);
        GenerateReportOfFlightsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(GenerateReportOfFlightsLabel);

        JTable report = new JTable();
        report.setBounds(43,130,420,300);
        report.setForeground(CommonConstants.TEXT_COLOR);
        report.setBackground(CommonConstants.PRIMARY_COLOR);
        report.setFont(new Font("Dialog", Font.PLAIN, 15));
        add(report);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setFont(new Font("Dialog", Font.BOLD, 25));
        generateReportButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generateReportButton.setBackground(CommonConstants.TEXT_COLOR);
        generateReportButton.setForeground(CommonConstants.PRIMARY_COLOR);
        generateReportButton.setBounds(43,500,420,60);
        add(generateReportButton);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(43,570,420,60);
        BackButton.setForeground(CommonConstants.PRIMARY_COLOR);
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
