import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
/**
 * Below is the wall class.
 * The field to this class, are rectangle and its coordinates.
 * The walls are used to restrict the motion of snake, from one point to other.
 * @author pradeep
 *
 */
public class Walls {
	private Rectangle wall;
	private int x;
	private int y;
	/**
	 * Below is the non-parameterised constructor of the wall class.
	 * The walls are generated randomly, with the help of the rectangle.
	 * The coordinates are also set randomly of the walls, with some restrictions.
	 * The color of wall is set to white.
	 */
	public Walls(){
		wall=new Rectangle();
		Random rand=new Random();
		x=rand.nextInt(5)*100;
		y=-200;
		wall.setX(x);
		wall.setY(y);
		wall.setHeight(200);
		wall.setWidth(5);
		wall.setArcHeight(30);
		wall.setArcWidth(30);
		wall.setFill(Color.WHITE);
	}/**
	 * 
	 * @return This method returns the wall to the screen of the gameplay.
	 */
	public Rectangle getwall() {
		return wall;
	}/**
	 * 
	 * @return This return the random generated x-coordinate of the wall. 
	 */
	public int getx() {
		return x/100;
	}/**
	 * This helps in setting the x-coordinate with some restrictions. 
	 */
	public void setx() {
		Random rand=new Random();
		x=rand.nextInt(5)*100;
	}
}
