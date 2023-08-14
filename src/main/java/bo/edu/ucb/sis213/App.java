
package bo.edu.ucb.sis213;

import java.sql.*;
import java.util.Scanner;

public class App {
    // Datos para ingresar a su base de datos en mysqlex
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bdd_atm";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static int idUsuarioActual = -1; // Identificador del usuario

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intentos = 3;

        System.out.println("Bienvenido al Cajero Automático.");

        while (intentos > 0) {
            System.out.print("Ingrese su PIN de 4 dígitos: ");
            int pinIngresado = scanner.nextInt();
            if (verificarPIN(pinIngresado)) {
                mostrarMenu();
                break;
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("PIN incorrecto. Le quedan " + intentos + " intentos.");
                } else {
                    System.out.println("PIN incorrecto. Ha excedido el número de intentos.");
                    System.exit(0);
                }
            }
        }
    }

    public static boolean verificarPIN(int pinIngresado) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Conexión exitosa a la base de datos.");

            String selectQuery = "SELECT id_usuario FROM Usuarios WHERE pin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, String.valueOf(pinIngresado));
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    idUsuarioActual = resultSet.getInt("id_usuario");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Error al verificar el PIN: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return false;
    }

    public static void mostrarMenu() {
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
                    realizarDeposito();
                    break;
                case 3:
                    realizarRetiro();
                    break;
                case 4:
                    cambiarPIN();
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
        if (idUsuarioActual != -1) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                System.out.println("Conexión exitosa a la base de datos.");

                String selectQuery = "SELECT saldo FROM OperacionesUsuarios WHERE id_usuario = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setInt(1, idUsuarioActual);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        double saldoUsuario = resultSet.getDouble("saldo");
                        System.out.println("Su saldo actual es: $" + saldoUsuario);
                    } else {
                        System.out.println("No se encontró el saldo del usuario.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al consultar el saldo: " + e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        } else {
            System.out.println("Debe ingresar un PIN válido antes de consultar el saldo.");
        }
    }

    public static void realizarDeposito() {
        if (idUsuarioActual != -1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la cantidad a depositar: $");
            double cantidad = scanner.nextDouble();

            if (cantidad <= 0) {
                System.out.println("Cantidad no válida.");
            } else {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    System.out.println("Conexión exitosa a la base de datos.");

                    String updateQuery = "UPDATE OperacionesUsuarios SET saldo = saldo + ? WHERE id_usuario = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setDouble(1, cantidad);
                        preparedStatement.setInt(2, idUsuarioActual);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Error al realizar el depósito: " + e.getMessage());
                    }

                    consultarSaldo(); // Consultar el saldo actualizado
                } catch (SQLException e) {
                    System.out.println("Error al conectar a la base de datos: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Debe ingresar un PIN válido antes de realizar un depósito.");
        }
    }

    public static void realizarRetiro() {
        if (idUsuarioActual != -1) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                System.out.println("Conexión exitosa a la base de datos.");

                String selectQuery = "SELECT saldo FROM OperacionesUsuarios WHERE id_usuario = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setInt(1, idUsuarioActual);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        double saldoUsuario = resultSet.getDouble("saldo");
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Ingrese la cantidad a retirar: $");
                        double cantidad = scanner.nextDouble();

                        if (cantidad <= 0) {
                            System.out.println("Cantidad no válida.");
                        } else if (cantidad > saldoUsuario) {
                            System.out.println("Saldo insuficiente.");
                        } else {
                            String updateQuery = "UPDATE OperacionesUsuarios SET saldo = saldo - ? WHERE id_usuario = ?";
                            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                                updateStatement.setDouble(1, cantidad);
                                updateStatement.setInt(2, idUsuarioActual);
                                updateStatement.executeUpdate();
                            } catch (SQLException e) {
                                System.out.println("Error al realizar el retiro: " + e.getMessage());
                            }

                            consultarSaldo(); // Consultar el saldo actualizado
                        }
                    } else {
                        System.out.println("No se encontró el saldo del usuario.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al consultar el saldo: " + e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        } else {
            System.out.println("Debe ingresar un PIN válido antes de realizar un retiro.");
        }
    }

    public static void cambiarPIN() {
        if (idUsuarioActual != -1) { // Verificar que haya ingresado un PIN válido
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese su nuevo PIN: ");
            int nuevoPin = scanner.nextInt();
            System.out.print("Confirme su nuevo PIN: ");
            int confirmacionPin = scanner.nextInt();

            if (nuevoPin == confirmacionPin) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    System.out.println("Conexión exitosa a la base de datos.");

                    String updateQuery = "UPDATE Usuarios SET pin = ? WHERE id_usuario = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setString(1, String.valueOf(nuevoPin));
                        preparedStatement.setInt(2, idUsuarioActual);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Error al actualizar el PIN: " + e.getMessage());
                    }

                    System.out.println("PIN actualizado con éxito.");
                } catch (SQLException e) {
                    System.out.println("Error al conectar a la base de datos: " + e.getMessage());
                }
            } else {
                System.out.println("Los PINs no coinciden.");
            }
        } else {
            System.out.println("Debe ingresar un PIN válido antes de cambiarlo.");
        }
    }
}
