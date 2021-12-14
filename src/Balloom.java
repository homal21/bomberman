import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Balloom extends Enemy {
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
            new ImageIcon("res/sprites/mob_dead1.png").getImage(),
            new ImageIcon("res/sprites/mob_dead2.png").getImage(),
            new ImageIcon("res/sprites/mob_dead3.png").getImage()
    };

    public Balloom(int x, int y, Image[] moveImages, Image[] deadImages) {
        super(x, y, moveImages, deadImages);
    }

    public Balloom(int x, int y) {
        super(x,y,balloomMoveImages, balloomDeadImages);
    }

    @Override
    public void move(Manage manage) {
        countMove ++; countMove %= 2;
        if (countMove > 0 )return;
        super.move(manage);
    }

    @Override
    public void setTarget(Manage manage) {
        //System.out.println(orient);
        int col = (x + Entity.SIZE/2) / Entity.SIZE;
        int row = (y + Entity.SIZE/2) / Entity.SIZE;
        ArrayList<Entity> targets = new ArrayList<>();

        Entity upEntity = manage.staticEntities[row - 1][col];
        if (!upEntity.isBlock()) {
            targets.add(upEntity);
            if (orient == UP) targets.add(upEntity);
        }
        Entity downEntity = manage.staticEntities[row + 1][col];
        if (!downEntity.isBlock()) {
            targets.add(downEntity);
            if (orient == DOWN) targets.add(downEntity);
        }
        Entity leftEntity = manage.staticEntities[row][col - 1];
        if (!leftEntity.isBlock()) {
            targets.add(leftEntity);
            if (orient == LEFT) targets.add(leftEntity);
        }
        Entity rightEntity = manage.staticEntities[row][col + 1];
        if (!rightEntity.isBlock()) {
            targets.add(rightEntity);
            if (orient == RIGHT) targets.add(rightEntity);
        }

        if (targets.size() == 0) {
            targetX = 0;
            targetY = 0;
            return;
        }
        Collections.shuffle(targets);
        targetX = targets.get(0).getX();
        targetY = targets.get(0).getY();
    }
}
