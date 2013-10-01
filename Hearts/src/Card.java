public class Card {

	// Reference variables
	public final static int spade = 2, heart = 3, diamond = 1, club = 0,
			ace = 1, jack = 11, queen = 12, king = 13, aceVal = 14,
			heartSort = 50, QueenOfSpadesSort = 100;

	// suit of the card
	private int suit;
	// value of the card
	private int value;

	// initializes card
	public Card(int theValue, int theSuit) {
		value = theValue;
		suit = theSuit;
	}

	// returns te suit
	public int getSuit() {
		return suit;
	}

	// returns the value
	public int getValue() {
		if (value == ace) {
			return aceVal;
		} else {
			return value;
		}
	}

	// returns the value in order of point value with card value
	public int getHeartValue() {
		int val = 0;

		if (value == ace) {
			val = aceVal;
		} else {
			val = value;
		}

		if (suit == heart) {
			val += heartSort;
		}

		if (suit == spade && value == queen) {
			val += QueenOfSpadesSort;
		}

		return val;

	}

	// returns the suit in string form
	public String suitToString() {
		switch (suit) {
		case spade:
			return "Spades";
		case heart:
			return "Hearts";
		case diamond:
			return "Diamonds";
		case club:
			return "Clubs";
		default:
			return "INVALID";
		}
	}

	// returns the value in string form
	public String valueToString() {
		switch (value) {
		case 1:
			return "Ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		default:
			return "INVALID";
		}
	}

	// returns the card in string form
	public String getCardString() {
		return valueToString() + " of " + suitToString();
	}

}
