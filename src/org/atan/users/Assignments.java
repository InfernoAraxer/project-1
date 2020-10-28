package org.atan.users;

import java.util.ArrayList;

public class Assignments {
	
	public static long nextAssignmentID = 6000000L;

	private String assignmentName;
	private long assignmentID;
	private String desc;
	private String time;
	public String comments;
	
	public Assignments(String assignmentName, String desc, String time, String comments) {
		this.assignmentName = assignmentName;
		this.desc = desc;
		this.time = time;
		this.comments = comments;
		
		this.assignmentID = Assignments.nextAssignmentID++;
	}
	
	public String getComments() {
		return comments;
	}
	
	public String getAssignmentName() {
		return assignmentName;
	}
	
	public long getAssignmentID() {
		return assignmentID;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getTime() {
		return time;
	}
}
