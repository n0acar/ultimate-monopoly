package kapitalMonopoly;

public class Pool {
	
	private Money pMoney;	
	
	public Pool() {
		pMoney = new Money();
	}
	
	public void setAmount(double amount){
		pMoney.setAmount(amount);
	}
	
	public void increaseAmount(double amount){
		pMoney.increaseAmount(amount);
	}
	
	public void decreaseAmount(double amount){
		pMoney.decreaseAmount(amount);
	}
	
	
}
