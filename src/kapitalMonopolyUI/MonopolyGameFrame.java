package kapitalMonopolyUI;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class MonopolyGameFrame {
		
	final int FRAME_WIDTH = 1400;
	final int FRAME_HEIGHT = 800;
	public static final int BREAK_AMOUNT = 50;
	
	private JFrame frame = new JFrame();;
	private DiePanel diePanel;
	private CardPanel cardPanel;
	private BoardPanel boardPanel;
	private PlayerPanel playerPanel;
	//private DeedPanel deedPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonopolyGameFrame window = new MonopolyGameFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MonopolyGameFrame() {
		diePanel = new DiePanel();
		cardPanel = new CardPanel();
		boardPanel = new BoardPanel();
		playerPanel = new PlayerPanel();
		//deedPanel = new DeedPanel();
		
		initialize();
		
		frame.getContentPane().add(diePanel.dieJPanel);
		frame.getContentPane().add(cardPanel.cardJPanel);
		frame.getContentPane().add(boardPanel.boardJPanel);
		frame.getContentPane().add(playerPanel.playerJPanel);
		//frame.getContentPane().add(deedPanel.deedJPanel);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	}
}
