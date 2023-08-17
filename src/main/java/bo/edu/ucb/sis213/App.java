package bo.edu.ucb.sis213;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class App {
    private static int usuarioId;
    private static double saldo;
    private static String passwordActual;
    private static String usernameActual;

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String DATABASE = "atm";

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DATABASE);
        try {
            // Asegúrate de tener el driver de MySQL agregado en tu proyecto
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found.", e);
        }

        return DriverManager.getConnection(jdbcUrl, USER, PASSWORD);
    }

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
            System.out.println("Ingrese su nombre de usuario: ");
            String usernameIngresado = scanner.next();
            System.out.print("Ingrese su contraseña: ");
            String passwordIngresado = scanner.next();
            if (validarUsuario(connection, passwordIngresado, usernameIngresado)) {
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
    }

    public static boolean validarUsuario(Connection connection, String password,  String username) {
        String query = "SELECT id, saldo, alias FROM usuarios WHERE alias = ? AND passw = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioId = resultSet.getInt("id");
                saldo = resultSet.getDouble("saldo");
                usernameActual = resultSet.getString("alias");
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
            System.out.println("4. Cambiar contraseña.");
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
                    cambiarPassword(connection);
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
            saldo += cantidad;
            System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + saldo);
            // Actualizacion en la BDD
            String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";//Para usuarios
            String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";// Para historico
            try{
                // TABLA usuarios
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1,saldo);
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
    }

    public static void realizarRetiro(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a retirar: $");
        double cantidad = scanner.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else if (cantidad > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo -= cantidad;
            System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + saldo);
        }
        String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";//Para usuarios
        String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";// Para historico
        try{
            // TABLA usuarios
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1,saldo);
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

    }

    public static void cambiarPassword(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("PRUEBAS: Password actual: "+passwordActual);
        System.out.print("Ingrese su contraseña actual: ");
        String passwordIngresado = scanner.next();
        // Ingreso de nueva password
        if (passwordIngresado.equals(passwordActual)) {
            System.out.print("Ingrese su nueva contraseña: ");
            String nuevoPassword = scanner.next();
            System.out.print("Confirme su nueva contraseña: ");
            String confirmacionPassword = scanner.next();

            if (nuevoPassword.equals(confirmacionPassword)) {
                passwordActual = nuevoPassword;
                System.out.println("Contraseña actualizada con éxito.");
                // Actualizacion del PIN en la BDD
                String updateQuery = "UPDATE usuarios SET passw = ? WHERE id = ?";
                try {
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setString(1, nuevoPassword);
                    updateStatement.setInt(2, usuarioId);
                    updateStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Las contraseñas introducidas no coinciden.");
            }
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }
}   
