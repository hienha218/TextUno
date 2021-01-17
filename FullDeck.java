package finaleproj;
import java.util.Random;
/** This is a full-sized version of an Uno Deck. It has 100 cards in total (Reverse cards are not included).
 * 
 * 
 * @author Hien Ha
 * @version 12.0.2
 * @since 1.0
 */

public class FullDeck {
	/**
	 * The size of the deck
	 */
	protected int size;
	/**
	 * The deck array
	 */
	protected Card[] deck;
	
	/**
	 * This is the constructor. 
	 * Once being called, it initiates a full deck with 21 cards 
	 */
	public FullDeck(){
		/** Red Cards */
		Card red0 = new Card(0,"red", "Red 0");
		Card red1 = new Card(1,"red", "Red 1");
		Card red2 = new Card(2,"red", "Red 2");
		Card red3 = new Card(3,"red", "Red 3");
		Card red4 = new Card(4,"red", "Red 4");
		Card red5 = new Card(5,"red", "Red 5");
		Card red6 = new Card(6,"red", "Red 6");
		Card red7 = new Card(7,"red", "Red 7");
		Card red8 = new Card(8,"red", "Red 8");
		Card red9 = new Card(9,"red", "Red 9");
		Card redSkip = new Card(10,"red", "Red Skip");
		Card redPlus2 = new Card(11,"red", "Red Plus 2");
		
		/** Yellow Cards */
		Card yellow0 = new Card(0,"yellow", "Yellow 0");
		Card yellow1 = new Card(1,"yellow", "Yellow 1");
		Card yellow2 = new Card(2,"yellow", "Yellow 2");
		Card yellow3 = new Card(3,"yellow", "Yellow 3");
		Card yellow4 = new Card(4,"yellow", "Yellow 4");
		Card yellow5 = new Card(5,"yellow", "Yellow 5");
		Card yellow6 = new Card(6,"yellow", "Yellow 6");
		Card yellow7 = new Card(7,"yellow", "Yellow 7");
		Card yellow8 = new Card(8,"yellow", "Yellow 8");
		Card yellow9 = new Card(9,"yellow", "Yellow 9");
		Card yellowSkip = new Card(10,"yellow", "Yellow Skip");
		Card yellowPlus2 = new Card(11,"yellow", "Yellow Plus 2");
		
		/** Blue Cards */
		Card blue0 = new Card(0,"blue", "Blue 0");
		Card blue1 = new Card(1,"blue", "Blue 1");
		Card blue2 = new Card(2,"blue", "Blue 2");
		Card blue3 = new Card(3,"blue", "Blue 3");
		Card blue4 = new Card(4,"blue", "Blue 4");
		Card blue5 = new Card(5,"blue", "Blue 5");
		Card blue6 = new Card(6,"blue", "Blue 6");
		Card blue7 = new Card(7,"blue", "Blue 7");
		Card blue8 = new Card(8,"blue", "Blue 8");
		Card blue9 = new Card(9,"blue", "Blue 9");
		Card blueSkip = new Card(10,"blue", "Blue Skip");
		Card bluePlus2 = new Card(11,"blue", "Blue Plus 2");
		
		/** Green Cards */
		Card green0 = new Card(0,"green", "Green 0");
		Card green1 = new Card(1,"green", "Green 1");
		Card green2 = new Card(2,"green", "Green 2");
		Card green3 = new Card(3,"green", "Green 3");
		Card green4 = new Card(4,"green", "Green 4");
		Card green5 = new Card(5,"green", "Green 5");
		Card green6 = new Card(6,"green", "Green 6");
		Card green7 = new Card(7,"green", "Green 7");
		Card green8 = new Card(8,"green", "Green 8");
		Card green9 = new Card(9,"green", "Green 9");
		Card greenSkip = new Card(10,"green", "Green Skip");
		Card greenPlus2 = new Card(11,"green", "Green Plus 2");
		
		/** Wild Cards */
		Card wild = new Card(13, null, "Wild");
		Card wildPlus4 = new Card(12, null, "Wild Plus 4");
		
		/** The full deck */
		deck = new Card[]{red0, red1, red1, red2, red2, red3, red3, red4, red4, red5, red5, red6, red6, red7, red7, red8, red8, red9, red9, redSkip, redSkip, redPlus2, redPlus2,
				yellow0, yellow1, yellow1, yellow2, yellow2, yellow3, yellow3, yellow4, yellow4, yellow5, yellow5, yellow6, yellow6, yellow7, yellow7, yellow8, yellow8, yellow9, yellow9, yellowSkip, yellowSkip, yellowPlus2, yellowPlus2,
				blue0, blue1, blue1, blue2, blue2, blue3, blue3, blue4, blue4, blue5, blue5, blue6, blue6, blue7, blue7, blue8, blue8, blue9, blue9, blueSkip, blueSkip, bluePlus2, bluePlus2,
				green0, green1, green1, green2, green2, green3, green3, green4, green4, green5, green5, green6, green6, green7, green7, green8, green8, green9, green9, greenSkip, greenSkip, greenPlus2, greenPlus2,
				wild, wild, wild, wild, wildPlus4, wildPlus4, wildPlus4, wildPlus4};
		size = 100;
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
			int randomPlace = rand.nextInt(i + 1); // randomizes an integer from 1 to i+1 (not include i+1)
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
	 * More specifically, the card will be put at the other end of the deck (front)
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
