import java.awt.event.*;
import javax.swing.*;

class Controller extends MouseAdapter {
    private Move move;
    private Background background;
    private Car selectedCar;
    private Sound sound;
    private int selectedCarX;
    private int selectedCarY;
    int cnt = 0;
    double score = 100;

    public Controller(Move move, Background background) {
        this.move = move;
        this.background = background;
        this.sound = new Sound();
    }
    public void mousePressed(MouseEvent e) {								
        int mouseX = e.getX();
        int mouseY = e.getY();
        selectedCar = move.getLocation(mouseX, mouseY);
        if (selectedCar != null) {
            selectedCarX = mouseX - selectedCar.getX() * Move.BLOCK_SIZE; 
            selectedCarY = mouseY - selectedCar.getY() * Move.BLOCK_SIZE;
            sound.soundEffect("sound/Vroom.wav");
        }
    }
    public void mouseReleased(MouseEvent e) {
        selectedCar = null;
        cnt++;
        if (move.isGoal()) {
        	RushHourGame game = (RushHourGame) SwingUtilities.getWindowAncestor(background);
            game.stopTimer();
            long durationTime = game.getDurationTime();
            score = score - (cnt / 5) - (durationTime / 3);
        	sound.soundEffect("sound/Victory.wav");
            JOptionPane.showMessageDialog(background, "축하합니다! 움직인 횟수 : " + cnt + "회, 걸린 시간: " + durationTime + "초, 점수 : " + (int)score + "점");
            
            game.showMainScreen();
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (selectedCar != null) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            int newX = (mouseX - selectedCarX) / Move.BLOCK_SIZE;
            int newY = (mouseY - selectedCarY) / Move.BLOCK_SIZE;

            move.moveCar(selectedCar, newX, newY);
            background.repaint();
        }
    }
}
