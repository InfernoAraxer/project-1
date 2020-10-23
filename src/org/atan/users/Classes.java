package org.atan.users;

public class Classes {
	
	public static long nextClassID = 5000000L;
	
	private String className;
	public TeacherAccount teacher;
	private String time;
	private long classID;
	
	public Classes(String className, TeacherAccount teacher, String time) {
		this.className = className;
		this.teacher = teacher;
		this.time = time;
		
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
	
}
