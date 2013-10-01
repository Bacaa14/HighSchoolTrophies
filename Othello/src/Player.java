/*
 * Player.class
 * 
 * This class defines basic data and methods necessary for all inherited player classes
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * Spring 2009
 */

public abstract class Player {

	protected String name; // the player's name
	
	/*
	 * getMove(GameState) returns the int representation of the player's next move
	 */
	public abstract String getMove(int[][] curBoard, int asPlayer);
	
	
	public String getName(){
		return name;
	}
	
	public static String getColor(int playerCode){
		if(playerCode == -1)
			return "Black";
		else if(playerCode == 1)
			return "White";
		else
			return "No Color";
	}
}
