package org.atan.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.model.AssignmentsPanel;
import org.atan.model.ClassPanels;
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
	private JComboBox<String> studentSelection;
	private JButton selectStudent;
	
	public CommentsView(ViewController manager) {
		super();
		
		this.manager = manager;
	}
	
	private void init(int x, boolean isTeacher, StudentAccount studentAccount) {
		this.setLayout(null);
		
		createAccountName();
		createAccountID();
		createLogoutButton();
		createSettingsIcon();
		createBackButton();
		
		
		createName(x);
		createDueDate(x);
		createDescription(x);
		createComments(x, isTeacher);
		addMessageBox();
		createSendCommentButton();
		createSubmitAssignmentButton(isTeacher);
		
		createFileName(x ,isTeacher, studentAccount);
		createDownloadButton(isTeacher);
		
		createStudentSelection(isTeacher);
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
				String assignmentID = Long.toString(AssignmentsPanel.assignmentIndex + 6000000);
				assignmentID += Long.toString(GUI.files.size() - 1);
				(GUI.students.get((int) (manager.getActiveStudentUser().getStudentID() - 1000001))).fileIndex.add(assignmentID);
				fileName.setText(file.getName());
			} else {

			}
		} else if (source.equals(settings)) {
			manager.settings();
		} else if (source.equals(fileDownload)) {
			try {
				JFileChooser fc = new JFileChooser(GUI.files.get(GUI.assignments.get(AssignmentsPanel.assignmentIndex).fileIndex));
				fc.setDialogTitle("Save a File");
				fc.showSaveDialog(null);
			} catch (Exception e1) {

			}
		} else if (source.equals(selectStudent)) {
			for (int x = 0; x < GUI.students.size(); x++) {
				if (GUI.students.get(x).getName().equals(studentSelection.getSelectedItem())) {
					for (int y = 1; y < GUI.students.get(x).fileIndex.size(); y++) {
						if (GUI.students.get(x).fileIndex.get(y).substring(0,7).equals(Long.toString(AssignmentsPanel.assignmentIndex + 6000000))) {
							int fileIndex = Integer.parseInt(GUI.students.get(x).fileIndex.get(y).substring(7));
							fileName.setText(GUI.files.get(fileIndex).getName());
							return;
						}
					}
				}
			}
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
	
	private void createComments(int x, boolean isTeacher) {
		JLabel commentsTitle = new JLabel("Comments", SwingConstants.CENTER);
		commentsTitle.setFont(new Font("DialogInput", Font.BOLD, 20));
		commentsTitle.setBounds(10, 200, 475, 35);
		assignmentComments = new JLabel("<html><p style=\"width:350px\">" + GUI.assignments.get(AssignmentsPanel.assignmentIndex).comments + "<br></p></html>");
		if (!isTeacher) {
			assignmentComments.setBounds(10, 230, 490, 440);
		} else {
			assignmentComments.setBounds(10, 230, 450, 440);
		}
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
	
	public void reset(int x, boolean isTeacher, StudentAccount studentAccount) {
		this.removeAll();
		this.init(x, isTeacher, studentAccount);
	}
	
	public void refreshComments() {
		assignmentComments.setText("<html><p style=\"width:350px\">" + GUI.assignments.get(AssignmentsPanel.assignmentIndex).comments + "<br></p></html>");
		comment.setText("");
	}
	
	private void createFileName(int x, boolean isTeacher, StudentAccount studentAccount) {
		fileName = new JLabel("No File Submitted", SwingConstants.CENTER);
		if (!isTeacher) {
			fileName.setBounds(10, 730, 175, 40);
		} else {
			fileName.setBounds(10, 730, 250, 40);
		}
		if (studentAccount != null) {
			for(int x1 = 1; x1 < studentAccount.fileIndex.size(); x1++) {
				if(studentAccount.fileIndex.get(x1).substring(0,7).equals(Long.toString(AssignmentsPanel.assignmentIndex + 6000000))) {
					int fileIndex = Integer.parseInt(studentAccount.fileIndex.get(x1).substring(7));
					fileName.setText(GUI.files.get(fileIndex).getName());
				}
			}
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
		fileDownload.addActionListener(this);
		
		this.add(fileDownload);
	}
	
	private void createStudentSelection(boolean isTeacher) {
		ArrayList<String> studentNamesArray = new ArrayList<>();
		
		if (isTeacher) {
			for(int x = 0; x < GUI.students.size(); x++) {
				for(int y = 1; y < GUI.students.get(x).classes.size(); y++) {
					if (GUI.students.get(x).classes.get(y) == ClassPanels.assignmentClass) {
						studentNamesArray.add(GUI.students.get(x).getName());
					}
				}
			}
			String[] studentsName = new String[studentNamesArray.size() + 1];
			studentsName[0] = "";
			for(int x = 1; x < studentNamesArray.size() + 1; x++) {
				studentsName[x] = studentNamesArray.get(x - 1);
			}
			
			studentSelection = new JComboBox<String>(studentsName);
			studentSelection.setBounds(5, 650, 350, 35);
			
			selectStudent = new JButton("Select");
			selectStudent.setBounds(360, 650, 120, 35);
			selectStudent.addActionListener(this);
			
			this.add(studentSelection);
			this.add(selectStudent);
		}
		
	}
    
}
