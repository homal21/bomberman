import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;

public class Wall extends Entity {
    public Wall(int x, int y) {
        super(x,y,new ImageIcon("boombermann1/res/sprites/wall.png").getImage());
    }

}
