package bo.edu.ucb.sis213.view;

import javax.swing.*;

import bo.edu.ucb.sis213.bl.AtmBL;

// import bo.edu.ucb.sis213.Controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarPinView {
    private JFrame frame;
    private JPasswordField nuevoPinField;
    private JPasswordField confirmarPinField;
    private JButton aceptarButton;
    private JButton cancelarButton;


    public CambiarPinView(AtmBL bl) {
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
                                String nuevoPinStr = String.valueOf(nuevoPinField.getPassword());
                                String confirmarNuevoPinStr = String.valueOf(confirmarPinField.getPassword());
                                try {
                                    if(bl.validarCambioPIN(nuevoPinStr,confirmarNuevoPinStr)){
                                        
                                        int oppin = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de cambiar de PIN", "Confirmar", JOptionPane.YES_NO_OPTION);
                                        if (oppin == JOptionPane.YES_OPTION) {
                                            try {
                                                if(bl.cambiarPIN(nuevoPinStr)){
                                                    System.out.println(bl.getSaldo()+"view");
                                                    JOptionPane.showMessageDialog(null, "Cambio de pin realizado con \u00E9xito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                                    close();
                                                }else{
                                                    JOptionPane.showMessageDialog(frame, bl.getTextoE(), bl.getTituloE(), JOptionPane.WARNING_MESSAGE);
                                                    return;
                                                }
                                            } catch (Exception ex) {
                                                System.out.println("Error Ss TT");
                                                ex.printStackTrace();
                                                JOptionPane.showMessageDialog(null, "Error al realizar el cambio de pin: "+ex, "Error", JOptionPane.ERROR_MESSAGE);
                                                close();
                                            }
                                            System.out.println("\nDepósito==" + bl.getSaldo());
                                            
                                        }else if (oppin == JOptionPane.NO_OPTION) {
                                            System.out.println("\nNo continuar con el cambio");
                                            return;
                                        }
                                    }else{
                                        return;
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Ayuda Sofia TT.");
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", JOptionPane.ERROR_MESSAGE);
                                    close();
                                }
                            }
                        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void close() {
        frame.dispose();
    }
}
