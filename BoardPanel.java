package blackJackSplit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class BoardPanel extends JPanel implements ActionListener
{
	private GameWindow window;
	private ArrayList<Card> cards;
	private Deck deck = new Deck();
	private JButton hitButton, standButton, doubleDownButton, splitButton;
	private JPanel gameBoard;
	private PlayerHand hand, splitHand;
	private DealerHand dealerHand;
	private Boolean playSplit = false;
	
	public BoardPanel(GameWindow window, ArrayList<Card> cards)
	{
		
//		restructure and make methods for each task so I just go to that to change that
		
		this.window = window;
		this.hand = new PlayerHand();
	    this.splitHand = new PlayerHand();
	    this.dealerHand = new DealerHand();
		this.cards = cards;
		setLayout(new BorderLayout());
        setUpGameBoard();//sets up gameBoard
        setGameBoard(); //adds a blank JLabel to every entry in the 5x10  
     // Create button panel
        createButtons();
        //draw cards
        startingDraw();
//        addCardToBoard(hand, 0, 24); added a card for testing to make sure the fuction was working
//        System.out.println(hand);
//        System.out.println(dealerHand);
//		System.out.println(cards);
		
        
	}
	
	private void testDeckUnder20Cards()
	{
		if (this.cards.size() < 20)
		{
			this.cards = deck.shuffled();
		}
	}
	
	private void createButtons() {
		JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        
        // Create buttons
        hitButton = new JButton("Hit");
        hitButton.setName("hit");
        hitButton.addActionListener(this);
        
        standButton = new JButton("Stand");
        standButton.setName("stand");
        standButton.addActionListener(this);
        
        doubleDownButton = new JButton("Double Down");
        doubleDownButton.setName("double");
        doubleDownButton.addActionListener(this);
        
        splitButton = new JButton("Split");
        splitButton.setName("split");
        splitButton.addActionListener(this);
        
        // Add buttons to button panel
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(doubleDownButton);
        buttonPanel.add(splitButton);
        
        // Add button panel to main panel
        add(buttonPanel, BorderLayout.SOUTH);
//        make doubleDown and split not usable when not enough money
        if (window.getBidAmount() > window.getTotalMoney()/2)
        {
        	doubleDownButton.setEnabled(false); //how to disable a button but still display it
        	splitButton.setEnabled(false);
        }

	}
	
	private void setUpGameBoard() {
		// Create game board panel
        gameBoard = new JPanel();
        gameBoard.setBackground(new Color(0, 80, 0));
        gameBoard.setLayout(new GridLayout(5, 10));
        add(gameBoard, BorderLayout.CENTER);
	}
	public void setGameBoard() 
	{
		 //adding all blank cards into positions
//      creates a 5x10
        for (int i = 0; i < 5; i++)
        {
        	for (int j = 0; j < 10; j++)
        	{
            	gameBoard.add(new JLabel());       		
        	}
        }
	}
	private static final long serialVersionUID = -119809592533367828L;
	
	private void handDrawCards(Hand hand, int row)
	{
		hand.addCard(cards.get(0));
		cards.remove(0);
		addCardToBoard(hand, hand.getSize() - 1, row * 10 + hand.getSize() - 1); //10 is the number of columns
		hand.aceNeedsChange();
		window.refresh();
		bust();
	}
	
	private void startingDraw()//uncomment last line at end 
	{
		handDrawCards(this.hand, 2);
		handDrawCards(this.dealerHand, 0);
		handDrawCards(this.hand, 2);
		handDrawCards(this.dealerHand, 0);
		testBlackJackStart();
		JLabel label = (JLabel) gameBoard.getComponent(1);
		label.setVisible(false); //make uncomment this for actual game play
		if (window.getTotalMoney() / 2 >= window.getBidAmount())
		{
		splitButton.setEnabled(hand.isSplitable()); //if hand is splitable the button is visible
		}
		
	}
	
	private void addCardToBoard(Hand hand , int handIndex, int gameBoardIndex)
	{
//		set up for the card
		Card card = hand.getCard(handIndex);
		String cardStringValue = "" + card.getValue();
		int cardValue = card.getValue();
		String cardName = card.getName();
		String cardSuit = card.getSuit();
		JLabel label = (JLabel) gameBoard.getComponent(gameBoardIndex);
//		System.out.println(hand);
//		System.out.println(card.getValue());
		
//		checking if the card is a Jack, Queen, King, or Ace
		if (cardValue >= 10)
		{
			if (cardName.contains("Jack"))
			{
				cardStringValue = "J";
			}
			else if (cardName.contains("Queen"))
			{
				cardStringValue = "Q";
			}
			else if (cardName.contains("King"))
			{
				cardStringValue = "K";
			}
			else if (cardName.contains("Ace"))
			{
				cardStringValue = "A";
			}
		}
		if (cardSuit.equals("Hearts"))
		{
			 label.setText("<html><table>" +
		                "<tr><td>" + cardStringValue +"</td><td></td><td></td></tr>" +
		                "<tr><td>&nbsp;</td><td>&hearts;</td><td></td></tr>" +
		                "<tr><td></td><td></td><td>" + cardStringValue + "</td></tr>" +
		                "</table></html>");
			 label.setForeground(Color.red);
		}
		else if (cardSuit.equals("Spades"))
		{
			label.setText("<html><table>" +
		                "<tr><td>" + cardStringValue +"</td><td></td><td></td></tr>" +
		                "<tr><td>&nbsp;</td><td>&spades;</td><td></td></tr>" +
		                "<tr><td></td><td></td><td>" + cardStringValue + "</td></tr>" +
		                "</table></html>");
			label.setForeground(Color.black);
		}
		else if (cardSuit.equals("Clubs"))
		{
			 label.setText("<html><table>" +
		                "<tr><td>" + cardStringValue +"</td><td></td><td></td></tr>" +
		                "<tr><td>&nbsp;</td><td>&clubs;</td><td></td></tr>" +
		                "<tr><td></td><td></td><td>" + cardStringValue + "</td></tr>" +
		                "</table></html>");
			 label.setForeground(Color.black);
		}
		else if (cardSuit.equals("Diamonds"))
		{
			 label.setText("<html><table>" +
		                "<tr><td>" + cardStringValue +"</td><td></td><td></td></tr>" +
		                "<tr><td>&nbsp;</td><td>&diams;</td><td></td></tr>" +
		                "<tr><td></td><td></td><td>" + cardStringValue + "</td></tr>" +
		                "</table></html>");
			 label.setForeground(Color.red);
		}
//		card properties
        label.setFont(new Font("Arial", Font.PLAIN, 30));
		label.setBackground(Color.white);
        label.setBorder(new BevelBorder(1, Color.black, new Color(0, 80, 0)));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
	}
	
	private void testBlackJackStart()
	{
		if (this.hand.score() == 21)
		{
//			System.out.println(this.hand.score());
			if (this.dealerHand.score() == 21)
			{
				tieGame();
			}
			else 
			{
				window.setTotalMoney(window.getTotalMoney() + window.getBidAmount() * 3/2);
				endGameLabel("Winner Winner Chicken Dinner");
			}
		}
		else if (this.dealerHand.score() == 21)
		{
			lostGame();
		}
		
//		System.out.println("Hello There");
	}
//	all from player perspective
	private void tieGame()
	{
		endGameLabel("Tie Game money back");
	}
	
	private void lostGame()
	{
		window.setTotalMoney(window.getTotalMoney() - window.getBidAmount());
		endGameLabel("Dealer Wins, you lose");
	}
	
	private void winGame()
	{
		window.setTotalMoney(window.getTotalMoney() + window.getBidAmount());
		endGameLabel("Player Wins");
	}
	
	private void endGameLabel(String gameOverMessage)
	{
//		System.out.println("Hi");
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		doubleDownButton.setEnabled(false);
		splitButton.setEnabled(false);
		JLabel gameMessage = (JLabel) gameBoard.getComponent(14); //row 1 * 10 + 4 (column 5)
		gameMessage.setText("<html>" + gameOverMessage.replaceAll("\n", "<br/>") + "</html>");
		gameMessage.setBackground(Color.white);
		gameMessage.setHorizontalAlignment(SwingConstants.CENTER);
		gameMessage.setOpaque(true);
		playAgainButton();
	}
	
	private void playAgainButton()
	{
		if (window.getTotalMoney() > 0)
		{
			JLabel label = (JLabel) gameBoard.getComponent(15);//row 1 * 10 + 5(column 6)
			JButton playAgain = new JButton("Play Again?");
			playAgain.setName("play again");
			playAgain.addActionListener(this);
			playAgain.setBackground(Color.black);
			playAgain.setForeground(Color.white);
			label.setLayout(new GridBagLayout());
			label.setBackground(Color.white);
			label.setOpaque(true);
			label.add(playAgain, new GridBagConstraints());
			window.refresh();
		}
		else
		{
			JLabel label = (JLabel) gameBoard.getComponent(15);//row 1 * 10 + 5(column 6)
			label.setText("No Money, Go Home");
			label.setBackground(Color.white);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setOpaque(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		splitButton.setEnabled(false);
		JButton button = (JButton) e.getSource();
        String buttonName = button.getName();
		if (buttonName.equals("play again")) 
        {
            window.removePanel(this);
            testDeckUnder20Cards();
        	window.addPanel( new BidPanel(window, cards));
		}
		
		else if (buttonName.equals("hit"))
		{
			if (playSplit)
			{
				handDrawCards(splitHand, 4);
			}
			else
			{
			gameBoard.getComponent(21).setVisible(true);
			handDrawCards(hand, 2);
			}
		}
		
		else if(buttonName.equals("stand"))
		{
			if (splitHand.score() > 0 && !playSplit)
			{
//				System.out.println("General Kenobi");
				makeSplitPlayable();
			}
			else if (playSplit)
			{
//				System.out.println("Hello There");
				dealerPlays();
			}
			else
			{
			dealerPlays();
			}
		}
		
		else if (buttonName.equals("double"))
		{
			if (splitHand.score() > 0 && !playSplit)
			{
				gameBoard.getComponent(21).setVisible(true);
				window.setBidAmount(window.getBidAmount() * 2);
				handDrawCards(hand, 2);
				makeSplitPlayable();
			}
			else if(playSplit)
			{
				window.setBidAmount(window.getBidAmount() * 2);
				handDrawCards(splitHand, 4);
				if (hand.score() <= 21 || splitHand.score() <= 21)
				{
					dealerPlays();
				}
			}
			else
			{
				window.setBidAmount(window.getBidAmount() * 2);
				handDrawCards(hand, 2);
				if (hand.score() <= 21)
				{
					dealerPlays();
				}
			}
		}
		
		else if (buttonName.equals("split"))
		{
			hand.split(splitHand, cards);
			handDrawCards(splitHand, 4);
			JLabel label = (JLabel) gameBoard.getComponent(21);
			label.setVisible(false);
			window.refresh();
//			System.out.println(hand);
//			System.out.println(splitHand);
		}
		
	}
	
	private void dealerPlays()
	{
		JLabel label = (JLabel) gameBoard.getComponent(1);
		label.setVisible(true);
		if (splitHand.score() > 0)
		{
//			System.out.println("hi");
			if (dealerHand.score() > 17)	
			{
				if (hand.score() < 21 && splitHand.score() < 21)
				{
					splitWhoWins();
				}
				else if (hand.score() > 21)
				{
					if (dealerHand.score() > splitHand.score())
					{
						endGameLabel("Both hands lose");
					}
					else if (dealerHand.score() < splitHand.score())
					{
						window.setTotalMoney(window.getTotalMoney() + window.getBidAmount());
						endGameLabelSplitOccured("Hand 2 wins", 3);
						playAgainButton();
					}
					else if (dealerHand.score() == splitHand.score())
					{
						endGameLabelSplitOccured("Hand 2 ties", 3);
						playAgainButton();
					}
				}
				
			}
			else if (dealerHand.score() == 17)
			{
				for (int i = 0; i < dealerHand.getSize(); i++)
				{
					if (dealerHand.getCard(i).getValue() == 11)
					{
						dealerHand.getCard(i).setValue(1);
						dealerPlays();
						return;
					}
					
				}
				if (dealerHand.score() == 17)
				{
//					System.out.println("hello");
					splitWhoWins();
				}
			}
			else
			{
				handDrawCards(dealerHand, 0);
				if (dealerHand.score() < 21)
				{
					dealerPlays();
				}
				
			}
		}
		else if(dealerHand.score() > 17)
			{
				whoWins();
			}
		else if (dealerHand.score() == 17)
		{
			for (int i = 0; i < dealerHand.getSize(); i++)
			{
				if (dealerHand.getCard(i).getValue() == 11)
				{
					dealerHand.getCard(i).setValue(1);
					dealerPlays();
					return;
				}
				
			}
			if (dealerHand.score() == 17)
			{
				whoWins();
			}
		}
		else
		{
			handDrawCards(dealerHand, 0);
			if (dealerHand.score() <= 21)
			{
				dealerPlays();
			}
			
		}
	}
	
	
	private void whoWins()
	{
		if (hand.score() == dealerHand.score())
		{
			tieGame();
		}
		else if (hand.score() > dealerHand.score())
		{
			winGame();
		}
		else
		{
			lostGame();
		}
	}
	
	private void splitWhoWins()
	{
		if (dealerHand.score() > hand.score() && dealerHand.score() > splitHand.score())
		{
			window.setTotalMoney(window.getTotalMoney() - window.getBidAmount()*2);
			endGameLabel("Both lose");
		}
		else if (dealerHand.score() < hand.score() && dealerHand.score() < splitHand.score())
		{
			window.setTotalMoney(window.getTotalMoney() + window.getBidAmount()*2);
			endGameLabel("Both win");
		}
		else if (dealerHand.score() > hand.score() && dealerHand.score() < splitHand.score())
		{
			endGameLabelSplitOccured("Hand 1 loses", 1);
			endGameLabelSplitOccured("Hand 2 wins", 3);
			playAgainButton();
		}
		else if (dealerHand.score() > hand.score() && dealerHand.score() == splitHand.score())
		{
			window.setTotalMoney(window.getTotalMoney() - window.getBidAmount());
			endGameLabelSplitOccured("Hand 1 loses", 1);
			endGameLabelSplitOccured("Hand 2 ties", 3);
			playAgainButton();
		}
		else if (dealerHand.score() < hand.score() && dealerHand.score() > splitHand.score())
		{
			endGameLabelSplitOccured("Hand 1 wins", 1);
			endGameLabelSplitOccured("Hand 2 loses", 3);
			playAgainButton();
		}
		else if (dealerHand.score() == hand.score() && dealerHand.score() > splitHand.score())
		{
			window.setTotalMoney(window.getTotalMoney() - window.getBidAmount());
			endGameLabelSplitOccured("Hand 1 ties", 1);
			endGameLabelSplitOccured("Hand 2 loses", 3);
			playAgainButton();
		}
	}
	private void bust()
	{
		if (hand.score() > 21 && splitHand.score() == 0)
		{
			lostGame();
		}
//		when split occurs
		else if(hand.score() > 21 && !playSplit)
		{
			window.setTotalMoney(window.getTotalMoney() - window.getBidAmount());
			endGameLabelSplitOccured("Hand 1 lost", 1);
			makeSplitPlayable();
		}
		else if (splitHand.score() > 21)
		{
			window.setTotalMoney(window.getTotalMoney() - window.getBidAmount());
			endGameLabelSplitOccured("Hand 2 lost", 3);
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			doubleDownButton.setEnabled(false);
			splitButton.setEnabled(false);
			playAgainButton();
		}
		else if (dealerHand.score() > 21 && splitHand.score() == 0)
		{
			winGame();
		}
		else if (dealerHand.score() > 21 && hand.score() > 21)
		{
			window.setTotalMoney(window.getTotalMoney() + window.getBidAmount());
			endGameLabelSplitOccured("Second hand wins", 1);
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			doubleDownButton.setEnabled(false);
			splitButton.setEnabled(false);
			playAgainButton();
		}
		else if (dealerHand.score() > 21 && splitHand.score() > 21)
		{
			window.setTotalMoney(window.getTotalMoney() + window.getBidAmount());
			endGameLabelSplitOccured("First hand wins", 1);
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			doubleDownButton.setEnabled(false);
			splitButton.setEnabled(false);
			playAgainButton();
		}
		else if (dealerHand.score() > 21)
		{
			window.setTotalMoney(window.getTotalMoney() + window.getBidAmount()*2);
			endGameLabelSplitOccured("Both hands win", 1);
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			doubleDownButton.setEnabled(false);
			splitButton.setEnabled(false);
			playAgainButton();
		}
	}
	
	
	
	private void makeSplitPlayable()
	{
		playSplit = true;
	}
	
	private void endGameLabelSplitOccured(String gameOverMessage, int row)
	{
//		System.out.println("Hi");

		JLabel gameMessage = (JLabel) gameBoard.getComponent(row * 10 + 4); //row  * 10 + 4 (column 5)
		gameMessage.setText("<html>" + gameOverMessage.replaceAll("\n", "<br/>") + "</html>");
		gameMessage.setBackground(Color.white);
		gameMessage.setHorizontalAlignment(SwingConstants.CENTER);
		gameMessage.setOpaque(true);
//		if (window.getTotalMoney() > 0)
//		{
//			playAgainButton();
//		}
//		else
//		{
//			JLabel label = (JLabel) gameBoard.getComponent(15);//row 1 * 10 + 5(column 6)
//			label.setText("No Money, Go Home");
//			label.setBackground(Color.white);
//			label.setHorizontalAlignment(SwingConstants.CENTER);
//			label.setOpaque(true);
//		}
	}
}
