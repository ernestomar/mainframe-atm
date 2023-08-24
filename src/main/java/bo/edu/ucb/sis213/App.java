package bo.edu.ucb.sis213;
import bo.edu.ucb.sis213.bl.AtmBL;
import bo.edu.ucb.sis213.view.LoginView;

public class App {
    public static void main(String[] args) {
        AtmBL bl=new AtmBL();
        new LoginView(bl);
        // loginView.setVisible(true);
    }
}
