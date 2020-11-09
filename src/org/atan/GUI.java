package org.atan;

import java.awt.CardLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.atan.controller.ViewController;
import org.atan.views.*;
import org.atan.users.*;

@SuppressWarnings("serial")
public class GUI extends JFrame{

	public static final String LOGIN_VIEW = "LOGIN_VIEW";
	public static final String TEACHER_VIEW = "TEACHER_VIEW";
	public static final String ADMIN_VIEW = "ADMIN_VIEW";
	public static final String CLASS_VIEW = "CLASS_VIEW";
	public static final String SETTINGS_VIEW = "SETTINGS_VIEW";
	public static final String ACCOUNT_CREATION_VIEW = "ACCOUNT_CREATION_VIEW";
	public static final String CLASS_SHOP_VIEW = "CLASS_SHOP_VIEW";
	public static final String MAKE_CLASS_VIEW = "MAKE_CLASS_VIEW";
	public static final String ASSIGNMENTS_VIEW = "ASSIGNMENTS_VIEW";
	public static final String MAKE_NEW_ASSIGNMENT_VIEW = "MAKE_NEW_ASSIGNMENT_VIEW";
	public static final String COMMENTS_VIEW = "COMMENTS_VIEW";
	public static final String DELETE_STUDENT_ACCOUNT_VIEW = "DELETE_STUDENT_ACCOUNT_VIEW";
	public static final String DELETE_TEACHER_ACCOUNT_VIEW = "DELETE_TEACHER_ACCOUNT_VIEW";
	
	public static final int LOGIN_VIEW_INDEX = 0;
	public static final int TEACHER_VIEW_INDEX = 1;
	public static final int ADMIN_VIEW_INDEX = 3;
	public static final int CLASS_VIEW_INDEX = 2;
	public static final int SETTINGS_VIEW_INDEX = 4;
	public static final int ACCOUNT_CREATION_VIEW_INDEX = 5;
	public static final int CLASS_SHOP_VIEW_INDEX = 6;
	public static final int MAKE_CLASS_VIEW_INDEX = 7;
	public static final int ASSIGNMENTS_VIEW_INDEX = 8;
	public static final int MAKE_NEW_ASSIGNMENT_VIEW_INDEX = 9;
	public static final int COMMENTS_VIEW_INDEX = 12;
	public static final int DELETE_STUDENT_ACCOUNT_VIEW_INDEX = 10;
	public static final int DELETE_TEACHER_ACCOUNT_VIEW_INDEX = 11;
	
	public static ArrayList<Classes> classes;
	public static ArrayList<AdminAccount> admins;
	public static ArrayList<TeacherAccount> teachers;
	public static ArrayList<StudentAccount> students;
	public static ArrayList<Assignments> assignments;
	public static ArrayList<File> files;

	public GUI() {
		super("UCVTS Course Management Software");
		
		//  - Testing Code and some accounts and classes
		teachers = new ArrayList<TeacherAccount>();
		students = new ArrayList<StudentAccount>();
		admins = new ArrayList<AdminAccount>();
		assignments = new ArrayList<Assignments>();
		files = new ArrayList<File>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(-1);
		ArrayList<String> tempString = new ArrayList<String>();
		tempString.add("-1");
		
		ArrayList<Integer> temp1 = new ArrayList<Integer>();
		temp1.add(-1);
		temp1.add(0);
		
		ArrayList<Integer> temp3 = new ArrayList<Integer>();
		temp3.add(-1);
		
		ArrayList<Integer> temp4 = new ArrayList<Integer>();
		temp4.add(-1);
		
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		temp2.add(-1);
		temp2.add(0);
		
		assignments.add(new Assignments("Project 1", "Make a GUI", "Nov. 1, 2020", ""));
		teachers.add(new TeacherAccount("Ryan", "Wilson", 9083164173L, "rwilson@gmail.com", "p", temp2));
		admins.add(new AdminAccount("God", "Account", 9320482333L, "admin@gmail.com", "p"));
		students.add(new StudentAccount("AlexTaneru", "Tan", 9083164190L, "atan@gmail.com", "p", temp, tempString));
		
		classes = new ArrayList<Classes>();	
		
		classes.add(new Classes("Advanced Software Development", teachers.get(0), "A 1/2", temp1));
		classes.add(new Classes("AP Computer Science", teachers.get(0), "A 1/2", temp3));
		classes.add(new Classes("AP Microeconomics", teachers.get(0), "A 7/8", temp4));
		
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
		views.add(new MakeClassView(manager), "MAKE_CLASS_VIEW");
		views.add(new AssignmentView(manager), "ASSIGNMENTS_VIEW");
		views.add(new MakeNewAssignmentView(manager), "MAKE_NEW_ASSIGNMENT_VIEW");
		views.add(new DeleteStudentAccountView(manager), "DELETE_STUDENT_ACCOUNT_VIEW");
		views.add(new DeleteTeacherAccountView(manager), "DELETE_TEACHER_ACCOUNT_VIEW");
		views.add(new CommentsView(manager), "COMMENTS_VIEW");
		
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
