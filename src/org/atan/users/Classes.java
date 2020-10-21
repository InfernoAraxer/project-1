package org.atan.users;

public class Classes {
	
	public static long nextClassID = 5000000L;
	
	private String className;
	private long classID;
	
	public Classes(String className) {
		this.className = className;
		
		this.classID = Classes.nextClassID++;
	}
	
	public String getClassName() {
		return className;
	}
	
	public long getClassID() {
		return classID;
	}
	
}
