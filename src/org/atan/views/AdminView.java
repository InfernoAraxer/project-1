package org.atan.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.atan.controller.ViewController;
import org.atan.users.AdminAccount;

public class AdminView extends JPanel implements ActionListener {
	
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton settings;
	private ViewController manager;
	private JButton makeClassButton;
	private JButton deleteTeacher;
	private JButton deleteStudent;
	private JLabel createdMessageLabel;
	
	public AdminView(ViewController manager) {
		super();
		
		this.manager = manager;
		
		this.init();
	}
	
	private void init() {
		this.setLayout(null);
		
		createAccountName();
		createAccountID();
		createLogoutButton();
		createSettingsIcon();
		makeClassButton();
		createDeleteTeacherButton();
		createDeleteStudentButton();
		createSuccessMessage();	
	}
	
	private void createAccountName() {
		accountName = new JLabel("Account Name: ");
		accountName.setBounds(10, 0, 290, 35);
		accountName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(accountName);
	}
	
	private void createAccountID() {
		accountID = new JLabel("Account ID: ");
		accountID.setBounds(10, 20, 190, 35);
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
	
	public void populate(AdminAccount AdminAccount) {
		accountName.setText("Account Name: " + AdminAccount.getName());
		accountID.setText("Account ID: " + AdminAccount.getAdminID());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(settings)) {
			manager.settings();
		} else if (source.equals(logoutButton)) {
			manager.logout();
		} else if (source.equals(makeClassButton)) {
			manager.goToMakeClass();
		} else if (source.equals(deleteStudent)) {
			manager.goToDeleteStudent();
		} else if (source.equals(deleteTeacher)) {
			manager.goToDeleteTeacher();
		}
	}
	
	public void createDeleteStudentButton() {
		deleteStudent = new JButton("Delete a Student's Account");
		deleteStudent.setBounds(10, 350, 465, 240);
		deleteStudent.addActionListener(this);
		
		this.add(deleteStudent);
	}
	
	public void createDeleteTeacherButton() {
		deleteTeacher = new JButton("Delete a Teacher's Account");
		deleteTeacher.setBounds(10, 600, 465, 240);
		deleteTeacher.addActionListener(this);
		
		this.add(deleteTeacher);
	}
	
	public void makeClassButton() {
		makeClassButton = new JButton("Make a New Class For a Teacher");
		makeClassButton.setBounds(10, 100, 465, 240);
		makeClassButton.addActionListener(this);
		
		this.add(makeClassButton);
	}

	public void createSuccessMessage() {
        createdMessageLabel = new JLabel("", SwingConstants.CENTER);
        createdMessageLabel.setBounds(0, 60, 500, 35);
        createdMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        createdMessageLabel.setForeground(Color.GREEN);

        this.add(createdMessageLabel);
	}
	
	public void toggleCreateClassMessage(boolean show) {
		if (show) {
			createdMessageLabel.setText("Class Successfully Created");
		} else {
			createdMessageLabel.setText("");
		}
	}
	
	public void changeSuccessMessage(String s) {
			createdMessageLabel.setText(s);
	}
	
	public void clear() {
		this.removeAll();
		this.init();
	}
}