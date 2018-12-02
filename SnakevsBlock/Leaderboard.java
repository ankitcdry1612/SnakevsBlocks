import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Leaderboard {
	private ArrayList<Player> players;
	private Pane pane;
	private Rectangle[] playersarray;
	private Text[] scoresarray;
	Leaderboard(Scene scene){
		
		
		pane=new Pane();
		playersarray=new Rectangle[10];
		scoresarray=new Text[10];
		for(int i=0;i<10;i++) {
			playersarray[i]=new Rectangle(500,50);
			scoresarray[i]=new Text();
			playersarray[i].setTranslateX(0);
			playersarray[i].setFill(Color.YELLOW);
			scoresarray[i].setTranslateX(50);
			pane.getChildren().addAll(playersarray[i],scoresarray[i]);
			
		}
		try {
			
			Main.in=new ObjectInputStream(new FileInputStream("leaderboard.txt"));
			players=(ArrayList<Player>) Main.in.readObject();
			this.board();
		}
		catch(Exception e) {
			players=new ArrayList<Player>();
			
		}
		playersarray[0].setTranslateY(55);
		playersarray[1].setTranslateY(115);
		playersarray[2].setTranslateY(175);
		playersarray[3].setTranslateY(235);
		playersarray[4].setTranslateY(295);
		playersarray[5].setTranslateY(355);
		playersarray[6].setTranslateY(415);
		playersarray[7].setTranslateY(475);
		playersarray[8].setTranslateY(535);
		playersarray[9].setTranslateY(595);
		
		scoresarray[0].setTranslateY(85);
		scoresarray[1].setTranslateY(145);
		scoresarray[2].setTranslateY(205);
		scoresarray[3].setTranslateY(265);
		scoresarray[4].setTranslateY(325);
		scoresarray[5].setTranslateY(385);
		scoresarray[6].setTranslateY(445);
		scoresarray[7].setTranslateY(505);
		scoresarray[8].setTranslateY(565);
		scoresarray[9].setTranslateY(625);
		Button back = new Button("back");
		back.setStyle("-fx-background-color: Red;");
		pane.getChildren().add(back);
		back.setOnAction(e1 -> {
			MainMenu menu=new MainMenu(scene);
		});
			
	}
	public ArrayList<Player> getplayers(){
		return players;
	}
	public void show(Scene scene) {
		scene.setRoot(pane);
	}
	public void addscore(Player p) {
		if(players.size()<10) {
			this.players.add(p);
			Collections.sort(players);
			board();
		}
		else {
			if(players.get(0).getscore()<p.getscore()) {
				this.players.remove(players.size()-1);
				this.players.add(players.size()-1, p);
				Collections.sort(players);
				board();
			}
		}
	}
	public void board() {
		for(int i=0;i<players.size();i++) {
			scoresarray[i].setText(players.get(i).toString());
		}
	}
	
}
