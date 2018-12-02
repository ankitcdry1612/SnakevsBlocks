import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Snake {
	private ArrayList<Token> snake; 
	private Token ball;
	private int length;
	private double x;
	private double y;
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
	public ArrayList<Token> getsnake() {
		return snake;
	}
	public int getlength() {
		return length;
	}
	public Ball getb(int index) {
		return (Ball) snake.get(index);
	}
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
	}
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
	}
	public double getposition() {
		return snake.get(0).gettoken().getLayoutX();
		
	}
}
