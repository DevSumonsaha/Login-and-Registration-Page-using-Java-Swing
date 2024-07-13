package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.myJDBC;
import constrants.commonconstrants;

public class LoginFormGui extends form {
    public LoginFormGui() {
        super("Login");
        addGuiComponents();
    }

    public void addGuiComponents() {
        // create a login label
        JLabel loginLabel = new JLabel("Login");

        // configure components x,y position and width/height values relativly
        loginLabel.setBounds(0, 50, 520, 100);

        // change the font color
        loginLabel.setForeground(commonconstrants.TEXT_COLOR);

        // change the font size
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        // center text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // create a UserName label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 210, 400, 25);
        usernameLabel.setForeground(commonconstrants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 19));

        // create a UserName textfield
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(30, 240, 450, 55);
        usernameTextField.setBackground(commonconstrants.SECOUNDARY_COLOR);
        usernameTextField.setForeground(commonconstrants.TEXT_COLOR);
        usernameTextField.setFont(new Font("Dialog", Font.PLAIN, 22));

        // create a Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setForeground(commonconstrants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 19));

        // create a Password textfield
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(30, 365, 450, 55);
        passwordTextField.setBackground(commonconstrants.SECOUNDARY_COLOR);
        passwordTextField.setForeground(commonconstrants.TEXT_COLOR);
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 22));

        // create a Login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setBounds(125, 480, 250, 50);
        loginButton.setBackground(commonconstrants.SECOUNDARY_COLOR);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // backend goes here
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // retrive the username and password value from our user
                // get username
                String username = usernameTextField.getText();
                String password = new String(passwordTextField.getPassword());

                // username and password validation
                if (myJDBC.validateLogin(username, password)) {
                    // login successful
                    JOptionPane.showMessageDialog(LoginFormGui.this, "Login Successfull");
                } else {
                    //login failed
                    JOptionPane.showMessageDialog(LoginFormGui.this, "Login Failed, Please check your Credentials");
                }
            }

        });

        // create a register label to redirect to the register gui
        JLabel registerLabel = new JLabel("Not a User? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(125, 580, 250, 40);
        registerLabel.setForeground(commonconstrants.TEXT_COLOR);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Adding functionality so that when we click it will launch the register form
        // gui
        registerLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // diapose login form gui
                LoginFormGui.this.dispose();
                // launch the register form gui
                new RegisterFormGui().setVisible(true);
            }

        });

        // add components
        add(loginLabel);

        add(usernameLabel);
        add(usernameTextField);

        add(passwordLabel);
        add(passwordTextField);

        add(loginButton);

        add(registerLabel);
    }
}
