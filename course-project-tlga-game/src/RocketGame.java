import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.FontStyle;
import java.awt.Color;


/**
 * The Rocket Game Authors: Lia, Gina, Tina References: Precepor Tim, Cora, Myles, Jacqueline and
 * Professor Joslenne Pena
 */

/**
 * This is Rockect Game, where the player controls a rocket traveling through space. When the game
 * starts, the background starts moving, the player's rocket sets to the center of the bottom
 * screen, and it starts shooting automatically. Random numbers of enemy rockets fall down from the
 * top and travel to the bottom of the canvas at random speeds in a straight line. The player can
 * either aim to shoot at the enemies or avoid getting hit by the enemies. The end goal of this game
 * is to survive as long as possible and hit as many enemies as possible without crashing into any
 * of the enemy rockets.
 */
public class RocketGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private static final Color Background_COLOR = new Color(188, 217, 255);
    private CanvasWindow canvas;
    private Rocket rocket;
    private EnemyManager enemyManager;
    private AmmoManager ammoManager;
    private Explosion explosion;
    private int loop = 0;
    private boolean Exploded;
    private double explosionX;
    private double explosionY;

    private GraphicsText score;
    private GraphicsText gameOver;
    private GraphicsText finalScore;

    private Background ground;
    public static final Color gray = new Color(248, 248, 248);

    /**
     * This is the constructor of the game, where it creates and sets the background color, adds a
     * moving background, and adds the player rocket, enemy rockest, ammos, and explosion to the canvas.
     * It also set up the graphicTexts for all the labels in the game.
     */
    public RocketGame() {
        canvas = new CanvasWindow("Rocket Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Background_COLOR);

        ground = new Background();
        canvas.setBackground(gray);
        ground.add(canvas);

        rocket = new Rocket(canvas);
        rocket.setCenter(CANVAS_WIDTH / 2, 700);

        enemyManager = new EnemyManager(canvas);
        ammoManager = new AmmoManager(canvas, rocket, enemyManager);

        Exploded = false;
        explosionX = 0;
        explosionY = 0;
        explosion = new Explosion(canvas);

        gameOver = new GraphicsText();
        gameOver.setAlignment(TextAlignment.CENTER);
        gameOver.setFillColor(gray);
        gameOver.setStrokeColor(Color.BLACK);
        gameOver.setStrokeWidth(2);
        gameOver.setCenter(CANVAS_WIDTH * 0.5, CANVAS_HEIGHT / 2);
        gameOver.setFont(FontStyle.BOLD_ITALIC, 40);

        finalScore = new GraphicsText();
        finalScore.setAlignment(TextAlignment.CENTER);
        finalScore.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT * 0.55);
        finalScore.setFont(FontStyle.ITALIC, 22);
        canvas.add(finalScore);

        score = new GraphicsText();
        score.setAlignment(TextAlignment.CENTER);
        score.setCenter(CANVAS_WIDTH * 0.9, 760);
        score.setFont(FontStyle.BOLD, 16);
        canvas.add(score);
    }

    /**
     * This method checks the collision between the player rocket and the enemy rocket to determine if
     * the game is over or not. If there is no collision, the game continues to run and keep track of
     * the score for each enemy the player destroys. If there is collision, this method then checks of
     * if the explosion animation is added to the canvas. If no explosion yet, it adds the image of the
     * explosion to the current location of the rocket and calls gameOver.
     */
    public void update() {
        canvas.animate(() -> {
            if (!enemyManager.checkEnemyCollision()) {
                run();
                score.setText("Score: " + ammoManager.getScore() * 10);
            } else if (enemyManager.checkEnemyCollision()) {
                if (!Exploded) {
                    Exploded = true;
                    explosionX = rocket.getRocketX();
                    explosionY = rocket.getRocketY();
                }
                gameOver();
            }
        });
    }


    /**
     * It sets the order of actions once the player loses. Once the game is over, the rocket will be
     * removed from the canvas, and the image of explosion and text will be added to the canvas. After
     * 45 frames, the canvas will remove all the elements on the screen and add a new moving background
     * with the "game over" text and total score count to the end screen. After staying on the end
     * screen for another 155 frames, the canvsa will close itself.
     */
    public void gameOver() {
        if (loop == 0) {
            rocket.remove(canvas);
            loop++;
        } else if (loop == 5) {
            explosion.setCenter(canvas, explosionX, explosionY);
            explosion.addToCanvas(canvas);
            gameOver.setText("GAMEOVER");
            canvas.add(gameOver);
            loop++;
        } else if (loop == 45) {
            loop++;
            canvas.removeAll();
            ground = new Background();
            ground.add(canvas);
            gameOver.setText("GAMEOVER");
            canvas.add(gameOver);
            finalScore.setText("Final Score: " + ammoManager.getScore() * 10);
            canvas.add(finalScore);
        } else if (loop == 200) {
            canvas.closeWindow();
        } else {
            ground.move();
            loop++;
        }
    }

    /**
     * This method calls for all the necessary methods from other class to run the game as long as the
     * player does not crash into the enemy rockets.
     */
    public void run() {
        ground.move();
        enemyManager.spawnEnemy();
        enemyManager.enemyOutOfBound();
        ammoManager.generateAmmo();
        ammoManager.moveAmmo();
        enemyManager.crashRocket(rocket);
    }

    /**
     * This makes the rocket move within the bound of the canvas.
     */
    public void rocketMove() {
        canvas.onMouseMove(event -> {
            if (event.getPosition().getX() >= CANVAS_WIDTH - 55 && event.getPosition().getY() <= 80) {
                // top right
                rocket.setCenter(CANVAS_WIDTH - 50, 75);
            } else if (event.getPosition().getX() <= 55 && event.getPosition().getY() <= 80) {
                // top left
                rocket.setCenter(50, 75);
            } else if (event.getPosition().getX() <= 55
                && event.getPosition().getY() >= CANVAS_HEIGHT - 80) {
                // bottom left
                rocket.setCenter(50, CANVAS_HEIGHT - 75);
            } else if (event.getPosition().getX() >= CANVAS_WIDTH - 55
                && event.getPosition().getY() >= CANVAS_HEIGHT - 80) {
                // bottom right
                rocket.setCenter(CANVAS_WIDTH - 50, CANVAS_HEIGHT - 75);
            } else if (event.getPosition().getX() >= CANVAS_WIDTH - 55) {
                // left
                rocket.setCenter(CANVAS_WIDTH - 50, event.getPosition().getY());
            } else if (event.getPosition().getY() <= 80) {
                // top
                rocket.setCenter(event.getPosition().getX(), 75);
            } else if (event.getPosition().getY() >= CANVAS_HEIGHT - 80) {
                // bottom
                rocket.setCenter(event.getPosition().getX(), CANVAS_HEIGHT - 75);
            } else if (event.getPosition().getX() <= 55) {
                // right
                rocket.setCenter(50, event.getPosition().getY());
            } else {
                rocket.setCenter(event.getPosition().getX(), event.getPosition().getY());
            }
        });
    }


    public static void main(String[] args) {
        RocketGame game = new RocketGame();
        game.update();
        game.rocketMove();
    }
}