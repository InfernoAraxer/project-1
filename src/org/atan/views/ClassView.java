package org.atan.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.atan.controller.ViewController;
import org.atan.model.ClassPanels;
import org.atan.users.StudentAccount;

public class ClassView extends JPanel implements ActionListener{
	
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	private JButton settings;
	private JPanel views;
	private JPanel classList;
	private ViewController manager;
	private JButton classShopButton;
	private ArrayList<Integer> temp;
	
	public ClassView(ViewController manager) {
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
		createClassShopButton();
	}
	
	private void createAccountName() {
		accountName = new JLabel("Account Name: ");
		accountName.setBounds(10, 0, 190, 35);
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
	
	public void populate(StudentAccount StudentAccount) {
		accountName.setText("Account Name: " + StudentAccount.getName());
		accountID.setText("Account ID: " + StudentAccount.getStudentID());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(settings)) {
			manager.settings();
		} else if (source.equals(logoutButton)) {
			manager.logout();
		} else if (source.equals(classShopButton)) {
			manager.goToShop();
		}
		
	}
	
	public void createClassShopButton() {
		classShopButton = new JButton("Class Shop");
		classShopButton.setBounds(200, 10, 100, 40);
		classShopButton.addActionListener(this);
		
		this.add(classShopButton);
	}
	
	public void createActiveClasses() {
		this.removeAll();
		init();
		 	views = new JPanel(new CardLayout());
			classList = new JPanel();
			classList.setLayout(new GridLayout(0, 1));
			temp = manager.returnStudentClasses();
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
    
    public void clear() {
		this.removeAll();
		this.init();
	}
    
}