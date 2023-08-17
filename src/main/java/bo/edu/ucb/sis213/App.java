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
import bo.edu.ucb.sis213.Menu;

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

// Inicio del programa, con el login
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
                Menu.mostrarMenu(connection);
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
}   
