package finaleproj;
/**
 * Simulate a player's hand of cards
 * 
 * @author Hien Ha
 * @version 12.0.2
 * @since 1.0
 */

public class PlayersHand {
	/** 
	 * Creates a node with a card as an element, and 2 nodes on 2 sides
	 * (similar to that in HW3)
	 */
	private static class Node<Card>{
		private Card element;
		private Node<Card> prev, next;
		public Node(Card e, Node<Card> p, Node<Card> n) {
			element = e;
			next = n;
			prev = p;
		}
		
		public Card getElement() { return element; }
		public Node<Card> getNext() { return next; }
		public Node<Card> getPrev() { return prev; }
		public void setPrev(Node<Card> p) { prev = p; }
		public void setNext(Node<Card> n) { next = n; }
	}
	
	/**
	 * The head node
	 */
	private Node<Card> header;
	/**
	 * The tail node
	 */
	private Node<Card> trailer;
	/**
	 * The size of the hand starts at 0 
	 */
	private int size = 0;
	
	/**
	 * Constructor
	 */
	public PlayersHand() {
		header = new Node<>(null,null,null);
		trailer = new Node<>(null,header,null);
		header.setNext(trailer);
	}
	
	/** 
	 * Gets the number of cards in the hand 
	 * @return the size of the hand
	 */
	public int checkSize() {
		return size;
	}
	
	/** 
	 * Fetches a card given an integer location
	 * @param loc An address of the array
	 * @return the card at that location in the hand
	 */
	public Card getCard(int loc) {
		Node<Card> node = header.getNext();
		Card card = node.getElement();
		// loops from 0 to the given location, then returns the card at the last loop
		for (int i = 0; i < loc; i++) {
			node = node.getNext();
			card = node.getElement();
		}
		return card;
	}
	
	/** 
	 * Detects if the hand is empty
	 * @return true if the size = 0, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/** 
	 * Gets the first(newest/topmost) card in the hand
	 * @return null if there is nothing in the hand, the first card otherwise
	 */
	public Card first() {
		if (isEmpty()) { 
			return null;
			}
		return header.getNext().getElement();
		}
	
	/** 
	 * Detects the first card that is playable in the hand given the most recently played card
	 * @param topCard The most recently played card
	 * @return a card if it can be played, null if none of the card can be played
	 */
	public Card probe(Card topCard) {
		Node<Card> node = header.getNext();
		Card card = node.getElement();
		for (int i = 0; i < size; i++) {
			if (canPlay(topCard, card)) {
				return card;
			}
			node = node.getNext();
			card = node.getElement();
		}
		return null;
	}
	
	/** 
	 * Inserts a new card into the hand
	 * @param e The desired card
	 */
	public void addFirst(Card e) {
		Node<Card> newhead = new Node<>(e, header, header.getNext());
		header.getNext().setPrev(newhead);
		header.setNext(newhead);
		size = size + 1;
	}
	
	/** 
	 * Checks if the chosen card can be played 
	 * @param topCard the most recently played card
	 * @param youcard the chosen card
	 * @return true if yourcard can be placed on topCard, false if not
	 * */
	public boolean canPlay(Card topCard, Card yourcard) {
		return (topCard.canPlayCard(yourcard));
		} 
	
	/** 
	 * Removes and returns the chosen card 
	 * @param topCard The most recently played card
	 * @param card the card that the player chose
	 * @return the card that is removed, null if the declared card is not in the hand
	 * */
	public Card playCard(Card topCard, Card card) {
		Node<Card> node = header.getNext();
		Card anoCard = node.getElement();
		if (canPlay(topCard, card)) {
			for (int i = 0; i < size; i++) {
				if (card.getName() == anoCard.getName()) {
					node.getNext().setPrev(node.getPrev());
					node.getPrev().setNext(node.getNext());
					size = size - 1;
					return anoCard;
				}
				node = node.getNext();
				anoCard = node.getElement();
			}
		}
		return null;
	}
	
	/** 
	 * Lists out every cards in the hand 
	 * If there are no cards left, print: "This hand is currently empty!"
	 */
	public void displayHand() {
		Node<Card> node = header.getNext();
		Card name = node.getElement();
		if (isEmpty()) {
			System.out.println("This hand is currently empty!");
		}
		for (int i = 0; i < size; i++) {
			System.out.println(name);
			node = node.getNext();
			name = node.getElement();
		}
	}
	
	/** 
	 * Finds a card given its name
	 * @param cardname The name of the chosen card (the typed input)
	 * @return the card with that name 
	 * @return null if the hand is empty or the card is not in the hand
	 */
	public Card findCard(String cardname) {
		Node<Card> node = header.getNext();
		Card card = node.getElement();
		if (!isEmpty()) {
			for (int i = 0; i < size; i++) {
				if (card.getName().compareTo(cardname) == 0) {
					return card;
				}
				node = node.getNext();
				card = node.getElement();
			}
			return null;
		}
		return null; 
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
