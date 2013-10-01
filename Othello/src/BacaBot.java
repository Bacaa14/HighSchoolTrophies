
public class BacaBot extends AIBot{

	@SuppressWarnings("static-access")
	public String getMove(int[][] curBoard, int asPlayer) {
		int depth = 5;
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		BacaOthelloNode state = new BacaOthelloNode(curBoard, asPlayer);
		HeurEval w = new BacaHeurVal();
		
		state.getHeurVal(depth, asPlayer, w);
		System.out.println("Old " + state.moveToString(state.getBestMove()));
		state.getHeurValAlphaBeta(depth, asPlayer, w, alpha, beta);
		System.out.println("AB " + state.moveToString(state.getBestMove()));
		//for (int c = 0; c < state.getChildren().size(); c++){
		//	System.out.println("Move " + c + " = " + state.moveToString(state.getChildren().get(c).getPrevMove()));
		//}
		String ret = state.moveToString(state.getBestMove());
		return ret;
	}

}
