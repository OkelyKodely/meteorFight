
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Designer {
    
    Graphics2D g = null;
    
    UserInterface theui = null;
    
    ControlPanel cp = null;
    
    Random ran = new Random();
    
    ImageIcon ii = new ImageIcon("obama.png");

    Image obamaImg = ii.getImage();
    
    public Designer(UserInterface ui) {
        this.theui = ui;

        this.cp = ui.getCp();
        
        this.cp.setMainPanel(ui.other);
        this.cp.initControlPanel();
    }
    
    public void setGC(Graphics graph) {
        this.g = (Graphics2D)graph;
    }

    public void drawMeteors(ArrayList<Meteor> meteors) {
        if(this.g != null) {
            Thread t = new Thread() {
                public void run() {
                    for(Iterator<Meteor> it=meteors.iterator(); it.hasNext();) {
                        try {
                            Meteor meteor = it.next();
                            Thread t9 = new Thread(new Runnable() {
                                public void run() {
                                    if(meteor.imageSrc.equals("") || 1== 1) {
                                        if(meteor.kind.equals("oval")) {
                                            g.setColor(Color.WHITE);
                                            g.fillOval(meteor.getX(), meteor.getY(), meteor.width, meteor.height);
                                        } else {
                                            if(meteor.issplit) {
                                                g.setColor(Color.WHITE);
                                                g.fillRect(meteor.getX(), meteor.getY(), meteor.width, meteor.height);
                                            } else {
                                                g.setColor(Color.WHITE);
                                                g.fillRect(meteor.getX(), meteor.getY(), meteor.width, meteor.height);
                                            }
                                        }
                                        g.setColor(Color.red);
                                        g.drawLine(meteor.getX()+4, meteor.getY()+meteor.height/2, meteor.getX()+meteor.width-4, meteor.getY()+meteor.height/2);
                                        g.drawLine(meteor.getX()+meteor.width/2, meteor.getY()+4, meteor.getX()+meteor.width/2, meteor.getY()+meteor.height-4);
                                    }
                                    try {
                                        g.setColor(Color.white);
                                        g.drawString(meteor.imageSrc, meteor.getX(), meteor.getY());
                                    } catch(Exception ex ) {System.out.println(ex);}
                                    meteor.width += 1;
                                    meteor.height += 1;
                                }
                            });
                            t9.start();
                        } catch(Exception e) {}
                    }
                }
            };
            t.start();
        }
    }

    public void drawStars(ArrayList<Star> stars) {
        if(this.g != null) {
            for(Iterator<Star> it=stars.iterator(); it.hasNext();) {
                int x = ran.nextInt(11);
                switch(x) {
                    case 0:
                        ((Graphics2D)this.g).setColor(Color.CYAN);
                        break;
                    case 1:
                        ((Graphics2D)this.g).setColor(Color.DARK_GRAY);
                        break;
                    case 2:
                        ((Graphics2D)this.g).setColor(Color.GRAY);
                        break;
                    case 3:
                        ((Graphics2D)this.g).setColor(Color.GREEN);
                        break;
                    case 4:
                        ((Graphics2D)this.g).setColor(Color.LIGHT_GRAY);
                        break;
                    case 5:
                        ((Graphics2D)this.g).setColor(Color.MAGENTA);
                        break;
                    case 6:
                        ((Graphics2D)this.g).setColor(Color.ORANGE);
                        break;
                    case 7:
                        ((Graphics2D)this.g).setColor(Color.PINK);
                        break;
                    case 8:
                        ((Graphics2D)this.g).setColor(Color.RED);
                        break;
                    case 9:
                        ((Graphics2D)this.g).setColor(Color.WHITE);
                        break;
                    case 10:
                        ((Graphics2D)this.g).setColor(Color.YELLOW);
                        break;
                }
                try {
                    Star star = it.next();
                    ((Graphics2D)this.g).fillOval(star.getX(), star.getY(), 5, 5);
                } catch(Exception e) {}
            }
        }
    }

    public void drawPlayerShots(ArrayList<Gun> guns) {
        if(this.g != null) {
            for(Iterator<Gun> it=guns.iterator(); it.hasNext();) {
                ((Graphics2D)this.g).setColor(Color.white);
                try {
                    Gun gun = it.next();
                    ((Graphics2D)this.g).fillRect(gun.getX(), gun.getY() + 15, 10, 10);
                } catch(Exception e) {}
            }
        }
    }

    public void drawObamasPlease(ArrayList<Obama> obamas) {
        if(this.g != null) {
            for(Iterator<Obama> it=obamas.iterator(); it.hasNext();) {
                ((Graphics2D)this.g).setColor(Color.white);
                try {
                    Obama obama = it.next();
                    ((Graphics2D)this.g).drawImage(obamaImg, obama.x, obama.y, 100, 100, null);
                } catch(Exception e) {}
            }
        }
    }
}