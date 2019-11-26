import java.util.*;

public class PassGame {

	public static void main(String[] args) {

		System.out.println("==============================");
		System.out.println("       1-2-3 PASS GAME!!!     ");
		System.out.println("==============================");

		Scanner input = new Scanner(System.in);
		System.out.println("How many players are playing (must be more than 3: ");
		String playerCountInit = input.nextLine();
		int playerCount = Integer.parseInt(playerCountInit);

		Player[] playerArray = new Player[playerCount];

		// Setting up the players

		for (int i = 0; i < 3; i++){
			Scanner userName = new Scanner(System.in);
			System.out.println("Enter player's name: ");
			String name = userName.nextLine();
			playerArray[i] = new Player(name);
		}

		for (int i = 0; i < playerCount; i++){
			System.out.println("Player: " + playerArray[i].getName());
		}

		Deck deck = new Deck();

		deck.shuffleDeck();


	}

}