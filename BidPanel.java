package blackJackSplit;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BidPanel extends JPanel implements ActionListener
{
	private JButton button;
	private JTextField textField;
	private JLabel totalMoney;
	private GameWindow window;
	private ArrayList<Card> cards;
	
	public BidPanel(GameWindow window, ArrayList<Card> cards) 
	{
		this.window = window;
		this.cards = cards;
		setBackground(new Color(0, 80, 0));
		setLayout(new FlowLayout());
		totalMoney = new JLabel("You have $" + window.getTotalMoney() + " to bid");
		totalMoney.setForeground(Color.white);
		add(totalMoney);
		textField = new JTextField(20);
	    add(textField);
	    button = new JButton("Place Bid");
	    button.addActionListener(this);
	    add(button);
	}
	
	private static final long serialVersionUID = -8217425366808200808L;

	private void errorFrame(String name)
	{
		JFrame error = new JFrame("error");
		error.setSize(300, 100);
		error.setResizable(false); //makes it so the JFrame cannot be maximized/resized
		error.setLocationRelativeTo(null);
		error.add(new JLabel(name));
		error.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String input = textField.getText();
		window.setBidAmount(0);
		Boolean yesInt = false;
		try {
            int intValue = Integer.parseInt(input);
            window.setBidAmount(intValue);
            yesInt = true;
            // add the int value to the panel or do whatever you want with it...
//            System.out.println("Input: " + intValue);
        } catch (NumberFormatException ex ) {
            // handle the case where the input is not a valid integer
            System.err.println("Input is not a valid integer");
            errorFrame("Input is not a valid integer");
            
        }
		if (yesInt)
		{
			if (window.getBidAmount() <= window.getTotalMoney() && window.getBidAmount() > 0)
			{
				window.removePanel(this);
				window.addPanel(new BoardPanel(window, cards));
			}
			else if (window.getBidAmount() > window.getTotalMoney() || window.getBidAmount() <= 0)
			{
				System.err.println("Input not within 1 and " + window.getTotalMoney());
				errorFrame("Input not within 1 and " + window.getTotalMoney());
			}
		}	
	}

}
