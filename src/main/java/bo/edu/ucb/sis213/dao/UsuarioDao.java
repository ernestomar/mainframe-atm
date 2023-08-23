package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.edu.ucb.sis213.bl.GestorUsuario;
import bo.edu.ucb.sis213.bl.UsuarioBl;
import bo.edu.ucb.sis213.dao.HistoricoDao;

public class UsuarioDao {
    private Connection connection;
    private HistoricoDao historicoDao;
    public boolean validarPIN_UsuarioDao (UsuarioBl usuarioBl, int pinIngresado){
        String query = "SELECT id, saldo, nombre FROM usuarios WHERE pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinIngresado);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioBl.setUsuarioId(resultSet.getInt("id"));
                usuarioBl.setSaldo(resultSet.getDouble("saldo"));
                usuarioBl.setNombreUser(resultSet.getString("nombre"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean realizarDeposito_UsuarioDao(UsuarioBl usuarioBl, double cantidad) {
        try {
            String updateQuery = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioBl.getUsuarioId());
            int colMod = updateStatement.executeUpdate();
            if (colMod > 0) {
                System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + usuarioBl.getSaldo());
                historicoDao.realizarDeposito_HistoricoDao(usuarioBl,cantidad);
                return true;
            } else {
                System.out.println("Error al actualizar el saldo en la base de datos.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean realizarRetiro_UsuarioDao(UsuarioBl usuarioBl, double cantidad) {
        try {
            String updateQuery = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioBl.getUsuarioId());
            int colMod = updateStatement.executeUpdate();
            if (colMod > 0) {
                System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + usuarioBl.getSaldo());
                historicoDao.realizarRetiro_HistoricoDao(usuarioBl,cantidad);
                return true;
            } else {
                System.out.println("Error al actualizar el saldo en la base de datos.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cambiarPIN_UsuarioDao(UsuarioBl usuarioBl,int pin) {
        try {
            String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, pin);
            updateStatement.setInt(2, usuarioBl.getUsuarioId());
            int colMod = updateStatement.executeUpdate();
            if (colMod > 0) {
                System.out.println("PIN actualizado con éxito.");
                return true;
            } else {
                System.out.println("Error al actualizar el pin en la base de datos.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
