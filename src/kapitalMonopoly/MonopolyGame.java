package kapitalMonopoly;

import kapitalMonopolyObservers.ThreeListener;
import kapitalMonopolyObservers.TurnListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonopolyGame {

	public static final int ICON_SIZE_X = 20;
	public static final int ICON_SIZE_Y = 20;

	private static MonopolyGame monopolyGame = null;

	public static Cup cup = new Cup();
	public enum CupInputs 
	{
		RRS, RRR, RR, R; 
	} 

	public DeckInterface chanceDeck = DeckFactory.getDeck("Chance Deck");
	public DeckInterface communityChestDeck = DeckFactory.getDeck("Community Chest Deck");
	public DeckInterface rollThreeDeck = DeckFactory.getDeck("Roll Three Deck");
	public Pool pool = new Pool();
	public static Board gameBoard; 
	public HashMap<DeedSquare, Player> deedOwners = new HashMap<DeedSquare, Player>();

	public static Player[] players = new Player[8];
	int i=0;
	public static Player currentPlayer = players[0];

	public synchronized static MonopolyGame getInstance(){
		if(monopolyGame == null) {
			monopolyGame = new MonopolyGame();
			gameBoard = new Board();
			players[0] = new Player("BoardPic/blackish.png",1);
			players[1] = new Player("BoardPic/blue.png",2);
			players[2] = new Player("BoardPic/dark_blue.png",3);
			players[3] = new Player("BoardPic/dark_red.png",4);
			players[4] = new Player("BoardPic/dark_yellow.png",5);
			players[5] = new Player("BoardPic/gri.png",6);
			players[6] = new Player("BoardPic/nevzat.png",7);
			players[7] = new Player("BoardPic/red.png",8);
			currentPlayer = players[0];
		}
		return monopolyGame;
	}

	// Observer for end turn
	static ArrayList<TurnListener> turnListeners = new ArrayList<TurnListener>();

	public void addTurnListener(TurnListener lis) {
		turnListeners.add(lis);
	}

	public void publishTurnEvent(String name, Player player){
		for(int i=0; i<turnListeners.size();i++){
			turnListeners.get(i).onTurnEvent(this, name, player.getId());
		}
	}
	// Observer ends

	// Observer for roll three
	static ArrayList<ThreeListener> threeListeners = new ArrayList<ThreeListener>();

	public void addThreeListener(ThreeListener lis) {
		threeListeners.add(lis);
	}

	public void publishThreeEvent(String name, String value) {
		for(int i=0;i<threeListeners.size();i++) {
			threeListeners.get(i).onThreeEvent(this, name, value);
		}
	}
	// Observer ends

	public void rollThree() {
		
		List<Integer> rolledValues = new ArrayList<>();
		rolledValues.add(cup.r1.rollDie());
		rolledValues.add(cup.r2.rollDie());
		rolledValues.add(cup.r3.rollDie());
		Collections.sort(rolledValues);
	
		System.out.println("Roll3 roll: " + rolledValues.get(0) + "," + rolledValues.get(1) + "," + rolledValues.get(2));
		
		for(int i=0;i<players.length;i++) {
			List<Integer>  cardValues = players[i].getRollThreeCard().getDiceValues();
			System.out.println("Player " + players[i].ID + " has roll card of " + cardValues.get(0) + "," + cardValues.get(1) + "," + cardValues.get(2));
			cardValues.retainAll(rolledValues);
			
			int matchCount = cardValues.size();
			System.out.print("Matched number of dice: " + matchCount);
			
			int earnedAmount = 0;
			if(matchCount == 1) {
				earnedAmount = 50;
			} else if(matchCount == 2) {
				earnedAmount = 200;
			} else if(matchCount == 3 && currentPlayer.ID == players[i].ID) {
				earnedAmount = 1500;
			} else if(matchCount == 3) {
				earnedAmount = 1000;
			}
			System.out.println(" earned: " + earnedAmount);
			players[i].balance.increaseAmount(earnedAmount);
		}
	}

	public void updateCurrentPlayer() {
		i++;
		currentPlayer = players[i%players.length];
		if(i==players.length) {
			i=0;
		}
		publishTurnEvent("EndTurn", currentPlayer);
		System.out.println("Player updated!" + currentPlayer.ID);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}	

	/*
	 * Controller class methods : Facade Pattern
	 *  ---------------------------------------------------------
	 *  All the methods below return either String, int, double or an ArrayList of them. UI calling these methods have no idea about the domain logic.
	 *  UI doesn't know which data structures are used, how are things stored and managed. So even if we use another library or even different language,
	 *  the adjustments are very easy to do.
	 */

	public static ArrayList<String> getPlayerDeeds(int Id){
		ArrayList<String> deedNames = new ArrayList<>();
		for (Player p: players) {
			if (Id == p.ID) {
				for(int i=0; i<p.deeds.size(); i++) {
					deedNames.add(p.deeds.get(i).toString());
				}
				return deedNames;
			}
		}
		return null;
	}

	public static double getPlayerBalance(int Id){
		for (Player p: players) {
			if (Id == p.ID)
				return p.balance.bMoney.amount;
		}
		return 0;
	}

	public static ArrayList<String> getPlayerProperties(int Id){
		ArrayList<String> properties = new ArrayList<>();
		for (Player p: players) {
			if (Id == p.ID) {
				for(int i=0; i<p.properties.size(); i++) {
					properties.add(p.properties.get(i).toString());
				}
				return properties;
			}
		}
		return null;
	}

	public static ArrayList<String> getPlayerOwnedCards(int Id){
		ArrayList<String> cardNames = new ArrayList<>();
		for (Player p: players) {
			if (Id == p.ID) {
				for(int i=0; i<p.cardsOwned.size(); i++) {
					cardNames.add(p.cardsOwned.get(i).toString());
				}
				return cardNames;
			}
		}
		return null;
	}

	public static boolean isPlayerInJail(int Id){
		for (Player p: players) {
			if (Id == p.ID)
				return p.isInJail;
		}
		return false;
	}

	public int getPlayerDirection (int Id){
		for (Player p: players) {
			if (Id == p.ID)
				return p.direction;
		}
		return 0;
	}

	public static Piece getPiece(int Id){
		for (Player p: players) {
			if (Id == p.ID)
				return p.getPiece();
		}
		return null;
	}

	public static String buyDeed(int Id) {
		for (Player p: players) {
			if (Id == p.ID)
				return p.buyDeed();
		}
		return null;
	}

	public static String sellDeed(int Id) {
		for (Player p: players) {
			if (Id == p.ID)
				return p.sellDeed();
		}
		return null;
	}

	// Die details

	public static int rollDice() {
		//if (s.equals("RRS")) 
		return cup.rollDice(MonopolyGame.CupInputs.RRS);
		//return 0;
	}

	public static Cup getCup() {
		return cup;
	}

	public static String getPlayerIcon(int playerID) {
		return players[playerID].getPiece().getIcon();
	}

	public static int[] getPlayerCoordinates(int playerID) {
		int[] coordinates = new int[2];
		coordinates[0] = players[playerID-1].getPiece().getX();
		coordinates[1] = players[playerID-1].getPiece().getY();
		return coordinates;
	}

	public static int getCurrentPlayerID() {
		return currentPlayer.ID;
	}
	
	public static int[] getCurrentPlayerCoordinates() {
		return getPlayerCoordinates(currentPlayer.ID);
	}
}
