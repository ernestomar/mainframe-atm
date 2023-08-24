package bo.edu.ucb.sis213.view;

import javax.swing.*;

import bo.edu.ucb.sis213.bl.AtmBL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaView {
    private JFrame frame;
    private JLabel saldoLabel;
    private JButton aceptarButton;
    private double saldo;

    public ConsultaView(AtmBL bl) {
        saldo = bl.getSaldo();

        frame = new JFrame("Consulta de Saldo");
        frame.getContentPane().setBackground(new Color(0, 139, 139));
        frame.setSize(550, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        saldoLabel = new JLabel("Su saldo actual es: " +saldo+" Bs.");
        saldoLabel.setForeground(new Color(255, 255, 255));
        saldoLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        saldoLabel.setBounds(0, 10, 536, 208);
        saldoLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(saldoLabel);
        
                aceptarButton = new JButton("Aceptar");
                aceptarButton.setBounds(166, 228, 216, 39);
                frame.getContentPane().add(aceptarButton);
                aceptarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        close();
                    }
                });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void close() {
        frame.dispose();
    }
}
