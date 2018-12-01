import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu implements Serializable {
	private Pane pane;
	private Button start;
	private Button exit;
	private Button resume;
	private Button leaderboard;
	private Button help;
	private Gameplay game;
	public MainMenu(Scene scene) throws IOException {
		pane=new Pane();
		
		pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		start=new Button("Start");
		exit=new Button("Exit");
		resume=new Button("Resume");
		leaderboard=new Button("Leaderboard");
		leaderboard(scene);
		help=new Button("Help");
		setup(scene);
		help(scene);
		exit(scene);
		scene.setRoot(pane);
	}
	public void exit(Scene scene) throws IOException {
		Pane exitpane = new Pane();
		Button yes = new Button("Yes");
		Button no = new Button("No");
		Text sure=new Text("Are you sure?");
		sure.setX(100);
		sure.setY(100);
		sure.setX(180);
		sure.setY(300);
//		sure.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
		yes.setStyle("-fx-background-color: Red;");
		yes.setLayoutY(350);
		yes.setLayoutX(130);
		no.setStyle("-fx-background-color: Green;");
		no.setLayoutY(350);
		no.setLayoutX(280);
		exitpane.getChildren().addAll(yes,no,sure);
		exit.setOnAction(e ->{
			scene.setRoot(exitpane);
			yes.setOnAction(e1 -> {
				try {
					ObjectOutputStream writetofile = new ObjectOutputStream(new FileOutputStream("Game.txt"));
					writetofile.writeObject(game);
				}catch(IOException error){
					error.getMessage();
				}
				finally {
					System.exit(0);
				}
			});
			no.setOnAction(e2 -> {
				try {
					MainMenu main = new MainMenu(scene);
					} catch (IOException e3) {
						e3.printStackTrace();
					}
			});
		});
	}
	public void resume(Scene scene) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream readfile = new ObjectInputStream(new FileInputStream("game.txt"));
		try {
			game = (Gameplay) readfile.readObject();
		}catch(Exception error) {
			game = new Gameplay(scene);
		}
	}
	public void help(Scene scene) {
		Pane helppane=new Pane();
		Image image3 = new Image("file:Help.jpeg");
		ImageView image2 = new ImageView();
		image2.setImage(image3);
		image2.setFitHeight(650.0);
		image2.setFitWidth(500.0);
		Button back = new Button("Back");
		back.setStyle("-fx-background-color: Red;");
		Text text1 = new Text();
		Text text2 = new Text();
		Text text3 = new Text();
		Text text4 = new Text();
		Text text5 = new Text();
		Text text6 = new Text();
		Text text7 = new Text();
		Text text8 = new Text();
		text1.setText("1. Tap leftkey to move left and right key to move right");
		text2.setText("2. Tap exit to quit the game");
		text3.setText("3. Tap on the leaderboard to see top player");
		text4.setText("4. colliding with balls will increase the length of the snake");
		text5.setText("5. colliding with block will increase the point");
		text6.setText("6. You will not lose the game untill you collide with the block,			" +"\n"+"which have more point then snakes length");
		text7.setText("7. You can collide with magnet to attract more balls");
		text8.setText("8. You can collide with sheild to cover urself to be save from the blocks");
		text1.setX(5);
		text1.setY(40);
		text2.setX(5);
		text2.setY(100);
		text3.setX(5);
		text3.setY(160);
		text4.setX(5);
		text4.setY(220);
		text5.setX(5);
		text5.setY(280);
		text6.setX(5);
		text6.setY(340);
		text7.setX(5);
		text7.setY(400);
		text8.setX(5);
		text8.setY(460);
		text1.setFill(Color.RED);
		text2.setFill(Color.RED);
		text3.setFill(Color.RED);
		text4.setFill(Color.RED);
		text5.setFill(Color.RED);
		text6.setFill(Color.RED);
		text7.setFill(Color.RED);
		text8.setFill(Color.RED);
		helppane.getChildren().addAll(image2,text1,text2,text3,text4,text5,text6,text7,text8,back);
		help.setOnAction(e -> {
			scene.setRoot(helppane);
			back.setOnAction(e1 -> {
				scene.setRoot(pane);
			});
		});
	}
	public void setup(Scene scene) {
		Image image = new Image("file:SnakevsBlock.png");
		ImageView image1 = new ImageView();
		image1.setImage(image);
		image1.setFitWidth(500.0);
		image1.setFitHeight(650.0);
		start.setMinSize(100, 50);
		resume.setMinSize(120, 50);
		leaderboard.setMinSize(150, 50);
		help.setMinSize(100, 50);
		exit.setMinSize(100, 50);
		pane.setStyle("-fx-background-color: purple;");
		start.setStyle("-fx-background-color: Red;");
		resume.setStyle("-fx-background-color: Yellow;");
		leaderboard.setStyle("-fx-background-color: Red;");
		help.setStyle("-fx-background-color: Yellow;");
		exit.setStyle("-fx-background-color: Red;");
		pane.getChildren().addAll(image1,start,resume,leaderboard,help,exit);
		start.setLayoutX(100.0);
		start.setLayoutY(80.0);
		resume.setLayoutX(90.0);
		resume.setLayoutY(160.0);
		leaderboard.setLayoutX(80.0);
		leaderboard.setLayoutY(240.0);
		help.setLayoutX(100.0);
		help.setLayoutY(320.0);
		exit.setLayoutX(100.0);
		exit.setLayoutY(400.0);
		start.setOnAction(e ->{
			game=new Gameplay(scene);
		});
		/*exit.setOnAction(e ->{
			System.exit(0);
		});*/
		
	}
	public void leaderboard(Scene scene) {
		Rectangle player1 = new Rectangle(500,50);
		Rectangle player2 = new Rectangle(500,50);
		Rectangle player3 = new Rectangle(500,50);
		Rectangle player4 = new Rectangle(500,50);
		Rectangle player5 = new Rectangle(500,50);
		Rectangle player6 = new Rectangle(500,50);
		Rectangle player7 = new Rectangle(500,50);
		Rectangle player8 = new Rectangle(500,50);
		Rectangle player9 = new Rectangle(500,50);
		Rectangle player10 = new Rectangle(500,50);
		player1.setTranslateX(0);
		player1.setTranslateY(55);
		player2.setTranslateX(0);
		player2.setTranslateY(115);
		player3.setTranslateX(0);
		player3.setTranslateY(175);
		player4.setTranslateX(0);
		player4.setTranslateY(235);
		player5.setTranslateX(0);
		player5.setTranslateY(295);
		player6.setTranslateX(0);
		player6.setTranslateY(355);
		player7.setTranslateX(0);
		player7.setTranslateY(415);
		player8.setTranslateX(0);
		player8.setTranslateY(475);
		player9.setTranslateX(0);
		player9.setTranslateY(535);
		player10.setTranslateX(0);
		player10.setTranslateY(595);
		player1.setFill(Color.YELLOW);
		player2.setFill(Color.YELLOW);
		player3.setFill(Color.YELLOW);
		player4.setFill(Color.YELLOW);
		player5.setFill(Color.YELLOW);
		player6.setFill(Color.YELLOW);
		player7.setFill(Color.YELLOW);
		player8.setFill(Color.YELLOW);
		player9.setFill(Color.YELLOW);
		player10.setFill(Color.YELLOW);
		Text score1 = new Text("Player1 score is : " + 0);
		Text score2 = new Text("Player2 score is : " + 0);
		Text score3 = new Text("Player3 score is : " + 0);
		Text score4 = new Text("Player4 score is : " + 0);
		Text score5 = new Text("Player5 score is : " + 0);
		Text score6 = new Text("Player6 score is : " + 0);
		Text score7 = new Text("Player7 score is : " + 0);
		Text score8 = new Text("Player8 score is : " + 0);
		Text score9 = new Text("Player9 score is : " + 0);
		Text score10 = new Text("Player10 score is : " + 0);
		score1.setTranslateX(50);
		score1.setTranslateY(85);
		score2.setTranslateX(50);
		score2.setTranslateY(145);
		score3.setTranslateX(50);
		score3.setTranslateY(205);
		score4.setTranslateX(50);
		score4.setTranslateY(265);
		score5.setTranslateX(50);
		score5.setTranslateY(325);
		score6.setTranslateX(50);
		score6.setTranslateY(385);
		score7.setTranslateX(50);
		score7.setTranslateY(445);
		score8.setTranslateX(50);
		score8.setTranslateY(505);
		score9.setTranslateX(50);
		score9.setTranslateY(565);
		score10.setTranslateX(50);
		score10.setTranslateY(625);
		Pane ldboard = new Pane();
//		pane.setTitle("ScoreBoard");
		pane.setStyle("-fx-background-color: Yellow;");
		Button back = new Button("back");
		back.setStyle("-fx-background-color: Red;");
		ldboard.getChildren().addAll(player1,player2,player3,player4,player5,player6,player7,player8,player9,player10,back,score1,score2,score3,score4,score5,score6,score7,score8,score9,score10);
		leaderboard.setOnAction(e -> {
			scene.setRoot(ldboard);
			back.setOnAction(e1 -> {
				scene.setRoot(pane);
			});
		});
	}
	public Pane getpane() {
		return pane;
	}
}
