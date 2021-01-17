package finaleproj;
import java.util.Random;
/** This is essentially a mini-sized version of FullDeck class. It has 24 cards in total, so using this
 * will allow you to test out all the end results without having to spend too much time on playing the game.
 * 
 * 
 * 
 * @author Hien Ha
 * @version 12.0.2
 * @since 1.1
 */

public class MiniDeck {
	/** The number of cards in the deck */
	protected int size;
	/** The deck array */
	protected Card[] deck;
	
	/**
	 * This is the constructor. 
	 * Once being called, it initiates a full deck with 21 cards 
	 */
	public MiniDeck(){
		/** Red Cards */
		Card red0 = new Card(0,"red", "Red 0");
		Card red1 = new Card(1,"red", "Red 1");
		Card red2 = new Card(2,"red", "Red 2");
		Card red3 = new Card(3,"red", "Red 3");
		Card red8 = new Card(8,"red", "Red 8");
		Card red9 = new Card(9,"red", "Red 9");
		Card redSkip = new Card(10,"red", "Red Skip");
		
		/** Yellow Cards */
		Card yellow0 = new Card(0,"yellow", "Yellow 0");
		Card yellow1 = new Card(1,"yellow", "Yellow 1");
		Card yellow2 = new Card(2,"yellow", "Yellow 2");
		Card yellow4 = new Card(4, "yellow", "Yellow 4");
		Card yellow5 = new Card(5,"yellow", "Yellow 5");
		Card yellowSkip = new Card(10,"yellow", "Yellow Skip");
		Card yellowPlus2 = new Card(11,"yellow", "Yellow Plus 2");
		
		/** Blue Cards */
		Card blue4 = new Card(4, "blue", "Blue 4");
		Card blue5 = new Card(5, "blue", "Blue 5");
		Card blue9 = new Card(9,"blue", "Blue 9");
		
		/** Wild Cards */
		Card wild = new Card(13, null, "Wild");
		Card wildPlus4 = new Card(12, null, "Wild Plus 4");
		
		deck = new Card[]{red0, red1, red1, red2, red2, red8, red9, redSkip, red3, blue4, blue5, blue9,
				yellow0, yellowSkip, yellowSkip, yellowPlus2, yellowPlus2, yellow1, yellow2, yellow2, yellow4, yellow5,
				wild, wildPlus4} ;
		size = 24;
	}
	
	/**
	 * Gets the number of cards that is currently in the deck
	 * @return the size of the deck
	 */
	public int getSize() {
		return size;
	}
	
	/** 
	 * Shuffles to get an unordered deck
	 */
	public void shuffle(){
		Random rand = new Random(); // use java.util.Random
		for (int i = size - 1; i > 0; i--) {
			int randomPlace = rand.nextInt(i + 1); // this will randomize a number in the range 1 - (i + 1)
			Card keep = deck[i];
			deck[i] = deck[randomPlace];
			deck[randomPlace] = keep;
		}
	}
	
	/** 
	 * Removes the topmost card and return it
	 * @return the topmost card
	 */
	public Card deal() {
		Card card = deck[size - 1];
		size = size - 1;
		return card;
	}
	
	/**
	 * Puts a card back into the deck
	 * More specifically, the card will be put at the other end of the deck
	 */
	public void putBack(Card card) {
		size = size + 1;
		for (int i = size - 1; i > 0; i--) {
			deck[i] = deck[i - 1];
		}
		deck[0] = card;
	}
	
	/** 
	 * Confirms if the deck is out of cards
	 * @return true if the size is 0, false if there are cards in the array
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
