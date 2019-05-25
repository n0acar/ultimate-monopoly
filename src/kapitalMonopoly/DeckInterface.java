package kapitalMonopoly;

import kapitalMonopolyObservers.DrawListener;

public interface DeckInterface {

	Card drawCard();
	Card fDraw();
	void addDrawListener(DrawListener lis);
	void publishDrawEvent(String name, String value);
}
