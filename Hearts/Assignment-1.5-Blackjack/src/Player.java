public class Player {

	// states variables
	private int HandLength = 5;
	private int ActualHandLength = 0;
	private String PlayerName = null;
	private Card hand[] = new Card[HandLength];

	// constructs hand
	public Player() {
		for (int c = 0; c < hand.length; c++) {
			hand[c] = null;
		}
	}

	// constructs a player
	public Player(String Name) {
		PlayerName = Name;

	}

	// adds a card to the hand
	public void addCard(Card HandCard) {
		hand[ActualHandLength] = HandCard;
		ActualHandLength++;
	}

	// prints the hand
	public String PrintHand() {
		// prints the dealers hand, hiding the first card in the hand
		if (PlayerName == "Dealer") {
			System.out.println("The first card in " + PlayerName
					+ "'s hand is [Hidden]");
			System.out.println("The second card in " + PlayerName
					+ "'s hand is the " + hand[1].getName());
			for (int c = 2; c < hand.length; c++) {
				if (hand[c] != null) {
					System.out.println("The next card in " + PlayerName
							+ "'s hand is the " + hand[c].getName());

				}
			}
		}

		// prints the player hand
		else {
			System.out.println("The first card in " + PlayerName
					+ "'s hand is the " + hand[0].getName());
			System.out.println("The second card in " + PlayerName
					+ "'s hand is the " + hand[1].getName());

			for (int c = 2; c < hand.length; c++) {
				if (hand[c] != null) {
					System.out.println("The next card in " + PlayerName
							+ "'s hand is the " + hand[c].getName());

				}
			}
		}
		return " ";
	}

	// method that tries to sum the hand up
	public int handVal() {
		int handVals[] = new int[ActualHandLength];
		int HandSum = 0, PrelimSum = 0, NumOfAces = 0;

		// Assigns preliminary card values
		for (int c = 0; c < ActualHandLength; c++) {

			// Assigns the 10 values to tens and face cards
			if (hand[c].getNumber() > 10) {
				handVals[c] = 10;
			}

			// Assigns card values 2-9
			else if (hand[c].getNumber() > 1 && hand[c].getNumber() <= 10) {
				handVals[c] = hand[c].getNumber();

			}

			// ignores aces and counts them
			else if (hand[c].getNumber() == 1) {
				handVals[c] = 0;
				NumOfAces++;
			}
		}
		// calculates preliminary sum
		for (int c = 0; c < ActualHandLength; c++) {
			PrelimSum += handVals[c];

		}
		// creates an array the length of the number of aces
		int aces[] = new int[NumOfAces];

		// attempts to deal with the aces
		for (int c = 0; c < ActualHandLength; c++) {
			// if 1 ace, and all other cards < 11 then ace = 11
			if (hand[c].getNumber() == 1 && PrelimSum < 11 && NumOfAces == 1) {
				handVals[c] = 11;
			}
			// if only aces in hand
			else if (PrelimSum == 0 && NumOfAces > 1) {

				// states a variable
				int counter = 0;

				// loops the aces
				while (counter < NumOfAces) {
					if (counter == 0) {
						handVals[0] = 11;
						counter++;
					} else if (counter > 0) {
						handVals[counter] = 1;
						counter++;
					}
				}
			}

			// if more than one ace
			else if (handVals[c] == 0 && NumOfAces > 1) {
				for (int count = 0; count < aces.length; count++) {
					aces[count] = c;
				}
				if (NumOfAces == 2 && PrelimSum <= 9) {
					handVals[aces[0]] = 11;
					handVals[aces[1]] = 1;
				} else if (NumOfAces == 2 && PrelimSum > 9) {
					handVals[aces[0]] = 1;
					handVals[aces[1]] = 1;
				} else if (NumOfAces == 3 && PrelimSum <= 8) {
					handVals[aces[0]] = 11;
					handVals[aces[1]] = 1;
					handVals[aces[2]] = 1;
				} else if (NumOfAces == 3 && PrelimSum > 8) {
					handVals[aces[0]] = 1;
					handVals[aces[1]] = 1;
					handVals[aces[2]] = 1;
				} else if (NumOfAces == 4 && PrelimSum <= 7) {
					handVals[aces[0]] = 11;
					handVals[aces[1]] = 1;
					handVals[aces[2]] = 1;
					handVals[aces[3]] = 1;
				} else if (NumOfAces == 4 && PrelimSum > 7) {
					handVals[aces[0]] = 1;
					handVals[aces[1]] = 1;
					handVals[aces[2]] = 1;
					handVals[aces[3]] = 1;
				}
			}

			// assigns ace value
			else if (hand[c].getNumber() == 1 && PrelimSum >= 11) {
				handVals[c] = 1;
			}
		}
		// calculates final sum
		for (int c = 0; c < ActualHandLength; c++) {
			HandSum += handVals[c];
		}

		// returns the sum
		return HandSum;
	}

	// returns the player's name
	public String getName() {
		return PlayerName;
	}

}
