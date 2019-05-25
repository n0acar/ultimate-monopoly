package kapitalMonopolyUI;
/*
 * 
 * This class is under construction for now, it will be used to determine the order of players
 * 
 */
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import kapitalMonopoly.MonopolyGame; // ONLY DOMAIN CLASS : MonopolyGame working as a controller
import kapitalMonopolyObservers.OrderListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class OrderDialog extends JDialog implements OrderListener{
	
	JLabel dieLabel = new JLabel("");
	JButton dialogBtn = new JButton("Roll Die");
	Boolean dieRolled = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDialog dialog = new OrderDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public OrderDialog() {
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		
		MonopolyGame.getCup().addOrderListener(this);
		
		dialogBtn.setBounds(100, 120, 100, 30);
		getContentPane().add(dialogBtn);
		
		
		dieLabel.setBounds(125, 50, 50, 50);
		getContentPane().add(dieLabel);
		dieLabel.setIcon(new ImageIcon("DiePictures/game-die_1.png"));
		
		dialogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dieRolled){
					MonopolyGame.getCup().rollDice(MonopolyGame.CupInputs.R);
					dialogBtn.setText("Start");
					dieRolled = true;
				}
				else {
					dispose();
				}
			}
		});
	}

	@Override
	public void onOrderEvent(Object source, String name, String value) {
		// TODO Auto-generated method stub
		dieLabel.setIcon(new ImageIcon(getPath(Integer.parseInt(value))));
	}
	
	public String getPath(int number){
		return "DiePictures/die-face-" + number + ".png";
	}
}
