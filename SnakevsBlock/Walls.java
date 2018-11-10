import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Walls {
	private Rectangle wall;
	private int x;
	private int y;
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
	}
	public Rectangle getwall() {
		return wall;
	}
	public int getx() {
		return x/100;
	}
	public void setx() {
		Random rand=new Random();
		x=rand.nextInt(5)*100;
	}
}