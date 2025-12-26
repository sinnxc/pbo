import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Pong implements ActionListener, KeyListener {

    public static Pong pong;
    public int width = 700, height = 700;
    public Renderer renderer;
    public Paddle player1, player2;
    public Ball ball;
    public boolean bot = false, selectingDifficulty;
    public boolean w, s, up, down;
    public int gameStatus = 0, scoreLimit = 7, playerWon;
    public int botDifficulty, botMoves, botCooldown = 0;
    public Random random;
    public JFrame jframe;

    public Pong() {
        Timer timer = new Timer(20, this);
        random = new Random();
        jframe = new JFrame("Pong");
        renderer = new Renderer();
        jframe.setSize(width + 15, height + 35);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(renderer);
        jframe.addKeyListener(this);
        timer.start();
    }

    public void start() {
        gameStatus = 2;
        player1 = new Paddle(this, 1);
        player2 = new Paddle(this, 2);
        ball = new Ball(this);
    }

    public void update() {
        if (player1.score >= scoreLimit) {
            playerWon = 1;
            gameStatus = 3;
        }
        if (player2.score >= scoreLimit) {
            playerWon = 2;
            gameStatus = 3;
        }
        if (w) player1.move(true);
        if (s) player1.move(false);
        if (!bot) {
            if (up) player2.move(true);
            if (down) player2.move(false);
        } else {
            handleBotMovement();
        }
        ball.update(player1, player2);
    }

    private void handleBotMovement() {
        if (botCooldown > 0) {
            botCooldown--;
            if (botCooldown == 0) botMoves = 0;
        }
        if (botMoves < 10) {
            if (player2.y + player2.height / 2 < ball.y) {
                player2.move(false);
                botMoves++;
            }
            if (player2.y + player2.height / 2 > ball.y) {
                player2.move(true);
                botMoves++;
            }
            botCooldown = switch (botDifficulty) {
                case 0 -> 20;
                case 1 -> 15;
                default -> 10;
            };
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        switch (gameStatus) {
            case 0 -> renderMenu(g);
            case 1 -> renderPaused(g);
            case 2 -> renderGame(g);
            case 3 -> renderGameOver(g);
        }
    }

    private void renderMenu(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("PONG", width / 2 - 75, 50);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        if (!selectingDifficulty) {
            g.drawString("Press Space to Play", width / 2 - 150, height / 2 - 25);
            g.drawString("Press Shift to Play with Bot", width / 2 - 200, height / 2 + 25);
            g.drawString("<< Score Limit: " + scoreLimit + " >>", width / 2 - 150, height / 2 + 75);
        } else {
            String difficulty = switch (botDifficulty) {
                case 0 -> "Easy";
                case 1 -> "Medium";
                default -> "Hard";
            };
            g.drawString("<< Bot Difficulty: " + difficulty + " >>", width / 2 - 180, height / 2 - 25);
            g.drawString("Press Space to Play", width / 2 - 150, height / 2 + 25);
        }
    }

    private void renderPaused(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("PAUSED", width / 2 - 103, height / 2 - 25);
    }

    private void renderGame(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(5f));
        g.drawLine(width / 2, 0, width / 2, height);
        g.setStroke(new BasicStroke(2f));
        g.drawOval(width / 2 - 150, height / 2 - 150, 300, 300);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(String.valueOf(player1.score), width / 2 - 90, 50);
        g.drawString(String.valueOf(player2.score), width / 2 + 65, 50);
        player1.render(g);
        player2.render(g);
        ball.render(g);
    }

    private void renderGameOver(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("PONG", width / 2 - 75, 50);
        String winnerMessage = bot && playerWon == 2 ? "The Bot Wins!" : "Player " + playerWon + " Wins!";
        g.drawString(winnerMessage, width / 2 - 170, 200);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Press Space to Play Again", width / 2 - 185, height / 2 - 25);
        g.drawString("Press ESC for Menu", width / 2 - 140, height / 2 + 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus == 2) update();
        renderer.repaint();
    }

    public static void main(String[] args) {
        pong = new Pong();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        switch (id) {
            case KeyEvent.VK_W -> w = true;
            case KeyEvent.VK_S -> s = true;
            case KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_DOWN -> down = true;
            case KeyEvent.VK_RIGHT -> handleRightKey();
            case KeyEvent.VK_LEFT -> handleLeftKey();
            case KeyEvent.VK_ESCAPE -> {
                if (gameStatus == 2 || gameStatus == 3) gameStatus = 0;
            }
            case KeyEvent.VK_SHIFT -> {
                if (gameStatus == 0) {
                    bot = true;
                    selectingDifficulty = true;
                }
            }
            case KeyEvent.VK_SPACE -> handleSpaceKey();
        }
    }

    private void handleRightKey() {
        if (selectingDifficulty) {
            botDifficulty = (botDifficulty < 2) ? botDifficulty + 1 : 0;
        } else if (gameStatus == 0) {
            scoreLimit++;
        }
    }

    private void handleLeftKey() {
        if (selectingDifficulty) {
            botDifficulty = (botDifficulty > 0) ? botDifficulty - 1 : 2;
        } else if (gameStatus == 0 && scoreLimit > 1) {
            scoreLimit--;
        }
    }

    private void handleSpaceKey() {
        if (gameStatus == 0 || gameStatus == 3) {
            if (!selectingDifficulty) {
                bot = false;
            } else {
                selectingDifficulty = false;
            }
            start();
        } else if (gameStatus == 1) {
            gameStatus = 2;
        } else if (gameStatus == 2) {
            gameStatus = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        switch (id) {
            case KeyEvent.VK_W -> w = false;
            case KeyEvent.VK_S -> s = false;
            case KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_DOWN -> down = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}