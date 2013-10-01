import java.util.ArrayList;

//Creates and stores a deck with other methods
public class Deck {
	// stores the deck
	private ArrayList<Card> deck;

	// creates the deck
	public Deck() {
		deck = new ArrayList<Card>();
		// loops through the different suits
		for (int s = 0; s <= 3; s++) {
			// loops through the different cards in the suit
			for (int c = 1; c <= 13; c++) {
				// creates a card with the given suit and card
				deck.add(new Card(c, s));
			}
		}
	}

	// shuffles the deck
	public void shuffleDeck() {
		// loops through the cards from the top
		for (int i = 51; i > 0; i--) {
			// gets a random index
			int rand = (int) (Math.random() * (i + 1));
			// creates a temp for the current card
			Card temp = deck.get(i);
			// sets the current card to the random card
			deck.set(i, deck.get(rand));
			// sets the random card to the current card
			deck.set(rand, temp);
		}
	}

	// deals the top card
	public Card deal() {
		Card temp = deck.get(0);
		deck.remove(0);
		return temp;
	}

	// returns the size
	public int getSize() {
		return deck.size();
	}

	// returns the card without removing it(so that you can test without
	// dealing)
	public Card get(int c) {
		return deck.get(c);
	}

	// removes the card at a given index
	public void remove(int c) {
		deck.remove(c);
	}

}