import java.util.ArrayList;

//holds the winnings(cards a player has won) for a player durring a round
public class Winnings {
	// holds the cards
	private ArrayList<Card> wHand;

	// initializes the class
	public Winnings() {
		wHand = new ArrayList<Card>();
	}

	// adds a card if it is not null
	public void addCard(Card c) {
		if (c != null) {
			wHand.add(c);
		}
	}

	// clears a players winnings
	public void clearWinnings() {
		wHand.clear();
	}

	// gets the number of cards a player has won
	public int getLength() {
		return wHand.size();
	}

	// prints the winnings
	public void printWinnings() {
		for (int c = 0; c < wHand.size(); c++) {
			System.out.println((c + 1) + ". The "
					+ wHand.get(c).getCardString());
		}
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	// gets the number of points represented by the cards in the winnings
	public int points() {
		// if there are no winnings returns 0
		if (wHand.size() == 0) {
			return 0;
		}
		// else
		else {
			// initializes total points to 0
			int points = 0;
			// loops through each card
			for (int c = 0; c < wHand.size(); c++) {
				// if its a heart, adds 1 point to the total points
				if (wHand.get(c).getSuit() == Card.heart) {
					points++;
				}
				// if its the queen of spades then adds 13
				else if (wHand.get(c).getSuit() == Card.spade
						&& wHand.get(c).getValue() == Card.queen) {
					points += 13;
				}
			}
			// returns the total points
			return points;

		}
	}

}
