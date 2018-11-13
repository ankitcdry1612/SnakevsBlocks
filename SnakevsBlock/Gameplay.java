import java.util.ArrayList; 
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class Gameplay {
	private double time;
	private MainMenu menu;
	private Button back;
	private Snake snake;
	private Pane pane;
	public Gameplay(Scene scene){
		pane=new Pane();
		back=new Button("back");
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		snake=new Snake();
		makesnake();
		movesnake(scene);
		screenbuild();
		scene.setRoot(pane);
		
	}

	public void makesnake() {
		int n=snake.getlength();
		for(int i=0;i<n;i++) {
			Token ball=snake.getsnake().get(i);
			pane.getChildren().addAll(ball.gettoken(),ball.gettext());
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
		         			if(snake.getb(i).gettoken().getLayoutX()+50<=500) {
		         				snake.getb(i).gettoken().setLayoutX(snake.getb(i).gettoken().getLayoutX() + 50);
		         				snake.getb(i).gettext().setX(snake.getb(i).gettext().getX()+50);
		         			}
		         		}
		         		break;
		         	case LEFT:  
		         		for(int i=0;i<snake.getlength();i++) {
		         			if(snake.getb(i).gettoken().getLayoutX()-50>=0) {
		         				snake.getb(i).gettoken().setLayoutX(snake.getb(i).gettoken().getLayoutX() - 50);
		         				snake.getb(i).gettext().setX(snake.getb(i).gettext().getX()-50);
		         			}
		         		}
		        	  	break;
		         	case HOME:
		         		MainMenu menu=new MainMenu(scene);
		        }
			}
			
		});
	}
	public void generateballs() {
		Random rand=new Random();
		int n=rand.nextInt(3)+1;
		//ArrayList<Ball> ball=new ArrayList<Ball>();
		for(int i=0;i<n;i++) {
			Token b=new Ball();
			pane.getChildren().addAll(b.gettoken(),b.gettext());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				b.gettoken().setLayoutY(b.gettoken().getLayoutY()+3);
				b.gettext().setY(b.gettext().getY()+3);
			}
		};
		t.start();
		}
	}
	public void generateblocks() {
		Random rand=new Random();
		int n=rand.nextInt(3)+2;
		//ArrayList<Block> blocks=new ArrayList<Block>();
		//int[] x=new int[5];
		
		for(int i=0;i<n;i++) {
			Block b=new Block();		
			pane.getChildren().addAll(b.getblock(),b.gettext());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				b.getblock().setY(b.getblock().getY()+3);
				b.gettext().setY(b.gettext().getY()+3);
			}
		};
		t.start();
		}	
	}
	public void generatewalls() {
		Random rand=new Random();
		int n=rand.nextInt(3)+1;
		//ArrayList<Ball> ball=new ArrayList<Ball>();
		for(int i=0;i<n;i++) {
			Walls wall=new Walls();
			pane.getChildren().addAll(wall.getwall());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				wall.getwall().setY(wall.getwall().getY()+3);
			}
		};
		t.start();
		}
	}
	public void generateshield() {
		Token shield = new Shield();
		pane.getChildren().add(shield.gettoken());
		AnimationTimer t = new AnimationTimer() {
			@Override
			public void handle(long now) {
				shield.gettoken().setLayoutY(shield.gettoken().getLayoutY()+3);
			}
		};
		t.start();
	}
	public void generatemagnet() {
		Token magnet=new Magnet();
		pane.getChildren().add(magnet.gettoken());
		AnimationTimer t=new AnimationTimer() {
		@Override
		public void handle(long now) {
			magnet.gettoken().setLayoutY(magnet.gettoken().getLayoutY()+3);
		}
		};
		t.start();
	}
	public void screenbuild() {
	
		AnimationTimer timer=new AnimationTimer() {
			int temp=0;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				time=time+1;
				temp=temp+1;
				if(time==95){
					generateballs();
					generateshield();
				}

				if(temp==430) 
					generatemagnet();
				if(temp==15*150)
					temp=0;
				if(time==85)
					generatewalls();
				if(time==150) {
					generateblocks();
					time=0;
				}
				
			}
			
		};
		timer.start();
	}
}
