import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
/**
 * This is the Token class which is being extended, by all the tokens
 * during game
 * @author pradeep
 *
 */
public class Token {
	private int x;
	private int y;
	private int value;
	private Text text;
	private Shape shape;
	/**
	 * Below is the parametrised constructor of the class.
	 * @param It takes the parameter shape, which tells us about the shape of the token.
	 */
	public Token(Shape s) {
		x=0;
		y=0;
		value=0;
		text=new Text();
		shape=s;
	}
	/**
	 * 
	 * @return Returns the x-coordinate of the token.
	 */
	public int getx() {
		return x/100;
	}
	/**
	 * 
	 * @return Returns the y-coordinate of the token.
	 */
	public int gety() {
		return y;
	}/**
	 * 
	 * @param val, Sets the coordinate-x.
	 */
	public void setx(int val) {
		x=val;
	}/**
	 * 
	 * @param val, Sets the coordinate-y.
	 */
	public void sety(int val) {
		y=val;
	}/**
	 * 
	 * @param val, Sets the value of the token.
	 */
	public void setvalue(int val) {
		value=val;
	}/**
	 * 
	 * @return Returns the value of the token.
	 */
	public int getvalue() {
		return value;
	}/**
	 * 
	 * @return Sets text to the screen.
	 */
	public Text gettext() {
		return text;
	}/**
	 * 
	 * @return Returns shape of the token.
	 */
	public Shape gettoken() {
		return shape;
	}
}
