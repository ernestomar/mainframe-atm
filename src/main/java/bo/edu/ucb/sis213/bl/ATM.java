package bo.edu.ucb.sis213.bl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bo.edu.ucb.sis213.dao.HistoricoDAO;
import bo.edu.ucb.sis213.dao.UsuariosDAO;

public class ATM {
    private int usuarioId;
    public double saldo;
    private int pinActual;
    private Connection connection;
    private HistoricoDAO historicoDAO;
    private UsuariosDAO usuariosDAO;

    public ATM(Connection connection) {
        this.connection = connection;
        this.historicoDAO = new HistoricoDAO(connection);
        this.usuariosDAO = new UsuariosDAO(connection);
    }

    public double getSaldo() {
        return saldo;
    }

    public int obtenerPinDesdeDB() throws SQLException {
        return usuariosDAO.obtenerPinDesdeDBDAO(usuarioId);
    }

    public boolean validarPIN(String alias, int pin) {
        return usuariosDAO.validarPINDAO(alias, pin);

    }

    public void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + saldo);
    }

    public void cambiarPIN(int pinIngresado, int nuevoPin, int confirmacionPin) throws SQLException {

        try {
            usuariosDAO.cambiarPINDAO(pinIngresado, nuevoPin, confirmacionPin);
            this.pinActual = nuevoPin;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void realizarDeposito(double cantidad) throws SQLException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad no válida.");
        }
        // realizar el deposito
        usuariosDAO.realizarDepositoDAO(cantidad);
        // Registra la operación en el histórico
        registrarOperacionHistorico("Depósito", cantidad);

        saldo += cantidad;
    }

    public void realizarRetiro(double cantidad) throws SQLException {
        // revisa si el retiro se puede realizar
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad no válida.");
        } else if (cantidad > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        // realiza retiro
        usuariosDAO.realizarRetiroDAO(cantidad);
        // Registra la operación en el histórico
        registrarOperacionHistorico("Retiro", cantidad);
        saldo -= cantidad;
    }

    public void registrarOperacionHistorico(String tipoOperacion, double cantidad) throws SQLException {
        historicoDAO.registrarOperacionHistoricoDAO(usuarioId, tipoOperacion, cantidad);
    }

}
