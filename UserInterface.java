
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInterface implements KeyListener {

    Player ply = new Player();

    JPanel p = new JPanel();
    
    JFrame frame = null;
    
    GunList guns = null;
    
    MeteorList meteors = null;
    
    StarList stars = null;
    
    Saucer saucer1 = null;
    
    Saucer saucer2 = null;
    
    JLabel livesLabel = new JLabel("Lives: 4");
    int lives = 40000;
    int countVer = 0;
    
    int countVer2 = 0;
    double sweich = 0;
    double sweich2 = 0;
    
    class Trail {
        int x, y;
        int seconds = 10;
        int milliseconds = 1000;
        boolean done = false;
        public Trail(Meteor meteor) {
            x = meteor.x;
            y = meteor.y;
            Thread thread = new Thread() {
                public void run() {
                    Thread t1 = new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(seconds * milliseconds);
                            } catch(Exception e) {}
                            done = true;
                        }
                    };
                    t1.start();
                    while(true) {
                        d.g.setColor(Color.black);
                        d.g.drawOval(x, y, 5, 5);
                        if(ply.x > x && ply.x < x + 5 &&
                                ply.y > y && ply.y < y + 5) {
                            lives --;
                            livesLabel.setText("Lives: " + lives);
                        }
                        if(done)
                            return;
                        try {
                            Thread.sleep(230);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
    }
    
    private void drawTrailers() {
        Thread thred = new Thread() {
            public void run() {
                while(true) {
                    for(Iterator<Meteor> it = meteors.list().iterator(); it.hasNext();) {
                        new Trail(it.next());
                    }
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }
            }
        };
        thred.start();
    }
    
    class Player {
        int x, y;   int x_accel = 0;  int y_accel = 0;
        String direction = "up";
        public void turn(Graphics grap) {
            if(direction.equals("right")) {
                direction = "up";
                grap.setColor(Color.WHITE);
                grap.drawRect(x, y - 20, 20, 40);
            } else if(direction.equals("up")) {
                direction = "left";
                grap.setColor(Color.WHITE);
                grap.drawRect(x - 20, y + 20, 40, 20);
            } else if(direction.equals("left")) {
                direction = "down";
            } else if(direction.equals("down")) {
                direction = "right";
            }
        }
    }
    
    class Saucer {
        int x, y;
    }
    
    final static int LOCATION_X = 0;
    final static int LOCATION_Y = 0;
    
    final static int UI_WIDTH = 1312;
    final static int UI_HEIGHT = 800;
    
    Designer d = null;
    
    ControlPanel cp = new ControlPanel();
    
    public UserInterface() {

        livesLabel.setBounds(10, 50, 200, 40);
        
        setupUserInterface();
        d = new Designer(this);
        setGUI(d);
        
        d.cp.add(livesLabel);
        
        beginGame();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            guns.shoot(ply);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            ply.x_accel -= 2;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ply.x_accel += 2;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            ply.y_accel += 2;
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            ply.y_accel -= 2;
        }
        
        if(e.getKeyCode() == KeyEvent.VK_S) {
            ply.turn(d.g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private void pauseIndefinitely() {
        try {
            Thread.sleep(100);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawShip() {
        if(ply.direction.equals("right")) {
            d.g.setColor(Color.red);
            d.g.drawRect(ply.x, ply.y, 40, 20);
            d.g.drawLine(ply.x + 40 + 12, ply.y + 10, ply.x + 40 - 12, ply.y - 10);
            d.g.drawLine(ply.x + 40 + 12, ply.y + 10, ply.x + 40 - 12, ply.y + 10+20);
        } else if(ply.direction.equals("up")) {
            d.g.setColor(Color.red);
            d.g.drawRect(ply.x, ply.y - 20, 20, 40);
        } else if(ply.direction.equals("left")) {
            d.g.setColor(Color.red);
            d.g.drawRect(ply.x - 20, ply.y + 20, 40, 20);
            d.g.drawLine(ply.x + 0 - 12, ply.y + 10, ply.x + 0 + 12, ply.y - 10);
            d.g.drawLine(ply.x + 0 - 12, ply.y + 10, ply.x + 0 + 12, ply.y + 10+20);
        } else if(ply.direction.equals("down")) {
            d.g.setColor(Color.red);
            d.g.drawRect(ply.x, ply.y, 20, 40);
        }
    }
    
    private void drawSaucer(Saucer saucer) {
        d.g.setColor(Color.ORANGE );
        d.g.drawRect(saucer.x, saucer.y, 100, 20);
        d.g.drawRect(saucer.x+30, saucer.y-20, 40, 20);
    }
    
    private void clearScreen() {
        d.g = p.getGraphics();
        d.g.setColor(Color .black);
        d.g.fillRect(0, 0, 1312, 600);
    }

    private void beginGame() {
        Thread thred = new Thread() {
            public void run() {
                ply.x = 100;
                ply.y = 400;
                stars = new StarList(0, 0);
                saucer1 = new Saucer();
                saucer1.x = 600;
                saucer1.y = 300;
                saucer2 = new Saucer();
                saucer2.x = 800;
                saucer2.y = 450;
                Random ran = new Random();
                Thread t9 = new Thread() {
                    public void run() {
                        while(1 == 1) {

                            try {
                                Thread.sleep(100);
                            } catch(Exception e) {}
                            
                            int v = ran.nextInt(3);

                            if(1 ==1 ) {
                                if(saucer1.x > 240 && !(sweich == 1)) {
                                    if(countVer < 20) {
                                saucer1.x -= 5;

                                        countVer = countVer+ 1;
                                        if(v == 0) {
                                            saucer1.y -= 5;
                                        } else if(v == 1) {
                                            saucer1.y -= 0;
                                        } else if(v == 2) {
                                            saucer1.y += 5;
                                        }
                                    } else if(countVer >= 20 && countVer <= 30) {
                                saucer1.x -= 3;
                                        countVer = countVer+ 1;
                                        if(v == 0) {
                                            saucer1.y -= 5;
                                        } else if(v == 1) {
                                            saucer1.y -= 0;
                                        } else if(v == 2) {
                                            saucer1.y += 5;
                                        }
                                    } else {
                                        countVer = 0;
                                    }
                                } else {
                                    if(saucer1.x > 230 && saucer1.x < 240) {
                                        sweich = 1;
                                    }
                                    if(saucer1.x < 1230 && saucer1.x > 1220) {
                                        sweich = 0;
                                    }
                                    if(countVer < 20) {
                                saucer1.x += 5;

                                        countVer = countVer+ 1;
                                        if(v == 0) {
                                            saucer1.y -= 5;
                                        } else if(v == 1) {
                                            saucer1.y -= 0;
                                        } else if(v == 2) {
                                            saucer1.y += 5;
                                        }
                                    } else if(countVer >= 20 && countVer <= 30) {
                                saucer1.x += 3;
                                        countVer = countVer+ 1;
                                        if(v == 0) {
                                            saucer1.y -= 5;
                                        } else if(v == 1) {
                                            saucer1.y -= 0;
                                        } else if(v == 2) {
                                            saucer1.y += 5;
                                        }
                                    } else {
                                        countVer = 0;
                                    }
                                }
                            }
                            if(1 ==1 ) {
                                if(saucer2.x > 240 && !(sweich2 == 1)) {
                                    if(countVer2 < 20) {
                                saucer2.x -= 5;

                                        countVer2 = countVer2+ 1;
                                        if(v == 0) {
                                            saucer2.y -= 5;
                                        } else if(v == 1) {
                                            saucer2.y -= 0;
                                        } else if(v == 2) {
                                            saucer2.y += 5;
                                        }
                                    } else if(countVer2 >= 20 && countVer2 <= 30) {
                                saucer2.x -= 3;
                                        countVer2 = countVer2+ 1;
                                        if(v == 0) {
                                            saucer2.y -= 5;
                                        } else if(v == 1) {
                                            saucer2.y -= 0;
                                        } else if(v == 2) {
                                            saucer2.y += 5;
                                        }
                                    } else {
                                        countVer2 = 0;
                                    }
                                } else {
                                    if(saucer2.x > 230 && saucer2.x < 240) {
                                        sweich2 = 1;
                                    }
                                    if(saucer2.x < 1230 && saucer2.x > 1220) {
                                        sweich2 = 0;
                                    }
                                    if(countVer2 < 20) {
                                saucer2.x += 5;

                                        countVer2 = countVer2+ 1;
                                        if(v == 0) {
                                            saucer2.y -= 5;
                                        } else if(v == 1) {
                                            saucer2.y -= 0;
                                        } else if(v == 2) {
                                            saucer2.y += 5;
                                        }
                                    } else if(countVer2 >= 20 && countVer2 <= 30) {
                                saucer2.x += 3;
                                        countVer2 = countVer2+ 1;
                                        if(v == 0) {
                                            saucer2.y -= 5;
                                        } else if(v == 1) {
                                            saucer2.y -= 0;
                                        } else if(v == 2) {
                                            saucer2.y += 5;
                                        }
                                    } else {
                                        countVer2 = 0;
                                    }
                                }
                            }
                        }
                    }
                };
                t9.start();
                for(int i=0; i<1000; i++) {
                    stars.set();
                }
                guns = new GunList(ply.x, ply.y);
                meteors = new MeteorList(0, 0);
                Thread t = new Thread() {
                    public void run() {
                        while(true) {
                            for(int i=0; i<30; i++) {
                                meteors.set();
                            }
                            try {
                                Thread.sleep(14000);
                            } catch(Exception e) {}
                        }
                    }
                };
                t.start();
                drawTrailers();
                while(true) {
                    clearScreen();
                    
                    drawShip();
                    
                    drawSaucer(saucer1);
                    
                    drawSaucer(saucer2);
                    
                    guns.moveAlongTheShots(ply);
                  
                    guns.drawShots(d);
                    
                    meteors.moveAlongTheMeteors();
                    
                    meteors.drawMeteors(d);
                    
                    stars.drawStars(d);
                    
                    for(int i = 0; i<guns.list().size(); i++) {
                        if(guns.list().get(i).x < 0 || guns.list().get(i).x > 1300 || guns.list().get(i).y < 0 || guns.list().get(i).y > 600)
                            guns.list().remove(guns.list().get(i));
                        for(int j=0; j<meteors.list().size(); j++) {
                            try {
                                Gun gun = guns.list().get(i);
                                Meteor meteor = meteors.list().get(j);
                                if(gun.x >= meteor.x && gun.x <= meteor.x + 30 && gun.y >= meteor.y && gun.y <= meteor.y + 30) {
                                    meteors.list().remove(meteor);
                                    explode(meteor);
                                    guns.list().remove(gun);
                                    d.cp.plusScore();
                                }
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    
                    boolean isalive = true;
                    for(int j=0; j<meteors.list().size() && isalive; j++) {
                        try {
                            Meteor meteor = meteors.list().get(j);
                            if(meteor.x < 0 || meteor.x > 1300 || meteor.y < 0 || meteor.y > 600)
                                meteors.list().remove(meteor);
                            if(ply.x >= meteor.x && ply.x <= meteor.x + 60 && ply.y >= meteor.y && ply.y <= meteor.y + 60) {
                                meteors.list().remove(meteor);
                                explode(meteor);
                                guns.list().clear();
                                meteors.list().clear();
                                for(int i=0; i<20; i++) {
                                    meteors.set();
                                }
                                isalive = false;
                                lives --;
                            }
                        } catch(Exception e) {
                        }
                    }
                    
                    livesLabel.setText("Lives: " + lives);
                    
                    if(lives == 0)
                        System.exit(0);

                    pauseIndefinitely();
                    
                    ply.x += ply.x_accel;
                    ply.y += ply.y_accel;
                    
                    guns.x = ply.x;
                    guns.y = ply.y;
                }
            }
        };
        thred.start();
    }
    
    public ControlPanel getCp() {
        return this.cp;
    }
    
    public JPanel getRootPanel() {
        
        return this.p;
    }
    
    public void explode(Meteor meteor) {
        Thread tt = new Thread() {
            public void run() {
                int x1, y1, x2, y2, x3, y3, x4, y4;
                x1 = meteor.x;
                y1 = meteor.y;
                x4 = x3 = x2 = x1;
                y4 = y3 = y2 = y1;
                int counter = 0;
                while(true) {
                    try {
                        counter ++;
                        if(counter == 80)
                            return;
                        d.g.setColor(Color.RED);
                        d.g.fillOval(x1, y1, 70, 70);
                        d.g.setColor(Color.RED);
                        d.g.fillOval(x2, y2, 70, 70);
                        d.g.setColor(Color.RED);
                        d.g.fillOval(x3, y3, 70, 70);
                        d.g.setColor(Color.RED);
                        d.g.fillOval(x4, y4, 70, 70);
                        Thread.sleep(5);
                        d.g.setColor(Color.black);
                        d.g.fillOval(x1, y1, 70, 70);
                        d.g.setColor(Color.black);
                        d.g.fillOval(x2, y2, 70, 70);
                        d.g.setColor(Color.black);
                        d.g.fillOval(x3, y3, 70, 70);
                        d.g.setColor(Color.black);
                        d.g.fillOval(x4, y4, 70, 70);
                        x1--; y1--;
                        x2++; y2--;
                        x3--; y3++;
                        x4++; y4++;
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        tt.start();
    }

    public void setupUserInterface() {
        frame = new JFrame("Meteor Fight");
        frame.setLayout(null);
        
        frame.addKeyListener(this);

        frame.setBounds(UserInterface.LOCATION_X, UserInterface.LOCATION_Y,
                        UserInterface.UI_WIDTH, UserInterface.UI_HEIGHT);
        p.setBounds(frame.getBounds());
        
        frame.add(p);
        
        setDefaults();
        
        frame.setVisible(true);
    }
    
    private void setGUI(Designer d) {

        Graphics graph = p.getGraphics();
        d.setGC(graph);
    }
    
    private void setDefaults() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}