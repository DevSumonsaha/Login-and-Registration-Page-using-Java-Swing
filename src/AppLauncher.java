import javax.swing.SwingUtilities;

import backend.myJDBC;
import gui.LoginFormGui;
import gui.RegisterFormGui;

public class AppLauncher {

    public static void main(String[] args) {
        //we use invokeLater() to make updates to GUI to make more thred safe and efficient
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //instance of login form gui
                new LoginFormGui().setVisible(true);
                // new RegisterFormGui().setVisible(true);

                //check user test
                // System.out.println(myJDBC.checkUser("sumonsaha"));

                //check register test
                // System.out.println(myJDBC.regester("username", "password"));
                // System.out.println(myJDBC.regester("username1234", "123456"));

                //check register test
                // System.out.println(myJDBC.validateLogin("username123", "1236"));
            }
            
        });

    }
}