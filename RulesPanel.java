package blackJackSplit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class RulesPanel extends JPanel implements ActionListener
{
	private JLabel rulesText;
	private JButton button;
	private GameWindow window;
	
	public RulesPanel(GameWindow window, Boolean start)
	{
		this.window = window;
		this.setName("Rules");
		this.setBackground(new Color(255, 255, 255));
		if (start)
		{
			rulesText = new JLabel
			(
				"<html> The Rules Of Blackjack<br>"
			    + "1. You start with $100. You place your bid in an integer amount<br>"
				+ "2. You and the dealer each get two cards, one of the dealer's cards is hidden<br>"
				+ "3. The value of each card is the number, or 10 for  Jack, Queen, and King, 1 and 11 for Aces <br>"
				+ "4. Get as close to 21 as possible. You go over you bust. If you have 21 at the start, then it is BlackJack<br>"
				+ "and you win 3/2 of your bid intead of the usual double. Dealer BlackJack and you lose automatically if you do not have a BlackJack<br>"
				+ "5. You have 4 options. Stand, do not draw a card and test against the dealer. Hit, draw a card, then see if you want to draw again<br>"
				+ "Double down, double your bid and draw only 1 more card, must go against the dealer then. Split, if your <br>"
				+ "two cards are matching you can do this once in a hand, your bid is added to the second hand. <br>"
				+ "6. You can end at anytime by closing the window, or when you run out of money<br>"
				+ "7. Top row of Cards is the dealers, the set below is yours. When you split, the cards will be<br>"
				+ "on the two seperate rows"
					
				+ "</html>"
			);
			rulesText.setHorizontalAlignment(JLabel.CENTER);
			this.add(rulesText, BorderLayout.CENTER);
			this.add(Box.createVerticalStrut(20));
			
			button = new JButton("Play Game");
			button.setBackground(Color.BLACK);
			button.setForeground(Color.WHITE);
			button.setPreferredSize(new Dimension(100, 50));
			button.addActionListener(this);
			
			this.add(new JLabel("                                                                                                                                             "));
			this.add(button, BorderLayout.SOUTH);
		}
		else 
		{
			rulesText = new JLabel
			(
				"<html> The Rules Of Blackjack<br>"
			    + "1. You start with $100. You place your bid<bt> in an integer amount<br>"
				+ "2. You and the dealer each get two cards, <br>one of the dealer's cards is hidden<br>"
				+ "3. The value of each card is the number,<br> or 10 for  Jack, Queen, and King, 1 and 11 for Aces <br>"
				+ "4. Get as close to 21 as possible.<br> You go over you bust.<br> If you have 21 at the start,<br> then it is BlackJack<br>"
				+ "and you win 3/2 of your bid intead<br> of the usual double. Dealer BlackJack and you<br> lose automatically if you do not have a BlackJack<br>"
				+ "5. You have 4 options.<br> Stand, do not draw a card and test against the dealer.<br> Hit, draw a card, then see if you want to draw again<br>"
				+ "Double down, double your bid and draw only 1 <br>more card, must go against the dealer then. <br>Split, if your "
				+ "two cards are matching you can do this<br> once in a hand, your bid is added to the second hand. <br>"
				+ "6. You can end at anytime by closing the window,<br> or when you run out of money<br>"
				+ "7. Top row of Cards is the dealers,<br> the set below is yours.<br> When you split, the cards will be<br>"
				+ "on the two seperate rows"
					
				+ "</html>"
			);
			this.add(rulesText);
		}
//		rulesText.doLayout();
		
		
		

	}
	private static final long serialVersionUID = 1663436701246684421L;

	@Override
	public void actionPerformed(ActionEvent e) 
	{
//		System.out.println("hi");
//		this.frame.remove(this);
		window.removePanel(this);
		JFrame rules = new JFrame("Rules");
		rules.add(new RulesPanel(this.window, false));
		rules.setSize(380, 500);
		rules.setLocation(0, 0);
		rules.setVisible(true);
		rules.setResizable(false);
		Deck deck = new Deck();
		ArrayList<Card> cards = deck.shuffled(); //remove num for final version
		BidPanel bidPanel = new BidPanel(window, cards);
		window.addPanel(bidPanel);
	}

}
