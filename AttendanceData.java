package sampleServer;

import java.io.Serializable;

public class AttendanceData implements Serializable{
	String studentId;
	String date;
	String hour;
	String state;
	
	public AttendanceData(String a, String b, String c, String d){
		studentId = a;
		date = b;
		hour = c;
		state = d;
	}
	public String getStudentId() {
		return studentId;
	}
	public String getDate() {
		return date;
	}
	public String getHour() {
		return hour;
	}

	public String getState() {
		return state;
	}

}
