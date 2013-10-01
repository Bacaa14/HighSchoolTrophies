/*
 * TicTacToeStatejava
 * This class is the game-specific implementation of the GameStateI interface. You will implement the required methods.
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 * Modified by:  Alexander Baca
 */

import java.io.PrintStream;
import java.util.ArrayList;

public class TicTacToeState implements GameStateI {

	// variables used throughout class stated
	protected int winCondition;
	protected int curPlayer;
	/*
	 * curPlayer = -1 represents X curPlayer = 1 represents O
	 */

	// tic tac toe board for this state
	protected int[][] board;

	// constructs the state
	public TicTacToeState() {
		board = new int[3][3];
		winCondition = board.length;
	}

	// constructs the state
	public TicTacToeState(int aPlayer) {
		board = new int[3][3];
		winCondition = board.length;
		curPlayer = aPlayer;
	}

	// constructs the state
	public TicTacToeState(int aPlayer, int[][] aBoard) {
		curPlayer = aPlayer;
		board = aBoard;
		if (board.length <= board[0].length) {
			winCondition = board.length;

		} else {
			winCondition = board[0].length;
		}
	}

	// constructs the state
	public TicTacToeState(int aPlayer, int[][] aBoard, int aCondition) {
		winCondition = aCondition;
		curPlayer = aPlayer;
		board = aBoard;
	}

	// displays the board
	public void display(PrintStream output) {
		// wont start if the win condition is impossible in the board given
		if (winCondition > board.length && winCondition > board[0].length) {
			output.println("It is impossible to win under these conditons");
		}

		else {
			// Reference for the printing of the chars
			char A = 'A';
			// spacing before the chars
			output.print("    ");
			// prints the chars
			for (int c = 0; c < board[0].length; c++) {
				output.print(((char) (A + c)) + "   ");
			}
			// spacing
			output.println();
			// prints the board
			for (int c = 0; c < board.length; c++) {
				for (int d = 0; d < board[0].length; d++) {
					// if it is the beginning of a row
					if (d == 0) {
						if (board[c][d] == 0) {
							output.print((c + 1) + "    ");
							output.print(" | ");
						} else if (board[c][d] == -1) {
							output.print((c + 1) + "   X");
							output.print(" | ");
						} else if (board[c][d] == 1) {
							output.print((c + 1) + "   O");
							output.print(" | ");
						}
					}
					// if it is the end of a row
					else if (d < board[0].length - 1) {
						if (board[c][d] == 0) {
							output.print(" ");
							output.print(" | ");
						} else if (board[c][d] == -1) {
							output.print("X");
							output.print(" | ");
						} else if (board[c][d] == 1) {
							output.print("O");
							output.print(" | ");
						}
					}
					// other parts of the row
					else {
						if (board[c][d] == 0) {
							output.print(" ");
						} else if (board[c][d] == -1) {
							output.print("X");
						} else if (board[c][d] == 1) {
							output.print("O");
						}
					}
				}
				// spacing
				output.println();
				// Determines if a line is necessary
				if (c < board.length - 1) {
					// spacing
					output.print("   ");
					// prints out a line depending on how big the board is
					for (int e = 0; e < board[0].length; e++) {
						if ((e + 1) == board.length) {
							output.print("---");
						} else {
							output.print("----");
						}
					}
					// spacing
					output.println();
				}
			}
			// spacing
			output.println();
		}
	}

	// returns the next player
	public int getNextPlayer() {
		return curPlayer * -1;
	}

	// returns an arraylist of possible moves
	public ArrayList<Integer> getPossibleMoves(int Player) {
		// creates an arraylist
		ArrayList<Integer> posMoves = new ArrayList<Integer>();
		// initializes a ref variable
		int space = 0;
		// loops through the board and adds the location of all the spaces with
		// no value (0)
		for (int c = 0; c < board.length; c++) {
			for (int d = 0; d < board[0].length; d++) {
				// if the spot is = to 0 it adds it to the list
				if (board[c][d] == 0) {
					posMoves.add((Integer) (space + 1));
				}
				space++;
			}
		}
		// returns the array
		return posMoves;
	}

	// Returns true if there is a move and false if there isn't
	public boolean hasMove() {
		ArrayList<Integer> posMoves = getPossibleMoves(0);
		if (posMoves.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	// determines if there is a winner
	public int hasWinner() {
		// initializes variables
		int hasWinner = 0;
		boolean hasWinnerTester = false;

		// if it is impossible to win it returns no winner (0)
		if (winCondition > board.length && winCondition > board[0].length) {
			return 0;
		}

		// loops through the rows to see if there is any sequence of the number
		// "winCondition" in a row
		for (int c = 0; c < board.length && hasWinnerTester == false; c++) {
			for (int d = winCondition; d < (board[0].length + 1)
					&& hasWinnerTester == false; d++) {
				int e = d - winCondition;
				while (e < (d - 1) && board[c][e] == board[c][(e + 1)]) {
					e++;
				}
				// if any "winCodition" in a row starting from the one given in
				// the nested loop produces "winCodition" in a row (determined
				// by e( generated in the while loop above)) then we return the
				// value of that initial board spot ( given its not 0) and set
				// the tester to true so the method will skip to the end and
				// return it)
				if (e == winCondition - 1 && board[c][(d - winCondition)] != 0) {
					hasWinner = board[c][(d - winCondition)];
					hasWinnerTester = true;
				}
			}
		}

		// loops through the columns to see if there is any sequence of the
		// number "winCondition" in a column
		for (int c = 0; c < board[0].length && hasWinnerTester == false; c++) {
			for (int d = winCondition; d < (board.length + 1)
					&& hasWinnerTester == false; d++) {
				int e = d - winCondition;
				while (e < (d - 1) && board[e][c] == board[(e + 1)][c]) {
					e++;
				}
				// if any "winCodition" in a column starting from the one given
				// in the nested loop produces "winCodition" in a column
				// (determined by e( generated in the while loop above)) then we
				// return the value of that initial board spot ( given its not
				// 0) and set the tester to true so the method will skip to the
				// end and return it)
				if (e == winCondition - 1 && board[(d - winCondition)][c] != 0) {
					hasWinner = board[(d - winCondition)][c];
					hasWinnerTester = true;
				}
			}
		}

		// loops through the diagnals to see if there is any sequence of the
		// number "winCondition" in a diagnal
		for (int c = 0; c < board.length - (winCondition - 1)
				&& hasWinnerTester == false; c++) {
			for (int d = 0; d < board[0].length && hasWinnerTester == false; d++) {
				if (board[c][d] != 0) {
					int e = 0;
					int f = 0;
					if (d - (winCondition - 1) >= 0) {
						while (e < (board.length - (c + 1))
								&& e < (winCondition - 1)
								&& board[c][d] == board[c + (e + 1)][d
										- (e + 1)]) {
							e++;
						}
					}
					if (d + (winCondition - 1) < board[0].length) {
						while (f < (board.length - (c + 1))
								&& e < (winCondition - 1)
								&& board[c][d] == board[c + (f + 1)][d
										+ (f + 1)]) {
							f++;
						}
					}
					// if any "winCodition" in a column starting from the one
					// given in the nested loop produces "winCodition" in a
					// column (determined by e and f (one for each direction of
					// diagonal)( generated in the while loops above)) then we
					// return the value of that initial board spot ( given its
					// not 0) and set the tester to true so the method will skip
					// to the end and return it)
					if (e == (winCondition - 1) || f == (winCondition - 1)) {
						hasWinner = board[c][d];
						hasWinnerTester = true;
					}
				}
			}
		}

		// returns the has winner int
		return hasWinner;
	}

	// retuns true if it is a draw and false otherwise
	public boolean isDraw() {
		// if there is no winner
		if (hasWinner() == 0) {
			// initializes variable
			int tester = 0;
			// loops through board to see if there are any empty spaces
			for (int c = 0; c < board.length; c++) {
				for (int d = 0; d < board[0].length; d++) {
					if (board[c][d] == 0) {
						tester++;
					}
				}
			}
			// if there are no empty spaces it returns true
			if (tester == 0) {
				return true;
			}
			// otherwise returns false
			else {
				return false;
			}
		}
		// if the game has a winner returns false
		else {
			return false;
		}
	}

	// determines if a given move (in integer form) is empty
	public boolean isValidMove(int move) {
		// initializes ref variable
		int spot = 1;
		// loops through board and if the move given is empty returns 0
		for (int c = 0; c < board.length; c++) {
			for (int d = 0; d < board[0].length; d++) {
				if (spot == move) {
					if (board[c][d] == 0) {
						return true;
					}
				}
				spot++;
			}
		}
		// else it returns false
		return false;
	}

	// determines if a given move (in string form) is empty
	public boolean isValidMove(String move) {
		// converts the string to integer form
		int c = moveToInt(move);
		// calls the integer form of this method and returns that
		return isValidMove(c);
	}

	// makes a child given a move in integer form
	public GameStateI makeChild(int move) {
		// if the move is valid it makes a child
		if (isValidMove(move)) {

			// clones the board
			int[][] Board = new int[board.length][board[0].length];
			Board = board.clone();

			// gets the next player
			int Player = getNextPlayer();

			// puts the new move in the board
			int col = (move - 1) % board[0].length;
			int row = ((move - col) / board[0].length);
			Board[row][col] = curPlayer;

			// creates a new TicTacToeState with the prev info and the win
			// condition
			TicTacToeState child = new TicTacToeState(Player, Board,
					winCondition);
			// returns the new TicTacToeState
			return child;
		}
		// if the move is invalid it returns null
		else {
			return null;
		}
	}

	// makes a child given a move in string form
	public GameStateI makeChild(String Move) {
		// converts the string to integer form
		int move = moveToInt(Move);
		// calls the integer form of this method and returns that
		return makeChild(move);
	}

	// converts a string move into integer form
	public int moveToInt(String move) {
		// gets the letter
		char col = move.charAt(0);
		// gets the number
		String row = ((move.substring(1)));
		// refrence boolean
		boolean equals = false;
		// initializes variable
		int rowI = 0;
		// loops through difrent string values created from an int till it
		// equals the given number part of the string, then it saves the number
		for (int c = 0; equals == false; c++) {
			String inte = c + "";
			if (inte.equals(row)) {
				rowI = c;
				equals = true;
			}
		}
		// gets the number of colums from the char assuming that 'A' is the
		// first one
		int colI = (col - 'A') + 1;
		// determines the number to use given the number of rows
		int c = ((rowI - 1) * board[0].length);
		// creates the integer index
		int index = colI + c;
		// returns the integer index
		return index;
	}

	// converts a integer move into string form
	public String moveToString(int move) {
		// gets the row and collum indexes from the int
		int col = (move - 1) % board[0].length;
		int row = ((move - col) / board[0].length) + 1;
		// converts the int to the cahr part of the board
		char colC = (char) ('A' + col);
		// creates the string index
		String index = colC + "" + row;
		// retuns the index
		return index;
	}

	// Unnecessary method for this project required by the interface
	public void printScore(PrintStream output) {

	}

	// returns the board
	public int[][] getBoard() {
		return board;
	}

	// clones the board
	public int[][] clone() {
		// creates a new board the same size as the old
		int[][] Board = new int[board.length][board[0].length];
		// gives it the same values
		for (int c = 0; c < board.length; c++) {
			for (int d = 0; d < board[0].length; d++) {
				Board[c][d] = board[c][d];
			}
		}
		// returns the clone
		return Board;
	}
}
