package bo.edu.ucb.sis213.Controller;

import bo.edu.ucb.sis213.Model.BackModel;
import bo.edu.ucb.sis213.View.LoginView;
import bo.edu.ucb.sis213.View.MenuView;
import bo.edu.ucb.sis213.View.ConsultaView;
import bo.edu.ucb.sis213.View.DepositoView;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    private Connection connection;
    private BackModel model;
    private LoginView loginView;
    private MenuView menuView;  
    private DepositoView depositoView;
    

    public App(Connection connection) {
        this.connection = connection;
        model = new BackModel(connection);
        loginView = new LoginView(this);
        
    }
    
    public void validarCredenciales(String usuario, String pin) {
        if (model.validarCredenciales(usuario, pin)) {
            loginView.close();
            menuView = new MenuView(this, usuario);
            System.out.println("ABRIMOS MENU :D");
        } else {
            int intentosRestantes = model.getIntentosRestantes();
            if (intentosRestantes > 0) {
                System.out.println("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");//joption2
            } else {
                System.out.println("PIN incorrecto. Ha excedido el número de intentos."); //joption3
                System.exit(0);
            }
        }
    }

    public void consultarSaldo() {
        double saldo = model.getSaldo();
        ConsultaView saldoView = new ConsultaView(saldo);
    }
    
    // // public void realizarDeposito(double cantidad) {
    // //     model.realizarDeposito(cantidad);
    // // }
    // public void mostrarDepositoView() {
    //     depositoView = new DepositoView(); // Pasa el controlador a la vista
    // }

    // public void realizarDeposito(double cantidad) {
    //     model.realizarDeposito(cantidad); // Llama al método en el modelo
    //     // Realizar otras operaciones después del depósito si es necesario
    // }

    // Resto de los métodos del controlador

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DatabaseConfig.getConnection();
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");//joption1
            ex.printStackTrace();
            System.exit(1);
        }

        new App(connection);
    }
}
