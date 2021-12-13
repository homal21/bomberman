import javax.swing.*;
import java.awt.*;

public class Brick extends Entity {
    public Brick(int x, int y) {
        super(x,y,new ImageIcon("res/sprites/brick.png").getImage());
    }
}

