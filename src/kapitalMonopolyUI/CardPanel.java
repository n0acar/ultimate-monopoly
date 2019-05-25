package kapitalMonopolyUI;

import javax.swing.JPanel;

import kapitalMonopoly.MonopolyGame; // ONLY DOMAIN CLASS : MonopolyGame working as a controller

import kapitalMonopolyObservers.DrawListener;

import javax.swing.JLabel;

import javax.swing.ImageIcon;

public class CardPanel implements DrawListener{

	/**
	 * Create the panel.
	 */
	static final int PANEL_WIDTH = 360;
	static final int PANEL_HEIGHT = 200;
	static final int[] CARD_COORDINATES = {56, 6};
	static final int[] CARD_IMAGE_SIZE = {300,180};
	
	JPanel cardJPanel;
	static JLabel cardImage;
	
	public CardPanel() {
		initialize();
		
	}

	private void initialize() {
		
		MonopolyGame.getInstance().chanceDeck.addDrawListener(this);
		
		cardJPanel = new JPanel();
		cardJPanel.setBounds(BoardPanel.BOARD_WIDTH + MonopolyGameFrame.BREAK_AMOUNT, PlayerPanel.PANEL_HEIGHT +  MonopolyGameFrame.BREAK_AMOUNT, PANEL_WIDTH, PANEL_HEIGHT);		
		cardJPanel.setLayout(null);
		
		cardImage = new JLabel("");

		cardImage.setBounds(CARD_COORDINATES[0], CARD_COORDINATES[1], CARD_IMAGE_SIZE[0], CARD_IMAGE_SIZE[1]);
		cardJPanel.add(cardImage);
	}

	@Override
	public void onDrawEvent(Object source, String name, String value) {
		cardImage.setVisible(true);
		cardImage.setIcon(new ImageIcon(value));
	}
}
