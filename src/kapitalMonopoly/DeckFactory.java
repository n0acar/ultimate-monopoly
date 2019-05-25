package kapitalMonopoly;

public class DeckFactory {

	public static DeckInterface getDeck(String deck)
	{
		if (deck.equals("Chance Deck"))
			return new ChanceDeck();
		else if (deck.equals("Community Chest Deck"))
			return new CommunityChestDeck();
		else if (deck.equals("Roll Three Deck"))
			return new RollThreeDeck();
		return null;
	}
}
