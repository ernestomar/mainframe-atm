package bo.edu.ucb.sis213.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import bo.edu.ucb.sis213.Model.BackModel;
import bo.edu.ucb.sis213.View.LoginView;
import bo.edu.ucb.sis213.Controller.App;
// import bo.edu.ucb.sis213.View.LoginView;

public class MenuView {
    private JPanel mainMenuPanel;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton changePinButton;
    private JButton exitButton;
    private JFrame frame;
    private App controller;
    
    private BackModel model;

    public MenuView(Connection connection, String username) {
        this.controller = new App(connection);
        this.model = new BackModel(connection);
        frame = new JFrame("ATM App - Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        initMainMenu(username);

        frame.setVisible(true);
    }
    // consultarSaldo
    private void initMainMenu(String username) {
        mainMenuPanel = new JPanel(new GridLayout(5, 1));

        checkBalanceButton = new JButton("Consultar saldo");
        depositButton = new JButton("Realizar un depósito");
        withdrawButton = new JButton("Realizar un retiro");
        changePinButton = new JButton("Cambiar PIN");
        exitButton = new JButton("Salir");

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.consultarSaldo();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // DepositoView depositoView = new DepositoView();
                controller.mostrarDepositoView();
            }
        });
        
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // DepositoView depositoView = new DepositoView();
                controller.mostrarRetiroView();
            }
        });

        // Para simplificar, solo agregaremos una acción al botón de salir
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
