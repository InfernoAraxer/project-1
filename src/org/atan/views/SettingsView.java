package org.atan.views;

// how to automatically close JFrame when clicked away, ask john
//Check Email validity;


import java.lang.ClassLoader;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.users.AdminAccount;
import org.atan.users.StudentAccount;
import org.atan.users.TeacherAccount;
import org.atan.views.*;

import javax.swing.ImageIcon;

public class SettingsView extends JPanel implements ActionListener{
	
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private ViewController manager;
	private JButton backButton;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel phoneNumber;
	private JLabel emailAddress;
	private JLabel password;
	private JLabel accountType;
	private JButton editFirstName;
	private JButton editLastName;
	private JButton editPhoneNumber;
	private JButton editEmailAddress;
	private JButton editPassword;
	private JPanel panel;
	private JLabel label;
	private JDialog dialog;
	private JButton returnButton;
	private JButton submitButton;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField recheckPasswordField;
	private JLabel errorMessageLabel;
	private JLabel createdMessageLabel;
	
	public SettingsView(ViewController manager) {
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
		createBackButton();
		createEditButtons();
		createFirstName();
		createLastName();
		createPhoneNumber();
		createEmailAddress();
		createPassword();
		createAccountType();
		createSuccessMessage();	
	}
	
	public void clear() {
		this.removeAll();
		this.init();
	}
	
	private void createAccountName() {
		accountName = new JLabel("Account Name: ");
		accountName.setBounds(10, 0, 290, 35);
		accountName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(accountName);
	}
	
	private void createAccountID() {
		accountID = new JLabel("Account ID: ");
		accountID.setBounds(10, 20, 290, 35);
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
	
	private void createFirstName() {
		firstName = new JLabel("First Name: ");
		firstName.setBounds(250, 200, 250, 30);
		firstName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(firstName);
	}
	
	private void createLastName() {
		lastName = new JLabel("Last Name: ");
		lastName.setBounds(250, 240, 250, 30);
		lastName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(lastName);
	}
	
	private void createPhoneNumber() {
		phoneNumber = new JLabel("Phone Number: ");
		phoneNumber.setBounds(250, 280, 250, 30);
		phoneNumber.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(phoneNumber);
	}
	
	private void createEmailAddress() {
		emailAddress = new JLabel("Email Address: ");
		emailAddress.setBounds(250, 320, 250, 30);
		emailAddress.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(emailAddress);
	}
	
	private void createPassword() {
		password = new JLabel("Password: " + "********");
		password.setBounds(250, 360, 250, 30);
		password.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(password);
	}
	
	private void createAccountType() {
		accountType	= new JLabel("Account Type: Admin");
		accountType.setBounds(250, 160, 250, 30);
		accountType.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(accountType);
	}
	
	public void populateAdmin(AdminAccount AdminAccount) {
		accountName.setText("Account Name: " + AdminAccount.getName());
		accountID.setText("Account ID: " + AdminAccount.getAdminID());
		firstName.setText("First Name: " + AdminAccount.getFirstName());
		lastName.setText("Last Name: " + AdminAccount.getLastName());
		phoneNumber.setText("Phone Number: " + AdminAccount.getPhoneNumber());
		emailAddress.setText("Email Address: " + AdminAccount.getEmailAddress());
		password.setText("Password: " + "********");
		accountType.setText("Account Type: Admin");
	}

	public void populateTeacher(TeacherAccount TeacherAccount) {
		accountName.setText("Account Name: " + TeacherAccount.getName());
		accountID.setText("Account ID: " + TeacherAccount.getTeacherID());
		firstName.setText("First Name: " + TeacherAccount.getFirstName());
		lastName.setText("Last Name: " + TeacherAccount.getLastName());
		phoneNumber.setText("Phone Number: " + TeacherAccount.getPhoneNumber());
		emailAddress.setText("Email Address: " + TeacherAccount.getEmailAddress());
		password.setText("Password: " + "********");
		accountType.setText("Account Type: Teacher");
	}
	
	public void populateStudent(StudentAccount StudentAccount) {
		accountName.setText("Account Name: " + StudentAccount.getName());
		accountID.setText("Account ID: " + StudentAccount.getStudentID());
		firstName.setText("First Name: " + StudentAccount.getFirstName());
		lastName.setText("Last Name: " + StudentAccount.getLastName());
		phoneNumber.setText("Phone Number: " + StudentAccount.getPhoneNumber());
		emailAddress.setText("Email Address: " + StudentAccount.getEmailAddress());
		password.setText("Password: " + "********");
		accountType.setText("Account Type: Student");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(logoutButton)) {
			manager.logout();
		} else if (source.equals(editFirstName)) {
			setUpPanel("What do you want to change your first name to?", 1);
		} else if (source.equals(editLastName)) {
			setUpPanel("What do you want to change your last name to?", 2);
		} else if (source.equals(editPhoneNumber)) {
			setUpPanel("What do you want to change your phone number to?", 4);
		} else if (source.equals(editEmailAddress)) {
			setUpPanel("What do you want to change your email address to?", 3);
		} else if (source.equals(editPassword)) {
			setUpPanel("What do you want to change your password to?", 5);
		}
		
	}
	
	private void createBackButton() {
		backButton = new JButton("Return to Main Screen");
		backButton.setBounds(5, 775, 475, 50);
	
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(backButton)) {
					manager.backToMain();
				}
			}
		});
		
		this.add(backButton);
	}
	
	private void createEditButtons() {
		editFirstName = new JButton("Change First Name");
		editLastName = new JButton("Change Last Name");
		editEmailAddress = new JButton("Change Email Address");
		editPhoneNumber = new JButton("Change Phone Number");
		editPassword = new JButton("Change Password");
		
		editFirstName.setBounds(25, 200, 200, 30);
		editLastName.setBounds(25, 240, 200, 30);
		editPhoneNumber.setBounds(25, 280, 200, 30);
		editEmailAddress.setBounds(25, 320, 200, 30);
		editPassword.setBounds(25, 360, 200, 30);
		
		editFirstName.addActionListener(this);
		editLastName.addActionListener(this);
		editEmailAddress.addActionListener(this);
		editPhoneNumber.addActionListener(this);
		editPassword.addActionListener(this);
		
		this.add(editFirstName);
		this.add(editLastName);
		this.add(editEmailAddress);
		this.add(editPhoneNumber);
		this.add(editPassword);
	}
	
	public void setUpPanel(String s, int index) {
		int choice = index;
		dialog = new JDialog();
		panel = new JPanel();
		label = new JLabel(s, SwingConstants.CENTER);
		if (index != 5) {
			textField = new JTextField();
			textField.setBounds(50, 100, 400, 30);
			panel.add(textField);
		} else {
			JLabel password = new JLabel("New Password:");
			password.setBounds(10, 60, 130, 30);
			passwordField = new JPasswordField();
			passwordField.setBounds(150, 60, 300, 30);
			panel.add(passwordField);
			panel.add(password);
			
			JLabel recheckPassword = new JLabel("Re-Enter Password:");
			recheckPassword.setBounds(10, 100, 130, 30);
			recheckPasswordField = new JPasswordField();
			recheckPasswordField.setBounds(150, 100, 300, 30);
			panel.add(recheckPasswordField);
			panel.add(recheckPassword);
		}
		
		label.setBounds(35, 5, 400, 30);
		panel.setLayout(null);
		panel.add(label);
		
		
		returnButton = new JButton("Return");
		returnButton.setBounds(5, 200, 105, 50);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(returnButton)) {
					dialog.dispose();
				} 
			}
		});
		
		errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        errorMessageLabel.setBounds(0, 140, 500, 35);
        errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMessageLabel.setForeground(Color.RED);

        panel.add(errorMessageLabel);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(115, 200, 360, 50);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				if (source.equals(submitButton)) {
					switch (choice) {
						case 1:
							if (textField.getText().equals("")) {
								errorMessageLabel.setText("Please enter a first name.");
								return;
							}
							manager.editFirstName(textField.getText());
							break;
						case 2:
							if (textField.getText().equals("")) {
								errorMessageLabel.setText("Please enter a last name.");
								return;
							}
							manager.editLastName(textField.getText());
							break;
						case 3:
							if (textField.getText().equals("")) {
								errorMessageLabel.setText("Please enter an email address.");
								return;
							}
							manager.editEmailAddress(textField.getText());
							break;
						case 4:
							try {
								String phoneNumber = textField.getText();
								if (textField.getText().equals("")) {
									errorMessageLabel.setText("Please enter a phone number.");
									return;
								}
								
								if (phoneNumber.length() != 10) {
									errorMessageLabel.setText("The phone number is not valid.");
									return;
								}
								manager.editPhoneNumber(Long.parseLong(textField.getText()));
								break;
							} catch (Exception e1) {
								errorMessageLabel.setText("The phone number is not valid.");
								return;
							}
						case 5:
							String password = String.valueOf(passwordField.getPassword());
							String recheckPassword = String.valueOf(recheckPasswordField.getPassword());
							if (password.equals("") || recheckPassword.equals("")) {
								errorMessageLabel.setText("Please fill in all the blanks.");
								return;
							}
							if (!password.equals(recheckPassword)) {
								errorMessageLabel.setText("The Passwords are not the same.");
								return;
							}
							manager.editPassword(password);
							break;
					}
					dialog.dispose();
				} 
			}
		});
		
		panel.add(returnButton);
		panel.add(submitButton);
		
		dialog.setBounds(100, 100, 500, 300);
		dialog.add(panel);
		dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
	}
	
	public void createSuccessMessage() {
        createdMessageLabel = new JLabel("", SwingConstants.CENTER);
        createdMessageLabel.setBounds(0, 500, 500, 35);
        createdMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        createdMessageLabel.setForeground(Color.GREEN);

        this.add(createdMessageLabel);
	}
	
	public void changeSuccessMessage(String s) {
		createdMessageLabel.setText(s);
	}
	
}
