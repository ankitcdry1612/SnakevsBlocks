import java.util.ArrayList; 
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Gameplay {
	private double time;

	private int currentscore;
	private MainMenu menu;
	private Snake snake;
	private Pane pane;
	private Button score;
	public Gameplay(Scene scene){
		pane=new Pane();
		currentscore=0;
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		snake=new Snake();
		makesnake();
		movesnake(scene);
		screenbuild(scene);
		addscore();
		pane.getChildren().remove(snake);
		scene.setRoot(pane);
	}
	public void addscore() {
		score=new Button("score"+" "+Integer.toString(currentscore));
		score.setStyle("-fx-background-color: Yellow;");
		pane.getChildren().add(score);
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
		         	case HOME:
		         		MainMenu menu=new MainMenu(scene);
		        }
			}
			
		});
		pane.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getX()>10 && event.getX()<490) {
					for(int i=0;i<snake.getsnake().size();i++) {
						snake.getb(i).gettoken().setLayoutX(event.getX());
						snake.getb(i).gettext().setLayoutX(event.getX()-250);
					}
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
				if(b.gettoken().getLayoutY()==480 && b.gettoken().getLayoutX()-20<snake.getsnake().get(0).gettoken().getLayoutX() && b.gettoken().getLayoutX()+20>snake.getsnake().get(0).gettoken().getLayoutX() ) {
					pane.getChildren().remove(b.gettoken());
					pane.getChildren().remove(b.gettext());
					snake.addball(b.getvalue(),pane);
				}
				if(b.gettoken().getLayoutY()>655) {
					pane.getChildren().remove(b.gettoken());
					pane.getChildren().remove(b.gettext());
				}
				
			}
		};
		t.start();
		}
	}
	public void generateblocks(Scene scene) {
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
				if(b.getblock().getY()>=400 && b.getblock().getY()<=402 && b.getblock().getX()<snake.getsnake().get(0).gettoken().getLayoutX() && b.getblock().getX()+100>snake.getsnake().get(0).gettoken().getLayoutX()) {
					pane.getChildren().remove(b.getblock());
					pane.getChildren().remove(b.gettext());
					currentscore=currentscore+b.getvalue();
					score.setText("score"+" "+Integer.toString(currentscore));
					try {
						snake.removeball(b.getvalue(), pane);
					}
					catch(Exception e) {
						System.exit(0);
					}
				}
				if(b.getblock().getY()>655) {
					pane.getChildren().remove(b.getblock());
					pane.getChildren().remove(b.gettext());
				}
				
				
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
				if(wall.getwall().getY()>655) {
					pane.getChildren().remove(wall.getwall());
				}
		
			}
		};
		t.start();
		}
	}
	public void generatemagnet() {
		Token magnet=new Magnet();
		pane.getChildren().add(magnet.gettoken());
		AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				magnet.gettoken().setLayoutY(magnet.gettoken().getLayoutY()+3);
				if(magnet.gettoken().getLayoutY()==480 && magnet.gettoken().getLayoutX()-30<snake.getsnake().get(0).gettoken().getLayoutX() && magnet.gettoken().getLayoutX()+30>snake.getsnake().get(0).gettoken().getLayoutX() ) {
					pane.getChildren().remove(magnet.gettoken());
					pane.getChildren().remove(magnet.gettext());
				}
				if(magnet.gettoken().getLayoutY()>655) {
					pane.getChildren().remove(magnet.gettoken());
				}
				
			}
		};
		t.start();
	}
	public void generateshield() {
		Token shield=new Sheild();
		pane.getChildren().add(shield.gettoken());
		AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				shield.gettoken().setLayoutY(shield.gettoken().getLayoutY()+3);
				if(shield.gettoken().getLayoutY()==480 && shield.gettoken().getLayoutX()-30<snake.getsnake().get(0).gettoken().getLayoutX() &&	shield.gettoken().getLayoutX()+30>snake.getsnake().get(0).gettoken().getLayoutX() ) {
					pane.getChildren().remove(shield.gettoken());
					pane.getChildren().remove(shield.gettext());
				}
				if(shield.gettoken().getLayoutY()>655) {
					pane.getChildren().remove(shield.gettoken());
				}
			}
		};
		t.start();
	}
	public void screenbuild(Scene scene) {
	
		AnimationTimer timer=new AnimationTimer() {
			int temp=0;
			int temp2=0;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				time=time+1;
				temp=temp+1;
				temp2=temp2+1;
				if(time==95)
					generateballs();
				if(temp2==95)
					generateshield();
				if(temp2==150*10)
					temp2=0;
				if(temp==430) 
					generatemagnet();
				if(temp==15*150)
					temp=0;
				if(time==85)
					generatewalls();
				if(time==150) {
					generateblocks(scene);
					time=0;
				}	
			}
			
		};
		timer.start();
	}
}
