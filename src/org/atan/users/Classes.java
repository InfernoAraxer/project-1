package org.atan.users;

import java.util.ArrayList;

public class Classes {
	
	public static long nextClassID = 5000000L;
	
	private String className;
	public TeacherAccount teacher;
	private String time;
	private long classID;
	public ArrayList<Integer> assignments;
	
	public Classes(String className, TeacherAccount teacher, String time, ArrayList<Integer> assignments) {
		this.className = className;
		this.teacher = teacher;
		this.time = time;
		this.assignments = assignments;
		
		this.classID = Classes.nextClassID++;
	}
	
	public String getClassName() {
		return className;
	}
	
	public long getClassID() {
		return classID;
	}
	
	public String getTime() {
		return time;
	}
	
	public ArrayList<Integer> getAssignments() {
		return assignments;
	}
	
}
