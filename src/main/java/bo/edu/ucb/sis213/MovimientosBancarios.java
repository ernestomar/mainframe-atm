package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import bo.edu.ucb.sis213.App;


public class MovimientosBancarios{
    public static void realizarDeposito(Connection connection) {

        int usuarioId = App.getUsuarioId();
        double saldo = App.getSaldo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a depositar: $");
        double cantidad = scanner.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else {
            App.setSaldo(saldo + cantidad);
            System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + App.getSaldo());
            // Actualizacion en la BDD
            String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";//Para usuarios
            String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";// Para historico
            try{
                // TABLA usuarios
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1,App.getSaldo());
                updateStatement.setInt(2, usuarioId);
                updateStatement.executeUpdate();
                // TABLA historico 
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1, usuarioId);
                insertStatement.setString(2, "deposito");
                insertStatement.setDouble(3, cantidad);
                insertStatement.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        // scanner.close();

    }

    public static void realizarRetiro(Connection connection) {

        int usuarioId = App.getUsuarioId();
        double saldo = App.getSaldo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a retirar: $");
        double cantidad = scanner.nextDouble();
        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else if (cantidad > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            App.setSaldo(saldo-cantidad);
            System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + App.getSaldo());
        }
        String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";//Para usuarios
        String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";// Para historico
        try{
            // TABLA usuarios
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1,App.getSaldo());
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
            // TABLA historico 
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, usuarioId);
            insertStatement.setString(2, "retiro");
            insertStatement.setDouble(3, cantidad);
            insertStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        // scanner.close();
    }

    public static void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + App.getSaldo());
    }
}