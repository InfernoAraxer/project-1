package org.atan.users;

public class TeacherAccount {
	
	public String firstName;
	public String lastName;
	public long phoneNumber;
	public String emailAddress;
	private static long nextTeacherID = 2000001L;
	public String password;
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
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
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
