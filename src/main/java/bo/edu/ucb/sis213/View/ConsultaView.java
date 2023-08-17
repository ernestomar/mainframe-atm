package bo.edu.ucb.sis213.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaView {
    private JFrame frame;
    private JLabel saldoLabel;
    private JButton aceptarButton;

    public ConsultaView(double saldo) {
        frame = new JFrame("Consulta de Saldo");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        saldoLabel = new JLabel("Su saldo actual es: $" + saldo);
        saldoLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(saldoLabel, BorderLayout.CENTER);

        aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aceptarButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }
}
