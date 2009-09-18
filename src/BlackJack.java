import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cardgame.Card;

public class BlackJack extends JFrame {

	private Card faceDownCard;
	private JPanel playerPanel, dealerPanel;
	private Card[] playerCards, dealerCards;
	private JLabel betSizeLabel;
	private JLabel myMoneyLabel;
	private JPanel messagesPanel;

	private Player player = new Player();

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

		JLabel bg = new JLabel(new ImageIcon("images/bg.jpg"));
		bg.setBounds(0, 0, 600, 415);

		playerCards = new Card[5];
		dealerCards = new Card[5];
		faceDownCard = new Card(null, null, new ImageIcon("images/b.gif"));
		initializeCards();

		playerPanel = new JPanel();
		dealerPanel = new JPanel();

		for (Card n : dealerCards) {
			dealerPanel.add(new JLabel(n.getCardImage()));
		}
		dealerPanel.setOpaque(false);
		dealerPanel.setBounds(45, 30, 500, 100);

		// playerPanel.add(playerLabel);
		for (Card n : playerCards) {
			playerPanel.add(new JLabel(n.getCardImage()));
		}
		playerPanel.setOpaque(false);
		playerPanel.setBounds(45, 190, 500, 100);

		createButtons();

		betSizeLabel = new JLabel();
		// betSizeLabel.setForeground(Color.CYAN);
		betSizeLabel.setText(" $ " + BJContext.getMinBetValue());
		betSizeLabel.setBounds(12, 198, 87, 25);
		betSizeLabel.setOpaque(true);

		JLabel betLabel = new JLabel();
		betLabel.setForeground(Color.CYAN);
		betLabel.setText("   Actual Bet ");
		betLabel.setBounds(12, 173, 87, 25);
		betLabel.setOpaque(false);

		myMoneyLabel = new JLabel();
		myMoneyLabel.setText(" $ " + BJContext.getInitialMoney());
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

		gameTable.add(messagesPanel, 1);
		gameTable.add(dealerPanel, 2);
		gameTable.add(playerPanel, 3);
		gameTable.add(upBetButton, 4);
		gameTable.add(downBetButton, 5);
		gameTable.add(hitButton, 6);
		gameTable.add(standButton, 7);
		gameTable.add(startButton, 8);
		gameTable.add(betSizeLabel, 9);
		gameTable.add(myMoneyLabel, 10);
		gameTable.add(betLabel, 11);
		gameTable.add(moneyLabel, 12);
		gameTable.add(bg, 13);
		this.add(gameTable);
	}

	private void createButtons() {

		upBetButton = new JButton("Up Bet");
		upBetButton.setBounds(10, 312, 90, 25);
		downBetButton = new JButton("Down Bet");
		downBetButton.setBounds(10, 340, 90, 25);
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

	}

	private void initializeCards() {
		for (int i = 0; i < 5; i++) {
			playerCards[i] = faceDownCard;
			dealerCards[i] = faceDownCard;
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

	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			// Up Bet button pressed
			if (e.getSource().equals(upBetButton)) {
				player.increaseBet();
				downBetButton.setEnabled(true);
				refreshCoinValues();

				if (player.getBet() == BJContext.getMaxBetValue()) {
					upBetButton.setEnabled(false);
				}
			}

			// Down Bet button pressed
			else if (e.getSource() == downBetButton) {
				player.decreaseBet();
				upBetButton.setEnabled(true);
				refreshCoinValues();

				if (player.getBet() == BJContext.getMinBetValue()) {
					downBetButton.setEnabled(false);
				}
			}

		}
	}
}
