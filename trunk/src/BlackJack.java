import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import cardgame.*;

public class BlackJack extends JFrame {

	private JPanel playerPanel, dealerPanel;
	private JLabel betSizeLabel;
	private JLabel myMoneyLabel;
	private JPanel messagesPanel;

	private Player player;
	private Dealer dealer;
	private Deck deck;

	private ButtonManager bmgr;

	private JButton upBetButton;
	private JButton downBetButton;
	private JButton hitButton;
	private JButton standButton;
	private JButton startButton;

	public BlackJack() {
		super("Black Jack");
		setBounds(300, 50, 600, 415);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLayeredPane gameTable = new JLayeredPane();
		initializeDeck();
		player = new Player();
		dealer = new Dealer();

		JLabel bg = new JLabel(new ImageIcon("images/bg.jpg"));
		bg.setBounds(0, 0, 600, 415);

		
		initializeCards();

		
		dealerPanel = new JPanel();
		dealerPanel.setOpaque(false);
		dealerPanel.setBounds(45, 30, 500, 100);
		
		playerPanel = new JPanel();
		playerPanel.setOpaque(false);
		playerPanel.setBounds(45, 190, 500, 100);

		createButtons();

		betSizeLabel = new JLabel(" $ " + BJContext.getMinBetValue());
		// betSizeLabel.setForeground(Color.CYAN);
		betSizeLabel.setBounds(12, 198, 87, 25);
		betSizeLabel.setOpaque(true);

		JLabel betLabel = new JLabel();
		betLabel.setForeground(Color.CYAN);
		betLabel.setText("   Actual Bet ");
		betLabel.setBounds(12, 173, 87, 25);
		betLabel.setOpaque(false);

		myMoneyLabel = new JLabel(" $ " + BJContext.getInitialMoney());
		// myMoneyLabel.setForeground(Color.YELLOW);
		myMoneyLabel.setBounds(300, 332, 90, 25);
		myMoneyLabel.setOpaque(true);

		JLabel moneyLabel = new JLabel();
		moneyLabel.setText(" My Money ");
		moneyLabel.setForeground(Color.YELLOW);
		moneyLabel.setBounds(310, 307, 90, 25);
		moneyLabel.setOpaque(false);

		messagesPanel = new JPanel();
		messagesPanel.add(new JLabel(new ImageIcon("images/apuestas.jpg")));
		messagesPanel.setBounds(205, 110, 175, 175);
		messagesPanel.setOpaque(false);

		gameTable.add(messagesPanel);
		gameTable.add(dealerPanel);
		gameTable.add(playerPanel);
		gameTable.add(upBetButton);
		gameTable.add(downBetButton);
		gameTable.add(hitButton);
		gameTable.add(standButton);
		gameTable.add(startButton);
		gameTable.add(betSizeLabel);
		gameTable.add(myMoneyLabel);
		gameTable.add(betLabel);
		gameTable.add(moneyLabel);
		gameTable.add(bg);
		this.add(gameTable);
	}

	private void createButtons() {

		upBetButton = new JButton("Up Bet");
		upBetButton.setBounds(10, 312, 90, 25);
		downBetButton = new JButton("Down Bet");
		downBetButton.setBounds(10, 340, 90, 25);
		downBetButton.setEnabled(false);
		hitButton = new JButton("Hit");
		hitButton.setBounds(105, 332, 90, 25);
		hitButton.setEnabled(false);
		standButton = new JButton("Stand");
		standButton.setBounds(200, 332, 90, 25);
		standButton.setEnabled(false);
		
		startButton = new JButton("Start");
		startButton.setBounds(250,190,90,25);

		bmgr = new ButtonManager();
		upBetButton.addActionListener(bmgr);
		downBetButton.addActionListener(bmgr);
		hitButton.addActionListener(bmgr);
		standButton.addActionListener(bmgr);
		startButton.addActionListener(bmgr);
	}

	private void initializeCards() {
		for (int i = 0; i < 5; i++) {

		}
	}

	public static void main(String[] args) {
		BlackJack sl = new BlackJack();
		sl.setVisible(true);
	}

	public Player getPlayer() {
		return player;
	}

	public void refreshCoinValues() {
		betSizeLabel.setText(" $ " + player.getBet());
		myMoneyLabel.setText(" $ " + player.getMoney());
	}
	
	// Create the Deck of the Table
	private void initializeDeck() {
		deck = new Deck();
		for (int i = 0; i < 6; i++) {
			for (Suit s : Suit.VALUES) {
				for (Rank r : Rank.VALUES) {
					Card c = new Card(s, r, new ImageIcon("images/"
							+ r.getSymbol() + s.getSymbol() + ".gif"));
					deck.addCard(c);
				}
			}
		}
		deck.shuffle();
	}
	
	private void dealCards(){
		
		dealCard(playerPanel, true);
		dealCard(dealerPanel, true);
		dealCard(playerPanel, true);
		dealCard(dealerPanel, false);
		
	}
	
	private void dealCard(JPanel where, boolean faceup){
		
		Card dealtCard = deck.dealCard();
		JLabel cardLabel;
		
		if (where == playerPanel){
			player.hit(dealtCard);
		} else {
			dealer.hit(dealtCard);
		}

		if (faceup){
			cardLabel = new JLabel(dealtCard.getCardImage());
		} else {
			cardLabel = new JLabel(BJContext.getFaceDownCard());
		}
		where.add(cardLabel);
		
	}

	private class ButtonManager implements ActionListener {

		private boolean upEnabled = false;
		private boolean downEnabled = true;
		
		public void actionPerformed(ActionEvent e) {
			
			// Up Bet button pressed
			if (e.getSource().equals(upBetButton)) {
				player.increaseBet();
				downEnabled = true;
				downBetButton.setEnabled(downEnabled);
				refreshCoinValues();

				if (player.getBet() == BJContext.getMaxBetValue()) {
					upEnabled = false;
					upBetButton.setEnabled(upEnabled);
				}
			}

			// Down Bet button pressed
			else if (e.getSource() == downBetButton) {
				player.decreaseBet();
				upEnabled = true;
				upBetButton.setEnabled(upEnabled);
				refreshCoinValues();

				if (player.getBet() == BJContext.getMinBetValue()) {
					downEnabled = false;
					downBetButton.setEnabled(downEnabled);
				}
			}
			
			// Start button pressed
			else if (e.getSource() == startButton) {
				upBetButton.setEnabled(false);
				downBetButton.setEnabled(false);
				startButton.setVisible(false);
				messagesPanel.setVisible(false);
				
				hitButton.setEnabled(true);
				standButton.setEnabled(true);
				
				dealCards();
				
			} // Hit button pressed
			else if (e.getSource() == hitButton) {
				
				dealCard(playerPanel, true);
				playerPanel.doLayout();
			}

		}
	}
}
