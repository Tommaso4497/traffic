package trafficracer.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import trafficracer.utils.Resources;

public class GameOverPanel extends JPanel {
    private final Image backgroundGameOver;
    private static final int WIDTH_PANEL = 800;
    private static final int HEIGHT_PANEL = 600;
    private final HotArea menu;
    private int points;

    public GameOverPanel() {
        this.points = 0;
        this.setSize(WIDTH_PANEL, HEIGHT_PANEL);
        this.backgroundGameOver = Resources.getImage("/trafficracer/GUI/images/riprova.png");
        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());
        this.menu = new HotArea(580, 508, 198, 66);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundGameOver, 0, 0, null);
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("" + this.points, 590, 195);
    }

    public class MouseListener extends MouseAdapter {
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (menu.isClicked(e)) {
                MainFrame.gameOverPanel.setVisible(false);
                MainFrame.menuPanel.setVisible(true);
                MainFrame.menuaudio.playLoop();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            if ((menu.contains(p))) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public void setPoints(int pPoints) {
        this.points = pPoints; 
    }
}
