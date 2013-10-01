/* 
 * HeurEval.java
 * 
 * HeurEval is simply a wrapper for sub classes that have 
 * heuristic evaluation for a particular game
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * 2009
*/
public abstract class HeurEval {
	
	/*
	 * getHeurVal(int[][], int) calcualtes the heuristic score of a given board configuration for a 
	 * given player
	 */
	public abstract int getHeurVal(int[][] board, int player);

}
