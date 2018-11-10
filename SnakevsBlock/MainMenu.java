import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {
	private Pane pane;
	private Button start;
	private Button exit;
	private Button resume;
	private Button leaderboard;
	private Button help;
	private Gameplay game;
	public MainMenu(Scene scene) {
		pane=new Pane();
		
		pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		start=new Button("Start");
		exit=new Button("Exit");
		resume=new Button("Resume");
		leaderboard=new Button("Leaderboard");
		help=new Button("Help");
		setup(scene);
		help(scene);
		scene.setRoot(pane);
	}
	public void help(Scene scene) {
		Pane helppane=new Pane();
		Image image3 = new Image("file:Help.jpeg");
		ImageView image2 = new ImageView();
		image2.setImage(image3);
		image2.setFitHeight(650.0);
		image2.setFitWidth(500.0);
		Button back = new Button("Back");
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
	public void exit() {
		exit.setOnAction(e ->{
			System.exit(0);
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
		exit.setOnAction(e ->{
			System.exit(0);
		});
		
	}
	public Pane getpane() {
		return pane;
	}
}
