package smash;

/**
 * 
 * @author Alec Pierce
 * created date: 05/18/2019
 * This class keeps track of the player's name and score
 */
public class Player {
	
	String name = "";
	int score = 0;

	public Player() { }
	
	public Player (String name) {
		this.name = name;
		this.score = 0;
	}
	
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void wins() {
		this.score += 1;
	}
}
