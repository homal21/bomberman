import javax.swing.*;
import java.awt.*;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y) {
        super(x, y, new ImageIcon("res/sprites/powerup_speed.png").getImage());
    }

    @Override
    public void upgrade(Bomber bomber) {
        bomber.speed ++;
    }
}
