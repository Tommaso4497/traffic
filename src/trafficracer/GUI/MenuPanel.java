package trafficracer.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import trafficracer.utils.Resources;

public class MenuPanel extends JPanel {

    private final Image backgroundMenu;
    private static final int WIDTH_PANEL = 800;
    private static final int HEIGHT_PANEL = 600;
    private final HotArea background;
    private final HotArea info;
    private final HotArea credits;
    private final HotArea record;

    public MenuPanel() {

        this.setSize(WIDTH_PANEL, HEIGHT_PANEL);
        this.backgroundMenu = Resources.getImage("/trafficracer/GUI/Images/menu.png");
        this.background = new HotArea(300, 187, 267, 24);
        this.info = new HotArea(300, 242, 170, 23);
        this.record = new HotArea(300, 303, 123, 23);
        this.credits = new HotArea(300, 360, 123, 23);
        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());
        MainFrame.menuaudio.play();

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundMenu, 0, 0, null);
    }

    public class MouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            if (credits.isClicked(e)) {
                MainFrame.creditsPanel.setVisible(true);
                MainFrame.menuPanel.setVisible(false);
            }
            if (info.isClicked(e)) {
                MainFrame.infoPanel.setVisible(true);
                MainFrame.menuPanel.setVisible(false);
            }
            if (record.isClicked(e)) {
                MainFrame.recordPanel.setVisible(true);
                MainFrame.menuPanel.setVisible(false);
            }
            if (background.isClicked(e)) {
                MainFrame.backgroundPanel.setVisible(true);
                MainFrame.menuPanel.setVisible(false);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            if ((background.contains(p)) || (info.contains(p)) || (credits.contains(p)) || (record.contains(p))) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
}
