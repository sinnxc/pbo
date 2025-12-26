import java.awt.*;


public class Paddle {
    public final int paddleNumber;
    public int x, y;
    public final int width = 50, height = 250;
    public int score;

    public Paddle(Pong pong, int paddleNumber) {
        this.paddleNumber = paddleNumber;
        this.x = (paddleNumber == 1) ? 0 : pong.width - width;
        this.y = pong.height / 2 - this.height / 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move(boolean up) {
        int speed = 15;
        if (up) {
            y = Math.max(y - speed, 0);
        } else {
            y = Math.min(y + speed, Pong.pong.height - height);
        }
    }
}