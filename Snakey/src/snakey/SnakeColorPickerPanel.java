package snakey;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class SnakeColorPickerPanel extends JPanel{
	
	Color headColor, bodyColor;
	
	SnakeColorPickerPanel(){
		this.setPreferredSize(new Dimension(200,150));
		this.setFocusable(true);
		
		headColor = ComponentHelper.snakeHeadColor;
		bodyColor = ComponentHelper.snakeBodyColor;
		
		JButton pickSnakeBodyColor = new JButton();
		pickSnakeBodyColor.setText("Pick body color");
		pickSnakeBodyColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bodyColor = JColorChooser.showDialog(SnakeColorPickerPanel.this, "Pick a snake body color", Color.MAGENTA);
			}
		});
		
		this.add(pickSnakeBodyColor);
		
		JButton pickSnakeHeadColor = new JButton();
		pickSnakeHeadColor.setText("Pick head color");
		pickSnakeHeadColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(SnakeColorPickerPanel.this, "Pick a snake head color", Color.MAGENTA);
				headColor = color;
			}
		});
		
		this.add(pickSnakeHeadColor);
		
		JLabel emptySpace = new JLabel();
		emptySpace.setPreferredSize(new Dimension(0,70));
		
		this.add(emptySpace);
		
		JButton save = new JButton();
		save.setText("Save");
		save.setBackground(Color.blue);
		repaint();
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComponentHelper.snakeBodyColor = bodyColor;
				ComponentHelper.snakeHeadColor = headColor;
				
				dispose();
				new MainFrame(new MainPanel());
			}
		});
		
		this.add(save);
	}
	
	public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}
	
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
	    g.setColor(headColor);
	    g.fillRect(50, 90, 20, 20);
	    g.setColor(bodyColor);
	    g.fillRect(70, 90, 20, 20);
	    g.fillRect(90, 90, 20, 20);
	    g.fillRect(110, 90, 20, 20);
	    g.fillRect(130, 90, 20, 20);
	    repaint();
	 }
}
