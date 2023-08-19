package bo.edu.ucb.sis213;

import bo.edu.ucb.sis213.screens.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }


        // Obtener el usuario y validar el PIN
        Usuario usuario = new Usuario(connection, 0, 0, 0, null); // Valores temporales
        new Login(connection);
    }

    public static void mostrarMenu(Connection connection, Usuario usuario, GestorUsuario gestorUsuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Consultar saldo.");
            System.out.println("2. Realizar un depósito.");
            System.out.println("3. Realizar un retiro.");
            System.out.println("4. Cambiar PIN.");
            System.out.println("5. Salir.");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestorUsuario.consultarSaldo(usuario.getSaldo());
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: $");
                    double dep = scanner.nextDouble();
    
                    gestorUsuario.realizarDeposito(usuario.getUsuarioId(), usuario.getSaldo(), dep);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: $");
                    double ret = scanner.nextDouble();

                    gestorUsuario.realizarRetiro(usuario.getUsuarioId(), usuario.getSaldo(), ret);
                    break;
                case 4:
                    gestorUsuario.cambiarPIN(usuario.getUsuarioId(), usuario.getPinActual());
                    break;
                case 5:
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
