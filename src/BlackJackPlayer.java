
import cardgame.Card;
import cardgame.Hand;
import cardgame.Rank;


public abstract class BlackJackPlayer {

	private BlackJackHand hand;
	private int status;

	public BlackJackPlayer() {
		hand = new BlackJackHand();	
		status = BJContext.STANDBY;
	}
	
	public BlackJackHand getHand(){
		return hand;
	}
	
	public void hit(Card card){
		hand.addCard(card);
		hand.evaluateHand();
		
		if (hand.getTotal() == 21){
			status = BJContext.WIN;
		} else if (hand.getTotal() > 21){
			status = BJContext.LOSE;
		}	
	}
	
	public int getStatus(){
		return status;
	}
	
	public void enterGame(){
		hand.resetTotal();
		status = BJContext.PLAYING;
	}
	
	public void startBetting(){
		hand.discardHand();
		status = BJContext.STANDBY;
	}
	
	public int getTotal(){
		return hand.getTotal();
	}
	
	
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
			else if (c.getRank().compareTo(Rank.TEN) < 0){
				
				acumValue += Integer.parseInt(c.getRank().getSymbol());
				if (acumValue > 21 && acesHigh>0){
					acesHigh--;
					acumValue-=10;
				}
			}
			else if (c.getRank().compareTo(Rank.TEN) >= 0){
				acumValue+=10;
				if (acumValue > 21 && acesHigh>0){
					acesHigh--;
					acumValue-=10;
				}
			}
			
			return 0;
		}
		
		public int getTotal(){
			return acumValue;
		}
		
		public void resetTotal(){
			acumValue = 0;
		}
		
	}
	
}
