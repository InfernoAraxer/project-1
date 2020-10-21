package org.atan.users;

public class TeacherAccount {
	
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String emailAddress;
	private static long nextTeacherID = 20000001L;
	private String password;
	private long teacherID;
	
	public TeacherAccount(String firstName, String lastName, long phoneNumber, String emailAddress, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		
		this.teacherID = TeacherAccount.nextTeacherID++;
	}
	
	public String getName() {
		return (firstName + " " + lastName);
	}
	
	public String password() {
		return password;
	}
	
	public long getTeacherID() {
		return teacherID;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
}
