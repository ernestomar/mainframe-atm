package bo.edu.ucb.sis213;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bo.edu.ucb.sis213.ValidacionUsuario;
import bo.edu.ucb.sis213.MovimientosBancarios;
import bo.edu.ucb.sis213.CambiosCuenta;
import bo.edu.ucb.sis213.ConexionBDD;

public class App {

     private static int usuarioId;
     private static double saldo;
     private static String passwordActual;
     private static String usernameActual;
     
     public static int getUsuarioId() {
        return usuarioId;
    }

    public static double getSaldo() {
        return saldo;
    }

    public static String getPasswordActual() {
        return passwordActual;
    }

    public static String getUsernameActual() {
        return usernameActual;
    }

    // Setters
    public static void setUsuarioId(int usuarioId) {
        App.usuarioId = usuarioId;
    }

    public static void setSaldo(double saldo) {
        App.saldo = saldo;
    }

    public static void setPasswordActual(String passwordActual) {
        App.passwordActual = passwordActual;
    }

    public static void setUsernameActual(String usernameActual) {
        App.usernameActual = usernameActual;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intentos = 3;

        System.out.println("Bienvenido al Cajero Automático.");
        Connection connection = null;
        try {
            connection = ConexionBDD.getConnection();
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }
        

        while (intentos > 0) {
            System.out.println("Ingrese su nombre de usuario: ");
            String usernameIngresado = scanner.next();
            System.out.print("Ingrese su contraseña: ");
            String passwordIngresado = scanner.next();
            if (ValidacionUsuario.validarUsuario(connection, passwordIngresado, usernameIngresado)) {
                passwordActual = passwordIngresado;
                mostrarMenu(connection);
                break;
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("El nombre de usuario y la contraseña no coinciden. Le quedan " + intentos + " intentos.");
                } else {
                    System.out.println("Nombre de usuario o contraseña incorrectos. Ha excedido el número de intentos.");
                    System.exit(0);
                }
            }
        }
        scanner.close();
    }

    public static void mostrarMenu(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Consultar saldo.");
            System.out.println("2. Realizar un depósito.");
            System.out.println("3. Realizar un retiro.");
            System.out.println("4. Cambiar contraseña.");
            System.out.println("5. Salir.");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    MovimientosBancarios.consultarSaldo();
                    break;
                case 2:
                    MovimientosBancarios.realizarDeposito(connection);
                    break;
                case 3:
                    MovimientosBancarios.realizarRetiro(connection);;
                    break;
                case 4:
                    CambiosCuenta.cambiarPassword(connection);
                    break;
                case 5:
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

}   
