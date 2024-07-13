package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.myJDBC;
import constrants.commonconstrants;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterFormGui extends form{
    public RegisterFormGui(){
        super("Register");
        addGuiComponents();
    }

    public void addGuiComponents(){
        //create a register label
        JLabel regesterLabel = new JLabel("Register");

        //configure components x,y position and width/height values relativly
        regesterLabel.setBounds(0, 35, 520, 100);

        //change the font color
        regesterLabel.setForeground(commonconstrants.TEXT_COLOR);

        //change the font size
        regesterLabel.setFont(new Font("Dialog", Font.BOLD,40));

        //center text
        regesterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //create a UserName label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(commonconstrants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN,19));

        //create a UserName textfield
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(30,180,450,55);
        usernameTextField.setBackground(commonconstrants.SECOUNDARY_COLOR);
        usernameTextField.setForeground(commonconstrants.TEXT_COLOR);
        usernameTextField.setFont(new Font("Dialog", Font.PLAIN,22));

        //create a Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 250, 400, 25);
        passwordLabel.setForeground(commonconstrants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,19));

        //create a Password textfield
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(30,290,450,55);
        passwordTextField.setBackground(commonconstrants.SECOUNDARY_COLOR);
        passwordTextField.setForeground(commonconstrants.TEXT_COLOR);
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN,22));

        //create a re-enter Password label
        JLabel repasswordLabel = new JLabel("Re-enter Password");
        repasswordLabel.setBounds(30, 365, 400, 25);
        repasswordLabel.setForeground(commonconstrants.TEXT_COLOR);
        repasswordLabel.setFont(new Font("Dialog", Font.PLAIN,19));

        //create a Password textfield
        JPasswordField repasswordTextField = new JPasswordField();
        repasswordTextField.setBounds(30,395,450,55);
        repasswordTextField.setBackground(commonconstrants.SECOUNDARY_COLOR);
        repasswordTextField.setForeground(commonconstrants.TEXT_COLOR);
        repasswordTextField.setFont(new Font("Dialog", Font.PLAIN,22));


        //create a register button
        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Dialog", Font.BOLD,20));
        registerButton.setBounds(125,490,250,50);
        registerButton.setBackground(commonconstrants.SECOUNDARY_COLOR);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //backend code goes here
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordTextField.getPassword());
                String repassword = new String(repasswordTextField.getPassword());

                //validate user input
                if (validateUserInput(username,password,repassword)) {
                    if (myJDBC.regester(username, repassword)) {
                        //dispose the register gui
                        RegisterFormGui.this.dispose();

                        //take user back to gui
                        LoginFormGui loginFormGui = new LoginFormGui();
                        loginFormGui.setVisible(true);

                        //create a result dialog box
                        JOptionPane.showMessageDialog(loginFormGui,"Registed Account Successfull");
                    }
                    else{
                        //registered failed 
                        JOptionPane.showMessageDialog(RegisterFormGui.this,"Username/Password Already Taken");
                    }
                }
                else{
                    //invalid user input
                    JOptionPane.showMessageDialog(RegisterFormGui.this,"Username/Password Must Be Atleast 6 Character's Long");
                }
            }
            
        });

        //create a login label to redirect to the login gui
        JLabel loginLabel = new JLabel("Already a User? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(125,580,250,40);
        loginLabel.setForeground(commonconstrants.TEXT_COLOR);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

         //Adding functionality so that when we click it will launch the login form gui
         loginLabel.addMouseListener((MouseListener) new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
               //diapose login form gui
               RegisterFormGui.this.dispose();
               //launch the register form gui
               new LoginFormGui().setVisible(true); 
            }

            
        });

        //add components
        add(regesterLabel);

        add(usernameLabel);
        add(usernameTextField);

        add(passwordLabel);
        add(passwordTextField);

        add(repasswordLabel);
        add(repasswordTextField);

        add(registerButton);

        add(loginLabel);
    }

    //so here we are going to validate our user input, making sure that user has placed a valid username and password
    private boolean validateUserInput(String username,String password,String repassword){
        //all field must have a value
        if (username.length() == 0 || password.length() ==0 || repassword.length() == 0) {
            return false;
        }
        //username must have 6 character
        if (username.length()<6) {
            return false;
        }

        //password and password must have 6 character
        if (!password.equals(repassword)) {
            return false;
        }
        return true;
    }
}

