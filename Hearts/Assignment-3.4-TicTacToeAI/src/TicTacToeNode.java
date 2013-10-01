/*
 * TicTacToeNodejava
 * This class both extends TicTacToeState and implements GameNodeI. You will implement the required methods.
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 * Modified by:  Alexander Baca
 */

import java.util.ArrayList;

public class TicTacToeNode extends TicTacToeState implements GameNodeI {

	// variables used throughout class stated
	protected int prevMove = -1;
	protected int bestMove = -1;
	protected ArrayList<TicTacToeNode> children = new ArrayList<TicTacToeNode>();

	//costructs the node
	public TicTacToeNode(int aPlayer, int aPrevMove) {

		curPlayer = aPlayer;
		prevMove = aPrevMove;
		winCondition = board.length;
	}

	//costructs the node
	public TicTacToeNode(int aPlayer, int aPrevMove, int[][] theBoard) {

		curPlayer = aPlayer;
		prevMove = aPrevMove;

		board = theBoard;
		if (board.length <= board[0].length) {
			winCondition = board.length;

		} else {
			winCondition = board[0].length;
		}
	}

	//costructs the node
	public TicTacToeNode(int aPlayer, int aPrevMove, int[][] theBoard,
			int awinCondition) {

		curPlayer = aPlayer;
		prevMove = aPrevMove;

		board = theBoard;
		winCondition = awinCondition;
	}

	//returns the best move for this node
	public int getBestMove() {
		return bestMove;
	}

	//this helper method gets the heur value of a leaf fpr the getHeurVal method
	public int getGameNodeHeurVal(int rootPlayer) {
		int otherPlayer = rootPlayer * -1;
		//if the leaf is a win for the root player it is good (high heur val)
		if (hasWinner() == rootPlayer) {
			return 100;
		} 
		//if the leaf isnt a win for the root player it is bad (low heur val)
		else if (hasWinner() == otherPlayer) {
			return -100;
		}
		//otherwise it is a tie and is neurtral (heur val of 0)
		else{
			return 0;
		}

	}

	//gets the heur value of the node looking at the children
	public int getHeurVal(int depth, int rootPlayer) {
		//if the node isnt a leaf
		if (depth > 0 && hasWinner() == 0 && !isDraw()) {
			//creates the array list to hold the heur vals
			ArrayList<Integer> heurVals = new ArrayList<Integer>();
			//creates children
			makeChildren();
		
			//creates the heur vlas of the children
			for (int c = 0; c < children.size(); c++) {
				heurVals.add(children.get(c).getHeurVal((depth - 1), rootPlayer));
			}
			//initializes the best move and best heur value with the first child
			int highest = heurVals.get(0);
			bestMove = children.get(0).getPrevMove();

			//loops through all the heur vals
			for (int d = 0; d < heurVals.size(); d++) {
				//if it is the rootplayers turn then we look for the best possible move
				if (curPlayer == rootPlayer) {
					if (heurVals.get(d) > highest) {
						highest = heurVals.get(d);
						bestMove = children.get(d).getPrevMove();
						System.out.println("best move = " + bestMove + " at " + depth);
					}
				} 
				//if it is the other players move we assume he will take his best pssible move
				else if (curPlayer == rootPlayer * -1) {
					if (heurVals.get(d) < highest) {
						highest = heurVals.get(d);
						bestMove = children.get(d).getPrevMove();
						System.out.println("best move = " + bestMove + " at " + depth);
					}
				}
				
			}
			//returns the best possible move's heur val
			return highest;
		}

		//if the node is a leaf, we return the heur val of the leaf
		else{
			return getGameNodeHeurVal(rootPlayer);
		}
		

	}

	//returns the prev move
	public int getPrevMove() {
		return prevMove;
	}

	//creates the possible move array list and passes it into the method that makes the children
	public void makeChildren() {
		ArrayList<Integer> posMoves = getPossibleMoves(curPlayer);
		makeChildren(posMoves);

	}

	//constructs the children array list using the make child method
	public void makeChildren(ArrayList<Integer> posMoves) {
		//loops through all the pos moves and creates children form each one
		for (int c = 0; c < posMoves.size(); c++) {
			TicTacToeNode childI = makeChild(posMoves.get(c));
			children.add(childI);
		}

	}

	//makes a child given a possible move
	public TicTacToeNode makeChild(int move) {

		//creates a clone of the board
		int[][] Board = new int[board.length][board[0].length];
		Board = clone();

		//gets the value of the other player
		int Player = getNextPlayer();

		//gets the col and row indexes
		int col = (move - 1) % board[0].length;
		int row = ((move - col) / board[0].length);
		//sets the spot that the move int reps to the curent players value
		Board[row][col] = curPlayer;

		//creates a child node using the new board, the next player, the prev move, and the win condition
		TicTacToeNode child = new TicTacToeNode(Player, move, Board,
				winCondition);
		//returns the child
		return child;

	}

	//returns the children array
	public ArrayList<TicTacToeNode> getChildren() {
		return children;
	}

}
