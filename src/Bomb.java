import javax.swing.*;
import java.awt.*;

public class Bomb extends Entity {
    protected int size, timeExplode;

    public Bomb(int x, int y) {
        super(x,y, new ImageIcon("res/sprites/bomb.png").getImage());
    }


}
