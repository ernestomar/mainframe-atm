package bo.edu.ucb.sis213.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import bo.edu.ucb.sis213.Controller.App;

public class MenuView {
    private JPanel mainMenuPanel;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton changePinButton;
    private JButton exitButton;
    private JFrame frame;
    private App controller;

    public MenuView(Connection connection, String username) {
        this.controller = new App(connection);
        frame = new JFrame("ATM App - Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        initMainMenu(username, connection);

        frame.setVisible(true);
    }
    private void initMainMenu(String username, Connection connection) {
        mainMenuPanel = new JPanel(new GridLayout(5, 1));

        checkBalanceButton = new JButton("Consultar saldo");
        depositButton = new JButton("Realizar un depósito");
        withdrawButton = new JButton("Realizar un retiro");
        changePinButton = new JButton("Cambiar PIN");
        exitButton = new JButton("Cerrar Sesi\u00F3n");

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.consultarSaldo();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.mostrarDepositoView();
            }
        });
        
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.mostrarRetiroView();
            }
        });

        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.validarPIN(2, frame);
                
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oplogout = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de cerrar su sesión ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (oplogout == JOptionPane.YES_OPTION) {
                    close();
                    controller.LoginView();
                }else{
                    return;
                }
                
            }
        });

        mainMenuPanel.add(new JLabel("Bienvenido, " + username));
        mainMenuPanel.add(checkBalanceButton);
        mainMenuPanel.add(depositButton);
        mainMenuPanel.add(withdrawButton);
        mainMenuPanel.add(changePinButton);
        mainMenuPanel.add(exitButton);

        frame.add(mainMenuPanel);
    }
    public void close() {
        frame.dispose();
    }
}
