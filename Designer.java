
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Designer {

    Graphics g = null;
    
    UserInterface theui = null;
    
    ControlPanel cp = null;
    
    Random ran = new Random();
    
    public Designer(UserInterface ui) {
        this.theui = ui;

        this.cp = ui.getCp();
        
        this.cp.setMainPanel(ui.getRootPanel());
        this.cp.initControlPanel();
    }
    
    public void setGC(Graphics graph) {
        this.g = graph;
    }

    public void drawMeteors(ArrayList<Meteor> meteors) {
        if(this.g != null) {
            for(Iterator<Meteor> it=meteors.iterator(); it.hasNext();) {
                this.g.setColor(Color.lightGray);
                try {
                    Meteor meteor = it.next();
                    this.g.drawOval(meteor.getX(), meteor.getY(), 30, 30);
                } catch(Exception e) {}
            }
        }
    }

    public void drawStars(ArrayList<Star> stars) {
        if(this.g != null) {
            for(Iterator<Star> it=stars.iterator(); it.hasNext();) {
                int x = ran.nextInt(11);
                switch(x) {
                    case 0:
                        this.g.setColor(Color.CYAN);
                        break;
                    case 1:
                        this.g.setColor(Color.DARK_GRAY);
                        break;
                    case 2:
                        this.g.setColor(Color.GRAY);
                        break;
                    case 3:
                        this.g.setColor(Color.GREEN);
                        break;
                    case 4:
                        this.g.setColor(Color.LIGHT_GRAY);
                        break;
                    case 5:
                        this.g.setColor(Color.MAGENTA);
                        break;
                    case 6:
                        this.g.setColor(Color.ORANGE);
                        break;
                    case 7:
                        this.g.setColor(Color.PINK);
                        break;
                    case 8:
                        this.g.setColor(Color.RED);
                        break;
                    case 9:
                        this.g.setColor(Color.WHITE);
                        break;
                    case 10:
                        this.g.setColor(Color.YELLOW);
                        break;
                }
                try {
                    Star star = it.next();
                    this.g.drawOval(star.getX(), star.getY(), 5, 5);
                } catch(Exception e) {}
            }
        }
    }

    public void drawPlayerShots(ArrayList<Gun> guns) {
        if(this.g != null) {
            for(Iterator<Gun> it=guns.iterator(); it.hasNext();) {
                this.g.setColor(Color.cyan);
                try {
                    Gun gun = it.next();
                    this.g.fillRect(gun.getX(), gun.getY() + 15, 10, 10);
                } catch(Exception e) {}
            }
        }
    }
}