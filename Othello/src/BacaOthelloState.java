import java.util.ArrayList;

public class BacaOthelloState extends OthelloState {

	/*
	 * protected int[][] board; protected int curPlayer;
	 * 
	 * protected ArrayList<Integer> curPlayerMoves = null;
	 * 
	 * protected static int N = -8; protected static int S = 8; protected static
	 * int E = 1; protected static int W = -1;
	 */

	public BacaOthelloState(int[][] bd, int cp) {
		curPlayer = cp;
		board = bd;
	}

	public int[][] cloneArray(int[][] arr) {
		int[][] ret = new int[arr.length][arr[0].length];
		for (int c = 0; c < arr.length; c++) {
			for (int d = 0; d < arr[0].length; d++) {
				ret[c][d] = arr[c][d];
			}
		}

		return ret;
	}

	public int getNextPlayer() {
		return (curPlayer * -1);
	}

	public ArrayList<Integer> getPossibleMoves() {
		ArrayList<Integer> ret = getLocations(0);
		for (int c = (ret.size() - 1); c >= 0; c--) {
			if (!isValidMove(ret.get(c))) {
				ret.remove(c);
			}
		}
		return ret;
	}

	public ArrayList<Integer> getLocations(int index) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int loc = 0;
		for (int c = 0; c < board.length; c++) {
			for (int d = 0; d < board.length; d++) {
				if (board[c][d] == index) {
					ret.add(loc);
				}
				loc++;
			}
		}
		return ret;
	}

	public boolean hasMove() {
		return (getPossibleMoves().size() > 0);
	}

	protected boolean otherHasMove() {
		curPlayer = getNextPlayer(); // chk
		boolean ret = (getPossibleMoves().size() > 0);
		curPlayer = getNextPlayer(); // chk
		return ret;
	}

	public int hasWinner() {
		if (isGameOver()) {
			if (!isDraw()) {
				if (getLocations(1).size() > getLocations(-1).size()) {
					return 1;
				}

				else {
					return -1;
				}
			}
		}
		return 0;
	}

	public boolean isDraw() {
		if (isGameOver()) {
			if (getLocations(1) == getLocations(-1)) {
				return true;
			}
		}
		return false;

	}

	protected boolean isGameOver() {
		if (!hasMove() && !otherHasMove()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidMove(int move) {
		boolean ret = false;
		ArrayList<Integer> dir = getDirs();
		for (int c = 0; c < dir.size(); c++) {
			//System.out.println();
			//System.out.println(dir.get(c));
			if (testD(move, dir.get(c))) {
				//System.out.println("TestD == true");
				if (directionalTester(move, dir.get(c))) {
					//System.out.println("directionalTester == true");
					ret = true;
				}
			}
		}
		return ret;
	}

	public boolean isValidMove(String move) {
		boolean ret = false;
		ArrayList<Integer> dir = getDirs();
		for (int c = 0; c < dir.size(); c++) {
			if (directionalTester(moveToInt(move), dir.get(c))) {
				ret = true;
			}
		}
		return ret;
	}

	protected ArrayList<Integer> getDirs() {
		ArrayList<Integer> dir = new ArrayList<Integer>();
		dir.add(N);
		dir.add(S);
		dir.add(E);
		dir.add(W);
		dir.add(N + E);
		dir.add(N + W);
		dir.add(S + E);
		dir.add(S + W);
		return dir;
	}

	protected boolean testD(int move, int Direction) {
		int t = move + Direction;
		//System.out.println("t == " + t);
		boolean ret = false;
		if (t >= 0 && t < 64) {
			//System.out.println("in bounderies");
			if (getVal(t) == getNextPlayer()) {
				//System.out.println("is next to enemy piece");
				ret = true;
			}
		}
		//System.out.println("in bounderies");
		if (ret) {
			//System.out.println("ret == true");
			if (isBoarderD(move, Direction)) {
				//System.out.println("boundery == true; ret set to false!!");
				ret = false;
			}
		}
		return ret;
	}

	private boolean isBoarder(int move) {
		if (getCol(move) == 0 || getCol(move) == 7 || getRow(move) == 0
				|| getRow(move) == 7) {
			//System.out.println("First is Returning True");
			//System.out.println();
			return true;
		} else {
			return false;
		}
	}

	private boolean isBoarderD(int move, int Direction) {
		
		if (isBoarder(move) && isBD(move, Direction)) {
			//System.out.println("chkkkkkkkkkkkkch " + isBD(move, Direction));
			//System.out.println("Third is Returning True");
			//System.out.println();
			return true;
		} else {
			return false;
		}
	}

	private boolean isBD(int move, int Direction) {
		//System.out.println("Col = " + getCol(move));
		//System.out.println("Row = " + getRow(move));
		//System.out.println("Dir = " + Direction);
		//System.out.println();
		if (getCol(move) == 0) {
			//System.out.println("chk = 0, move = " + moveToString(move));
			if (getRow(move) == 0) {
				
				if (Direction == N || Direction == N + E || Direction == S + W
						|| Direction == W || Direction == N + W) {
					//System.out.println("Second is Returning True");
					//System.out.println();
					return true;
				} else {
					return false;
				}
			} else if (getRow(move) == 7) {
				if (Direction == N + W || Direction == S + E || Direction == S
						|| Direction == S + W || Direction == W) {
					//System.out.println("Second is Returning True");
					//System.out.println();
					return true;
				} else {
					return false;
				}

			} else {
				//System.out.println("Chk = 01, move = " + moveToString(move));
				if (Direction == S + W || Direction == N + W || Direction == W) {
					//System.out.println("Second is Returning True");
					//System.out.println();
					return true;
				} else {
					return false;
				}
			}
		} else if (getCol(move) == 7) {
			//System.out.println("Chk = 7, move = " + moveToString(move));
			if (getRow(move) == 0) {
				if (Direction == N + E || Direction == N || Direction == N + W
						|| Direction == E || Direction == S + E) {
					//System.out.println("Second is Returning True");
					//System.out.println();
					return true;
				} else {
					return false;
				}
			} else if (getRow(move) == 7) {
				if (Direction == S + E || Direction == S || Direction == S + W
						|| Direction == E || Direction == N + E) {
					//System.out.println("Second is Returning True");
					//System.out.println();
					return true;
				} else {
					return false;
				}
			} else {
				//System.out.println("Chk = 71, move = " + moveToString(move));
				if (Direction == N + E || Direction == E || Direction == S + E) {
					//System.out.println("Second is Returning True");
					//System.out.println();
					return true;
				} else {
					return false;
				}
			}
		} else if (getRow(move) == 0) {
			if (Direction == N || Direction == N + W || Direction == N + E) {
				//System.out.println("Second is Returning True");
				//System.out.println();
				return true;
			} else {
				return false;
			}
		} else if (getRow(move) == 7) {
			if (Direction == S || Direction == S + W || Direction == S + E) {
				//System.out.println("Second is Returning True");
				//System.out.println();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	protected boolean directionalTester(int move, int Direction) {
		/*System.out.println();
		System.out.println("------------");
		System.out.println("in Directional tester:");*/

		int t = move + Direction;
		//System.out.println("t == " + t);

		if (t >= 0 && t < 64) {
			//System.out.println("t is valid");

			int MV = getVal(t);
			if (MV == 0 || (MV == getNextPlayer() && isBoarderD(t, Direction))) {
				//System.out.println("MV == 0? (" + MV + ") OR isBoarderD == false");
				return false;
			} else if (MV == getNextPlayer()) {
				//System.out.println("keep going");
				return directionalTester(t, Direction);
			} else {
				/*System.out.println("bingo");
				System.out.println("end method");
				System.out.println("---------");
				System.out.println();*/
				return true;
			}
		} else {
			/*System.out.println("MV invalid");
			System.out.println("end method");
			System.out.println("---------");
			System.out.println();*/
			return false;
		}
	}

	protected int getVal(int index) {
		int ret = -1;
		int loc = 0;
		for (int c = 0; c < board.length; c++) {
			for (int d = 0; d < board.length; d++) {
				if (loc == index) {
					ret = board[c][d];
				}
				loc++;
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

}