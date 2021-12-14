import java.awt.*;

public abstract class MoveEntity extends Entity {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    protected int speed = 1;
    public int countChangeImage = 0;
    public int countMove = 0;
    public int index = 0;
    public boolean isAlive = true;
    public boolean isDying = false;
    protected int orient;
    public Image[] moveImages;
    public Image[] deadImages;

    public MoveEntity(int x, int y, Image[] moveImages, Image[] deadImages) {
        super(x, y, moveImages[0]);
        this.moveImages = moveImages;
        this.deadImages = deadImages;
    }

    public int getOrient() {
        return orient;
    }

    public int getSpeed() {
        return speed;
    }

    public void changeOrient(int orient) {
        this.orient = orient;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void moveRight(Manage manage) {
        countChangeImage++;
        if (countChangeImage == 5) {
            index++;
            countChangeImage = 0;
        }
        if (index > 2) index = 0;
        int row = (y + Entity.SIZE / 2) / Entity.SIZE;
        int col = (x + Entity.SIZE / 2) / Entity.SIZE;
        Entity entity = manage.staticEntities[row][col + 1];
        if (entity.isBlock()) {
            if (x + speed <= col * Entity.SIZE) x += speed;
            else setX(col * Entity.SIZE);
            return;
        }
        if (y > row * Entity.SIZE) {
            if (y - speed >= row * Entity.SIZE) y -= speed;
            else setY(row * Entity.SIZE);
            return;
        }
        if (y < row * Entity.SIZE) {
            if (y + speed <= row * Entity.SIZE) y += speed;
            else setY(row * Entity.SIZE);
            return;
        }
        x += speed;
    }

    public void moveLeft(Manage manage) {
        countChangeImage++;
        if (countChangeImage == 5) {
            index++;
            countChangeImage = 0;
        }
        if (index > 5 || index < 3) index = 3;
        int row = (y + Entity.SIZE / 2) / Entity.SIZE;
        int col = (x + Entity.SIZE / 2) / Entity.SIZE;
        Entity entity = manage.staticEntities[row][col - 1];
        if (entity.isBlock()) {
            if (x - speed >= col * Entity.SIZE) x -= speed;
            else setX(col * Entity.SIZE);
            return;
        }
        if (y > row * Entity.SIZE) {
            if (y - speed >= row * Entity.SIZE) y -= speed;
            else setY(row * Entity.SIZE);
            return;
        }
        if (y < row * Entity.SIZE) {
            if (y + speed <= row * Entity.SIZE) y += speed;
            else setY(row * Entity.SIZE);
            return;
        }
        x -= speed;
    }

    public void moveDown(Manage manage) {
        countChangeImage++;
        if (countChangeImage == 5)
        {
            index++;
            countChangeImage = 0;
        }
        if (index > 8 || index < 6) index = 6;
        int row = (y + Entity.SIZE / 2) / Entity.SIZE;
        int col = (x + Entity.SIZE / 2) / Entity.SIZE;
        Entity entity = manage.staticEntities[row + 1][col];
        if (entity.isBlock()) {
            if (y + speed <= row * Entity.SIZE) y += speed;
            else setY(row * Entity.SIZE);
            return;
        }
        if (x > col * Entity.SIZE) {
            if (x - speed >= col * Entity.SIZE) x -= speed;
            else setX(col * Entity.SIZE);
            return;
        }
        if (x < col * Entity.SIZE) {
            if (x + speed <= col * Entity.SIZE) x += speed;
            else setX(col * Entity.SIZE);
            return;
        }
        y += speed;
    }

    public void moveUp(Manage manage) {
        countChangeImage++;
        if (countChangeImage == 5)
        {
            index++;
            countChangeImage =0;
        }
        if (index > 11 || index < 9) index = 9;
        int row = (y + Entity.SIZE / 2) / Entity.SIZE;
        int col = (x + Entity.SIZE / 2) / Entity.SIZE;
        Entity entity = manage.staticEntities[row - 1][col];
        if (entity.isBlock()) {
            if (y - speed >= row * Entity.SIZE) y -= speed;
            else setY(row * Entity.SIZE);
            return;
        }
        if (x > col * Entity.SIZE) {
            if (x - speed >= col * Entity.SIZE) x -= speed;
            else setX(col * Entity.SIZE);
            return;
        }
        if (x < col * Entity.SIZE) {
            if (x + speed <= col * Entity.SIZE) x += speed;
            else setX(col * Entity.SIZE);
            return;
        }
        y -= speed;
    }

    public abstract void move(Manage manage);

    @Override
    public void show(Graphics2D graphics) {
        if (isAlive) {
            setImg(moveImages[index]);
        } else {
            setImg(deadImages[index]);
        }
        super.show(graphics);
        if (this instanceof Bomber) orient = 0;
    }

    public void die() {
        countChangeImage++;
        countChangeImage %= 30;
        if (countChangeImage == 0) {
            if (index + 1 < deadImages.length) index++;
            else isDying = false;
        }
    }

    public void startDie() {
        index = 0;
        countChangeImage = 0;
        isAlive = false;
        isDying = true;
    }

    public boolean isTouchFlame(Manage manage) {
        for (Bomb bomb : manage.bombs) {
            if (!bomb.isExploding) continue;
            for (Flame flame : bomb.flames)
                if (Math.abs(getX() - flame.getX()) <= Entity.SIZE - 10
                        && Math.abs(getY() - flame.getY()) <= Entity.SIZE - 10) {
                    return true;
                }
        }
        return false;
    }
}

