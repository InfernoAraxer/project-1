package org.atan.model;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.users.Classes;
import org.atan.users.*;

public class AssignmentsPanel extends JPanel implements ActionListener{

	public static int addClass;
	private JLabel assignmentName;
	private JLabel assignmentDesc;
	private JLabel time;
	private JLabel taught;
	private JButton comments;
	public static int assignmentIndex;
	private int assignmentID;
	public int x;
	
	public AssignmentsPanel(int x, int y) {
		super();
		
		this.init(x, y);
	}
	
	public void init(int x, int y) {
		this.setLayout(null);
		
		createAssignmentName(GUI.assignments.get(y));
		createAssignmentID(GUI.assignments.get(y));
		createComments();
		addTeacherName(GUI.classes.get(x));
		addTime(GUI.assignments.get(y));
		
	}
	
	private void createAssignmentName(Assignments assignment) {
		assignmentName = new JLabel ("Assignment Name: " + assignment.getAssignmentName());
		assignmentName.setBounds(10, 0, 270, 30);
		assignmentName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignmentName);
	}
	
	private void createAssignmentID(Assignments assignment) {
		assignmentDesc = new JLabel ("Assignment Desc: " + assignment.getDesc());
		assignmentDesc.setBounds(10, 30, 150, 30);
		assignmentDesc.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignmentDesc);
		assignmentID = (int) ((assignment.getAssignmentID()) - 6000000);
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
		time.setBounds(275, 0, 185, 30);
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
			assignmentIndex = assignmentID;
			ViewController.goToComments();
		}
		
	}
}
