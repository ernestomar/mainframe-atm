package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.SQLException;

import bo.edu.ucb.sis213.dao.Conecction;
import bo.edu.ucb.sis213.view.LoginView;

public class App {
    public static void main(String[] args) {
        // Connection connection = null;
        // try {
        //     connection = Conecction.getConnection();
            
        // } catch (SQLException ex) {
        //     System.err.println("No se puede conectar a la Base de Datos");//joption1
        //     ex.printStackTrace();
        //     System.exit(1);
        // }

        LoginView loginView = new LoginView();
        // loginView.setVisible(true);
    }
}
