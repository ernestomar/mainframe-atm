package bo.edu.ucb.sis213.bl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import bo.edu.ucb.sis213.dao.HistoricoDao;
import bo.edu.ucb.sis213.dao.UsuarioDao;

public class AtmBL {
    private UsuarioDao usuarioDao;
    private HistoricoDao historicoDao;
    // private Exceptions util;

    private static int usuarioId;
    private int usuarioPin;
    private String usuarioNombre;
    private double usuarioSaldo;
    // private double saldo;
    // private Connection connection;
    private int intentosRestantes;
    private String textoE="texto",tituloE="titulo";

    public AtmBL() {
        this.usuarioDao = new UsuarioDao();
        this.historicoDao = new HistoricoDao();
        intentosRestantes = 3;
        //this.util = new Exceptions(textoE,tituloE,0);
        // usuarioId=usuarioDao.getUsuarioID(usuario, pin);
        // pin=usuarioDao.getUsuarioPIN(usuarioId);
        // nombre=usuarioDao.getUsuarioNombre(usuarioId);
        // System.out.println(usuarioId);
        // saldo=usuarioDao.getSaldo(usuarioId);
        usuarioSaldo=0;
        usuarioId=0;
        usuarioPin=0;
        usuarioNombre=null;
        System.out.println(usuarioId);
        
    }
    public double getSaldo(){
        return usuarioDao.getSaldo(usuarioId);
    }
    public String getNombre(){
        return usuarioDao.getUsuarioNombre(usuarioId);
    }
    public int getPin(){
        return usuarioDao.getUsuarioPIN(usuarioId);
    }
    public int getID(){
        return usuarioDao.getUsuarioID(usuarioNombre, usuarioPin);
    }
    public int getIntentos(){
        return intentosRestantes;
    } 
    public String getTextoE(){
        return textoE;
    }
    public String getTituloE(){
        return tituloE;
    }

    public boolean validarLoginBL(String usuario, String pin){
        if(validarUserBL(usuario, pin)){
            return true;
        }else{
            if(intentosRestantes<=0){
                System.exit(0);
            }
            return false;
        }
    }

    public boolean validarUserBL(String usuario, String pin) {
        if(usuario.isEmpty() && pin.isEmpty()){
            textoE="Ambos campos se encuentran vacios";
            tituloE="Campos vacios";
            // JOptionPane.showMessageDialog(null, "Ambos campos se encuentran vacios", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(usuario.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            textoE="Debe ingresar un usuario";
            tituloE="Campos vacios";
            return false;
        }else if(pin.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar el PIN", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            textoE="Debe ingresar un PIN";
            tituloE="Campos vacios";
            return false;
        }else if(usuario.isBlank() && pin.isBlank()){
            // JOptionPane.showMessageDialog(null, "Ambos campos se encuentran vacios", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            // exception.ExceptionMessage(0, "Ambos campos se encuentran vacios", "Campos vacios", 2);
            textoE="Ambos campos se encuntran vacios";
            tituloE="Campos vacios";
            return false;
        }else if(usuario.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            textoE="Debe ingresar un usuario";
            tituloE="Campos vacios";
            return false;
        }else if(pin.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar el PIN", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            textoE="Debe ingresar un PIN";
            tituloE="Campos vacios";
            return false;
        }else{
            if(usuarioDao.getUsuarioID(usuario, Integer.parseInt(pin))>0){
                usuarioId=usuarioDao.getUsuarioID(usuario, Integer.parseInt(pin));
                usuarioPin=usuarioDao.getUsuarioPIN(usuarioId);
                usuarioNombre=usuarioDao.getUsuarioNombre(usuarioId);
                System.out.println(usuarioId);
                usuarioSaldo=usuarioDao.getSaldo(usuarioId);
                System.out.println("tamos aqui TT"+usuarioSaldo);
                return true;
            }else {
                intentosRestantes--;
                System.out.println("false"+intentosRestantes);
                if (intentosRestantes > 0) {
                    System.out.println("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");//joption2
                    // JOptionPane.showMessageDialog(null, "PIN incorrecto. Le quedan " + intentosRestantes + " intentos.", "Error", JOptionPane.WARNING_MESSAGE);
                    textoE="PIN incorrecto. Le quedan " + intentosRestantes + " intentos.";
                    tituloE="Error";
                    return false;
                } else {
                    System.out.println("PIN incorrecto. Ha excedido el número de intento(s)."); //joption3
                    // JOptionPane.showMessageDialog(null, "PIN incorrecto. Ha excedido el n\u00FAmero de intentos.", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    textoE="PIN incorrecto. Ha excedido el numero de intentos.";
                    tituloE="Error";
                    // System.exit(0);
                    return false;
                }
            }

                    
        }
        // return false;
    }

    public boolean realizarDeposito(String cantidadStr) {
        double cantidad=0;
        System.out.println("Esta entrando TT");
        if (cantidadStr.isEmpty() ) {
            // JOptionPane.showMessageDialog(null, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
            textoE="Debe ingresar un monto.";
            tituloE="Error";
        }else{
            try {
                System.out.println("Cantidad str"+cantidadStr);//joption4
                cantidad = Double.parseDouble(cantidadStr);
                System.out.println("Cantidad no."+cantidad);//joption4
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(null, "Monto no num\u00E9rico", "Error", JOptionPane.ERROR_MESSAGE);
                textoE="Monto no num\\u00E9rico";
                tituloE="Error";
                return false;
            }
            if (cantidad <= 0) {
                    System.out.println("Cantidad no válida.");//joption4
                    // JOptionPane.showMessageDialog(null, "Cantidad no v\u00E1lida.", "Error", JOptionPane.ERROR_MESSAGE);
                    textoE="Cantidad no válida";
                    tituloE="Error";
                }else{
                System.out.println(usuarioSaldo);//joption4
                
                System.out.println("\nDepósito==" + usuarioSaldo);//joption5
                try {
                        usuarioSaldo += cantidad;
                        usuarioDao.actualizarSaldo(usuarioSaldo,usuarioId);
                        historicoDao.registrarOperacion(usuarioId,"Deposito", cantidad);
                        System.out.println("\nDepósito realizado con éxito. Su nuevo saldo es: $" + usuarioSaldo);//joption5
                        // JOptionPane.showMessageDialog(null, "Dep\u00F3sito realizado con \u00E9xito. Su nuevo saldo es: " + usuarioSaldo+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("\nError al realizar el depósito.");
                        // JOptionPane.showMessageDialog(null, "Error al realizar el dep\u00F3sito.", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                }
        }
        return false;
    }

    public boolean saldoSuficiente(String cantidadStr) {
        double cantidad=0.0;
        System.out.println("Esta entrando a retiro TT");
        if(cantidadStr.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
            textoE="Debe ingresar un monto.";
            tituloE="Error";
            return false;
        }else{
            try {
                cantidad = Double.parseDouble(cantidadStr);
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(null, "Monto no num\u00E9rico", "Error", JOptionPane.ERROR_MESSAGE);
                textoE="Monto no num\\u00E9rico";
                tituloE="Error";
                return false;
            }
            if (cantidad <= 0) {
                // JOptionPane.showMessageDialog(null, "Cantidad no v\u00E1lida.", "Error", JOptionPane.ERROR_MESSAGE);
                textoE="Cantidad no v\\u00E1lida.";
                tituloE="Error";
                return false;
            }else if(cantidad>usuarioSaldo){
            System.out.println("Saldo insuficiente.");
                textoE="Saldo insuficiente";
                tituloE="Error";
                return false;
            // JOptionPane.showMessageDialog(null, "Saldo insuficiente.", "Error", JOptionPane.WARNING_MESSAGE);
            
        }else{
            return true;
        }
    }
    }
    
    public boolean realizarRetiro(String cantidadStr){
        double cantidad = Double.parseDouble(cantidadStr);
        try {
            System.out.println("antes TT"+(usuarioSaldo-cantidad));
                        usuarioDao.actualizarSaldo(usuarioSaldo-cantidad, usuarioId); 
                        System.out.println("despues"+(usuarioSaldo-cantidad));
                        System.out.println(getSaldo()+"bl");
                        historicoDao.registrarOperacion(usuarioId, "Retiro", -cantidad); 
                        System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + usuarioSaldo);
                        // JOptionPane.showMessageDialog(null, "Retiro realizado con \u00E9xito. Su nuevo saldo es: " + usuarioSaldo+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } catch (Exception ex) {
                        System.out.println("Error al realizar el retiro.");
                        ex.printStackTrace();
                        return false;
                    }
    }

    public boolean validarPIN(String pinStr){
        int pin=Integer.parseInt(pinStr);
        if(pin==getPin()){
            return true;
        }else{
            return false;
        }
    }

    public boolean validarCambioPIN(String newPINStr, String confirmPINStr) throws SQLException {
        if(newPINStr.isEmpty() && confirmPINStr.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Ambos campos se encuentran vacios", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            // exception.ExceptionMessage(0, "Ambos campos se encuentran vacios", "Campos vacios", 2);
            return false;
        }else if(newPINStr.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(confirmPINStr.isEmpty()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar el PIN", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(newPINStr.isBlank() && confirmPINStr.isBlank()){
            // JOptionPane.showMessageDialog(null, "Ambos campos se encuentran vacios", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            // exception.ExceptionMessage(0, "Ambos campos se encuentran vacios", "Campos vacios", 2);
            return false;
        }else if(newPINStr.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(confirmPINStr.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar el PIN", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return false;
        }else{
            int newPIN = Integer.parseInt(newPINStr);
            int confirmPIN = Integer.parseInt(confirmPINStr);
            if (newPIN == confirmPIN) {
                if(usuarioDao.getUsuarioPIN(usuarioId)==newPIN){
                    return false;
                // JOptionPane.showMessageDialog(null, "Esta ingresando el mismo PIN", "Observaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    
                    return true;
                }
            
            } else {
                System.out.println("Los PINs no coinciden.");
                return false;
                // JOptionPane.showMessageDialog(null, "Los PINs no coinciden.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public boolean cambiarPIN(String newPINStr){
        int newPIN = Integer.parseInt(newPINStr);
        try {
                            // System.out.println("de "+getPinActual()+" a "+newPIN);
                            usuarioDao.actualizarPIN(newPIN, usuarioId);
                            historicoDao.registrarOperacion(usuarioId, "Cambio de PIN", 0.0);
                            // JOptionPane.showMessageDialog(null, "PIN actualizado con \u00E9xito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            return true;
                        } catch (Exception ex) {
                            System.out.println("Error al actualizar el PIN.");
                            // JOptionPane.showMessageDialog(null, "Error al actualizar el PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            return false;
                        }
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
