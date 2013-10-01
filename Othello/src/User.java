/*
 * User.java
 * 
 * This class is the implementation of the abstract Player class and mostly just defines the method
 * for prompting the user for a move.
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * Spring 2009
 */

import java.util.Scanner;
import java.io.PrintStream;

public class User extends Player{
	
	private String movePrompt;
	private PrintStream output;
	
	public User(String aName, String aMovePrompt, PrintStream anOutput){
		
		name = aName;
		movePrompt = aMovePrompt;
		output = anOutput;
	}
	
	public String getMove(int[][] curBoard, int asPlayer){
		
		Scanner sc = new Scanner(System.in);

		String move;
		
		output.print(movePrompt);
		move = sc.nextLine();
		
		return move;
	}

}
