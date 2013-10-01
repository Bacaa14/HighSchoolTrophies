//Cards that are in play (in the current trick)
public class Active {
	// Stores the card and the owner's ID (unique integer given in order of
	// creation)
	private Card card;
	private int owner;

	// makes the Active
	public Active(Card c, int p) {
		card = c;
		owner = p;
	}

	// returns the card
	public Card getCard() {
		return card;
	}

	// returns the ID of the owner(unique integer given in order of creation)
	public int getOwner() {
		return owner;
	}

}
