package bo.edu.ucb.sis213;

import bo.edu.ucb.sis213.gui.ATMUsuario;

import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.SQLException;
import bo.edu.ucb.sis213.gui.Bienvenido;

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
