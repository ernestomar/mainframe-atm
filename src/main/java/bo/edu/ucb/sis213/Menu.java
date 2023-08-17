package bo.edu.ucb.sis213;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Menu{
    public static void mostrarMenu(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Consultar saldo.");
            System.out.println("2. Realizar un depósito.");
            System.out.println("3. Realizar un retiro.");
            System.out.println("4. Cambiar contraseña.");
            System.out.println("5. Salir.");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    MovimientosBancarios.consultarSaldo();
                    break;
                case 2:
                    MovimientosBancarios.realizarDeposito(connection);
                    break;
                case 3:
                    MovimientosBancarios.realizarRetiro(connection);;
                    break;
                case 4:
                    CambiosCuenta.cambiarPassword(connection);
                    break;
                case 5:
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
