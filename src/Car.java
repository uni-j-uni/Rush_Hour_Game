import java.awt.*;
import java.io.*;
import javax.imageio.*;

class Car {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;

    public Car(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int carWidth() { return width; }
    public int carHeight() { return height; }
    public void move(int moveX, int moveY) {
        x = moveX;
        y = moveY;
    }
    public boolean userLocation() { return getX() == 4 && getY() == 2; }
    public Rectangle makeRect() { return new Rectangle(x * Move.BLOCK_SIZE, y * Move.BLOCK_SIZE, width * Move.BLOCK_SIZE, height * Move.BLOCK_SIZE); }
    public Image getImage() { return image; }
}
