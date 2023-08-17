package bo.edu.ucb.sis213.View;

import javax.swing.*;

import bo.edu.ucb.sis213.Controller.App;
import bo.edu.ucb.sis213.Model.BackModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositoView {
    private JFrame frame;
    private JTextField cantidadField;
    private JButton aceptarButton;
    private JButton cancelarButton;

    
    private BackModel model;
    private App controller;

    public DepositoView() {
        frame = new JFrame("Depósito");
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

        // Acción para el botón Aceptar
        // aceptarButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String cantidadStr = cantidadField.getText();
        //         double cantidad = Double.parseDouble(cantidadStr);
        //         controller.realizarDeposito(cantidad); // Llama al método en el controlador
        //         frame.dispose();
        //     }
        // });
        

        // Acción para el botón Cancelar
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        // frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }
}
