import javax.swing.*;
import java.awt.*;

public class game1 extends JFrame {
   // public static final WIDTH = 1412;
   // public static final HEIGHT = 632;
    private Screen screen;

    public game1() {
        setSize(32*32, 32*15);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen = new Screen(this);
        add(screen);
    }


}
