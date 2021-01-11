


import java.util.Random;

public class Gun {

    int x, y;
    String direction = "up";
    public int x_accel, y_accel;
    
    public Gun() {

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
