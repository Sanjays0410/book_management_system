package com.cruds.demo;

public class Student {

	String USN;
	String Name;
	
	
	
	public Student(String uSN) {
		super();
		USN = uSN;
	}

	public Student(String uSN, String name) {
		super();
		USN = uSN;
		Name = name;
	}
	
	public String getUSN() {
		return USN;
	}
	
	public void setUSN(String uSN) {
		USN = uSN;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	
	@Override
	public String toString() {
		return "Student [USN=" + USN + ", Name=" + Name + "]";
	}
	
	
	
	
}
