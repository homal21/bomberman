import javax.swing.*;
import java.awt.*;

public class Bomber extends MoveEntity {
    public static Image[] bomberMoveImages = {
            new ImageIcon("boombermann1/res/sprites/player_right.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_right_1.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_right_2.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_left.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_left_1.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_left_2.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_down.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_down_1.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_down_2.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_up.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_up_1.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_up_2.png").getImage()
    };
    public static Image[] bomberDeadImages = {
            new ImageIcon("boombermann1/res/sprites/player_dead1.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_dead2.png").getImage(),
            new ImageIcon("boombermann1/res/sprites/player_dead3.png").getImage()
    };

    public Bomber(int x, int y) {
        super(x, y, bomberMoveImages, bomberDeadImages);
    }
    @Override
    public void move(Manage manage) {
        if (orient == RIGHT) super.moveRight(manage);
        if (orient == LEFT) super.moveLeft(manage);
        if (orient == DOWN) super.moveDown(manage);
        if (orient == UP) super.moveUp(manage);
    }

}
