package blackJackSplit;

import java.util.ArrayList;

public class Hand 
{
	private ArrayList<Card> hand;
	
	public Hand()
	{
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card card)
	{
		hand.add(card);
	}

	@Override
	public String toString() {
		return "" + hand;
	}
	
	public Card getCard(int index)
	{
		return hand.get(index);
	}
	
	public int getSize()
	{
		return hand.size();
	}
	
	public int score()
	{
		int sumValues = 0;
		for (Card card : hand)
		{
			sumValues = sumValues + card.getValue();
		}
		return sumValues;
	}

	public void aceNeedsChange() 
	{
		if (this.score() > 21)
		{
			for (Card card:hand)
			{
				if (card.getValue() == 11)
				{
					card.setValue(1);
					System.out.println(this.score());
					return;
				}
			}
		}
	}
	
	public void removeCard(int index)
	{
		hand.remove(index);
	}
}
