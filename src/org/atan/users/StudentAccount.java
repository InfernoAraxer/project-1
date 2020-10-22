package org.atan.users;

public class StudentAccount {
	
	public String firstName;
	public String lastName;
	public long phoneNumber;
	public String emailAddress;
	private static long nextStudentID = 1000001L;
	public String password;
	private long studentID;
	
	public StudentAccount(String firstName, String lastName, long phoneNumber, String emailAddress, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		
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
}
