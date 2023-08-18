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

public class Atm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPassword;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atm frame = new Atm();
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
	public Atm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BANCO BOLIVIA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(128, 10, 187, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Nombre de usuario:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(86, 82, 148, 20);
		contentPane.add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsername.setBounds(244, 82, 123, 22);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(137, 143, 97, 20);
		contentPane.add(lblPassword);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPassword.setColumns(10);
		tfPassword.setBounds(244, 146, 123, 22);
		contentPane.add(tfPassword);
		
		JButton btnLogin = new JButton("Iniciar sesion");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameIngresado = tfUsername.getText();
				String passwordIngresado = tfPassword.getText();
				
				if(App.Login(usernameIngresado, passwordIngresado)) {
					callMenu();
				}else {
					tfUsername.setText("");
					tfPassword.setText("");
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(271, 205, 129, 30);
		contentPane.add(btnLogin);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalir.setBounds(74, 205, 129, 30);
		contentPane.add(btnSalir);
	}
	
	public void callMenu() {
		dispose();
		Menu m = new Menu();
		m.setLocationRelativeTo(null);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}
}
