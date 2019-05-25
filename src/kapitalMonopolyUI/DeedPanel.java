package kapitalMonopolyUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DeedPanel {

	public static final int PANEL_WIDTH = 330;
	public static final int PANEL_HEIGHT = 300;
	
	static final int[] DEED_COORDINATES = {0, 0};
	static final int[] DEED_IMAGE_SIZE = {270, 300};
	
	JPanel deedJPanel = new JPanel();
	JLabel deedLabel = new JLabel();
	
	/**
	 * Create the panel.
	 */
	public DeedPanel() {
		initialize();
	}
	
	private void initialize() {
		
		deedJPanel.setBounds(BoardPanel.BOARD_WIDTH + DiePanel.PANEL_WIDTH + MonopolyGameFrame.BREAK_AMOUNT, PlayerPanel.PANEL_HEIGHT + MonopolyGameFrame.BREAK_AMOUNT, PANEL_WIDTH, PANEL_HEIGHT);
		deedJPanel.setLayout(null);
		
		deedLabel.setBounds(DEED_COORDINATES[0], DEED_COORDINATES[1], DEED_IMAGE_SIZE[0], DEED_IMAGE_SIZE[1]);
		deedJPanel.add(deedLabel);
		
		deedLabel.setIcon(new ImageIcon("DeedCardPics/Atlantic Avenue.png")); // Example
		
	}

}
