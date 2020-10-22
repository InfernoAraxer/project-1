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
import javax.swing.JComboBox;
import javax.swing.SwingConstants;


import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.users.Classes;
import org.atan.users.TeacherAccount;


public class ClassPanels extends JPanel implements ActionListener{
	private JLabel className;
	private JLabel classID;
	private JButton assignments;
	private JComboBox scheduleTime;
	private static TeacherAccount teacher;
	private static String[] times = { "", "A 1/2", "A 3/4", "A 7/8", "A 9/10", "B 1/2", "B 3/4", "B 7/8", "B 9/10"};
	
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
	}
	
	public static void giveTeacherAccount(TeacherAccount teacher) {
		ClassPanels.teacher = teacher;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
