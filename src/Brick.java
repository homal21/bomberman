import javax.swing.*;
import java.awt.*;

public class Brick extends Entity {
    public Image[] explodedImage = {
            new ImageIcon("res/sprites/brick_exploded.png").getImage(),
            new ImageIcon("res/sprites/brick_exploded1.png").getImage(),
            new ImageIcon("res/sprites/brick_exploded2.png").getImage()
    };
    public boolean isDestroyed = false;
    public boolean isExploding = false;
    public int count = 0;
    public int explodeIndex =0;
    public boolean isPortal = false;
    public Item item;

    public Brick(int x, int y) {
        super(x,y,new ImageIcon("res/sprites/brick.png").getImage());
    }

    public void explode() {
        setImg(explodedImage[explodeIndex]);
        count ++;count %= 20;
        if (count == 0) {
            explodeIndex ++;
            if (explodeIndex > 2) {
                explodeIndex = 2;
                isDestroyed = true;
            }

        }
    }

}

