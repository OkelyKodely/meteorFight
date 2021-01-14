
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {

    public static void main(String args[]) {
        try {
            EventQueue.invokeAndWait(new Runnable() {
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