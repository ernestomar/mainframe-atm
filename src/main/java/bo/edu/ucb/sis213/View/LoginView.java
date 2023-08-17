package bo.edu.ucb.sis213.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import bo.edu.ucb.sis213.Controller.App;
import bo.edu.ucb.sis213.Controller.DatabaseConfig;
import bo.edu.ucb.sis213.Model.BackModel;

public class LoginView {
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JFrame frame;
    private Connection connection;

    private BackModel model;
    private MenuView menuView;  
    // private App controller;

    public LoginView(Connection connection) {
        this.connection = connection;
        // this.controller = controller;
        this.model = new BackModel(connection);
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
                // frame.setVisible(false);
                // close();
                // frame.dispose();
                validarCredenciales(userField.getText(), new String(pinField.getPassword()));
                close();
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
    public void validarCredenciales(String usuario, String pin) {
        if (model.validarCredenciales(usuario, pin)) {
            close();
            menuView = new MenuView(connection, usuario);
            System.out.println("ABRIMOS MENU :D");
        } else {
            int intentosRestantes = model.getIntentosRestantes();
            if (intentosRestantes > 0) {
                System.out.println("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");//joption2
            } else {
                System.out.println("PIN incorrecto. Ha excedido el número de intentos."); //joption3
                System.exit(0);
            }
        }
    }

    public void close() {
        frame.dispose();
    }
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DatabaseConfig.getConnection();
            
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");//joption1
            ex.printStackTrace();
            System.exit(1);
        }

        new LoginView(connection);
    }
}
