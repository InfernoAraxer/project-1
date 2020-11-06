package org.atan.model;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.atan.GUI;

public class AssignmentsList extends JPanel{
	
	JPanel classes;
	
	public AssignmentsList(int x) {
		super();
		
		this.init(x);
	}
	
	public void init(int x) {
		this.setLayout(new GridLayout(0, 1, 0, 5));
		
		for (int i = 1; i < GUI.classes.get(x).assignments.size(); i++){
		    addClassToGrid(x, GUI.classes.get(x).assignments.get(i));
		}
		
	}
	
	private void addClassToGrid(int x, int i) {
		JPanel views = new JPanel(new CardLayout());
		
		views.add(new AssignmentsPanel(x, i), "CLASS_PANELS");
		
		views.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.add(views);
	}
	
}
