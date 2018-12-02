import java.util.Random; 

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
/**
 * Below is the destroyer class which extends the Token class.
 * The destroyer is used to clear the screen.
 * when snake eats the destroyer, the length of snake increases by the amount of balls on the screen.
 * And the score is updated by the sum of all the blocks in the screen.
 * @author pradeep
 *
 */
public class Destroyer extends Token{
	/**
	 * Below is the default constructor of the Destroyer class.
	 * It is used to randomly generate the destroyer.
	 */
	public Destroyer(){
		super(new Circle());
		Image magnet=new Image("file:destroyer.jpg");
		ImagePattern image=new ImagePattern(magnet);
		Random rand=new Random();
		int x=rand.nextInt(5)*100 + 50;
		super.gettoken().setLayoutX(x);
		super.gettoken().setLayoutY(-30);
		((Circle) super.gettoken()).setRadius(15);
		super.gettoken().setFill(image);
	}
}
