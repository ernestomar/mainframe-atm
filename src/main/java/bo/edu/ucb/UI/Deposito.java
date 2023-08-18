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
//import atm.Menu;


public class Deposito extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMonto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposito frame = new Deposito();
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
	public Deposito() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BANCO BOLIVIA - DEPOSITO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(62, 10, 318, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Introduzca el monto a depositar:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 57, 249, 26);
		contentPane.add(lblNewLabel_1);
		
		tfMonto = new JTextField();
		tfMonto.setFont(new Font("Tahoma", Font.PLAIN, 19));
		tfMonto.setBounds(101, 117, 241, 41);
		contentPane.add(tfMonto);
		tfMonto.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("$");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(82, 124, 21, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callMenu();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBounds(292, 227, 134, 26);
		contentPane.add(btnVolver);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cantidad = Double.parseDouble(tfMonto.getText());
				
				App.depositar(cantidad);
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmar.setBounds(163, 168, 134, 26);
		contentPane.add(btnConfirmar);
	}
	public void callMenu() {
		dispose();
		Menu m = new Menu();
		m.setLocationRelativeTo(null);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}

}
