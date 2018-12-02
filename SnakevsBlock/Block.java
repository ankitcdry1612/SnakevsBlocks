import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * This class is Block class, which comes as obstruction between the path of the snake.
 * The score of the user increases, when snake's destroys the block.
 * And the no. written on the block is added to the score.
 * @author pradeep
 *
 */
public class Block {
	private Rectangle block;
	private int x;
	private int y;
	private int value;
	private Text text;
	/**
	 * This is the parameterised constructor of Block class.
	 * @param list The parameter to this is the arraylist of position of blocks.
	 * which is used to generate the blocks randomly.
	 * In this the color of blocks are filled differently.
	 */
	public Block(ArrayList<Integer> list){
		block=new Rectangle();
		Random rand=new Random();
		x=list.get(0)*100;
		list.remove(0);
		y=-100;
		block.setX(x);
		block.setY(y);
		block.setHeight(100);
		block.setWidth(100);
		block.setArcHeight(30);
		block.setArcWidth(30);
		value=rand.nextInt(40)+1;
		text=new Text(Integer.toString(value));
		text.setX(x+45);
		text.setY(y+55);
		if(value>30)
			block.setFill(Color.RED);
		else if(value>20)
			block.setFill(Color.ORANGE);
		else if(value>10)
			block.setFill(Color.YELLOW);
		else if(value>5)
			block.setFill(Color.LEMONCHIFFON);
		else
			block.setFill(Color.WHITE);
	}
	/**
	 * 
	 * @return This method returns block to the screen.
	 */
	public Rectangle getblock() {
		return block;
	}
	/**
	 * 
	 * @return This method writes the text over the block.
	 */
	public Text gettext() {
		return text;
	}
	/**
	 * 
	 * @return This method returns the coordinate of the text.
	 */
	public int getx() {
		return x/100;
	}
	/**
	 *
	 * @return This method returns the score of the block, which is added to the score of the user.
	 */
	public int getvalue() {
		return value;
	}
	/**
	 * This method is used to set random x-position of the list of the block. 
	 */
	public void setx() {
		Random rand=new Random();
		this.x=rand.nextInt(5)*100;
	}
}
