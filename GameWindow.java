package blackJackSplit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameWindow 
{
	private JFrame f;
	private JPanel rulesPanel;
	private int totalMoney = 100;
	private int bidAmount;
	
	public int getBidAmount() {
		return bidAmount;
	}

	public int getTotalMoney() {
		return totalMoney;
	}
	
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	public GameWindow()
	{
		f = new JFrame("BlackJack");
		f.setSize(1280, 720);
		f.setResizable(false); //makes it so the JFrame cannot be maximized/resized
//		f.setBackground(Color.GREEN);
		this.rulesPanel = new RulesPanel(this, true);
		f.add(rulesPanel);
		
//		f.remove(rulesPanel);//removes panel name
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public void removePanel(JPanel panel)
	{
		f.remove(panel);
		f.repaint();
	}
	public void addPanel(JPanel panel)
	{
		f.add(panel);
		f.setVisible(true);
//		System.out.println("Hello");
	}
	
	public void refresh()
	{
		f.revalidate();
		f.repaint();
	}
}
