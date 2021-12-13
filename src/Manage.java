import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Manage  {
    public Entity[][] staticEntities = new Entity[50][50];
    private ArrayList<Bomb> arrBomb;
    private Bomber bomber;
    private Balloom balloom, balloom1;
    public int height;
    public int width;
    public Bomber getBomber() {
        return bomber;
    }

    public Balloom getBalloom() {
        return balloom;
    }

    public ArrayList<Bomb> getArrBomb() {
        return arrBomb;
    }

    public Manage() {
        init();
    }

    public void init() {
        bomber = new Bomber(Entity.SIZE, 2 * Entity.SIZE);
        balloom = new Balloom(Entity.SIZE * 29, Entity.SIZE * 11, Balloom.balloomMoveImages, Balloom.balloomDeadImages);
        balloom1 = new Balloom(Entity.SIZE * 15, Entity.SIZE * 11, Balloom.balloomMoveImages, Balloom.balloomDeadImages);
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res/levels/level1.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arrays = list.get(0).trim().split(" ");
        int level = Integer.parseInt(arrays[0]);
        height = Integer.parseInt(arrays[1]);
        width = Integer.parseInt(arrays[2]);
        char[][] map = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i+1).charAt(j);
            }
        }

        arrBomb = new ArrayList<Bomb>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (map[i][j]) {
                    case '#':
                        Wall wall = new Wall(j * Entity.SIZE, i * Entity.SIZE);
                        staticEntities[i][j] = wall;
                        break;
                    case '*':
                        Brick brick = new Brick(j * Entity.SIZE, i * Entity.SIZE);
                        staticEntities[i][j] = brick;
                        break;
                    default:
                        Grass grass = new Grass(j * Entity.SIZE, i * Entity.SIZE);
                        staticEntities[i][j] = grass;
                        break;
                }
            }
        }

    }

    public void drawStaticEntities(Graphics2D g) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                staticEntities[i][j].show(g);
            }
        }
    }

    public void run(Graphics2D g) {
        drawStaticEntities(g);
        balloom1.setColMove(this);
        balloom1.move(this);
        balloom1.show(g);

        balloom.setRowMove(this);
        balloom.move(this);

        balloom.show(g);
        bomber.move(this);
        bomber.show(g);



    }
}
