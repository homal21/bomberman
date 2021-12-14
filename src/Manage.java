import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Manage  {
    public Entity[][] staticEntities = new Entity[50][50];
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Bomb> bombs = new ArrayList<>();
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


    public Manage() {
        init();
    }

    public void init() {
        bomber = new Bomber(Entity.SIZE, Entity.SIZE);
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
                    case '1':
                        enemies.add(new Balloom(j * Entity.SIZE, i * Entity.SIZE));
                        staticEntities[i][j] =  new Grass(j * Entity.SIZE, i * Entity.SIZE);
                        break;
                    case '2':
                        enemies.add(new Oneal(j * Entity.SIZE, i * Entity.SIZE));
                        staticEntities[i][j] =  new Grass(j * Entity.SIZE, i * Entity.SIZE);
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
                if (staticEntities[i][j] instanceof  Brick) {
                    if (((Brick) staticEntities[i][j]).isDestroyed) {
                        staticEntities[i][j]= new Grass(j * Entity.SIZE, i * Entity.SIZE);
                        continue;
                    }
                    if (((Brick) staticEntities[i][j]).isExploding) {
                        new Grass(j * Entity.SIZE, i * Entity.SIZE).show(g);
                        ((Brick) staticEntities[i][j]).explode();
                    }
                }
                if (staticEntities[i][j] instanceof  Bomb) {
                    if (!((Bomb) staticEntities[i][j]).isRunning) {
                        staticEntities[i][j]= new Grass(j * Entity.SIZE, i * Entity.SIZE);
                    }
                    new Grass(j * Entity.SIZE, i * Entity.SIZE).show(g);
                }
                staticEntities[i][j].show(g);
            }
        }
    }
    void addNewBomb() {
        if (bombs.size() == bomber.bombCapacity) return;
        int row = (bomber.y + Entity.SIZE/2) / Entity.SIZE;
        int col = (bomber.x + Entity.SIZE/2) / Entity.SIZE;
        if (staticEntities[row][col] instanceof Bomb) return;
        Bomb bomb = new Bomb(col * Entity.SIZE, row * Entity.SIZE, bomber.bombSize);
        staticEntities[row][col] = bomb;
        bombs.add(bomb);
    }
    public void run(Graphics2D g) {
        drawStaticEntities(g);

        bombs.removeIf(bomb -> !bomb.isRunning);
        for (Bomb bomb : bombs) {
            bomb.run(this);
        }
        for (Bomb bomb : bombs) {
            bomb.show(g);
        }

        enemies.removeIf(enemy -> (!enemy.isAlive && !enemy.isDying));
        for (Enemy enemy : enemies) {
            if (enemy.isDying) {
                enemy.die();
            }
            else
                if (enemy.isTouchFlame(this)) {
                    enemy.startDie();
                    continue;
                }
                else {

                    enemy.move(this);
                }
            enemy.show(g);
        }

        bomber.move(this);
        bomber.show(g);



    }
}
