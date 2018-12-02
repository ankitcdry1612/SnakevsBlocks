import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Block {
	private Rectangle block;
	private int x;
	private int y;
	private int value;
	private Text text;
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
	public Rectangle getblock() {
		return block;
	}
	public Text gettext() {
		return text;
	}
	public int getx() {
		return x/100;
	}
	public int getvalue() {
		return value;
	}
	public void setvalue(int val) {
		value=val;
	}
	public void setx() {
		Random rand=new Random();
		this.x=rand.nextInt(5)*100;
	}
}
