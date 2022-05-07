import java.awt.Color;
import edu.macalester.graphics.*;

public class Ammo extends Ellipse {
    public static final double BALL_RADIUS = 4;
    private static final Color BALL_COLOR = new Color(204, 68, 0);
    private double dy;

    /**
     * constructor of ammo, and set the position and the speed of ammo
     */
    public Ammo(double centerX, double centerY, double dy) {
        super(centerX - BALL_RADIUS, centerY - BALL_RADIUS, BALL_RADIUS, BALL_RADIUS * 2);
        setFillColor(BALL_COLOR);
        this.dy = dy;
    }

    public void updateAmmoPosition() {
        moveBy(0, -dy);
    }

}