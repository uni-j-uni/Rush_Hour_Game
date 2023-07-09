import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RushHourGame extends JFrame {				// RushHourGame의 클래스를 정의하고 JFrame을 상속
    private MainScreen mainscreen;						// 게임의 메인화면을 표시하는 MainScreen 객체를 저장하는 변수
    private Move move;									// 플레이어의 마우스 드래그(차량의 움직임)을 추적하는 Move 객체를 저장하는 변수
    private Background background;						// 게임의 배경을 표시하는 Background 객체를 저장하는 변수
    private Controller controller;						// 플레이어의 모든 입력을 처리하는 Controller 객체를 저장하는 변수
    private Sound sound;								// 게임의 사운드를 재생하는 Sound 객체를 저장하는 변수
    private Map map;									// 인게임에서 차량의 위치를 정해 맵을 구현하는 Map 객체를 저장하는 변수
    private Timer timer;								// 게임의 소요시간을 측정하는 Timer 객체를 저장하는 변수
    long startTime;										// 게임 시작 시간을 저장하는 long형 변수, 타이머의 시작 시간을 나타냄
    long durationTime;									// 게임이 진행되는 시간을 저장하는 long형 변수, 타이머의 경과 시간을 나타냄 

    public RushHourGame() {								// RushHourGame 클래스의 기본 생성자
        setTitle("Rush Hour Game");						// 메인 Frame의 타이틀을 설정

        sound = new Sound();							// Sound 클래스의 인스턴스를 생성하여 sound 객체 변수에 할당, 초기화
        sound.startBackgroundMusic();					// 배경음악을 재생하는 메소드를 호출

        mainscreen = new MainScreen(this);				// 메인화면을 MainScreen 클래스에 대해 초기화
        showMainScreen();								// 프레임에 메인화면을 추가
        setResizable(false);							// 게임 창의 크기 조절이 불가능하도록 설정
        setVisible(true);								// 프레임을 화면에 보이도록 표시
    }
    public void showMainScreen() {						// 메인화면을 보여주는 메소드
        getContentPane().removeAll();					// 기존에 존재하면 ContentPane의 내용들을 모두 제거
        add(mainscreen);								// 프레임에 메인화면을 추가
        setSize(1200, 700);								// 프레임 창의 크기를 1200px, 700px로 설정
        setLocationRelativeTo(null);					// 창의 위치를 화면의 중앙으로 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 창을 닫을경우 프레임을 종료시킴
        revalidate();									// 화면을 다시 그림
    }
    public void startGame(int level) {					// 게임을 시작하는 메소드
        remove(mainscreen);								// 게임 패널을 표시하기 위해 메인화면 제거
        move = new Move();								// Move 클래스의 인스턴스를 생성하여 move 객체 변수에 할당, 초기화
        switch (level) {								// 변수 level의 값에 따라 맵을 선택
            case 1:	map.map1(move);	break;				// level이 1일 경우 map1에 move클래스를 전달하고 맵을 초기화
            case 2: map.map2(move); break;
            case 3: map.map3(move); break;
            case 4: map.map4(move); break;
            case 5: map.map5(move); break;
            case 6: map.map6(move); break;
            case 7: map.map7(move); break;
            case 8: map.map8(move); break;
            case 9: map.map9(move); break;
        }
        background = new Background(move);				// Background 클래스의 인스턴스를 생성하여 background 객체 변수에 할당, 생성자로 move 객체를 전달하여 초기화 
        controller = new Controller(move, background);	// Controller 클래스의 인스턴스를 생성하여 controller 객체 변수에 할당, 생성자로 move, background 객체를 전달하여 초기화 
        background.addMouseListener(controller);		// background 메소드에 마우스 이벤트 리스너 추가
        background.addMouseMotionListener(controller);	// background 메소드에 마우스 모션 이벤트 리스너 추가

        setTitle("Rush Hour Game");						// 게임 패널의 타이틀을 Rush Hour Game으로 설정
        setLayout(new BorderLayout());					// 게임 창의 레이아웃 설정을 BorderLayout으로 설정
        add(background, BorderLayout.CENTER);			// background 메소드를 레이아웃의 중앙에 추가

        pack();											// 프레임의 크기를 자동으로 조절
        setLocationRelativeTo(null);					// 게임 창의 크기를 화면의 중앙으로 설정
        setResizable(false);							// 게임 창의 크기 조절이 불가능하도록 설정
        setVisible(true);								// 프레임을 화면에 보이도록 표시

        startTimer();									// 타이머를 시작하기 위한 startTimer 메소드 호출
    }
    
    public void startTimer() {							// 게임 시작시 타이머를 재생하는 메소드
        startTime = System.currentTimeMillis();			// startTime 변수를 현재 시스템의 시간으로 초기화
        durationTime = 0;								// 경과 시간 변수를 0으로 초기화

        timer = new Timer(1000, new ActionListener() {	// Timer 객체를 생성하여 1초마다 아래의 코드를 실행
            public void actionPerformed(ActionEvent e) {	// 액션이벤트 리스너 메소드
                durationTime = (System.currentTimeMillis() - startTime) / 1000;// 경과시간 변수에 현재시간에서 시작시간을 빼고 1000으로 나눈 값(시작부터의 경과시간)으로 초기화하여 경과시간 값을 업데이트
            }
        });
        timer.start();									// 타이머를 시작
    }
    public void stopTimer() { if (timer != null) { timer.stop(); } }// 타이머를 종료하기 위한 stopTimer 메소드 호출 timer 변수가 null이 아닐 경우(timer가 작동하면) 타이머를 종료
    public long getDurationTime() { return durationTime; }	// 게임의 경과 시간을 반환하는 메소드
    public void stopBackgroundMusic() { if (sound != null) { sound.stopBackgroundMusic(); } }	// sound 객체 변수가 null이 아닐 경우 stopBackgroundMusic 메소드를 호출하여 배경음악을 정지
    public static void main(String[] args) { RushHourGame game = new RushHourGame(); }			// RushHourGame 실행
}
