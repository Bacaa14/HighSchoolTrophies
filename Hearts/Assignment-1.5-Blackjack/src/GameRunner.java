//imports a scanner
import java.util.Scanner;

public class GameRunner {

	/**
	 * Alexander Baca AP Computer Science Mr. Wulsin 10/23/08
	 * 
	 * This program uses the suit.java to create cards, uses the cards to create
	 * a deck, and uses the deck to create a player. This player is the only one
	 * of these classes purely orientated toward the game of blackjack.
	 * Gamerunner.java acts as the main and calls the other methods and classes
	 * created in the program. This program has the end effect of a single hand
	 * of blackjack.
	 */

	// Runs the game of black jack
	public static void main(String[] args) {

		// designates a name for the player
		String player1name = "Alex";

		// creates a player and a dealer (special player)
		Player Player1 = new Player(player1name);
		Player Dealer = new Player("Dealer");

		// calls the deck
		Deck Deck = new Deck(true);

		// deals the first two cards to each player using both a method in the
		// deck to display the next card and remove it, and a method in the
		// player to save that card in a hand (card array)
		Player1.addCard(Deck.dealNextCard());
		Dealer.addCard(Deck.dealNextCard());
		Player1.addCard(Deck.dealNextCard());
		Dealer.addCard(Deck.dealNextCard());

		// prints out the hands of the two players, taking into account that the
		// dealers hand should not be fully displayed
		System.out.println(Player1.getName() + "'s hand:");
		System.out.println(Player1.PrintHand());
		System.out.println("Your hand's value is " + Player1.handVal());
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.println(Dealer.getName() + "'s hand:");
		System.out.println(Dealer.PrintHand());

		// calls a scanner
		Scanner scanner = new Scanner(System.in);

		// creates some integers
		boolean Tester = true;
		int NumOfCards = 2;

		// loops the hitting process till no longer applicable
		for (int c = 0; NumOfCards < 5 && Player1.handVal() <= 21
				&& Tester == true; c++) {

			// initializes a variable
			Tester = true;

			// states a variable
			int userinput = 0;

			// prints a prompt for user input
			System.out
					.println("Do you want to hit (press 1 to hit, and 2 to stay)");

			// saves the scan
			userinput = scanner.nextInt();

			// hits
			if (userinput == 1) {

				// deals a card
				Card Save = Deck.dealNextCard();
				Player1.addCard(Save);

				// prints out the updated hand
				System.out.println("---------------------------------------");
				System.out.println(Player1.getName() + "'s next card is "
						+ Save.getName());
				System.out.println("");
				System.out.println("Your hand's value is " + Player1.handVal());
				System.out.println("");

				// increases the number of cards in the deck
				NumOfCards++;
			}

			// stops the loop
			else if (userinput == 2) {
				Tester = false;
			}

			// tells the user his input is not valid
			else {
				System.out.println("---------------------------------------");
				System.out.println("Input is not valid");
			}
		}

		// states a variable
		boolean indicator = true;

		// nests some conditionals
		{
			// determines a loss if busted
			if (Player1.handVal() > 21) {

				// prints results
				System.out.println("---------------------------------------");
				System.out.println("You Busted!");
				System.out.println("You Lose!");

				// stops program
				indicator = false;
			}

			// determines a win if you have five cards and not busted
			else if (NumOfCards == 5) {

				// prints results
				System.out.println("---------------------------------------");
				System.out
						.println("Congrats! you got 5 cards without busting!!");
				System.out.println("You Win!!!!!");
				System.out
						.println("--------------------------------------------------------------------------------");
				System.out
						.println("                        oooo$$$$$$$$$$$$oooo");
				System.out
						.println("                    oo$$$$$$$$$$$$$$$$$$$$$$$$o");
				System.out
						.println("                oo$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$o         o$   $$ o$");
				System.out
						.println("o $ oo        o$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$o       $$ $$ $$o$");
				System.out
						.println("oo $ $  $      o$$$$$$$$$    $$$$$$$$$$$$$    $$$$$$$$$o       $$$o$$o$");
				System.out
						.println(" $$$$$$o$     o$$$$$$$$$      $$$$$$$$$$$      $$$$$$$$$$o    $$$$$$$$");
				System.out
						.println("$$$$$$$    $$$$$$$$$$$      $$$$$$$$$$$      $$$$$$$$$$$$$$$$$$$$$$$");
				System.out
						.println("$$$$$$$$$$$$$$$$$$$$$$$    $$$$$$$$$$$$$    $$$$$$$$$$$$$$     $$$");
				System.out
						.println("  $$$    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$      $$$");
				System.out
						.println("$$$   o$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$      $$$o");
				System.out
						.println("o$$    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$       $$$o");
				System.out
						.println("$$$    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   $$$$$$ooooo$$$$o");
				System.out
						.println("o$$$oooo$$$$$  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   o$$$$$$$$$$$$$$$$$");
				System.out
						.println("$$$$$$$$ $$$$   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$     $$$$        ");
				System.out
						.println("           $$$$     $$$$$$$$$$$$$$$$$$$$$$$$$$$$       o$$$");
				System.out
						.println("       $$$o        $$$$$$$$$$$$$$$$$$ $$          $$$");
				System.out
						.println("        $$$o           $$  $$$$$$               o$$$");
				System.out
						.println("         $$$$o                                o$$$ ");
				System.out
						.println("           $$$$o      o$$$$$$o $$$$o        o$$$$");
				System.out
						.println("             $$$$$oo       $$$$o$$$$$o   o$$$$  ");
				System.out
						.println("                $$$$$$oooo   $$$o$$$$$$$$$   ");
				System.out.println("                   $$$$$$$$oo $$$$$$$$$$");
				System.out
						.println("                            $$$$$$$$$$$$$$");
				System.out
						.println("                              $$$$$$$$$$$$");
				System.out
						.println("                               $$$$$$$$$$ ");
				System.out.println("                                 $$$$$   ");

				// stops program
				indicator = false;
			}

			// deals the dealers hand if the player has not already won or
			// busted
			else if (Player1.handVal() <= 21) {

				// loops dealing of the dealers cards till he wants to stay or
				// busts
				while (Dealer.handVal() < 17) {
					Card Save = Deck.dealNextCard();
					Dealer.addCard(Save);

					// prints the dealers new card
					System.out.println("The dealer hits and gets the "
							+ Save.getName());
					System.out.println("");
				}

				// prints the dealers new hand value if he hasn't busted
				if (Dealer.handVal() <= 21) {
					System.out.println("The dealer stays with a total of "
							+ Dealer.handVal());
				}

				// states the dealer has busted
				else if (Dealer.handVal() > 21) {
					System.out.println("The dealer busts with a total of "
							+ Dealer.handVal());
				}
			}
		}

		{
			// determines if the rest of the program is necessary
			if (indicator == true) {

				// determines who wins
				if (Dealer.handVal() < Player1.handVal()
						|| Dealer.handVal() > 21) {
					System.out
							.println("---------------------------------------");
					System.out.println("You Win!!!!!");
				} else if (Dealer.handVal() > Player1.handVal()) {
					System.out
							.println("---------------------------------------");
					System.out.println("You Lose!");
				} else if (Dealer.handVal() == Player1.handVal()) {
					System.out
							.println("---------------------------------------");
					System.out.println("Its a push (tie).");
				}
			}
		}
	}
}
