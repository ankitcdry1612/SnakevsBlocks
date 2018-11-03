import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MainMenu menu=new MainMenu(stage);
		stage.setScene(menu.getscene());
		stage.show();
		
	}

}
