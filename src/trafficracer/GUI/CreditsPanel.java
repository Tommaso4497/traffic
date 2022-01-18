package trafficracer.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import trafficracer.utils.Resources;

public class CreditsPanel extends JPanel {

    private final Image backgroundCredits;
    private static final int WIDTH_PANEL = 800;
    private static final int HEIGHT_PANEL= 600;
    private final HotArea back;

    public CreditsPanel() {
        this.setSize(WIDTH_PANEL, HEIGHT_PANEL);
        this.backgroundCredits = Resources.getImage("/trafficracer/GUI/images/crediti.png");
        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());
        this.back = new HotArea(265, 455, 290, 96);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundCredits, 0, 0, null);
    }

    public class MouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            if (back.isClicked(e)) {
                MainFrame.menuPanel.setVisible(true);
                MainFrame.creditsPanel.setVisible(false);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            if ((back.contains(p))) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
}
