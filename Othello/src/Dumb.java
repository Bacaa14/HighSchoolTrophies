public class Dumb extends HeurEval {
	private BacaOthelloState state;

	public int getHeurVal(int[][] board, int player) {
		state = new BacaOthelloState(board, player);
		int[][]heurVals = {
		{20,3,8,8,8,8,3,20},
		{3,0,1,1,1,1,0,3},
		{8,1,4,4,4,4,1,8},
		{8,1,4,4,4,4,1,8},
		{8,1,4,4,4,4,1,8},
		{8,1,4,4,4,4,1,8},
		{3,0,1,1,1,1,0,3},
		{20,3,8,8,8,8,3,20},
	};
		int heurVal;
		if (getTime() > 12){
		heurVal = (getVal(heurVals)/2 + getMobility());
		}
		else{
			heurVal = getMost();
		}
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
	
	public int getMobility(){
		int moves = state.getPossibleMoves().size();
		if (!state.hasMove()){
			return -100000000;
		}
		else if (!state.otherHasMove()){
			return 100000000;
		}
		else{
			return moves * (2 / 3);
		}
	}
	
	public int getTime(){
		int total = state.getLocations(0).size();
		return total;
	}
	
	public int getMost(){
		return (state.getLocations(state.getCurPlayer()).size() - state.getLocations(state.getNextPlayer()).size());
	}

}
