package org.atan.controller;

import java.awt.CardLayout;
import java.awt.Container;

import org.atan.GUI;
import org.atan.users.*;
import org.atan.views.*;

public class ViewController {
	private Container views;
	private TeacherAccount activeTeacherUser;
	private StudentAccount activeStudentUser;
	private AdminAccount activeAdminUser;
	
	public ViewController (Container views) {
		this.views = views;
		this.activeStudentUser = null;
		this.activeTeacherUser = null;
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

    public void login(String emailAddress, char[] password, int accountType) {
        
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
    
}
