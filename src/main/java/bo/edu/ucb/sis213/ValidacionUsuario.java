package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bo.edu.ucb.sis213.App;


public class ValidacionUsuario{

    public static boolean validarUsuario(Connection connection, String password,  String username) {
        String query = "SELECT id, saldo, alias FROM usuarios WHERE alias = ? AND passw = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int usuarioId = resultSet.getInt("id");
                double saldo = resultSet.getDouble("saldo");
                // usernameActual = resultSet.getString("alias");

                // Establecer los valores en la clase App usando los setters
                App.setUsuarioId(usuarioId);
                App.setSaldo(saldo);
                App.setUsernameActual(username);
                App.setPasswordActual(password);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}