import java.awt.*;

public abstract class Entity {
    protected int x, y;
    public static final int SIZE = 32;
    protected Image img;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Entity(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public boolean isBlock() {
        if (this instanceof Wall) return true;
        if (this instanceof Brick) return true;
        if (this instanceof Bomb) return true;
        return false;
    }

    public void show(Graphics2D graphics) {
        graphics.drawImage(img, x, y, SIZE, SIZE, null);
    }
}
