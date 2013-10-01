/*
 * GameStateI.java
 * 
 * This interface defines the methods necessary for evaluating a given GameState
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * Spring 2009
 */

import java.util.ArrayList;

public interface GameStateI {
	
	/* hasWinner() returns a non-zero number corresponding to the player numbner
	 * (coud be -1 and 1 for a 2 player game) if that player has won the game;
	 * otherwise, getWinner() returns 0 for no current winner 
	*/
	public abstract int hasWinner();
	
	/*
	 * isDraw() returns true if the game is a draw and false otherwise
	 */
	public abstract boolean isDraw();
	
	
	/*
	 * getMoves(int) returns all of the legal moves of a certain player for the 
	 * given GameState as an ArrayList of Objects, which may be Integers or some
	 * other way of representing the move
	 */
	public abstract ArrayList<Integer> getPossibleMoves();
	
	/*
	 * isValidMove(int) tests whether a certain move is valid given the current 
	 * GameState
	 */
	public abstract boolean isValidMove(int move);
	
	/*
	 * isValidMove(String) tests whether a certain move is valid given the current 
	 * GameState
	 */
	public abstract boolean isValidMove(String move);
	
	/*
	 * moveToInt(String) takes a string represntation of a move and converts it to an 
	 * int representation
	 */
	public abstract int moveToInt(String move);
	
	/*
	 * moveToString(int) takes an int representation of a move and converts it to a 
	 * String representation
	 */
	public abstract String moveToString(int move);
	
	
	/*
	 * getNextPlayer() returns the int value for the next player to play
	 */
	public abstract int getNextPlayer();
	
	/*
	 * printScore() prints the current score of the game to the specified output; 
	 * this method is intended to be overridden in a sub class if desired, otherwise 
	 * it does nothing
	 */
	//public abstract void printScore(PrintStream output);
	
	
	/*
	 * hasMove() returns true if the current player can move on the current game state; 
	 * otherwise, returns false; this method is intended to be overridden 
	 * in a sub class
	 */
	public abstract boolean hasMove();
	
	/*
	 * getCurPlayer() returns the player next to move in the GameState
	 */
	public abstract int getCurPlayer();
	
	/*
	 * setCurPlayer() sets teh player to move in the GameState
	 */
	public abstract void setCurPlayer(int aPlayer);
	
	
	/*
	 * getBoard returns the internal data of the GameState to represent the board or current state of play
	 */
	public abstract Object getBoard();

}
