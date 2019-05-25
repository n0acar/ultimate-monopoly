package kapitalMonopoly;

import java.util.ArrayList;

public class Player {

	public ArrayList<DeedSquare> deeds;
	boolean isInJail;
	int direction;
	public int ID;
	Piece piece;
	public Balance balance;
	ArrayList<Property> properties;
	public ArrayList<Card> cardsOwned;

	public Player(String icon, int ID) {
		this.ID = ID;
		deeds = new ArrayList<>();
		isInJail = false;
		direction = 1;
		balance = new Balance();
		piece = new Piece(icon, ID);

		cardsOwned=new ArrayList<>();
		cardsOwned.add(MonopolyGame.getInstance().rollThreeDeck.fDraw());
		System.out.println("Player created. ");
	}


	public String buyProperty(Property property) { // must return string
		if(balance.bMoney.amount >= property.getPrice()) {
			balance.decreaseAmount(property.getPrice());
			properties.add(property);
			return "Successfully bought";
		}else
			return "Not enough money you have";

	}

	public String sellProperty(Property property) { //must return string
		if(properties.contains(property)) {
			balance.increaseAmount(property.getPrice());
			properties.remove(property);
			return "";
		}else
			return "No property you have";
	}

	public void putProperty(DeedSquare square, Property property) {
		if(properties.contains(property) && deeds.contains(square)) {
			square.updateBuildingCount(property);
			properties.remove(property);
		}
	}

	public String buyDeed() {  //must return string
		System.out.println("Applied for purchase of:" + piece.getPosition().type + ", is it a deed:" + piece.getPosition());
		if(piece.getPosition().isDeedSquare()) {
			DeedSquare deedSquare = (DeedSquare) piece.getPosition();
			if(!MonopolyGame.getInstance().deedOwners.containsKey(deedSquare) && balance.bMoney.amount >= deedSquare.price) {
				balance.decreaseAmount(deedSquare.price);
				MonopolyGame.getInstance().deedOwners.put(deedSquare, this);
				deeds.add(deedSquare);
				return "Deed Successfully bought";
			}else {
				return "This square cannot be bought";
			}
		}
		return  "Cannot be bought, not even a Deed Square";
	}

	public String sellDeed() {  //must return string
		if(piece.getPosition().isDeedSquare()) {
			DeedSquare deedSquare = (DeedSquare) piece.getPosition();
			if(deeds.contains(deedSquare)) {
				deeds.remove(deedSquare);
				balance.increaseAmount(deedSquare.price);
				MonopolyGame.getInstance().deedOwners.remove(deedSquare);
				return "Successfully sold";
			}else
				return "Deed could not be sold";
		}
		return "Cannot be sold, not even a Deed Square";
	}

	public void mortgageDeed(DeedSquare square) { //must return string but will be implemented later.
		if(deeds.contains(square) && isInJail==false) {
			balance.increaseAmount(square.price/2);
		}else
			System.out.println("You can't mortgage that");
	}

	public Piece getPiece() {
		return piece;
	}
	
	public String getPositionName() {
		return piece.getPositionName();
	}
	
	public Square getPosition() {
		return piece.getPosition();
	}
	
	public int getId() {
		return ID;
	}
	
	public RollThreeCard getRollThreeCard() {
		for(int i=cardsOwned.size(); i>0; i--) {
			if(cardsOwned.get(i-1).isRollThreeCard()) {
				return (RollThreeCard) cardsOwned.get(i-1);
			}
		}
		return null;
	}
	
}
