package bo.edu.ucb.sis213.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.bl.ATM;
import bo.edu.ucb.sis213.dao.DatabaseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Bienvenido extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuariox;
	private JPasswordField passwordField;
	private ATM atm;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DatabaseManager dbManager = new DatabaseManager();
				Connection connection = null;

				try {
					connection = dbManager.getConnection();
					System.out.println("conectado");
				} catch (SQLException ex) {
					System.err.println("No se puede conectar a la Base de Datos");
					ex.printStackTrace();
					System.exit(1);
				}

				ATM atm = new ATM(connection);
				Bienvenido frame = new Bienvenido(atm, connection);
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bienvenido(ATM atm, Connection connection) {
		this.atm = atm;
		this.connection = connection;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 455);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(144, 238, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		panel.setBounds(0, 0, 564, 58);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bienvenido al ATM del Banco");
		lblNewLabel.setBounds(40, 5, 484, 36);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 128, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setBounds(0, 293, 564, 178);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnInicioSesion = new JButton("Inicio de sesion");

		btnInicioSesion.setForeground(new Color(0, 0, 0));
		btnInicioSesion.setBackground(new Color(255, 255, 255));
		btnInicioSesion.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnInicioSesion.setToolTipText("Click para verificar su inicio de sesion");
		btnInicioSesion.setBounds(120, 12, 179, 52);
		panel_1.add(btnInicioSesion);

		JButton btnSalir = new JButton("Salir");

		btnSalir.setToolTipText("Click para Salir");
		btnSalir.setForeground(Color.BLACK);
		btnSalir.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(325, 12, 118, 52);
		panel_1.add(btnSalir);

		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1.setBounds(87, 74, 303, 32);
		contentPane.add(lblNewLabel_1);

		txtUsuariox = new JTextField();
		txtUsuariox.setToolTipText("Debe ingresar su nombre de usuario");
		txtUsuariox.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtUsuariox.setBounds(87, 106, 351, 25);
		contentPane.add(txtUsuariox);
		txtUsuariox.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("PIN:");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(87, 131, 303, 32);
		contentPane.add(lblNewLabel_1_1);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Ingrese su PIN");
		passwordField.setBounds(87, 161, 351, 25);
		contentPane.add(passwordField);

		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String alias = txtUsuariox.getText();
				char[] pinChars = passwordField.getPassword();
				String pinStr = new String(pinChars);

				try {
					int pin = Integer.parseInt(pinStr);

					if (atm.validarPIN(alias, pin)) {
						System.out.println("Inicio de sesión correcto.");
						ATMUsuario atmUsuarioFrame = new ATMUsuario(atm);
						atmUsuarioFrame.setVisible(true);
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Usuario o PIN incorrectos/no existentes.",
								"Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(contentPane, "Usuario o PIN incorrectos/no existentes.",
							"Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Cierra la aplicación
			}
		});
		setLocationRelativeTo(null);
	}

}
