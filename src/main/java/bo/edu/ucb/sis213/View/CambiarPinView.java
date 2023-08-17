package bo.edu.ucb.sis213.View;

import javax.swing.*;

import bo.edu.ucb.sis213.Controller.App;
import bo.edu.ucb.sis213.Model.BackModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class CambiarPinView {
    private JFrame frame;
    private JPasswordField pinAntiguoField;
    private JPasswordField nuevoPinField;
    private JPasswordField confirmarPinField;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private BackModel model;


    public CambiarPinView(Connection connection) {
        
        // this.controller = new App(connection);
        this.model = new BackModel(connection);
        frame = new JFrame("Cambiar PIN");
        frame.setSize(300, 250); // Ajusta el tamaño para el nuevo campo
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 2)); // Añade una fila para el PIN antiguo

        JLabel pinAntiguoLabel = new JLabel("Ingrese el PIN antiguo:");
        JLabel nuevoPinLabel = new JLabel("Ingrese el nuevo PIN:");
        JLabel confirmarPinLabel = new JLabel("Confirmar nuevo PIN:");
        pinAntiguoField = new JPasswordField();
        nuevoPinField = new JPasswordField();
        confirmarPinField = new JPasswordField();

        aceptarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");

        mainPanel.add(pinAntiguoLabel);
        mainPanel.add(pinAntiguoField);
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
                String pinAntiguoStr = String.valueOf(pinAntiguoField.getPassword());
                String nuevoPinStr = String.valueOf(nuevoPinField.getPassword());
                String confirmarNuevoPinStr = String.valueOf(confirmarPinField.getPassword());

                if (pinAntiguoStr.isEmpty() || nuevoPinStr.isEmpty() || confirmarNuevoPinStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    frame.dispose();
                }else{
                    int pinAntiguo = Integer.parseInt(pinAntiguoStr);
                    int nuevoPin = Integer.parseInt(nuevoPinStr);
                    int confirmarNuevoPin = Integer.parseInt(confirmarNuevoPinStr);
                    try {
                        model.cambiarPIN(pinAntiguo,nuevoPin,confirmarNuevoPin);
                        System.out.println("Funcino Sofia TT.");
                    } catch (SQLException e1) {
                        System.out.println("Ayuda Sofia TT.");
                        e1.printStackTrace();
                    }
                    
                }
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

    public void close() {
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crea una instancia del CambiarPinView aquí
                // CambiarPinView cambiarPinView = new CambiarPinView(connection);
            }
        });
    }
}
