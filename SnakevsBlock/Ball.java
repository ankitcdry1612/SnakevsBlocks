import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;
/**
 * This is Ball class which extends token class.
 * This class is used to generate a snake.
 * @author pradeep
 *
 */
public class Ball extends Token {
	/**
	 * Below is the default constructor of the Ball class.
	 * The shape of ball is circle, which is initialised by the constructer. By calling the
	 * super constructor of parent class.
	 * we set the radius of the ball as 10 unit.
	 * and the coordinates of the snake are set randomly.
	 */
	public Ball() {
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
	/**
	 * This method randomly generates the token ball.
	 * When the snake eats the ball, the length of the snake got increases.
	 * This method also assures that, two balls are overlapping or not.
	 * @param list The parameter to this is arraylist of integer , which helps in generating random balls.
	 */
	public Ball(ArrayList<Integer> list) {
		super(new Circle());
		Random rand=new Random();
		int x=list.get(0)*100+50;
		list.remove(0);
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
