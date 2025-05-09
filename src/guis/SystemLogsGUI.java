package guis;

import components.CommonConstants;
import myJDBC.myDB;

import javax.swing.*;
import java.awt.*;

public class SystemLogsGUI extends Form{
    public SystemLogsGUI(){
        super("System Logs");
        addGuiComponents();
    }
    public void addGuiComponents(){
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);
        JLabel systemLogsLabel = new JLabel("System Logs");
        systemLogsLabel.setForeground(CommonConstants.TEXT_COLOR);
        systemLogsLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        systemLogsLabel.setBounds(0, 25, 520, 100);
        systemLogsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(systemLogsLabel);

        JTextArea logsTextArea = new JTextArea();
        logsTextArea.setBounds(35, 150, 440, 350);
        logsTextArea.setBackground(CommonConstants.PRIMARY_COLOR);
        logsTextArea.setForeground(CommonConstants.TEXT_COLOR);
        logsTextArea.setFont(new Font ("Dialog", Font.PLAIN, 18));
        logsTextArea.setEditable(false);
        add(logsTextArea);


        JButton loadLogsButton = new JButton("Show Logs");
        loadLogsButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loadLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loadLogsButton.setBackground(CommonConstants.TEXT_COLOR);
        loadLogsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        loadLogsButton.setBounds(35, 520, 200, 50);
        loadLogsButton.addActionListener(e -> {
            String logs = myDB.showLogs();
            logsTextArea.setText(logs);
        });
        add(loadLogsButton);


        JButton clearLogsButton = new JButton("Clear Logs");
        clearLogsButton.setFont(new Font("Dialog", Font.BOLD, 18));
        clearLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearLogsButton.setBackground(CommonConstants.TEXT_COLOR);
        clearLogsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        clearLogsButton.setBounds(275, 520, 200, 50);
        clearLogsButton.addActionListener(e -> {
            logsTextArea.setText("");
        });
        add(clearLogsButton);


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
