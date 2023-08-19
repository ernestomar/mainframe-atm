package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ATM {
    private int usuarioId;
    public double saldo;
    private int pinActual;
    private Connection connection;

    public ATM(Connection connection) {
        this.connection = connection;
    }

    public double getSaldo() {
        return saldo;
    }

    public int obtenerPinDesdeDB() {
        String query = "SELECT pin FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("pin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Valor sentinela para indicar que no se pudo obtener el PIN
    }

    public boolean validarPIN(String alias, int pin) {
        String query = "SELECT id, saldo FROM usuarios WHERE alias = ? AND pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, alias);
            preparedStatement.setInt(2, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioId = resultSet.getInt("id");
                saldo = resultSet.getDouble("saldo");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + saldo);
    }

    public void cambiarPIN(int pinIngresado, int nuevoPin, int confirmacionPin) throws SQLException {

        // Actualiza el PIN en la base de datos
        try (PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE usuarios SET pin = ? WHERE id = ?")) {
            updateStatement.setInt(1, nuevoPin);
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
        }

        this.pinActual = nuevoPin;
    }

    public void realizarDeposito(double cantidad) throws SQLException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad no válida.");
        }

        // Actualiza el saldo en la base de datos
        try (PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE usuarios SET saldo = saldo + ? WHERE id = ?")) {
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
        }

        // Registra la operación en el histórico
        registrarOperacionHistorico("Depósito", cantidad);

        saldo += cantidad;
    }

    public void realizarRetiro(double cantidad) throws SQLException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad no válida.");
        } else if (cantidad > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        // Actualiza el saldo en la base de datos
        try (PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE usuarios SET saldo = saldo - ? WHERE id = ?")) {
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
        }

        // Registra la operación en el histórico
        registrarOperacionHistorico("Retiro", cantidad);

        saldo -= cantidad;
    }

    public void registrarOperacionHistorico(String tipoOperacion, double cantidad) throws SQLException {
        try (PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO historicos (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)")) {
            insertStatement.setInt(1, usuarioId);
            insertStatement.setString(2, tipoOperacion);
            insertStatement.setDouble(3, cantidad);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
