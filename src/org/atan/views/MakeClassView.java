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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.users.AdminAccount;
import org.atan.users.TeacherAccount;

public class MakeClassView extends JPanel implements ActionListener{

	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton settings;
	private ViewController manager;
	private JButton submitButton;

	private JTextField taughtByField;
	private JButton backButton;
	private JLabel errorMessageLabel;
	private JTextField classNameField;
	private JComboBox<String> classTimeBox;
	
	private static final String[] times = {"", "A 1/2", "A 3/4", "A 7/8", "A 9/10", "B 1/2", "B 3/4", "B 7/8", "B 9/10"};
	
	public MakeClassView(ViewController manager) {
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
		createNameAndTime();
		createTaughtBy();
		addSubmitButton();
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
		JLabel label = new JLabel("Make A Class", SwingConstants.CENTER);
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
	
	private void createNameAndTime() {
		JLabel className = new JLabel ("Class Name :",  SwingConstants.RIGHT);
		className.setLabelFor(classNameField);
		JLabel classTime = new JLabel("Class Time :",  SwingConstants.RIGHT);
		classTime.setLabelFor(classTimeBox);
		
		className.setBounds(50, 170, 150, 35);
		classTime.setBounds(50, 210, 150, 35);
		
		className.setFont(new Font("DialogInput", Font.BOLD, 14));
		classTime.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		classNameField = new JTextField();
		classNameField.setBounds(210, 170, 200, 35);

		classTimeBox = new JComboBox<String>(times);
		classTimeBox.setBounds(210, 210, 200, 35);
		
		this.add(className);
		this.add(classTime);
		this.add(classNameField);
		this.add(classTimeBox);
	}
	
	private void createTaughtBy() {
		JLabel taughtBy = new JLabel ("Teacher's Email :", SwingConstants.CENTER);
		taughtBy.setLabelFor(taughtByField);
		
		taughtBy.setBounds(10, 290, 250, 35);
		
		taughtBy.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		taughtByField = new JTextField();
		taughtByField.setBounds(210, 290, 200, 35);
		
		this.add(taughtBy);
		this.add(taughtByField);
	}
	
	public void addSubmitButton() {
		submitButton = new JButton("Create Class");
		submitButton.setBounds(210,435,200,35);
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				
				if (source.equals(submitButton)) {
					try {
						String className = classNameField.getText();
						String classTime = classTimeBox.getSelectedItem().toString();
						String taughtBy = taughtByField.getText();
						
						if (className.equals("") || classTime.equals("") || taughtBy.equals("")) {
							changeErrorText("Fill in all the Blanks1");
							return;
						}
						
						manager.createNewClass(className, classTime, taughtBy);
					} catch (Exception e1) {
						changeErrorText("Fill in all the Blanks");
					}
				}
			}
		});
		this.add(submitButton);
	}
	
	public void clear() {
		classTimeBox.setSelectedItem("");
		classNameField.setText("");
		taughtByField.setText("");
		toggleErrorMessage(false);
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
	
	public void toggleErrorMessage(boolean error) {
		if (error) {
			errorMessageLabel.setText("Please Fill In All Of The Required Blanks");
	    } else {
	        errorMessageLabel.setText("");
		}
	}


}
