import java.awt.Color;
import java.awt.Font;

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
	private JLabel playerLabel;
	private Card[] playerCards, dealerCards;
	private JLabel dealerLabel;
	private JLabel betSizeLabel;
	private int sizeMoneyBet, myMoney;
	private JLabel myMoneyLabel;
	private JLabel betLabel;
	private JLabel moneyLabel;
	private JPanel messagesPanel;

	public BlackJack() {
		super("Black Jack");
		setSize(600, 415);
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
		
		//playerLabel = new JLabel("PLEAYER CARDS");
		//playerLabel.setFont(new Font("Arial", "", 12));
		//dealerLabel = new JLabel("DEALER CARDS");
		//playerLabel.setOpaque(false);
		//playerLabel.setBounds(100, 20, 100, 20);
		//gameTable.add(playerLabel, 2);
		
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
		
		
		// Create the buttons
		JButton upBetBottom = new JButton("Up Bet");
		upBetBottom.setBounds(10, 312, 90, 25);
		JButton downBetBottom = new JButton("Down Bet");
		downBetBottom.setBounds(10, 340, 90, 25);
		JButton hitBottom = new JButton("Hit");
		hitBottom.setBounds(105, 332, 90, 25);
		JButton standBottom = new JButton("Stand");
		standBottom.setBounds(200, 332, 90, 25);
		
		betSizeLabel = new JLabel();
		//betSizeLabel.setForeground(Color.CYAN);
		betSizeLabel.setText(" $ " + sizeMoneyBet);
		betSizeLabel.setBounds(12, 198, 87, 25);
		betSizeLabel.setOpaque(true);
		
		betLabel = new JLabel();
		betLabel.setForeground(Color.CYAN);
		betLabel.setText("   Actual Bet ");
		betLabel.setBounds(12, 173, 87, 25);
		betLabel.setOpaque(false);
		
		myMoneyLabel = new JLabel();
		myMoneyLabel.setText(" $ " + myMoney);
		//myMoneyLabel.setForeground(Color.YELLOW);
		myMoneyLabel.setBounds(300, 332, 90, 25);
		myMoneyLabel.setOpaque(true);
		
		moneyLabel = new JLabel();
		moneyLabel.setText(" My Money ");
		moneyLabel.setForeground(Color.YELLOW);
		moneyLabel.setBounds(310, 307, 90, 25);
		moneyLabel.setOpaque(false);
		
		messagesPanel = new JPanel();
		messagesPanel.add(new JLabel(new ImageIcon("images/apuestas.jpg")));
		messagesPanel.setBounds(200, 110, 175, 175);
		messagesPanel.setOpaque(false);
		
		gameTable.add(messagesPanel, 1);
		gameTable.add(dealerPanel, 2);
		gameTable.add(playerPanel, 2);
		gameTable.add(upBetBottom, 3);
		gameTable.add(downBetBottom, 3);
		gameTable.add(hitBottom, 4);
		gameTable.add(standBottom, 4);
		gameTable.add(betSizeLabel, 5);
		gameTable.add(myMoneyLabel, 5);
		gameTable.add(betLabel, 6);
		gameTable.add(moneyLabel, 6);
		gameTable.add(bg, 10);
		this.add(gameTable);
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
}
