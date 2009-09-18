import javax.swing.ImageIcon;

public class BJContext {

	private static int MIN_BET_VALUE = 5;
	private static int MAX_BET_VALUE = 500;

	private static int INITIAL_MONEY = 1000;

	private static ImageIcon FACE_DOWN_CARD = new ImageIcon("images/b.gif");

	public static int getMinBetValue() {
		return MIN_BET_VALUE;
	}

	public static int getMaxBetValue() {
		return MAX_BET_VALUE;
	}

	public static int getInitialMoney() {
		return INITIAL_MONEY;
	}
	
	public static ImageIcon getFaceDownCard(){
		return FACE_DOWN_CARD;
	}

}