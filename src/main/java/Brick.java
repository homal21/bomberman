import javax.swing.*;
import java.awt.*;

public class Brick extends Entity {
    public Brick(int x, int y) {
        super(x,y,new ImageIcon("boombermann1/res/sprites/brick.png").getImage());
    }
}

