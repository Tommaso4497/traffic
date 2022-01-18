package trafficracer.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import trafficracer.utils.Resources;

public class BackgroundPanel extends JPanel {

    private static final int WIDTH_PANEL = 800;
    private static final int HEIGHT_PANEL = 600;
    public static int background;
    private final Image BackgroundChoose;
    private final HotArea desert;
    private final HotArea hill;
    private final HotArea mountain;
    private final HotArea sea;
    public static final int HILL = 1;
    public static final int DESERT = 2;
    public static final int MOUNTAIN = 3;
    public static final int SEA = 4;

    public BackgroundPanel() {
        this.setSize(WIDTH_PANEL, HEIGHT_PANEL);
        this.BackgroundChoose = Resources.getImage("/trafficracer/GUI/images/background.png");
        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());
        this.sea = new HotArea(88, 559, 116, 29);
        this.desert = new HotArea(65, 313, 188, 29);
        this.hill = new HotArea(525, 313, 180, 29);
        this.mountain = new HotArea(485, 548, 230, 29);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(BackgroundChoose, 0, 0, null);
    }

    public class MouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            if (sea.isClicked(e)) {
                background = SEA;
                MainFrame.backgroundPanel.setVisible(false);
                MainFrame.gamePanel.setVisible(true);
                MainFrame.gamePanel.requestFocusInWindow();
                MainFrame.gamePanel.startGame();
                MainFrame.gamePanel.setBackground("mare");
                MainFrame.menuaudio.stop();
            }
            if (mountain.isClicked(e)) {
                background = MOUNTAIN;
                MainFrame.backgroundPanel.setVisible(false);
                MainFrame.gamePanel.setVisible(true);
                MainFrame.gamePanel.requestFocusInWindow(); 
                MainFrame.gamePanel.startGame();
                MainFrame.gamePanel.setBackground("neve");
                MainFrame.menuaudio.stop();
            }
            if (hill.isClicked(e)) {
                background = HILL;
                MainFrame.backgroundPanel.setVisible(false);
                MainFrame.gamePanel.setVisible(true);
                MainFrame.gamePanel.requestFocusInWindow();
                MainFrame.gamePanel.startGame();
                MainFrame.gamePanel.setBackground("collina");
                MainFrame.menuaudio.stop();
            }
            if (desert.isClicked(e)) {
                background = DESERT;
                MainFrame.backgroundPanel.setVisible(false);
                MainFrame.gamePanel.setVisible(true);
                MainFrame.gamePanel.requestFocusInWindow();
                MainFrame.gamePanel.startGame();
                MainFrame.gamePanel.setBackground("deserto");
                MainFrame.menuaudio.stop();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            if ((sea.contains(p)) || (desert.contains(p)) || (mountain.contains(p)) || (hill.contains(p))) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
}
