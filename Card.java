package finaleproj;
/**
 * Creates a Card object
 * 
 * @author Hien Ha
 * @version 12.0.2
 * @since 1.0
 */

public class Card {
	/**
	 * The integer value of the card
	 */
	protected int value;
	/**
	 * The color of the card
	 */
	protected String color;
	/**
	 * The full name of the card
	 */
	protected String name;
	
	/** 
	 * Constructor that create a card with given fields 
	 */
	public Card (int num, String c, String Name) {
		value = num;
		color = c;
		name = Name;
	}
	
	/** 
	 * Gets the value
	 * @return the value of the card
	 */
	public int getValue() {
		return value;
	}
	
	/** 
	 * Gets the color
	 * @return the color of the card
	 */
	public String getColor() {
		return color;
	}
	
	/** 
	 * Gets the name
	 * @return the name of the card
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Checks if the card is a Wild card 
	 * @return true if it is, false otherwise
	 */
	public boolean isWild() {
		if (name.compareTo("Wild") == 0 || name.compareTo("Wild Plus 4") == 0) {
			return true;
		}
		return false;
	}
	
	/** 
	 * Checks if the card is a Plus 2 card 
	 * @return true if it is, false otherwise
	 */
	public boolean isPlus2() {
		// Let each Plus 2 card has the value = 11
		if (value == 11) {
			return true;
		}
		return false;
	}
	
	/** 
	 * Checks if the card is a Plus 4 card
	 * @return true if it is, false otherwise
	 */
	public boolean isPlus4() {
		// Let each Plus 4 card has the value = 12
		if (value == 12) {
			return true;
		}
		return false;
	}
	
	/** 
	 * Checks if the card is a Skip card 
	 * @return true if it is, false otherwise
	 */
	public boolean isSkip() {
		// Let each Skip card has the value = 10
		if (value == 10) {
			return true;
		}
		return false;
	}
	
	/** 
	 * Changes the color of a card (use for Wild cards)
	 */
	public void changeColor(String cardcolor) {
		color = cardcolor;
	}
	
	/** 
	 * Checks if a card is playable
	 * @return true if the other card is a Wild card, or if both cards have the same value/color
	 * @return false if none of the above condition is met
	 */
	public boolean canPlayCard(Card yourcard) {
		if (yourcard.isWild()) {
			return true;
		}
		else if (value == yourcard.getValue() || color == yourcard.getColor()) {
			return true;
		}
		return false;
	}
	
	/** 
	 * Converts the card into String form
	 * @return the card's name
	 */
	public String toString() {
		return getName();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}