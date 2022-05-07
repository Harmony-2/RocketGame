import java.util.ArrayList;
import java.util.Random;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

public class EnemyManager {
    private CanvasWindow canvas;
    private ArrayList<EnemyMini> minis = new ArrayList<>();
    private Random random = new Random();
    private GraphicsGroup group;
    public static boolean collision = false;

    /**
     * the constructor of EnemyManager，add ground which hold all the enemies.
     */
    public EnemyManager(CanvasWindow canvas) {
        this.canvas = canvas;
        group = new GraphicsGroup();
        canvas.add(group);
    }

    /**
     * spawn random number of Enermies(between 1-10) at random position and add them to the group
     */
    public void spawnEnemy() {
        if (random.nextInt(100) < 1.5) {
            for (int num = 0; num < random.nextInt(10); num++) {
                EnemyMini mini = new EnemyMini(57 + random.nextInt(canvas.getWidth() - 104), 0);
                minis.add(mini);
                group.add(mini);
            }
        }
    }

    /**
     * remove enemies from the group when they are out of bound, otherwise update enemy's position
     */
    public void enemyOutOfBound() {
        minis.removeIf(enemy -> {
            enemy.updatePosition();
            if (enemy.outOfBound(canvas)) {
                group.remove(enemy);
                return true;
            } else
                return false;
        });
    }

    /**
     * remove enemy when they have the collision with ammos from the group and list(minis)
     */
    public boolean removeEnemiesCollision(Ammo ammo) {
        GraphicsObject mini1 = group.getElementAt(ammo.getCenter());
        if (mini1 != null) {
            minis.remove(mini1);
            group.remove(mini1);
            return true;
        }
        return false;
    }

    /**
     * check whether there is a collision between rocket and enemies
     */
    public void crashRocket(Rocket rocket) {
        GraphicsObject top = group.getElementAt(rocket.getRocketX(),
            rocket.getRocketY() - rocket.getHeight() / 2);
        GraphicsObject bottom = group.getElementAt(rocket.getRocketX(),
            rocket.getRocketY() + rocket.getHeight() / 2);
        GraphicsObject left = group.getElementAt(rocket.getRocketX() - rocket.getWidth() / 2,
            rocket.getRocketY());
        GraphicsObject right = group.getElementAt(rocket.getRocketX() + rocket.getWidth() / 2,
            rocket.getRocketY());
        if (top != null) {
            collision = true;
        } else if (bottom != null) {
            collision = true;
        } else if (left != null) {
            collision = true;
        } else if (right != null) {
            collision = true;
        }
    }

    public boolean checkEnemyCollision() {
        return collision;
    }
}