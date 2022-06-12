package snakey;

import java.awt.Component;

import javax.swing.*;

public class MainFrame extends JFrame{
	
	MainFrame(Component clazz){
		this.add(clazz);
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}

}
