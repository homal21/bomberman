import javax.swing.*;
import java.awt.*;

public abstract class Item extends Brick{
    public Item(int x, int y, Image img) {
        super(x, y);
        this.img = img;
        explodedImage = new Image[] {
                new ImageIcon("res/sprites/item_exploded.png").getImage(),
                new ImageIcon("res/sprites/item_exploded1.png").getImage(),
                new ImageIcon("res/sprites/item_exploded2.png").getImage()
        };
    }

    public abstract void upgrade(Bomber bomber);
}
