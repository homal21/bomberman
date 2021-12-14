import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bomb extends Entity {
    public static Image[] bombImages = {
            new ImageIcon("res/sprites/bomb.png").getImage(),
            new ImageIcon("res/sprites/bomb_1.png").getImage(),
            new ImageIcon("res/sprites/bomb_2.png").getImage()

    };
    public static Image[] explodeImages = {
            new ImageIcon("res/sprites/bomb_exploded.png").getImage(),
            new ImageIcon("res/sprites/bomb_exploded1.png").getImage(),
            new ImageIcon("res/sprites/bomb_exploded2.png").getImage()
    };
    public static Image[] horizontalImages = {
            new ImageIcon("res/sprites/explosion_horizontal.png").getImage(),
            new ImageIcon("res/sprites/explosion_horizontal1.png").getImage(),
            new ImageIcon("res/sprites/explosion_horizontal2.png").getImage()
    };
    public static Image[] verticalImages = {
            new ImageIcon("res/sprites/explosion_vertical.png").getImage(),
            new ImageIcon("res/sprites/explosion_vertical1.png").getImage(),
            new ImageIcon("res/sprites/explosion_vertical2.png").getImage()
    };
    public static Image[] topImages = {
            new ImageIcon("res/sprites/explosion_vertical_top_last.png").getImage(),
            new ImageIcon("res/sprites/explosion_vertical_top_last1.png").getImage(),
            new ImageIcon("res/sprites/explosion_vertical_top_last2.png").getImage()

    };
    public static Image[] bottomImages = {
            new ImageIcon("res/sprites/explosion_vertical_down_last.png").getImage(),
            new ImageIcon("res/sprites/explosion_vertical_down_last1.png").getImage(),
            new ImageIcon("res/sprites/explosion_vertical_down_last2.png").getImage()

    };
    public static Image[] leftImages = {
            new ImageIcon("res/sprites/explosion_horizontal_left_last.png").getImage(),
            new ImageIcon("res/sprites/explosion_horizontal_left_last1.png").getImage(),
            new ImageIcon("res/sprites/explosion_horizontal_left_last2.png").getImage()

    };
    public static Image[] rightImages = {
            new ImageIcon("res/sprites/explosion_horizontal_right_last.png").getImage(),
            new ImageIcon("res/sprites/explosion_horizontal_right_last1.png").getImage(),
            new ImageIcon("res/sprites/explosion_horizontal_right_last2.png").getImage(),
    };
    public int size;
    public long createTime;
    public boolean isExploding = false;
    public boolean isRunning = true;
    public int count = 0;
    public int index = 0;
    public ArrayList<Flame> flames = new ArrayList<>();
    public Bomb(int x, int y, int size) {
        super(x,y, new ImageIcon("res/sprites/bomb.png").getImage());
        this.size = size;
        createTime = System.currentTimeMillis();
    }

    @Override
    public void show(Graphics2D graphics) {
        if (isExploding) {
            for (Flame flame : flames) {
                flame.setImg(flame.images[index]);
                flame.show(graphics);
            }
        }
        else {
            setImg(bombImages[index]);
            super.show(graphics);
        }

    }

    public void run(Manage manage) {
        if (isExploding) {
            Explode(manage);
            return;
        }

        if (System.currentTimeMillis() - createTime >= 2000) {
            startExplode(manage);
            isExploding = true;
        }
        count ++; count %= 20;
        if (count > 0 ) return;
        index ++; index %= 3;
    }

    void Explode(Manage manage) {
        count ++;count %= 20;
        if (count > 0) return;
        count = 0;
        index ++;
        if (index == 3) {
            index = 2;
            isRunning = false;
            return;
        }

    }

    void startExplode(Manage manage) {
        count = 0;
        index = 0;
        isExploding = true;
        int row = (y + Entity.SIZE/2) / Entity.SIZE;
        int col = (x + Entity.SIZE/2) / Entity.SIZE;
        flames.add(new Flame(col * Entity.SIZE, row * Entity.SIZE, explodeImages));
        for (int j = col + 1; j <= col + size; j++) {
            if (manage.staticEntities[row][j] instanceof Wall) break;
            if (manage.staticEntities[row][j] instanceof Brick) {
                ((Brick) manage.staticEntities[row][j]).isExploding = true;
                break;
            }
            if (j - col < size) flames.add(new Flame(j * Entity.SIZE, row * Entity.SIZE, horizontalImages));
            else flames.add(new Flame(j * Entity.SIZE, row * Entity.SIZE, rightImages));
        }

        for (int j = col - 1; j >= col - size; j--) {
            if (manage.staticEntities[row][j] instanceof Wall) break;
            if (manage.staticEntities[row][j] instanceof Brick) {
                ((Brick) manage.staticEntities[row][j]).isExploding = true;
                break;
            }
            if (col - j < size) flames.add(new Flame(j * Entity.SIZE, row * Entity.SIZE, horizontalImages));
            else flames.add(new Flame(j * Entity.SIZE, row * Entity.SIZE, leftImages));
        }

        for (int i = row + 1; i <= row + size; i++) {
            if (manage.staticEntities[i][col] instanceof Wall) break;
            if (manage.staticEntities[i][col] instanceof Brick) {
                ((Brick) manage.staticEntities[i][col]).isExploding = true;
                break;
            }
            if (i - row < size) flames.add(new Flame(col * Entity.SIZE, i * Entity.SIZE, verticalImages));
            else flames.add(new Flame(col * Entity.SIZE, i * Entity.SIZE, bottomImages));
        }

        for (int i = row - 1; i >= row - size; i--) {
            if (manage.staticEntities[i][col] instanceof Wall) break;
            if (manage.staticEntities[i][col] instanceof Brick) {
                ((Brick) manage.staticEntities[i][col]).isExploding = true;
                break;
            }
            if (row - i < size) flames.add(new Flame(col * Entity.SIZE, i * Entity.SIZE, verticalImages));
            else flames.add(new Flame(col * Entity.SIZE, i * Entity.SIZE, topImages));
        }

    }

}
