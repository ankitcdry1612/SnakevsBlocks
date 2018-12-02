import java.io.Serializable;
import java.util.Date;

public class Player implements Comparable,Serializable{
	private String Name;
	private int Score;
	private Date date;
	Player(String Name){
		this.Name=Name;
		date=new Date();
		this.Score=Score;
		Score=0;
	}
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
	public int getscore() {
		return Score;
	}
	public String toString() {
		return ("Name: "+Name+"      Score: "+Score+"    Date/Time"+date.toGMTString());
	}
	public void setscore(int val) {
		Score=val;
	}
}
