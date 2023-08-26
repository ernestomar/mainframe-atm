package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoDAO {
    private Connection connection;

    public HistoricoDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarOperacionHistoricoDAO(int usuarioId, String tipoOperacion, double cantidad)
            throws SQLException {
        try (PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO historicos (id, tipo_operacion, cantidad) VALUES (?, ?, ?)")) {
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
