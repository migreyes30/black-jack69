

public class Player extends BlackJackPlayer{
	
	
	private int money, bet;
	
	Player(){
		super();
		money = BJContext.getInitialMoney();
		bet = BJContext.getMinBetValue();
	}
	
	public int getMoney(){
		return money;
	}
	
	public int getBet(){
		return bet;
	}
	
	public void increaseBet(){
		if (money > 0) {
			bet ++;
			money --;
		}
	}
	
	public void decreaseBet(){
		bet --;
		money ++;
	}
	
	public void setLooseMoney(int betLoose){
		money -= betLoose;
	}
	
	public void setWinMoney(int betWin){
		money -= betWin;
	}

}
