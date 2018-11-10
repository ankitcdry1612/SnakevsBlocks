import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

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
		stage.setScene(scene);
		stage.show();
		
		
	}

}
