import guis.*;
import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args){
        // We use invokeLater() to make updates to GUI more thread safe and efficient
        SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run() {
                // instantiate an LoginFormGUI object and make it visible
                new  LoginFormGUI ().setVisible (true);
            }
        });
    }
}
