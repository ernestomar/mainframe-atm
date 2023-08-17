package bo.edu.ucb.sis213.Controller;

import bo.edu.ucb.sis213.Model.BackModel;
import bo.edu.ucb.sis213.View.LoginView;
import bo.edu.ucb.sis213.View.MenuView;
import bo.edu.ucb.sis213.View.ConsultaView;
import bo.edu.ucb.sis213.View.DepositoView;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    private Connection connection;
    private BackModel model;
    private LoginView loginView;
    private MenuView menuView;  
    

    public App(Connection connection) {
        this.connection = connection;
        this.model = new BackModel(connection);
        //loginView = new LoginView(this);
        
    }
    // public AppLogin(Connection connection) {
    //     this.connection = connection;
    //     this.model = new BackModel(connection);
    //     //loginView = new LoginView(this);
        
    // }
    

    public void consultarSaldo() {
        double saldo = model.getSaldo();
        ConsultaView saldoView = new ConsultaView(saldo);
    }
    
    // public void realizarDeposito(double cantidad) {
    //     model.realizarDeposito(cantidad);
    // }
    public void mostrarDepositoView() {
        DepositoView depositoView = new DepositoView(connection); // Pasa el controlador a la vista
    }

    // public void realizarDeposito(double cantidad) {
    //     model.realizarDeposito(cantidad); // Llama al método en el modelo
    //     // Realizar otras operaciones después del depósito si es necesario
    // }

    // Resto de los métodos del controlador

    
}
