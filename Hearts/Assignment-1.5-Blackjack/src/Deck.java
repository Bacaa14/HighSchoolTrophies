//imports the random generator
import java.util.Random;

//creates a deck of cards
public class Deck {

	// states some private variables for reference in the constructors in this
	// class
	private int deckSize = 52;
	private Card[] cards = new Card[deckSize];
	private int nullCards = 0;

	// Constructs a deck of cards
	public Deck() {
		// creates all the Clubs cards
		for (int c = 0; c < 13; c++) {
			cards[c] = new Card(Suit.Clubs, c + 1);
		}

		// creates all the Diamonds cards
		for (int c = 13; c < 26; c++) {
			cards[c] = new Card(Suit.Diamonds, c - 12);
		}

		// creates all the Spades cards
		for (int c = 26; c < 39; c++) {
			cards[c] = new Card(Suit.Spades, c - 25);
		}

		// creates all the Hearts cards
		for (int c = 39; c < 52; c++) {
			cards[c] = new Card(Suit.Hearts, c - 38);
		}
	}

	// Determines weather to shuffle the deck based off a user imputed boolean
	// value
	public Deck(Boolean Shuffle) {

		// Constructs the deck using the deck constructor above
		this();

		// conditional to determine weather to shuffle or not
		if (Shuffle == true) {

			// Calls the shuffle
			ShuffleDeck();

		}
	}

	// Shuffles the Deck
	public void ShuffleDeck() {

		// calls the random generator
		Random random = new Random();

		// creates new card array
		Card[] newcards = new Card[cards.length];

		// creates new integer array
		int RandomIndex[] = new int[cards.length];

		// creates new integer array
		int array[] = new int[cards.length];

		// initializes array
		for (int c = 0; c < cards.length; c++) {
			array[c] = c;
		}

		// states variables
		int d = 0;
		int e = 0;
		int f = 0;

		// generates a random integer between 0 and 51, then sets that number to
		// -1; once set to negative one the conditional makes sure it is not
		// used
		while (f < cards.length) {

			// stores a random number between 1 and 51
			e = array[d = random.nextInt(cards.length)];

			// tests if the number has been set to -1; tests if the number has
			// already been used
			if (e >= 0) {

				// stores the number in the next spot on the array
				RandomIndex[f] = e;

				// sets the value just stored to -1; makes it so that variable
				// won't pass the conditional again; it won't repeat
				array[d] = -1;

				// goes on to the next value in the array by advancing the loop
				// towards the end of the loop
				f++;
			}
		}

		// mixes the values of the cards in a different card array
		for (int c = 0; c < cards.length; c++) {
			newcards[c] = cards[RandomIndex[c]];
		}

		// returns the values of the shuffled cards into the cards array
		for (int c = 0; c < cards.length; c++) {
			cards[c] = newcards[c];
		}

	}

	// prints the deck
	public void printDeck() {
		System.out.println();

		// prints the amount of cards in the deck
		System.out.println("The number of cards in the deck is "
				+ (cards.length - nullCards));

		System.out.println();

		System.out.println("The deck contains:");

		System.out.println();

		// prints the names of the cards in the deck
		for (int c = 0; c < cards.length - nullCards; c++) {
			System.out.println("   *" + cards[c].getName());
		}
	}

	// deals the card in the first spot in the deck array (on top of the deck),
	// and seemingly removes it from the deck
	public Card dealNextCard() {

		// states a variable
		Card save = null;

		// saves the variable to be returned at the end of the program
		save = cards[0];

		// creates a new card array
		Card newcards[] = new Card[cards.length];

		// keeps track of the amount of cards dealt, and seemingly removed from
		// the deck
		nullCards++;

		// moves all cards up one spot in the deck to replace the card dealt
		for (int c = 1; c < cards.length; c++) {
			newcards[c - 1] = cards[c];
		}

		// sets the moved up cards to the cards array
		for (int c = 0; c < cards.length - nullCards; c++) {
			cards[c] = newcards[c];
		}

		// sets the back cards that represent the cards which have been dealt to
		// null
		for (int c = (cards.length - (nullCards)); c < cards.length; c++) {
			cards[c] = null;
		}

		// returns the dealt card
		return save;

	}

	// returns the length of the deck
	public int deckLength() {
		return cards.length;
	}
}
