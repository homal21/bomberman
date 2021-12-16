import java.awt.*;

public abstract class Enemy extends MoveEntity{
    public int targetX;
    public int targetY;
    public Enemy(int x, int y, Image[] moveImages, Image[] deadImages) {
        super(x, y, moveImages, deadImages);
        this.orient = 1;
    }

    @Override
    public void move(Manage manage) {
        if (orient == 0) {
            System.out.println("zzz");
        }
        if (targetX == 0 && targetY == 0) setTarget(manage);
        if (manage.staticEntities[targetY / Entity.SIZE][targetX / Entity.SIZE].isBlock()) setTarget(manage);
        if (Math.abs(x - targetX) <= speed && Math.abs(y-targetY) <= speed) {
            setX(targetX);
            setY(targetY);
            setTarget(manage);
            return;
        }
        if (x > targetX) {this.orient = LEFT; moveLeft(manage);}
        if (x < targetX) {this.orient = RIGHT;moveRight(manage);}
        if (y > targetY) {this.orient = UP;moveUp(manage);}
        if (y < targetY) {this.orient = DOWN;moveDown(manage);}
    }
    public abstract void setTarget(Manage manage);
}
