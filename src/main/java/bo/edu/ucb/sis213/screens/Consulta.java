package bo.edu.ucb.sis213.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consulta {

    private JFrame frame;
    private JPanel consultaPanel;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JButton exitButton;
    private JButton anotherOperationButton;

    public Consulta(int userid) {
        frame = new JFrame("Consulta de Saldo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        initConsulta(userid);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initConsulta(int userid) {
        consultaPanel = new JPanel(new GridBagLayout());
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        consultaPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        nameLabel = new JLabel("Consulta de Saldo", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(letras);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        consultaPanel.add(nameLabel, constraints);

        // Simular un saldo cualquiera
        balanceLabel = new JLabel("Saldo: $1000.00");
        balanceLabel.setForeground(letras);
        constraints.gridy = 1;
        consultaPanel.add(balanceLabel, constraints);

        anotherOperationButton = new JButton("Otra Operación");
        anotherOperationButton.setPreferredSize(new Dimension(100, 30)); // Tamaño personalizado
        anotherOperationButton.setFont(new Font("Arial", Font.BOLD, 8));
        anotherOperationButton.setBackground(Color.orange);
        constraints.gridx = 0; // Cambiar la posición en la columna 0
        constraints.gridy = 2; // Cambiar la posición en la fila 2
        constraints.gridwidth = 1;
        consultaPanel.add(anotherOperationButton, constraints);

        exitButton = new JButton("Salir");
        exitButton.setBackground(Color.GRAY);
        exitButton.setPreferredSize(new Dimension(100, 30)); // Tamaño personalizado
        exitButton.setFont(new Font("Arial", Font.BOLD, 8));
        constraints.gridx = 1; // Cambiar la posición en la columna 1
        constraints.gridy = 2; // Mantener la posición en la fila 2
        consultaPanel.add(exitButton, constraints);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
            }
        });

        anotherOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar lógica para manejar otra operación
            }
        });

        frame.add(consultaPanel);
    }

}
