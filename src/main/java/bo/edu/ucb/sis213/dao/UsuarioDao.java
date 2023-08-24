package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UsuarioDao {
    private static Connection connection = null;
    private int intentos;
    
    public UsuarioDao() {
        try {
            connection = Conecction.getConnection();
            
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }
        intentos = 3;    
    }

    public int getUsuarioID(String alias, int pin){
        int id=-1;
        String query = "SELECT id FROM usuarios WHERE alias = ? AND pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, alias);
            preparedStatement.setInt(2, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id= resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Error al obtener id."+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return id; 
    }
    public int getUsuarioPIN(int usuarioId){
        int pin=0;
        String query = "SELECT pin FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pin= resultSet.getInt("pin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Error al obtener pin."+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return pin; 
    }
    public String getUsuarioNombre(int usuarioId){
        String nombre="";
        String query = "SELECT alias FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nombre= resultSet.getString("alias");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Error al obtener el nombre."+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return nombre;
    }
    public double getSaldo(int usuarioId) {
        double saldo=-1;
        String query = "SELECT saldo FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                saldo= resultSet.getDouble("saldo");
                System.out.println(saldo);
                return saldo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Error al obtener saldo."+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return saldo;
    }

    public void actualizarSaldo(double nuevoSaldo, int usuarioId) throws SQLException {
        String query = "UPDATE usuarios SET saldo = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, nuevoSaldo);
            preparedStatement.setInt(2, usuarioId);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error al actualizar el saldo.");
            // JOptionPane.showMessageDialog(null, "Error al actualizar el saldo.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void actualizarPIN(int nuevoPin, int usuarioId) throws SQLException {
        String query = "UPDATE usuarios SET pin = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, nuevoPin);
            preparedStatement.setInt(2, usuarioId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el PIN.");
            // JOptionPane.showMessageDialog(null, "Error al actualizar el PIN.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


}


