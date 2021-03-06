package org.atan.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.atan.GUI;
import org.atan.controller.ViewController;

public class AccountCreationView extends JPanel {
	
	private ViewController manager;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField recheckPassword;
	private JButton createAccountButton;
	private JLabel errorMessageLabel;
	private JRadioButton student;
	private JRadioButton teacher;
	private JRadioButton admin;
	private JButton backButton;
	private JTextField phoneNumberField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private ButtonGroup g;
	
	public AccountCreationView (ViewController manager) {
		super();
		
		this.manager = manager;
		
		this.init();
	}
	
	private void init() {
		this.setLayout(null);
		
		createTitle();
		createPasswordFields();
		createEmailAddressField();
		createAccountButton();
		createErrorMessageLabel();
		createAccountType();
		createBackButton();
		createNames();
		createPhoneNumber();
	}
	
	public void clear() {
		emailField.setText("");
		passwordField.setText("");
		recheckPassword.setText("");
		phoneNumberField.setText("");
		firstNameField.setText("");
		lastNameField.setText("");
		g.clearSelection();
		
		toggleErrorMessage(false);
	}
	
	public void createErrorMessageLabel() {
		errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        errorMessageLabel.setBounds(0, 500, 500, 35);
        errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMessageLabel.setForeground(Color.RED);

        this.add(errorMessageLabel);
	}
	
	public void toggleErrorMessage(boolean error) {
		if (error) {
			errorMessageLabel.setText("Please Fill In All Of The Required Blanks");
	    } else {
	        errorMessageLabel.setText("");
		}
	}
	
	private void createAccountType() {    
        JLabel label = new JLabel(
            "Select the account type:",
            SwingConstants.CENTER
        );
        label.setBounds(100, 80, 300, 50);
        
        JPanel radio = new JPanel();
        student = new JRadioButton("Student");
        teacher = new JRadioButton("Teacher");
        admin = new JRadioButton("Admin");
        
        g = new ButtonGroup();
        
        radio.add(student);
        radio.add(teacher);
        radio.add(admin);
      
        g.add(student);
        g.add(teacher);
        g.add(admin);
        
        radio.setBounds(90, 110, 300, 50);
        
        this.add(label, BorderLayout.NORTH);
        this.add(radio, BorderLayout.CENTER);
        
	}
	
	private void createAccountButton() { //fix and edit
		createAccountButton = new JButton("Create Account");
		createAccountButton.setBounds(210,435,200,35);
		
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				
				if (source.equals(createAccountButton)) {
					try {
						String firstName = firstNameField.getText();
						String lastName = lastNameField.getText();
						String emailAddress = emailField.getText().trim();
						Long phoneNumber = Long.parseLong(phoneNumberField.getText());
						char[] password = passwordField.getPassword();
						char[] checkedPassword = recheckPassword.getPassword();
						if (firstName.equals("") || lastName.equals("") || emailAddress.equals("") || String.valueOf(phoneNumber).equals("") || String.valueOf(password).equals("") || String.valueOf(checkedPassword).equals("")) {
							toggleErrorMessage(true);
						} else if (Long.toString(phoneNumber).length() != 10) {
							changeErrorText("Please enter a valid phone number.");
						} else if (!isValid(emailAddress)) {
							changeErrorText("Please enter a valid email address.");
						} else if (isUsed(emailAddress)) {
							changeErrorText("That email address is already used.");
						} else if (!String.valueOf(password).equals(String.valueOf(checkedPassword))) {
							changeErrorText("Passwords are not the same.");
						} else {
							if (student.isSelected()) {
								manager.createAccount(firstName, lastName, emailAddress, phoneNumber, password, checkedPassword, 1);
							} else if (teacher.isSelected()) {
								manager.createAccount(firstName, lastName, emailAddress, phoneNumber, password, checkedPassword, 2);
							} else if (admin.isSelected()) {
								manager.createAccount(firstName, lastName, emailAddress, phoneNumber, password, checkedPassword, 3);
							} else {
								errorMessageLabel.setText("Please Select An Account Type.");
							}
						}
					} catch (Exception e1) {
						changeErrorText("Please fill in all the blanks and enter the correct information into the correct places.");
					}
				}
			}
		});
		this.add(createAccountButton);
	}
	
	private void createPasswordFields() {
		JLabel label = new JLabel("Password :", SwingConstants.RIGHT);
		label.setBounds(50, 330, 150, 35);
		label.setLabelFor(passwordField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(210, 330, 200, 35);
		
		JLabel label2 = new JLabel("Recheck Password :", SwingConstants.RIGHT);
		label2.setBounds(50, 370, 150, 35);
		label2.setLabelFor(recheckPassword);
		label2.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		recheckPassword = new JPasswordField();
		recheckPassword.setBounds(210, 370, 200, 35);
		
		this.add(label);
		this.add(label2);
		this.add(passwordField);
		this.add(recheckPassword);
	}
	
	private void createEmailAddressField() {
		JLabel label = new JLabel("Email Address :", SwingConstants.RIGHT);
		label.setBounds(50, 250, 150, 35);
		label.setLabelFor(emailField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		emailField = new JTextField();
		emailField.setBounds(210, 250, 200, 35);
		
		this.add(label);
		this.add(emailField);
	}
	
	private void createTitle() {
		JLabel label = new JLabel("UCVTS Course Management Software", SwingConstants.CENTER);
		label.setBounds(0, 20, 500, 35);
		label.setFont(new Font("Dialog Input", Font.BOLD, 21));
		
		this.add(label);
	}
	
	private void createBackButton() {
		backButton = new JButton("Return to Login Screen");
		backButton.setBounds(5, 775, 475, 50);
	
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(backButton)) {
					manager.logout();
				}
			}
		});
		
		this.add(backButton);
	}
	
	private void createNames() {
		JLabel firstName = new JLabel ("First Name :",  SwingConstants.RIGHT);
		firstName.setLabelFor(firstNameField);
		JLabel lastName = new JLabel("Last Name :",  SwingConstants.RIGHT);
		lastName.setLabelFor(lastNameField);
		
		firstName.setBounds(50, 170, 150, 35);
		lastName.setBounds(50, 210, 150, 35);
		
		firstName.setFont(new Font("DialogInput", Font.BOLD, 14));
		lastName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstNameField = new JTextField();
		firstNameField.setBounds(210, 170, 200, 35);

		lastNameField = new JTextField();
		lastNameField.setBounds(210, 210, 200, 35);
		
		this.add(firstName);
		this.add(lastName);
		this.add(firstNameField);
		this.add(lastNameField);
	}
	
	private void createPhoneNumber() {
		JLabel phoneNumber = new JLabel ("Phone Number :", SwingConstants.CENTER);
		phoneNumber.setLabelFor(phoneNumberField);
		
		phoneNumber.setBounds(19, 290, 250, 35);
		
		phoneNumber.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phoneNumberField = new JTextField();
		phoneNumberField.setBounds(210, 290, 200, 35);
		
		this.add(phoneNumber);
		this.add(phoneNumberField);
	}
	
	public void changeErrorText(String s) {
		errorMessageLabel.setText(s);
	}
	
	public boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
	
	public boolean isUsed(String email) {
		for (int x = 0; x < GUI.students.size(); x++) {
			if (GUI.students.get(x).getEmailAddress().equals(email)) {
				return true;
			}
		}
		for (int x = 0; x < GUI.teachers.size(); x++) {
			if (GUI.teachers.get(x).getEmailAddress().equals(email)) {
				return true;
			}
		}
		for (int x = 0; x < GUI.admins.size(); x++) {
			if (GUI.admins.get(x).getEmailAddress().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
}
