import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
/**
 * Below is the shield class.
 * The shield is used to protect the snake, from all the blocks.
 * @author pradeep
 * with the help of shield, snake is not going to die for a specific period.
 * Irrespective of the fact, it collides with the blocks of greater value.
 * The sheild class extends token class.
 */
public class Sheild extends Token {
	/**
	 * Below is the non-parameterized constructor of the shield class.
	 * It sets the coordinate of the shield, which are randomly generated.
	 * Also sets the image over the shield.
	 */
	public Sheild() {
		super(new Circle());
		Image shield = new Image("file:shield.jpeg");
		ImagePattern image = new ImagePattern(shield);
		Random rand = new Random();
		int x = rand.nextInt(5)*100+50;
		super.gettoken().setLayoutX(x);
		super.gettoken().setLayoutY(-30);
		((Circle) super.gettoken()).setRadius(15);
		super.gettoken().setFill(image);
	}
}
