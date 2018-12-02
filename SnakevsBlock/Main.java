import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * This is the main class which executes the game.
 * This class extends the application class.
 * @author pradeep
 *
 */
public class Main extends Application {
	static Leaderboard board;
	static ObjectInputStream in=null;
	static ObjectOutputStream out=null;
	/**
	 * This is the main method which launches the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	/**
	 * This is the method in the application class which is used to start the game.
	 * And creates a stage for the game.
	 * This method also throws the exceptions.
	 */

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Snake vs Blocks");
		Pane pane=new Pane();
		Scene scene=new Scene(pane,500,650);
		MainMenu menu=new MainMenu(scene);
		board=new Leaderboard(scene);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
		
	}

}
