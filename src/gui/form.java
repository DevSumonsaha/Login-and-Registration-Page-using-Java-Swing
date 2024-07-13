package gui;

import javax.swing.JFrame;

import constrants.commonconstrants;

public class form extends JFrame {
    //create a constructor
    public form(String title){
        //set the title of the JFrame title bar
        super(title);

        //set the size of the GUI
        setSize(520,680);

        //configure GUI to end process after closing
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set layout to NULL to disable layout management to place component whatever we want
        setLayout(null);

        //login gui in the center of the screen
        setLocationRelativeTo(null);

        //create the bakground color of gui
        getContentPane().setBackground(commonconstrants.PRIMARY_COLOR);

    }
}
