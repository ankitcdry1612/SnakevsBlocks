import java.util.Random; 

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
/**
 * Below is the Magnet class, which extends Token class.
 * Magnet is type of token. which helps the player to collect more balls.
 * Which increases the length of the snake and its life.
 * @author pradeep
 *
 */
public class Magnet extends Token{
	/**
	 * Below is the non-Parameterized creates the magnet randomly.
	 * It sets the coordinate of the magnet randomly.
	 */
	public Magnet(){
		super(new Circle());
		Image magnet=new Image("file:12.png");
		ImagePattern image=new ImagePattern(magnet);
		Random rand=new Random();
		int x=rand.nextInt(5)*100 + 50;
		super.gettoken().setLayoutX(x);
		super.gettoken().setLayoutY(-30);
		((Circle) super.gettoken()).setRadius(15);
		super.gettoken().setFill(image);
	}
}
