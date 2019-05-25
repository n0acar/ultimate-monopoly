package kapitalMonopoly;

import kapitalMonopolyObservers.MoveListener;

public class Piece implements MoveListener{
	
	private String icon;
	private Square position;
	private int playerID;
	
	
	@SuppressWarnings("static-access")
	public Piece(String icon, int playerID) {
		this.playerID = playerID;
		position = MonopolyGame.getInstance().gameBoard.getSquare("Go");
		this.icon=icon;
		MonopolyGame.getInstance().cup.addMoveListener(this);
		
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Square getPosition() {
		return position;
	}
	
	public String getPositionName() {
		return position.getName();
	}
	
	public int getX() {
		return position.getCoordinates(playerID)[0];
	}
	
	public int getY() {
		return position.getCoordinates(playerID)[1];
	}
	
	public void setPosition(Square position) {
		this.position = position;
	}

	@SuppressWarnings("static-access")
	@Override
	public void onMoveEvent(Object source, String name, String value) {
		System.out.print("BEFORE=> " + position.getName());
		Square temp = MonopolyGame.currentPlayer.getPiece().getPosition();
		for(int i=0;i<Integer.parseInt(""+value.charAt(0))+Integer.parseInt(""+value.charAt(1))+Integer.parseInt(""+value.charAt(2));i++) {
			temp = MonopolyGame.getInstance().gameBoard.getNextSquare(temp);
		}
		MonopolyGame.currentPlayer.piece.position=temp;
		System.out.println(", AFTER=> "+position.getName());
		//System.out.println("action square:" + MonopolyGame.currentPlayer.piece.getPosition().isActionSquare());
		if(MonopolyGame.currentPlayer.piece.getPosition().isActionSquare()) {
			((ActionSquare)MonopolyGame.currentPlayer.piece.getPosition()).doAction();
		}
		
	}

}
