package org.atan.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.users.AdminAccount;

public class DeleteTeacherAccountView extends JPanel implements ActionListener{
	
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton settings;
	private ViewController manager;
	private JButton submitButton;

	private JButton backButton;
	private JLabel passwordLabel;
	private JComboBox<String> teacherAccounts;
	private JPasswordField password;
	private JLabel errorMessageLabel;
	
	public DeleteTeacherAccountView (ViewController manager) {
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
		
		createTitle();
		createBackButton();
		createSubmitButton();
		makeTeacherOptions();
		makePasswordField();
		createErrorMessageLabel();
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
		}
	}
	
	private void createTitle() {
		JLabel label = new JLabel("Delete A Teacher Account", SwingConstants.CENTER);
		label.setBounds(0, 75, 500, 35);
		label.setFont(new Font("Dialog Input", Font.BOLD, 21));
		
		this.add(label);
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
	
	private void createSubmitButton() {
		submitButton = new JButton("Delete Account");
		submitButton.setBounds(210,305,200,35);
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				
				if (source.equals(submitButton)) {
					try {
						String teacherName = teacherAccounts.getSelectedItem().toString();
						char[] passwordArray = password.getPassword();
						if (teacherName.equals("") || String.valueOf(passwordArray).equals("")) {
							changeErrorText("Fill in all the blanks");
							return;
						} else if (!String.valueOf(passwordArray).equals(manager.getActiveAdminUser().password)) {
							changeErrorText("That's not the correct password");
							return;
						}
						manager.deleteTeacher(teacherName);
					} catch (Exception e1) {
						
					}
				}
			}
		});
		this.add(submitButton);
	}
	
	private void makeTeacherOptions() {
		JLabel teacherAccountLabel = new JLabel ("Choose a Teacher :",  SwingConstants.RIGHT);
		teacherAccountLabel.setLabelFor(teacherAccounts);
		
		teacherAccountLabel.setBounds(50, 170, 150, 35);
		
		teacherAccountLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		int position = 1;
		String[] teacherNames = new String[GUI.teachers.size() + 1];
		teacherNames[0] = "";
		
		for(int x = 0; x < GUI.teachers.size(); x++) {
			if(GUI.teachers.get(x) != null) {
				teacherNames[position] = GUI.teachers.get(x).getName();
				position++;
			}
		}
		teacherAccounts = new JComboBox<String>(teacherNames);
		teacherAccounts.setBounds(210, 170, 200, 35);

		
		this.add(teacherAccountLabel);
		this.add(teacherAccounts);
		
		//gotta update combobox later
	}
	
	private void makePasswordField() {
		passwordLabel = new JLabel("Enter your Password :", SwingConstants.RIGHT);
		passwordLabel.setLabelFor(password);
		
		passwordLabel.setBounds(0, 210, 200, 35);
		
		passwordLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		password = new JPasswordField();
		password.setBounds(210, 210, 200, 35);
		
		this.add(password);
		this.add(passwordLabel);
	}
	
	public void clear() {
		password.setText("");
		teacherAccounts.setSelectedItem("");
		errorMessageLabel.setText("");
	}
	
	public void createErrorMessageLabel() {
		errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        errorMessageLabel.setBounds(0, 500, 500, 35);
        errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMessageLabel.setForeground(Color.RED);

        this.add(errorMessageLabel);
	}
	
	public void changeErrorText(String s) {
		errorMessageLabel.setText(s);
	}
}
