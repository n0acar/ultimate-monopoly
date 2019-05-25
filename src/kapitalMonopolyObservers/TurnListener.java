package kapitalMonopolyObservers;


public interface TurnListener {

	void onTurnEvent(Object source, String name, int playerId);
}
