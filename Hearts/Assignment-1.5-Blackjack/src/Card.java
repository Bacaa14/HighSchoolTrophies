//Creates cards for the deck, and constructs some other useful information relevant to the identity of the cards
public class Card {
	// states some private variables for reference in the constructors in this
	// class
	private int myNumber;
	private Suit mySuit;

	// Gives value to the variable to be referenced in this class relevant to
	// the card called for by the deck class
	public Card(Suit aSuit, int aNumber) {
		// Inputs the Suit value
		mySuit = aSuit;
		// Inputs the Number value
		myNumber = aNumber;
	}

	// Puts a name (String value) to the Number and Suit values
	public String getName() {
		// states a variable
		String Number = null;

		// gives a name to a number depending on the case
		switch (myNumber) {
		case 1:
			Number = "Ace";
			break;
		case 2:
			Number = "Two";
			break;
		case 3:
			Number = "Three";
			break;
		case 4:
			Number = "Four";
			break;
		case 5:
			Number = "Five";
			break;
		case 6:
			Number = "Six";
			break;
		case 7:
			Number = "Seven";
			break;
		case 8:
			Number = "Eight";
			break;
		case 9:
			Number = "Nine";
			break;
		case 10:
			Number = "Ten";
			break;
		case 11:
			Number = "Jack";
			break;
		case 12:
			Number = "Queen";
			break;
		case 13:
			Number = "King";
			break;
		}

		// Creates string with the Card Name
		String CardName = Number + " of " + mySuit;

		// returns the name of the Card
		return CardName;
	}

	// Returns the numerical value of the card
	public int getNumber() {
		return myNumber;

	}

}
