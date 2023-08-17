package bo.edu.ucb.sis213.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BackModel {
    private static int usuarioId;
    private double saldo;
    private int pinActual;
    private Connection connection;
    private int intentosRestantes;

    public BackModel(Connection connection) {
        this.connection = connection;
        intentosRestantes = 3;
        saldo=getSaldo();
    }
    
    // Dentro de la clase BackModel

    public boolean validarCredenciales(String usuario, String pin) {
        String query = "SELECT id FROM usuarios WHERE alias = ? AND pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioId = resultSet.getInt("id");
                intentosRestantes = 3; // Reiniciar los intentos restantes
                return true;
            } else {
                intentosRestantes--;
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void realizarDeposito(double cantidad) {
        System.out.println("Esta entrando TT");//joption4
        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");//joption4
            return;
        }

        saldo += cantidad;
        System.out.println("\nDepósito==" + saldo);//joption5
        try {
            actualizarSaldo(connection, saldo);
            registrarOperacion(connection, "Deposito", cantidad);
            System.out.println("\nDepósito realizado con éxito. Su nuevo saldo es: $" + saldo);//joption5
            // return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("\nError al realizar el depósito.");//joption6
            // return false;
        }
    }

    public static void actualizarSaldo(Connection connection, double nuevoSaldo) throws SQLException {
        String query = "UPDATE usuarios SET saldo = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, nuevoSaldo);
            preparedStatement.setInt(2, usuarioId);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error al actualizar el saldo.");
            ex.printStackTrace();
        }
    }
    
    public static void registrarOperacion(Connection connection, String tipoOperacion, double cantidad) throws SQLException {
        String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, usuarioId);
            insertStatement.setString(2, tipoOperacion);
            insertStatement.setDouble(3, cantidad);
            insertStatement.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error al registrar la operación.");
            ex.printStackTrace();
        }
    }   



    // public void realizarDeposito(double amount) throws SQLException {
    //     double nuevoSaldo = saldo + amount;
    //     String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";
    //     try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
    //         preparedStatement.setDouble(1, nuevoSaldo);
    //         preparedStatement.setInt(2, usuarioId);
    //         preparedStatement.executeUpdate();
    //         saldo = nuevoSaldo;
    //         registrarOperacion("Depósito", amount);
    //     } catch (SQLException ex) {
    //         ex.printStackTrace();
    //         throw ex;
    //     }
    // }

    public void cambiarPIN(int currentPIN, int newPIN, int confirmPIN) throws SQLException {
        if (currentPIN == pinActual && newPIN == confirmPIN) {
            String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, newPIN);
                preparedStatement.setInt(2, usuarioId);
                preparedStatement.executeUpdate();
                pinActual = newPIN;
                registrarOperacion("Cambio de PIN", 0.0);
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }
        } else {
            throw new SQLException("PIN actual incorrecto o los nuevos PIN no coinciden.");
        }
    }

    public void registrarOperacion(String tipoOperacion, double cantidad) throws SQLException {
        String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, usuarioId);
            insertStatement.setString(2, tipoOperacion);
            insertStatement.setDouble(3, cantidad);
            insertStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    // Agregar más métodos para otras operaciones y lógica de negocio

    // Dentro de la clase BackModel

    public double getSaldo() {
        String query = "SELECT saldo FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("saldo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // Retornar un valor por defecto en caso de error
    }

    //     public double getusuarioId() {
    //     String query = "SELECT id FROM usuarios WHERE id = ?";
    //     try {
    //         PreparedStatement preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setInt(1, usuarioId);
    //         ResultSet resultSet = preparedStatement.executeQuery();

    //         if (resultSet.next()) {
    //             return resultSet.getDouble("saldo");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return 0.0; // Retornar un valor por defecto en caso de error
    // }


    public int getPinActual() {
        return pinActual;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }
}
