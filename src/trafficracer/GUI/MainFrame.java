package trafficracer.GUI;

import javax.swing.JFrame;
import trafficracer.Player.Player;
import trafficracer.utils.AudioPlayer;

public class MainFrame extends JFrame {

    public static MenuPanel menuPanel;
    public static CreditsPanel creditsPanel;
    public static InfoPanel infoPanel;
    public static RecordPanel recordPanel;
    public static BackgroundPanel backgroundPanel;
    public static GamePanel gamePanel;
    public static Player player;
    public static GameOverPanel gameOverPanel;

    public static AudioPlayer gameaudio;
    public static AudioPlayer menuaudio;
    public static AudioPlayer gameoveraudio;
    public static AudioPlayer partenzauto;
    public static AudioPlayer trombetta;

    private static final int WIDTH_FRAME = 800;
    private static final int HEIGHT_FRAME = 630;

    public MainFrame() {
        this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("TRAFFIC RACER");

        gameaudio = new AudioPlayer("/trafficracer/Audio/gameaudio.wav");
        menuaudio = new AudioPlayer("/trafficracer/Audio/menuAudio.wav");
        gameoveraudio = new AudioPlayer("/trafficracer/Audio/incidente.wav");
        partenzauto = new AudioPlayer("/trafficracer/Audio/partenza.wav");
        trombetta = new AudioPlayer("/trafficracer/Audio/trmp.wav");

        menuPanel = new MenuPanel();
        creditsPanel = new CreditsPanel();
        infoPanel = new InfoPanel();
        recordPanel = new RecordPanel();
        backgroundPanel = new BackgroundPanel();
        player = new Player();
        gamePanel = new GamePanel(this, player);
        gameOverPanel = new GameOverPanel();
        

        this.getContentPane().add(menuPanel);
        this.getContentPane().add(creditsPanel);
        this.getContentPane().add(infoPanel);
        this.getContentPane().add(recordPanel);
        this.getContentPane().add(backgroundPanel);
        this.getContentPane().add(gamePanel);
        this.getContentPane().add(gameOverPanel);

        menuPanel.setVisible(true);
        creditsPanel.setVisible(false);
        infoPanel.setVisible(false);
        recordPanel.setVisible(false);
        backgroundPanel.setVisible(false);
        gamePanel.setVisible(false);
        gameOverPanel.setVisible(false);

    }
}
