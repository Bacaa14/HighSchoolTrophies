import java.util.ArrayList;


public class StateTester {

	public static void main(String[] args) {
		int[][] bd = {	
				{1,0,0,0,0,0,0,0},
				{-1,0,0,0,0,0,0,0},
				{0,0,0,-1,0,0,0,0},
				{0,0,0,-1,-1,0,0,0},
				{0,0,0,-1,1,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
			};
		BacaOthelloNode st = new BacaOthelloNode(bd,1);
		System.out.println(st.isValidMove(16));
		/*System.out.println(st.hasWinner());
		ArrayList<Integer> s = st.getPossibleMoves();
		for (int c = 0; c < s.size(); c++){
			System.out.println(s.get(c));
		}*/
		
		/*bd = st.makeBoard(19);
		
		for (int c = 0; c < bd.length; c++){
			for (int d = 0; d < bd[0].length; d++){
				System.out.print(bd[c][d] + ",");
			}
			System.out.println();
		}*/
		
		//ArrayList<BacaOthelloNode> t = st.makeChildren();
		//System.out.println("2nd:");
		//t.get(0).makeChildren();
		/*for (int e = 0; e < t.size(); e++){
			bd = t.get(e).board;
			System.out.println("The board for the " + e + "th child is:");
			for (int c = 0; c < bd.length; c++){
				for (int d = 0; d < bd[0].length; d++){
					System.out.print(bd[c][d] + ",");
				}
				System.out.println();
			}
		}*/
	}

}
