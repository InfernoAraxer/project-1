package org.atan.views;

import java.awt.CardLayout;
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
import org.atan.model.ClassList;
import org.atan.users.AdminAccount;
import org.atan.users.Classes;
import org.atan.users.StudentAccount;
import org.atan.users.TeacherAccount;

public class ClassShopView extends JPanel implements ActionListener{

	private ViewController manager;
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton backButton;
	private JButton settings;
	private JLabel errorMessage;
	private JLabel createdMessageLabel;
	
	public ClassShopView(ViewController manager) {
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
		createClassList();
		createErrorMessage();
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
	
	public void populateStudent(StudentAccount StudentAccount) {
		accountName.setText("Account Name: " + StudentAccount.getName());
		accountID.setText("Account ID: " + StudentAccount.getStudentID());
	}
	
	public void populateTeacher(TeacherAccount TeacherAccount) {
		accountName.setText("Account Name: " + TeacherAccount.getName());
		accountID.setText("Account ID: " + TeacherAccount.getTeacherID());
	}
	
	public void populateAdmin(AdminAccount AdminAccount) {
		accountName.setText("Account Name: " + AdminAccount.getName());
		accountID.setText("Account ID: " + AdminAccount.getAdminID());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(logoutButton)) {
			manager.logout();
		} else if (source.equals(settings)) {
			manager.settings();
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
	
	public void createClassList() {
		JPanel views = new JPanel(new CardLayout());
		
		views.add(new ClassList(), "CLASS_LIST");
		views.setBounds(5, 95, 475, 95 * (int) (Classes.nextClassID - 5000000));
		this.add(views);
	}
	
	public void reset() {
		this.removeAll();
		this.init();
	}
	
	private void createErrorMessage() {
		errorMessage = new JLabel("", SwingConstants.CENTER);
        errorMessage.setBounds(0, 60, 500, 35);
        errorMessage.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMessage.setForeground(Color.RED);

        this.add(errorMessage);
	}
	
	public void changeErrorText(String s) {
		errorMessage.setText(s);
	}
	
	public void createSuccessMessage() {
        createdMessageLabel = new JLabel("", SwingConstants.CENTER);
        createdMessageLabel.setBounds(0, 60, 500, 35);
        createdMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        createdMessageLabel.setForeground(Color.GREEN);

        this.add(createdMessageLabel);
	}
	
	public void changeSuccessMessage(String s) {
			createdMessageLabel.setText(s);
	}
}
