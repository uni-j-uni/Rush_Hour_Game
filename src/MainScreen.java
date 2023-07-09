import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class MainScreen extends JPanel {
        private Image MainImage;
        private Sound sound;

        public MainScreen(RushHourGame game) {
        	sound = new Sound();
            try {
                MainImage = ImageIO.read(new File("images/Main.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setLayout(null);
            
            ImageIcon icon = new ImageIcon("images/StartButton.png");
            
            JButton startbutton = new JButton();
            startbutton.setBounds(890, 20, 300, 148);
            startbutton.setIcon(icon);  // 아이콘 설정
            startbutton.setOpaque(false);  // 버튼의 배경을 투명하게 설정
            startbutton.setContentAreaFilled(false);  // 버튼의 내용 영역 채우기 비활성화
            startbutton.setBorderPainted(false);
            startbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sound.soundEffect("sound/Enter.wav");
                    remove(startbutton); // 시작 버튼 제거
                    LevelButtons(); // 새로운 버튼 생성
                    repaint();  // 패널 다시 그리기
                }
            });
            add(startbutton);
        }

        private void LevelButtons() {
            int ButtonWidth = 200;
            int ButtonHeight = 62;
            int gap = 30;
            int rows = 3;
            int cols = 3;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                	ImageIcon icon = new ImageIcon("images/Level/" + (row * cols + col + 1) + ".png");
                	
                    JButton levelbutton = new JButton();
                    levelbutton.setBounds(1100 - (ButtonWidth + gap) * (cols - col), 350 + (ButtonHeight + gap) * row, ButtonWidth, ButtonHeight);
                    levelbutton.setIcon(icon);  // 아이콘 설정
                    levelbutton.setOpaque(false);  // 버튼의 배경을 투명하게 설정
                    levelbutton.setContentAreaFilled(false);  // 버튼의 내용 영역 채우기 비활성화
                    levelbutton.setBorderPainted(false);
                    final int level = row * cols + col + 1;
                    
                    levelbutton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            sound.soundEffect("sound/Enter.wav");
                            RushHourGame game = (RushHourGame) SwingUtilities.getWindowAncestor(MainScreen.this);
                            remove(levelbutton);
                            game.startGame(level);
                            game.startTimer();
                        }
                    });
                    add(levelbutton);
                }
            }
            revalidate(); // 레이아웃 업데이트
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (MainImage != null) {
                g.drawImage(MainImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
}