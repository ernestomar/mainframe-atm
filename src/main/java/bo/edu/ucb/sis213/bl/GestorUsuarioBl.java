package bo.edu.ucb.sis213.bl;
import bo.edu.ucb.sis213.dao.UsuarioDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorUsuarioBl {
    private Connection connection;
    private UsuarioBl usuarioBl;
    private UsuarioDao usuarioDao;

    public GestorUsuarioBl(Connection connection) {
        this.connection = connection;
    }

    public boolean validarPIN_UsuarioBl(int pinIngresado) {
        return usuarioDao.validarPIN_UsuarioDao(usuarioBl,pinIngresado);
    }
    
    public double consultarSaldo_UsuarioBl() {
        double saldo=usuarioBl.getSaldo();
        System.out.println("Su saldo actual es: $" + saldo);
        return saldo;
    }

    public boolean realizarDeposito_UsuarioBl(double cantidad) {
        return usuarioDao.realizarDeposito_UsuarioDao(usuarioBl,cantidad);
    }
    
    public boolean realizarRetiro_UsuarioBl(double cantidad) {
        return usuarioDao.realizarRetiro_UsuarioDao(usuarioBl,cantidad);
    }

    public boolean cambiarPIN_UsuarioBl(int pin) {
        return usuarioDao.cambiarPIN_UsuarioDao(usuarioBl,pin);
    }

}
