import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Token {
	private int x;
	private int y;
	private int value;
	private Text text;
	private Shape shape;
	public Token(Shape s) {
		x=0;
		y=0;
		value=0;
		text=new Text();
		shape=s;
	}
	public int getx() {
		return x/100;
	}
	public int gety() {
		return y;
	}
	public void setx(int val) {
		x=val;
	}
	public void sety(int val) {
		y=val;
	}
	public void setvalue(int val) {
		value=val;
	}
	public int getvalue() {
		return value;
	}
	public Text gettext() {
		return text;
	}
	public Shape gettoken() {
		return shape;
	}
}
