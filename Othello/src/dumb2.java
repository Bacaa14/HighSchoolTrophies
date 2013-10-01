
public class dumb2 extends HeurEval{
	private BacaOthelloState state;

	public int getHeurVal(int[][] board, int player) {
		state = new BacaOthelloState(board, player);
		int[][]heurVals = {
		{10,3,8,8,8,8,3,10},
		{3,0,1,1,1,1,0,3},
		{8,1,4,4,4,4,1,8},
		{8,1,4,4,4,4,1,8},
		{8,1,4,4,4,4,1,8},
		{8,1,4,4,4,4,1,8},
		{3,0,1,1,1,1,0,3},
		{10,3,8,8,8,8,3,10},
	};
		int heurVal = (getVal(heurVals));
		return heurVal;
	}
	
	public int getVal(int[][] heur){
		int Player = 0;
		int Other = 0;
		int ret = 0;
		int[][]board = state.cloneArray(state.getBoard());
		for (int c = 0; c < heur.length; c++){
			for (int d = 0; d < heur[0].length; d++){
				int cur = board[c][d];
				if (cur == state.getCurPlayer()){
					Player += heur[c][d];
				}
				else if (cur == state.getNextPlayer()){
					Other += heur[c][d];
				}
			}
		}
		
		ret = Player - Other;
		return ret;
	}
}
