import javax.swing.*;
import java.awt.*;

public class Bomber extends MoveEntity {
    public static Image[] bomberMoveImages = {
            new ImageIcon("res/sprites/player_right.png").getImage(),
            new ImageIcon("res/sprites/player_right_1.png").getImage(),
            new ImageIcon("res/sprites/player_right_2.png").getImage(),
            new ImageIcon("res/sprites/player_left.png").getImage(),
            new ImageIcon("res/sprites/player_left_1.png").getImage(),
            new ImageIcon("res/sprites/player_left_2.png").getImage(),
            new ImageIcon("res/sprites/player_down.png").getImage(),
            new ImageIcon("res/sprites/player_down_1.png").getImage(),
            new ImageIcon("res/sprites/player_down_2.png").getImage(),
            new ImageIcon("res/sprites/player_up.png").getImage(),
            new ImageIcon("res/sprites/player_up_1.png").getImage(),
            new ImageIcon("res/sprites/player_up_2.png").getImage()
    };
    public static Image[] bomberDeadImages = {
            new ImageIcon("res/sprites/player_dead1.png").getImage(),
            new ImageIcon("res/sprites/player_dead2.png").getImage(),
            new ImageIcon("res/sprites/player_dead3.png").getImage(),
            new ImageIcon("res/sprites/transparent.png").getImage()
    };
    public int bombSize = 1;
    public int bombCapacity = 1;
    public Bomber(int x, int y) {
        super(x, y, bomberMoveImages, bomberDeadImages);
    }

    public boolean isTouchEnemy(Manage manage) {
        for (Enemy enemy : manage.enemies) {
            if (enemy.isAlive && Math.abs(getX() - enemy.getX()) <= Entity.SIZE - 5
                    && Math.abs(getY() - enemy.getY()) <= Entity.SIZE - 5) {
                return true;
            }
        }
        return false;
    }

    public void checkConflictItem(Manage manage) {
        int row = (getY() + Entity.SIZE/2) / Entity.SIZE;
        int col = (getX() + Entity.SIZE/2) / Entity.SIZE;
        if (getX() >= Entity.SIZE * col + 5) col++;
        if (getX() <= Entity.SIZE * col - 5) col--;
        if (getY() >= Entity.SIZE * row + 5) row++;
        if (getY() <= Entity.SIZE * row - 5) row--;
        if (manage.staticEntities[row][col] instanceof Item) {
            ((Item) manage.staticEntities[row][col]).upgrade(this);
            manage.staticEntities[row][col] = new Grass(col * Entity.SIZE, row * Entity.SIZE);
            sounds.Sound.play("sounds/item.wav");
        }
    }

    @Override
    public void move(Manage manage) {
        if (!isAlive) {
            die();
            return;
        }
        countMove ++; countMove %=2;
        if (countMove > 0) return;
        if (orient == RIGHT) super.moveRight(manage);
        if (orient == LEFT) super.moveLeft(manage);
        if (orient == DOWN) super.moveDown(manage);
        if (orient == UP) super.moveUp(manage);
        if (isTouchEnemy(manage) || isTouchFlame(manage)) {
            startDie();
            return;
        }
        checkConflictItem(manage);
    }

}
