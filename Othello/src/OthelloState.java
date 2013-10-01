/*
 * OthelloState.java
 * 
 * This class is responsible for much of the game's properties, like displaying the current GameState, 
 * calculating the score, and getting the current player.
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * Spring 20009
 */

import java.util.ArrayList;
import java.io.PrintStream;

public abstract class OthelloState {

	protected int[][] board;
	protected int curPlayer;

	protected ArrayList<Integer> curPlayerMoves = null;

	protected static int N = -8;
	protected static int S = 8;
	protected static int E = 1;
	protected static int W = -1;

	/*
	 * getPossibleMoves() returns an Integer ArrayList of moves the curPlayer
	 * can make given the current board configuration
	 */
	public abstract ArrayList<Integer> getPossibleMoves();

	/*
	 * hasMove() returns True if the curPlayer can make a move, False otherwise
	 */
	public abstract boolean hasMove();

	/*
	 * isValidMove(int) returns True if the move parameter is a valid move for
	 * the curPlayer given the current board configuration
	 */
	public abstract boolean isValidMove(int move);

	/*
	 * isValidMove(String) returns True if the move parameter is a valid move
	 * for the curPlayer given the current board configuration
	 */
	public abstract boolean isValidMove(String move);

	/*
	 * getNextPlayer() returns the player next to play after the curPlayer
	 */
	public abstract int getNextPlayer();

	/*
	 * hasWinner() returns the playerCode (-1 or 1) of the winning player;
	 * otherwise, it returns 0
	 */
	public abstract int hasWinner();

	/*
	 * isDraw() returns True if the current board has resulted in a draw
	 */
	public abstract boolean isDraw();

	/*
	 * cloneArray returns a clone of the 2D array parameter, meaning a new array
	 * is created in a new memory address that has the same values as those in
	 * the array parameter
	 */
	public abstract int[][] cloneArray(int[][] arr);

	/*
	 * moveToInt(String) takes a String representation of a move (ex. "a1") and
	 * converts it to an int representation (ex. 0)
	 */
	public static int moveToInt(String move) {

		if (move.length() == 2) {

			int col = (int) move.charAt(0) - ((int) 'a');
			int row = 0;
			try {
				row = Integer.parseInt(move.substring(1)) - 1;
			} catch (NumberFormatException e) {
				return -1;
			}

			if (row >= 0 && row < 8 && col >= 0 && col < 8) {
				return row * 8 + col;
			}
		}

		return -1;
	}

	/*
	 * moveToInt(int) takes an int representation of a move (ex. 0) and converts
	 * it to a String representation (ex. "a1")
	 */
	public static String moveToString(int move) {

		char col = (char) ((int) 'a' + (move % 8));
		return String.valueOf(col) + Integer.toString(1 + move / 8);
	}

	/*
	 * display(PrintStream) displays the current board configuration
	 */
	public void display(PrintStream output) {

		output.println();
		output.println();
		output.println("\t   a b c d e f g h");
		output.println("\t  +---------------+");

		for (int i = 0; i < 64; i++) {
			String row = Integer.toString(1 + i / 8);
			String line = "\t" + row + " |";
			for (int c = 0; c < 8 && i < 64; c++, i++) {
				String p;
				if (board[i / 8][i % 8] == 0) {
					p = " ";
				} else if (board[i / 8][i % 8] == -1) {
					p = "B";
				} else {
					p = "W";
				}

				line += p + "|";
			}
			i--;
			line += " " + row;
			output.println(line);
		}

		output.println("\t  +---------------+");
		output.println("\t   a b c d e f g h");
		output.println();
		output.println();

	}

	public int[][] getBoard() {
		return board;
	}

	public int getCurPlayer() {
		return curPlayer;
	}

	public void setCurPlayer(int aPlayer) {
		curPlayer = aPlayer;

		// reset curPlayerMoves to null to force recalculation
		curPlayerMoves = null;
	}
}
