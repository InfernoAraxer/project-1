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
import org.atan.users.*;

public class AssignmentsPanel extends JPanel implements ActionListener{

	public static int addClass;
	private JLabel assignmentName;
	private JLabel assignmentID;
	private JLabel time;
	private JLabel taught;
	private JButton comments;
	private ViewController manager;
	private static TeacherAccount teacher;
	public int x;
	private static String[] times = { "", "A 1/2", "A 3/4", "A 7/8", "A 9/10", "B 1/2", "B 3/4", "B 7/8", "B 9/10"};
	
	public AssignmentsPanel(int i, int j) {
		super();
		
		this.init(i, j);
	}
	
	public void init(int x, int y) {
		this.setLayout(null);
		
		createAssignmentName(GUI.assignments.get(GUI.classes.get(x).assignments.get(y)));
		createAssignmentID(GUI.assignments.get(GUI.classes.get(x).assignments.get(y)));
		createComments();
		addTeacherName(GUI.classes.get(x));
		addTime(GUI.assignments.get(GUI.classes.get(x).assignments.get(y)));
	}
/*	
	public static void giveTeacherAccount(TeacherAccount teacher) {
		ClassPanels.teacher = teacher;
	}
*/
	
	private void createAssignmentName(Assignments assignment) {
		assignmentName = new JLabel ("Assignment Name: " + assignment.getAssignmentName());
		assignmentName.setBounds(10, 0, 275, 30);
		assignmentName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignmentName);
	}
	
	private void createAssignmentID(Assignments assignment) {
		assignmentID = new JLabel ("Assignment Desc: " + assignment.getDesc());
		assignmentID.setBounds(10, 30, 150, 30);
		assignmentID.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignmentID);
	}
	
	private void createComments() {
		comments = new JButton ("Expand and View Comments");
		comments.setBounds(10, 60, 455, 30);
		comments.setFont(new Font("DialogInput", Font.BOLD, 14));
		comments.addActionListener(this);
		
		this.add(comments);
	}
	
	private void addTime(Assignments assignment) {
		time = new JLabel ("Due On: " + assignment.getTime(), SwingConstants.RIGHT);
		time.setBounds(285, 0, 100, 30);
		time.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(time);
	}
	
	private void addTeacherName(Classes classes) {
		taught = new JLabel ("Given By: " + classes.teacher.getName(), SwingConstants.RIGHT);
		taught.setBounds(160, 29, 300, 30);
		taught.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(taught);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		
		if (source.equals(comments)) {
			System.out.print("Class ID: \n");
			//manager.checkAssignments();
		}
		
	}
}
