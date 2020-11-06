package org.atan.views;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

import org.atan.controller.ViewController;

public class LoginView extends JPanel{
	
	private ViewController manager;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel errorMessageLabel;
	private JLabel createdMessageLabel;
	private JRadioButton student;
	private JRadioButton teacher;
	private JRadioButton admin;
	private ButtonGroup g;
	
	public LoginView(ViewController manager) {
		super();
		
		this.manager = manager;
		
		this.createAndShowGui();
	}
	
	private void createTitle() {
		JLabel label = new JLabel("UCVTS Course Management Software", SwingConstants.CENTER);
		label.setBounds(0, 20, 500, 35);
		label.setFont(new Font("Dialog Input", Font.BOLD, 21));
		
		this.add(label);
	}
	
	private void createEmailAddressField() {
		JLabel label = new JLabel("Email Address :", SwingConstants.RIGHT);
		label.setBounds(50, 160, 150, 35);
		label.setLabelFor(emailField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		emailField = new JTextField();
		emailField.setBounds(210, 160, 200, 35);
		
		this.add(label);
		this.add(emailField);
	}
	
	private void createPasswordField() {
		JLabel label = new JLabel("Password :", SwingConstants.RIGHT);
		label.setBounds(100, 200, 100, 35);
		label.setLabelFor(passwordField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(210, 200, 200, 35);
		
		this.add(label);
		this.add(passwordField);
	}
	
	private void createLoginButton() {
		loginButton = new JButton("Login");
		loginButton.setBounds(210,260,200,35);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(loginButton)) {
					toggleCreateAccountMessage(false);
					if (student.isSelected()) {
						String emailAddress = emailField.getText();
						char[] password = passwordField.getPassword();
						
						manager.login(emailAddress, password, 1);
					} else if (teacher.isSelected()) {
						String emailAddress = emailField.getText();
						char[] password = passwordField.getPassword();
						
						manager.login(emailAddress, password, 2);
					} else if (admin.isSelected()) {
						String emailAddress = emailField.getText();
						char[] password = passwordField.getPassword();
						
						manager.login(emailAddress, password, 3);
					} else {
						errorMessageLabel.setText("Please Select An Account Type.");
					}
				}
			}
		});
		this.add(loginButton);
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
	
	public void createAccountCreation() {
		JButton createAccount = new JButton("Create an Account");
		createAccount.setBounds(210, 310, 200, 35);
		createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(createAccount)) {
					manager.createAccount();
				}
			}
		});
		
		this.add(createAccount);
	}
	
	public void showError(boolean error) {
		if (error) {
			errorMessageLabel.setText("Invalid email and/or password.");
	    } else {
	        errorMessageLabel.setText("");
		}
	}
	
	private void createErrorMessageLabel() {
        errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        errorMessageLabel.setBounds(0, 450, 500, 35);
        errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMessageLabel.setForeground(Color.RED);

        this.add(errorMessageLabel);
    }
	
	public void clear() {
		emailField.setText("");
		passwordField.setText("");
		g.clearSelection();
		
		toggleErrorMessage(false);
	}
	
	public void createAndShowGui() {
		this.setLayout(null);
		
		createTitle();
		createPasswordField();
		createEmailAddressField();
		createLoginButton();
		createErrorMessageLabel();
		createAccountType();
		createAccountCreation();
		createAccountMessage();
	}

	public void toggleErrorMessage(boolean show) {
		toggleCreateAccountMessage(false);
		if (show) {
            errorMessageLabel.setText("Invalid Email Address and/or Password.");
        } else {
            errorMessageLabel.setText("");
        }
	}
	
	public void createAccountMessage() {
        createdMessageLabel = new JLabel("", SwingConstants.CENTER);
        createdMessageLabel.setBounds(0, 450, 500, 35);
        createdMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        createdMessageLabel.setForeground(Color.GREEN);

        this.add(createdMessageLabel);
	}
	
	public void toggleCreateAccountMessage(boolean show) {
		if (show) {
			createdMessageLabel.setText("Account Successfully Created");
		} else {
			createdMessageLabel.setText("");
		}
	}
}
