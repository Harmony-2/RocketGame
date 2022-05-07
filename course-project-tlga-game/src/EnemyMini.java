import edu.macalester.graphics.Image;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import java.util.Random;


public class EnemyMini extends Image {
 
    private Random random = new Random();
    private double dy = random.nextDouble() * 3 + 1.5;

    public EnemyMini(double x, double y){
        super("enemy1.png");
        setCenter(x, y);   
    }


    public void updatePosition(){
        moveBy(0, dy);
    }

    /**
     * remove Rocket from the canvas
     */
    public boolean outOfBound(CanvasWindow canvas){
        if (getCenter().getY()>=canvas.getHeight()) return true;    
        return false;
    }
     
    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("test", 480, 700);
        GraphicsGroup group = new GraphicsGroup();
        EnemyMini enemy = new EnemyMini(300, 0);
        canvas.add(group);
        group.add(enemy);
        canvas.animate(() -> {
            enemy.updatePosition();
        });
        
    }
    
}



  
    






