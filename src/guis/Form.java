package guis;

import javax.swing.*;

public class Form extends JFrame {
    public Form(String title) {
        super(title);

        // set the size of the GUI
        setSize(520, 680);

        // configure GUI to end a process after closing
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set layout to null to disable layout management so we can use absolute positioning
        // to place the components wherever we want
        setLayout(null);

        //load GUI in the center of the screen
        setLocationRelativeTo(null);

        //prevent resizing
        setResizable(false);
    }


}
