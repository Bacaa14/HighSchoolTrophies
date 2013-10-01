import java.util.ArrayList;
import java.util.Scanner;

//has everything needed for the game, and uses it to make the game running methods
public class GameState {
	private ArrayList<Player> players;
	private int numPlayers;
	private ArrayList<Round> rounds;
	private int numRounds;
	private Deck deck;
	private int currRound;
	private int currTrick;
	public static final int shootMoon = 26;

	// initializes the class
	public GameState() {
		rounds = new ArrayList<Round>();
		players = new ArrayList<Player>();
		newDeck();
		currRound = 1;
		currTrick = 0;
	}

	// prompts for the number of players and sets it to the prompt as long as it
	// is between 3 and 6
	public void setNumPLayers() {
		int players0 = 0;
		while (players0 < 3 || players0 > 6) {
			Scanner sc = new Scanner(System.in);
			System.out.println("How many players are there (between 3 & 6)");
			numPlayers = sc.nextInt();
			players0 = numPlayers;
		}
	}

	// returns the number of players
	public int getNumPlayers() {
		return numPlayers;
	}

	// prompts for the number of rounds (must be more than 0)
	public void setNumRounds() {
		int rounds0 = 0;
		while (rounds0 <= 0) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("How many rounds will you be playing? (more than 0)");
			numRounds = sc.nextInt();
			rounds0 = numRounds;
		}
	}

	// returns the number of rounds
	public int getNumRounds() {
		return numRounds;
	}

	// prompts for players names, then creates players with that name and an ID
	public void makePlayers() {
		for (int c = 0; c < numPlayers; c++) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Player " + (c + 1) + " what is your name?");
			String name = sc.next();
			Player newPlayer = new Player(c, name);
			players.add(newPlayer);
		}
	}

	// returns the arraylist of players
	public ArrayList<Player> getPlayers() {
		return players;
	}

	// returns the arraylist of rounds
	public ArrayList<Round> getRounds() {
		return rounds;
	}

	// creates a new deck and shuffles it
	public void newDeck() {
		deck = new Deck();
		deck.shuffleDeck();
	}

	// deals cards from the deck, distributing a the cards equaly to all players
	// (removing cards if necessary, but not removing any point cards)
	public void Deal() {
		int d = deck.getSize() % numPlayers;
		if (d != 0) {
			int c = 0;
			for (int e = 0; c < d; e++) {
				if (!(deck.get(e).getSuit() == Card.heart || (deck.get(e)
						.getSuit() == Card.spade && deck.get(e).getValue() == Card.queen))) {
					deck.remove(e);
					c++;
				}
			}
		}
		while (deck.getSize() > 0) {
			int c = deck.getSize() % numPlayers;
			players.get(c).addCard(deck.deal());
		}
	}

	// plays a round
	public void playRound() {
		// creates a round for point storage
		Round round = new Round();
		int start = 0;
		// initializes the start boolean as true
		boolean first = true;
		// creates an arraylist of card arrays for passing purposes
		ArrayList<Card[]> pass = new ArrayList<Card[]>();

		// clears players for the begining of the game
		for (int c = 0; c < players.size(); c++) {
			players.get(c).clearPlayer();
		}

		// creates a new shuffled deck
		newDeck();
		// deals the cards in the deck to the players hands
		Deal();

		// has all the players pass 3 cards and adds them to the pass array list
		for (int c = 0; c < players.size(); c++) {
			pass.add(players.get(c).passO());
		}

		// used to determine which way to pass (alternates round to round
		int d = currRound % 2;
		switch (d) {
		case 0:
			// gives an array of cards to a player 1 more than it was given by
			for (int c = 0; c < players.size() - 1; c++) {
				players.get(c).passI(pass.get(c + 1));
			}
			players.get(players.size() - 1).passI(pass.get(0));
		case 1:
			// gives an array of cards to a player 1 less than it was given by
			players.get(0).passI(pass.get(players.size() - 1));
			for (int c = 1; c < players.size(); c++) {
				players.get(c).passI(pass.get(c - 1));
			}
		}

		// finds the player with the 2 of clubs
		for (int c = 0; c < players.size(); c++) {
			if (players.get(c).isStart()) {
				start = players.get(c).getID();
			}
		}

		// plays tricks while there are still cards left in the hands of the
		// first player (should be the same with all players)
		while (players.get(0).cardsLeft() > 0) {
			int temp = 0;
			// play trick returns the winner so that we can pass the winner in
			// as the first the next trick
			temp = playTrick(start, first);
			start = temp;
			// so that only the first trick is given the status of true
			first = false;
		}

		// adds the points in order of player ID to the round class
		for (int c = 0; c < players.size(); c++) {
			round.addPoints(players.get(c).getPoints());
		}

		// changes the round class if some one has shot the moon
		round = shootMoon(round);

		// adds the round class to the arraylist of rounds
		rounds.add(round);

		// increases the currRound counter
		currRound++;

		// prints out the total amount of points for the game each player has
		{
			ArrayList<Integer> pointsC = new ArrayList<Integer>();
			for (int c = 0; c < rounds.size(); c++) {
				for (int p = 0; p < players.size(); p++) {
					if (c == 0) {
						pointsC.add(rounds.get(c).getPoint(p));
					} else {
						pointsC.set(p, (pointsC.get(p) + rounds.get(c)
								.getPoint(p)));
					}
				}
			}

			System.out.println();
			System.out
					.println("*********************************************************");
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out.println("The current standings for the GAME are:");
			for (int c = 0; c < players.size(); c++) {
				System.out.println(players.get(c).getName() + " has "
						+ pointsC.get(c) + " points.");
			}
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out
					.println("*********************************************************");
			System.out.println();
		}

		// sets the currTrick counter to 0
		currTrick = 0;

		// prints out the amount of rounds left and then waits till the user
		// inputs anything to continue
		{
			System.out.println();
			System.out
					.println("*********************************************************");
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out.println("There are " + ((numRounds - currRound) + 1)
					+ " rounds left in the game.");
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out
					.println("*********************************************************");
			System.out.println();

			Scanner sc = new Scanner(System.in);
			System.out.println("Press any leter and then return to continue.");
			sc.next();
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}

	// plays a trick given a player to start with and a boolean saying weather
	// it is the first trick of a round
	public int playTrick(int start, boolean first) {
		// initializes a new trick
		Trick trick = new Trick();
		int winner;
		int Suit;
		boolean HB = false;
		Scanner sc = new Scanner(System.in);
		boolean St = false;

		// if it is the first trick in the round it puts St to true
		if (currTrick == 0) {
			St = true;
		}

		// if any player has broken hearts, then HB is set to true
		for (int c = 0; c < players.size(); c++) {
			if (players.get(c).playedHeart()) {
				HB = true;
			}
		}

		// if the starting player has nothing but hearts then HB is set to true
		if ((!(players.get(start).hasSuit(Card.club)))
				&& (!(players.get(start).hasSuit(Card.diamond)))
				&& (!(players.get(start).hasSuit(Card.spade)))) {
			HB = true;
		}

		// gets the first card
		Active cardF;
		cardF = players.get(start).play(first, HB);

		// puts the card into the trick
		trick.recieve(cardF);

		// gets the suit for the trick
		Suit = trick.getSuit();

		// prompts the playing of cards for all the players after the start
		// player
		for (int c = start + 1; c < numPlayers; c++) {
			trick.printTrick();
			Active card;
			card = players.get(c).play(Suit, St);
			trick.recieve(card);
		}
		// prompts the playing of cards for all the players before the start
		// player
		for (int c = 0; c < start; c++) {
			trick.printTrick();
			Active card;
			card = players.get(c).play(Suit, St);
			trick.recieve(card);
		}

		// gets the winner of the trick
		winner = trick.getWinner();

		// says who won the trick
		System.out.println();
		System.out
				.println("*********************************************************");
		System.out.println(players.get(winner).getName() + " won the trick");
		System.out
				.println("*********************************************************");
		System.out.println();

		// end trick text
		trick.printEnd();

		// initializes a variable
		ArrayList<Card> winC = trick.getCards();

		// gives the cards in the trick to the winning player
		for (int c = 0; c < winC.size(); c++) {
			players.get(winner).winCard(winC.get(c));
		}

		// prints out the current score for the round
		System.out.println();
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("The current standings for this ROUND are:");
		for (int c = 0; c < players.size(); c++) {
			System.out.println(players.get(c).getName() + " has "
					+ players.get(c).getPoints() + " points.");
		}
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();

		// waits for the player to input anything to continue
		System.out.println("Press any leter and then return to continue.");
		sc.next();
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();

		// currTrick increase
		currTrick++;

		// returns the winner
		return winner;
	}

	// determines if someone shot the moon and changes the points accordingly
	public Round shootMoon(Round round) {
		boolean test = false;
		int player = -1;

		// determines if the moon was shot and gets the ID of the player that
		// did
		for (int c = 0; c < players.size(); c++) {
			if (round.getPoints().get(c) == shootMoon) {
				test = true;
				player = c;
			}
		}

		// loops through players if shot moon
		for (int c = 0; c < players.size() && test; c++) {
			// if the currPlayer shot moon set points to 0 and says he shot the
			// moon
			if (c == player) {
				round.setPoints(c, 0);
				System.out.println();
				System.out
						.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println(players.get(c).getName()
						+ " shot the moon!!");
				System.out
						.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println();
			}
			// all other players points are set to the public variable shootMoon
			// (26 normally)
			else {
				round.setPoints(c, shootMoon);
			}
		}
		// returns the new round
		return round;
	}

	// end game text, winner announced
	public void endGame() {
		// end game text
		System.out.println();
		System.out
				.println("*********************************************************");
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");

		System.out.println("GAME OVER");
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out
				.println("*********************************************************");
		System.out.println();

		// gets the pointsfor each [layer
		ArrayList<Integer> pointsC = new ArrayList<Integer>();
		for (int c = 0; c < rounds.size(); c++) {
			for (int p = 0; p < players.size(); p++) {
				if (c == 0) {
					pointsC.add(rounds.get(c).getPoint(p));
				} else {
					pointsC
							.set(
									p,
									(pointsC.get(p) + rounds.get(c).getPoint(p)));
				}
			}
		}

		boolean isTie = false;

		// determines the winner
		Player winner = players.get(0);
		int lowest = pointsC.get(0);
		int lowIndex = 0;
		for (int c = 1; c < players.size(); c++) {
			if (pointsC.get(c) < lowest) {
				winner = players.get(c);
				lowest = pointsC.get(c);
				lowIndex = c;
			}
		}

		ArrayList<Player> tie = new ArrayList<Player>();
		ArrayList<Integer> tieP = new ArrayList<Integer>();

		// determines if there was a tie
		{

			tie.add(winner);
			tieP.add(lowest);
			for (int c = (lowIndex + 1); c < players.size(); c++) {
				if (pointsC.get(c) == lowest) {
					tie.add(players.get(c));
					tieP.add(pointsC.get(c));
					isTie = true;
				}
			}
			for (int c = 0; c < lowIndex; c++) {
				if (pointsC.get(c) == lowest) {
					tie.add(players.get(c));
					tieP.add(pointsC.get(c));
					isTie = true;
				}
			}
		}

		// if there was a tie, prints all winners
		if (isTie) {
			String winners = "";
			for (int c = 0; c < tie.size(); c++) {
				winners += "" + tie.get(c).getName() + " (with " + tieP.get(c)
						+ " points) ";
			}

			System.out.println();
			System.out
					.println("*********************************************************");
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");

			System.out.println("The winners are " + winners);
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out
					.println("*********************************************************");
			System.out.println();
		}
		// otherwise prints the one winner
		else {
			System.out.println();
			System.out
					.println("*********************************************************");
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");

			System.out.println("The winner is " + winner.getName() + ", with "
					+ lowest + " points!");
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out
					.println("*********************************************************");
			System.out.println();
		}
	}
}
