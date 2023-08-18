package bo.edu.ucb.sis213.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Retiros {

    private JFrame frame;
    private JPanel retiroPanel;
    private JLabel titleLabel;
    private JLabel saldoLabel;
    private JButton[] quickWithdrawButtons;
    private JButton otherButton;
    private JButton cancelButton;

    private double saldoActual = 1000.00; // Simulación de saldo actual

    public Retiros(int userid) {
        frame = new JFrame("Retirar Saldo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        initRetiros(userid);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initRetiros(int userid) {
        retiroPanel = new JPanel(new GridBagLayout());
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        retiroPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("Retiro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(letras);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        retiroPanel.add(titleLabel, constraints);

        saldoLabel = new JLabel("Saldo actual: $" + saldoActual);
        saldoLabel.setForeground(letras);
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        retiroPanel.add(saldoLabel, constraints);

        // Botones de retiro rápido
        quickWithdrawButtons = new JButton[4];
        String[] quickWithdrawLabels = {"100", "500", "1000", "2000"};
        for (int i = 0; i < 4; i++) {
            quickWithdrawButtons[i] = new JButton(quickWithdrawLabels[i]);
            quickWithdrawButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double quickWithdrawAmount = Double.parseDouble(e.getActionCommand());
                    showQuickWithdrawConfirmation(quickWithdrawAmount);
                    System.out.println("Valor seleccionado: " + quickWithdrawAmount);
                }
            });
            constraints.gridx = i;
            constraints.gridy = 2;
            constraints.gridwidth = 1;
            quickWithdrawButtons[i].setBackground(Color.orange);
            retiroPanel.add(quickWithdrawButtons[i], constraints);
        }

        otherButton = new JButton("Otros");
        otherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openOtherWithdrawScreen();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        otherButton.setBackground(Color.orange);
        retiroPanel.add(otherButton, constraints);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(Color.GRAY);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        retiroPanel.add(cancelButton, constraints);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
            }
        });

        frame.add(retiroPanel);
    }

    private void showQuickWithdrawConfirmation(double amount) {
        int choice = JOptionPane.showConfirmDialog(frame, "¿Está seguro que desea retirar $" + amount + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            performWithdraw(amount);
        }
    }

    private void openOtherWithdrawScreen() {
        frame.dispose(); // Cerrar la ventana actual
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        // Abrir la pantalla de retiro personalizado
        JFrame otherWithdrawFrame = new JFrame("Retiro Personalizado");
        otherWithdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        otherWithdrawFrame.setSize(400, 400);
        otherWithdrawFrame.setLocationRelativeTo(null);

        JPanel otherWithdrawPanel = new JPanel(new GridBagLayout());
        otherWithdrawPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Retiro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(letras);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        otherWithdrawPanel.add(titleLabel, constraints);

        JLabel enterAmountLabel = new JLabel("Ingrese monto a retirar:");
        enterAmountLabel.setForeground(letras);
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        otherWithdrawPanel.add(enterAmountLabel, constraints);

        JTextField amountField = new JTextField();
        constraints.gridx = 1;
        constraints.weightx = 1.0;
        otherWithdrawPanel.add(amountField, constraints);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(Color.GRAY);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        otherWithdrawPanel.add(cancelButton, constraints);

        JButton withdrawButton = new JButton("Retirar");
        constraints.gridx = 1;
        withdrawButton.setBackground(Color.orange);
        otherWithdrawPanel.add(withdrawButton, constraints);

        otherWithdrawFrame.add(otherWithdrawPanel);
        otherWithdrawFrame.setVisible(true);

        // Acción del botón "Cancelar"
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otherWithdrawFrame.dispose(); // Cerrar la ventana de retiro personalizado
            }
        });

        // Acción del botón "Retirar"
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double withdrawAmount = Double.parseDouble(amountField.getText());
                otherWithdrawFrame.dispose(); // Cerrar la ventana de retiro personalizado
                performWithdraw(withdrawAmount); // Ir a la pantalla de confirmación
            }
        });
    }


    private void performWithdraw(double amount) {
        saldoActual -= amount;
        saldoLabel.setText("Saldo actual: $" + saldoActual);
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        // Mostrar la pantalla de confirmación de retiro
        JFrame confirmationFrame = new JFrame("Confirmación de Retiro");
        confirmationFrame.setBackground(bckg);
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmationFrame.setSize(400, 400);
        confirmationFrame.setLocationRelativeTo(null);

        JPanel confirmacionPanel = new JPanel(new FlowLayout());
        JLabel confirmationLabel = new JLabel("Retiro realizado");
        confirmationLabel.setForeground(letras);
        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        confirmacionPanel.add(confirmationLabel);

        confirmationFrame.add(confirmacionPanel);
        confirmationFrame.setVisible(true);
    }

}
