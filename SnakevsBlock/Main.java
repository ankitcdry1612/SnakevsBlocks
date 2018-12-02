import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	static Leaderboard board;
	static ObjectInputStream in=null;
	static ObjectOutputStream out=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

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
