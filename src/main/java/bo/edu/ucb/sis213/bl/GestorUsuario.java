package bo.edu.ucb.sis213.bl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorUsuario {
    private Connection connection;

    public GestorUsuario(Connection connection) {
        this.connection = connection;
    }

    public boolean validarPIN(UsuarioBl usuario, int pinIngresado) {
        //El usuario ingresa el pinIngresado
        String query = "SELECT id, saldo, nombre FROM usuarios WHERE pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinIngresado);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario.setUsuarioId(resultSet.getInt("id"));
                usuario.setSaldo(resultSet.getDouble("saldo"));
                usuario.setNombreUser(resultSet.getString("nombre"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void consultarSaldo(double saldo) {
        System.out.println("Su saldo actual es: $" + saldo);
    }

    public boolean realizarDeposito(int usuarioId,double saldo,double cantidad) {
        try {
            String updateQuery = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioId);
            int colMod = updateStatement.executeUpdate();
            if (colMod > 0) {
                System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + (saldo + cantidad));
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
    
    public boolean realizarRetiro(int usuarioId, double saldo, double cantidad) {
        try {
            String updateQuery = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1, cantidad);
            updateStatement.setInt(2, usuarioId);
            int colMod = updateStatement.executeUpdate();
            if (colMod > 0) {
                System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + (saldo - cantidad));
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

    public boolean cambiarPIN(int usuarioId,int pin) {
        try {
            String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, pin);
            updateStatement.setInt(2, usuarioId);
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
    public static double consultarSaldo_UsuarioBl(UsuarioBl usuarioBl) {
        double saldo=usuarioBl.getSaldo();
        return saldo;
    }

}
