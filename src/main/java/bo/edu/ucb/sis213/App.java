package bo.edu.ucb.sis213;

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
    private static int usuarioId;
    private static double saldo;
    private static int pinActual;
    private static String nombreUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intentos = 3;

        System.out.println("Bienvenido al Cajero Automático.");

        Connection connection = null;
        try {
            connection = getConnection(); // Reemplaza esto con tu conexión real
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }
        

        while (intentos > 0) {
            System.out.print("Ingrese su PIN de 4 dígitos: ");
            int pinIngresado = scanner.nextInt();
            if (validarPIN(connection, pinIngresado)) {
                pinActual = pinIngresado;
                System.out.println("Bienvenid@ "+nombreUser+" 😀!!!!! ");
                mostrarMenu(connection);
                break;
            } else {
                intentos--;
                if (intentos > 1) {
                    System.out.println("PIN incorrecto. Le quedan " + intentos + " intentos. 🙂");
                } else if (intentos == 1) {
                    System.out.println("PIN incorrecto. Le queda " + intentos + " intento. 😐");
                } else {
                    System.out.println("PIN incorrecto. Ha excedido el número de intentos. ☹");
                    System.exit(0);
                }
            }
        }
    }

    public static boolean validarPIN(Connection connection, int pin) {
        String query = "SELECT id, saldo, nombre FROM usuarios WHERE pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioId = resultSet.getInt("id");
                saldo = resultSet.getDouble("saldo");
                nombreUser = resultSet.getString("nombre");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void mostrarMenu(Connection connection) {
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
                    consultarSaldo();
                    break;
                case 2:
                    realizarDeposito(connection);
                    break;
                case 3:
                    realizarRetiro(connection);
                    break;
                case 4:
                    cambiarPIN(connection);
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

    public static void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + saldo);
    }

    public static void realizarDeposito(Connection connection) {
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
                    saldo += cantidad;
                    System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + saldo);
                } else {
                    System.out.println("Error al actualizar el saldo en la base de datos.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    public static void realizarRetiro(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a retirar: $");
        double cantidad = scanner.nextDouble();
    
        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        }else if (cantidad > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            try {
                String updateQuery = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1, cantidad);
                updateStatement.setInt(2, usuarioId); 
                int colMod = updateStatement.executeUpdate(); //ver si se realizaron modificaciones
                if (colMod > 0) {
                    saldo -= cantidad;
                    System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + saldo);
                } else {
                    System.out.println("Error al actualizar el saldo en la base de datos.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cambiarPIN(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su PIN actual: ");
        int pinIngresado = scanner.nextInt();

        if (pinIngresado == pinActual) {
            System.out.print("Ingrese su nuevo PIN: ");
            int nuevoPin = scanner.nextInt();
            System.out.print("Confirme su nuevo PIN: ");
            int confirmacionPin = scanner.nextInt();

            if (nuevoPin == confirmacionPin) {
                try {
                    String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setDouble(1, nuevoPin);
                    updateStatement.setInt(2, usuarioId); 
                    int colMod = updateStatement.executeUpdate(); //ver si se realizaron modificaciones
                    if (colMod > 0) {
                        pinActual = nuevoPin;
                        System.out.println("PIN actualizado con éxito.");
                    } else {
                        System.out.println("Error al actualizar el pin en la base de datos.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Los PINs no coinciden.");
            }
        } else {
            System.out.println("PIN incorrecto.");
        }
    }
}