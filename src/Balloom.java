import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Balloom extends Enemy {
    private int res;
    private int remember;
    public static Image[] balloomMoveImages = {
            new ImageIcon("res/sprites/balloom_right1.png").getImage(),
            new ImageIcon("res/sprites/balloom_right2.png").getImage(),
            new ImageIcon("res/sprites/balloom_right3.png").getImage(),
            new ImageIcon("res/sprites/balloom_left1.png").getImage(),
            new ImageIcon("res/sprites/balloom_left2.png").getImage(),
            new ImageIcon("res/sprites/balloom_left3.png").getImage(),
            new ImageIcon("res/sprites/balloom_right1.png").getImage(),
            new ImageIcon("res/sprites/balloom_right2.png").getImage(),
            new ImageIcon("res/sprites/balloom_right3.png").getImage(),
            new ImageIcon("res/sprites/balloom_left1.png").getImage(),
            new ImageIcon("res/sprites/balloom_left2.png").getImage(),
            new ImageIcon("res/sprites/balloom_left3.png").getImage(),
    };
    public static Image[] balloomDeadImages = {
            new ImageIcon("res/sprites/balloom_dead.png").getImage(),
    };

    public Balloom(int x, int y, Image[] moveImages, Image[] deadImages) {
        super(x, y, moveImages, deadImages);
    }

    @Override
    public void setRowMove(Manage manage) {
        int row = y / Entity.SIZE;
        int colRight = x / Entity.SIZE;
        int colLeft = (x + Entity.SIZE-1 )/ Entity.SIZE;
        Entity entity1 = manage.staticEntities[row][colRight + 1];
        Entity entity2 = manage.staticEntities[row][colLeft-1];
        if (entity2.isBlock()) res = RIGHT;
        if (entity1.isBlock()) res = LEFT;
    }

    @Override
    public void setColMove(Manage manage) {
        int col = x / Entity.SIZE;
        int rowUp = (y + + Entity.SIZE-1) / Entity.SIZE;
        int rowDown = y / Entity.SIZE;
        Entity entity1 = manage.staticEntities[rowUp - 1][col];
        Entity entity2 = manage.staticEntities[rowDown + 1][col];
        if (entity2.isBlock()) remember = UP;
        if (entity1.isBlock()) remember = DOWN;
    }

    @Override
    public void move(Manage manage) {
        if (res == RIGHT) moveRight(manage);
        if (res == LEFT) moveLeft(manage);
        if (remember == UP) moveUp(manage);
        if (remember == DOWN) moveDown(manage);

    }
}
