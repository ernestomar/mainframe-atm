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
        frame.setSize(703, 450);
        initLoginScreen();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void initLoginScreen() {
        loginPanel = new JPanel();
        loginPanel.setBackground(new Color(70, 130, 180));
        userField = new JTextField();
        userField.setBounds(266, 139, 251, 32);
        pinField = new JPasswordField();
        pinField.setBounds(266, 224, 251, 32);
        loginButton = new JButton("Ingresar");
        loginButton.setBackground(SystemColor.inactiveCaption);
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        loginButton.setBounds(173, 305, 344, 44);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCredencialesV(userField.getText(), new String(pinField.getPassword()));
            }
        });
        loginPanel.setLayout(null);

        JLabel lblUsuario = new JLabel("USUARIO:");
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUsuario.setBounds(173, 135, 99, 38);
        loginPanel.add(lblUsuario);
        loginPanel.add(userField);
        JLabel label_1 = new JLabel("PIN:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        label_1.setBounds(173, 220, 99, 44);
        loginPanel.add(label_1);
        loginPanel.add(pinField);
        loginPanel.add(loginButton);

        frame.getContentPane().add(loginPanel);
        
        JLabel lblbienvenidoAlAtm = new JLabel("\u00A1BIENVENIDO AL CAJERO FMA!");
        lblbienvenidoAlAtm.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
        lblbienvenidoAlAtm.setBounds(146, 49, 411, 38);
        loginPanel.add(lblbienvenidoAlAtm);
        
        JButton btnNewButton = new JButton("Salir");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnNewButton.setBounds(594, 382, 85, 21);
        loginPanel.add(btnNewButton);
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
