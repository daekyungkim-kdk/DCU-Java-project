import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Main extends JFrame {
    // 게임 데이터 변수
    private int score = 0;
    private int life = 3;
    private int highScore = 0;

    // GUI 컴포넌트
    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private JLabel highScoreLabel;
    private JPanel gamePanel;
    private JTextField inputField;

    // 게임 관리를 위한 변수들
    private final String[] wordBank = {"java", "class", "object", "method", "variable", "string", "array", "loop", "thread", "interface", "extends", "public"};
    private final ArrayList<JLabel> activeWords = new ArrayList<>(); // 현재 화면에 떨어지고 있는 단어들
    private Timer spawnTimer; // 단어를 생성하는 타이머
    private Timer moveTimer;  // 단어를 떨어뜨리는 타이머
    private final Random random = new Random();

    private static final String SCORE_FILE = "score.txt";

    public Main() {
        // 1. 최고 점수 불러오기
        loadHighScore();

        // 2. 메인 창(JFrame) 기본 설정
        setTitle("자바 산성비 타이핑 게임");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 3. 상단 레이블 패널 (점수, 목숨, 최고점수 표시)
        JPanel statusPanel = new JPanel(new GridLayout(1, 3));
        statusPanel.setBackground(Color.LIGHT_GRAY);
        statusPanel.setPreferredSize(new Dimension(600, 50));

        scoreLabel = new JLabel("현재 점수: 0", SwingConstants.CENTER);
        lifeLabel = new JLabel("❤️ 목숨: 3", SwingConstants.CENTER);
        highScoreLabel = new JLabel("🏆 최고 점수: " + highScore, SwingConstants.CENTER);

        Font statusFont = new Font("맑은 고딕", Font.BOLD, 16);
        scoreLabel.setFont(statusFont);
        lifeLabel.setFont(statusFont);
        highScoreLabel.setFont(statusFont);

        statusPanel.add(scoreLabel);
        statusPanel.add(lifeLabel);
        statusPanel.add(highScoreLabel);
        add(statusPanel, BorderLayout.NORTH);

        // 4. 중앙 패널 (단어가 떨어지는 영역)
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setLayout(null);
        add(gamePanel, BorderLayout.CENTER);

        // 5. 하단 패널 (텍스트 입력창)
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.GRAY);
        inputPanel.setPreferredSize(new Dimension(600, 60));

        inputField = new JTextField(25);
        inputField.setFont(new Font("맑은 고딕", Font.PLAIN, 18));

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputField.getText().trim();
                if (!text.isEmpty()) {
                    checkWord(text); // 입력 단어 검사
                }
                inputField.setText("");
            }
        });

        inputPanel.add(inputField);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);

        // 6. 게임 시작 (타이머 가동)
        startGame();
    }

    // 게임 시작 및 타이머 설정
    private void startGame() {
        // 2.5초마다 새로운 단어 생성
        spawnTimer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnWord();
            }
        });

        // 0.1초(100ms)마다 단어들을 아래로 이동
        moveTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveWords();
            }
        });

        spawnTimer.start();
        moveTimer.start();
    }

    // 단어 랜덤 생성
    private void spawnWord() {
        String randomWord = wordBank[random.nextInt(wordBank.length)];
        JLabel wordLabel = new JLabel(randomWord);
        wordLabel.setForeground(Color.WHITE);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // 단어가 창 밖으로 나가지 않도록 랜덤 X 좌표 설정 (가로 600 기준 여유있게 50~450)
        int x = random.nextInt(400) + 50;
        int y = 0;
        wordLabel.setBounds(x, y, 150, 30);

        gamePanel.add(wordLabel);
        activeWords.add(wordLabel);
        gamePanel.repaint();
    }

    // 단어 하강 및 점수별 속도 증가
    private void moveWords() {
        // 기본 속도 5에서 시작해서, 점수 20점 오를 때마다 낙하 속도가 1씩 누적 증가
        int speed = 5 + (score / 20);

        ArrayList<JLabel> toRemove = new ArrayList<>();

        for (JLabel label : activeWords) {
            int currentX = label.getX();
            int currentY = label.getY() + speed; // 속도만큼 Y좌표 아래로
            label.setLocation(currentX, currentY);

            // 바닥에 닿았을 때
            if (currentY > gamePanel.getHeight() - 20) {
                toRemove.add(label);
                decreaseLife(); // 바닥에 놓치면 목숨 감소
            }
        }

        // 바닥에 닿아 만료된 단어들은 화면과 리스트에서 제거
        for (JLabel label : toRemove) {
            gamePanel.remove(label);
            activeWords.remove(label);
        }
        gamePanel.repaint();
    }

    //  입력한 단어가 맞는지 검사, 오답시 체력 감소
    private void checkWord(String text) {
        boolean matched = false;
        JLabel matchedLabel = null;

        // 현재 화면에 떠 있는 단어 중 일치하는 게 있는지 검사
        for (JLabel label : activeWords) {
            if (label.getText().equalsIgnoreCase(text)) {
                matchedLabel = label;
                matched = true;
                break;
            }
        }

        if (matched) {
            // 단어를 맞춘 경우: 점수 10점 추가 및 단어 삭제
            gamePanel.remove(matchedLabel);
            activeWords.remove(matchedLabel);
            score += 10;
            scoreLabel.setText("현재 점수: " + score);
            gamePanel.repaint();
        } else {
            // 틀린 단어를 입력한 경우: 목숨 감소!
            decreaseLife();
        }
    }

    // 목숨 감소 메서드
    public synchronized void decreaseLife() {
        if (life > 0) {
            life--;
            lifeLabel.setText("❤️ 목숨: " + life);

            if (life <= 0) {
                gameOver();
            }
        }
    }

    // 게임 오버 처리
    private void gameOver() {
        spawnTimer.stop(); // 단어 생성 중지
        moveTimer.stop();   // 단어 이동 중지

        JOptionPane.showMessageDialog(this, "게임 오버! 최종 점수: " + score + "점", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        inputField.setEnabled(false);
        saveScore(score);
    }

    //  파일에서 최고 점수 불러오기
    private void loadHighScore() {
        File file = new File(SCORE_FILE);
        if (!file.exists()) {
            highScore = 0;
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null) {
                highScore = Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            highScore = 0;
        }
    }

    //  파일에 최고 점수 기록하기
    private void saveScore(int currentScore) {
        if (currentScore > highScore) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(SCORE_FILE))) {
                bw.write(String.valueOf(currentScore));
                highScoreLabel.setText("🏆 최고 점수: " + currentScore);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}