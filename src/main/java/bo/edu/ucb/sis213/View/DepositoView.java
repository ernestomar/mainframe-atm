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
        frame.setSize(550, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel lblIngreseLaCantidad = new JLabel("Ingrese la cantidad a depositar: ");
        lblIngreseLaCantidad.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblIngreseLaCantidad.setBounds(65, 65, 368, 35);
        cantidadField = new JTextField();
        cantidadField.setBounds(65, 137, 396, 35);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(0, 139, 139));
        inputPanel.setLayout(null);
        inputPanel.add(lblIngreseLaCantidad);
        inputPanel.add(cantidadField);
        frame.getContentPane().add(inputPanel, BorderLayout.CENTER);

        aceptarButton = new JButton("Aceptar");
        aceptarButton.setBounds(116, 225, 136, 35);
        inputPanel.add(aceptarButton);
        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(266, 225, 136, 35);
        inputPanel.add(cancelarButton);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
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
