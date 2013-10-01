import java.util.ArrayList;

//holds a players hand for a round
public class Hand {
	// holds the cards in the hand
	private ArrayList<Card> hand;

	// initializes the hand
	public Hand() {
		hand = new ArrayList<Card>();
	}

	// adds a card to the hand if it is not null
	public void addCard(Card c) {
		if (c != null) {
			hand.add(c);
		}
	}

	// clears a hand
	public void clearHand() {
		hand.clear();
	}

	// returns the card at a given index and removes it from an array
	public Card playCard(int c) {
		Card temp = hand.get(c);
		hand.remove(c);
		return temp;
	}

	// returns the hand
	public ArrayList<Card> getHand() {
		return hand;
	}

	// prints the hand
	public void printHand() {
		for (int c = 0; c < hand.size(); c++) {
			System.out
					.println((c + 1) + ". The " + hand.get(c).getCardString());
		}
		System.out
				.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
	}

	// returns the amount of cards in the hand
	public int getLength() {
		return hand.size();
	}

	// sorts the cards by suit and lowest to highest (clubs, diamonds, spades,
	// hearts)
	public void heartsSort() {
		// creates a new hand
		ArrayList<Card> newHand = new ArrayList<Card>();
		// while the old hand still has cards
		while (hand.size() > 0) {
			// gets the first card and index
			int a = 0;
			Card ele = hand.get(0);
			// loops through the cards in the hand
			for (int c = 0; c < hand.size(); c++) {
				// tests if the current card is either a lower suit, or if its
				// equal suit if its a lower number
				Card tester = hand.get(c);
				if (tester.getSuit() < ele.getSuit()
						|| (tester.getSuit() == ele.getSuit() && tester
								.getValue() < ele.getValue())) {
					a = c;
					ele = tester;
				}
			}
			hand.remove(a);
			newHand.add(ele);
		}
		// sets the hand to the new sorted hand
		hand = newHand;
	}

	// the following two methods are never used but i dont want to delete them
	// in case i ever want to make the AI
	/*
	 * public void AI_Sort() { ArrayList<Card> newHand = new ArrayList<Card>();
	 * while (hand.size() > 0) { int a = 0; Card ele = hand.get(0); for (int c =
	 * 0; c < hand.size(); c++) { Card tester = hand.get(c); if
	 * (tester.getValue() < ele.getValue() || (tester.getValue() ==
	 * ele.getValue() && tester .getSuit() < ele.getSuit())) { a = c; ele =
	 * tester; } } hand.remove(a); newHand.add(ele); } hand = newHand;
	 * 
	 * }
	 * 
	 * public void heartsPointSort(){ ArrayList<Card> newHand = new
	 * ArrayList<Card>(); while (hand.size() > 0) { int a = 0; Card ele =
	 * hand.get(0); for (int c = 0; c < hand.size(); c++) { Card tester =
	 * hand.get(c); if (tester.getHeartValue() < ele.getHeartValue() ||
	 * (tester.getHeartValue() == ele.getHeartValue() && tester .getSuit() <
	 * ele.getSuit())) { a = c; ele = tester; } } hand.remove(a);
	 * newHand.add(ele); } hand = newHand;
	 * 
	 * }
	 */

	// checks to see if the hand contains the two of clubs
	public boolean isStart() {
		for (int c = 0; c < hand.size(); c++) {
			if (hand.get(c).getValue() == 2 && hand.get(c).getSuit() == 0) {
				return true;
			}
		}
		return false;
	}

	// returns the card at a given index without removing it for testing
	// purposes
	public Card get(int c) {
		return hand.get(c);
	}
}
