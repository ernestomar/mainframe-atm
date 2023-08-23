package bo.edu.ucb.sis213.view;

import javax.swing.*;

// import bo.edu.ucb.sis213.Controller.Controller;

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
    // private Controller controller;


    public CambiarPinView(Connection connection) {
        // this.controller = new Controller(connection);
        // this.model = new BackModel(connection);
        frame = new JFrame("Cambiar PIN");
        frame.setSize(700, 440);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 139, 139));
        JLabel nuevoPinLabel = new JLabel("Ingrese el nuevo PIN:");
        nuevoPinLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        nuevoPinLabel.setBounds(121, 53, 301, 47);
        JLabel confirmarPinLabel = new JLabel("Confirmar nuevo PIN:");
        confirmarPinLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        confirmarPinLabel.setBounds(121, 182, 301, 47);
        nuevoPinField = new JPasswordField();
        nuevoPinField.setBounds(121, 110, 464, 30);
        confirmarPinField = new JPasswordField();
        confirmarPinField.setBounds(121, 239, 464, 30);
        mainPanel.setLayout(null);
        mainPanel.add(nuevoPinLabel);
        mainPanel.add(nuevoPinField);
        mainPanel.add(confirmarPinLabel);
        mainPanel.add(confirmarPinField);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        
                aceptarButton = new JButton("Aceptar");
                aceptarButton.setBounds(190, 322, 136, 35);
                mainPanel.add(aceptarButton);
                cancelarButton = new JButton("Cancelar");
                cancelarButton.setBounds(340, 322, 136, 35);
                mainPanel.add(cancelarButton);
                
                        cancelarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                close();
                            }
                        });
                
                        aceptarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cambiar();
                            }
                        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
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
                // if(controller.cambioPIN(nuevoPin,confirmarNuevoPin)){
                //     close();
                // }else{
                //     return;
                // }
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
