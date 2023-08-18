package bo.edu.ucb.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import bo.edu.ucb.sis213.App;

public class CambioPassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfActual;
	private JTextField tfNueva;
	private JTextField tfConfirmacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambioPassword frame = new CambioPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CambioPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BANCO BOLIVIA - CAMBIO CONTRASEÑA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(21, 10, 461, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Introduzca la contraseña actual:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 58, 230, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Introduzca la contraseña nueva:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 125, 230, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Vuelva a introducir la contrasña nueva:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(10, 198, 279, 20);
		contentPane.add(lblNewLabel_1_2);
		
		tfActual = new JTextField();
		tfActual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfActual.setBounds(155, 88, 159, 27);
		contentPane.add(tfActual);
		tfActual.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callMenu();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBounds(348, 326, 134, 26);
		contentPane.add(btnVolver);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passwordIngresado = tfActual.getText();
				String nuevoPassword = tfNueva.getText();
				String confirmacionPassword = tfConfirmacion.getText();
				
				App.nuevoPassw(passwordIngresado, nuevoPassword, confirmacionPassword);
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmar.setBounds(348, 259, 134, 26);
		contentPane.add(btnConfirmar);
		
		tfNueva = new JTextField();
		tfNueva.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfNueva.setColumns(10);
		tfNueva.setBounds(155, 155, 159, 27);
		contentPane.add(tfNueva);
		
		tfConfirmacion = new JTextField();
		tfConfirmacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfConfirmacion.setColumns(10);
		tfConfirmacion.setBounds(155, 228, 159, 27);
		contentPane.add(tfConfirmacion);
	}
	public void callMenu() {
		dispose();
		Menu m = new Menu();
		m.setLocationRelativeTo(null);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}

}
