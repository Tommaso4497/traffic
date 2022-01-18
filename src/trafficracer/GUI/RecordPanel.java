package trafficracer.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import trafficracer.utils.Resources;

public class RecordPanel extends JPanel {
    private final Image backgroundRecord;
    private static final int WIDTH_PANEL= 800;
    private static final int HEIGHT_PANEL = 600;
    private final HotArea back;
    private int points;

    public RecordPanel() {
        this.points = 0;
        this.setSize(WIDTH_PANEL, HEIGHT_PANEL);
        this.backgroundRecord = Resources.getImage("/trafficracer/GUI/Images/record.png");
        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());
        this.back = new HotArea(539, 470, 236, 106);
    }

    public void setPoints(int pPoints) {
        this.points = pPoints;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundRecord, 0, 0, null);
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD,40));
        g.drawString("Punteggio : " + points, 375, 300);
    }

    public class MouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            if (back.isClicked(e)) {
                MainFrame.menuPanel.setVisible(true);
                MainFrame.recordPanel.setVisible(false);
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
