package bo.edu.ucb.sis213.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JFrame frame;
    private JPanel menuPanel;
    private JLabel welcomeLabel;

    public Menu(String usuario) {
        frame = new JFrame("ATM Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        initMenu(usuario);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initMenu(String usuario) {
        menuPanel = new JPanel(new GridBagLayout());
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        menuPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        welcomeLabel = new JLabel("Bienvenido " + usuario, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(letras);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        menuPanel.add(welcomeLabel, constraints);

        JLabel titleLabel = new JLabel("Banco Luna", SwingConstants.CENTER);
        titleLabel.setForeground(letras);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        constraints.gridy = 1;
        menuPanel.add(titleLabel, constraints);

        // Agregar los botones con las opciones del menú
        addButton("Consultar Saldo", 0, 2, constraints, Color.ORANGE);
        addButton("Realizar Depósito", 1, 2, constraints, Color.ORANGE);
        addButton("Realizar Retiro", 0, 3, constraints, Color.ORANGE);
        addButton("Cambiar PIN", 1, 3, constraints, Color.ORANGE);
        addButton("Salir", 0, 4, constraints, Color.GRAY);

        frame.add(menuPanel);
    }

    private void addButton(String label, int gridX, int gridY, GridBagConstraints constraints, Color color) {
        JButton button = new JButton(label);
        button.setBackground(color);
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        menuPanel.add(button, constraints);

        // Agregar ActionListener a cada botón utilizando una expresión lambda
        button.addActionListener(e -> manejoBotones(label));
    }

    private void manejoBotones(String buttonText) {
        int userid=1;
        switch (buttonText) {
            case "Consultar Saldo":
                new Consulta(userid);
                // Agregar lógica para consultar saldo
                break;
            case "Realizar Depósito":
                new Deposito(userid);
                // Agregar lógica para realizar depósito
                break;
            case "Realizar Retiro":
                new Retiros(userid);
                // Agregar lógica para realizar retiro
                break;
            case "Cambiar PIN":
                new CambioPin(userid);
                // Agregar lógica para cambiar PIN
                break;
            case "Salir":
                System.exit(0); // Cerrar la aplicación
                break;
            default:
                // Manejar otros botones si es necesario
                break;
        }
    }
}
