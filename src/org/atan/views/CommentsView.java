package org.atan.views;

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

import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.model.AssignmentsPanel;
import org.atan.users.AdminAccount;
import org.atan.users.StudentAccount;
import org.atan.users.TeacherAccount;

public class CommentsView extends JPanel implements ActionListener{
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton backButton;
	private ViewController manager;
	
	private JLabel assignmentName;
	private JLabel assignmentDueDate;
	private JLabel assignmentDesc;
	private JLabel assignmentComments;
	private JTextField comment;
	private JButton sendComment;
	private JButton addAssignment;
	
	public CommentsView(ViewController manager) {
		super();
		
		this.manager = manager;
	}
	
	private void init(int x) {
		this.setLayout(null);
		
		createAccountName();
		createAccountID();
		createLogoutButton();
		createSettingsIcon();
		createBackButton();
		
		
		createName(x);
		createDueDate(x);
		createDescription(x);
		createComments(x);
		addMessageBox();
		createSendCommentButton();
		createSubmitAssignmentButton();
	}
	
	private void createAccountName() {
		accountName = new JLabel("Account Name: ");
		accountName.setBounds(10, 0, 490, 35);
		accountName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(accountName);
	}
	
	private void createAccountID() {
		accountID = new JLabel("Account ID: ");
		accountID.setBounds(10, 20, 490, 35);
		accountID.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		JSeparator divider = new JSeparator();
		divider.setBounds(0, 55, 500, 10);
		
		this.add(accountID);
		this.add(divider);
	}
	
	private void createLogoutButton() {
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(400, 10, 80, 40);
        logoutButton.addActionListener(this);

        this.add(logoutButton);
    }
	
	private void createSettingsIcon() {
		JButton settings = new JButton("Settings");
		settings.setBounds(305, 10, 90, 40);
		this.add(settings);
	}
	
	public void populateStudent(StudentAccount StudentAccount) {
		accountName.setText("Account Name: " + StudentAccount.getName());
		accountID.setText("Account ID: " + StudentAccount.getStudentID());
	}
	
	public void populateTeacher(TeacherAccount TeacherAccount) {
		accountName.setText("Account Name: " + TeacherAccount.getName());
		accountID.setText("Account ID: " + TeacherAccount.getTeacherID());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(logoutButton)) {
			manager.logout();
		} else if (source.equals(sendComment)) {
			String comment = this.comment.getText();
			ViewController.addComment(comment);
		} else if (source.equals(addAssignment)) {
			
		}
		
	}
	
	private void createBackButton() {
		backButton = new JButton("Return to Main Screen");
		backButton.setBounds(5, 775, 475, 50);
	
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(backButton)) {
					ViewController.checkAssignments();
				}
			}
		});
		
		this.add(backButton);
	}
	
	private void createName(int x) {
		assignmentName = new JLabel("Assignment Name: " + GUI.assignments.get(x).getAssignmentName());
		assignmentName.setBounds(10, 60, 490, 35);
		assignmentName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignmentName);
	}
	
	private void createDueDate(int x) {
		assignmentDueDate = new JLabel("Assignment Due Date: " + GUI.assignments.get(x).getTime());
		assignmentDueDate.setBounds(10, 100, 490, 35);
		assignmentDueDate.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignmentDueDate);
	}
	
	private void createDescription(int x) {
		//set restriction if desc is too long;
		assignmentDesc = new JLabel("<html><p style=\"width:350px, top=120px\">" + "Assigment Description: " + GUI.assignments.get(x).getDesc() + "</p></html>");
		assignmentDesc.setBounds(10, 120, 475, 70);
		assignmentDesc.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(assignmentDesc);
	}
	
	private void createComments(int x) {
		JLabel commentsTitle = new JLabel("Comments", SwingConstants.CENTER);
		commentsTitle.setFont(new Font("DialogInput", Font.BOLD, 20));
		commentsTitle.setBounds(10, 200, 475, 35);
		assignmentComments = new JLabel("<html><p style=\"width:350px\" align:\"top\">" + GUI.assignments.get(AssignmentsPanel.assignmentIndex).comments + "<br></p></html>");
		assignmentComments.setBounds(10, 230, 490, 440);
		assignmentComments.setFont(new Font("DialogInput", Font.BOLD, 14));
		assignmentComments.setVerticalAlignment(JLabel.TOP);
		
		this.add(assignmentComments);
		this.add(commentsTitle);
	}
	
	private void addMessageBox() {
		comment = new JTextField();
		comment.setBounds(5, 690, 390, 35);
		this.add(comment);
	}
	
	private void createSendCommentButton() {
		sendComment = new JButton("Send");
		sendComment.setBounds(400, 690, 80, 35);
		sendComment.addActionListener(this);
		
		this.add(sendComment);
	}
	
	private void createSubmitAssignmentButton() {
		addAssignment = new JButton("Send Assignment");
		addAssignment.setBounds(5, 730, 475, 40);
		addAssignment.addActionListener(this);
		
		this.add(addAssignment);
	}
	
	public void reset(int x) {
		this.removeAll();
		this.init(x);
	}
	
	public void refreshComments() {
		assignmentComments.setText("<html><p style=\"width:350px\" align=\"top\">" + GUI.assignments.get(AssignmentsPanel.assignmentIndex).comments + "<br></p></html>");
		comment.setText("");
	}
}
