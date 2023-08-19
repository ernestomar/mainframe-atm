package bo.edu.ucb.sis213.screens;

import bo.edu.ucb.sis213.GestorUsuario;
import bo.edu.ucb.sis213.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Consulta {

    private JFrame frame;
    private JPanel consultaPanel;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JButton exitButton;
    private JButton anotherOperationButton;
    private Connection connection;
    private Usuario usuario;
    private GestorUsuario gestorUsuario;

    public Consulta(Connection connection, Usuario usuario, GestorUsuario gestorUsuario) {
        this.connection = connection;
        this.usuario = usuario;
        this.gestorUsuario = gestorUsuario;

        frame = new JFrame("Consulta de Saldo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        initConsulta(connection, usuario, gestorUsuario);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initConsulta(Connection connection, Usuario usuario, GestorUsuario gestorUsuario) {
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
        balanceLabel = new JLabel("Saldo: Bs"+usuario.getSaldo());
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
                System.exit(0);
            }
        });

        anotherOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(consultaPanel);
    }

}
