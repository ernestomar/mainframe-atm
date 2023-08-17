package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import bo.edu.ucb.sis213.App;


public class CambiosCuenta{
    public static void cambiarPassword(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("PRUEBAS: Password actual: "+passwordActual);
        System.out.print("Ingrese su contraseña actual: ");
        String passwordIngresado = scanner.next();
        // Ingreso de nueva password
        if (passwordIngresado.equals(App.getPasswordActual())) {
            System.out.print("Ingrese su nueva contraseña: ");
            String nuevoPassword = scanner.next();
            System.out.print("Confirme su nueva contraseña: ");
            String confirmacionPassword = scanner.next();

            if (nuevoPassword.equals(confirmacionPassword)) {
                App.setPasswordActual(nuevoPassword);
                System.out.println("Contraseña actualizada con éxito.");
                // Actualizacion del PIN en la BDD
                String updateQuery = "UPDATE usuarios SET passw = ? WHERE id = ?";
                try {
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setString(1, nuevoPassword);
                    updateStatement.setInt(2, App.getUsuarioId());
                    updateStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Las contraseñas introducidas no coinciden.");
            }
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }
}