package kapitalMonopoly;

import java.util.ArrayList;

public class ChanceDeck extends Deck<ChanceCard> implements DeckInterface {

	private static final String CHANCE_CARD_NAMES_FILE = "ChanceCardNames.txt";
	
	Deck<ChanceCard> chanceDeck = new Deck<>();
	ArrayList<String> cardNames;
	
	int cardCount;
	
	public ChanceDeck() {
		cardNames = readCardFile(CHANCE_CARD_NAMES_FILE);
		cardCount = cardNames.size();
		for(int i=0;i<cardCount;i++) {
			ChanceCard chanceCard = new ChanceCard(i, cardNames.get(i));
			chanceDeck.putUnder(chanceCard);
		}
		chanceDeck.shuffleDeck();
	}
	
	
	public ChanceCard drawCard() {
		ChanceCard temp = chanceDeck.drawCard("Chance");
		publishDrawEvent("DrawCard", temp.cardName);
		return temp;
	}
	
	
	public void putUnder(ChanceCard chanceCard) {
		chanceDeck.putUnder(chanceCard);
	}


	@Override
	public Card fDraw() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
