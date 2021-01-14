
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class UserInterface implements KeyListener {

    public class Bass {
        int x, y;
        int x_accel, y_accel;
        int lives = 250;
        public Bass() {
            Random random = new Random();
            x = random.nextInt(500);
            y = random.nextInt(500);
            Thread t = new Thread() {
                public void run() {
                    while(true) {
                        try {
                            x_accel = random.nextInt(10) - random.nextInt(10);
                            y_accel = random.nextInt(10) - random.nextInt(10);
                            x += x_accel;
                            y += y_accel;
                            if(x < 0 || x > 1312 || y < 0 || y > 600)
                            {
                                x = random.nextInt(500);
                                y = random.nextInt(500);
                            }
                            for(int i=0; i<guns.list().size(); i++) {
                                if(guns.list().get(i).x >= x && guns.list().get(i).x <= x + 100 &&
                                        guns.list().get(i).y >= y && guns.list().get(i).y <= y + 150) {
                                    lives--;
                                }
                            }
                            if(lives <= 0)
                                return;
                            drawMe();
                            Thread.sleep(40);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.start();
        }
        private void drawMe() {
            // Recover Graphics2D 
            Graphics2D g2 = (Graphics2D) d.g;

            // Draw the head
            Ellipse2D.Double head = new Ellipse2D.Double(x, y, 100, 150);
            g2.setColor(Color.GREEN);
            g2.fill(head);

            // Draw the eyes
            Line2D.Double eye1 = new Line2D.Double(x+20, y+20, x+20, y+40);
            g2.setColor(Color.RED);
            g2.draw(eye1);

            Line2D.Double eye2 = new Line2D.Double(x+80, y+20, x+60, y+40);
            g2.setColor(Color.RED);
            g2.draw(eye2);

            // Draw the mouth
            Rectangle mouth = new Rectangle(x+25, y+80, 45, 20);
            g2.setColor(Color.RED);
            g2.fill(mouth);
        }
    }

    int iei = 0;

    int kikl = 0;

    Random rand = new Random();
    
    JPanel eat = new JPanel();
    
    class Title {
        int x, y;
        ArrayList<Integer> x_;
        ArrayList<Integer> y_;
        ArrayList<Integer> x_accel;
        ArrayList<Integer> y_accel;
        String title = null;
        int length = -1;
        public Character get(int i) {
            if(title == null) {
                return null;
            } else {
                return title.charAt(i);
            }
        }
    }
    
    ArrayList<String> abcd = new ArrayList<>();
    ArrayList<String> abc = new ArrayList<>();

    int iii = 1;

    Clip sound = null;
               
    Player ply = new Player();

    JPanel p = new JPanel();
    
    public JPanel other = new JPanel();
    
    JFrame frame = null;
    
    GunList guns = null;
    
    MeteorList meteors = null;
    
    StarList stars = null;
    
    Saucer saucer1 = null;
    
    Saucer saucer2 = null;
    
    Film speech = new Film("Speech", "speech.wav");

    MusicStorage storage = new MusicStorage();

    JLabel livesLabel = new JLabel("Lives: 40000");
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
        if (1 == 1)
        {
           return;
        }
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
    
    int percentage = 0;
    
    final static int LOCATION_X = 0;
    final static int LOCATION_Y = 0;
    
    final static int UI_WIDTH = 1312;
    final static int UI_HEIGHT = 800;
    
    Designer d = null;
    
    ControlPanel cp = new ControlPanel();
    
    public UserInterface() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(5000);

                        other.remove(p);
                        p = null;
                        p = new JPanel();
                        p.setLayout(null);
                        p.setBounds(0, 0, 1312, 600);
                        other.add(p);
                    } catch(Exception e) {}
                }
            }
        });
        
        t.start();
        
        final JFrame jjj = new JFrame();
        JPanel ppp = new JPanel();
        jjj.setLayout(null);
        ppp.setLayout(null);
        jjj.setBounds(0, 0, 570, 250);
        ppp.setBounds(jjj.getBounds());
        jjj.add(ppp);
        Thread tt = new Thread() {
            public void run() {
        Thread tt = new Thread() {
            public void run() {
        jjj.setVisible(true);
        jjj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ppp.getGraphics().setColor(Color.WHITE);
            }
        };
        tt.start();
            }
        };
        tt.start();

        try {

        boolean doi = true;
        boolean doi2 = true;
            int id = 1;

            int pag = 1;

            int ii = 1;

            String tstring = "";

            Document doc = Jsoup.connect("https://www.allrecipes.com/recipes/").get();
            String toString = doc.toString();

            String ss = toString.substring(toString.indexOf("\"name\": [")+"\"name\": [ [".length(), toString.length());
            String ss2 = toString.substring(toString.indexOf("\"url\": [")+"\"url\": [ [".length(), toString.length());

            String ttstr = toString;

            String ttstr2 = toString;

            String imgCat1 = "";

            String imgCat2 = "";


            int kount = 0;

            boolean bbb = false;
            int wacky0 = 1;
            int count = 1;
            do
            {
                try {
                    String sub = ss;
                    String sub2 = ss2;
                    tstring = toString;

                    imgCat1 = ttstr.substring(ttstr.indexOf("<noscript>") + "<noscript>".length(), ttstr.length());
                    imgCat1 = imgCat1.substring(0, imgCat1.indexOf("</noscript>"));
                    imgCat1 = imgCat1.substring(imgCat1.indexOf("src=\"") + "src=\"".length(), imgCat1.length());
                    imgCat1 = imgCat1.substring(0, imgCat1.indexOf("\""));
                    ttstr = ttstr.substring(ttstr.indexOf("<noscript>") + "<noscript>".length(), ttstr.length());
                    ttstr = ttstr.substring(ttstr.indexOf("</noscript>") + "</noscript>".length(), ttstr.length());

                    toString = ss;

                        if(wacky0 != 1) {                
                            try {
                                ss = ss.substring(ss.indexOf("\",")+"\",".length(), ss.length());
                                ss2 = ss2.substring(ss2.indexOf("\",")+"\",".length(), ss2.length());
                            } catch(Exception eu) {
                            }
                        }

                        if(doi) {
                        wacky0++;
                        if(wacky0 > 30)
                        doi = false;

                        String a1aa = ss.substring(ss.indexOf("\"") + 1, ss.length());

                        String cat1 = a1aa.substring(0, a1aa.indexOf("\""));

                        if(cat1.equals("World Cuisine"))
                            doi = false;

                        String a1_aa = ss2.substring(ss2.indexOf("\"") + 1, ss2.length());

                        String url1 = a1_aa.substring(0, a1_aa.indexOf("\""));

                    Document doc1 = Jsoup.connect(url1).get();
                    String tooString = doc1.toString();

                    String ss_ = tooString.substring(tooString.indexOf("\"name\": [")+"\"name\": [ [".length(), tooString.length());
                    ss_ = ss_.substring(0, ss_.indexOf("]"));
                    String ss_2 = tooString.substring(tooString.indexOf("\"url\": [")+"\"url\": [ [".length(), tooString.length());
                    ss_2 = ss_2.substring(0, ss_2.indexOf("]"));

                    int wacky = 1;

                    doi2 = true;

                    ttstr2 = tooString;

                    do {

                        imgCat2 = ttstr2.substring(ttstr2.indexOf("<noscript>") + "<noscript>".length(), ttstr2.length());
                        imgCat2 = imgCat2.substring(0, imgCat2.indexOf("</noscript>"));
                        imgCat2 = imgCat2.substring(imgCat2.indexOf("src=\"") + "src=\"".length(), imgCat2.length());
                        imgCat2 = imgCat2.substring(0, imgCat2.indexOf("\""));
                        ttstr2 = ttstr2.substring(ttstr2.indexOf("<noscript>") + "<noscript>".length(), ttstr2.length());
                        ttstr2 = ttstr2.substring(ttstr2.indexOf("</noscript>") + "</noscript>".length(), ttstr2.length());

                        if(wacky != 1) {                
                            try {
                                ss_ = ss_.substring(ss_.indexOf("\",")+"\",".length(), ss_.length());
                                ss_2 = ss_2.substring(ss_2.indexOf("\",")+"\",".length(), ss_2.length());
                            } catch(Exception eu) {
                            }
                        }

                        if(doi2) {
                        wacky++;
                if(wacky > 37)
                doi2 = false;
                        String aaa = ss_.substring(ss_.indexOf("\"") + 1, ss_.length());

                        String cat2 = aaa.substring(0, aaa.indexOf("\""));

                        String a_aa = ss_2.substring(ss_2.indexOf("\"") + 1, ss_2.length());

                        String url2 = a_aa.substring(0, a_aa.indexOf("\""));

                        Document do1 = Jsoup.connect(url2).get();

                        if(percentage > 99) { 
                            jjj.dispose();
                            bbb = true;
                        }

                        String toooString = do1.toString();

                            for(int i=0; i<12; i++) {
                            try {
                                String image = toooString.substring(toooString.indexOf("<noscript>") + "<noscript>".length(), toooString.length());
                                image = image.substring(0, image.indexOf("</noscript>"));
                                image = image.substring(image.indexOf("src=\"") + "src=\"".length(), image.length());
                                image = image.substring(0, image.indexOf("\""));
                                toooString = toooString.substring(toooString.indexOf("<noscript>") + "<noscript>".length(), toooString.length());
                                toooString = toooString.substring(toooString.indexOf("</noscript>") + "</noscript>".length(), toooString.length());
                                String title= toooString.substring(toooString.indexOf("<h3 class=\"card__title\">") + "<h3 class=\"card__title\">".length(), toooString.length());
                                toooString = title;
                                title = title.substring(0, title.indexOf("</h3>"));
                                String url_title = toooString.substring(toooString.indexOf("<a class=\"card__titleLink manual-link-behavior\" href=\"") + "<a class=\"card__titleLink manual-link-behavior\" href=\"".length(), toooString.length());
                                toooString = url_title;
                                url_title = url_title.substring(0, url_title.indexOf("\""));

                                toooString = toooString.substring(toooString.indexOf("</h3>") + "</h3>".length(), toooString.length());

                                abc.add(title);
                                abcd.add(image);
                                count++;

                                if(count % 37 == 0 || count == 1) {
                                    percentage ++;
                                    Thread t3 = new Thread() {
                                        public void run() {
                    ppp.getGraphics().setColor(Color.WHITE);
                    ppp.getGraphics().setColor(Color.RED);
                    ppp.getGraphics().setFont(new Font("arial", Font.BOLD, 80));
                                    ppp.getGraphics().drawString("Loading...", 10, 10);
                                        String ss = "|";
                                        for(int ii = 2; ii <= 99; ii++) {
                                            if(percentage >= ii)
                                                ss += "|";
                                            else
                                                ss += " ";
                                        }
                                        ss += "|";
                                        ppp.getGraphics().drawString(ss, 150, 100);
                                        ppp.getGraphics().drawString("0%", 150, 130);
                                        ppp.getGraphics().drawString("100%", 430, 130);
                                        }
                                    };
                                    t3.start();

                                }

                            } catch(Exception eeee) {System.out.println(eeee);}    
                            }

                                }

                    } while(doi2 && !bbb);

                }

                } catch(Exception ee) {

                    toString = tstring;
                }

                pag++;

            } while(doi && !bbb);
        } catch(Exception e) {
            e.printStackTrace();
        }        
        
        
        
        livesLabel.setBounds(10, 50, 200, 40);
        
        setupUserInterface();
        d = new Designer(this);
        setGUI(d);
        
        d.cp.add(livesLabel);
        
        beginGame();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
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

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {

            guns.shoot(ply);
        }

    }
    
    private void pauseIndefinitely() {
        try {
            Thread.sleep(100);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawShip() {
        d.g = p.getGraphics()
                ;
        if(ply.direction.equals("right")) {
            d.g.setColor(Color.pink);
            d.g.fillRect(ply.x, ply.y, 40, 20);
            d.g.drawLine(ply.x + 40 + 12, ply.y + 10, ply.x + 40 - 12, ply.y - 10);
            d.g.drawLine(ply.x + 40 + 12, ply.y + 10, ply.x + 40 - 12, ply.y + 10+20);
            d.g.setColor(new Color(255,0,255));
            d.g.fillOval(ply.x - 10, ply.y, 10, 10);
            d.g.fillOval(ply.x - 10, ply.y+10, 10, 10);
            d.g.fillOval(ply.x - 20, ply.y+5, 10, 10);
            d.g.fillOval(ply.x - 20-10, ply.y+5, 10, 10);
        } else if(ply.direction.equals("up")) {
            d.g.setColor(Color.pink);
            d.g.fillRect(ply.x, ply.y - 20, 20, 40);
            d.g.drawLine(ply.x + 10, ply.y - 12-20, ply.x + 10 - 20, ply.y + 12-20);
            d.g.drawLine(ply.x + 10, ply.y - 12-20, ply.x + 10 + 20, ply.y + 12-20);
            d.g.setColor(new Color(255,0,255));
            d.g.fillOval(ply.x, ply.y+20, 10, 10);
            d.g.fillOval(ply.x+10, ply.y+20, 10, 10);
            d.g.fillOval(ply.x+ 5, ply.y+30, 10, 10);
            d.g.fillOval(ply.x +5, ply.y + 40 , 10, 10);
        } else if(ply.direction.equals("left")) {
            d.g.setColor(Color.pink);
            d.g.fillRect(ply.x - 20, ply.y + 20, 40, 20);
            d.g.drawLine(ply.x - 20 - 12, ply.y + 10+20, ply.x - 20 + 12, ply.y - 10+20);
            d.g.drawLine(ply.x - 20 - 12, ply.y + 10+20, ply.x - 20 + 12, ply.y + 10+20+20);
            d.g.setColor(new Color(255,0,255));
            d.g.fillOval(ply.x + 20, ply.y+20, 10, 10);
            d.g.fillOval(ply.x + 20, ply.y+20+10, 10, 10);
            d.g.fillOval(ply.x + 30, ply.y+20+5, 10, 10);
            d.g.fillOval(ply.x + 30+10, ply.y+20+5, 10, 10);
        } else if(ply.direction.equals("down")) {
            d.g.setColor(Color.pink);
            d.g.fillRect(ply.x, ply.y, 20, 40);
            d.g.drawLine(ply.x + 10, ply.y + 12 + 40, ply.x + 10 - 20, ply.y - 12+40);
            d.g.drawLine(ply.x + 10, ply.y + 12 + 40, ply.x + 10 + 20, ply.y - 12+40);
            d.g.setColor(new Color(255,0,255));
            d.g.fillOval(ply.x, ply.y-10, 10, 10);
            d.g.fillOval(ply.x+10, ply.y-10, 10, 10);
            d.g.fillOval(ply.x+ 5, ply.y-20, 10, 10);
            d.g.fillOval(ply.x +5, ply.y -30 , 10,10) ; 
        }
    }
    
    private void drawSaucer(Saucer saucer) {
        d.g.setColor(Color.green);
        d.g.fillRect(saucer.x, saucer.y, 100, 20);
        d.g.setColor(Color.gray);
        d.g.fillRect(saucer.x+30, saucer.y-20, 40, 20);
    }
    
    ImageIcon imageIcon = new ImageIcon("space.gif");
    Image space = imageIcon.getImage();
    
    private void clearScreen() {
        try {
            d.g = p.getGraphics();
            d.g.setColor(Color.BLACK);
            d.g.fillRect(0, 0, 1312, 800);
        } catch(Exception ec) {
        }
    }
    
    public class MusicStorage {

        /**
        * Opens a wav file and plays it
        * @param args
        */
       public void play(Film song) {
           try {

               sound = AudioSystem.getClip();

               sound.open(AudioSystem.getAudioInputStream(new File(song.getClip())));
               
               sound.start();

               if(iii == 1)
                iii = 2;

           } catch (Exception e) {
               System.out.println("Whatever" + e);
           }
         }
   }

    private void drawLetter(Character ch, int xloc, int yloc) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                d.g.setColor(Color.WHITE);
                d.g.drawString(ch.toString(), xloc, yloc);
            }
        });
        t.start();
    }
    
    public class Film {

        private String name;
        private String clip;

        public Film(String name, String clip) {
            this.name = name;
            this.clip = clip;
        }

        public String getClip() {
            return clip;
        }

        public String getName() {
            return name;
        }
    }

    int u = 0;

    public void setAbcd() {
        Graphics gr = eat.getGraphics();
        if(kikl == 0) {
            kikl = 1;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread a = new Thread() {
                        public void run() {
                            while(true) {
                            }
                        }
                    };
                    while(true)
                        try {
                            try {
                            final BufferedImage image = new BufferedImage(
                                    200, 200, BufferedImage.TYPE_INT_RGB);
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    Graphics2D g = (Graphics2D) gr;
                                    GradientPaint primary = new GradientPaint(
                                            0f, 0f, Color.WHITE, 200f, u, new Color(u,u,u));
                                    GradientPaint shade = new GradientPaint(
                                            0f, 0f, new Color(u, 0, 0, 0),
                                            0f, 200f, new Color(u, 0, 0, 255));
                                    u+=10;
                                    if(u >= 255)
                                        u = 0;
                                    g.setPaint(primary);
                                    g.fillRect(0, 0, 1312, 140);
                                    g.setPaint(shade);
                                    g.fillRect(0, 0, 1312, 140);
                                }
                            };
                            SwingUtilities.invokeLater(r);
                                Thread.sleep(130);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            int j = 0;
                            for(int i=iei; i<iei+11; i++) {
                                try {
                                    URL url = new URL(abcd.get(i));
                                    Image image2 = ImageIO.read(url);
                                    gr.drawImage(image2, 10 + j++*113, 10, 95, 95, null);
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            iei += 11;
                            if(iei >= abcd.size())
                                iei = 0;
                            Thread.sleep(10000);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                }
            });
            t.start();
        }
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
                for(int i=0; i<410; i++) {
                    stars.set();
                }
                guns = new GunList(ply.x, ply.y);
                meteors = new MeteorList(0, 0);
                Thread t = new Thread() {
                    public void run() {
                        while(true) {
                            for(int i=0; i<16; i++) {
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
                setAbcd();
                int jj = 1;
                while(true) {
                    try {
                    clearScreen();
                    
                    if(d.cp.score > jj && d.cp.score < jj + 43) {
                        jj += 42;

                        Bass b = new Bass();
                        Bass c = new Bass();
                    }
                    
                    drawShip();
                    
                    drawSaucer(saucer1);
                    
                    drawSaucer(saucer2);
                    
                    guns.moveAlongTheShots(ply);
                  
                    guns.drawShots(d);
                    
                    meteors.moveAlongTheMeteors();
                    
                    meteors.drawMeteors(d);
                    
                    stars.drawStars(d);

                            if(abc.size() > 40)
                                for(int i=0; i<meteors.list().size(); i++) {
                                    try {
                                        meteors.list().get(i).imageSrc = abc.get(i);
                                        meteors.list().get(i).imageSrc1 = abcd.get(i);
                                    } catch(Exception ee) {ee.printStackTrace();}
                                }

                    for(int j=0; j<meteors.list().size(); j++) {
                        try {
                            Meteor meteor = meteors.list().get(j);
                            if(saucer1.x >= meteor.x && saucer1.x <= meteor.x + meteor.width && saucer1.y >= meteor.y && saucer1.y <= meteor.y + meteor.height) {
                                meteors.list().remove(meteor);
                                cp.plusScore();
                                cp.scoreLabel.setText("Delicious score: " + cp.score);
                                explode(meteor);
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }

                    for(int j=0; j<meteors.list().size(); j++) {
                        try {
                            Meteor meteor = meteors.list().get(j);
                            if(saucer2.x >= meteor.x && saucer2.x <= meteor.x + meteor.width && saucer2.y >= meteor.y && saucer2.y <= meteor.y + meteor.height) {
                                meteors.list().remove(meteor);
                                cp.plusScore();
                                explode(meteor);
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }

                    for(int j=0; j<meteors.list().size(); j++) {
                        try {
                            Meteor meteor2 = meteors.list().get(j);
                            meteor2.width ++;
                            meteor2.height ++;
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    
                    for(int i = 0; i<meteors.list().size(); i++) {
                        for(int j=0; j<meteors.list().size(); j++) {
                            try {
                                Meteor meteor2 = meteors.list().get(i);
                                Meteor meteor = meteors.list().get(j);
                                if(meteor2 != meteor && meteor2.x >= meteor.x && meteor2.x <= meteor.x + meteor.width && meteor2.y >= meteor.y && meteor2.y <= meteor.y + meteor.height) {
                                    if(!meteor2.issplit)
                                    meteors.list().remove(meteor2);
                                    if(!meteor.issplit)
                                    meteors.list().remove(meteor);
                                    explode(meteor);
                                }
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    for(int i = 0; i<guns.list().size(); i++) {
                        if(guns.list().get(i).x < 0 || guns.list().get(i).x > 1300 || guns.list().get(i).y < 0 || guns.list().get(i).y > 600)
                            guns.list().remove(guns.list().get(i));
                        for(int j=0; j<meteors.list().size(); j++) {
                            try {
                                Gun gun = guns.list().get(i);
                                Meteor meteor = meteors.list().get(j);
                                if(!meteor.issplit && gun.x >= meteor.x && gun.x <= meteor.x + meteor.width && gun.y >= meteor.y && gun.y <= meteor.y + meteor.height) {
                                    if(1==1||gun.x <= meteor.x + 0.2*meteor.width && meteor.kind.equals("square")) {
                                        if(1==1||gun.y <= meteor.y + 0.8*meteor.height) {
                                            Thread tt = new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Title title = new Title();
                                                    title.title = meteor.imageSrc;
                                                    title.x = meteor.x;
                                                    title.y = meteor.y;
                                                    title.x_ = new ArrayList<>();
                                                    title.y_ = new ArrayList<>();
                                                    title.x_accel = new ArrayList<>();
                                                    title.y_accel = new ArrayList<>();
                                                    for(int ind=0; ind<title.title.length(); ind++) {
                                                        title.x_.add(title.x);
                                                        title.y_.add(title.y);
                                                        title.x_accel.add(rand.nextInt(30) - rand.nextInt(30));
                                                        title.y_accel.add(rand.nextInt(30) - rand.nextInt(30));
                                                    }int twe = 0;
                                                    while(true) {
                                                        for(int i=0; i<title.title.length(); i++) {
                                                            title.x_.set(i, title.x_.get(i) + title.x_accel.get(i));
                                                            title.y_.set(i, title.y_.get(i) + title.y_accel.get(i));
                                                            drawLetter(title.get(i), title.x_.get(i), title.y_.get(i));
                                                        }
                                                        try {
                                                            Thread.sleep(40);
                                                        } catch(Exception exc) {
                                                            exc.printStackTrace();
                                                        }
                                                        twe++;
                                                        if(twe == 7)
                                                            return;
                                                    }
                                                }
                                            });
                                            tt.start();
                                            Meteor m = new Meteor();
                                            Meteor n = new Meteor();
                                            m.kind = "square";
                                            n.kind = "square";
                                            m.width = meteor.width;
                                            m.height = meteor.height - (meteor.y - gun.y);
                                            m.x = meteor.x;
                                            m.y = meteor.y;
                                            m.y_accel -= 20;
                                            m.y_accel -= meteor.y_accel;
                                            m.x_accel = meteor.x_accel;
                                            meteors.list().add(m);
                                            n.y_accel += 20;
                                            n.y_accel += meteor.y_accel;
                                            n.x_accel = meteor.x_accel;
                                            n.width = meteor.width;
                                            n.height = meteor.height - (meteor.height - (meteor.y - gun.y));
                                            n.x = meteor.x;
                                            n.y = gun.y;
                                            meteors.list().add(n);
                                            
                                            m.issplit = true;
                                            n.issplit
                                                    = true;
                                        }
                                    }
                                    meteors.list().remove(meteor);
                                    explode(meteor);
                                    guns.list().remove(gun);
                                    d.cp.plusScore();
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
                            if(ply.x >= meteor.x && ply.x <= meteor.x + meteor.width && ply.y >= meteor.y && ply.y <= meteor.y + meteor.height) {
                                meteors.list().remove(meteor);
                                explode(meteor);
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
                    
                    if(ply.x < 0)
                        ply.x = 1300;
                    
                    if(ply.x > 1300)
                        ply.x = 0;
                    
                    if(ply.y < 0)
                        ply.y = 1300;
                    
                    if(ply.y > 1300)
                        ply.y = 0;
                    } catch(Exception e) {}
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
        if(1==1)return;
        Thread tt = new Thread() {
            public void run() {
                storage.play(speech);
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
                        d.g.setColor(Color.WHITE);
                        d.g.fillOval(x1, y1, 70, 70);
                        d.g.setColor(Color.WHITE);
                        d.g.fillOval(x2, y2, 70, 70);
                        d.g.setColor(Color.WHITE);
                        d.g.fillOval(x3, y3, 70, 70);
                        d.g.setColor(Color.WHITE);
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
        frame = new JFrame("Meteor Fight: Space to shoot, arrows to move, and s to turn");
        frame.setLayout(null);
        
        frame.addKeyListener(this);

        frame.setBounds(UserInterface.LOCATION_X, UserInterface.LOCATION_Y,
                        UserInterface.UI_WIDTH, UserInterface.UI_HEIGHT);
        p.setBounds(0, 0, 1312, 600);
        other.setBounds(0, 0, 1312, 850);
        
        frame.add(other);
        
        eat.setBounds(0, 600, 1312, 150);
        
        other.add(eat);
        
        other.add(p);
        
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