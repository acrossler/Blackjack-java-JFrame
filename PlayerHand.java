package blackJackSplit;

import java.util.ArrayList;

public class PlayerHand extends Hand
{
	public PlayerHand()
	{
		super();
	}
	
	public Boolean isSplitable() //only can be called when there are two cards in the deck, all other times it causes an error
	{
		if (super.getCard(0).getName().substring(0, 2).equals(super.getCard(1).getName().substring(0, 2)))
		{
			return true;
		}
		return false;
	}
	
	public void split(Hand hand, ArrayList<Card> cards)
	{
		cards.add(0, this.getCard(1));; //second card added to the splitHand
		this.removeCard(1);
	}
}
