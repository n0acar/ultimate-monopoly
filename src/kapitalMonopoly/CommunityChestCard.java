package kapitalMonopoly;

public class CommunityChestCard extends Card{

	public static final String CARD_FILES_PATH  = "CommunityChestCards/";
	private static final String CARD_FILE_EXTENSION  = ".png";
	
	int cardNumber;
	
	public CommunityChestCard(int cardNumber, String cardPath) {
		super(CARD_FILES_PATH + cardPath +  CARD_FILE_EXTENSION);
		this.cardNumber = cardNumber;
	}

	@SuppressWarnings("static-access")
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		switch(cardNumber) {
		case 0:
			MonopolyGame.getInstance().players[MonopolyGame.getInstance().currentPlayer.ID-1].balance.decreaseAmount(100);
			MonopolyGame.getInstance().pool.increaseAmount(100);
			System.out.println(MonopolyGame.getInstance().currentPlayer.balance.bMoney.amount);
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
		default:
			break;
		}
	}
}
