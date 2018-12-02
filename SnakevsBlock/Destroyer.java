import java.util.Random; 

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Destroyer extends Token{
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
