package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImageUrl {
	String stid;
	ImageUrl(String id){
		stid = id;
	}
	public String imageurl(){
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
			sql = "SELECT Image FROM `Student` WHERE StudentID LIKE '"+stid+"'";
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				re = rs.getString(1);
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
