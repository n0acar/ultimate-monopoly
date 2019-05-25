package kapitalMonopoly;

public class Balance {
	
	private static final int INITIAL_BALANCE = 3200;
	
	Money bMoney;

	public Balance() {
		bMoney=new Money();
		bMoney.setAmount(INITIAL_BALANCE);
	}
	
	public void setAmount(double amount) {
		bMoney.setAmount(amount);
	}
	
	public void increaseAmount(double amount) {
		bMoney.increaseAmount(amount);
	}
	
	public void decreaseAmount(double amount) {
		bMoney.decreaseAmount(amount);
	}

	@Override
	public String toString() {
		return "Player's Balance =" + bMoney.amount;
	}
	
	
	
}
