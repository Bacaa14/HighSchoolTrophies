
public class DumbBot extends AIBot{
	
	@SuppressWarnings("static-access")
	public String getMove(int[][] curBoard, int asPlayer) {
		int depth = 6;
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		BacaOthelloNode state = new BacaOthelloNode(curBoard, asPlayer);
		HeurEval w = new Dumb();
		System.out.println(state.getLocations(0).size());
		if (state.getLocations(0).size() <= 12){
			depth = 12;
		}
		state.getHeurValAlphaBeta(depth, asPlayer, w, alpha, beta);
		String ret = state.moveToString(state.getBestMove());
		return ret;
	}
}
