import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Oneal extends Enemy {
    public static Image[] OnealMoveImages = {
            new ImageIcon("res/sprites/oneal_right1.png").getImage(),
            new ImageIcon("res/sprites/oneal_right2.png").getImage(),
            new ImageIcon("res/sprites/oneal_right3.png").getImage(),
            new ImageIcon("res/sprites/oneal_left1.png").getImage(),
            new ImageIcon("res/sprites/oneal_left2.png").getImage(),
            new ImageIcon("res/sprites/oneal_left3.png").getImage(),
            new ImageIcon("res/sprites/oneal_right1.png").getImage(),
            new ImageIcon("res/sprites/oneal_right2.png").getImage(),
            new ImageIcon("res/sprites/oneal_right3.png").getImage(),
            new ImageIcon("res/sprites/oneal_left1.png").getImage(),
            new ImageIcon("res/sprites/oneal_left2.png").getImage(),
            new ImageIcon("res/sprites/oneal_left3.png").getImage(),
    };
    public static Image[] OnealDeadImages = {
            new ImageIcon("res/sprites/oneal_dead.png").getImage(),
            new ImageIcon("res/sprites/mob_dead1.png").getImage(),
            new ImageIcon("res/sprites/mob_dead2.png").getImage(),
            new ImageIcon("res/sprites/mob_dead3.png").getImage()
    };

    public Oneal(int x, int y, Image[] moveImages, Image[] deadImages) {
        super(x, y, moveImages, deadImages);
    }

    public Oneal(int x, int y) {
        super(x,y,OnealMoveImages, OnealDeadImages);
    }

    @Override
    public void move(Manage manage) {
        countMove ++;
       // if (speed == 2) countMove %=3;
       // else
            countMove %= 2;
        if (countMove > 0 ) return;
        if (speed == 2 && new Random().nextInt(50) == 0)  speed = 1;
        if (speed == 1 && new Random().nextInt(200) == 0) speed = 2;
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
        }
        Entity downEntity = manage.staticEntities[row + 1][col];
        if (!downEntity.isBlock()) {
            targets.add(downEntity);
        }
        Entity leftEntity = manage.staticEntities[row][col - 1];
        if (!leftEntity.isBlock()) {
            targets.add(leftEntity);
        }
        Entity rightEntity = manage.staticEntities[row][col + 1];
        if (!rightEntity.isBlock()) {
            targets.add(rightEntity);
        }

        if (targets.size() == 0) {
            targetX = 0;
            targetY = 0;
            return;
        }
        int minDistance = 10000;
        Entity bestEntity = targets.get(0);
        for (Entity entity : targets)
        {
            int dis = Math.abs(manage.getBomber().getX() - entity.getX()) + Math.abs(manage.getBomber().getY()  - entity.getY());
            if (minDistance > dis ){
                minDistance = dis;
                bestEntity = entity;
            }
        }
        targetX = bestEntity.getX();
        targetY = bestEntity.getY();
    }
}
