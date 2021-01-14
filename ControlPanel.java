
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
    
    final static int LOCATIONx = 0;
    final static int LOCATIONy = 750;
    
    final static int ControlPanel_WIDTH = 1300;
    final static int ControlPanel_HEIGHT = 100;
    
    public double score = 0.0d;
    
    JLabel scoreLabel = null;
    
    public ControlPanel() {
        super();
    }
    
    public void plusScore() {
        score += 0.5d;
        scoreLabel.setText("Delicious score: " + score);
    }
   
    public void setMainPanel(JPanel mainPne) {
        
        if(mainPne.getWidth() > 1200 && mainPne.getHeight() >= 700) {
        
            mainPne.setLayout(null);
            this.setLayout(null);
            mainPne.add(this);
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
        
        this.setBackground(Color.white );
    }

    private void wakeMeUp() {
        scoreLabel = new JLabel();
        scoreLabel.setText("Delicious score: 0.0");
        this.add(scoreLabel);
        
        
        scoreLabel.setBounds(0, 10, 200, 34);
    }
}