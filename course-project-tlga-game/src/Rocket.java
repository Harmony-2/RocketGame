import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * This is a class that call an image to create the rocket.
 */
public class Rocket {
    private Image rocket;

    public Rocket(CanvasWindow canvas) {
        rocket = new Image(30, 60, "rocket.png");
        canvas.add(rocket);
    }

    /**
     * Get rocket X location
     * 
     * @return
     */
    public double getRocketX() {
        return rocket.getCenter().getX();
    }

    /**
     * Get rocket Y location
     * 
     * @return
     */
    public double getRocketY() {
        return rocket.getCenter().getY();
    }

    /**
     * Set center of the rocket image.
     * 
     * @param x1
     * @param y1
     */
    public void setCenter(double x1, double y1) {
        rocket.setCenter(x1, y1);
    }

    /**
     * Add rocket image to canvas.
     * 
     * @param canvas
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(rocket);
    }

    /**
     * Remove rocket image to canvas.
     * 
     * @param canvas
     */
    public void remove(CanvasWindow canvas) {
        canvas.remove(rocket);
    }

    /**
     * Get rocket image height
     * 
     * @return
     */
    public double getHeight() {
        return rocket.getHeight();
    }

    /**
     * get rocket image width.
     * 
     * @return
     */
    public double getWidth() {
        return rocket.getWidth();
    }


    public static void main(String[] args) {
        new Rocket(new CanvasWindow("test", 600, 800));
    }
}
