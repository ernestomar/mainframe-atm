package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class HistoricoDao {
    private static Connection connection = null;
    public HistoricoDao() {
        try {
            connection = Conecction.getConnection();
            
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }
        
    }

    public static void registrarOperacion(int usuarioId, String tipoOperacion, double cantidad) throws SQLException {
        String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, usuarioId);
            insertStatement.setString(2, tipoOperacion);
            insertStatement.setDouble(3, cantidad);
            insertStatement.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error al registrar la operación.");
            JOptionPane.showMessageDialog(null, "Error al registrar la operaci\u00F3n.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }  
}


