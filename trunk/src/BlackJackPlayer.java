import cardgame.Card;
import cardgame.Hand;
import cardgame.Rank;


public abstract class BlackJackPlayer {

	private BlackJackHand hand;

	public BlackJackPlayer() {
		hand = new BlackJackHand();	
	}
	
	public BlackJackHand getHand(){
		return hand;
	}
	
	public int hit(Card card){
		hand.addCard(card);
		return hand.evaluateHand();
	}
	
	
	/*
	public int scoreKingHigh(){
		for (Card c: hand){
			
		}
		return bet;
	}
	*/
	
	private class BlackJackHand extends Hand{
		
		private int acumValue;
		private int acesHigh;
		
		public BlackJackHand(){
			acumValue = 0;
			acesHigh=0;
		}
		
		@Override
		public int evaluateHand() {
			Card c = getCard(getNumberOfCards()-1);
			if (c.getRank().compareTo(Rank.ACE) == 0){
				acumValue +=11;
				if (acumValue > 21){
					acumValue-=10;
				} else {
					acesHigh++; 
				}
			}
			else if (c.getRank().compareTo(Rank.JACK) < 0){
				acumValue +=10;
				if (acumValue > 21 && acesHigh>0){
					acesHigh--;
				}
			}
			else if (c.getRank().compareTo(Rank.TEN) > 0){
				acumValue+=10;
				if (acumValue > 21 && acesHigh>0){
					acesHigh--;
				}
			}
			
			return acumValue;
		}
		
	}
	
}
