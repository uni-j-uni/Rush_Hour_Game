import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class Background extends JPanel {
    private static final int MAP_SIZE = 6;
    private static final int BLOCK_SIZE = 100;

    private Move move;
    private Image[][] backgroundImages;

    public Background(Move move) {
        this.move = move;
        setPreferredSize(new Dimension(MAP_SIZE * BLOCK_SIZE, MAP_SIZE * BLOCK_SIZE));

        backgroundImages = new Image[MAP_SIZE][MAP_SIZE];
        try {
            for (int i = 0; i < MAP_SIZE; i++) {
	                for (int j = 0; j < MAP_SIZE; j++) {
                    backgroundImages[i][j] = ImageIO.read(new File("images/Road.png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int row = getY() / BLOCK_SIZE;
        int col = getX() / BLOCK_SIZE;

        Image backgroundImage = backgroundImages[row][col];
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, backgroundImages[row][col].getWidth(null), backgroundImages[row][col].getHeight(null), this);
        }

        Car[] car = move.makeCars();

        g.setColor(Color.BLACK);
        for (int i = 0; i <= Move.MAP_SIZE; i++) {
            g.drawLine(i * Move.BLOCK_SIZE, 0, i * Move.BLOCK_SIZE, getHeight());
            g.drawLine(0, i * Move.BLOCK_SIZE, getWidth(), i * Move.BLOCK_SIZE);
        }

        for (Car Car : car) {
            int x = Car.getX() * Move.BLOCK_SIZE;
            int y = Car.getY() * Move.BLOCK_SIZE;
            int width = Car.carWidth() * Move.BLOCK_SIZE;
            int height = Car.carHeight() * Move.BLOCK_SIZE;

            Image image = Car.getImage();
            if (image != null) {
                g.drawImage(image, x, y, width, height, this);
            }
        }
    }
}
