package kapitalMonopolyUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kapitalMonopoly.MonopolyGame; // ONLY DOMAIN CLASS : MonopolyGame working as a controller

import kapitalMonopolyObservers.RollListener;
import javax.swing.SwingConstants;

public class DiePanel implements RollListener{

	JPanel dieJPanel = new JPanel();
	static JButton btnRollDice;
	
	public static final int PANEL_WIDTH = 330;
	public static final int PANEL_HEIGHT = 200;
	
	final String DIE_ICON = "DiePictures/game-die_1.png";
	final String DIE_IMAGE_PATH = "DiePictures/die-face-";
	final String DIE_IMAGE_EXTENSION = ".png";
	final int BUS_ROLL = 4;
	final int MR_MONOPOLY_ROLL = 6;
	final String BUS_ROLL_PATH = "DiePictures/die-face-bus.png";
	final String MR_MONOPOLY_PATH = "DiePictures/die-face-mr.monopoly.png";


	final int[] ROLL_BUTTON = {140, 43, 116, 62};
	final int[] DIE_1_COORDINATES = {110, 134};
	final int[] DIE_2_COORDINATES = {172, 134};
	final int[] DIE_3_COORDINATES = {234, 134};
	final int[] DIE_SIZE = {50,50};
	
	String pathReg1 = "";
	String pathReg2 = "";
	String pathSpeed = "";

	JLabel dieLabel1 = new JLabel("");
	JLabel dieLabel2 = new JLabel("");
	JLabel dieLabel3 = new JLabel("");


	public DiePanel() {
		initialize();
	}

	public void initialize() {

		dieJPanel.setBounds(BoardPanel.BOARD_WIDTH + MonopolyGameFrame.BREAK_AMOUNT, PlayerPanel.PANEL_HEIGHT + CardPanel.PANEL_HEIGHT + MonopolyGameFrame.BREAK_AMOUNT, PANEL_WIDTH, PANEL_HEIGHT);
		dieJPanel.setLayout(null);

		MonopolyGame.getCup().addRollListener(this); // 

		btnRollDice = new JButton("Roll Dice");
		btnRollDice.setHorizontalAlignment(SwingConstants.TRAILING);
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonopolyGame.rollDice();
				PlayerPanel.sellDeedButton.setEnabled(true);
				PlayerPanel.buyDeedButton.setEnabled(true);
			}
		});
		btnRollDice.setBounds(ROLL_BUTTON[0], ROLL_BUTTON[1], ROLL_BUTTON[2], ROLL_BUTTON[3]);
		btnRollDice.setIcon(new ImageIcon(DIE_ICON));
		dieJPanel.add(btnRollDice);

		dieLabel1.setLayout(null);
		dieLabel1.setBounds(DIE_1_COORDINATES[0], DIE_1_COORDINATES[1], DIE_SIZE[0], DIE_SIZE[1]);
		dieLabel1.setIcon(new ImageIcon(getPath(1)));
		dieJPanel.add(dieLabel1);

		dieLabel2.setBounds(DIE_2_COORDINATES[0], DIE_2_COORDINATES[1], DIE_SIZE[0], DIE_SIZE[1]);
		dieLabel2.setIcon(new ImageIcon(getPath(2)));
		dieJPanel.add(dieLabel2);

		dieLabel3.setBounds(DIE_3_COORDINATES[0], DIE_3_COORDINATES[1], DIE_SIZE[0], DIE_SIZE[1]);
		dieLabel3.setIcon(new ImageIcon(getPath(3)));
		dieJPanel.add(dieLabel3);

	}

	@Override
	public void onRollEvent(Object source, String name, String value) {
		pathReg1 = getPath(Integer.parseInt("" + value.charAt(0)));
		pathReg2 = getPath(Integer.parseInt("" + value.charAt(1)));

		if(Integer.parseInt("" + value.charAt(2)) == BUS_ROLL) {
			pathSpeed = BUS_ROLL_PATH;
		} else if(Integer.parseInt("" + value.charAt(2)) == MR_MONOPOLY_ROLL) {
			pathSpeed = MR_MONOPOLY_PATH;
		} else {
			pathSpeed = getPath(Integer.parseInt("" + value.charAt(2)));
		}

		dieLabel1.setIcon(new ImageIcon(pathReg1));
		dieLabel2.setIcon(new ImageIcon(pathReg2));
		dieLabel3.setIcon(new ImageIcon(pathSpeed));
		
		if(Integer.parseInt("" + value.charAt(0)) != Integer.parseInt("" + value.charAt(1))) {
			btnRollDice.setEnabled(false);
			PlayerPanel.endTurnButton.setVisible(true);
		}
	}

	public String getPath(int number){
		return DIE_IMAGE_PATH + number + DIE_IMAGE_EXTENSION;
	}
	
	

}
