import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
/**
 * Below is the Gameplay class which is behaving as main game here.
 * It is linked with the Main class and Mainmenu and all the other component of the Games.
 * @author pradeep
 *
 */
public class Gameplay {
	private double time;
	private int gameover;
	private int currentscore;
	private Snake snake;
	private Pane pane;
	private Button score;
	private Button restart;
	private Button home;
	private int sheild;
	private int destroy;
	private int ismagnet;
	private int speed;
	private int isresume;
	double l1=10;
	double r1=490;
	private Player player;
	/**
	 * Below is the parameterized constructor of the Gameplay class
	 * @param scene , It is the scene on the which the game is operating.
	 * @param player, This is the player, who is playing the game.
	 * @param isresume, checks whether resume is being pressed or not.
	 * The constructor sets the speed of the snake, default as 4 unit.
	 * It has the restart,home and score button over itself.
	 * It initialize the score.
	 */
	public Gameplay(Scene scene,Player player,int isresume) {
		pane=new Pane();
		this.player=player;
		this.isresume=isresume;
		
		if(isresume==1) {
			try {
				
				Main.in=new ObjectInputStream(new FileInputStream("score.txt"));
				currentscore=(int) Main.in.readObject();
				Main.in=new ObjectInputStream(new FileInputStream("snakelength.txt"));
				int l=(int) Main.in.readObject();
				Main.in=new ObjectInputStream(new FileInputStream("snakepos.txt"));
				double pos=(double) Main.in.readObject();
				snake=new Snake(l,pos);
				
				
			}
			catch(Exception e) {
				currentscore=0;
				snake=new Snake(4,250);
			}
		}
		else {
			currentscore=0;
			snake=new Snake(4,250);
		}
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		makesnake();
		movesnake(scene);
		if(snake.getlength()>30){
			speed=7;
		}
		else if(snake.getlength()>20) {
			speed=6;
		}
		else if(snake.getlength()>10) {
			speed=5;
		}
		else if(snake.getlength()>0) {
			speed=4;
		}
			
		restart(scene);
		addscore();
		home(scene);
		screenbuild(scene);
		
	}/**
	 * Below is the method which starts the game.
	 * @param scene , It takes the parameter on which the game should be operated.
	 */
	public void play(Scene scene) {
		scene.setRoot(pane);
	}/**
	 * It restarts the game, sets the score to zero and length of snake as 4 units.
	 * @param scene , It takes the parameter on which the game should be operated.
	 */
	public void restart(Scene scene) {
		restart = new Button("restart");
		restart.setStyle("-fx-background-color: Yellow;");
		restart.setLayoutX(435.0);
		restart.setLayoutY(0);
		restart.setOnAction(e ->{
			gameover=1;
			Gameplay gameplay = new Gameplay(scene,player,0);
			gameplay.play(scene);
		});
		pane.getChildren().add(restart);
	}/**
	 * It takes the user to the home screen of the Game.
	 * @param scene, The home screen of the game is the scene here.
	 */
	public void home(Scene scene) {
		home = new Button("home");
		home.setStyle("-fx-background-color: Yellow;");
		home.setLayoutX(210.0);
		home.setLayoutY(0);
		home.setOnAction(e -> {
			if(gameover==1) {
				try {
					Main.out=new ObjectOutputStream(new FileOutputStream("leaderboard.txt"));
					Main.out.writeObject(Main.board.getplayers());
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
			gameover=1;
			try {
				Main.out=new ObjectOutputStream(new FileOutputStream("snakelength.txt"));
				Main.out.writeObject(snake.getlength());
				Main.out=new ObjectOutputStream(new FileOutputStream("score.txt"));
				Main.out.writeObject(currentscore);
				Main.out=new ObjectOutputStream(new FileOutputStream("snakepos.txt"));
				Main.out.writeObject(snake.getposition());
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				
				
			}
			MainMenu menu = new MainMenu(scene);
		});
		pane.getChildren().add(home);
	}
	/**
	 * It increases the score of the player.
	 */
	public void addscore() {
		score=new Button("score"+" "+Integer.toString(currentscore));
		score.setStyle("-fx-background-color: Yellow;");
		pane.getChildren().add(score);
	}/**
	 * It makes the snake of length 4 in starting.
	 */
	public void makesnake() {
		int n=snake.getlength();
		for(int i=0;i<n;i++) {
			Token ball=snake.getsnake().get(i);
			pane.getChildren().addAll(ball.gettoken(),ball.gettext());
		}
	}/**
	 * It moves the snake on the left and right of the screen , with the help of mouse.
	 * @param scene, The scene on which the game is being played.
	 */
	public void movesnake(Scene scene) {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			/**
			 * This method sends the user to the homescreen, by just tapping on the home button of the keyboard.
			 */
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
			/**
			 * this method is used to operate snake using mouse.
			 */
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				{if(event.getX()>l1 && event.getX()<r1 && gameover==0) {
					for(int i=0;i<snake.getsnake().size();i++) {
						snake.getb(i).gettoken().setLayoutX(event.getX());
						snake.getb(i).gettext().setX(event.getX()-5);
						
					}
				}}
				if(snake.getlength()>30){
					speed=7;
				}
				else if(snake.getlength()>20) {
					speed=6;
				}
				else if(snake.getlength()>10) {
					speed=5;
				}
				else if(snake.getlength()>0) {
					speed=4;
				}
			}
			
		});
	}/**
	 * This method is used to generate the balls on the screen.
	 */
	public void generateballs() {
		Random rand=new Random();
		int n=rand.nextInt(4)+1;
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
					if(gameover==0 && b.gettoken().getLayoutY()>480 && b.gettoken().getLayoutY()<490  && b.gettoken().getLayoutX()-20<snake.getsnake().get(0).gettoken().getLayoutX() && b.gettoken().getLayoutX()+20>snake.getsnake().get(0).gettoken().getLayoutX() ) {
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
	}/**
	 * This is used to generate the new blocks on the screen.
	 * @param scene, This is the screen on the which the game is being operated.
	 */
	public void generateblocks(Scene scene) {
		Random rand=new Random();
		int n=rand.nextInt(3)+2;
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<5; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
		
		for(int i=0;i<n;i++) {
			Block b=new Block(list);		
			pane.getChildren().addAll(b.getblock(),b.gettext());
			AnimationTimer t=new AnimationTimer() {
				/**
				 * This method is used to move blocks upside down on the screen.
				 */
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
					if(gameover==0 && b.getblock().getY()>=400 && b.getblock().getY()<=410 && b.getblock().getX()<snake.getsnake().get(0).gettoken().getLayoutX() && b.getblock().getX()+100>snake.getsnake().get(0).gettoken().getLayoutX()) {
							
						pane.getChildren().remove(b.getblock());
						pane.getChildren().remove(b.gettext());
						currentscore=currentscore+b.getvalue();
						score.setText("score"+" "+Integer.toString(currentscore));
						if(sheild==0) {
							try {
								snake.removeball(b.getvalue(), pane);
							}
							catch(Exception e) {
								player.setscore(currentscore);
								Main.board.addscore(player);
								Image Gameover=new Image("file:GameOver.gif");
								ImageView view =new ImageView(Gameover);
								pane.getChildren().add(view);
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
	/**
	 * This method is used to generate walls, which is acting as a restriction for the snake.
	 */
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
	}/**
	 * This is used to generate the magnet randomly on the screen.
	 */
	public void generatemagnet() {
		Token magnet=new Magnet();
		pane.getChildren().add(magnet.gettoken());
		AnimationTimer t=new AnimationTimer() {
			/**
			 * This method is used to move the magnet upside down.
			 * Magnet helps the snake to attract the balls and increase its length.
			 */
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
	}/**
	 * This method is used to generate the shield.
	 * Shield helps the snake to escape, from all the restrictions and from all the problems in the game.
	 */
	public void generateshield() {
		Token shield=new Sheild();
		pane.getChildren().add(shield.gettoken());
		AnimationTimer t=new AnimationTimer() {
			/**
			 * This method is used to send the shield upside down on the screen.
			 */
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
	}/**
	 * This method is used to generate the destroyer in the game.
	 * Destroyer is used to clear the screen and add the score and increment the length of the snake.
	 */	
	public void generatedestroyer() {
		Token destroyer=new Destroyer();
		pane.getChildren().add(destroyer.gettoken());
		AnimationTimer t=new AnimationTimer() {
			/**
			 * This method is used to handle the destroyer upside down with the help of the
			 * animation timer.
			 */
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
	}/**
		 * This is the screen builder which is calling all the snakes, balls etc in the loop.
		 * @param scene
		 */
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
