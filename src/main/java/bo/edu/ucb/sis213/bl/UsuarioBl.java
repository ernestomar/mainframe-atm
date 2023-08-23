package bo.edu.ucb.sis213.bl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBl {
    private Connection connection;
    private int usuarioId;
    private double saldo;
    private int pinActual;
    private String nombreUser;

    public UsuarioBl(Connection connection, int usuarioId, double saldo, int pinActual, String nombreUser) {
        this.connection = connection;
        this.usuarioId = usuarioId;
        this.saldo = saldo;
        this.pinActual = pinActual;
        this.nombreUser = nombreUser;
    }
    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getPinActual() {
        return pinActual;
    }

    public void setPinActual(int pinActual) {
        this.pinActual = pinActual;
    }

}
