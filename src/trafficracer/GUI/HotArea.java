package trafficracer.GUI;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class HotArea extends Rectangle {

    public HotArea(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
    }

    public boolean isClicked(MouseEvent e) {
        return this.contains(e.getPoint());
    }
}
