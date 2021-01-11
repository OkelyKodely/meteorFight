


public class Meteor {

    int x, y;
    
    int x_accel, y_accel;
    
    public Meteor() {
        this.x = 0;
        this.y = 0;
        
        this.x_accel = 0;
        this.y_accel = 0;
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
