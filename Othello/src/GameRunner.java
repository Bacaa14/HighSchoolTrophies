/*
 * GameRunner.java
 * 
 * This class handles running an Othello game
 * 
 * D. Wulsin
 * AP Computer Science, Landon Science
 * Spring 20009
 */


import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

public class GameRunner {

	private String welcomeMsg;
	private int timeLimit;
		
	private Player curPlayer;
	private String playerMove = null;
	
	
	
	
	public GameRunner(String aWelcomeMsg, int aTimeLimit){
		
		welcomeMsg = aWelcomeMsg;
		timeLimit = aTimeLimit;
		
	}
	
	// inline class definition so it can see the local data
	private class MovePrompt implements Runnable{
		Player curPlayer;
		GameStateProcessorI curGsProc;
		int curPlayerCode;
		
		public MovePrompt(Player aPlayer, GameStateProcessorI aCurGsProc, int aPlayerCode){
			curPlayer = aPlayer;
			curGsProc = aCurGsProc;
			curPlayerCode = aPlayerCode;
		}
		public void run(){
			
			// the the Player is a User, prompt again if he/she enters an invalid move 
			if(curPlayer instanceof User){
				do{
					playerMove = curPlayer.getMove(curGsProc.getBoard(), curPlayerCode);
				} while(!curGsProc.isValidMove(playerMove));
			}
			else{
				System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
				playerMove = curPlayer.getMove(curGsProc.getBoard(), curPlayerCode);
			}
			
		}
	}
	
	// another inline class definition for simple time limit warnings
	private class MessageTask extends TimerTask {
		private String message;
		private PrintStream output;
		
		public MessageTask(String aMessage, PrintStream anOutput){
			message = aMessage;
			output = anOutput;
		}
		
		public void run(){
			output.println(message);
		}
	}
	
	
	
	/*
	 * start() starts and runs the game; it returns the playerCode of the winning player or
	 * 0 if the game is a draw
	 */
	public int start(Player player1, Player player2, GameStateProcessorI curGsProc, PrintStream output) throws InterruptedException{
		
		int movesSkipped = 0;
		curPlayer = player1;
		int curPlayerCode = -1;
		
		output.println(welcomeMsg);
		output.println();
		output.println();
		
		System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
		
		// process turns of the game until there is a winner or the game is a draw
		while(!curGsProc.isDraw() && curGsProc.hasWinner() == 0 && movesSkipped < 2){
			
			System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
			
			if(curGsProc.hasMove()){
				// print out the current GameState
				curGsProc.display(output);
				output.println();
				
				// prompt player for a move
				output.println(curPlayer.getName() + "'s move, " + Player.getColor(curPlayerCode));
				
				// start new thread for getMove
				Thread moveThread = new Thread(new MovePrompt(curPlayer, curGsProc, curPlayerCode));
				playerMove = null;
				moveThread.start();
				
				// set up time limit warning timers
				Timer warningsT = new Timer();
				warningsT.schedule(new MessageTask("\n...10 seconds left", output), (timeLimit - 10)*1000);
				warningsT.schedule(new MessageTask("...3 seconds left", output), (timeLimit - 3)*1000);
				
				// rejoin the getMove thread after the time limit
				moveThread.join(timeLimit * 1000);
				warningsT.cancel();
				
				// if the player hasn't made a move within the time limit
				if(playerMove == null){
					output.println();
					output.println(curPlayer.getName() + " has exceeded the " + timeLimit + " second time limit. " + 
									curPlayer.getName() + " forfeits.");
					return curPlayerCode * -1;
				}
				
				Runtime.getRuntime().gc();
				System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
				
				output.println(curPlayer.getName() + " has played " + playerMove);
				output.println();
				
				// check to see if move is valid
				if(curGsProc.isValidMove(playerMove)){
					curGsProc = curGsProc.makeChild(playerMove);
					curPlayer = getOtherPlayer(curPlayer, player1, player2);
					curPlayerCode *= -1;
				}
				// if move is invalid, that player forfeits
				else{
					output.println(playerMove + " is an invalid move. " + curPlayer.getName() + " forfeits");
					return curPlayerCode * -1;
				}
				
				movesSkipped = 0;
			}
			// if the next move is the same player, we need to change that in
			// the game state
			else{
				output.println();
				output.println(curPlayer.getName() + " cannot play. It's turn is skipped.");
				output.println();
				
				curPlayerCode *= -1;
				curGsProc.setCurPlayer(curPlayerCode);
				curPlayer = getOtherPlayer(curPlayer, player1, player2);
				movesSkipped++;
			}
		}
		
		output.println();
		output.println();
		
		curGsProc.display(output);
		output.println();
		
		// if the game is a draw
		if(curGsProc.isDraw()){
			output.println("It's a draw!");
			return 0;
		}
		else{
			if(curGsProc.hasWinner() == -1){
				output.println(player1.getName() + " wins!");
				curGsProc.printScore(output);
				return -1;
			}
			else{
				output.println(player2.getName() + " wins!");
				curGsProc.printScore(output);
				return 1;
			}
		}
	}
	
	// returns the other player from aPlayer between player1 and player2
	// Precondition: aPlayer is either player1 or player2
	private Player getOtherPlayer(Player aPlayer, Player player1, Player player2){
		if(aPlayer == player1){
			return player2;
		}
		else{
			return player1;
		}
	}
}
