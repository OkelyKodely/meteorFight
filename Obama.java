


import java.util.Random;

public class Obama {

    int x, y;
    String direction = "up";
    public int x_accel, y_accel;
    
    public Obama() {
        Random random = new Random();
        x_accel = random.nextInt(12) - random.nextInt(12);
        y_accel = random.nextInt(12) - random.nextInt(12);
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
