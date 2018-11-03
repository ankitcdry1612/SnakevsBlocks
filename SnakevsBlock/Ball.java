import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Random;

public class Ball {
	private Circle circle;
	private int x;
	private int y;
	private int value;
	private Text text;
	Ball(){
		text=new Text();
		Random rand=new Random();
		x=rand.nextInt(4)*100 + 50;
		y=0;
		circle=new Circle(x,y,10);
		circle.setFill(Color.YELLOW);
		value=rand.nextInt(15)+1;
		text.setText(Integer.toString(value));
		text.setX(circle.getCenterX()-5);
		text.setY(circle.getCenterY()+2);
		
	}
	public Circle getBall() {
		return circle;
	}
	public Text gettext() {
		return text;
	}
}
