package org.atan.model;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.atan.users.*;


public class ClassList extends JPanel{
	
	JPanel classes;
	
	public ClassList() {
		super();
		
		this.init();
	}
	
	public void init() {
		this.setLayout(new GridLayout(0, 1));
		
		for (int i = 0; i < (int) (Classes.nextClassID - 5000000); i++){
		    addClassToGrid(i);
		}
		addScrollBar();
	}
	
	private void addClassToGrid(int i) {
		JPanel views = new JPanel(new CardLayout());
		
		views.add(new ClassPanels(i, true), "CLASS_PANELS");
		
		views.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.add(views);
	}
	
	private void addScrollBar() {
		
	}
	
	
}
