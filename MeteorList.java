


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MeteorList {

    ArrayList<Meteor> meteors;
    public ArrayList<Meteor> list() {return this.meteors;}
    int x, y;
    Random random = new Random();
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getY() {
        return this.y;
    }

    public MeteorList(int x, int y) {
        meteors = new ArrayList<>();

        this.x = x;
        this.y = y;
    }
    
    public void set() {
        
        int xx = 0;
        int yy = 0;
        int xx_accel = 0;
        int yy_accel = 0;
        int xx2_accel = 0;
        int yy2_accel = 0;
        
        xx = random.nextInt(1300);
        yy = random.nextInt(600);
        
        xx_accel = 2 + random.nextInt(7);
        yy_accel = 2 + random.nextInt(7);
        
        xx2_accel = -2 + -1*random.nextInt(7);
        yy2_accel = -2 + -1*random.nextInt(7);

        Meteor meteor = new Meteor();
        meteor.setX(xx);
        meteor.setY(yy);
        meteor.x_accel = xx_accel + xx2_accel;
        meteor.y_accel = yy_accel + yy2_accel;
        
        this.meteors.add(meteor);
    }

    public void moveAlongTheMeteors() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(Iterator<Meteor> it=meteors.iterator(); it.hasNext();) {
                    Meteor meteor = it.next();
                    meteor.x += meteor.x_accel;
                    meteor.y += meteor.y_accel;
                }
            }
        });
        t.start();
    }
    
    public void drawMeteors(Designer gui) {
        gui.drawMeteors(meteors);
    }
}
