import java.util.*;

public class Card {

	// Code for the suite
	public final static int SPADES = 0;
	public final static int HEARTS = 1;
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;

	// Code for the ranks
	public final static int ACE = 1;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;

	private final int suit;
	private final int value;

	public Card(int cardValue, int cardSuit){
		value = cardValue;
		suit = cardSuit;
	}

	public int getSuit() { // Gets the specified suit of cards
		return suit;
	}

	public int getValue() { // Gets the specified rank of cards
		return value;
	}

	public String getSuitString() { // Converts the "suit" code into a string
		switch(suit){
			case SPADES: return "Spades";
			case HEARTS: return "Hearts";
			case DIAMONDS: return "Diamonds";
			default: return "Clubs";
		}
	}


	public String getValueString() { // Converts the "rank" code into a string
		switch(value){
			case 1: return "Ace";
			case 2: return "2";
			case 3: return "3";
			case 4: return "4";
			case 5: return "5";
			case 6: return "6";
			case 7: return "7";
			case 8: return "8";
			case 9: return "9";
			case 10: return "10";
			case 11: return "Jack";
			case 12: return "Queen";
			default: return "King";
		}
	}


	public String toString(){ // Creates the "full name" of the card
		return getValueString() + " of " + getSuitString();
	}

}