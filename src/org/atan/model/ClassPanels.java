package org.atan.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import org.atan.GUI;
import org.atan.users.Classes;


public class ClassPanels extends JPanel{
	JLabel className;
	JLabel classID;
	JButton assignments;
	
	public ClassPanels(int i) {
		super();
		
		this.init(i);
	}
	
	public void init(int x) {
		this.setLayout(null);
		
		createClassName(GUI.classes.get(x));
		createClassID(GUI.classes.get(x));
		createCheckAssignments();
		addTeacherPicture();
		addTeacherName();
		addTimeSlot();
	}
	
	private void createClassName(Classes classes) {
		className = new JLabel ("Class Name: " + classes.getClassName());
		className.setBounds(10, 0, 300, 30);
		className.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(className);
	}
	
	private void createClassID(Classes classes) {
		classID = new JLabel ("Class ID: " + classes.getClassID());
		classID.setBounds(10, 30, 300, 30);
		classID.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(classID);
	}
	
	private void createCheckAssignments() {
		assignments = new JButton ("Check Assignments");
		assignments.setBounds(10, 60, 200, 30);
		assignments.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignments);
	}
	
	private void addTeacherPicture() {
		// add later
	}
	
	private void addTeacherName() {
		// add later
	}
	
	private void addTimeSlot() {
		// add later
	}
}
