package bo.edu.ucb.sis213;

import javax.swing.SwingUtilities;

import bo.edu.ucb.sis213.bl.ATM;
import bo.edu.ucb.sis213.dao.DatabaseManager;
import bo.edu.ucb.sis213.view.ATMUsuario;
import bo.edu.ucb.sis213.view.Bienvenido;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = null;

        try {
            connection = dbManager.getConnection();
            System.out.println("Conexion a la Base de datos exitosa!");

        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }
        ATM atm = new ATM(connection);
        Bienvenido bienvenidoFrame = new Bienvenido(atm, connection);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                bienvenidoFrame.setLocationRelativeTo(null); // Centrar en la pantalla
                bienvenidoFrame.setVisible(true);
            }
        });
    }
}
