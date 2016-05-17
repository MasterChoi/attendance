package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScanSerialNumber {
	String serialNumber;
	String who;
	public ScanSerialNumber(String s, String h){
		serialNumber = s;
		who = h;
	}
	
	public String scanSerialNumber(){
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		String userId = "root";
		String userPass = "root";

		Connection conn;
		String sql="";
		PreparedStatement pstmt;
		String re = null;
		
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			if(who.equals("student")){
				sql = "SELECT StudentId FROM LogInTable WHERE MACID LIKE '"+serialNumber+"'";
			} else if(who.equals("professor")){
				sql = "SELECT ProfessorId FROM ProLoginTable WHERE MACID LIKE '"+serialNumber+"'";
			}
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				re = rs.getString(1);
			}
			if(re==null){
				re = "SerialNumber Nonexist";
			}
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return re;
	}
}
