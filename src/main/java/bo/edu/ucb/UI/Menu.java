package bo.edu.ucb.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import bo.edu.ucb.sis213.MovimientosBancarios;


public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BANCO BOLIVIA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(138, 10, 187, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Por favor, introduzca una opcion:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 53, 235, 26);
		contentPane.add(lblNewLabel_1);
		
		JButton btnConsultaSaldo = new JButton("Consultar saldo");
		btnConsultaSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovimientosBancarios.consultarSaldo();
			}
		});
		btnConsultaSaldo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultaSaldo.setBounds(31, 99, 172, 26);
		contentPane.add(btnConsultaSaldo);
		
		JButton btnDeposito = new JButton("Deposito");
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callDeposito();
			}
		});
		btnDeposito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeposito.setBounds(31, 173, 172, 26);
		contentPane.add(btnDeposito);
		
		JButton btnRetiro = new JButton("Reitro");
		btnRetiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callRetiro();
			}
		});
		btnRetiro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRetiro.setBounds(239, 173, 187, 26);
		contentPane.add(btnRetiro);
		
		JButton btnCambioPassw = new JButton("Cambiar contraseña");
		btnCambioPassw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callCambioPassword();
			}
		});
		btnCambioPassw.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCambioPassw.setBounds(239, 99, 187, 26);
		contentPane.add(btnCambioPassw);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalir.setBounds(287, 227, 139, 26);
		contentPane.add(btnSalir);
	}
	
	
	
	public  void callDeposito() {
		dispose();
		Deposito d = new Deposito();
		d.setLocationRelativeTo(null);
		d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d.setVisible(true);
	}
	public void callRetiro() {
		dispose();
		Retiro r = new Retiro();
		r.setLocationRelativeTo(null);
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r.setVisible(true);
	}
	public void callCambioPassword() {
		dispose();
		CambioPassword c = new CambioPassword();
		c.setLocationRelativeTo(null);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}

}