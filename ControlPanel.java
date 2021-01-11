
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
    
    final static int LOCATIONx = 0;
    final static int LOCATIONy = 601;
    
    final static int ControlPanel_WIDTH = 1301;
    final static int ControlPanel_HEIGHT = 203;
    
    public double score = 0.0d;
    
    JLabel scoreLabel = null;
    
    public ControlPanel() {
        super();
    }
    
    public void plusScore() {
        score += 0.5d;
        scoreLabel.setText("Score: " + score);
    }
   
    public void setMainPanel(JPanel mainPne) {
        
        if(mainPne.getWidth() > 1300 && mainPne.getHeight() == 800) {
        
            mainPne.add(this);
            this.setLayout(null);
            mainPne.setLayout(null);
        } else {
            System.out.println("Another 1 jpanel was set.  Fix bug~.");
        }
    }

    public void initControlPanel() {
        startMeUp();
        wakeMeUp();
    }
    
    private void startMeUp() {
        this.setBounds(ControlPanel.LOCATIONx, ControlPanel.LOCATIONy,
                       ControlPanel.ControlPanel_WIDTH, ControlPanel.ControlPanel_HEIGHT);
        
        this.setBackground(Color.ORANGE);
    }

    private void wakeMeUp() {
        scoreLabel = new JLabel();
        scoreLabel.setText("Score: 0.0");
        this.add(scoreLabel);
        
        
        scoreLabel.setBounds(0, 10, 200, 34);
    }
}