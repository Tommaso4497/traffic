package trafficracer.Player;

import java.awt.*;
import trafficracer.utils.Resources;

public class Player {
    private final Rectangle playerCollider;
    public final Image playerImage;
    public Point coordinate;
    private boolean canMove;

    public Player() {
        this.playerImage = Resources.getImage("/trafficracer/Player/Images/macchina_blu.png");
        this.coordinate = new Point(370, 510);
        this.playerCollider = new Rectangle(this.coordinate.x, this.coordinate.y, this.playerImage.getWidth(null), this.playerImage.getHeight(null));
        this.canMove = false;
    }
    
    public boolean getMove() {
        return this.canMove;
    }

    public void setMove(boolean move) {
        this.canMove = move;
    }

    public void resetPos() { 
        this.coordinate.x = 370;
        this.coordinate.y = 510;
    }

    public void paintPlayer(Graphics g) { 
        this.playerCollider.setLocation(this.coordinate);
        g.drawImage(playerImage, this.coordinate.x, this.coordinate.y, null);
        //g.drawRect(this.coordinate.x, this.coordinate.y, this.playerImage.getWidth(null), this.playerImage.getHeight(null));
    }

    public Rectangle getCollide() {
        return this.playerCollider;
    }
}
