package org.atan.model;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
		
		views.add(new ClassPanels(i), "CLASS_PANELS");
		
		views.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.add(views);
	}
	
	private void addScrollBar() {
		
	}
	
}
