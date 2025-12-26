import java.awt.*;
import java.util.Random;

public class Ball {
    public int x, y, width = 25, height = 25;
    public int motionX, motionY;
    public Random random;
    private final Pong pong;
    public int amountOfHits;

    public Ball(Pong pong) {
        this.pong = pong;
        this.random = new Random();
        spawn();
    }

    public void update(Paddle paddle1, Paddle paddle2) {
        int speed = 5;
        this.x += motionX * speed;
        this.y += motionY * speed;

        handleWallCollision();
        handlePaddleCollision(paddle1, paddle2);
    }

    private void handleWallCollision() {
        if (this.y + height - motionY > pong.height || this.y + motionY < 0) {
            if (this.motionY < 0) {
                this.y = 0;
                this.motionY = randomizeMotionY();
            } else {
                this.motionY = -randomizeMotionY();
                this.y = pong.height - height;
            }
        }
    }

    private int randomizeMotionY() {
        int motion = random.nextInt(4);
        return (motion == 0) ? 1 : motion;
    }

    private void handlePaddleCollision(Paddle paddle1, Paddle paddle2) {
        if (checkCollision(paddle1) == 1) {
            adjustMotionAfterHit(1, paddle1);
        } else if (checkCollision(paddle2) == 1) {
            adjustMotionAfterHit(-1, paddle2);
        }

        if (checkCollision(paddle1) == 2) {
            paddle2.score++;
            spawn();
        } else if (checkCollision(paddle2) == 2) {
            paddle1.score++;
            spawn();
        }
    }

    private void adjustMotionAfterHit(int direction, Paddle paddle) {
        this.motionX = direction * (1 + (amountOfHits / 5));
        this.motionY = -2 + random.nextInt(4);
        if (motionY == 0) motionY = 1;
        amountOfHits++;
    }

    public void spawn() {
        this.amountOfHits = 0;
        this.x = pong.width / 2 - this.width / 2;
        this.y = pong.height / 2 - this.height / 2;
        this.motionY = randomizeMotionY();
        this.motionX = random.nextBoolean() ? 1 : -1;
    }

    public int checkCollision(Paddle paddle) {
        if (this.x < paddle.x + paddle.width && this.x + width > paddle.x && this.y < paddle.y + paddle.height && this.y + height > paddle.y) {
            return 1; // bounce
        } else if ((paddle.x > x && paddle.paddleNumber == 1) || (paddle.x < x - width && paddle.paddleNumber == 2)) {
            return 2; // score
        }
        return 0; // nothing
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}