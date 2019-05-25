package kapitalMonopoly;

import java.util.ArrayList;

import kapitalMonopoly.MonopolyGame.CupInputs;
import kapitalMonopolyObservers.MoveListener;
import kapitalMonopolyObservers.OrderListener;
import kapitalMonopolyObservers.RollListener;

public class Cup {

	//Observer pattern starts here:
	static ArrayList<RollListener> rollListeners = new ArrayList<RollListener>();
	static ArrayList<MoveListener> moveListeners = new ArrayList<MoveListener>();
	static ArrayList<OrderListener> orderListeners = new ArrayList<OrderListener>();
	
	public void addRollListener(RollListener lis) {
		rollListeners.add(lis);
	}
	
	public void addOrderListener(OrderListener lis) {
		orderListeners.add(lis);
	}
	
	public void addMoveListener(MoveListener lis) {
		moveListeners.add(lis);
	}
	
	public void publishPropertyEvent(String name, String value){
		for(int i=0; i<rollListeners.size();i++){
			rollListeners.get(i).onRollEvent(this, name, value);
		}
	}
	public void publishPropertyEvent2(String name, String value){
			moveListeners.get(MonopolyGame.getInstance().i).onMoveEvent(this, name, value);
			moveListeners.get(8).onMoveEvent(this, name, value);
	}
	
	public void publishPropertyEvent3(String name, String value){
		for(int i=0; i<orderListeners.size();i++){
			orderListeners.get(i).onOrderEvent(this, name, value);
		}
	}
	//Observer pattern finished
	
	RegularDie r1;
	RegularDie r2;
	RegularDie r3;

	SpeedDie s1;

	public Cup() {
		r1 = new RegularDie();
		r2 = new RegularDie();
		r3 = new RegularDie();

		s1 = new SpeedDie();
	}

	public int rollDice(CupInputs cupInput) {
		int total = 0;
		int reg1 = 0;
		int reg2 = 0;
		int reg3 = 0;
		int speed = 0;
		
		switch(cupInput){
		case RRS: 
			reg1 = r1.rollDie();
			reg2 = r2.rollDie();
			speed = s1.rollDie();
			total = reg1 + reg2 + speed;
			publishPropertyEvent("RollDice", 
					Integer.toString(reg1)+Integer.toString(reg2)+Integer.toString(speed));
			
			break; 
		case RRR: //Called by Roll Three 
			reg1 = r1.rollDie();
			reg2 = r2.rollDie();
			reg3 = r3.rollDie();
			total = reg1 + reg2 + reg3;
			break; 
		case RR: 
			total = r1.rollDie() + r2.rollDie();
			break; 
		case R: 
			reg1 = r1.rollDie();
			publishPropertyEvent3("OrderPlayers", 
					Integer.toString(reg1));
			break; 	
		}
		
		publishPropertyEvent2("MovePlayer", 
				Integer.toString(reg1)+Integer.toString(reg2)+Integer.toString(speed));
		
		
		return total;
		
		
	}

	


}
