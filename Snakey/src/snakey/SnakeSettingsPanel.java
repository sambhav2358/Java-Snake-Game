package snakey;

import java.awt.*;
import javax.swing.*;

public class SnakeSettingsPanel extends JPanel{
	SnakeSettingsPanel() {
		
		this.setPreferredSize(new Dimension(300,100));
		
		JLabel moneyText = new JLabel();
		moneyText.setText("Total money: " + ComponentHelper.totalMoney);
		this.add(moneyText);
				
		JPanel horizontalLayout = new JPanel();
		
		JLabel snakeLenght = new JLabel();
		snakeLenght.setText("Current snake lenght: " + ComponentHelper.startSnakeSize + "");
		horizontalLayout.add(snakeLenght);
		
		JButton add = new JButton();
		add.setText("+ (" + (ComponentHelper.startSnakeSize * 5) + " money)");
		
		add.addActionListener(d -> {
			if(ComponentHelper.totalMoney >= ComponentHelper.startSnakeSize * 5) {
				ComponentHelper.totalMoney -= ComponentHelper.startSnakeSize * 5;
				ComponentHelper.startSnakeSize += 1;
				moneyText.setText("Total money: " + ComponentHelper.totalMoney);
				snakeLenght.setText("Current snake lenght: " + ComponentHelper.startSnakeSize + "");
				add.setText("+ (" + (ComponentHelper.startSnakeSize * 5) + " money)");
			}
		});
		horizontalLayout.add(add);
		
		this.add(horizontalLayout);
		
		JButton back = new JButton();
		back.setText("Back");
		back.addActionListener(d -> {
			dispose();
			new MainFrame(new MainPanel());
		});
		this.add(back);
	}
	
	public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}
}
