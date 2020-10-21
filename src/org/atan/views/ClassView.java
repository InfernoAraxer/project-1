package org.atan.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import org.atan.model.ClassList;
import org.atan.model.ClassPanels;
import org.atan.users.Classes;
import org.atan.users.StudentAccount;

public class ClassView extends JPanel{
	
	private JLabel accountName;
	private JLabel accountID;
	private JButton logoutButton;
	
	public ClassView() {
		super();
		
		this.init();
	}
	
	private void init() {
		this.setLayout(null);
		
		createAccountName();
		createAccountID();
		createLogoutButton();
		createClassList();
	}
	
	private void createAccountName() {
		accountName = new JLabel("Account Name: ");
		accountName.setBounds(10, 0, 490, 35);
		accountName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(accountName);
	}
	
	private void createAccountID() {
		accountID = new JLabel("Account ID: ");
		accountID.setBounds(10, 20, 490, 35);
		accountID.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		JSeparator divider = new JSeparator();
		divider.setBounds(0, 55, 500, 10);
		
		this.add(accountID);
		this.add(divider);
	}
	
	private void createLogoutButton() {
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(400, 10, 80, 40);
       // logoutButton.addActionListener(this);

        this.add(logoutButton);
    }
	
	public void createClassList() {
		JPanel views = new JPanel(new CardLayout());
		
		views.add(new ClassList(), "CLASS_LIST");
		views.setBounds(5, 65, 475, 100*(int) (Classes.nextClassID - 5000000));
		this.add(views);
	}
	
	public void populate(StudentAccount StudentAccount) {
		accountName.setText("Account Name: " + StudentAccount.getName());
		accountID.setText("Account ID: " + StudentAccount.getStudentID());
	}
	/*
	public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(actionChooser)) {
            switch (actionChooser.getSelectedIndex()) {
                case 0:
                    clear();
                    toggleDollarAmountField(false);
                    toggleAccountField(false);
    
                    break;
                case 1:
                    // intentionally fall through
                case 2:
                    clear();
                    toggleDollarAmountField(true);
                    toggleAccountField(false);
    
                    break;
                case 3:
                    clear();
                    toggleDollarAmountField(true);
                    toggleAccountField(true);
    
                    break;
            }
        } else if (source.equals(submitButton)) {
            // we'll come back to this
        } else if (source.equals(logoutButton)) {
            // we'll come back to this
        }
    }
	*/
}
