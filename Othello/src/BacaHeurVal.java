public class BacaHeurVal extends HeurEval {
	private BacaOthelloState state;

	private double percentBoard() {
		int pos = (state.getBoard().length * state.getBoard()[0].length);
		int total = state.getLocations(state.getCurPlayer()).size();
		double percent = total / pos;
		return percent;
	}
	
	private double percentBoardII() {
		int pos = (state.getBoard().length * state.getBoard()[0].length);
		int total = state.getLocations(state.getCurPlayer()).size();
		double percent = (pos - total) / pos;
		return percent;
	}
	
	private boolean isTime(){
		boolean ret = false;
		if (hasCorners() < 0 || percentBoard() > .5 || hasEdges() > 0){
			ret = true;
		}
		return ret;
	}

	private double expand(double percent, double num) {
		return (((2 * num) * percent) - num);
	}
	
	/*
	 * gives a value between -5 and 5 as a base value determined by the percent
	 * of total places occupied
	 */
	
	private double getBaseValue(){
		if (isTime()){
			return getBaseValueL();
		}
		else{
			return getBaseValueE();
		}
	}
	
	private double getBaseValueL() {
		return expand(percentBoard(), 1);
	}
	
	private double getBaseValueE() {
		return expand(percentBoardII(), 1);
	}

	private double hasCorners() {
		double val = 0;
		int[] corners = new int[] { state.getBoard()[0][0],
				state.getBoard()[0][7], state.getBoard()[7][0],
				state.getBoard()[7][7] };
		for (int c = 0; c < corners.length; c++) {
			if (corners[c] != 0) {
				if (corners[c] == state.getCurPlayer()) {
					val += .375;
				} else if (corners[c] == state.getNextPlayer()) {
					val += -.5;
				}
			}
		}
		return expand(val, 50);
	}

	private double hasEdges() {
		double val = 0;
		int[] edges = new int[] { state.getBoard()[0][1],
				state.getBoard()[0][6], state.getBoard()[1][0],
				state.getBoard()[1][1], state.getBoard()[1][6],
				state.getBoard()[1][7], state.getBoard()[6][0],
				state.getBoard()[6][1], state.getBoard()[6][6],
				state.getBoard()[6][7], state.getBoard()[7][1],
				state.getBoard()[7][6] };
		for (int c = 0; c < edges.length; c++){
			if (edges[c] != 0){
				if (edges[c] == state.getCurPlayer()){
					val += -.375;
				}else if (edges[c] == state.getNextPlayer()){
					val += .25;
				}
			}
		}
		return expand (val, 35);
	}
	
	private double hasBoarders(){
		double val = 0;
		int[] edges = new int[] { state.getBoard()[0][2],
				state.getBoard()[0][3], state.getBoard()[0][4],
				state.getBoard()[0][5], state.getBoard()[7][2],
				state.getBoard()[7][3], state.getBoard()[7][4],
				state.getBoard()[7][5], state.getBoard()[2][0],
				state.getBoard()[3][0], state.getBoard()[4][0],
				state.getBoard()[5][0], state.getBoard()[2][7],
				state.getBoard()[3][7], state.getBoard()[4][7],
				state.getBoard()[5][7]};
		for (int c = 0; c < edges.length; c++){
			if (edges[c] != 0){
				if (edges[c] == state.getCurPlayer()){
					val += .25;
				}else if (edges[c] == state.getNextPlayer()){
					val += -.25;
				}
			}
		}
		return expand (val, 30);
	}
	
	private double hasEB(){
		double val = 0;
		int[] edges = new int[] { state.getBoard()[1][2],
				state.getBoard()[1][3], state.getBoard()[1][4],
				state.getBoard()[1][5], state.getBoard()[6][2],
				state.getBoard()[6][3], state.getBoard()[6][4],
				state.getBoard()[6][5], state.getBoard()[2][1],
				state.getBoard()[3][1], state.getBoard()[4][1],
				state.getBoard()[5][1], state.getBoard()[2][6],
				state.getBoard()[3][6], state.getBoard()[4][6],
				state.getBoard()[5][6]};
		for (int c = 0; c < edges.length; c++){
			if (edges[c] != 0){
				if (edges[c] == state.getCurPlayer()){
					val += -.175;
				}else if (edges[c] == state.getNextPlayer()){
					val += +.25;
				}
			}
		}
		return expand (val, 10);
	}
	
//	private double skipper(){
//		//try to get other player to not have a move
//	}

	public int getHeurVal(int[][] board, int player) {
		int heurVal = 0;
		state = new BacaOthelloState(board, player); // is this the right
		// player??
		if (state.hasWinner() != 0 || state.isDraw()) {
			if (state.isDraw()) {
				return 0;
			} else {
				// is player right??
				if (state.hasWinner() == player) {
					return Integer.MAX_VALUE;
				} else {
					return Integer.MIN_VALUE;
				}
			}
		} else {
			
			
			
			
			heurVal += getBaseValueL();
			heurVal += hasCorners();
			heurVal += hasEdges();
			heurVal += hasBoarders();
			heurVal += hasEB();
			return heurVal;
			//return getHeur();
		}
	}
	
	/*public int getTime(){
		int total = state.getLocations(0).size();
		return total;
	}*/
	
	/*public int getHeur(){
		double heur;
		//early:
		if (getTime() >= 53){
			heur += getEarlyMobility(); //goes for the most possible moves, and the other player to have no moves
			heur += getEarlyCorners(); //wants corners
			heur += getEarlyMost(); //general heuristic value of squares
			heur += getEarlyCornerStability(); //dont get near corner/ want him near corner
		}
		//majority:
		else if (getTime() > 13){
			heur += getMobility(); //goes for the most possible moves, and the other player to have no moves
			heur += getCorners(); //wants corners/ doesnt want to give them up
			heur += getMost(); //general heuristic value of squares
			heur += getCornerStability(); //dont get near corner/ want him near corner
		}
		//late:
		else{
			heur += getLateMobility(); //goes for the most possible moves, and the other player to have no moves
			heur += getLateCorners(); //wants corners
			heur += getLateMost(); //general heuristic value of squares
			heur += getLateCornerStability(); //dont get near corner/ want him near corner
		}
		return ((int)heur);
	}
	
	private double getEarlyMobility(){
		
	}
	
	private double getEarlyCorners(){
		
	}
	
	private double getEarlyMost(){
		
	}
	
	private double getEarlyCornerStability(){
		
	}
	
	private double getMobility(){
		
	}
	
	private double getCorners(){
		
	}
	
	private double getMost(){
		
	}
	private double
	private double
	private double
	private double
	private double*/
	
}
