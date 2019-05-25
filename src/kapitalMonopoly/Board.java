package kapitalMonopoly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;


public class Board {

	private final static int TOKEN_COUNT_FOR_DEEDS = 5;
	private final static String TOKEN_SEPARATOR = "-";
	private final static String SQUARE_BORDERS_FILE = "SquarePositions.txt";
	private final static String SQUARES_LIST_FILE = "SquaresList.txt";
	
	HashMap<String,Square> squareMap = new HashMap<>(); // Square Name -> Square
	
	public Board() {
		HashMap<String, int[]> squareBorders = readSquareBorders();
		readSquareFile(squareBorders);
	}
	
	private HashMap<String, int[]> readSquareBorders(){
		
		HashMap<String, int[]> squareBorders = new HashMap<>(); // Square Name -> Square Coordinates (x1,y1,x2,y2)

		try {
			FileReader file = new FileReader(SQUARE_BORDERS_FILE); 
			BufferedReader br = new BufferedReader(file);
			String line;
			while( (line=br.readLine()) != null ){
				String[] tokens = line.split(TOKEN_SEPARATOR);
				String name = tokens[0].trim();
				int[] borders = new int[4]; // Format: (x1,y1,x2,y2)
				for(int i=0; i<4; i++) {
					borders[i] = Integer.parseInt(tokens[i+1].trim());
				}
				squareBorders.put(name, borders);
			}
			file.close();
			br.close();
		} catch (IOException e) {
			System.out.println("The file is not a valid format for the reader!");
		}
		return squareBorders;
	}
	

	private void readSquareFile(HashMap<String, int[]> squareBorders) {
		try {
			FileReader file = new FileReader(SQUARES_LIST_FILE); 
			//feed the FileReader into a BufferedReader to read line by line
			BufferedReader br = new BufferedReader(file);

			String line;
			
			//reads file until there are no more lines, using a null check
			while( (line=br.readLine()) != null ){
				String[] tokens = line.split(TOKEN_SEPARATOR);
				// Format: Square's Name - NextSquare's Name - PreviousSquare's Name
				String name = tokens[0].trim();
				String nextName = tokens[1].trim();
				String prevName = tokens[2].trim();
				
				int[] borders;
				if(squareBorders.containsKey(name)) {
					borders = squareBorders.get(name);
				} else {
					System.out.println("Cannot find borders for SQUARE: " + name);
					borders = new int[4];
				}
				
				if(tokens.length < TOKEN_COUNT_FOR_DEEDS) {
					//System.out.println(name + "ACTION SQUARE");
					squareMap.put(name, new ActionSquare(name, nextName, prevName, borders));
				} else {
					//System.out.println(name + "DEED SQUARE");
					Double price = Double.parseDouble(tokens[3].trim());
					System.out.println(price.toString());
					String color = tokens[4].trim();
					squareMap.put(name, new DeedSquare(name, color, price , nextName, prevName, borders));
				}
			
			}
			file.close();
			br.close();
		}
		catch (IOException e) {
			System.out.println("The file is not a valid format for the reader!");

		}
	}
	
	public Square getSquare(String squareName) {
		return squareMap.get(squareName);
	}
	
	public Square getNextSquare(String squareName) { // Query with a square's name
		return getNextSquare(squareMap.get(squareName));
	}
	
	public Square getNextSquare(Square currentSquare) {  // Query with a square
		return this.getSquare(currentSquare.getNextSquareName());
	}
	
	public Square getPrevSquare(String squareName) { // Query with a square's name
		return getPrevSquare(squareMap.get(squareName));
	}
	
	public Square getPrevSquare(Square currentSquare) { // Query with a square
		return this.getSquare(currentSquare.getPrevSquareName());
	}
}
