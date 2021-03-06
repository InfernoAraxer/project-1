package org.atan.users;

public class AdminAccount {
	public String firstName;
	public String lastName;
	public long phoneNumber;
	public String emailAddress;
	private static long nextAdminID = 3000001L;
	public String password;
	private long adminID;
	
	public AdminAccount(String firstName, String lastName, long phoneNumber, String emailAddress, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		
		this.adminID = AdminAccount.nextAdminID++;
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
	
	public long getAdminID() {
		return adminID;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
}

