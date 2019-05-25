package kapitalMonopolyObservers;

public interface OrderListener {

	void onOrderEvent(Object source, String name, String value);
	
}
