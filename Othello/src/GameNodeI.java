/*
 * GameNodeI.java
 * 
 * This interface defines the required methods used by an AIBot in getting a best move
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * Spring 2009
 */

public interface GameNodeI {
	
	/*
	 * getBestMove() returns the best move of the children for the node
	 */
	public abstract int getBestMove();
	
	
	/*
	 * getPreMove() returns the move that led to the current node
	 */
	public abstract int getPrevMove();
	
	/*
	 * getHeurVal(int, int) is a recursive method that traverses the game tree down
	 * to a specified depth (stops when depth == 0) for a specific player
	 */
	public abstract int getHeurVal(int depth, int rootPlayer, HeurEval w);
	
	
	/*
	 * getGameStateHeurVal() returns the heuristic value of the current GameState
	 * for the current player
	 */
	public abstract int getGameNodeHeurVal(int rootPlayer, HeurEval w);

}
