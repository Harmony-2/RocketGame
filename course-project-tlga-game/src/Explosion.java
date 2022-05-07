import edu.macalester.graphics.Image;
import edu.macalester.graphics.CanvasWindow;

/**
 * Add explosion image to canvas.
 */
public class Explosion {
    private Image explosion;

    public Explosion(CanvasWindow canvas) {
        explosion = new Image(30, 60, "explosion.png");
    }

    /**
     * Set center X and Y of the explosion image on the canvas.
     */
    public void setCenter(CanvasWindow canvas, double posX, double posY) {
        explosion.setCenter(posX, posY);
    }

    /**
     * Add the image to canvas
     * 
     * @param canvas
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(explosion);
    }

    /**
     * remove the image from the canvas
     * 
     * @param canvas
     */
    public void remove(CanvasWindow canvas) {
        canvas.remove(explosion);
    }

    public static void main(String[] args) {
        new Explosion(new CanvasWindow("test", 600, 800));
    }


}
