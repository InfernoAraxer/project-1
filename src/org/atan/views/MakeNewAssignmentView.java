package org.atan.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.atan.GUI;
import org.atan.controller.ViewController;
import org.atan.users.TeacherAccount;

public class MakeNewAssignmentView extends JPanel implements ActionListener {
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton settings;
	private ViewController manager;
	private JButton submitButton;

	private JTextField descriptionField;
	private JButton backButton;
	private JLabel errorMessageLabel;
	private JTextField assignmentNameField;
	private JTextField dueDateField;
	private JComboBox<String> classChoicesBox;
	private JLabel classOption;
	
	private String[] classes;
	
	public MakeNewAssignmentView(ViewController manager) {
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
		createNameAndDueDate();
		createDescription();
		addSubmitButton();
		createClassChoices();
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
	
	public void populate(TeacherAccount TeacherAccount) {
		accountName.setText("Account Name: " + TeacherAccount.getName());
		accountID.setText("Account ID: " + TeacherAccount.getTeacherID());
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
		JLabel label = new JLabel("Make An Assignment", SwingConstants.CENTER);
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
	
	private void createNameAndDueDate() {
		JLabel assignmentName = new JLabel ("Assignment Name :",  SwingConstants.RIGHT);
		assignmentName.setLabelFor(assignmentNameField);
		JLabel dueDate = new JLabel("Due Date :",  SwingConstants.RIGHT);
		dueDate.setLabelFor(dueDateField);
		
		assignmentName.setBounds(50, 170, 150, 35);
		dueDate.setBounds(50, 210, 150, 35);
		
		assignmentName.setFont(new Font("DialogInput", Font.BOLD, 14));
		dueDate.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		assignmentNameField = new JTextField();
		assignmentNameField.setBounds(210, 170, 200, 35);
		
		dueDateField = new JTextField();
		dueDateField.setBounds(210, 210, 200, 35);
		
		this.add(assignmentName);
		this.add(dueDate);
		this.add(assignmentNameField);
		this.add(dueDateField);
	}
	
	private void createDescription() {
		JLabel description = new JLabel ("Description :", SwingConstants.CENTER);
		description.setLabelFor(descriptionField);
		
		description.setBounds(22, 290, 250, 35);
		
		description.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		descriptionField = new JTextField();
		descriptionField.setBounds(210, 290, 200, 35);
		
		this.add(description);
		this.add(descriptionField);
	}
	
	public void createClassChoices() {
		classOption = new JLabel("Pick a Class :", SwingConstants.LEFT);
		classOption.setLabelFor(classChoicesBox);
		
		classOption.setBounds(87, 250, 250, 35);
		classOption.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		classes = new String[manager.returnTeacherClasses().size()];
		classes[0] = "";
		for (int x = 1; x < manager.returnTeacherClasses().size(); x++) {
			String addClass = GUI.classes.get(manager.returnTeacherClasses().get(x)).getClassName() + " " + GUI.classes.get((manager.returnTeacherClasses().get(x))).getTime(); 
			classes[x] = addClass;
		}
		
		classChoicesBox = new JComboBox<String>(classes);
		classChoicesBox.setBounds(210, 250, 200, 35);
		
		this.add(classChoicesBox);
		this.add(classOption);
	}
	
	public void addSubmitButton() {
		submitButton = new JButton("Create Assignment");
		submitButton.setBounds(210,435,200,35);
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				try {
					if (source.equals(submitButton)) {
						String assignmentName = assignmentNameField.getText();
						String className = classChoicesBox.getSelectedItem().toString();
						String description = descriptionField.getText();
						String dueDate = dueDateField.getText().trim();
						
						if (assignmentName.equals("") || className.equals("") || description.equals("") || dueDate.equals("")) {
							changeErrorText("Please fill in all the blanks.");
							return;
						} 
						
						int x = 0;
						int y = 0;
						int year = 0;
						String month = "";
						String shortenMonth = "";
						int day = -1;
						do {
							if (dueDate.charAt(x) == ' ') {
								if (dueDate.charAt(x - 1) == '.') {
									x--;
									if (dueDate.charAt(x + 3) == ',') {
										y = x + 3;
									} else {
										y = x + 4;
									}
									day = Integer.parseInt(dueDate.substring(x + 2, y));
								} else {
									if (dueDate.charAt(x + 2) == ',') {
										y = x + 2;
									} else {
										y = x + 3;
									}
									day = Integer.parseInt(dueDate.substring(x + 1, y));
								}
								year = Integer.parseInt(dueDate.substring(dueDate.length() - 4, dueDate.length()));
								month = dueDate.substring(0, x).toUpperCase();
								shortenMonth = month.substring(0,3);
								x = dueDate.length();
							}
							x++;
						} while (x < dueDate.length());
						if (!(checkMonth(month, shortenMonth, day, year))) {
							changeErrorText("<html><p style=\"width:350px, top=120px\">" + "That is not a valid date or check the format.<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(mmm dd, yyyy) Ex: Nove. 7, 2020" + "</p></html>");
							return;
						}
						dueDate = shortenMonth.substring(0,1).toUpperCase() + shortenMonth.substring(1).toLowerCase() + ". " + day + ", " + year; 
						manager.createNewAssignment(assignmentName, description, className, dueDate);
					}
				} catch (Exception e1) {
					changeErrorText("Please check the format for all inputs.");
				}
			}
		});
		this.add(submitButton);
	}
	
	public void clear() {
		classChoicesBox.setSelectedItem("");
		assignmentNameField.setText("");
		descriptionField.setText("");
		dueDateField.setText("");
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
	
	public void reset() {
		this.removeAll();
		this.init();
	}
	
	public boolean checkMonth(String month, String shortenMonth, int day, int year) {
		switch (shortenMonth) {
	        case "JAN":
	            if (month.equals("JAN") || month.equals("JANU") || month.equals("JANUARY")) {
	            	if (day < 1 || day > 31) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "FEB":
	            if (month.equals("FEB") || month.equals("FEBR") || month.equals("FEBRUARY")) {
	            	boolean requirement = false;
	            	if (year % 4 == 0) requirement = true;
	                if (year % 100 == 0) requirement = false;
	                if (year % 400 == 0) requirement = true;
	
	                if (requirement) {
	                	if (day < 1 || day > 29) {
	                		return false;
	                	}
	                } else {
	                	if (day < 1 || day > 28) {
	                		return false;
	                	}
	                }
	            	return true;
	            }
	            return false;
	        case "MAR":
	            if (month.equals("MAR") || month.equals("MARC") || month.equals("MARCH")) {
	            	if (day < 1 || day > 31) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "APR":
	            if (month.equals("APR") || month.equals("APRI") || month.equals("APRIL")) {
	            	if (day < 1 || day > 30) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "MAY":
	            if (month.equals("MAY")) {
	            	if (day < 1 || day > 31) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "JUN":
	            if (month.equals("JUN") || month.equals("JUNE")) {
	            	if (day < 1 || day > 30) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "JUL":
	            if (month.equals("JUL") || month.equals("JULY")) {
	            	if (day < 1 || day > 31) {
	            		return false;
	            	}
	            	return true;
	            }
	        case "AUG":
	            if (month.equals("AUG") || month.equals("AUGU") || month.equals("AUGUST")) {
	            	if (day < 1 || day > 31) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "SEP":
	            if (month.equals("SEP") || month.equals("SEPT") || month.equals("SEPTEMBER")) {
	            	if (day < 1 || day > 30) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "OCT":
	            if (month.equals("OCT") || month.equals("OCTO") || month.equals("OCTOBER")) {
	            	if (day < 1 || day > 31) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "NOV":
	            if (month.equals("NOV") || month.equals("NOVE") || month.equals("NOVEMBER")) {
	            	if (day < 1 || day > 30) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        case "DEC":
	            if (month.equals("DEC") || month.equals("DECE") || month.equals("DECEMBER")) {
	            	if (day < 1 || day > 31) {
	            		return false;
	            	}
	            	return true;
	            }
	            return false;
	        default:
	        	return false;
		}
	}
}
