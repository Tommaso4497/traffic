package trafficracer.GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JPanel;
import trafficracer.Enemies.Enemies;
import trafficracer.utils.Resources;
import trafficracer.Player.Player;

public class GamePanel extends JPanel {

    private final Player player;
    private final Intro intro;
    private Image backgroundGame;
    private int time = 4;

    private Thread timeThread;
    private Thread scoreThread;
    private Thread enemieGen;
    private Thread enemieMove;

    private static final int WIDTH_PANEL = 800;
    private static final int HEIGHT_PANEL = 600;
    private int velocita;
    private static int points = 0;
    private boolean spawn;
    private int Y = 0;
    private int Y2 = -590;
    private final MainFrame mainFrame;

    private final  ArrayList<Enemies> enemies;

    public GamePanel( MainFrame pMainFrame,Player pPlayer) {
        this.mainFrame = pMainFrame;
        this.setSize(WIDTH_PANEL, HEIGHT_PANEL);
        this.player = pPlayer;
        this.enemies = new ArrayList<>();
        
        this.addKeyListener(new KeyListener());
        this.intro = new Intro(25, 100); 
    }

    public void startGame() {
        this.timeThread = new Thread(new TimeThread());
        this.timeThread.start();
    }

    public void newGame() {
        this.enemieGen = new Thread(new EnemiesGenThread());
        this.scoreThread = new Thread(new PointThread());
        this.enemieMove = new Thread(new EnemiesMovementThread());

        enemieGen.start();
        scoreThread.start();
        enemieMove.start();

        spawn = true;
        MainFrame.gameaudio.playLoop();
        MainFrame.partenzauto.play();
    }

    public void setBackground(String s) {
        this.backgroundGame = Resources.getImage("/trafficracer/GUI/images/background_" + s + ".png");
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundGame, 0, Y, null);
        g.drawImage(backgroundGame, 0, Y2, null);
        this.player.paintPlayer(g);
        g.setColor(Color.black);
        g.fillRect(650, 0, 150, 25);
        g.setColor(Color.red);
        g.drawString("Punteggio : " + points, 670, 15);

       
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).enemiePaint(g);
        }
        if (intro.getVisible()) {
            intro.paintIntro(g);
        }
    }

    public void gameOver() {
        mainFrame.gameOverPanel.setPoints(this.points); 
        if (this.points > mainFrame.recordPanel.getPoints()) { 
            mainFrame.recordPanel.setPoints(points); 
        }

        points = 0;
        time = 4;
        spawn = false;
        player.resetPos();
        player.setMove(false);
        enemies.clear(); 
        
        
        MainFrame.gameaudio.stop();
        MainFrame.gameoveraudio.play();
        MainFrame.gameOverPanel.setVisible(true);
        MainFrame.gamePanel.setVisible(false);
    }

    private void update() {
        if (Y < 600) {
            Y += velocita;
            Y2 += velocita;
        } else {
            Y = 0;
            Y2 = -600;
        }
    }

    private void updateSpeed() { 
        velocita = 1 + (points / 6000);
    }

    public class KeyListener extends KeyAdapter {  

        @Override
        public void keyPressed(KeyEvent ke) {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (player.getMove() && player.coordinate.y > 315) {
                        player.coordinate.y -= 20;
                    }
                    break;
                case KeyEvent.VK_S:
                    if (player.getMove() && player.coordinate.y < 520) {
                        player.coordinate.y += 20;
                    }
                    break;
                case KeyEvent.VK_D:
                    if (player.getMove() && player.coordinate.x < 470) {
                        player.coordinate.x += 103;
                    }
                    break;
                case KeyEvent.VK_A:
                    if (player.getMove() && player.coordinate.x > 270) {
                        player.coordinate.x -= 103;
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    private class EnemiesGenThread implements Runnable { 

        @Override
        public void run() {

            int enemiesWait;

            while (spawn) {
                Random randomSpaceEnemies = new Random();
                enemiesWait = randomSpaceEnemies.nextInt(2);
                if (enemiesWait == 1) {
                    enemies.add(new Enemies());
                }
                try {
                    Thread.sleep(375); 
                } catch (InterruptedException ex) {
                }
            }

        }

    }

    private class EnemiesMovementThread implements Runnable {

        @Override
        public void run() {
            while (spawn) {
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).canMove()) {
                        enemies.get(i).moveCollider();
                        if (player.getCollide().intersects(enemies.get(i).getCollide())) {
                            gameOver();
                        }
                    }
                };
                try {
                    Thread.sleep(7); //quantità di macchine in spawn
                } catch (InterruptedException ex) {
                }

            }

        }
    }

    private class PointThread implements Runnable {   

        @Override
        public void run() {
            while (spawn) {
                points++;
                repaint();  
                update();
                updateSpeed();
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class TimeThread implements Runnable {  

        @Override
        public void run() {
            intro.setVisible(true);
            while (time > 0) {
                time--;
                intro.setNextImage(time);
                repaint();
                MainFrame.trombetta.play();
                try {
                    Thread.sleep(1025);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            intro.setVisible(false);
            player.setMove(true);
            newGame();
        }
    }
}
