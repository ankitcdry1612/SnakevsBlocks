import java.io.Serializable;
import java.util.Date;
/**
 * Below is the player class. which implements interfaces like comparable and serializable.
 * This class helps in chosing name to different users and setting score to their profile.
 * @author pradeep
 *
 */
public class Player implements Comparable,Serializable{
	private String Name;
	private int Score;
	private Date date;
	/**
	 * Below is the parameterized constructor of this class.
	 * @param Name, It takes the parameter Name of the player.
	 * Initialise the score of the player as zero.
	 */
	Player(String Name){
		this.Name=Name;
		date=new Date();
		this.Score=Score;
		Score=0;
	}
	/**
	 * This is the overriden method compareTo of Interface Comparable.
	 * This is used to sort the score of the players in the leaderboard.
	 */
	@Override
	public int compareTo(Object player) {
		// TODO Auto-generated method stub
		Player p=(Player) player;
		if(this.Score>p.getscore()) {
			return -1;
		}
		else if(this.Score<p.getscore()) {
			return 1;
		}
		else
			return 0;
	}
	/**
	 * 
	 * @return This method returns the score of the player.
	 */
	public int getscore() {
		return Score;
	}
	/**
	 * This method concatinates the name and the score of the respective player.
	 * which is being displayed on the leaderboard.
	 */
	public String toString() {
		return ("Name: "+Name+"      Score: "+Score+"    Date/Time"+date.toGMTString());
	}/**
	 * 
	 * @param val, This method is used to set the score of the player.
	 */
	public void setscore(int val) {
		Score=val;
	}
}
