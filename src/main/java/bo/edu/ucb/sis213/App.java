package bo.edu.ucb.sis213;

import bo.edu.ucb.sis213.bl.UsuarioBl;
import bo.edu.ucb.sis213.dao.ConexionDao;
import bo.edu.ucb.sis213.views.Login;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConexionDao.getConnection();
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }


        // Obtener el usuario y validar el PIN
        UsuarioBl usuario = new UsuarioBl(connection, 0, 0, 0, null); // Valores temporales
        new Login(connection);
    }
}
