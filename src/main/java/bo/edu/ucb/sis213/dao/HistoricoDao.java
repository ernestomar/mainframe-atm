package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bo.edu.ucb.sis213.bl.UsuarioBl;

public class HistoricoDao {
    private Connection connection;
    public void realizarDeposito_HistoricoDao(UsuarioBl usuarioBl, double cantidad) {
        try {
            String updateQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?,?,?);";
            PreparedStatement insertStatement = connection.prepareStatement(updateQuery);
            insertStatement.setInt(1, usuarioBl.getUsuarioId());
            insertStatement.setString(2, "deposito");
            insertStatement.setDouble(3, cantidad);

            int colMod = insertStatement.executeUpdate();
            if (colMod > 0) {
                System.out.println("Historico actualizado");
            } else {
                System.out.println("Error al actualizar el historico.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void realizarRetiro_HistoricoDao(UsuarioBl usuarioBl, double cantidad) {
        try {
            String updateQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?,?,?);";
            PreparedStatement insertStatement = connection.prepareStatement(updateQuery);
            insertStatement.setInt(1, usuarioBl.getUsuarioId());
            insertStatement.setString(2, "retiro");
            insertStatement.setDouble(3, cantidad);
            int colMod = insertStatement.executeUpdate();
            if (colMod > 0) {
                System.out.println("Historico actualizado");
            } else {
                System.out.println("Error al actualizar el historico.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
