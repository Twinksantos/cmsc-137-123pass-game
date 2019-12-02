import java.util.*;

public class PassGame {

	public static void main(String[] args) {
		
		boolean playerRequirementSatisfied = false;

		System.out.println("==============================");
		System.out.println("       1-2-3 PASS GAME!!!     ");
		System.out.println("==============================");

		// Prompt to ask how many players are playing
		Scanner input = new Scanner(System.in);
		System.out.println("How many players are playing (Minimum of 3 Players, Maximum of 13 players): ");
		String playerCountInit = input.nextLine();
		int playerCount = Integer.parseInt(playerCountInit);
		int rankCount = 0;
		while (playerRequirementSatisfied != true){
			if (playerCount < 3 || playerCount > 13){ // if the input is invalid
				System.out.println("INVALID NUMBER OF PLAYERS");
				System.out.println(" ");
				System.out.println("How many players are playing (Minimum of 3 Players, Maximum of 13 players): ");
				playerCountInit = input.nextLine();
				playerCount = Integer.parseInt(playerCountInit);
			} else {
				playerRequirementSatisfied = true;
			}
		}
		
		Player[] playerArray = new Player[playerCount];
		Player[] rankingArray = new Player[playerCount];	// ARRAY FOR SAVING RANKS WHILE IN GAME, FOR LATER PRINTING

		// Setting up the players

		for (int i = 0; i < playerCount; i++){
			Scanner userName = new Scanner(System.in);
			System.out.println("Enter player's name: ");
			String name = userName.nextLine();
			playerArray[i] = new Player(name);
		}

		for (int i = 0; i < playerCount; i++){
			System.out.println("Player: " + playerArray[i].getName());
		}

		Deck deck = new Deck(playerCount); //initializes the deck to be used

		deck.shuffleDeck(); //shuffles the deck


		// shows that the cards where shuffled and distributed (NOTE: HINDI DAPAT MAKITA ITO SA ACTUAL GAME. THIS JUST HELPS THE DEVELOPER TO SEE NA GUMAGANA ANG SHUFFLING)		
		for (int distributionCount = 0; distributionCount < 4; distributionCount++){
			for (int i = 0; i < playerCount; i++){
				Card newCard = deck.drawCard();
				playerArray[i].addCard(newCard);
				System.out.println("The card drawn by " + playerArray[i].getName() + " is "  + newCard);
			}
		}
		

		// Main Game

		boolean endOfGame = false; // checks if all players have submitted their hands
		ArrayList<Card> passedCards = new ArrayList<Card>(); // Array List of Cards to be passed
		int noOfFinishedPlayers = 0; // no. of players done
		int gamePlayers = playerCount;
		int counts = gamePlayers;

		while(endOfGame != true){
			
			for (int i = 0; i < counts; i++){
				System.out.println(" ");
				System.out.println(" ");
				System.out.println("***********************************************");
				System.out.println(" ");
				System.out.println("Player " + playerArray[i].getName() + "'s turn.");
				System.out.println(" ");
				System.out.println("***********************************************");
				System.out.println("***************** CURRENT HAND ****************");
				for (int j = 0; j < 4; j++){
					Card handCard = playerArray[i].getCard(j);
					System.out.println("[" + (j+1) + "] - " + handCard);
				}
				System.out.println("-----------------------------------------------");
				System.out.println("MOVE OPTIONS:                                  ");
				System.out.println(" ");
				System.out.println("[P] - Pass a card                              ");
				System.out.println("[S] - Submit hand                              ");
				System.out.println(" ");
				System.out.println("ENTER THE LETTER OF DESIRED MOVE:              ");
				Scanner userInput = new Scanner(System.in);
				char choice = userInput.next().charAt(0);
				
				//Decision made by user

				// if the user decides to pass a card
				if (choice == 'P'){
					System.out.println(" ");
					for (int j = 0; j < 4; j++){ // prints the current hand
						Card handCard = playerArray[i].getCard(j);
						System.out.println("[" + (j+1) + "] - " + handCard);
					}
					System.out.println("PICK A CARD TO PASS: "); // player chooses what card to be passed
					Scanner cardInput = new Scanner(System.in);
					String cardInputInit = input.nextLine();
					int cardChoice = Integer.parseInt(cardInputInit);

					if (cardChoice == 1){
						Card cardToBePassed = playerArray[i].getCard(0);
						System.out.println("You have passed the * " + cardToBePassed + " *.");
						passedCards.add(cardToBePassed);
						playerArray[i].removeCard(0);
					} else if (cardChoice == 2){
						Card cardToBePassed = playerArray[i].getCard(1);
						System.out.println("You have passed the * " + cardToBePassed + " *.");
						passedCards.add(cardToBePassed);
						playerArray[i].removeCard(1);
					} else if (cardChoice == 3){
						Card cardToBePassed = playerArray[i].getCard(2);
						System.out.println("You have passed the * " + cardToBePassed + " *.");
						passedCards.add(cardToBePassed);
						playerArray[i].removeCard(2);
					} else if (cardChoice == 4){
						Card cardToBePassed = playerArray[i].getCard(3);
						System.out.println("You have passed the * " + cardToBePassed + " *.");
						passedCards.add(cardToBePassed);
						playerArray[i].removeCard(3);
					}

				} else if (choice == 'S'){
					// checks if the hand of the player has the same value (4 kings, 4 queens, 4 jacks, 4 aces, 4 2s, 4 3s, etc.)
					ArrayList<Card> cardsChecked = new ArrayList<Card>();

					Card cardBeingChecked1 = playerArray[i].getCard(0);
					Card cardBeingChecked2 = playerArray[i].getCard(1);
					Card cardBeingChecked3 = playerArray[i].getCard(2);
					Card cardBeingChecked4 = playerArray[i].getCard(3);
					
					// if the cards on the hand has the same value
					if (cardBeingChecked1.getValue() == cardBeingChecked2.getValue() && cardBeingChecked1.getValue() == cardBeingChecked3.getValue() && cardBeingChecked1.getValue() == cardBeingChecked4.getValue()){
						noOfFinishedPlayers = noOfFinishedPlayers + 1;
						rankingArray[rankCount] = new Player(playerArray[i].getName());
						rankCount ++;
						for(int jar = i; jar < counts-1; jar++){
							playerArray[jar] = playerArray[jar+1];
						}
						// playerArray[i].clearHand();
						// if(i != counts){
						// 	playerArray[i]= new Player(playerArray[i+1].getName());
						// 	player	
						// }
						System.out.println("CONGRATULATIONS! YOU HAVE SUBMITTED A HAND WITH THE SAME RANK!!!");
						System.out.println(" ");
						System.out.println("PLACEMENT: " + noOfFinishedPlayers + " / " + gamePlayers);
						counts --;
						for (int j = 0; j < counts; j++){ 
							if (playerArray[i] != playerArray[j]){
								for (int k = j; k < counts - 1; k++){// ELEMENT SHIFTING
									playerArray[k] = playerArray[k+1];
									playerArray[k+1].clearHand();
								}
							}
						}
					} else { // if the cards on the hand have different values
						System.out.println("SORRY! YOU ATTEMPTED TO SUBMIT A HAND WITH DIFFERENT RANKS!!!");
						System.out.println("THE ONLY MOVE YOU CAN MAKE IS TO PASS A CARD!");
						System.out.println(" ");
						for (int j = 0; j < 4; j++){
							Card handCard = playerArray[i].getCard(j);
							System.out.println("[" + (j+1) + "] - " + handCard);
						}
						System.out.println("PICK A CARD TO PASS: "); // forces the user to pass a card
						Scanner cardInput = new Scanner(System.in);
						String cardInputInit = input.nextLine();
						int cardChoice = Integer.parseInt(cardInputInit);

						if (cardChoice == 1){
							Card cardToBePassed = playerArray[i].getCard(0);
							System.out.println("You have passed the * " + cardToBePassed + " *.");
							passedCards.add(cardToBePassed);
							playerArray[i].removeCard(0);
						} else if (cardChoice == 2){
							Card cardToBePassed = playerArray[i].getCard(1);
							System.out.println("You have passed the * " + cardToBePassed + " *.");
							passedCards.add(cardToBePassed);
							playerArray[i].removeCard(1);
						} else if (cardChoice == 3){
							Card cardToBePassed = playerArray[i].getCard(2);
							System.out.println("You have passed the * " + cardToBePassed + " *.");
							passedCards.add(cardToBePassed);
							playerArray[i].removeCard(2);
						} else if (cardChoice == 4){
							Card cardToBePassed = playerArray[i].getCard(3);
							System.out.println("You have passed the * " + cardToBePassed + " *.");
							passedCards.add(cardToBePassed);
							playerArray[i].removeCard(3);
						}
					}
				}
			}

			//Passing of Cards
			for (int i = 0; i < counts; i++){
				System.out.println(gamePlayers);
				System.out.println(counts);
				if(counts-1 == 0){
					//Boolean endOfGame = true;
					rankingArray[gamePlayers-1] = new Player(playerArray[0].getName());
					System.out.println("The game has ended! These are the following rankings of each player: \n");
					for(int r = 0; r < gamePlayers; r++){
						System.out.println("Rank " + (r+1) + ": " + rankingArray[r].getName());
					}
					System.exit(1);
				}
				else{
					Card newHandCard = passedCards.get(0);
				if ((i + 1) == counts){
					playerArray[0].addCard(newHandCard);
					passedCards.remove(0);
				} else {
					playerArray[i+1].addCard(newHandCard);
					passedCards.remove(0);
				}
				}		
			}
		}		
	}
}