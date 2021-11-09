package finaleproj;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * This is the main game. Run this to test out all the classes.
 * There are only 2 players: the user and BOT in this game.
 * 
 * @author Hien Ha
 * @version 12.0.3
 * @since 1.0
 */

public class Gameplay {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); // getting the user's input
		boolean running = true; // the state of the menu (false to close the whole program)
		boolean play = false; // the state of the gaming mode (true to start the game)
		boolean pturn = true; // the player's turn
		boolean bturn = false; // the bot's turn
		boolean ask = false; // the state when the game asks the player to choose (true to start asking)
		System.out.println("Welcome to UNO");
		while (running) {
		 		System.out.print("Type 'start' to play, 'quit' to close > ");
		 		String inStr = scanner.nextLine(); // collects user's input
		 		
		 		/** 
		 		 * If they type in "quit", the whole program shut down
		 		 */
		 		if (inStr.compareTo("quit") == 0){
		 			running = false;
		 			break;
		 		}
		 		
		 		/**
		 		 * If they type "start", the game begins
		 		 */
		 		else if (inStr.compareTo("start") == 0) {
		 			play = true;
		 			//FullDeck fulldeck = new FullDeck(); // uncomment this to play the game with all 100 cards
		 			MiniDeck fulldeck = new MiniDeck(); // uncomment this smaller deck (24 cards) to test the results faster
		 			fulldeck.shuffle();
		 			PlayersHand pHand = new PlayersHand(); // player's hand
		 			PlayersHand bHand = new PlayersHand(); // BOT's hand
		 			PlayersHand playedDeck = new PlayersHand(); // all played cards
		 			
		 			/**
		 			 * Each player starts with 7 cards
		 			 */
		 			for (int i = 0; i < 7; i++) {
		 				Card icard = fulldeck.deal();
		 				pHand.addFirst(icard);
		 				icard = fulldeck.deal();
		 				bHand.addFirst(icard);
		 			}
		 			
		 			Card initialCard = fulldeck.deal(); // the first played card
		 			
		 			// if the initial card is special, it is put back to the deck and another card is dealt
		 			if (initialCard.isWild() || initialCard.isPlus2() || initialCard.isPlus4() || initialCard.isSkip()) {
		 				Card keep = initialCard;
		 				initialCard = fulldeck.deal();
		 				fulldeck.putBack(keep);
		 			}
		 			
		 			playedDeck.addFirst(initialCard);
		 			while (play) {
		 				/**
		 				 * If pturn is true, the player can play.
		 				 * If it's false, the player cannot play
		 				 */
		 				while (pturn) {
							System.out.println(" ");
		 					System.out.println("BOT is having " + bHand.checkSize() + " cards.");
			 				System.out.println("You are having " + pHand.checkSize() + " cards.");
							System.out.println("--------------------");
			 				System.out.println("|  You are having");
			 				pHand.displayHand();
							System.out.println("--------------------");
			 				System.out.println("Most recently played card is " + playedDeck.first());
							System.out.println(" ");
			 				System.out.println("To play a card, type 'play [cardname]'");
			 				System.out.println("To get a card, type 'get cards'");
			 				System.out.println("To quit, type 'quit game'");
			 				System.out.print("What do you want to do? Type here >>>> ");
			 				String newcmd = scanner.nextLine();
			 				StringTokenizer fcmd = new StringTokenizer(newcmd);
			 				// separate the input into 2 parts: command + card's name
			 				String cmd = fcmd.nextToken();
			 				String card = fcmd.nextToken();
			 				// getting all the remaining words in the input, which is the cards' full name
			 				while (fcmd.hasMoreTokens()) {
				 					card = card + " " + fcmd.nextToken();
			 				}
			 				
			 				// if command is "quit", the game ends
			 				if (cmd.compareTo("quit") == 0) {
			 					play = false;
			 					pturn = false;
			 					break;
			 				}
			 				
			 				// if command is "play", the game continues to find the card that is typed in
			 				else if (cmd.compareTo("play") == 0) {
			 					Card chosen = pHand.findCard(card);
			 					if (chosen != null) {
			 						if (pHand.canPlay(playedDeck.first(), chosen)) {
			 							pHand.playCard(playedDeck.first(), chosen);
			 							playedDeck.addFirst(chosen);
			 							System.out.println("You just played " + chosen);
			 							
			 							// if the chosen card is a Wild card
			 							if (chosen.isWild()) {
			 								// if it is a Wild Draw 4
			 								if (chosen.isPlus4()) {
			 									/** 
			 									 * if there are more than 4 cards in the undealt deck,
			 									 * all 4 topmost cards is added into the opponent's hand
			 									 */
			 									if (fulldeck.getSize() >= 4) {
				 									for (int i = 0; i < 4; i++) {
				 										Card e = fulldeck.deal();
				 										bHand.addFirst(e);
				 									}
			 									}
			 									/**
			 									 * if there are less than 4 cards in the undealt deck,
			 									 * the remaining cards are added into the hand, then print "Out of cards"
			 									 */
			 									else if (fulldeck.getSize() < 4) {
			 										for (int i = 0; i < fulldeck.getSize(); i++) {
			 											Card e = fulldeck.deal();
			 											bHand.addFirst(e);
			 										}
			 										System.out.println("There are no cards left to draw");
			 									}
			 								}
			 								ask = true;
			 								/**
			 								 * ask for the player's choice of color
			 								 */
			 								while (ask) {
			 									System.out.print("Choose a color (lowercase please!) > ");
			 									String color = scanner.nextLine();
				 								if (color.compareTo("red") == 0 || color.compareTo("Red") == 0) {
				 									chosen.changeColor("red");
													System.out.println("-------------------------------------");
													System.out.println("| The color has been changed to RED |");
													System.out.println("-------------------------------------");
				 									ask = false;
				 								}
				 								else if (color.compareTo("blue") == 0 || color.compareTo("Blue") == 0) {
				 									chosen.changeColor("blue");
													System.out.println("--------------------------------------");
													System.out.println("| The color has been changed to BLUE |");
													System.out.println("--------------------------------------");
				 									ask = false;
				 								}
				 								else if (color.compareTo("green") == 0 || color.compareTo("Green") == 0) {
				 									chosen.changeColor("green");
													System.out.println("---------------------------------------");
													System.out.println("| The color has been changed to GREEN |");
													System.out.println("---------------------------------------");
				 									ask = false;
				 								}
				 								else if (color.compareTo("yellow") == 0 || color.compareTo("Yellow") == 0) {
				 									chosen.changeColor("yellow");
													System.out.println("----------------------------------------");
													System.out.println("| The color has been changed to YELLOW |");
													System.out.println("----------------------------------------");
				 									ask = false;
				 								}
				 								// keeps asking until a valid color is put in
				 								else {
				 									System.out.println("Invalid choice! Try again.");
				 								}
			 								}
			 								// changes turn when finished
			 								pturn = false;
			 								bturn = true;
			 								break;
			 							}
			 							
			 							// if the chosen card is a Skip card, BOT lost one turn
			 							else if (chosen.isSkip()) {
			 								pturn = true;
			 								bturn = false;
			 								System.out.println("> BOT just lost their turn!");
			 								break;
			 							}
			 							
			 							// if the chosen card is a Plus 2/Draw 2 card, the opponent has to draw 2 cards
			 							else if (chosen.isPlus2()) {
			 								if (fulldeck.getSize() >= 2) {
				 								for (int i = 0; i < 2; i++) {
				 									Card newcard = fulldeck.deal();
				 									bHand.addFirst(newcard);
				 								}
			 								}
			 								else if (fulldeck.getSize() < 2) {
			 									for (int i = 0; i < fulldeck.getSize(); i++) {
				 									Card newcard = fulldeck.deal();
				 									bHand.addFirst(newcard);
			 									}
			 									System.out.println("There are no cards left.");
			 								}
			 								pturn = false;
			 								bturn = true;
			 								break;
			 							}
			 							// finishes a turn, switches to BOT's turn
			 							pturn = false;
			 							bturn = true;
			 							break;
			 						}
			 						
			 						// in case the player choose a card in their hand, but that card is not playable at the moment
			 						else
			 							System.out.println("You can't play that card. Choose another one.");
			 							pturn = true;
			 							bturn = false;
			 							break;
			 						}
			 					
			 					// in case the player accidentally types in a card that they do not have
			 					else if (chosen == null) {
			 						System.out.println("You don't have that card. Try again!");
			 						pturn = true;
			 						bturn = false;
			 						break;
			 						}
			 				}
			 				
			 				// if the command is "get", new cards are added into the hand
			 				else if (cmd.compareTo("get") == 0) {
			 					if (!fulldeck.isEmpty()) {
				 					Card icard = fulldeck.deal();
				 					pHand.addFirst(icard);
				 					System.out.println("You just got " + icard);
				 					
				 					// keeps dealing cards until the player get a card that is playable
				 					while (!pHand.canPlay(playedDeck.first(), icard)) {
				 						/** if the undealt deck is empty, returns to the input line and let the player
				 						* choose a card in their hand if there is a card that is playable
				 						*/
				 						if (fulldeck.isEmpty()) {
				 							System.out.println("There are no cards left.");
				 							pturn = true;
				 							bturn = false;
				 							// if the player's hand does not have any playable card, the game ends
				 							if (pHand.probe(playedDeck.first()) == null) {
				 			 					System.out.println("You are out of move!");
				 			 					pturn = false;
				 			 					bturn = false;
				 			 					play = false;
				 			 					break;
				 			 				}
				 							break;
				 						}
				 						icard = fulldeck.deal();
				 						pHand.addFirst(icard);
				 						System.out.println("You just got " + icard);
				 					}
				 					if (pHand.canPlay(playedDeck.first(), icard)) {
				 						ask = true;
				 					}
				 					
				 					// asking for the player's decision when having a playable card
				 					while (ask) {
				 						System.out.println("You are holding a playable card " + icard);
				 						System.out.print("Type Y to play, type N to hold that card >>>> ");
				 						String choice = scanner.nextLine();
				 						// if Y/y, starts the player's turn again to let them choose/play the card
				 						if (choice.compareTo("Y") == 0 || choice.compareTo("y") == 0) {
				 							pturn = true;
				 							bturn = false;
				 							ask = false;
				 							break;
				 						}
				 						
				 						// if N/n, continues to BOT's turn
				 						else if (choice.compareTo("N") == 0 || choice.compareTo("n") == 0) {
				 							bturn = true;
				 							pturn = false;
				 							ask = false;
				 							break;
				 						}
				 						
				 						// keeps asking until a valid command is typed in
				 						else
				 							System.out.println("Invalid command! Please try again.");
				 					}
			 					}
			 					// if the undealt deck is empty, print "There are no cards left."
			 					else
			 						System.out.println("There are no cards left.");
			 					
			 				}
			 				// keeps asking new command until a valid one is typed in
			 				else if (cmd.compareTo("get") != 0 || cmd.compareTo("quit") != 0 || cmd.compareTo("play") != 0) {
			 					System.out.println("Invalid command! Please try again."); 
			 					}
		 					}
		 				
		 				
		 				// BOT's turn
		 				while (bturn) {
		 					Card bcard = bHand.probe(playedDeck.first()); // getting the first playable card in BOT's hand
		 					if (bcard != null) {
		 						bHand.playCard(playedDeck.first(), bcard);
		 						playedDeck.addFirst(bcard);
		 						System.out.println("> BOT just played " + bcard);
		 						
		 						/**
		 						 * If BOT plays a Wild card, the mode color in its hand is chose as the new color
		 						 */
		 						if (bcard.isWild()) {
		 							int red = 0;
		 							int blue = 0;
		 							int green = 0;
		 							int yellow = 0;
		 							for (int i = 0; i < bHand.checkSize(); i++) {
		 								Card car = bHand.getCard(i);
		 								if (car.isWild()) {
		 									continue;
		 								}
		 								else if (car.getColor().compareTo("red") == 0) {
		 									red++;
		 								}
		 								else if (car.getColor().compareTo("blue") == 0) {
		 									blue++; 
		 								}
		 								else if (car.getColor().compareTo("green") == 0) {
		 									green++;
		 								}
		 								else if (car.getColor().compareTo("yellow") == 0) {
		 									yellow++;
		 								}
		 							}
		 							if (red >= blue && red >= green && red >= yellow) {
		 								bcard.changeColor("red");
										System.out.println("-------------------------------------");
										System.out.println("| The color has been changed to RED |");
										System.out.println("-------------------------------------");
		 							}
		 							else if (blue >= red && blue >= green && blue >= yellow) {
		 								bcard.changeColor("blue");
		 								System.out.println("--------------------------------------");
										System.out.println("| The color has been changed to BLUE |");
										System.out.println("--------------------------------------");
		 							}
		 							else if (green >= red && green >= blue && green >= yellow) {
		 								bcard.changeColor("green");
		 								System.out.println("---------------------------------------");
										System.out.println("| The color has been changed to GREEN |");
										System.out.println("---------------------------------------");
		 							}
		 							else if (yellow >= red && yellow >= blue && yellow >= green) {
		 								bcard.changeColor("yellow");
		 								System.out.println("----------------------------------------");
										System.out.println("| The color has been changed to YELLOW |");
										System.out.println("----------------------------------------");
		 							}
		 							// if it is a Draw 4 Wild card, the player has to take in 4 cards
		 							if (bcard.isPlus4()) {
		 								if (fulldeck.getSize() >= 4) {
				 							for (int i = 0; i < 4; i++) {
				 								Card e = fulldeck.deal();
				 								pHand.addFirst(e);
				 								System.out.println("You got " + e);
				 							}
			 							}
		 								else if (fulldeck.getSize() < 4) {
		 									for (int i = 0; i < fulldeck.getSize(); i++) {
		 										Card e = fulldeck.deal();
		 										pHand.addFirst(e);
		 										System.out.println("You got " + e);
		 									}
		 									System.out.println("There are no card left.");
		 								}
		 							// changes to players' turn
		 							pturn = true;
		 							bturn = false;
		 							break;
		 							}
		 						}
		 						
		 						// if the card is a Skip card, the player lose their turn
		 						else if (bcard.isSkip()) {
		 							System.out.println("You lost a turn!");
		 							pturn = false;
		 							bturn = true;
		 							break;
		 						}
		 						
		 						// if the chosen card is a Draw 2 card, the player takes in 2 cards
		 						else if (bcard.isPlus2()) {
		 							if (fulldeck.getSize() >= 2 ) {
			 							for (int i = 0; i < 2; i++) {
			 								Card e = fulldeck.deal();
			 								pHand.addFirst(e);
			 								System.out.println("You got " + e);
			 							}
		 							}
		 							else if (fulldeck.getSize() < 2) {
		 								for (int i = 0; i < fulldeck.getSize(); i++) {
	 										Card e = fulldeck.deal();
	 										pHand.addFirst(e);
	 										System.out.println("You got " + e);
	 									}
	 									System.out.println("There are no cards left.");
		 							}
		 							pturn = true;
		 							bturn = false;
		 							break;
		 						}
		 						pturn = true;
		 						bturn = false;
		 						break;
		 					} 
		 					
		 					/** 
		 					 * If there are no playable cards in BOT's hand, it deals cards until it gets one
		 					 */
		 					else {
		 						if (fulldeck.getSize() > 0) {
		 							bcard = fulldeck.deal();
		 							bHand.addFirst(bcard);
		 							while (playedDeck.first().canPlayCard(bcard) == false) {
		 								if (fulldeck.getSize() > 0) {
		 									bcard = fulldeck.deal();
		 									bHand.addFirst(bcard);
		 								}
		 								// if the deck is empty, stop drawing cards and move on
		 								else if (fulldeck.getSize() == 0) {
		 									System.out.println("There are no cards left.");
		 									break;
		 								}
		 							}
		 						}
		 						// starts BOT's turn again to let it play the card
		 						pturn = false;
		 						bturn = true;
		 						break;
		 					}
		 				}
		 				
		 				/** 
		 				 * The player wins when their hand is empty first
		 				 */
		 				if (pHand.checkSize() == 0) {
		 					System.out.println("UNO! You win!");
		 					pturn = false;
		 					bturn = false;
		 					play = false;
		 				}
		 				
		 				/**
		 				 * The BOT wins when its hand is empty first
		 				 */
		 				else if (bHand.checkSize() == 0) {
		 					System.out.println("> BOT said UNO :( You lose!");
		 					pturn = false;
		 					bturn = false;
		 					play = false;
		 				}
		 				
		 				/** 
		 				 * The player is out of move if the undealt deck is empty and their hand has no playable card
		 				 */
		 				else if (pHand.probe(playedDeck.first()) == null && fulldeck.isEmpty()) {
		 					System.out.println("You are out of move!");
		 					pturn = false;
		 					bturn = false;
		 					play = false;
		 				}
		 				
		 				/**
		 				 * BOT is out of move if the undealt deck is empty and its hand has no playable card
		 				 */
		 				else if (bHand.probe(playedDeck.first()) == null && fulldeck.isEmpty()) {
		 					System.out.println("> BOT is out of move!");
		 					pturn = false;
		 					bturn = false;
		 					play = false;
		 				}
		 				
		 				/**
		 				 * It is a tie if both the player and BOT are out of move
		 				 */
		 				else if (bHand.probe(playedDeck.first()) == null && pHand.probe(playedDeck.first()) == null && fulldeck.isEmpty()) {
		 					System.out.println("It's a tie!");
		 					pturn = false;
		 					bturn = false;
		 					play = false;
		 				}
		 			}
		 		}
		 		// keep getting new command until a valid one is put in
		 		else
		 			System.out.println("Invalid command! Please try again.");
		}
		 		System.out.println("Bye bye!");
	}
}
