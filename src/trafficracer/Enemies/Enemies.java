package trafficracer.Enemies;

import java.awt.*;
import java.util.Random;
import trafficracer.utils.Resources;

public class Enemies {

    private final Image enemyImage;
    private final Point coordinate;
    public Rectangle enemieCollider;
    private final Random random;
    private int speed;

    String tipo_macchina[] = {"arancione", "rossa", "polizia", "gialla", "verde"};
    int corsia_macchina[] = {270, 370, 470}; 
    
    Thread carSpeed;

    public Enemies() {
        this.speed = 2;
        this.coordinate = new Point();
        this.random = new Random();
        this.enemyImage = Resources.getImage("/trafficracer/Enemies/images/macchina_" + tipo_macchina[random.nextInt(tipo_macchina.length)] + ".png");
        this.coordinate.x = corsia_macchina[random.nextInt(corsia_macchina.length)];
        this.coordinate.y = -80;
        this.enemieCollider = new Rectangle(this.coordinate.x, this.coordinate.y, this.enemyImage.getWidth(null), this.enemyImage.getHeight(null));
        this.carSpeed = new Thread(new incSpeed());
        this.carSpeed.start();
    }

    
    public void enemiePaint(Graphics g) {
        g.drawImage(this.enemyImage, this.coordinate.x, this.coordinate.y, null);
       // g.drawRect(this.coordinate.x, this.coordinate.y, this.enemyImage.getWidth(null), this.enemyImage.getWidth(null));
    }

    public boolean canMove() {
        return (this.coordinate.y < 600);
    }

    public void moveCollider() { 
        this.enemieCollider.setLocation(this.coordinate);
        this.coordinate.y += this.speed;
    }

    public Rectangle getCollide() { 
        return this.enemieCollider;
    }

    private class incSpeed implements Runnable {

        @Override
        public void run() {
            do{
            try {
                Thread.sleep(6000);
            } catch (InterruptedException ex) {
            }
            speed += 2; 
        }while(true);
            }
    }
}
