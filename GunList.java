


import java.util.ArrayList;
import java.util.Iterator;

public class GunList {

    ArrayList<Gun> guns;
    public ArrayList<Gun> list() {return this.guns;}
    int x, y;
    
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

    public GunList(int x, int y) {
        guns = new ArrayList<>();

        this.x = x;
        this.y = y;
    }
    
    public void shoot(UserInterface.Player player) {
        Gun gun = new Gun();
        gun.setX(x);
        gun.setY(y);
        gun.direction = player.direction;
        gun.x_accel = player.x_accel;
        gun.y_accel = player.y_accel;
        
        this.guns.add(gun);
    }

    public void moveAlongTheShots(UserInterface.Player player) {
        for(Iterator<Gun> it=guns.iterator(); it.hasNext();) {
            Gun gun = it.next();
            if(gun.direction.equals("up")) {
                gun.setY(gun.getY() - 40 + 2*gun.y_accel);
            } else if(gun.direction.equals("left")) {
                gun.setX(gun.getX() - 40 + 2*gun.x_accel);
            } else if(gun.direction.equals("down")) {
                gun.setY(gun.getY() + 40 +2* gun.y_accel);
            } else if(gun.direction.equals("right")) {
                gun.setX(gun.getX() + 40 + 2 *gun.x_accel);
            }
        }
    }
    
    public void drawShots(Designer gui) {
        gui.drawPlayerShots(guns);
    }
}
