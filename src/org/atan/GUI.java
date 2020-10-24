package org.atan;

import java.awt.CardLayout;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.atan.controller.ViewController;
import org.atan.model.ClassList;
import org.atan.model.ClassPanels;
import org.atan.views.*;
import org.atan.users.*;

/*
 * 		JScrollPane scrollPane = new JScrollPane(views);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 50, 500);
 */
@SuppressWarnings("serial")
public class GUI extends JFrame{

	public static final String LOGIN_VIEW = "LOGIN_VIEW";
	public static final String TEACHER_VIEW = "TEACHER_VIEW";
	public static final String ADMIN_VIEW = "ADMIN_VIEW";
	public static final String CLASS_VIEW = "CLASS_VIEW";
	public static final String SETTINGS_VIEW = "SETTINGS_VIEW";
	public static final String ACCOUNT_CREATION_VIEW = "ACCOUNT_CREATION_VIEW";
	public static final String CLASS_SHOP_VIEW = "CLASS_SHOP_VIEW";
	
	public static final int LOGIN_VIEW_INDEX = 0;
	public static final int TEACHER_VIEW_INDEX = 1;
	public static final int ADMIN_VIEW_INDEX = 3;
	public static final int CLASS_VIEW_INDEX = 2;
	public static final int SETTINGS_VIEW_INDEX = 4;
	public static final int ACCOUNT_CREATION_VIEW_INDEX = 5;
	public static final int CLASS_SHOP_VIEW_INDEX = 6;
	
	public static ArrayList<Classes> classes;
	public static ArrayList<AdminAccount> admins;
	public static ArrayList<TeacherAccount> teachers;
	public static ArrayList<StudentAccount> students;
	
	@SuppressWarnings("static-access")
	public GUI() {
		super("UCVTS Course Management Software");
		
		teachers = new ArrayList<TeacherAccount>();
		students = new ArrayList<StudentAccount>();
		admins = new ArrayList<AdminAccount>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		
		
		teachers.add(new TeacherAccount("Ryan", "Wilson", 9083164173L, "r", "p"));
		admins.add(new AdminAccount("God", "Account", 9320482333L, "l", "p"));
		students.add(new StudentAccount("AlexTaneru", "Tan", 9083164190L, "x", "p", temp));
		
		classes = new ArrayList<Classes>();
		
		classes.add(new Classes("Advanced Software Development", teachers.get(0), "A 1/2"));
		classes.add(new Classes("AP Computer Science", teachers.get(0), "A 3/4"));
		classes.add(new Classes("AP Microeconomics", teachers.get(0), "A 7/8"));
		
	}
	
	private void init() {
		JPanel views = new JPanel(new CardLayout());
		ViewController manager = new ViewController(views);
		
		views.add(new LoginView(manager), "LOGIN_VIEW");
		views.add(new TeacherView(manager), "TEACHER_VIEW");
		views.add(new ClassView(manager), "CLASS_VIEW");
		views.add(new AdminView(manager), "ADMIN_VIEW");
		views.add(new SettingsView(manager), "SETTINGS_VIEW");
		views.add(new AccountCreationView(manager), "ACCOUNT_CREATION_VIEW");
		views.add(new ClassShopView(manager), "CLASS_SHOP_VIEW");
		
		this.add(views);
		this.setBounds(100, 100, 500, 1000);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI().init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static StudentAccount lookupUserStudent(String emailAddress, String password) {
		for(StudentAccount studentAccount : students) {
			if(studentAccount.getEmailAddress().equals(emailAddress) && studentAccount.getPassword().equals(password)) {
				return studentAccount;
			}
		}
		return null;
	}
	
	public static TeacherAccount lookupUserTeacher(String emailAddress, String password) {
		for(TeacherAccount teacherAccount : teachers) {
			if(teacherAccount.getEmailAddress().equals(emailAddress) && teacherAccount.getPassword().equals(password)) {
				return teacherAccount;
			}
		}
		return null;
	}
	
	public static AdminAccount lookupUserAdmin(String emailAddress, String password) {
		for(AdminAccount adminAccount : admins) {
			if(adminAccount.getEmailAddress().equals(emailAddress) && adminAccount.getPassword().equals(password)) {
				return adminAccount;
			}
		}
		return null;
	}
}
