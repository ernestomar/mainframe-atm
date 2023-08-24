package bo.edu.ucb.sis213.util;

import javax.swing.JOptionPane;

public class Exceptions {
    public Exceptions(){

    }

    public void ExceptionMessage(int numero, String texto, String titulo, int tipo) {
        
            switch (numero) {
                // case 1:
                //     System.out.println("El número es uno.");
                //     break;
                case 2:
                    // System.out.println("El número es dos.");
                    JOptionPane.showMessageDialog(null, texto, titulo, tipoMensaje(tipo));
                    break;
                case 3:
                    System.out.println("El número es tres.");
                    break;
                case 4:
                    System.out.println("El número es cuatro.");
                    break;
                case 5:
                    System.out.println("El número es cinco.");
                    break;
                case 6:
                    System.out.println("El número es seis.");
                    break;
                case 7:
                    System.out.println("El número es siete.");
                    break;
                case 8:
                    System.out.println("El número es ocho.");
                    break;
                case 9:
                    System.out.println("El número es nueve.");
                    break;
                case 10:
                    System.out.println("El número es diez.");
                    break;
                default:
                    System.out.println("El número no está en el rango del 1 al 10.");
                    break;
            }
        }

    public static int tipoMensaje(int tipo) {
        switch (tipo) {
            case 1:
                return JOptionPane.ERROR_MESSAGE;
            case 2:
                return JOptionPane.WARNING_MESSAGE;
            case 3:
                return JOptionPane.INFORMATION_MESSAGE;
            default:
                return JOptionPane.PLAIN_MESSAGE;
        }
    }
    public void exceptionError(String texto){
        JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void exceptionWarnig(String texto){
        JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.WARNING_MESSAGE);
    }
    public void exceptionExito(String texto){
        JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
