package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Logout_Professor {
	String id;
	Logout_Professor(String i){
		id = i;
	}
	
	public String logout(){
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		String userId = "root";
		String userPass = "root";

		Connection conn;
		String sql;
		PreparedStatement pstmt;
		String re;
		
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "DELETE FROM ProLoginTable WHERE ProfessorId LIKE '"+id+"'";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
			System.out.println("Logout id : "+id);
			System.out.println("Logout_success");
			return "Logout_success";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Logout_fail";
	}
}
