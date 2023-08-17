package bo.edu.ucb.sis213.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.edu.ucb.sis213.Controller.App;

public class LoginView {
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JFrame frame;
    private App controller;

    public LoginView(App controller) {
        this.controller = controller;
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
                // Llamar al controlador para validar las credenciales
                controller.validarCredenciales(userField.getText(), new String(pinField.getPassword()));
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

    public void close() {
        frame.dispose();
    }
}
