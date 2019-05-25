package kapitalMonopolyUI;
import javax.swing.JPanel;

import kapitalMonopoly.MonopolyGame; // ONLY DOMAIN CLASS : MonopolyGame working as a controller

import kapitalMonopolyObservers.MoveListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BoardPanel implements MoveListener{
	
	public static final int BOARD_WIDTH = 650;
	public static final int BOARD_HEIGHT = 650;
	final int[] BOARD_COORDINATES = {25 , 25};
	final String BOARD_IMAGE_PATH = "BoardPic/board_with_portals.png";
	
	
	JPanel boardJPanel;
	JLabel boardLabel;
	static JLabel[] pieceLabel = new JLabel[8];
	int[] iconSize = {MonopolyGame.ICON_SIZE_X, MonopolyGame.ICON_SIZE_Y};
			
	/**
	 * Create the panel.
	 */
	public BoardPanel() {
		initialize();
	}

	private void initialize() {

		MonopolyGame.getCup().addMoveListener(this);
		
		boardJPanel = new JPanel();
		boardJPanel.setBounds(BOARD_COORDINATES[0], BOARD_COORDINATES[1], BOARD_WIDTH, BOARD_HEIGHT);		
		boardJPanel.setLayout(null);
		
		boardLabel = new JLabel("");
		boardLabel.setIcon(new ImageIcon("BoardPic/board_with_portals.png"));
		boardLabel.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		boardJPanel.add(boardLabel);
		
		for(int i=0;i<pieceLabel.length;i++) {
			pieceLabel[i] = new JLabel("");
			pieceLabel[i].setIcon(new ImageIcon(MonopolyGame.getPlayerIcon(i)));
			int[] coordinates = MonopolyGame.getPlayerCoordinates(i+1);
			pieceLabel[i].setBounds(coordinates[0], coordinates[1], iconSize[0], iconSize[1]);
			boardLabel.add(pieceLabel[i]);
		}
	}
	
	@Override
	public void onMoveEvent(Object source, String name, String value) {
		// UI doesn't know how Domain logic works, so contacts the controller only to obtain the required information
		int[] coordinates = MonopolyGame.getCurrentPlayerCoordinates();
		BoardPanel.pieceLabel[MonopolyGame.getCurrentPlayerID()-1].setBounds(coordinates[0], coordinates[1], iconSize[0], iconSize[1]);
		
	}

}
