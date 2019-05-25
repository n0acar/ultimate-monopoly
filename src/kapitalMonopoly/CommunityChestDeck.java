package kapitalMonopoly;

import java.util.ArrayList;

public class CommunityChestDeck extends Deck<CommunityChestCard> implements DeckInterface {
	
	private static final String CARD_NAMES_FILE = "CommunityChestCardNames.txt";
	
	Deck<CommunityChestCard> communityChestDeck = new Deck<>();
	ArrayList<String> cardNames;

	int cardCount;
	
	public CommunityChestDeck() {
		cardNames = readCardFile(CARD_NAMES_FILE);
		cardCount = cardNames.size();
		for(int i=0;i<cardCount;i++) {
			CommunityChestCard communityChestCard = new CommunityChestCard(i,cardNames.get(i));
			communityChestDeck.putUnder(communityChestCard);
		}
		communityChestDeck.shuffleDeck();
	}
	
	public CommunityChestCard drawCard() {
		CommunityChestCard temp = communityChestDeck.drawCard("Community Chest");
		publishDrawEvent("DrawCard", temp.cardName);
		return temp;
	}
	
	public void putUnder(CommunityChestCard communityChestCard) {
		communityChestDeck.putUnder(communityChestCard);
	}

	@Override
	public Card fDraw() {
		// TODO Auto-generated method stub
		return null;
	}
}
