


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObamaList {

    ArrayList<Obama> obamas;
    public ArrayList<Obama> list() {return this.obamas;}
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

        Obama meteor = new Obama();
        meteor.setX(xx);
        meteor.setY(yy);
        meteor.x_accel = xx_accel + xx2_accel;
        meteor.y_accel = yy_accel + yy2_accel;
        
        this.obamas.add(meteor);
    }

    public ObamaList(int x, int y) {
        this.obamas = new ArrayList<>();

        this.x = x;
        this.y = y;
        
        this.moveAlongTheMeatHeads();
    }
    
    public synchronized void moveAlongTheMeatHeads() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    for(Iterator<Obama> it=obamas.iterator(); it.hasNext();) {
                        Obama meteor = it.next();
                        meteor.x += meteor.x_accel;
                        meteor.y += meteor.y_accel;
                    }
                    try {
                        Thread.sleep(1000);
                        
                    } catch(Exception e) {}
                }
            }
        });
        t.start();
    }

    public void drawObamas(Designer gui) {
        gui.drawObamasPlease(obamas);
    }
}
