package kapitalMonopoly;

import java.util.ArrayList;

public class RollThreeDeck extends Deck<RollThreeCard> implements DeckInterface {

	private static final String CARD_NAMES_FILE = "RollThreeCardNames.txt";
	
	public Deck<RollThreeCard> rollThreeDeck = new Deck<>();
	ArrayList<String> cardNames;

	int cardCount;
	
	public RollThreeDeck() {
		cardNames = readCardFile(CARD_NAMES_FILE);
		cardCount = cardNames.size();
		for(int i=0;i<cardCount;i++) {
			System.out.println(i);
			RollThreeCard rollThreeCard = new RollThreeCard(i, cardNames.get(i));
			rollThreeDeck.putUnder(rollThreeCard);
		}
		rollThreeDeck.shuffleDeck();
	}

	@SuppressWarnings("static-access")
	public RollThreeCard drawCard() {
		RollThreeCard temp = rollThreeDeck.drawCard("Roll Three Deck");
		publishDrawEvent("DrawCard", temp.cardName);
		MonopolyGame.getInstance().currentPlayer.cardsOwned.add(temp);
		return temp;
	}
	

	public void putUnder(RollThreeCard rollThreeCard) {
		rollThreeDeck.putUnder(rollThreeCard);
	}

	@Override
	public Card fDraw() {
		RollThreeCard temp = rollThreeDeck.drawCard("Roll Three Deck");
		// TODO Auto-generated method stub
		return temp;
	}
	
}
