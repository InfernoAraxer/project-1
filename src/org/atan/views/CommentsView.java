package org.atan.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
	
	private JFileChooser fileChooser = new JFileChooser();
	
	private JLabel fileName;
	private JButton fileDownload;
	private JButton settings;
	//connect this to assignment later;
	
	public CommentsView(ViewController manager) {
		super();
		
		this.manager = manager;
	}
	
	private void init(int x, boolean isTeacher) {
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
		createSubmitAssignmentButton(isTeacher);
		
		createFileName(x ,isTeacher);
		createDownloadButton(isTeacher);
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
		settings = new JButton("Settings");
		settings.setBounds(305, 10, 90, 40);
		settings.addActionListener(this);
		
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
			if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				java.io.File file = fileChooser.getSelectedFile();
				GUI.files.add(file);
				GUI.assignments.get(AssignmentsPanel.assignmentIndex).fileIndex = GUI.files.size() - 1;
				fileName.setText(file.getName());
			} else {
				//print error that no file was selected
			}
		} else if (source.equals(settings)) {
			manager.settings();
		} else if (source.equals(fileDownload)) {
			//download file here;
		}
		
	}
	
	private void createBackButton() {
		backButton = new JButton("Return to Assignments Screen");
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
		assignmentDesc = new JLabel("<html><p style=\"width:350px, top=120px\">" + "Assigment Description: " + GUI.assignments.get(x).getDesc() + "</p></html>");
		assignmentDesc.setBounds(10, 140, 475, 70);
		assignmentDesc.setFont(new Font("DialogInput", Font.BOLD, 14));
		assignmentDesc.setVerticalAlignment(JLabel.TOP);
		
		this.add(assignmentDesc);
	}
	
	private void createComments(int x) {
		JLabel commentsTitle = new JLabel("Comments", SwingConstants.CENTER);
		commentsTitle.setFont(new Font("DialogInput", Font.BOLD, 20));
		commentsTitle.setBounds(10, 200, 475, 35);
		assignmentComments = new JLabel("<html><p style=\"width:350px\">" + GUI.assignments.get(AssignmentsPanel.assignmentIndex).comments + "<br></p></html>");
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
	
	private void createSubmitAssignmentButton(boolean isTeacher) {
		if (!isTeacher) {
			addAssignment = new JButton("Send Assignment");
			addAssignment.setBounds(340, 730, 140, 40);
			addAssignment.addActionListener(this);
			
			this.add(addAssignment);
		}
	}
	
	public void reset(int x, boolean isTeacher) {
		this.removeAll();
		this.init(x, isTeacher);
	}
	
	public void refreshComments() {
		assignmentComments.setText("<html><p style=\"width:350px\">" + GUI.assignments.get(AssignmentsPanel.assignmentIndex).comments + "<br></p></html>");
		comment.setText("");
	}
	
	private void createFileName(int x, boolean isTeacher) {
		fileName = new JLabel("No File Submitted", SwingConstants.CENTER);
		if (!isTeacher) {
			fileName.setBounds(10, 730, 175, 40);
		} else {
			fileName.setBounds(10, 730, 250, 40);
		}
		if (GUI.assignments.get(x).fileIndex != -1) {
			fileName.setText(GUI.files.get(x).getName());
		}
		
		fileName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(fileName);
	}
	
	private void createDownloadButton(boolean isTeacher) {
		fileDownload = new JButton("Download File");
		if (!isTeacher) {
			fileDownload.setBounds(195, 730, 140, 40);
		} else {
			fileDownload.setBounds(260, 730, 220, 40);
		}
		
		this.add(fileDownload);
		
		//make it so if button was ckicked and no file exit, an error would appear.
		//Also make it so that a file is actually downloaded
	}
	/*
	private void getFileName(boolean save)
    {   System.out.println ("proces get file name");
        FileDialog myFD;
        if(save) myFD = new FileDialog(c,"Save...", FileDialog.SAVE);
                       else myFD = new FileDialog("Open...", FileDialog.LOAD);
        myFD.setVisible(true);
        
    }
    */
	
}
