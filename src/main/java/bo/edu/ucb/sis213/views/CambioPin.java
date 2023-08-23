package bo.edu.ucb.sis213.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import bo.edu.ucb.sis213.bl.UsuarioBl;
import bo.edu.ucb.sis213.bl.GestorUsuario;

public class CambioPin {

    private JFrame frame;
    private JPanel cambioPinPanel;
    private JLabel titleLabel;
    private JLabel currentPasswordLabel;
    private JPasswordField currentPasswordField;
    private JLabel newPasswordLabel;
    private JPasswordField newPasswordField;
    private JLabel confirmNewPasswordLabel;
    private JPasswordField confirmNewPasswordField;
    private JButton cancelButton;
    private JButton changeButton;
    private Connection connection;
    private UsuarioBl usuario;
    private GestorUsuario gestorUsuario;

    public CambioPin(Connection connection, UsuarioBl usuario, GestorUsuario gestorUsuario) {
        this.connection = connection;
        this.usuario = usuario;
        this.gestorUsuario = gestorUsuario;

        frame = new JFrame("Cambio de PIN");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        initCambioPin();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initCambioPin() {
        cambioPinPanel = new JPanel(new GridBagLayout());
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);
        cambioPinPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("Cambio de PIN", SwingConstants.CENTER);
        titleLabel.setForeground(letras);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        cambioPinPanel.add(titleLabel, constraints);

        currentPasswordLabel = new JLabel("Ingrese contraseña actual:");
        currentPasswordLabel.setForeground(letras);
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        cambioPinPanel.add(currentPasswordLabel, constraints);

        currentPasswordField = new JPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        cambioPinPanel.add(currentPasswordField, constraints);

        newPasswordLabel = new JLabel("Nueva contraseña:");
        newPasswordLabel.setForeground(letras);
        constraints.gridy = 2;
        constraints.gridx = 0;
        cambioPinPanel.add(newPasswordLabel, constraints);

        newPasswordField = new JPasswordField();
        constraints.gridy = 2;
        constraints.gridx = 1;
        cambioPinPanel.add(newPasswordField, constraints);

        confirmNewPasswordLabel = new JLabel("Confirme nueva contraseña:");
        confirmNewPasswordLabel.setForeground(letras);
        constraints.gridy = 3;
        constraints.gridx = 0;
        cambioPinPanel.add(confirmNewPasswordLabel, constraints);

        confirmNewPasswordField = new JPasswordField();
        constraints.gridy = 3;
        constraints.gridx = 1;
        cambioPinPanel.add(confirmNewPasswordField, constraints);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setPreferredSize(new Dimension(100, 30)); // Ajusta el tamaño del botón
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER; // Centra el botón
        cambioPinPanel.add(cancelButton, constraints);

        changeButton = new JButton("Cambiar");
        changeButton.setBackground(Color.orange);
        changeButton.setPreferredSize(new Dimension(100, 30)); // Ajusta el tamaño del botón
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER; // Centra el botón
        cambioPinPanel.add(changeButton, constraints);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPassword = new String(currentPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmNewPassword = new String(confirmNewPasswordField.getPassword());

                if (newPassword.equals(confirmNewPassword)) {
                    if(currentPassword.equals(usuario.getPinActual())){
                        int nuevoPin = Integer.parseInt(newPassword);
                        boolean flag = gestorUsuario.cambiarPIN(usuario.getUsuarioId(),nuevoPin);
                        if(flag){
                            showSuccessScreen();
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "No se pudo cambiar la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // Aquí puedes agregar la lógica para cambiar la contraseña

                } else {
                    JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(cambioPinPanel);
    }

    private void showSuccessScreen() {
        frame.dispose(); // Cerrar la ventana actual
        Color bckg = new Color(0x0C0E9B);
        Color letras = new Color(0xF1E30A);

        // Abrir la pantalla de éxito
        JFrame successFrame = new JFrame("Contraseña Cambiada");
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setLocationRelativeTo(null);
        successFrame.setSize(400, 400);

        JPanel successPanel = new JPanel(new GridBagLayout());
        successPanel.setBackground(bckg);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel successLabel = new JLabel("Contraseña cambiada con éxito", SwingConstants.CENTER);
        successLabel.setForeground(letras);
        successLabel.setFont(new Font("Arial", Font.BOLD, 16));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        successPanel.add(successLabel, constraints);

        JButton anotherOperationButton = new JButton("Otra Operación");
        anotherOperationButton.setBackground(Color.orange);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        successPanel.add(anotherOperationButton, constraints);

        JButton exitButton = new JButton("Salir");
        exitButton.setBackground(Color.GRAY);
        constraints.gridx = 1;
        successPanel.add(exitButton, constraints);

        anotherOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                successFrame.dispose(); // Cerrar la ventana actual
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir de la aplicación
            }
        });

        successFrame.add(successPanel);
        successFrame.setVisible(true);
    }
}
