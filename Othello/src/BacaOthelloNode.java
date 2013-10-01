import java.util.ArrayList;

public class BacaOthelloNode extends BacaOthelloState implements GameNodeI {
	private int bestMove;
	private int prevMove;
	private ArrayList<BacaOthelloNode> children;

	public BacaOthelloNode(int[][] bd, int cp) {
		super(bd, cp);
	}

	public BacaOthelloNode(int[][] bd, int cp, int pm) {
		super(bd, cp);
		prevMove = pm;
	}

	public int getBestMove() {
		return bestMove;
	}

	public int getGameNodeHeurVal(int rootPlayer, HeurEval w) {
		return w.getHeurVal(board, rootPlayer);
	}

	public int getHeurVal(int depth, int rootPlayer, HeurEval w) {
		if (depth > 0 && hasWinner() == 0 && !isDraw()) {
			ArrayList<Integer> heurVals = new ArrayList<Integer>();
			setChildren();

			int highest = 0;
			if (hasMove()) {
				for (int c = 0; c < children.size(); c++) {
					heurVals.add(children.get(c).getHeurVal((depth - 1),
							rootPlayer, w));
				}
				highest = heurVals.get(0);
				bestMove = children.get(0).getPrevMove();
			} else {
				curPlayer = getNextPlayer();
				setChildren();
				for (int c = 0; c < children.size(); c++) {
					heurVals.add(children.get(c).getHeurVal((depth - 1),
							rootPlayer, w));
				}
				highest = heurVals.get(0);
				bestMove = children.get(0).getPrevMove();
			}
			for (int d = 0; d < heurVals.size(); d++) {

				if (curPlayer == rootPlayer) {

					if (heurVals.get(d) > highest) {
						highest = heurVals.get(d);
						bestMove = children.get(d).getPrevMove();
						// System.out.println("best move = " + bestMove +
						// " at "
						// + depth);
					}
				}

				else if (curPlayer == rootPlayer * -1) {

					if (heurVals.get(d) < highest) {
						highest = heurVals.get(d);
						bestMove = children.get(d).getPrevMove();
						// System.out.println("best move = " + bestMove +
						// " at "
						// + depth);
					}
				}

			}

			return highest;
		}

		else {
			return getGameNodeHeurVal(rootPlayer, w);
		}
	}

	public int getHeurValAlphaBeta(int depth, int rootPlayer, HeurEval w,
			int alpha, int beta) {
		if (depth > 0 && hasWinner() == 0 && !isDraw()) {
			setChildren();

			if (!hasMove()) {
				curPlayer = getNextPlayer();
				setChildren();
			}
			
			if (curPlayer == rootPlayer) {
				for (int c = 0; c < children.size() && !(alpha >= beta); c++) {
					int state = children.get(c).getHeurValAlphaBeta(
							(depth - 1), rootPlayer, w, alpha, beta);
					if (state > alpha) {
						alpha = state;
						bestMove = children.get(c).getPrevMove();
					}
				}

				return alpha;
			}

			else {
				for (int c = 0; c < children.size() && !(alpha >= beta); c++) {
					int state = children.get(c).getHeurValAlphaBeta(
							(depth - 1), rootPlayer, w, alpha, beta);
					if (state < beta){
						beta = state;
						bestMove = children.get(c).getPrevMove();
					}
				}
				return beta;
			}

		}

		else {
			return getGameNodeHeurVal(rootPlayer, w);
		}
	}

	public int getPrevMove() {
		return prevMove;
	}

	public void setChildren() {
		children = makeChildren();
	}

	public ArrayList<BacaOthelloNode> makeChildren() {
		ArrayList<Integer> posMoves = getPossibleMoves();
		ArrayList<BacaOthelloNode> ret = new ArrayList<BacaOthelloNode>();
		for (int c = 0; c < posMoves.size(); c++) {
			ret.add(makeChild(posMoves.get(c)));
		}
		return ret;
	}

	private BacaOthelloNode makeChild(int move) {
		int[][] bd = makeBoard(move);
		BacaOthelloNode child = new BacaOthelloNode(bd, getNextPlayer(), move);
		return child;
	}

	public int[][] makeBoard(int move) {
		int[][] ret = cloneArray(board);
		ArrayList<Integer> dir = getDirs();
		ret[getRow(move)][getCol(move)] = curPlayer;
		for (int c = 0; c < dir.size(); c++) {
			if (testD(move, dir.get(c))) {
				if (directionalTester(move, dir.get(c))) {
					int t = move + dir.get(c);
					while (getVal(t) == getNextPlayer()) {
						ret[getRow(t)][getCol(t)] = curPlayer;
						t += dir.get(c);
					}
				}
			}
		}

		return ret;
	}

	private int getRow(int move) {
		return (move / 8);
	}

	private int getCol(int move) {
		return (move % 8);
	}

	public void setPrevMove(int move) {
		prevMove = move;
	}

	public ArrayList<BacaOthelloNode> getChildren() {
		return children;
	}

}
