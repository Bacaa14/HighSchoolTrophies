//runs the game
public class GameRunner {

	public static void main(String[] args) {

		// makes the game
		GameState GS = new GameState();
		// sets the number of players
		GS.setNumPLayers();
		// sets the number of rounds
		GS.setNumRounds();
		// creates the players
		GS.makePlayers();
		// gets the number of rounds
		int numRounds = GS.getNumRounds();
		// plays rounds for the number of rounds
		for (int c = 0; c < numRounds; c++) {
			GS.playRound();
		}
		// displays the end of the game stats
		GS.endGame();
	}
}
