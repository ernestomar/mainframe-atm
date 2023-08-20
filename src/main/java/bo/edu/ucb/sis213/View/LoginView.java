package bo.edu.ucb.sis213.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import bo.edu.ucb.sis213.Controller.App;

public class LoginView {
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JFrame frame;
    private App controller;


    public LoginView(Connection connection) {
        this.controller = new App(connection);
        frame = new JFrame("ATM App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        initLoginScreen();
        frame.setVisible(true);
    }

    private void initLoginScreen() {
        loginPanel = new JPanel(new GridLayout(3, 2));
        userField = new JTextField();
        pinField = new JPasswordField();
        loginButton = new JButton("Ingresar");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCredencialesV(userField.getText(), new String(pinField.getPassword()));
            }
        });

        loginPanel.add(new JLabel("Usuario:"));
        loginPanel.add(userField);
        loginPanel.add(new JLabel("PIN:"));
        loginPanel.add(pinField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        frame.add(loginPanel);
    }
    public void validarCredencialesV(String usuario, String pin) {
        if (controller.validarEntrada(usuario, pin)) {
            close();
            // menuView = new MenuView(connection, usuario);//controññer
            controller.mostrarMenu(usuario);
            System.out.println("ABRIMOS MENU :D");
        }else{
            return;
        }
    }

    public void close() {
        frame.dispose();
    }
    
}
