package org.atan.users;

import java.util.ArrayList;

public class StudentAccount {
	
	public String firstName;
	public String lastName;
	public long phoneNumber;
	public String emailAddress;
	public static long nextStudentID = 1000001L;
	public String password;
	private long studentID;
	public ArrayList<Integer> classes;
	public ArrayList<String> fileIndex;
	
	public StudentAccount(String firstName, String lastName, long phoneNumber, String emailAddress, String password, ArrayList<Integer> classes, ArrayList<String> fileIndex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		this.classes = classes;
		this.fileIndex = fileIndex;
		
		this.studentID = StudentAccount.nextStudentID++;
	}
	
	public String getName() {
		return (firstName + " " + lastName);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public long getStudentID() {
		return studentID;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public ArrayList<Integer> getClasses() {
		return classes;
	}
	
	public ArrayList<String> getFileIndex() {
		return fileIndex;
	}
}
