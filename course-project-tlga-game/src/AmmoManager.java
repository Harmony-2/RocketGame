import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import java.util.ArrayList;

public class AmmoManager {
    private GraphicsGroup ammoGroup;
    private ArrayList<Ammo> ammoList = new ArrayList<>();
    private Rocket rocket;
    private EnemyManager enemyManager;
    private int score;
    private int rateOfAmmo;

    public AmmoManager(CanvasWindow canvas, Rocket rocket, EnemyManager enemyManager) {
        this.rocket = rocket;
        this.enemyManager = enemyManager;
        ammoGroup = new GraphicsGroup();
        canvas.add(ammoGroup);
        rateOfAmmo = 0;
    }

    /**
     * generate ammo, based on the positon of rocket, add all the ammo to the ammogroup and ammoList
     */
    public void generateAmmo() {
        if (rateOfAmmo == 5) {
            double rocketTop = rocket.getRocketY() - rocket.getHeight() / 2;
            Ammo ammo = new Ammo(rocket.getRocketX() + 3, rocketTop, 10);
            ammoList.add(ammo);
            ammoGroup.add(ammo);
            rateOfAmmo = 0;
        } else {
            rateOfAmmo++;
        }
    }


    /**
     * move ammo, remove ammo and enemy when there is a collision between ammo from its list.
     */

    public void moveAmmo() {
        for (Ammo ammo : ammoList) {
            ammo.updateAmmoPosition();
        }
        ammoList.removeIf(ammo1 -> {
            if (enemyManager.removeEnemiesCollision(ammo1)) {
                ammoGroup.remove(ammo1);
                score++;
                return true;
            }
            if (ammo1.getCenter().getY() < 0) {
                ammoGroup.remove(ammo1);
                return true;
            } else
                return false;
        });
    }

    public int getScore() {
        return score;
    }
   
}

