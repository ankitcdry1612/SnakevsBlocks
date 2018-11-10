import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Magnet extends Token{
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
