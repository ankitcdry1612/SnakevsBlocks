import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Sheild extends Token {
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