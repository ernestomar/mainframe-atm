package bo.edu.ucb.sis213.screens;
import bo.edu.ucb.sis213.Conexion;
import bo.edu.ucb.sis213.GestorUsuario;
import bo.edu.ucb.sis213.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private JFrame frame;
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;

    public Login(Connection connection) {
        frame = new JFrame("ATM Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        initLoginScreen(connection);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initLoginScreen(Connection connection) {
        loginPanel = new JPanel(new GridBagLayout());
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        loginPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Banco Luna", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(letras); // Aplicar color al título
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        loginPanel.add(titleLabel, constraints);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setForeground(letras); // Aplicar color a la etiqueta de usuario
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 70, 10, 0);
        loginPanel.add(userLabel, constraints);

        userField = new JTextField();
        userField.setForeground(bckg);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 2.0;
        constraints.insets = new Insets(10, -70, 10, 40); // Adjusted insets
        loginPanel.add(userField, constraints);

        JLabel pinLabel = new JLabel("Contraseña:");
        pinLabel.setForeground(bckg);
        pinLabel.setForeground(letras);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 50, 10, 0);
        loginPanel.add(pinLabel, constraints);

        pinField = new JPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, -70, 10, 40);
        loginPanel.add(pinField, constraints);

        loginButton = new JButton("Ingresar");
        loginButton.setBackground(Color.orange);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 50, 10, 50);
        loginPanel.add(loginButton, constraints);

        // Agregar ActionListener al botón de ingreso
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                int contrasena = Integer.parseInt(new String(pinField.getPassword()));

                System.out.println("Usuario: " + username);
                System.out.println("Contraseña: " + contrasena);

                GestorUsuario gestorUsuario = new GestorUsuario(connection);
                Usuario usuario = new Usuario(connection, 0, 0, 0, null);
                if (gestorUsuario.validarPIN(usuario, contrasena)) {
                    System.out.println("Bienvenid@ " + usuario.getNombreUser() + " 😀!!!!! ");
                    new Menu(connection, usuario, gestorUsuario);
                } else {
                    JOptionPane.showMessageDialog(frame, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("PIN incorrecto.");
                }
            }
        });

        frame.add(loginPanel);
    }
}
