import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gameplay {
	private double time;
	Snake snake;
	private Pane pane;
	public Gameplay(Stage stage,Scene scene){
		pane=new Pane();
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		snake=new Snake();
		stage.setTitle("Snake vs Blocks");
		makesnake();
		movesnake(scene);
		screenbuild();
		
	}
	public void makesnake() {
		int n=snake.getlength();
		for(int i=0;i<n;i++) {
			Ball ball=snake.getsnake().get(i);
			pane.getChildren().addAll(ball.getBall(),ball.gettext());
		}
	}
	public void movesnake(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				 switch (event.getCode()) {
		         	case RIGHT:
		         		for(int i=0;i<snake.getlength();i++) {
		         			if(snake.getb(i).getBall().getCenterX()+50<=500) {
		         				snake.getb(i).getBall().setCenterX(snake.getb(i).getBall().getCenterX() + 50);
		         				snake.getb(i).gettext().setX(snake.getb(i).gettext().getX()+50);
		         			}
		         		}
		         		break;
		         	case LEFT:  
		         		for(int i=0;i<snake.getlength();i++) {
		         			if(snake.getb(i).getBall().getCenterX()-50>=0) {
		         				snake.getb(i).getBall().setCenterX(snake.getb(i).getBall().getCenterX() - 50);
		         				snake.getb(i).gettext().setX(snake.getb(i).gettext().getX()-50);
		         			}
		         		}
		        	  	break;
		        }
			}
			
		});
	}
	public void generateballs(Pane pane) {
		Random rand=new Random();
		int n=rand.nextInt(3)+1;
		ArrayList<Ball> ball=new ArrayList<Ball>();
		for(int i=0;i<n;i++) {
			Ball b=new Ball();
			pane.getChildren().addAll(b.getBall(),b.gettext());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				b.getBall().setCenterY(b.getBall().getCenterY()+2);
				b.gettext().setY(b.gettext().getY()+2);
			}
		};
		t.start();
		}
	}
	public void generateblocks(Pane pane) {
		Random rand=new Random();
		int n=rand.nextInt(4)+1;
		ArrayList<Block> blocks=new ArrayList<Block>();
		for(int i=0;i<n;i++) {
			Block b=new Block();
			pane.getChildren().addAll(b.getblock(),b.gettext());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				b.getblock().setY(b.getblock().getY()+2);
				b.gettext().setY(b.gettext().getY()+2);
			}
		};
		t.start();
		}	
	}
	public void screenbuild() {
		AnimationTimer timer=new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				time=time+1;
				if(time==95) {
					generateballs(pane);
					//time=0;
				}
				else if(time==150) {
					generateblocks(pane);
					time=0;
				}
				
			}
			
		};
		timer.start();
	}
	public Pane getpane() {
		return pane;
	}
}
