import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

public class Game extends JPanel implements Runnable, ActionListener {
    private Screen screen;
    public Manage manage;
    private BitSet traceKey = new BitSet();
    public static boolean running = true;
    private int count = 0;
    public static Entity entity;
    public boolean isEnding = false;
    public int stageIndex = 0;
    public boolean isLoser = false;
    public boolean isStartStage = true;
    public String[] stageName = {
            "res/levels/level1.txt",
            "res/levels/level2.txt"
    };

    public Game(Screen screen) {
        this.screen = screen;
        setLayout(null);
        setFocusable(true);
        addKeyListener(keyAdapter);
        Thread mythread = new Thread(this);
        mythread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        manage.run(graphics2D);
        if (isStartStage) {
            manage.startStage(graphics2D, stageIndex + 1);
        }
        if (isEnding) {
            manage.endGame(graphics2D);
        }
        if (isLoser) {
            manage.loseGame(graphics2D);
        }
    }

    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            traceKey.set(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            traceKey.clear(e.getKeyCode());
        }
    };

    @Override
    public void run() {

        manage = new Manage(stageName[0]);
        while (running) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (traceKey.get(KeyEvent.VK_SPACE)) {
                manage.addNewBomb();
            }
            if (traceKey.get(KeyEvent.VK_RIGHT)) {
                manage.getBomber().changeOrient(Bomber.RIGHT);
            }
            if (traceKey.get(KeyEvent.VK_LEFT)) {
                manage.getBomber().changeOrient(Bomber.LEFT);
            }

            if (traceKey.get(KeyEvent.VK_DOWN)) {
                manage.getBomber().changeOrient(Bomber.DOWN);

            }

            if (traceKey.get(KeyEvent.VK_UP)) {
                manage.getBomber().changeOrient(Bomber.UP);
            }
            repaint();
            if (isStartStage) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isStartStage = false;
            }
            if (!manage.getBomber().isAlive) {
                isLoser = true;
            }
            count++;
            if (count == 1000000) {
                count = 1;
            }
            if (manage.isWon()) {
                sounds.Sound.play("sounds/complete.wav");
                stageIndex ++;
                if (stageIndex == stageName.length) {
                    isEnding = true;
                    repaint();
                    break;
                }
                manage = new Manage(stageName[stageIndex]);
                isStartStage = true;
            }

        }
    }

        @Override
        public void actionPerformed (ActionEvent e){

        }
}
