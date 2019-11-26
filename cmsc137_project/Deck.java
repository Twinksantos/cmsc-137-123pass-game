import java.util.*;

public class Deck {

	private Card[] deck; // Contains the full deck of cards

	private int usedCards; // number of counts used/not in the deck currently


	public Deck(){
		deck = new Card[52];
		int cardCounter = 0; // checks how many cards are created

		for (int suit = 0; suit <= 3; suit++){
			for (int value = 1; value <= 13; value++){
				deck[cardCounter] = new Card(value, suit);
				cardCounter++;
			}
		}

		usedCards = 0;
	}

	public void shuffleDeck() { // shuffles the deck of cards
		for (int i = deck.length-1; i>0; i-- ){
			int rand = (int)Math.random()*(i+1);
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		usedCards = 0;
	}

	public int remainingCards() { // prints the remaining cards in the deck
		return deck.length - usedCards;
	}

	public Card drawCard() { // function that draws a card from the deck
		if (usedCards == deck.length){
			throw new IllegalStateException("Deck is empty!");
		}
		usedCards++;
		return deck[usedCards - 1];
	}
}
