package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAO {
    private Connection connection;
    private int usuarioId;
    public double saldo;

    public UsuariosDAO(Connection connection) {
        this.connection = connection;

    }

    public int obtenerPinDesdeDBDAO(int usuarioId) throws SQLException {
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
        return -1; // Default value indicating PIN not found
    }

    public boolean validarPINDAO(String alias, int pin) {

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

    public void cambiarPINDAO(int pinIngresado, int nuevoPin, int confirmacionPin) throws SQLException {

        try (PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE usuarios SET pin = ? WHERE id = ?")) {
            updateStatement.setInt(1, nuevoPin);
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void realizarRetiroDAO(double cantidad) throws SQLException {
        try (PreparedStatement updateStatement = connection.prepareStatement(
                "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?")) {
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioId);
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new SQLException("No se pudo realizar el retiro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void realizarDepositoDAO(double cantidad) throws SQLException {
        try (PreparedStatement updateStatement = connection.prepareStatement(
                "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?")) {
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioId);
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new SQLException("No se pudo realizar el depósito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
