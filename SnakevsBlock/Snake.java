import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Snake {
	private ArrayList<Token> snake; 
	private Token ball;
	int length;
	private int x;
	private int y;
	public Snake() {
		snake=new ArrayList<Token>();
		x=250;
		y=500;
		length=0;
		for(int i=0;i<4;i++) {
			ball=new Ball();
			ball.gettoken().setLayoutX(x);
			ball.gettext().setX(x-5);
			ball.gettext().setY(y+2);
			ball.gettoken().setLayoutY(y);
			length++;
			ball.gettext().setText("");
			snake.add(ball);
			y=y+20;
		}
		snake.get(0).gettext().setText(Integer.toString(length));
	}
	public ArrayList<Token> getsnake() {
		return snake;
	}
	public int getlength() {
		return length;
	}
	public Ball getb(int index) {
		return (Ball) snake.get(index);
	}
}
