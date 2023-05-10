package blackJackSplit;



public class Card 
{
	private int value;
	private String suit;
	private String name;
	public Card(int value, String suit, String name) {
		this.value = value;
		this.suit = suit;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public String getName() {
		return name;
	}

}
