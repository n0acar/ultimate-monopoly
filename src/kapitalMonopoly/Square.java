package kapitalMonopoly;

public class Square { 
 
	private final int VERTICAL_TOLERANCE = 5;
	private final int SQUARE_TOLERANCE = 20;
	private final double NEIGHBORING_LIMIT = 3.0;
	
	private String name; 
	private String nextSquare; 
	private String prevSquare; 
	public String type; 
	private int[][] playerLocation;  
	 
	public Square(String name, String nextSquare, String prevSquare, String type, int[] borders) { 
		this.name = name; 
		this.nextSquare = nextSquare; 
		this.prevSquare = prevSquare; 
		this.type = type; 
		setPlayerPositions(borders); 
	} 
	 
	
	private void setPlayerPositions(int[] borders) {
		@SuppressWarnings("static-access")
		int playerCount = MonopolyGame.getInstance().players.length;
		 
		int minX = borders[0]; 
		int minY = borders[1]; 
		int maxX = borders[2] - MonopolyGame.ICON_SIZE_X;
		int maxY = borders[3] - MonopolyGame.ICON_SIZE_Y;
		 
		int xRange = maxX - minX; 
		int yRange = maxY - minY; 
		 
		playerLocation = new int[playerCount][2]; 
		
		if(xRange > yRange + VERTICAL_TOLERANCE) { // Horizontal Square
			
			int diffX = (int) Math.ceil(xRange / NEIGHBORING_LIMIT); 
			for(int i = 0; i<playerCount; i++) {
				playerLocation[i][0] = minX + diffX * (i/2);
				if(i % 2 == 0) {
					playerLocation[i][1] = minY; 
				} else {
					playerLocation[i][1] = maxY; 
				}
			} 
		} else { // Vertical Square
			
			if(xRange > SQUARE_TOLERANCE) { // Square shaped Square
				minX += MonopolyGame.ICON_SIZE_X / 2;
				maxX -= MonopolyGame.ICON_SIZE_X / 2;
			}
		
			int diffY = (int) Math.ceil(yRange / NEIGHBORING_LIMIT); 
			for(int i = 0; i<playerCount;i++) { 
				if(i % 2 == 0) {
					playerLocation[i][0] = minX; 
				} else {
					playerLocation[i][0] = maxX; 
				}
				playerLocation[i][1] = minY + diffY * (i/2);
			} 
		} 
 
	} 
	 
	public int[] getCoordinates(int pieceID) { 
		return playerLocation[pieceID-1]; 
	} 
	 
	public String getName() { 
		return name; 
	} 
	 
	public String getNextSquareName() { 
		return nextSquare; 
	} 
	 
	public String getPrevSquareName() { 
		return prevSquare; 
	}	 
	 
	public boolean isDeedSquare() { 
		return type == DeedSquare.CARD_TYPE;
	} 
	 
	public boolean isActionSquare() { 
		return type == ActionSquare.CARD_TYPE; 
	} 
	 
} 
