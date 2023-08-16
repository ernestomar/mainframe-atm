package bo.edu.ucb.sis213.screens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Deposito {

    public static void realizarDeposito(Connection connection, int usuarioId, double saldoActual) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a depositar: $");
        double cantidad = scanner.nextDouble();
    
        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else {
            try {
                String updateQuery = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1, cantidad);
                updateStatement.setInt(2, usuarioId); 
                int colMod = updateStatement.executeUpdate(); //ver si se realizaron modificaciones
                if (colMod > 0) {
                    saldoActual += cantidad;
                    System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + saldoActual);
                } else {
                    System.out.println("Error al actualizar el saldo en la base de datos.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
