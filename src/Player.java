import java.util.ArrayList;
import java.util.HashMap;

import cardgame.Card;
import cardgame.Rank;
import cardgame.Suit;

public class Player {
	
	private ArrayList<Card> hand;
	
	private int money, bet;
	
	Player(){
		hand = new ArrayList<Card>();
		money = BJContext.getInitialMoney();
		bet = BJContext.getMinBetValue();
	}
	
	public void hitCard(Card actualCard){
		if (hand.size() < 5){
			hand.add(actualCard);
		}
	}
	
	public void cleanHand(){
		hand = new ArrayList<Card>();
	}
	/*
	public int scoreKingHigh(){
		for (Card c: hand){
			
		}
		return bet;
	}
	*/
	public int getMoney(){
		return money;
	}
	
	public int getBet(){
		return bet;
	}
	
	public void increaseBet(){
		bet ++;
		money --;
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
