import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.Random;

public class Ball extends Token {
	Ball(){
		super(new Circle());
		Random rand=new Random();
		int x=rand.nextInt(4)*100 + 50;
		int y=0;
		super.gettoken().setLayoutX(x);
		super.gettoken().setLayoutY(y);
		((Circle) super.gettoken()).setRadius(10);
		super.gettoken().setFill(Color.YELLOW);
		super.setx(x);
		super.sety(0);
		int value=rand.nextInt(15)+1;
		super.setvalue(value);
		super.gettext().setText(Integer.toString(value));
		super.gettext().setX(super.gettoken().getLayoutX()-5);
		super.gettext().setY(super.gettoken().getLayoutY()+2);
		
	}
}
