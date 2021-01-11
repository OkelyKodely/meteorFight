


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class StarList {

    ArrayList<Star> stars;
    public ArrayList<Star> list() {return this.stars;}
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

    public StarList(int x, int y) {
        stars = new ArrayList<>();

        this.x = x;
        this.y = y;
    }
    
    public void set() {
        
        int xx = 0;
        int yy = 0;
        
        xx = random.nextInt(1300);
        yy = random.nextInt(600);

        Star star = new Star();
        star.setX(xx);
        star.setY(yy);
        
        this.stars.add(star);
    }

    public void drawStars(Designer gui) {
        gui.drawStars(stars);
    }
}
