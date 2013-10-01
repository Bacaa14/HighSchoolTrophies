/*
 * User.java
 * This is a generalized class for a human player that prompts the user to enter a move.
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 */
import java.util.Scanner;
import java.io.PrintStream;

// a User is a human version of the generic Player class
public class User extends Player{
	
	private String movePrompt;
	private PrintStream output;
	
	public User(String aName, int aPlayerCode, String aMovePrompt, PrintStream anOutput){
		
		name = aName;
		playerCode = aPlayerCode;
		movePrompt = aMovePrompt;
		output = anOutput;
	}
	
	public String getMove(GameStateI curGameState){
		
		Scanner sc = new Scanner(System.in);

		output.print(movePrompt);
		
		return sc.nextLine();
	}

}
