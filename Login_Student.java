package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login_Student {
	String id, pass;
	String serialNumber;
	Login_Student(String i, String p, String s){
		id = i;
		pass = p;
		serialNumber = s;
	}
	
	public String login(){
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		String userId = "root";
		String userPass = "root";

		Connection conn;
		String sql;
		PreparedStatement pstmt;
		String re = null;
		
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT * FROM `Student` WHERE StudentID LIKE '"+id+"' AND PASSWORD LIKE '"+pass+"'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			int num = 0;
			while(rs.next()){ num++;}
			
			if (num >= 1){
				System.out.println("Insert");
				PreparedStatement pstmt2;
				sql = "INSERT INTO LogInTable (`StudentID`, `MACID`) VALUES ('"+id+"', '"+serialNumber+"')";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.executeUpdate();
				//pstmt2.close();
				return "LoginSuccess";
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "LoginFail";
	}
}
