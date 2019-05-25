package kapitalMonopoly;

import java.util.Random;

public class SpeedDie implements Die {
	
	int faceValue;
	
	public SpeedDie() {

	}
	
	@Override
	public int rollDie() {
		Random rand = new Random();
		faceValue = rand.nextInt(6) + 1;
		return faceValue;
	}
}
