
import java.util.Random;




public class Meteor {

    int x, y;
    
    int x_accel, y_accel;
    
    public int width = 30;
    
    public int height = 30;
    
    Random ran = new Random();
    
    String kind = null;
    
    public String imageSrc = "";
    public String imageSrc1 = "";
    
    public boolean issplit = false;
    
    public Meteor() {
        
        int x = ran.nextInt(2);
        if(x == 0) {
            kind = "oval";
        } else {
            kind = "square";
        }
        
        this.x = 0;
        this.y = 0;
        
        this.x_accel = 0;
        this.y_accel = 0;
        
//        Thread t = new Thread() {
//            public void run() {
//                while(true) {
//                    try {
//                        Thread.sleep(1000);
//                        if(width > 130) width = 29;
//                        if(height > 130) height = 29;
//                        if(x < 0 || x > 1300 || y < 0 || y > 600) {
//                            return;
//                        }
//                    } catch(Exception e) {e.printStackTrace();}
//                }
//            }
//        };
//        t.start();
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
