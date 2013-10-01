
public class Bot3 extends Player{
	
	@SuppressWarnings("static-access")
	public String getMove(int[][] curBoard, int asPlayer) {
		int depth = 6;
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		BacaOthelloNode state = new BacaOthelloNode(curBoard, asPlayer);
		HeurEval w = new dumb2();
		state.getHeurValAlphaBeta(depth, asPlayer, w, alpha, beta);
		//for (int c = 0; c < state.getChildren().size(); c++){
		//	System.out.println("Move " + c + " = " + state.moveToString(state.getChildren().get(c).getPrevMove()));
		//}
		String ret = state.moveToString(state.getBestMove());
		return ret;
	}
}
