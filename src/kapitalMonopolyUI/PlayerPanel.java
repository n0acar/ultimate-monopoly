package kapitalMonopolyUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kapitalMonopoly.MonopolyGame;
import kapitalMonopolyObservers.DrawListener;
//import kapitalMonopoly.Player;
import kapitalMonopolyObservers.TurnListener;

public class PlayerPanel implements TurnListener, DrawListener{
	
	public static final int PANEL_WIDTH = 675;
	public static final int PANEL_HEIGHT = 225;

	final int[] BUY_BUTTON = {160, 56, 100, 29};
	final int[] SELL_BUTTON = {260, 56, 100, 29};
	final int[] END_BUTTON = {360, 56, 100, 29};
	final int[] PLAYER_LABEL = {12, 13, 131, 67};
	final int[] DEED_LABEL = {12, 80, 231, 67};
	final int[] BALANCE_LABEL = {12, 92, 331, 67};
	final int[] DEED_INFO_LABEL = {160, 85, 300, 29};
	
	JPanel playerJPanel = new JPanel();
	JLabel playerLabel;
	JLabel playerBalanceLabel;
	JLabel playerDeedLabel;
	static JButton buyDeedButton;
	static JButton sellDeedButton;
	static JButton endTurnButton;
	JLabel deedInformationLabel;
	
	
	public PlayerPanel() {
		initialize();
	}

	private void initialize() {
		int firstPlayerID = MonopolyGame.getCurrentPlayerID();
		
		playerJPanel.setBounds(BoardPanel.BOARD_WIDTH + MonopolyGameFrame.BREAK_AMOUNT, MonopolyGameFrame.BREAK_AMOUNT, PANEL_WIDTH, PANEL_HEIGHT);		
		playerJPanel.setLayout(null);
		
		MonopolyGame.getInstance().addTurnListener(this);
		MonopolyGame.getInstance().chanceDeck.addDrawListener(this);
		
		playerLabel = new JLabel("Player 1's Panel");
		deedInformationLabel = new JLabel("Information Label");
		playerBalanceLabel = new JLabel("Balance: " + MonopolyGame.getPlayerBalance(firstPlayerID));
		playerDeedLabel = new JLabel("Deeds: \n" + MonopolyGame.getPlayerDeeds(firstPlayerID).size());
		
		buyDeedButton = new JButton("Buy Deed");
		buyDeedButton.setBounds(BUY_BUTTON[0], BUY_BUTTON[1], BUY_BUTTON[2], BUY_BUTTON[3]);
		
		buyDeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int currentPlayerId = MonopolyGame.getCurrentPlayerID();
					deedInformationLabel.setText(MonopolyGame.buyDeed(currentPlayerId));
					playerBalanceLabel.setText("Balance: "+ MonopolyGame.getPlayerBalance(currentPlayerId));
					playerDeedLabel.setText("Deeds: \n"+MonopolyGame.getPlayerDeeds(currentPlayerId).size());
					sellDeedButton.setEnabled(false);
					buyDeedButton.setEnabled(false);
				}catch (Error e1){
					deedInformationLabel.setText("something is wrong");
				}
			}
		});
		
		endTurnButton = new JButton("End Turn");
		endTurnButton.setBounds(END_BUTTON[0], END_BUTTON[1], END_BUTTON[2], END_BUTTON[3]);
		
		endTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiePanel.btnRollDice.setEnabled(true);
				endTurnButton.setVisible(false);
				deedInformationLabel.setText("");
				MonopolyGame.getInstance().updateCurrentPlayer();
				//int currentPlayerId = MonopolyGame.getCurrentPlayer().getId();
				//playerBalanceLabel.setText("Balance: "+ MonopolyGame.getPlayerBalance(currentPlayerId));
				//playerDeedLabel.setText("Deeds: \n"+MonopolyGame.getPlayerDeeds(currentPlayerId).size());
				CardPanel.cardImage.setVisible(false);
				sellDeedButton.setEnabled(true);
				buyDeedButton.setEnabled(true);
			}
		});
		
		endTurnButton.setVisible(false);
		
		sellDeedButton = new JButton("Sell Deed");
		sellDeedButton.setBounds(SELL_BUTTON[0], SELL_BUTTON[1], SELL_BUTTON[2], SELL_BUTTON[3]);
		sellDeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentPlayerId = MonopolyGame.getCurrentPlayerID();
				deedInformationLabel.setText(MonopolyGame.sellDeed(currentPlayerId));
				playerDeedLabel.setText("Deeds: \n"+MonopolyGame.getPlayerDeeds(currentPlayerId).size());
				playerBalanceLabel.setText("Balance: "+ MonopolyGame.getPlayerBalance(currentPlayerId));
				sellDeedButton.setEnabled(false);
				buyDeedButton.setEnabled(false);
			}
			
		});

		playerLabel.setBounds(PLAYER_LABEL[0], PLAYER_LABEL[1], PLAYER_LABEL[2], PLAYER_LABEL[3]);
		playerDeedLabel.setBounds(DEED_LABEL[0], DEED_LABEL[1], DEED_LABEL[2], DEED_LABEL[3]);
		playerBalanceLabel.setBounds(BALANCE_LABEL[0], BALANCE_LABEL[1], BALANCE_LABEL[2], BALANCE_LABEL[3]);
		deedInformationLabel.setBounds(DEED_INFO_LABEL[0], DEED_INFO_LABEL[1], DEED_INFO_LABEL[2], DEED_INFO_LABEL[3]);
		
		playerJPanel.add(playerLabel);
		playerJPanel.add(buyDeedButton);
		playerJPanel.add(sellDeedButton);
		playerJPanel.add(deedInformationLabel);
		playerJPanel.add(playerDeedLabel);
		playerJPanel.add(endTurnButton);
		playerJPanel.add(playerBalanceLabel);

	}

	@Override
	public void onTurnEvent(Object source, String name, int playerId) {
		playerLabel.setText("Player " + playerId + "'s Label");
		playerBalanceLabel.setText("Balance: "+ MonopolyGame.getPlayerBalance(playerId));
		playerDeedLabel.setText("Deeds: \n"+ MonopolyGame.getPlayerDeeds(playerId).size());
		
	}

	@Override
	public void onDrawEvent(Object source, String name, String value) {
		int currentPlayerId = MonopolyGame.getCurrentPlayerID();
		playerBalanceLabel.setText("Balance: " + MonopolyGame.getPlayerBalance(currentPlayerId));
		playerDeedLabel.setText("Deeds: \n"+ MonopolyGame.getPlayerDeeds(currentPlayerId).size());
	}

	
}
