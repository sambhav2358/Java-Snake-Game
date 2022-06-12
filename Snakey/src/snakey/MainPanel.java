package snakey;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

public class MainPanel extends JPanel{

	MainPanel(){
		
		this.setPreferredSize(new Dimension(200,110));
		
		JButton gameButton = new JButton();
		gameButton.setText("Play Game");
		gameButton.setPreferredSize(new Dimension(170, 30));
		gameButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		        new MainFrame(new GamePanel());
		    }
		});
		
		this.add(gameButton);
		
		JButton snakeColorSettingsButton = new JButton();
		snakeColorSettingsButton.setText("Snake Color Settings");
		snakeColorSettingsButton.setPreferredSize(new Dimension(170, 30));
		snakeColorSettingsButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		        new MainFrame(new SnakeColorPickerPanel());
		    }
		});
		
		this.add(snakeColorSettingsButton);
		
		JButton snakeSettingsButton = new JButton();
		snakeSettingsButton.setText("Snake Size Settings");
		snakeSettingsButton.setPreferredSize(new Dimension(170, 30));
		snakeSettingsButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		        new MainFrame(new SnakeSettingsPanel());
		    }
		});
		
		this.add(snakeSettingsButton);
	
	}
	
	public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}
	
}
