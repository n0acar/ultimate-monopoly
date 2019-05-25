package kapitalMonopoly;

public abstract class Card {

	boolean cardSavable;
	String cardName;

	public Card(String cardName) {
		this.cardName = cardName;
		cardSavable = false;
	}

	public Card(String cardName, boolean cardSavable) {
		this.cardName = cardName;
		this.cardSavable = cardSavable;
	}

	public abstract void doAction();

	@Override
	public String toString(){
		return cardName;
	}
	
	public boolean isSavable() {
		return cardSavable;
	}

	public boolean isRollThreeCard() {
		return cardName.contains(RollThreeCard.CARD_FILES_PATH);
	}
	
	public boolean isChanceCard() {
		return cardName.contains(ChanceCard.CARD_FILES_PATH);
	}
	
	public boolean isCommunityChestCard() {
		return cardName.contains(CommunityChestCard.CARD_FILES_PATH);
	}
}
