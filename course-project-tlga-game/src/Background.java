import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
public class Background {
    private Image ground1;
    private Image ground2;
    private Image ground3;
    
    /**
     * Creates a new background that initializes three images that scroll continuously
     */
    public Background() {
        ground1 = new Image(0, 0, "background.png");
        ground2 = new Image(0, 0, "background.png");
        ground3 = new Image(0, 0, "background.png");


        ground1.setScale(1.25,1.14);
        ground2.setScale(1.25,1.14);
        ground3.setScale(1.25,1.14);
    }


    /**
     * Adds the ground images to the canvas
     * @param canvas the CanvasWdow that the game is running on
     */
    public void add(CanvasWindow canvas) {
        ground1.setCenter(canvas.getCenter());
        ground2.setCenter(canvas.getCenter().getX(), ground1.getCenter().getY()-ground1.getImageHeight());
        ground3.setCenter(canvas.getCenter().getX(), ground2.getCenter().getY()-ground2.getImageHeight());
        canvas.add(ground1);
        canvas.add(ground2);
        canvas.add(ground3);
    }

    /**
     * Moves the backgrounds and cycles the three identical images so that when one gets off the screen it moves back to the beginning
     */
    public void move() {
        if (isOutOfScreen(ground1)) {
            ground1.setCenter(ground1.getCenter().getX() , ground3.getCenter().getY()- ground3.getImageHeight());
        }
        if (isOutOfScreen(ground2)) {
            ground2.setCenter(ground2.getCenter().getX() , ground1.getCenter().getY()- ground1.getImageHeight());
        }
        if (isOutOfScreen(ground3)) {
            ground3.setCenter(ground3.getCenter().getX() , ground2.getCenter().getY()- ground2.getImageHeight());
        }

        ground1.setCenter(ground1.getCenter().getX(), ground1.getCenter().getY()+1);
        ground2.setCenter(ground2.getCenter().getX(), ground2.getCenter().getY()+1);
        ground3.setCenter(ground3.getCenter().getX(), ground3.getCenter().getY()+1);
    }

    /**
     * boolean that check whether ground image is out of canvas, return true if it does, otherwise reture false.
     */
    private boolean isOutOfScreen(Image ground) {
        if (ground.getCenter().getY() - ground.getImageHeight()/2 > 801) {
            return true;
        }
        return false;
    }      
}
