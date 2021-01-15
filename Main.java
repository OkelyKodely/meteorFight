
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String args[]) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    UserInterface UI = new UserInterface();
                    UI.setupUserInterface();
                    UI.frame.setExtendedState(UI.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                }
            });
        } catch(Exception e) { 
            
            e.printStackTrace();
        }
    }
}