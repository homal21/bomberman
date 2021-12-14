import java.awt.*;

public class Flame extends Entity{
    public Image[] images;
    public Flame(int x, int y, Image[] images) {
        super(x, y, images[0]);
        this.images = images;
    }
}
