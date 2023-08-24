package bo.edu.ucb.sis213.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import bo.edu.ucb.sis213.bl.UsuarioBl;
import bo.edu.ucb.sis213.bl.GestorUsuarioBl;

public class Deposito {

    private JFrame frame;
    private JPanel depositoPanel;
    private JLabel titleLabel;
    private JLabel enterAmountLabel;
    private JLabel resultLabel;
    private JTextField amountField;
    private JButton cancelButton;
    private JButton depositButton;
    private Connection connection;
    private GestorUsuarioBl gestorUsuario;

    public Deposito(Connection connection, GestorUsuarioBl gestorUsuario) {
        this.connection = connection;
        this.gestorUsuario = gestorUsuario;

        frame = new JFrame("Depósito");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        initDeposito();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initDeposito() {
        depositoPanel = new JPanel(new GridBagLayout());
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        depositoPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("Depósito", SwingConstants.CENTER);
        titleLabel.setForeground(letras);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        depositoPanel.add(titleLabel, constraints);

        enterAmountLabel = new JLabel("Ingrese monto:");
        enterAmountLabel.setForeground(letras);
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        depositoPanel.add(enterAmountLabel, constraints);

        amountField = new JTextField();
        constraints.gridx = 1;
        constraints.weightx = 1.0;
        depositoPanel.add(amountField, constraints);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(Color.GRAY);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        depositoPanel.add(cancelButton, constraints);

        depositButton = new JButton("Depositar");
        depositButton.setBackground(Color.orange);
        constraints.gridx = 1;
        depositoPanel.add(depositButton, constraints);

        resultLabel = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        depositoPanel.add(resultLabel, constraints);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double depositAmount = Double.parseDouble(amountField.getText());
                Color bckg = new Color(0x0C0E9B);
                Color letras = new Color(0xF1E30A);

                // Mostrar la pantalla de confirmación
                JFrame confirmationFrame = new JFrame("Banco Luna");
                confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                confirmationFrame.setSize(400, 400);
                confirmationFrame.setLocationRelativeTo(null);
                if(depositAmount<=0){
                    JOptionPane.showMessageDialog(frame, "Cantidad no válida", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    boolean flag = gestorUsuario.realizarDeposito_UsuarioBl(depositAmount);
                    if(flag){
                        JPanel confirmacionPanel = new JPanel(new FlowLayout());
                        confirmacionPanel.setBackground(bckg);
                        JLabel confirmationLabel = new JLabel("Depósito realizado: $" + depositAmount);
                        confirmationLabel.setForeground(letras);
                        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        confirmacionPanel.add(confirmationLabel);
                        confirmationFrame.add(confirmacionPanel);
                    }
                    else{
                        JPanel confirmacionPanel = new JPanel(new FlowLayout());
                        confirmacionPanel.setBackground(bckg);
                        JLabel confirmationLabel = new JLabel("No se pudo realizar el deposito, intente mas tarde");
                        confirmationLabel.setForeground(letras);
                        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        confirmacionPanel.add(confirmationLabel);
                        confirmationFrame.add(confirmacionPanel);
                    }
                    confirmationFrame.setVisible(true);
                }
            }
        });


        frame.add(depositoPanel);
    }
}
