package kapitalMonopoly;

public class ChanceCard extends Card{
	
	public static final String CARD_FILES_PATH  = "ChanceCards/";
	private static final String CARD_FILE_EXTENSION  = ".jpg";
	
	int cardNumber;
	
	public ChanceCard(int cardNumber, String cardName) {
		super(CARD_FILES_PATH + cardName + CARD_FILE_EXTENSION);
		this.cardNumber = cardNumber;
	}

	
	@SuppressWarnings("static-access")
	@Override
	public void doAction() {
		switch(cardNumber) {
		case 0: 
			MonopolyGame.getInstance().currentPlayer.balance.increaseAmount(100);
			System.out.println(MonopolyGame.getInstance().currentPlayer.balance.toString());
			break;
		case 1:
			break;	
		case 2:
			break;
		case 3:
			break;	
		case 4:
			break;
		case 5:
			break;	
		case 6:
			break;
		case 7:
			break;	
		case 8:
			break;
		case 9:
			break;	
		case 10:
			break;
		case 11:
			break;	
		case 12:
			break;	
		case 13:
			break;
		case 14:
			break;	
		case 15:
			break;	
		case 16:
			break;
		case 17:
			break;	
		case 18:
			break;	
		case 19:
			break;	
		case 20:
			break;	
		case 21:
			break;	
		case 22:
			break;	
		default:
			break;
		}
	}

}
