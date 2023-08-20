package bo.edu.ucb.sis213.View;

import javax.swing.*;

import bo.edu.ucb.sis213.Controller.App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CambiarPinView {
    private JFrame frame;
    private JPasswordField nuevoPinField;
    private JPasswordField confirmarPinField;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private App controller;


    public CambiarPinView(Connection connection) {
        this.controller = new App(connection);
        // this.model = new BackModel(connection);
        frame = new JFrame("Cambiar PIN");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 2)); 
        JLabel pinAntiguoLabel = new JLabel("Ingrese el PIN antiguo:");
        JLabel nuevoPinLabel = new JLabel("Ingrese el nuevo PIN:");
        JLabel confirmarPinLabel = new JLabel("Confirmar nuevo PIN:");
        nuevoPinField = new JPasswordField();
        confirmarPinField = new JPasswordField();

        aceptarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");

        mainPanel.add(pinAntiguoLabel);
        mainPanel.add(nuevoPinLabel);
        mainPanel.add(nuevoPinField);
        mainPanel.add(confirmarPinLabel);
        mainPanel.add(confirmarPinField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aceptarButton);
        buttonPanel.add(cancelarButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiar();
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

    public void cambiar(){
        String nuevoPinStr = String.valueOf(nuevoPinField.getPassword());
        String confirmarNuevoPinStr = String.valueOf(confirmarPinField.getPassword());
        if (nuevoPinStr.isEmpty() || confirmarNuevoPinStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            int nuevoPin = Integer.parseInt(nuevoPinStr);
            int confirmarNuevoPin = Integer.parseInt(confirmarNuevoPinStr);
            try {
                if(controller.cambioPIN(nuevoPin,confirmarNuevoPin)){
                    close();
                }else{
                    return;
                }
            } catch (Exception e) {
                System.out.println("Ayuda Sofia TT.");
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: "+e, "Error", JOptionPane.ERROR_MESSAGE);
                close();
            }
        }
    }

    public void close() {
        frame.dispose();
    }
}
