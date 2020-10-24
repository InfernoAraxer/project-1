package org.atan.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
	
	public static int addClass;
	private JLabel className;
	private JLabel classID;
	private JLabel time;
	private JLabel taught;
	private JButton assignments;
	private JButton purchase;
	private ViewController manager;
	private static TeacherAccount teacher;
	public int x;
	private static String[] times = { "", "A 1/2", "A 3/4", "A 7/8", "A 9/10", "B 1/2", "B 3/4", "B 7/8", "B 9/10"};
	
	public ClassPanels(int i, boolean inClass) {
		super();
		
		this.init(i, inClass);
	}
	
	public void init(int x, boolean inClass) {
		this.setLayout(null);
		
		createClassName(GUI.classes.get(x));
		createClassID(GUI.classes.get(x));
		createCheckAssignments();
		addPurchase(inClass);
		addTeacherName(GUI.classes.get(x));
		addTime(GUI.classes.get(x));
	}
	
	public static void giveTeacherAccount(TeacherAccount teacher) {
		ClassPanels.teacher = teacher;
	}
	
	private void createClassName(Classes classes) {
		className = new JLabel ("Class Name: " + classes.getClassName());
		className.setBounds(10, 0, 350, 30);
		className.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(className);
	}
	
	private void createClassID(Classes classes) {
		classID = new JLabel ("Class ID: " + classes.getClassID());
		classID.setBounds(10, 30, 150, 30);
		classID.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(classID);
	}
	
	private void createCheckAssignments() {
		assignments = new JButton ("Check Assignments");
		assignments.setBounds(10, 60, 220, 30);
		assignments.setFont(new Font("DialogInput", Font.BOLD, 14));
		assignments.addActionListener(this);
		
		this.add(assignments);
	}
	
	private void addPurchase(boolean inClass) {
		if (inClass) {
		purchase = new JButton ("Purchase Class");
		purchase.setBounds(235, 60, 230, 30);
		purchase.setFont(new Font("DialogInput", Font.BOLD, 14));
		purchase.addActionListener(this);
		
		this.add(purchase);
		}
	}
	
	private void addTime(Classes classes) {
		time = new JLabel ("Time: " + classes.getTime(), SwingConstants.RIGHT);
		time.setBounds(360, 0, 100, 30);
		time.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(time);
	}
	
	private void addTeacherName(Classes classes) {
		taught = new JLabel ("Taught By: " + classes.teacher.getName(), SwingConstants.RIGHT);
		taught.setBounds(160, 29, 300, 30);
		taught.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(taught);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(purchase)) {
			addClass = Integer.valueOf((classID.getText()).substring(10)) - 5000000;
			ViewController.addClass();
		} else if (source.equals(assignments)) {
			System.out.print("Class ID: " + (classID.getText()).substring(8) + "\n");
		}
		
	}
}
