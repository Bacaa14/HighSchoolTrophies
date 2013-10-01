import java.util.Scanner;

//holds all the things a player has
public class Player {
	// hand for player
	private Hand Hand;
	// the players number (in order of created)
	private int ID;
	// winnings for player
	private Winnings wHand;
	// name for player
	private String Name;
	// weather hearts has been broken
	private boolean HB;

	// initializes player with a given ID and Name
	public Player(int id, String name) {
		ID = id;
		Name = name;
		Hand = new Hand();
		wHand = new Winnings();
		HB = false;
	}

	// initializes player with a given ID and Name and Hand
	public Player(int id, String name, Hand hand) {
		Hand = hand;
		ID = id;
		Name = name;
		wHand = new Winnings();
		HB = false;
	}

	// decides which method to use to play a card using a boolean that indicates
	// weather it is the first play of the round, and a boolean that indicates
	// if hearts has been broken
	public Active play(boolean first, boolean HB) {
		// prints the hand
		printH();
		// if its the first hand of the round
		if (first) {
			return playFirst();
		}
		// if hearts hasnt been broken
		else if (HB == false) {
			return playCardHBF();
		}
		// if hearts has been broken
		else {
			return playCard();
		}
	}

	// decides which method to use to play a card, given a suit for the trick
	// and a boolean that indicates weather it is the first trick in a round
	public Active play(int Suit, boolean St) {
		// prints the hand
		printH();
		// if the player has the suit given
		if (hasSuit(Suit)) {
			return playSuit(Suit);
		}
		// if it is the first trick in the round
		else if (St) {
			return playCardHBFS();
		}
		// if the player doesn't have the suit and it isn't the first trick in
		// the
		// round
		else {
			return playCard();
		}
	}

	// if it is the first play of the round (it makes you play the 2 of clubs)
	public Active playFirst() {
		// initializes scanner
		Scanner sc = new Scanner(System.in);
		int c = -1;
		Active play = null;
		// while the input is not the first card in the hand it keeps prompting
		// for your input
		while (c != 0) {
			// prompts for input
			System.out
					.println("You must start the play with the 2 of clubs(input the #)");
			c = (sc.nextInt() - 1);
			// if you played the right card
			if ((c < Hand.getLength() && c >= 0) && c == 0) {
				// Actually plays the card
				Card temp = Hand.playCard(c);
				play = new Active(temp, ID);
			}
		}
		// returns the card that has been played
		return play;
	}

	// plays any card in the hand you say
	public Active playCard() {
		// initializes scanner
		Scanner sc = new Scanner(System.in);
		int c = -1;
		Active play = null;
		int originalHandLength = Hand.getLength();
		// while c isn't in the bounds of the hand it keeps prompting for input
		while (c >= originalHandLength || c < 0) {
			// prompts for input
			System.out
					.println("Which card would you like to play (input the #)");
			c = (sc.nextInt() - 1);
			// if your prompt is in the bounds it plays the card
			if (c < originalHandLength && c >= 0) {
				Card temp = Hand.playCard(c);
				// if you play a heart, then the hearts break variable is set to
				// true
				if (temp.getSuit() == Card.heart) {
					HB = true;
				}
				play = new Active(temp, ID);
			}
		}
		// returns the card you played
		return play;
	}

	// plays any card except hearts
	public Active playCardHBF() {
		// initializes scanner
		Scanner sc = new Scanner(System.in);
		int c = -1;
		boolean test = true;
		Active play = null;
		int originalHandLength = Hand.getLength();
		// while the prompt isn't in the bounds or it is a heart
		while ((c >= originalHandLength || c < 0) || test) {
			// prompts for input
			System.out
					.println("Which card would you like to play (input the #)");
			c = (sc.nextInt() - 1);
			// if it is in the parameters of the hand
			if ((c < originalHandLength && c >= 0)) {
				Card temp = Hand.get(c);
				// if the card is hearts it loops again
				if (temp.getSuit() == Card.heart) {
					System.out.println();
					System.out
							.println("------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Hearts has not been broken.");
					System.out
							.println("------------------------------------------------------------------------------------------------------------------------");
					System.out.println();
				}
				// if it isn't a heart then it ends the loop and plays the card
				else {
					temp = Hand.playCard(c);
					play = new Active(temp, ID);
					test = false;
				}
			}
		}
		// returns the card thats been played
		return play;
	}

	// plays the card if it is the first trick (wont let you play points)
	public Active playCardHBFS() {
		// initializes scanner
		Scanner sc = new Scanner(System.in);
		int c = -1;
		boolean test = true;
		Active play = null;
		int originalHandLength = Hand.getLength();
		// while the prompt isn't in the bounds or it is a heart
		while ((c >= originalHandLength || c < 0) || test) {
			// prompt for input
			System.out
					.println("Which card would you like to play (input the #)");
			c = (sc.nextInt() - 1);
			// if it is in the parameters of the hand
			if ((c < originalHandLength && c >= 0)) {
				Card temp = Hand.get(c);
				// if the card is a heart or the queen of spades it loops again
				if (temp.getSuit() == Card.heart
						|| (temp.getSuit() == Card.spade && temp.getValue() == Card.queen)) {
					System.out.println();
					System.out
							.println("------------------------------------------------------------------------------------------------------------------------");
					System.out
							.println("You can't play points on the first trick.");
					System.out
							.println("------------------------------------------------------------------------------------------------------------------------");
					System.out.println();
				}
				// otherwise it ends the loop and plays the card
				else {
					temp = Hand.playCard(c);
					play = new Active(temp, ID);
					test = false;
				}
			}
		}
		// returns the played card
		return play;
	}

	// plays a card of the given suit
	public Active playSuit(int Suit) {
		// initializes scanner
		Scanner sc = new Scanner(System.in);
		int c = -1;
		boolean test = false;
		Active play = null;
		// while it is the wrong suit it loops through the prompt
		while (!test) {
			// tells the player the suit
			System.out.println();
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out.println("The current suit is " + toSuit(Suit));
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			// prompts for input
			System.out
					.println("Which card would you like to play (input the #)");
			c = (sc.nextInt() - 1);
			// if the index given is in the perameters of the hand
			if ((c < Hand.getLength() && c >= 0)) {
				Card temp = Hand.get(c);
				// if the given card is not the correct suit it keeps looping
				if (temp.getSuit() != Suit) {
					System.out.println();
					System.out
							.println("------------------------------------------------------------------------------------------------------------------------");
					System.out.println("That is not the correct suit.");
					System.out
							.println("------------------------------------------------------------------------------------------------------------------------");
					System.out.println();
					printH();
				}
				// otherwise it plays the card and ends the loop
				else {
					temp = Hand.playCard(c);
					play = new Active(temp, ID);
					test = true;
				}
			}
		}
		// returns the played card
		return play;
	}

	// determines weather the player has a given suit
	public boolean hasSuit(int suit) {
		// loops through all the cards in the hand
		for (int c = 0; c < Hand.getLength(); c++) {
			// if the card is the given suit it returns true
			if (Hand.getHand().get(c).getSuit() == suit) {
				return true;
			}
		}
		// if it hasn't returned true by the end of the loop, it returns false
		return false;
	}

	// turns a given suit into string
	public String toSuit(int suit) {
		switch (suit) {
		case Card.spade:
			return "Spades";
		case Card.heart:
			return "Hearts";
		case Card.diamond:
			return "Diamonds";
		case Card.club:
			return "Clubs";
		default:
			return "INVALID";
		}
	}

	// prompts for three cards to pass to a player and returns them
	public Card[] passO() {
		// initializes scanner
		Scanner sc = new Scanner(System.in);
		// initializes the array
		Card[] cards = new Card[3];
		System.out.println();
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("You have to pass 3 cards to another player:");
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		int c = -1;
		// while the index is out of bounds, it keeps prompting
		while (c < 0 || c >= Hand.getLength()) {
			printH();
			System.out
					.println("Which card would you like to pass 1st (input the #)");
			c = (sc.nextInt() - 1);
		}
		// saves the played card
		cards[0] = Hand.getHand().get(c);
		Hand.getHand().remove(c);
		int d = -1;
		// while the index is out of bounds, it keeps prompting
		while (d < 0 || d >= Hand.getLength()) {
			printH();
			System.out
					.println("Which card would you like to pass 2nd (input the #)");
			d = (sc.nextInt() - 1);
		}
		// saves the played card
		cards[1] = Hand.getHand().get(d);
		Hand.getHand().remove(d);
		int e = -1;
		// while the index is out of bounds, it keeps prompting
		while (e < 0 || e >= Hand.getLength()) {
			printH();
			System.out
					.println("Which card would you like to pass 3rd (input the #)");
			e = (sc.nextInt() - 1);
		}
		// saves the played card
		cards[2] = Hand.getHand().get(e);
		Hand.getHand().remove(e);
		// returns the played cards
		return cards;
	}

	// Receives an arraylist of cards and adds them to the hand
	public void passI(Card[] cards) {
		for (int c = 0; c < cards.length; c++) {
			Hand.addCard(cards[c]);
		}
	}

	// adds a given card to the hand
	public void addCard(Card c) {
		Hand.addCard(c);
	}

	// clears the hand
	public void clearH() {
		Hand.clearHand();
	}

	// prints the hand of the player
	public void printH() {
		System.out.println();
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		SortHand();
		System.out.println(Name + "'s hand contains:");
		Hand.printHand();
	}

	// determines if the player has the 2 of clubs
	public boolean isStart() {
		return Hand.isStart();
	}

	// returns the amount of cards i the players hand
	public int cardsLeft() {
		return Hand.getLength();
	}

	// sorts the hand
	public void SortHand() {
		Hand.heartsSort();
	}

	// methods arent used
	/*
	 * public void SortHandAI() { Hand.AI_Sort(); }
	 * 
	 * public void SortHandAIP() { Hand.heartsPointSort(); }
	 */

	// adds cards to the winnings
	public void winCard(Card c) {
		wHand.addCard(c);
	}

	// clears winnings
	public void clearW() {
		wHand.clearWinnings();
	}

	// prints the winnings
	public void printW() {
		System.out.println(Name + " has won:");
		wHand.printWinnings();
		System.out.println();
		System.out.println(Name + " has won " + wHand.points() + " points.");
	}

	// returns the amount of cards won
	public int cardsWon() {
		return wHand.getLength();
	}

	// returns the points represented in the winnings
	public int getPoints() {
		return wHand.points();
	}

	// resets the players stuff
	public void clearPlayer() {
		clearW();
		clearH();
		clearHB();
	}

	// returns the number identification of the player
	public int getID() {
		return ID;
	}

	// returns the hand
	public Hand getHand() {
		return Hand;
	}

	// returns the winnings
	public Winnings getWinnings() {
		return wHand;
	}

	// returns the name
	public String getName() {
		return Name;
	}

	// returns HB
	public boolean playedHeart() {
		return HB;
	}

	// resets HB
	public void clearHB() {
		HB = false;
	}
}
