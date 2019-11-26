import java.util.*;

public class Player {

	private String name;

	private ArrayList<Card> player;

	public Player(String name) { // Shows the cards that are currently on the hand of the user 
		this.name = name;
		player = new ArrayList<Card>();
	}

	public void clearHand() { // Empties the hand of the user
		player.clear();
	}

	public void addCard(Card c){ // Adds a card to the player's hand
		player.add(c);
	}

	public void removeCard(Card c){ // Removes a card from the player's hand
		player.remove(c);
	}


	public String getName() {
		return name;
	}


}