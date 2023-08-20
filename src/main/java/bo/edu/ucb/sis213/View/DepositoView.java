package bo.edu.ucb.sis213.View;

import javax.swing.*;

import bo.edu.ucb.sis213.Controller.App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class DepositoView {
    private JFrame frame;
    private JTextField cantidadField;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private App controller;
    // private BackModel model;

    public DepositoView(Connection connection) {
        frame = new JFrame("Depósito");
        // this.model = new BackModel(connection);
        this.controller = new App(connection);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Ingrese la cantidad a depositar: $");
        cantidadField = new JTextField();
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(label);
        inputPanel.add(cantidadField);
        frame.add(inputPanel, BorderLayout.CENTER);

        aceptarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aceptarButton);
        buttonPanel.add(cancelarButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        frame.setVisible(true);
    }
    public void depositar(){
        String cantidadStr = cantidadField.getText();
        try {
            System.out.println("cant: "+cantidadStr);
            if(controller.realizarDeposito(cantidadStr)){//si guardo?
                close();
            }else{
                return;
            }   
        } catch (Exception ex) {
            System.out.println("Error S TT");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar el dep\u00F3sito: "+ex, "Error", JOptionPane.ERROR_MESSAGE);
            close();
        }
    }

    public void close() {
        frame.dispose();
    }
}
