/*
 * GameRunner.java
 * This class handles the basics of a two person gameplay, including displaying the game every turns and prompting each player for his move. 
 * It is generalized, and specific classes for a particular game are passed in through the constructor.
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 */
 
 
import java.io.PrintStream;

// a game runner for a two player game
public class GameRunner {

	private Player player1;	// player 1's int code is -1
	private Player player2; // player 2's int code is 1
	
	private GameStateI curGameState;
	
	private String welcomeMsg;
	private PrintStream output;
	
	public GameRunner(Player p1, Player p2, GameStateI initGameState, 
						PrintStream anOutput, String aWelcomeMsg){
		player1 = p1;
		player2 = p2;
		
		curGameState = initGameState;
		
		output = anOutput;
		welcomeMsg = aWelcomeMsg;
		
	}
	
	/*
	 * start() starts and runs the game; it returns the playerCode of the winning player or
	 * 0 if the game is a draw
	 */
	public int start(){
		
		Player curPlayer = player1;
		String playerMove;
		
		output.println(welcomeMsg);
		output.println();
		output.println();
		
		// process turns of the game until there is a winner or the game is a draw
		while(!curGameState.isDraw() && curGameState.hasWinner() == 0){
			
			// print out the current GameState
			curGameState.display(output);
			output.println();
			
			// prompt player for a move
			output.println(curPlayer.getName() + "'s move");
			playerMove = curPlayer.getMove(curGameState);
			
			output.println(curPlayer.getName() + " has played " + playerMove);
			output.println();
			
			// check to see if move is valid
			if(curGameState.isValidMove(playerMove)){
				curGameState = curGameState.makeChild(playerMove);
				
				// check to see if the next player is the other player
				if(curGameState.getNextPlayer() == curPlayer.getCode()){
					curPlayer = getOtherPlayer(curPlayer);
				}
			}
			// if move is invalid, that player forfeits
			else{
				output.println(playerMove + " is an invalid move. " + curPlayer.getName() + " forfeits");
				return getOtherPlayer(curPlayer).getCode();
			}
		}
		
		output.println();
		output.println();
		
		curGameState.display(output);
		output.println();
		
		// if the game is a draw
		if(curGameState.isDraw()){
			output.println("It's a draw!");
			return 0;
		}
		else{
			if(player1.getCode() == curGameState.hasWinner()){
				output.println(player1.getName() + " wins!");
				return player1.getCode();
			}
			else{
				output.println(player2.getName() + " wins!");
				return player2.getCode();
			}
		}
	}
	
	// returns the other player from aPlayer between player1 and player2
	// Precondition: aPlayer is either player1 or player2
	private Player getOtherPlayer(Player aPlayer){
		if(aPlayer == player1){
			return player2;
		}
		else{
			return player1;
		}
	}
}
