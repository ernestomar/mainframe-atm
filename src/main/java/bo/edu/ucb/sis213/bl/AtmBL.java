package bo.edu.ucb.sis213.bl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import bo.edu.ucb.sis213.dao.HistoricoDao;
import bo.edu.ucb.sis213.dao.UsuarioDao;
import bo.edu.ucb.sis213.until.Exceptions;

public class AtmBL {
    private UsuarioDao usuarioDao;
    private HistoricoDao historicoDao;
    private Exceptions exception;

    private static int usuarioId;
    private int pin;
    private String nombre;
    private double saldo;
    // private double saldo;
    // private Connection connection;
    private int intentosRestantes;

    public AtmBL(String usuario, int pin) {
        this.usuarioDao = new UsuarioDao();
        this.historicoDao = new HistoricoDao();
        intentosRestantes = 3;
        usuarioId=usuarioDao.getUsuarioID(usuario, pin);
        pin=usuarioDao.getUsuarioPIN(usuarioId);
        nombre=usuarioDao.getUsuarioNombre(usuarioId);
        saldo=usuarioDao.getSaldo(usuarioId);
    } 

    public boolean validarLoginBL(String usuario, String pin){
        if(validarUserBL(usuario, pin)==1){
            return true;
        }else{
            return false;//return exception.Message(validarUserBL(usuario, pin));
        }
    }

    public int validarUserBL(String usuario, String pin) {
        if(usuario.isEmpty() && pin.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Ambos campos se encuentran vacios", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            // exception.ExceptionMessage(0, "Ambos campos se encuentran vacios", "Campos vacios", 2);
            return 4;
        }else if(usuario.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return 5;
        }else if(pin.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar el PIN", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return 6;
        }else if(usuario.isBlank() && pin.isBlank()){
            // JOptionPane.showMessageDialog(null, "Ambos campos se encuentran vacios", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            // exception.ExceptionMessage(0, "Ambos campos se encuentran vacios", "Campos vacios", 2);
            return 4;
        }else if(usuario.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return 5;
        }else if(pin.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar el PIN", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return 6;
        }else{
            if(usuarioDao.getUsuarioID(usuario, Integer.parseInt(pin))>0){
                return 1;
            }else {
                intentosRestantes--;
                System.out.println("false"+intentosRestantes);
                if (intentosRestantes > 0) {
                    System.out.println("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");//joption2
                    // JOptionPane.showMessageDialog(null, "PIN incorrecto. Le quedan " + intentosRestantes + " intentos.", "Error", JOptionPane.WARNING_MESSAGE);
                    return 2;
                } else {
                    System.out.println("PIN incorrecto. Ha excedido el número de intento(s)."); //joption3
                    JOptionPane.showMessageDialog(null, "PIN incorrecto. Ha excedido el n\u00FAmero de intentos.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                    return 3;
                }
            }

            // if (resultSet.next()) {
            //     usuarioId = resultSet.getInt("id");
            //     System.out.println("truee"+intentosRestantes);
            //     intentosRestantes = 3; // Reiniciar los intentos restantes
            //     System.out.println("truee"+intentosRestantes);
            //     JOptionPane.showMessageDialog(null, "PIN correcto", "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
            //     return true;
            // } else {
            //     intentosRestantes--;
            //     System.out.println("false"+intentosRestantes);
            //     if (intentosRestantes > 0) {
            //         System.out.println("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");//joption2
            //         JOptionPane.showMessageDialog(null, "PIN incorrecto. Le quedan " + intentosRestantes + " intentos.", "Error", JOptionPane.WARNING_MESSAGE);
            //         // return false;
            //     } else {
            //         System.out.println("PIN incorrecto. Ha excedido el número de intento(s)."); //joption3
            //         JOptionPane.showMessageDialog(null, "PIN incorrecto. Ha excedido el n\u00FAmero de intentos.", "Error", JOptionPane.ERROR_MESSAGE);
            //         System.exit(0);
            //     }
            //     return false;
            // }
        
        }
        // return false;
    }

    // public double getSaldo() {
    //     String query = "SELECT saldo FROM usuarios WHERE id = ?";
    //     try {
    //         PreparedStatement preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setInt(1, usuarioId);
    //         ResultSet resultSet = preparedStatement.executeQuery();

    //         if (resultSet.next()) {
    //             return resultSet.getDouble("saldo");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         JOptionPane.showMessageDialog(null, "Error al obtener saldo."+e, "Error", JOptionPane.ERROR_MESSAGE);
    //     }
    //     return 0.0; // Retornar un valor por defecto en caso de error
    // }

    public double obtenerSaldoBL(){
        return usuarioDao.getSaldo(usuarioId);
    }

    public boolean realizarDeposito(String cantidadStr) {
        double cantidad=0;
        System.out.println("Esta entrando TT");
        if (cantidadStr.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                System.out.println("Cantidad str"+cantidadStr);//joption4
                cantidad = Double.parseDouble(cantidadStr);
                System.out.println("Cantidad no."+cantidad);//joption4
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Monto no num\u00E9rico", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (cantidad <= 0) {
                    System.out.println("Cantidad no válida.");//joption4
                    JOptionPane.showMessageDialog(null, "Cantidad no v\u00E1lida.", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                System.out.println(saldo);//joption4
                // System.out.println(getSaldo());//joption4
                System.out.println("\nDepósito==" + saldo);//joption5
                int opdep = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de dep\u00F3sitar "+cantidadStr+" Bs. ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opdep == JOptionPane.YES_OPTION) {
                    try {
                        saldo += cantidad;
                        // actualizarSaldo(connection, saldo);
                        // registrarOperacion(connection, "Deposito", cantidad);
                        System.out.println("\nDepósito realizado con éxito. Su nuevo saldo es: $" + saldo);//joption5
                        JOptionPane.showMessageDialog(null, "Dep\u00F3sito realizado con \u00E9xito. Su nuevo saldo es: " + saldo+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("\nError al realizar el depósito.");
                        JOptionPane.showMessageDialog(null, "Error al realizar el dep\u00F3sito.", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                }else if (opdep == JOptionPane.NO_OPTION) {
                    System.out.println("\nNo continuar con el deposito");
                    return false;
                }
                }
        }
        return false;
    }

    public boolean realizarRetiro(String cantidadStr) {
        double cantidad=0.0;
        System.out.println("Esta entrando a retiro TT");
        if(cantidadStr.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                cantidad = Double.parseDouble(cantidadStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Monto no num\u00E9rico", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Cantidad no v\u00E1lida.", "Error", JOptionPane.ERROR_MESSAGE);
            }else if(cantidad>saldo){
            System.out.println("Saldo insuficiente.");
            JOptionPane.showMessageDialog(null, "Saldo insuficiente.", "Error", JOptionPane.WARNING_MESSAGE);
        }else{
            int opre = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de retirar "+cantidadStr+" Bs. ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opre == JOptionPane.YES_OPTION) {
                    saldo -= cantidad;
                    System.out.println("\nDepósito==" + saldo);
                    try {
                        // actualizarSaldo(connection, saldo); 
                        // registrarOperacion(connection, "Retiro", -cantidad); 
                        System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + saldo);
                        JOptionPane.showMessageDialog(null, "Retiro realizado con \u00E9xito. Su nuevo saldo es: " + saldo+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } catch (Exception ex) {
                        System.out.println("Error al realizar el retiro.");
                        ex.printStackTrace();
                    }
                }else if (opre == JOptionPane.NO_OPTION) {
                    System.out.println("\nNo continuar con el deposito");
                    return false;
                } 
            }
        }
        return false;
    }

    // public static void actualizarSaldo(Connection connection, double nuevoSaldo) throws SQLException {
    //     String query = "UPDATE usuarios SET saldo = ? WHERE id = ?";
    //     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //         preparedStatement.setDouble(1, nuevoSaldo);
    //         preparedStatement.setInt(2, usuarioId);
    //         preparedStatement.executeUpdate();
    //     }catch (SQLException ex) {
    //         System.out.println("Error al actualizar el saldo.");
    //         JOptionPane.showMessageDialog(null, "Error al actualizar el saldo.", "Error", JOptionPane.ERROR_MESSAGE);
    //         ex.printStackTrace();
    //     }
    // }
    
    // public static void registrarOperacion(Connection connection, String tipoOperacion, double cantidad) throws SQLException {
    //     String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";
    //     try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
    //         insertStatement.setInt(1, usuarioId);
    //         insertStatement.setString(2, tipoOperacion);
    //         insertStatement.setDouble(3, cantidad);
    //         insertStatement.executeUpdate();
    //     }catch (SQLException ex) {
    //         System.out.println("Error al registrar la operación.");
    //         JOptionPane.showMessageDialog(null, "Error al registrar la operaci\u00F3n.", "Error", JOptionPane.ERROR_MESSAGE);
    //         ex.printStackTrace();
    //     }
    // }  
    

    public boolean cambiarPIN(int newPIN, int confirmPIN) throws SQLException {
        if (newPIN == confirmPIN) {
            // if(Integer.parseInt(getPinActual())==newPIN){
                if(usuarioDao.getUsuarioPIN(usuarioId)==newPIN){
                JOptionPane.showMessageDialog(null, "Esta ingresando el mismo PIN", "Observaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
            }else{
                int oppin = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de cambiar su pin?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (oppin == JOptionPane.YES_OPTION) {
                    try {
                        // System.out.println("de "+getPinActual()+" a "+newPIN);
                        // actualizarPIN(connection, newPIN);
                        // registrarOperacion(connection, "Cambio de PIN", 0.0);
                        JOptionPane.showMessageDialog(null, "PIN actualizado con \u00E9xito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } catch (Exception ex) {
                        System.out.println("Error al actualizar el PIN.");
                        JOptionPane.showMessageDialog(null, "Error al actualizar el PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
            
        } else {
            System.out.println("Los PINs no coinciden.");
            JOptionPane.showMessageDialog(null, "Los PINs no coinciden.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    // public static void actualizarPIN(Connection connection, int nuevoPin) throws SQLException {
    //     String query = "UPDATE usuarios SET pin = ? WHERE id = ?";
    //     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //         preparedStatement.setInt(1, nuevoPin);
    //         preparedStatement.setInt(2, usuarioId);
    //         preparedStatement.executeUpdate();
    //     } catch (SQLException ex) {
    //         System.out.println("Error al actualizar el PIN.");
    //         ex.printStackTrace();
    //     }
    // }
    
    

    

    // public String getPinActual() {
    //     String query = "SELECT pin FROM usuarios WHERE id = ?";
    //     try {
    //         PreparedStatement preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setInt(1, usuarioId);
    //         ResultSet resultSet = preparedStatement.executeQuery();

    //         if (resultSet.next()) {
    //             return resultSet.getString("pin");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return ""; 
    // }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }
}
