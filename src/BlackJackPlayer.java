import cardgame.Card;
import cardgame.Hand;


public abstract class BlackJackPlayer {

	private BlackJackHand hand;
	private int acumValue;
	
	public BlackJackPlayer() {
		hand = new BlackJackHand();	
		acumValue = 0;
	}
	
	public BlackJackHand getHand(){
		return hand;
	}
	
	public void hit(Card card){
		hand.addCard(card);
	}
	
	public int getTotal(){
		return acumValue;
	}
	
	/*
	public int scoreKingHigh(){
		for (Card c: hand){
			
		}
		return bet;
	}
	*/
	
	private class BlackJackHand extends Hand{

		@Override
		public int evaluateHand() {
			return 0;
		}
		
	}
	
}
