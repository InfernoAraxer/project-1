package org.atan.controller;

import java.awt.CardLayout;
import java.awt.Container;

import java.util.ArrayList;

import org.atan.GUI;
import org.atan.model.ClassPanels;
import org.atan.users.*;
import org.atan.views.*;

public class ViewController {
	private static Container views;
	private static TeacherAccount activeTeacherUser;
	private static StudentAccount activeStudentUser;
	private AdminAccount activeAdminUser;
	
	public ViewController (Container views) {
		this.views = views;
		ViewController.activeStudentUser = GUI.students.get(0);
		ViewController.activeTeacherUser = GUI.teachers.get(0);
		this.activeAdminUser = null;
	}
	
	public TeacherAccount getActiveTeacherUser() {
		return activeTeacherUser;
	}
	
	public StudentAccount getActiveStudentUser() {
		return activeStudentUser;
	}
	
	public AdminAccount getActiveAdminUser() {
		return activeAdminUser;
	}
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	public static void switchTo1(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}

    public void login(String emailAddress, char[] password, int accountType) {
        activeStudentUser = null;
        activeTeacherUser = null;
        if (accountType == 1) {
        	LoginView lv = ((LoginView) views.getComponents()[GUI.LOGIN_VIEW_INDEX]);
        	try {
        		activeStudentUser = GUI.lookupUserStudent(
    	                String.valueOf(emailAddress),
    	                String.valueOf(password)
    	            );
	            if (activeStudentUser == null) {
	                lv.toggleErrorMessage(true);
	            } else {
	                ((ClassView) views.getComponents()[GUI.CLASS_VIEW_INDEX])
	                    .populate(activeStudentUser);
	                switchTo(GUI.CLASS_VIEW);
	                lv.clear();
	            }
        	} catch (NumberFormatException e) {
        		lv.toggleErrorMessage(true);
        	}
        } else if (accountType == 2) {
        	LoginView lv = ((LoginView) views.getComponents()[GUI.LOGIN_VIEW_INDEX]);
        	try {
        		activeTeacherUser = GUI.lookupUserTeacher(
    	                String.valueOf(emailAddress),
    	                String.valueOf(password)
    	            );
	            if (activeTeacherUser == null) {
	                lv.toggleErrorMessage(true);
	            } else {
	                ((TeacherView) views.getComponents()[GUI.TEACHER_VIEW_INDEX])
	                .createActiveClasses();
	                ((TeacherView) views.getComponents()[GUI.TEACHER_VIEW_INDEX])
                    .populate(activeTeacherUser);
	                switchTo(GUI.TEACHER_VIEW);
	                lv.clear();
	            }
        	} catch (NumberFormatException e) {
        		lv.toggleErrorMessage(true);
        	}
        } else if (accountType == 3) {
        	LoginView lv = ((LoginView) views.getComponents()[GUI.LOGIN_VIEW_INDEX]);
        	try {
        		activeAdminUser = GUI.lookupUserAdmin(
    	                String.valueOf(emailAddress),
    	                String.valueOf(password)
    	            );
	            if (activeAdminUser == null) {
	                lv.toggleErrorMessage(true);
	            } else {
	                ((AdminView) views.getComponents()[GUI.ADMIN_VIEW_INDEX])
	                    .populate(activeAdminUser);
	                switchTo(GUI.ADMIN_VIEW);
	                lv.clear();
	            }
        	} catch (NumberFormatException e) {
                lv.toggleErrorMessage(true);
        	}
        }
    }
    
public void createAccount(String firstName, String lastName, String emailAddress, Long phoneNumber, char[] password, char[] checkedPassword, int accountType) {
	AccountCreationView acv = ((AccountCreationView) views.getComponents()[GUI.ACCOUNT_CREATION_VIEW_INDEX]);
	LoginView lv = ((LoginView) views.getComponents()[GUI.LOGIN_VIEW_INDEX]);
	//also Check Passwords
        if (accountType == 1) {
        	try {
        		ArrayList<Integer> temp = new ArrayList<Integer>();
        		temp.add(1);
        		GUI.students.add(new StudentAccount (firstName, lastName, phoneNumber, emailAddress, String.valueOf(password), temp));
        		acv.toggleErrorMessage(false);
        		acv.clear();
        		switchTo(GUI.LOGIN_VIEW);
        		lv.toggleCreateAccountMessage(true);
        	} catch (NumberFormatException e) {
        		acv.changeErrorText("");
        	}
        } else if (accountType == 2) {
        	try {
        		ArrayList<Integer> temp = new ArrayList<Integer>();
        		temp.add(1);
        		GUI.teachers.add(new TeacherAccount (firstName, lastName, phoneNumber, emailAddress, String.valueOf(password), temp));
        		acv.toggleErrorMessage(false);
        		acv.clear();
        		switchTo(GUI.LOGIN_VIEW);
        		lv.toggleCreateAccountMessage(true);
        	} catch (NumberFormatException e) {
        		acv.changeErrorText("");
        	}
        } else if (accountType == 3) {
        	try {
        		GUI.admins.add(new AdminAccount (firstName, lastName, phoneNumber, emailAddress, String.valueOf(password)));
        		acv.toggleErrorMessage(false);
        		acv.clear();
        		switchTo(GUI.LOGIN_VIEW);
        		lv.toggleCreateAccountMessage(true);
        	} catch (NumberFormatException e) {
        		acv.changeErrorText("");
        	}
        }
	}
    
    public void logout() {
    	switchTo(GUI.LOGIN_VIEW);
    	activeTeacherUser = null;
    	activeStudentUser = null;
    	activeAdminUser = null;
    	((AccountCreationView) views.getComponents()[GUI.ACCOUNT_CREATION_VIEW_INDEX])
    	.clear();
    	
    }
    
    public void settings() {
    	if (activeStudentUser != null) {
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeTeacherUser != null) {
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateTeacher(activeTeacherUser);
    	} else if (activeAdminUser != null) {
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateAdmin(activeAdminUser);
    	}
    	switchTo(GUI.SETTINGS_VIEW);
    }
    
    public void createAccount() {
    	LoginView lv = ((LoginView) views.getComponents()[GUI.LOGIN_VIEW_INDEX]);
    	lv.clear();
    	switchTo(GUI.ACCOUNT_CREATION_VIEW);
    }
    
    public void backToMain() {
    	if (activeStudentUser != null) {
    		((ClassView) views.getComponents()[GUI.CLASS_VIEW_INDEX])
            .populate(activeStudentUser);
    		switchTo(GUI.CLASS_VIEW);
    	} else if (activeTeacherUser != null) {
    		((TeacherView) views.getComponents()[GUI.TEACHER_VIEW_INDEX])
            .populate(activeTeacherUser);
    		switchTo(GUI.TEACHER_VIEW);
    	} else if (activeAdminUser != null) {
    		((AdminView) views.getComponents()[GUI.ADMIN_VIEW_INDEX])
            .populate(activeAdminUser);
    		switchTo(GUI.ADMIN_VIEW);
    	}
    }
    
    public void editFirstName(String s) {
    	if (activeStudentUser != null) {
    		activeStudentUser.firstName = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeTeacherUser != null) {
    		activeTeacherUser.firstName = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeAdminUser != null) {
    		activeAdminUser.firstName = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	}
    }
    
    public void editLastName(String s) {
    	if (activeStudentUser != null) {
    		activeStudentUser.lastName = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeTeacherUser != null) {
    		activeTeacherUser.lastName = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeAdminUser != null) {
    		activeAdminUser.lastName = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	}
    }
    
    public void editEmailAddress(String s) {
    	if (activeStudentUser != null) {
    		activeStudentUser.emailAddress = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeTeacherUser != null) {
    		activeTeacherUser.emailAddress = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeAdminUser != null) {
    		activeAdminUser.emailAddress = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	}
    }
    
    public void editPhoneNumber(Long s) {
    	if (activeStudentUser != null) {
    		activeStudentUser.phoneNumber = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeTeacherUser != null) {
    		activeTeacherUser.phoneNumber = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeAdminUser != null) {
    		activeAdminUser.phoneNumber = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	}
    } //Change so it only accepts 10 digit numbers
    
    public void editPassword(String s) {
    	if (activeStudentUser != null) {
    		activeStudentUser.password = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeTeacherUser != null) {
    		activeTeacherUser.password = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	} else if (activeAdminUser != null) {
    		activeAdminUser.password = s;
    		((SettingsView) views.getComponents()[GUI.SETTINGS_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    	}
    }
    
    public void goToShop() {
    	if (activeStudentUser != null) {
    		((ClassShopView) views.getComponents()[GUI.CLASS_SHOP_VIEW_INDEX])
            .populateStudent(activeStudentUser);
    		switchTo(GUI.CLASS_SHOP_VIEW);
    	} else if (activeAdminUser != null) {
    		((ClassShopView) views.getComponents()[GUI.CLASS_SHOP_VIEW_INDEX])
            .populateAdmin(activeAdminUser);
    		switchTo(GUI.CLASS_SHOP_VIEW);
    	}
    }
    
    
    public ArrayList<Integer> returnStudentClasses() {
    	return activeStudentUser.getClasses();
    }
    
    public ArrayList<Integer> returnTeacherClasses() {
    	return activeTeacherUser.getClasses();
    }
    
    public static void addClass() {
    	activeStudentUser.getClasses().add(ClassPanels.addClass);
    	((ClassView) views.getComponents()[GUI.CLASS_VIEW_INDEX])
    	.createActiveClasses();
    }
    
    public void goToMakeClass() {
    	((MakeClassView) views.getComponents()[GUI.MAKE_CLASS_VIEW_INDEX])
		.clear();
    	((MakeClassView) views.getComponents()[GUI.MAKE_CLASS_VIEW_INDEX])
		.populate(activeAdminUser);
		switchTo(GUI.MAKE_CLASS_VIEW);
    }
    
    public void createNewClass(String className, String classTime, String taughtBy) {
    	int teach = -1;
    	for (int x = 0; x < GUI.teachers.size(); x++) {
    		if (GUI.teachers.get(x).getEmailAddress().equals(taughtBy)) {
    			teach = x;
    		}
    	}
    	ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(-1);
    	GUI.classes.add(new Classes(className, GUI.teachers.get(teach), classTime, temp));
    	switchTo(GUI.ADMIN_VIEW);
    	((ClassShopView) views.getComponents()[GUI.CLASS_SHOP_VIEW_INDEX])
    	.reset();
    	
    	GUI.teachers.get(teach).getClasses().add(GUI.classes.size()-1);
    	((MakeClassView) views.getComponents()[GUI.MAKE_CLASS_VIEW_INDEX]).clear();
    }
    
    public static void checkAssignments() {
    	if (activeStudentUser != null) {
    		((AssignmentView) views.getComponents()[GUI.ASSIGNMENTS_VIEW_INDEX]).createAssignments(ClassPanels.assignmentClass);
    		((AssignmentView) views.getComponents()[GUI.ASSIGNMENTS_VIEW_INDEX]).populateStudent(activeStudentUser);
    		switchTo1(GUI.ASSIGNMENTS_VIEW);
    	} else if (activeTeacherUser != null) {
    		((AssignmentView) views.getComponents()[GUI.ASSIGNMENTS_VIEW_INDEX]).createAssignments(ClassPanels.assignmentClass);
    		((AssignmentView) views.getComponents()[GUI.ASSIGNMENTS_VIEW_INDEX]).populateTeacher(activeTeacherUser);
    		switchTo1(GUI.ASSIGNMENTS_VIEW);
    	}
    }
    
    public void goToMakeNewAssignment() {
    	((MakeNewAssignmentView) views.getComponents()[GUI.MAKE_NEW_ASSIGNMENT_VIEW_INDEX])
		.populate(activeTeacherUser);
		switchTo(GUI.MAKE_NEW_ASSIGNMENT_VIEW);
    }
    
    public void createNewAssignment(String assignmentName, String description, String className, String dueDate) {
    	String classTime = className.substring(className.length() - 5);
    	className = className.substring(0, className.length() - 6);
    	Classes classAssignment = null;
    	for (int x = 0; x < GUI.classes.size(); x++) {
    		if(GUI.classes.get(x).getClassName().equals(className) && GUI.classes.get(x).getTime().equals(classTime)) {
    			classAssignment = GUI.classes.get(x);
    		}
    	}
    	GUI.assignments.add(new Assignments (assignmentName, description, dueDate));
    	switchTo(GUI.TEACHER_VIEW);
    	System.out.println(GUI.classes.get(0).getClassName());
    	int assignmentIndex = GUI.assignments.size() - 1;
    	classAssignment.assignments.add(assignmentIndex);
    	((MakeNewAssignmentView) views.getComponents()[GUI.MAKE_NEW_ASSIGNMENT_VIEW_INDEX]).clear();
    }
    
    public void goToDeleteTeacher() {
    	((DeleteTeacherAccountView) views.getComponents()[GUI.DELETE_TEACHER_ACCOUNT_VIEW_INDEX])
		.clear();
    	((DeleteTeacherAccountView) views.getComponents()[GUI.DELETE_TEACHER_ACCOUNT_VIEW_INDEX])
		.populate(activeAdminUser);
		switchTo(GUI.DELETE_TEACHER_ACCOUNT_VIEW);
    }
    
    public void goToDeleteStudent() {
    	((DeleteStudentAccountView) views.getComponents()[GUI.DELETE_STUDENT_ACCOUNT_VIEW_INDEX])
		.clear();
    	((DeleteStudentAccountView) views.getComponents()[GUI.DELETE_STUDENT_ACCOUNT_VIEW_INDEX])
		.populate(activeAdminUser);
		switchTo(GUI.DELETE_STUDENT_ACCOUNT_VIEW);
    }
    
    public void deleteStudent(String StudentName) {
    	int studentIndex = -1;
    	for(int x = 0; x < GUI.students.size(); x++) {
    		if (GUI.students.get(x).getName().equals(StudentName)) {
    			studentIndex = x;
    		}
    	}
    	GUI.students.remove(studentIndex);
    	switchTo(GUI.ADMIN_VIEW);
    }
    
    public void deleteTeacher(String teacherName) {
    	int teacherIndex = -1;
    	for(int x = 0; x < GUI.teachers.size(); x++) {
    		if (GUI.teachers.get(x).getName().equals(teacherName)) {
    			teacherIndex = x;
    		}
    	}
    	GUI.teachers.remove(teacherIndex);
    	switchTo(GUI.ADMIN_VIEW);
    }
}
