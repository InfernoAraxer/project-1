package org.atan.views;

//add scroll bar if there are too many classes

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.atan.controller.ViewController;
import org.atan.model.ClassList;
import org.atan.model.ClassPanels;
import org.atan.users.Classes;
import org.atan.users.StudentAccount;
import org.atan.users.TeacherAccount;

public class TeacherView extends JPanel implements ActionListener {
	
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton settings;
	private ViewController manager;
	private JButton makeAssignmentButton;
	private ArrayList<Integer> temp;
	private JPanel views;
	private JPanel classList;
	private JLabel createdMessageLabel;
	
	public TeacherView(ViewController manager) {
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
		makeAssignmentButton();
		createSuccessMessage();	
	}
	
	private void createAccountName() {
		accountName = new JLabel("Account Name: ");
		accountName.setBounds(10, 0, 190, 35);
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
		} else if (source.equals(makeAssignmentButton)) {
			manager.goToMakeNewAssignment();
		}
	}
	public void createActiveClasses() {
		this.removeAll();
		init();
		 	views = new JPanel(new CardLayout());
			classList = new JPanel();
			classList.setLayout(new GridLayout(0, 1));
			temp = manager.returnTeacherClasses();
			Collections.sort(temp);
			ArrayList<Integer> newList = removeDuplicates(temp); 
			
			for (int x = 1; x < newList.size(); x++) {
				JPanel view = new JPanel(new CardLayout());
				view.add(new ClassPanels(newList.get(x), false), "CLASS_LIST");
				view.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				classList.add(view);	
			}
			
			views.add(classList);
			views.setBounds(5, 65, 475, Math.min(100 * (newList.size() - 1), 750));
			this.add(views);
	}
	
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) { 

        ArrayList<T> newList = new ArrayList<T>(); 

        for (T element : list) { 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
        return newList; 
    } 
    
	public void makeAssignmentButton() {
		makeAssignmentButton = new JButton("New Assignment");
		makeAssignmentButton.setBounds(200, 10, 100, 40);
		makeAssignmentButton.setMargin(new Insets(0, 0, 0, 0));
		makeAssignmentButton.addActionListener(this);
		
		this.add(makeAssignmentButton);
	}
	
	public void clear() {
		this.removeAll();
		this.init();
	}
	
	public void createSuccessMessage() {
        createdMessageLabel = new JLabel("", SwingConstants.CENTER);
        createdMessageLabel.setBounds(0, 775, 500, 35);
        createdMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        createdMessageLabel.setForeground(Color.GREEN);

        this.add(createdMessageLabel);
	}
	
	public void changeSuccessMessage(String s) {
			createdMessageLabel.setText(s);
	}
    
}