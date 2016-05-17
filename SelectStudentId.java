package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectStudentId {
	String stid;
	
	public SelectStudentId(String i){
		stid = i;
	}
	public String selectStudentId(){
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		String userId = "root";
		String userPass = "root";

		Connection conn;
		String sql;
		PreparedStatement pstmt;
		String re = "";
		
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			//sql = "SELECT StudentId FROM `Attendance` WHERE NFCID LIKE ( SELECT NFCID FROM `NFC` WHERE `DaskID` LIKE '"+stid+"' )";
			sql = "SELECT StudentId, Name FROM Student WHERE StudentId LIKE (SELECT StudentId FROM `Attendance` WHERE NFCID LIKE ( SELECT NFCID FROM `NFC` WHERE `DaskID` LIKE '"+stid+"' ))";
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				re = rs.getString(1);
				re += "#";
				re += rs.getString(2);
			}
			pstmt.close();
			conn.close();
			return re;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "b";
	}
}
