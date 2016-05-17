package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Modify {
	String studentId, date, hour, subjectName,state;
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
	String userId = "root";
	String userPass = "root";
//	ArrayList<AttendanceData> list = new ArrayList<>();
	ArrayList<ArrayList<String>> list = new ArrayList<>();
	ArrayList<String> cList = new ArrayList<>();
	public Modify(String a, String b, String c, String d, String e) {
		// TODO Auto-generated constructor stub
		studentId = a;
		date = b;
		hour = c;
		subjectName = d;
		state = e;
	}
		
	public void dbUpdate(){
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "UPDATE Attendance SET State='"+state+"' "
					+ "WHERE StudentID LIKE '"+studentId+"' AND Date LIKE '"+date+"' AND Hour LIKE '"+hour+"' "
					+ "AND SubjectID LIKE (SELECT SubjectID FROM Subject WHERE Name LIKE '"+subjectName+"')"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
	

}
