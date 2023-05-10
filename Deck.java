package blackJackSplit;

import java.util.ArrayList;
import java.util.Random;

public class Deck 
{
	private ArrayList<Card> cards = new ArrayList<Card>();
	public Deck() 
	{
        cards = new ArrayList<Card>();
        
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] names = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", 
                          "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < names.length; j++) {
                Card card = new Card(values[j], suits[i], names[j] + " of " + suits[i]);
                cards.add(card);
            }
        }
//        System.out.println(cards);
    }
	public ArrayList<Card> shuffled() //actual shuffled deck
	{
		ArrayList<Card> shuffledCards = new ArrayList<Card>();
		int index = 0;
		Random random = new Random();
		while (shuffledCards.size() < 52)
		{
			index = random.nextInt(52);
			Card card = cards.get(index);
			if (!shuffledCards.contains(card))
			{
				shuffledCards.add(card);
			}
		}
//		System.out.println(shuffledCards);
		return shuffledCards;
	}
	
	public ArrayList<Card> shuffled(int numDoesNotMatter) //what is used for test purposes so I can stack the deck to test different test cases
	{
		ArrayList<Card> shuffledCards = new ArrayList<Card>();
		
		shuffledCards.add(new Card(10, "Spades", "10 of Spades"));
		shuffledCards.add(new Card(2, "Hearts", "Jack of Hearts"));
		shuffledCards.add(new Card(10, "Diamonds", "Queen of Diamonds"));
		
		shuffledCards.add(new Card(3, "Clubs", "King of Clubs"));
		shuffledCards.add(new Card(11, "Hearts", "Ace of Hearts"));
		shuffledCards.add(new Card(11, "Hearts", "Ace of Hearts"));
		shuffledCards.add(new Card(8, "Spades", "Ace of Spades"));
		shuffledCards.add(new Card(5, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(8, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(8, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		shuffledCards.add(new Card(2, "Spades", "Test Name"));
		System.out.println(shuffledCards);
		return shuffledCards;
	}
}
