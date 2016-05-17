package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CheckSubject {
	
	String proId;
//	String[] result;
	ArrayList<String> list = new ArrayList<>();
	int i=0;
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
	String userId = "root";
	String userPass = "root";
	public CheckSubject(String p) {
		// TODO Auto-generated constructor stub
		proId=p;
	}
	public ArrayList<String> check(){
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT Name FROM Subject sub, "
					+ "(SELECT SubjectID FROM Teach WHERE ProfessorID LIKE '"+proId+"')tea "
					+ "WHERE sub.SubjectID LIKE tea.SubjectID";
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
