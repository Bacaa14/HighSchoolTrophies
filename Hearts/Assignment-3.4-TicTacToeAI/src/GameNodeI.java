/*
 * GameNodeI.java
 * This interface, which extends \texttt{GameStateI}, defines methods used for a node in a game tree like those involved in generating the children 
 * for each valid  move from the game state represented by that node and getting the heuristic value of that node (and its children). 
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 */

import java.util.ArrayList;

public interface GameNodeI extends GameStateI {

	// any implementation should have the following private data:
	
		//protected int prevMove; // the move that led to the current GameState
			
		//protected ArrayList<GameNode> children; // the possible GameStates that could result
												 // from various moves
	
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
	public abstract int getHeurVal(int depth, int rootPlayer);
	
	
	/*
	 * getGameStateHeurVal() returns the heuristic value of the current GameState
	 * for the current player
	 */
	public abstract int getGameNodeHeurVal(int rootPlayer);
	
	
	/*
	 * makeChildren() generates the children GameStates for all possible moves
	 * as returned by getPossibleMoves(int) 
	 */
	public abstract void makeChildren();
	
	
	/*
	 * makeChildren(ArrayList<Objects>) generates the children GameStates for
	 * a given list of moves  
	 */
	public abstract void makeChildren(ArrayList<Integer> moves);
	
	/*
	 * makeChild(int) creates a new GameState from the current GameState 
	 * with a given move
	 * 
	 * Precondition: move is a valid move
	 */
	public abstract GameNodeI makeChild(int move);
}
