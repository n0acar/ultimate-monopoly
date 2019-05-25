package kapitalMonopoly;

public class DeedSquare extends Square{

	public final static String CARD_TYPE = "Deed";
	
	String color; 
	Double price;
	Double rent;
	Boolean isPurchasable;
	int[] buildingCount; // ex: 0 for hotel, buildingCount[0] means number of hotels

	public DeedSquare(String name, String color, Double price, String nextSquare, String prevSquare, int[] borders) {
		super(name, nextSquare, prevSquare, CARD_TYPE, borders);
		this.price = price;
		this.isPurchasable = true;
		this.color = color;
		
		buildingCount = new int[3];
		buildingCount[0]= 0;
		buildingCount[1]= 0;
		buildingCount[2]= 0;
		
	}

	public void payRent() {
		
	}

	public void changePurchasable(boolean purchase) {
		this.isPurchasable = purchase;
	}
	
	public void updateBuildingCount(Property property) {
		if(property.getIcon() == "House") {
			buildingCount[0]++;
		}
		else if(property.getIcon() == "Hotel") {
			buildingCount[1]++;
		}
		else if(property.getIcon() == "Skyscraper") {
			buildingCount[2]++;
		}
	}

}
