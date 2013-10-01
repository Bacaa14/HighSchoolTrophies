/*
 * AIBot.java
 * This is a generalized class for getting a move from an AI, which queries a game tree
 * to a certain depth to determine the best move.
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * Spring 20009
 */

// rename the class from "AIBot" to "(yourLastname)Bot"
public abstract class AIBot extends Player {
	
	protected HeurEval myHeurEval;
	
	protected String creator;
		
	public abstract String getMove(int[][] curBoard, int asPlayer);
}
