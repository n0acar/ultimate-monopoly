package kapitalMonopoly;

public class ActionSquare extends Square{

	public final static String CARD_TYPE = "Action";
	
	
	public ActionSquare(String name, String nextSquare, String prevSquare, int[] borders) {
		super(name, nextSquare, prevSquare, CARD_TYPE, borders);
	}


	@SuppressWarnings({ "static-access"})
	public void doAction() {
		switch(MonopolyGame.getInstance().currentPlayer.getPositionName()) {
		case "Chance 1":
			MonopolyGame.getInstance().chanceDeck.drawCard();
			break;
		case "Chance 2":
			MonopolyGame.getInstance().chanceDeck.drawCard();
			break;
		case "Chance 3":
			MonopolyGame.getInstance().chanceDeck.drawCard();
			break;
		case "Community Chest 1":
			MonopolyGame.getInstance().communityChestDeck.drawCard();
			break;
		case "Community Chest 2":
			MonopolyGame.getInstance().communityChestDeck.drawCard();
			break;
		case "Community Chest 3":
			MonopolyGame.getInstance().communityChestDeck.drawCard();
			break;
		case "Roll Three":
			MonopolyGame.getInstance().rollThreeDeck.drawCard();
			MonopolyGame.getInstance().rollThree();
			break;
		}
	}
}
