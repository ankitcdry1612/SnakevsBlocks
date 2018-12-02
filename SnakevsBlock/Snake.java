import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
/**
 * Below is the snake class, which is used to play game.
 * @author pradeep
 *
 */
public class Snake {
	private ArrayList<Token> snake; 
	private Token ball;
	private int length;
	private double x;
	private double y;
	/**
	 * Below  is the non-parameterized constructor, which is used to create a snake.
	 * Snake of length 4 unit.
	 * where 1 unit is equal to 1 ball.
	 */
	public Snake(int l,double pos) {
		snake=new ArrayList<Token>();
		x=pos;
		y=500;
		length=0;
		for(int i=0;i<l;i++) {
			ball=new Ball();
			ball.gettoken().setLayoutX(x);
			ball.gettext().setX(x-5);
			ball.gettext().setY(y-11);
			ball.gettoken().setLayoutY(y);
			length++;
			ball.gettext().setText("");
			snake.add(ball);
			y=y+20;
		}
		snake.get(0).gettext().setText(Integer.toString(length));
		snake.get(0).gettext().setFill(Color.WHITE);
	}
	/**
	 * 
	 * @return Method to get snake on the screen.
	 */
	public ArrayList<Token> getsnake() {
		return snake;
	}/**
	 * 
	 * @return Method to get the length of the snake.
	 */
	public int getlength() {
		return length;
	}/**
	 * 
	 * @param index which index ball we need.
	 * @return returns the ball of specific index.
	 */
	public Ball getb(int index) {
		return (Ball) snake.get(index);
	}/**
	 * 
	 * @param n , Used to increase the length of the snake.
	 * @param pane , The pane on which the snake is being operated.
	 */
	public void addball(int n,Pane pane) {
		for(int i=0;i<n;i++) {
			Token ball=new Ball();
			double x=snake.get(0).gettoken().getLayoutX();
			double y=snake.get(length-1).gettoken().getLayoutY()+20;
			ball.gettoken().setLayoutX(x);
			ball.gettoken().setLayoutY(y);
			pane.getChildren().add(ball.gettoken());
			length++;
			snake.add(ball);
			this.y=this.y+20;
		}
		snake.get(0).gettext().setText(Integer.toString(length));
	}/**
	 * 
	 * @param n, To reduce the length of the snake by n unit.
	 * @param pane, The pane on which the snake is being operated.
	 */
	public void removeball(int n,Pane pane) {
		
		int j=0;
		while(j<n) {
			int i=snake.size()-1;
			pane.getChildren().remove(snake.get(i).gettoken());
			snake.remove(i);
			length--;
			j++;
		}
		snake.get(0).gettext().setText(Integer.toString(length));
	}/**
	 * this methods returns the x-coordinate of the snake.
	 */
	public double getposition() {
		return snake.get(0).gettoken().getLayoutX();
		
	}
}
