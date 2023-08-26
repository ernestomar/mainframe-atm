package bo.edu.ucb.sis213.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.bl.ATM;

public class ATMUsuario extends JFrame {

	private JPanel contentPane;
	private static ATM atm;
	private Connection connection;
	private JLabel lblSaldoActual;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMUsuario frame = new ATMUsuario(atm);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ATMUsuario(ATM atm) {

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

		JButton btnSalir = new JButton("Salir");

		btnSalir.setToolTipText("Click para Salir");
		btnSalir.setForeground(Color.BLACK);
		btnSalir.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(325, 12, 118, 52);
		panel_1.add(btnSalir);

		JLabel lblNewLabel_1 = new JLabel("¿Que desea hacer?");
		lblNewLabel_1.setFont(new Font("Manjari", Font.BOLD | Font.ITALIC, 34));
		lblNewLabel_1.setBounds(53, 62, 322, 40);
		contentPane.add(lblNewLabel_1);

		JButton btnConsultarSaldo = new JButton("Consultar saldo");

		btnConsultarSaldo.setToolTipText("Click para verificar su inicio de sesion");
		btnConsultarSaldo.setForeground(Color.BLACK);
		btnConsultarSaldo.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnConsultarSaldo.setBackground(Color.WHITE);
		btnConsultarSaldo.setBounds(53, 99, 179, 52);
		contentPane.add(btnConsultarSaldo);

		JButton btnCambiarDePin = new JButton("Cambiar de PIN");

		btnCambiarDePin.setToolTipText("Click para verificar su inicio de sesion");
		btnCambiarDePin.setForeground(Color.BLACK);
		btnCambiarDePin.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnCambiarDePin.setBackground(Color.WHITE);
		btnCambiarDePin.setBounds(53, 177, 179, 52);
		contentPane.add(btnCambiarDePin);

		JButton btnRealizarRetiro = new JButton("Realizar un retiro");
		btnRealizarRetiro.setToolTipText("Click para verificar su inicio de sesion");
		btnRealizarRetiro.setForeground(Color.BLACK);
		btnRealizarRetiro.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnRealizarRetiro.setBackground(Color.WHITE);
		btnRealizarRetiro.setBounds(300, 99, 205, 52);
		contentPane.add(btnRealizarRetiro);

		JButton btnRealizarDeposito = new JButton("Realizar un deposito");

		btnRealizarDeposito.setToolTipText("Click para verificar su inicio de sesion");
		btnRealizarDeposito.setForeground(Color.BLACK);
		btnRealizarDeposito.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		btnRealizarDeposito.setBackground(Color.WHITE);
		btnRealizarDeposito.setBounds(300, 177, 205, 52);
		contentPane.add(btnRealizarDeposito);

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Cierra la aplicación
			}
		});
		btnConsultarSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double saldoActual = atm.getSaldo(); // Obtén el saldo actual del ATM
				JOptionPane.showMessageDialog(contentPane, "Su saldo actual es: $" + saldoActual, "Consulta de Saldo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnRealizarRetiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double saldoActual = atm.getSaldo();
					String montoStr = JOptionPane.showInputDialog(contentPane,
							"Su saldo actual es: $" + saldoActual + "\nIngrese monto a retirar:", "Realizar Retiro",
							JOptionPane.PLAIN_MESSAGE);
					if (montoStr != null) {
						double monto = Double.parseDouble(montoStr);
						if (monto > 0) {
							if (monto <= atm.getSaldo()) {
								atm.realizarRetiro(monto);
								JOptionPane.showMessageDialog(contentPane,
										"Retiro realizado con éxito. Su nuevo saldo es: $" + atm.getSaldo(),
										"Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(contentPane, "Saldo insuficiente para la operación.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(contentPane, "Ingrese una cantidad positiva por favor.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NumberFormatException | SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnRealizarDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double saldoActual = atm.getSaldo();
					String montoStr = JOptionPane.showInputDialog(contentPane,
							"Su saldo actual es: $" + saldoActual + "\nIngrese monto a depositar:", "Realizar Depósito",
							JOptionPane.PLAIN_MESSAGE);
					if (montoStr != null) {
						double monto = Double.parseDouble(montoStr);
						if (monto > 0) {
							atm.realizarDeposito(monto);
							JOptionPane.showMessageDialog(contentPane,
									"Depósito realizado con éxito. Su nuevo saldo es: $" + atm.saldo,
									"Operación Exitosa",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(contentPane, "Ingrese una cantidad positiva por favor.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NumberFormatException | SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCambiarDePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pinIngresado = Integer
							.parseInt(JOptionPane.showInputDialog(contentPane, "Ingrese su PIN actual:",
									"Cambiar PIN", JOptionPane.PLAIN_MESSAGE));

					// Obtener el PIN actual desde la base de datos
					int pinAlmacenado = atm.obtenerPinDesdeDB();
					System.out.println("PIN actual ingresado: " + pinIngresado);
					System.out.println("PIN actual almacenado: " + pinAlmacenado);

					if (pinIngresado != pinAlmacenado) {
						JOptionPane.showMessageDialog(contentPane, "El PIN actual ingresado es incorrecto.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return; // Salir del ActionListener si el PIN actual es incorrecto
					}

					int nuevoPin = Integer.parseInt(JOptionPane.showInputDialog(contentPane, "Ingrese su nuevo PIN:",
							"Cambiar PIN", JOptionPane.PLAIN_MESSAGE));
					int confirmarPin = Integer.parseInt(JOptionPane.showInputDialog(contentPane,
							"Confirme su nuevo PIN:", "Cambiar PIN", JOptionPane.PLAIN_MESSAGE));

					if (nuevoPin == confirmarPin) { // Compara si el PIN nuevo y el PIN confirmado son iguales
						atm.cambiarPIN(pinIngresado, nuevoPin, confirmarPin);
						JOptionPane.showMessageDialog(contentPane, "PIN actualizado con éxito.", "Operación Exitosa",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Los PINs no coinciden.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException | SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setLocationRelativeTo(null);
	}

}
