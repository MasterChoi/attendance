package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CheckStudent {
	String subjectName;
//	String[] result;
	ArrayList<String> list = new ArrayList<>();
	int i=0;
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
	String userId = "root";
	String userPass = "root";
	public CheckStudent(String p) {
		// TODO Auto-generated constructor stub
		subjectName=p;
	}
	public ArrayList<String> check(){
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT c.StudentID FROM Course c,"
					+ "(SELECT SubjectID FROM Subject WHERE Name LIKE '"+ subjectName  +"')sid "
					+ "WHERE c.SubjectID LIKE sid.SubjectID";
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(rs.getString(1));
				i++;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
