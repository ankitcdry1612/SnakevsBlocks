import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.SequentialTransition;
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
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Gameplay {
	private double time;
	private int gameover;
	private int currentscore;
	private Snake snake;
	private Pane pane;
	private Button score;
	private int sheild;
	private int destroy;
	private int ismagnet;
	private int speed;
	double l1=10;
	double r1=490;;
	public Gameplay(Scene scene){
		pane=new Pane();
		speed=4;
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
				if(event.getX()>l1 && event.getX()<r1 && gameover==0) {
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
		int n=rand.nextInt(5);
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<5; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
		for(int i=0;i<n;i++) {
			Token b=new Ball(list);
			pane.getChildren().addAll(b.gettoken(),b.gettext());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				b.gettoken().setLayoutY(b.gettoken().getLayoutY()+speed);
				b.gettext().setY(b.gettext().getY()+speed);
				if(ismagnet>0 && pane.getChildren().contains(b.gettoken()) && gameover==0 && b.gettoken().getLayoutY()>250 && b.gettoken().getLayoutX()-snake.getposition()<150 && b.gettoken().getLayoutX()-snake.getposition()>-150) {
					pane.getChildren().remove(b.gettoken());
					pane.getChildren().remove(b.gettext());
					snake.addball(b.getvalue(),pane);
				}
				if(pane.getChildren().contains(b.gettoken())) {
					if(gameover==0 && b.gettoken().getLayoutY()==480 && b.gettoken().getLayoutX()-20<snake.getsnake().get(0).gettoken().getLayoutX() && b.gettoken().getLayoutX()+20>snake.getsnake().get(0).gettoken().getLayoutX() ) {
						pane.getChildren().remove(b.gettoken());
						pane.getChildren().remove(b.gettext());
						snake.addball(b.getvalue(),pane);
					}
				}
				if(b.gettoken().getLayoutY()>655) {
					pane.getChildren().remove(b.gettoken());
					pane.getChildren().remove(b.gettext());
				}
				if(gameover==1) {
					this.stop();
				}
			}
		};
		t.start();
		}
	}
	public void generateblocks(Scene scene) {
		Random rand=new Random();
		int n=rand.nextInt(5)+1;
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<5; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
		
		for(int i=0;i<n;i++) {
			Block b=new Block(list);		
			pane.getChildren().addAll(b.getblock(),b.gettext());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				b.getblock().setY(b.getblock().getY()+speed);
				b.gettext().setY(b.gettext().getY()+speed);
				if(destroy>0 && pane.getChildren().contains(b.getblock())) {
					pane.getChildren().remove(b.getblock());
					pane.getChildren().remove(b.gettext());
					currentscore=currentscore+b.getvalue();
					score.setText("score"+" "+Integer.toString(currentscore));
				}
				else if(pane.getChildren().contains(b.getblock())){
					if(gameover==0 && b.getblock().getY()>=400 && b.getblock().getY()<=402 && b.getblock().getX()<snake.getsnake().get(0).gettoken().getLayoutX() && b.getblock().getX()+100>snake.getsnake().get(0).gettoken().getLayoutX()) {
						this.stop();
						pane.getChildren().remove(b.getblock());
						pane.getChildren().remove(b.gettext());
						currentscore=currentscore+b.getvalue();
						score.setText("score"+" "+Integer.toString(currentscore));
						if(sheild==0) {
							try {
								snake.removeball(b.getvalue(), pane);
							}
							catch(Exception e) {
								gameover=1;
							}
						}
					}
					if(b.getblock().getY()>655) {
						pane.getChildren().remove(b.getblock());
						pane.getChildren().remove(b.gettext());
					}
				}
				if(gameover==1) {
					this.stop();
				}
			}
		};
		t.start();
		
		}	
	}
	public void generatewalls() {
		Random rand=new Random();
		int n=rand.nextInt(3)+1;
		for(int i=0;i<n;i++) {
			Walls wall=new Walls();
			pane.getChildren().addAll(wall.getwall());
			AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				wall.getwall().setY(wall.getwall().getY()+speed);
				if(gameover==0 && wall.getwall().getY()>300 && snake.getposition()-wall.getwall().getX()>0 && snake.getposition()-wall.getwall().getX()<15) {
					l1=snake.getposition();
				}
				if(gameover==0 && wall.getwall().getY()>300 && snake.getposition()-wall.getwall().getX()<0 && snake.getposition()-wall.getwall().getX()>-10) {
					r1=snake.getposition();
				}
				if(wall.getwall().getY()>655) {
					l1=10;
					r1=490;
					pane.getChildren().remove(wall.getwall());
				}
				if(gameover==1){
					this.stop();
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
				magnet.gettoken().setLayoutY(magnet.gettoken().getLayoutY()+speed);
				if(magnet.gettoken().getLayoutY()>=480 && magnet.gettoken().getLayoutY()<=482 && magnet.gettoken().getLayoutX()-30<snake.getsnake().get(0).gettoken().getLayoutX() && magnet.gettoken().getLayoutX()+30>snake.getsnake().get(0).gettoken().getLayoutX() ) {
					pane.getChildren().remove(magnet.gettoken());
					pane.getChildren().remove(magnet.gettext());
					ismagnet=1;
				}
				if(magnet.gettoken().getLayoutY()>655) {
					pane.getChildren().remove(magnet.gettoken());
				}
				if(gameover==1) {
					this.stop();
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
				shield.gettoken().setLayoutY(shield.gettoken().getLayoutY()+speed);
				if(shield.gettoken().getLayoutY()>=480 && shield.gettoken().getLayoutY()<=482 && shield.gettoken().getLayoutX()-30<snake.getsnake().get(0).gettoken().getLayoutX() &&	shield.gettoken().getLayoutX()+30>snake.getsnake().get(0).gettoken().getLayoutX() ) {
					pane.getChildren().remove(shield.gettoken());
					pane.getChildren().remove(shield.gettext());
					sheild=1;
				}
				if(shield.gettoken().getLayoutY()>655) {
					pane.getChildren().remove(shield.gettoken());
				}
				if(gameover==1) {
					this.stop();
				}
			}
		};
		t.start();
	}
	public void generatedestroyer() {
		Token destroyer=new Destroyer();
		pane.getChildren().add(destroyer.gettoken());
		AnimationTimer t=new AnimationTimer() {
			@Override
			public void handle(long now) {
				destroyer.gettoken().setLayoutY(destroyer.gettoken().getLayoutY()+speed);
				if(destroyer.gettoken().getLayoutY()>=480 && destroyer.gettoken().getLayoutY()<=482 && destroyer.gettoken().getLayoutX()-30<snake.getsnake().get(0).gettoken().getLayoutX() && destroyer.gettoken().getLayoutX()+30>snake.getsnake().get(0).gettoken().getLayoutX() ) {
					pane.getChildren().remove(destroyer.gettoken());
					pane.getChildren().remove(destroyer.gettext());
					destroy=1;
				}
				if(destroyer.gettoken().getLayoutY()>655) {
					pane.getChildren().remove(destroyer.gettoken());
				}
				if(gameover==1) {
					this.stop();
				}
			}
		};
		t.start();
	}
	public void screenbuild(Scene scene) {
	
		AnimationTimer timer=new AnimationTimer() {
			int temp=0;
			int temp2=0;
			int swipe=0;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				time=time+1;
				temp=temp+1;
				temp2=temp2+1;
				if(sheild>0) {
					sheild++;
					snake.getb(0).gettoken().setFill(Color.ORANGE);
				}
				if(sheild==400) {
					sheild=0;
					snake.getb(0).gettoken().setFill(Color.YELLOW);
				}
				if(ismagnet>0) {
					ismagnet++;
				}
				if(ismagnet==400) {
					ismagnet=0;
				}
				if(destroy>0)
					destroy++;
				if(destroy==2)
					destroy=0;
				if(gameover==1) {
					this.stop();
				}
				if(time==95) 
					generateballs();
				if(temp2==95) {
					if(swipe==0) {
						generateshield();
						swipe=1;
					}
					else {
						generatedestroyer();
						swipe=0;
					}
				}	
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
