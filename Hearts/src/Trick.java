import java.util.ArrayList;

//holds the cards in a trick
public class Trick {
	// holds cards in the forms of Actives
	private ArrayList<Active> theTrick;

	// initializes trick
	public Trick() {
		theTrick = new ArrayList<Active>();
	}

	// adds an Active to the trick if it isnt null
	public void recieve(Active a) {
		if (a != null) {
			theTrick.add(a);
		}
	}

	// gets the suit of the first card played in the trick
	public int getSuit() {
		return theTrick.get(0).getCard().getSuit();
	}

	// gets the winner of the trick
	public int getWinner() {
		// gets the first card and sets it as the highest
		int winner = theTrick.get(0).getOwner();
		Card highest = theTrick.get(0).getCard();
		// loops through the other cards
		for (int c = 1; c < theTrick.size(); c++) {
			// if the new card is the same suit as the current highest and has a
			// higher value, it is set as the new highest
			if (highest.getSuit() == theTrick.get(c).getCard().getSuit()
					&& highest.getValue() < theTrick.get(c).getCard()
							.getValue()) {
				highest = theTrick.get(c).getCard();
				winner = theTrick.get(c).getOwner();
			}
		}
		// returns the winner
		return winner;
	}

	// returns the cards in the trick (without owners)
	public ArrayList<Card> getCards() {
		// creates an arraylist of cards
		ArrayList<Card> cards = new ArrayList<Card>();
		// loops through and adds the cards to the new arraylist
		for (int c = 0; c < theTrick.size(); c++) {
			cards.add(theTrick.get(c).getCard());
		}
		// returns the cards
		return cards;
	}

	// prints out the cards in the trick in order of when they were played
	public void printTrick() {
		System.out.println();
		System.out.println();
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("The trick contains:");
		for (int c = 0; c < theTrick.size(); c++) {
			System.out.println((c + 1) + ". "
					+ theTrick.get(c).getCard().getCardString());
		}
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	// prints the trick, with what the trick contained
	public void printEnd() {
		System.out.println();
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("The trick contained:");
		for (int c = 0; c < theTrick.size(); c++) {
			System.out.println((c + 1) + ". "
					+ theTrick.get(c).getCard().getCardString());
		}
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}

}
