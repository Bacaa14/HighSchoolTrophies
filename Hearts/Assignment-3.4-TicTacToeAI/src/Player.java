/*
 * Player.java
 * This abstract class is a wrapper for any type of player, human or computer. Its most important (abstract) method 
 * gets a play from the player given a certain game state.
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 */
 
 
public abstract class Player {

	protected String name; // the player's name
	protected int playerCode; // the numerical code for the player; player 1 = -1, player 2 = 1 
	
	/*
	 * getMove(GameState) returns the int representation of the player's next move
	 */
	public abstract String getMove(GameStateI curState);
	
	
	public String getName(){
		return name;
	}
	
	public int getCode(){
		return playerCode;
	}
}
