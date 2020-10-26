package org.atan.users;

import java.util.ArrayList;

public class Assignments {
	
	public static long nextAssignmentID = 6000000L;

	private String assignmentName;
	private long assignmentID;
	private String desc;
	private String time;
	
	public Assignments(String assignmentName, String desc, String time) {
		this.assignmentName = assignmentName;
		this.desc = desc;
		this.time = time;
		
		this.assignmentID = Assignments.nextAssignmentID++;
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
