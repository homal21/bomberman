import javax.swing.*;
import java.awt.*;

public class FlamesItem extends Item{
    public FlamesItem(int x, int y) {
        super(x, y, new ImageIcon("res/sprites/powerup_flames.png").getImage());
    }

    @Override
    public void upgrade(Bomber bomber) {
        bomber.bombSize ++;
    }
}
