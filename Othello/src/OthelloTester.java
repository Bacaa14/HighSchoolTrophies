//NOTES:

// 1) At the end of the game do i need to cancel the win and loss HeurVals? OR Do i need to change it to adding the heur vals together ;; How would I make it understand that it needs to go towards the greatest chance of winning...??
// 2) Multithread time AND memory (How / Can I multithread this??)! AND collect garbage after each tree in the depth incrimentation
// 3) Do i want to make a special algorithem for the beginning of the game?
// 4) Optimize the initial depths!!
// 5) Play with HeurWeigths


/*
 * OthelloTester.java
 * 
 * This class is the main entry point for the Othello Game.
 * 
 * D. Wulsin
 * AP Computer Science, Landon School
 * Spring 2009
 */
public class OthelloTester {

	public static void main(String[] args) throws InterruptedException {

		// init board 2D array
		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, -1, 0, 0, 0 }, { 0, 0, 0, -1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, };

		GameStateProcessorI initGsProc = new OthelloStateProcessor(-1, board);

		// Player player1 = new User("Alex", "Enter your move (e.g. a1): ",System.out);
		// Player player1 = new BacaBot();
		Player player2 = new DumbBot();

		Player player1 = new Bot3();
		// Player player2 = new BacaBot();

		GameRunner myRun = new GameRunner("Welcome to Othello", 30);

		// start the game
		myRun.start(player1, player2, initGsProc, System.out);
	}
}
