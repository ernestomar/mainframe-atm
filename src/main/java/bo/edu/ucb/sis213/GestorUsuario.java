package bo.edu.ucb.sis213;
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

    public boolean validarPIN(Usuario usuario, int pinIngresado) {
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

    public void realizarDeposito(int usuarioId,double saldo,double cantidad) {
        //El usuario ingresa la cantidad
        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else {
            try {
                String updateQuery = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1, cantidad);
                updateStatement.setInt(2, usuarioId); 
                int colMod = updateStatement.executeUpdate();
                if (colMod > 0) {
                    System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + (saldo + cantidad));
                } else {
                    System.out.println("Error al actualizar el saldo en la base de datos.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void realizarRetiro(int usuarioId, double saldo, double cantidad) {   
        //El usuario ingresa la cantidad 
        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else if (cantidad > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            try {
                String updateQuery = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1, cantidad);
                updateStatement.setInt(2, usuarioId); 
                int colMod = updateStatement.executeUpdate();
                if (colMod > 0) {
                    System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + (saldo - cantidad));
                } else {
                    System.out.println("Error al actualizar el saldo en la base de datos.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cambiarPIN(int usuarioId,int pin) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su PIN actual: ");
        int pinIngresado = scanner.nextInt();

        if (pinIngresado == pin) {
            System.out.print("Ingrese su nuevo PIN: ");
            int nuevoPin = scanner.nextInt();
            System.out.print("Confirme su nuevo PIN: ");
            int confirmacionPin = scanner.nextInt();

            if (nuevoPin == confirmacionPin) {
                try {
                    String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, nuevoPin);
                    updateStatement.setInt(2, usuarioId); 
                    int colMod = updateStatement.executeUpdate();
                    if (colMod > 0) {
                        System.out.println("PIN actualizado con éxito.");
                    } else {
                        System.out.println("Error al actualizar el pin en la base de datos.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Los PINs no coinciden.");
            }
        } else {
            System.out.println("PIN incorrecto.");
        }
    }

}
