import javax.swing.*;
import java.awt.*;

public class BombsItem extends Item{
    public BombsItem(int x, int y) {
        super(x, y, new ImageIcon("res/sprites/powerup_bombs.png").getImage());
    }

    @Override
    public void upgrade(Bomber bomber) {
        bomber.bombCapacity ++;
    }
}
