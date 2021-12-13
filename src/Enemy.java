import java.awt.*;

public abstract class Enemy extends MoveEntity{
    public Enemy(int x, int y, Image[] moveImages, Image[] deadImages) {
        super(x, y, moveImages, deadImages);
    }
    public abstract void setRowMove(Manage manage);

    public abstract void setColMove(Manage manage);
    @Override
    public void move(Manage manage) {

    }
}
