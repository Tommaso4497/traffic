package trafficracer.GUI;

import java.awt.*;
import trafficracer.utils.Resources;

public class Intro {
    private Image image;
    private final Point coord;
    private boolean isVisible;

    public Intro(int x, int y) {
        this.coord = new Point(x, y);
        this.isVisible = false;
    }

    public void setVisible(boolean visibility) {
        this.isVisible = visibility;
    }

    public boolean getVisible() {
        return this.isVisible;
    }

    public void setNextImage(int i) {
        this.image = Resources.getImage("/trafficracer/GUI/images/semaforo_" + i + ".png");
    }

    public void paintIntro(Graphics g) {
        g.drawImage(this.image, this.coord.x, this.coord.y, null);
    }

   
}
