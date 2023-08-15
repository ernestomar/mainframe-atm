package bo.edu.ucb.sis213.screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private JFrame frame;
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;

    public Login() {
        frame = new JFrame("ATM Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        initLoginScreen();

        frame.setVisible(true);
    }

    private void initLoginScreen() {
        loginPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Banco Luna", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        loginPanel.add(titleLabel, constraints);

        JLabel userLabel = new JLabel("Usuario:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 70, 10, 0);
        loginPanel.add(userLabel, constraints);

        userField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 2.0;
        constraints.insets = new Insets(10, -70, 10, 40); // Adjusted insets
        loginPanel.add(userField, constraints);

        JLabel pinLabel = new JLabel("Contraseña:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 50, 10, 0);
        loginPanel.add(pinLabel, constraints);

        pinField = new JPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, -70, 10, 40);
        loginPanel.add(pinField, constraints);

        loginButton = new JButton("Ingresar");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 50, 10, 50);
        loginPanel.add(loginButton, constraints);

        frame.add(loginPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        });
    }
}
