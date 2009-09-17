/* 
 ITESM-CEM
 Jorge Adrian Garcia Jimenez - 967292
 Uriel Cruz Pineda - 465722 
 Materia: Programacion y Estructura de Datos - Ariel Ortiz
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Map;
import java.util.TreeMap;

import cardgame.Card;
import cardgame.Deck;
import cardgame.Rank;
import cardgame.Suit;

public class BlackJackMaster extends JFrame {

	Card faceDownCard, faceDown;
	private JLabel imgPlayerCard1, imgPlayerCard2, imgPlayerCard3, imgPlayerCard4, imgPlayerCard5, imgDealerCard1, imgDealerCard2, imgDealerCard3,
			imgDealerCard4, imgDealerCard5, bg, playerLabel, dealerLabel, a, d;
	private Deck deckTable;
	public JButton botonDeal, botonHit, botonStand, botonUpBet, botonDownBet;
	private JTextField apue, din;
	int ncartas = 0, ncartas2 = 0, puntjug, puntcas, puntjugace, puntcasace;
	int[] play, playace, casa, casaace;
	boolean inTurnGame = true;
	public double dinero = 500, apuesta = 2;
	JPanel playerPanel = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel bgpanel = new JPanel();
	JPanel panela = new JPanel();
	Map<String, Integer> rang = new TreeMap<String, Integer>();
	JLabel apuestas = new JLabel(new ImageIcon("images/apuestas.jpg"));
	Card[] playerCards, dealerCards;

	private class botonDealAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (inTurnGame) {
				botonUpBet.setEnabled(false);
				botonDownBet.setEnabled(false);
				botonDeal.setEnabled(false);
				botonHit.setEnabled(true);
				botonStand.setEnabled(true);
				apuestas.setEnabled(false);
				deckTable.shuffle();
				faceDownCard = deckTable.dealCard();
				imgPlayerCard1.setIcon(faceDownCard.getCardImage());
				veintiuno();
				faceDownCard = deckTable.dealCard();
				imgPlayerCard2.setIcon(faceDownCard.getCardImage());
				veintiuno();
				inTurnGame = false;
			} else {
				botonHit.setEnabled(false);
				faceDownCard = new Card(null, null, new ImageIcon(
						"images/b.gif"));
				imgPlayerCard1.setIcon(faceDownCard.getCardImage());
				imgPlayerCard2.setIcon(faceDownCard.getCardImage());
				imgPlayerCard3.setIcon(faceDownCard.getCardImage());
				imgPlayerCard4.setIcon(faceDownCard.getCardImage());
				imgPlayerCard5.setIcon(faceDownCard.getCardImage());
				imgDealerCard1.setIcon(faceDownCard.getCardImage());
				imgDealerCard2.setIcon(faceDownCard.getCardImage());
				imgDealerCard3.setIcon(faceDownCard.getCardImage());
				imgDealerCard4.setIcon(faceDownCard.getCardImage());
				imgDealerCard5.setIcon(faceDownCard.getCardImage());
				ncartas = 0;
				ncartas2 = 0;
				puntjug = 0;
				puntcas = 0;
				puntjugace = 0;
				puntcasace = 0;
				reseteo(play);
				reseteo(playace);
				reseteo(casa);
				reseteo(casaace);
				inTurnGame = true;
			}
		}
	}

	public class botonHitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (ncartas) {
			case 2:
				faceDownCard = deckTable.dealCard();
				imgPlayerCard3.setIcon(faceDownCard.getCardImage());
				veintiuno();
				break;
			case 3:
				faceDownCard = deckTable.dealCard();
				imgPlayerCard4.setIcon(faceDownCard.getCardImage());
				veintiuno();
				break;
			case 4:
				faceDownCard = deckTable.dealCard();
				imgPlayerCard5.setIcon(faceDownCard.getCardImage());
				veintiuno();
				break;
			}
			if (puntjug > 21 && puntjugace > 21) {
				computadora();
			}
		}
	}

	public class botonStandAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			botonHit.setEnabled(false);
			computadora();
		}
	}

	public class boton4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (dinero > 2) {
				dinero--;
				apuesta++;
			}
			din.setText("$" + dinero);
			apue.setText("$" + apuesta);
		}
	}

	public class boton5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (apuesta > 2) {
				dinero++;
				apuesta--;
			}
			din.setText("$" + dinero);
			apue.setText("$" + apuesta);
		}
	}

	public BlackJackMaster() {
		initializeDeck();
		setTitle("BlackJack Master");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Se le meten los valores relacionados con los rangos
		rang.put("Ace", 1);
		rang.put("Two", 2);
		rang.put("Three", 3);
		rang.put("Four", 4);
		rang.put("Five", 5);
		rang.put("Six", 6);
		rang.put("Seven", 7);
		rang.put("Eight", 8);
		rang.put("Nine", 9);
		rang.put("Ten", 10);
		rang.put("Jack", 10);
		rang.put("Queen", 10);
		rang.put("King", 10);

		// Iniciar cuenta
		play = new int[10];
		casa = new int[10];
		playace = new int[10];
		casaace = new int[10];
		
		//Player and Dealer's Cards
		playerCards = new Card[5];
		dealerCards = new Card[5];

		// BackGround
		JLabel bg = new JLabel(new ImageIcon ("images/bg.jpg"));
		bgpanel.add(bg);

		// Cartas inciales [Jugador]
		faceDownCard = new Card(null, null, new ImageIcon("images/b.gif"));
		playerLabel = new JLabel("CARTAS DEL JUGADOR");
		playerPanel.add(playerLabel);
		for(Card n: playerCards){
			n = faceDownCard;
			playerPanel.add(new JLabel(faceDownCard.getCardImage()));
		}
		/*
		imgPlayerCard1 = new JLabel(faceDownCard.getCardImage());
		imgPlayerCard2 = new JLabel(faceDownCard.getCardImage());
		imgPlayerCard3 = new JLabel(faceDownCard.getCardImage());
		imgPlayerCard4 = new JLabel(faceDownCard.getCardImage());
		imgPlayerCard5 = new JLabel(faceDownCard.getCardImage());
		playerLabel = new JLabel("CARTAS DEL JUGADOR");
		playerPanel.add(playerLabel);
		playerPanel.add(imgPlayerCard1);
		playerPanel.add(imgPlayerCard2);
		playerPanel.add(imgPlayerCard3);
		playerPanel.add(imgPlayerCard4);
		playerPanel.add(imgPlayerCard5);
		*/

		// Cartas del Dealer
		imgDealerCard1 = new JLabel(faceDownCard.getCardImage());
		imgDealerCard2 = new JLabel(faceDownCard.getCardImage());
		imgDealerCard3 = new JLabel(faceDownCard.getCardImage());
		imgDealerCard4 = new JLabel(faceDownCard.getCardImage());
		imgDealerCard5 = new JLabel(faceDownCard.getCardImage());
		dealerLabel = new JLabel("CARTAS DE LA COMPUTADORA");
		panel2.add(apuestas);
		panel2.add(dealerLabel);
		panel2.add(imgDealerCard1);
		panel2.add(imgDealerCard2);
		panel2.add(imgDealerCard3);
		panel2.add(imgDealerCard4);
		panel2.add(imgDealerCard5);

		// Panel para botones
		JPanel f = new JPanel();
		playerPanel.add(BorderLayout.SOUTH, f);

		// Botones [Gameplay]
		botonDeal = new JButton("Repartir");
		botonHit = new JButton("Pedir otra Carta");
		botonStand = new JButton("Quedarse con esa mano");
		botonDeal.addActionListener(new botonDealAction());
		botonHit.addActionListener(new botonHitAction());
		botonStand.addActionListener(new botonStandAction());
		botonHit.setEnabled(false);
		botonStand.setEnabled(false);

		// Botones Apuesta
		botonUpBet = new JButton("Subir Apuesta");
		botonDownBet = new JButton("Bajar Apuesta");
		botonUpBet.addActionListener(new boton4());
		botonDownBet.addActionListener(new boton5());
		a = new JLabel("APUESTA:");
		d = new JLabel("DINERO:");
		apue = new JTextField("$" + apuesta, 5);
		din = new JTextField("$" + dinero, 5);
		apue.setEditable(false);
		din.setEditable(false);
		JPanel apues = new JPanel();
		apues.add(a);
		apues.add(apue);
		JPanel dine = new JPanel();
		dine.add(d);
		dine.add(din);
		JPanel boto4 = new JPanel();
		boto4.add(botonUpBet);
		JPanel boto5 = new JPanel();
		boto5.add(botonDownBet);

		// Añladir los paneles
		this.add(bgpanel);
		this.add(BorderLayout.NORTH, panel2);
		this.add(BorderLayout.SOUTH, playerPanel);
		this.add(BorderLayout.WEST, panela);

		// Posicion de los botones
		f.add(botonDeal);
		f.add(botonHit);
		f.add(botonStand);
		panela.setLayout(new GridLayout(4, 1));

		panela.add(apues);
		panela.add(boto4);
		panela.add(boto5);
		panela.add(dine);

		pack();
	}

	// Create the Deck of the Table
	private void initializeDeck() {
		deckTable = new Deck();
		for (int i = 0; i < 6; i++) {
			for (Suit s : Suit.VALUES) {
				for (Rank r : Rank.VALUES) {
					Card c = new Card(s, r, new ImageIcon("images/"
							+ r.getSymbol() + s.getSymbol() + ".gif"));
					deckTable.addCard(c);
				}
			}
		}
	}

	public void computadora() {

		botonHit.setEnabled(false);
		botonStand.setEnabled(false);
		do {
			faceDown = deckTable.dealCard();
			String tem = faceDown.getRank().getName();
			switch (ncartas2) {
			case 0:
				imgDealerCard1.setIcon(faceDown.getCardImage());
				break;
			case 1:
				imgDealerCard2.setIcon(faceDown.getCardImage());
				break;
			case 2:
				imgDealerCard3.setIcon(faceDown.getCardImage());
				break;
			case 3:
				imgDealerCard4.setIcon(faceDown.getCardImage());
				break;
			case 4:
				imgDealerCard5.setIcon(faceDown.getCardImage());
				break;
			}
			if (tem.equals("Ace")) {
				if (hayace(casa)) {
					casa[ncartas] = 1;
					casaace[ncartas] = 1;
				} else {
					casa[ncartas] = 1;
					casaace[ncartas] = 11;
				}
			} else {
				casa[ncartas] = rang.get(tem);
				casaace[ncartas] = rang.get(tem);
			}
			ncartas2++;
			for (int x : casa)
				puntcas += x;
			for (int x : casaace)
				puntcasace += x;
			System.out.println(puntcas + "  " + ncartas2);
			System.out.println(puntcasace + "");
		} while (puntajecompu());
		terminar(ganador());
	}

	public boolean puntajecompu() {
		if ((ncartas2 == 2 || ncartas2 == 1) && puntcasace < 17)
			return true;
		int temo;
		if (puntjugace < 22) {
			temo = puntjugace;
		} else {
			if (puntjug < 22) {
				temo = puntjug;
			} else {
				return false;
			}
		}
		if (puntcasace < temo) {
			return true;
		} else {
			if (puntcasace < 22) {
				return false;
			} else {
				if (puntcas < temo) {
					return true;
				} else {
					if (puntcas < 22) {
						return false;
					}
				}
			}
		}
		return false;
	}

	public void veintiuno() {
		String tem = faceDownCard.getRank().getName();
		puntjug = 0;
		puntjugace = 0;
		if (tem.equals("Ace")) {
			if (hayace(play)) {
				play[ncartas] = 1;
				playace[ncartas] = 1;
			} else {
				play[ncartas] = 1;
				playace[ncartas] = 11;
			}
		} else {
			play[ncartas] = rang.get(tem);
			playace[ncartas] = rang.get(tem);
		}
		ncartas++;
		for (int x : play)
			puntjug += x;
		for (int x : playace)
			puntjugace += x;
		System.out.println(puntjug + "  " + ncartas);
		System.out.println(puntjugace + "");
	}

	public boolean hayace(int[] t) {
		for (int x : t) {
			if (x == 1 || x == 11) {
				return true;
			}
		}
		return false;
	}

	public int ganador() {
		int a, c;
		if (puntjugace < 22) {
			a = puntjugace;
		} else {
			if (puntjug < 22) {
				a = puntjug;
			} else {
				a = 0;
			}
		}
		if (puntcasace < 22) {
			c = puntcasace;
		} else {
			if (puntcas < 22) {
				c = puntcas;
			} else {
				c = 0;
			}
		}
		if (a < c) {
			return -1;
		} else {
			if (a > c) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public void terminar(int x) {
		switch (x) {
		case -1:
			dinero = dinero - 2;
			apuesta = 2;
			break;

		case 0:
			dinero = dinero + apuesta - 2;
			apuesta = 2;
			break;

		case 1:
			dinero = dinero + (apuesta * 1.5) - 2;
			apuesta = 2;
			break;

		}
		din.setText("$" + dinero);
		apue.setText("$" + apuesta);
		deckTable.restoreDeck();
		botonDeal.setEnabled(true);
		apuestas.setEnabled(true);
		botonUpBet.setEnabled(true);
		botonDownBet.setEnabled(true);

	}

	public void reseteo(int[] arreglo) {
		for (int i = 0; i < arreglo.length; i++) {
			arreglo[i] = 0;
		}
	}

	public static void main(String[] args) {
		BlackJackMaster bjm = new BlackJackMaster();
		bjm.setVisible(true);

	}

}
